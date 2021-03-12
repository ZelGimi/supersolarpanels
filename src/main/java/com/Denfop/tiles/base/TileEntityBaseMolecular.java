package com.Denfop.tiles.base;

import com.Denfop.container.ContainerBaseMolecular;
import ic2.api.network.INetworkTileEntityEventListener;
import ic2.api.recipe.RecipeOutput;
import ic2.core.ContainerBase;
import ic2.core.IC2;
import ic2.core.IHasGui;
import ic2.core.audio.AudioSource;
import ic2.core.block.invslot.InvSlotOutput;
import ic2.core.block.invslot.InvSlotProcessable;
import ic2.core.block.machine.tileentity.TileEntityElectricMachine;
import ic2.core.network.NetworkManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

public abstract class TileEntityBaseMolecular extends TileEntityElectricMachine implements IHasGui, INetworkTileEntityEventListener {
    private static final int EventStart = 0;
    private static final int EventInterrupt = 1;
    private static final int EventStop = 2;
    public final int defaultEnergyConsume;
    public final int defaultOperationLength;
    public final int defaultTier;
    public final int defaultEnergyStorage;
    public final InvSlotOutput outputSlot;
    public int energyConsume;
    public int operationLength;
    public int operationsPerTick;
    public AudioSource audioSource;
    public InvSlotProcessable inputSlot;
    protected int progress;
    protected double guiProgress;
    protected double inputEU;
    private boolean active;


    public TileEntityBaseMolecular(int energyPerTick, int length, int outputSlots) {
        this(energyPerTick, length, outputSlots, 1);
    }

    public TileEntityBaseMolecular(int energyPerTick, int length, int outputSlots, int aDefaultTier) {
        super(0, 10, 1);
        this.progress = 0;
        this.defaultEnergyConsume = this.energyConsume = energyPerTick;
        this.defaultOperationLength = this.operationLength = length;

        this.defaultTier = 11;
        this.defaultEnergyStorage = energyPerTick * length;
        this.outputSlot = new InvSlotOutput(this, "output", 2, 1);
        this.active = false;
    }

    public static int applyModifier(int base, int extra, double multiplier) {
        double ret = Math.round((base + extra) * multiplier);
        return (ret > 2.147483647E9D) ? Integer.MAX_VALUE : (int) ret;
    }

    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        this.progress = nbttagcompound.getInteger("progress");
        this.inputEU = nbttagcompound.getDouble("inputEU");
    }

    public void writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setInteger("progress", this.progress);
        nbttagcompound.setDouble("inputEU", this.inputEU);

    }

    public double getProgress() {
        return this.guiProgress;
    }

    public void onLoaded() {
        super.onLoaded();
        if (IC2.platform.isSimulating())
            setOverclockRates();
    }

    public void onUnloaded() {
        super.onUnloaded();
        if (IC2.platform.isRendering() && this.audioSource != null) {
            IC2.audioManager.removeSources(this);
            this.audioSource = null;
        }
    }

    public void markDirty() {
        super.markDirty();
        if (IC2.platform.isSimulating())
            setOverclockRates();
    }

    protected void updateEntityServer() {
        super.updateEntityServer();
        boolean needsInvUpdate = false;
        RecipeOutput output = getOutput();
        if (output != null) {
            setActive(true);
            this.active = true;
            if (this.progress == 0)
                IC2.network.get().initiateTileEntityEvent(this, 0, true);

            this.progress = (int) this.energy;
            double k = this.progress;
            double p = (k / output.metadata.getInteger("energy"));
            double u = this.inputEU;
            this.inputEU = u;
            if (p <= 1)
                this.guiProgress = p;
            if (p > 1)
                this.guiProgress = 1;
            if (this.progress >= output.metadata.getInteger("energy")) {
                operate(output);
                needsInvUpdate = true;
                this.progress = 0;
                this.energy = 0;

                IC2.network.get().initiateTileEntityEvent(this, 2, true);
            }
        } else {
            if (this.progress != 0 && getActive())
                IC2.network.get().initiateTileEntityEvent(this, 1, true);
            if (output == null)
                this.progress = 0;
            setActive(false);
        }


        if (needsInvUpdate)
            super.markDirty();
    }

    public double injectEnergy(ForgeDirection directionFrom, double amount, double voltage) {
        if (this.energy >= this.maxEnergy)
            return 0;
        this.energy += amount;
        double p = amount;

        this.inputEU = p;

        return 0.0D;
    }

    public void setOverclockRates() {

        double previousProgress = this.progress / this.operationLength;
        double stackOpLen = (this.defaultOperationLength + 0) * 64.0D * 1;
        this.operationsPerTick = (int) Math.min(Math.ceil(64.0D / stackOpLen), 2.147483647E9D);
        this.operationLength = (int) Math.round(stackOpLen * this.operationsPerTick / 64.0D);
        this.energyConsume = applyModifier(this.defaultEnergyConsume, 0, 0);
        setTier(applyModifier(this.defaultTier, 0, 1.0D));
        RecipeOutput output = getOutput();
        if (inputSlot.isEmpty()) {
            this.maxEnergy = 0;
        } else if (output != null) {
            this.maxEnergy = output.metadata.getInteger("energy");
        } else {
            this.maxEnergy = 0;
        }

        this.progress = (short) (int) Math.floor(previousProgress * this.operationLength + 0.1D);


    }

    public void operate(RecipeOutput output) {
        for (int i = 0; i < this.operationsPerTick; i++) {
            List<ItemStack> processResult = output.items;

            operateOnce(output, processResult);
            output = getOutput();
            if (output == null)
                break;
        }
    }

    public void operateOnce(RecipeOutput output, List<ItemStack> processResult) {
        this.inputSlot.consume();
        this.outputSlot.add(processResult);
    }

    public RecipeOutput getOutput() {
        if (this.inputSlot.isEmpty())
            return null;
        RecipeOutput output = this.inputSlot.process();
        if (output == null)
            return null;
        if (this.outputSlot.canAdd(output.items))
            return output;
        return null;
    }

    public abstract String getInventoryName();

    public ContainerBase<? extends TileEntityBaseMolecular> getGuiContainer(EntityPlayer entityPlayer) {
        return (ContainerBase<? extends TileEntityBaseMolecular>) new ContainerBaseMolecular(entityPlayer, this);
    }

    public String getStartSoundFile() {
        return null;
    }

    public String getInterruptSoundFile() {
        return null;
    }

    public void onNetworkEvent(int event) {
        if (this.audioSource == null && getStartSoundFile() != null)
            this.audioSource = IC2.audioManager.createSource(this, getStartSoundFile());
        switch (event) {
            case 0:
                if (this.audioSource != null)
                    this.audioSource.play();
                break;
            case 1:
                if (this.audioSource != null) {
                    this.audioSource.stop();
                    if (getInterruptSoundFile() != null)
                        IC2.audioManager.playOnce(this, getInterruptSoundFile());
                }
                break;
            case 2:
                if (this.audioSource != null)
                    this.audioSource.stop();
                break;
        }
    }

    public double getEnergy() {
        return this.energy;
    }

    public double getPower() {
        return this.inputEU;
    }

    public boolean useEnergy(double amount) {
        if (this.energy >= amount) {
            this.energy -= amount;
            return true;
        }
        return false;
    }

    public boolean getActive() {
        return this.active;
    }

    public void onGuiClosed(EntityPlayer entityPlayer) {
    }


}
