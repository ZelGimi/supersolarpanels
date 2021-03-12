package com.Denfop.tiles.base;

import com.Denfop.api.inv.IInvSlotProcessableMulti;
import com.Denfop.container.ContainerMultiMachine1;
import com.Denfop.gui.GuiMultiMachine1;
import com.Denfop.tiles.Mechanism.EnumMultiMachine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.network.INetworkTileEntityEventListener;
import ic2.api.recipe.RecipeOutput;
import ic2.core.ContainerBase;
import ic2.core.IC2;
import ic2.core.IHasGui;
import ic2.core.audio.AudioSource;
import ic2.core.block.invslot.InvSlotOutput;
import ic2.core.block.invslot.InvSlotUpgrade;
import ic2.core.block.machine.tileentity.TileEntityElectricMachine;
import ic2.core.network.NetworkManager;
import ic2.core.upgrade.IUpgradableBlock;
import ic2.core.upgrade.IUpgradeItem;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.util.List;

public abstract class TileEntityMultiMachine1 extends TileEntityElectricMachine implements IHasGui, INetworkTileEntityEventListener, IUpgradableBlock {
    public final int defaultTier, defaultEnergyStorage, defaultOperationsPerTick, defaultEnergyConsume;
    /**
     * ��������� �������� ������
     */
    public final InvSlotOutput outputSlots;
    /**
     * ��������� ��������� ���������
     */
    public final InvSlotUpgrade upgradeSlot;
    public int expstorage = 0;
    public int operationLength, operationsPerTick, sizeWorkingSlot, energyConsume;

    public AudioSource audioSource;

    /**
     * ��������� ������ ��� ��������� � ������ ������� � ������� ������
     */
    public IInvSlotProcessableMulti inputSlots;
    public int expmaxstorage;
    protected short[] progress;
    protected double[] guiProgress;

    /**
     * ����������� �������� ������ �������� TileEntity � ��������� ��������� ������
     * ������ ������� 1
     */
    public TileEntityMultiMachine1() {
        this(1);
    }

    /**
     * ����������� �������� ������ �������� TileEntity � ��������� ������ ����������
     * ������ ������ �������
     */
    public TileEntityMultiMachine1(int aDefaultTier) {
        super(1, 1, 1);
        this.sizeWorkingSlot = getMachine().sizeWorkingSlot;
        this.progress = new short[sizeWorkingSlot];
        this.guiProgress = new double[sizeWorkingSlot];
        this.defaultEnergyConsume = getMachine().usagePerTick;
        this.defaultOperationsPerTick = getMachine().lenghtOperation;
        this.defaultTier = aDefaultTier;
        this.defaultEnergyStorage = energyConsume * operationLength;
        this.outputSlots = new InvSlotOutput(this, "output", 1, sizeWorkingSlot);
        this.upgradeSlot = new InvSlotUpgrade(this, "upgrade", 4, 4);
        this.maxEnergy = energyConsume * defaultOperationsPerTick;
        this.expmaxstorage = 500;
    }

    public static int recycleChance() {
        return 8;
    }

    public static int applyModifier(int base, int extra, double multiplier) {
        double ret = Math.round((base + extra) * multiplier);
        return (ret > 2.147483647E9D) ? Integer.MAX_VALUE : (int) ret;
    }

    protected abstract EnumMultiMachine getMachine();

    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        for (int i = 0; i < sizeWorkingSlot; i++) {
            this.progress[i] = nbttagcompound.getShort("progress" + i);
        }
        this.expstorage = nbttagcompound.getInteger("expstorage");
    }

    public void writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        for (int i = 0; i < sizeWorkingSlot; i++) {
            nbttagcompound.setShort("progress" + i, progress[i]);
        }
        nbttagcompound.setInteger("expstorage", this.expstorage);
    }

    /**
     * �������� ������� �������� ��� ����������� � GUI
     *
     * @param slotId
     * @return
     */
    public double getProgress(int slotId) {
        return this.guiProgress[slotId];
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
        boolean isActive = false;
        for (int i = 0; i < sizeWorkingSlot; i++) {
            RecipeOutput output = getOutput(i);
            if (output != null && useEnergy((this.energyConsume + this.upgradeSlot.extraEnergyDemand) * this.upgradeSlot.energyDemandMultiplier)) {
                isActive = true;
                if (this.progress[i] == 0)
                    initiate(0);

                this.progress[i]++;
                this.guiProgress[i] = (double) this.progress[i] / this.operationLength;

                if (this.progress[i] >= this.operationLength) {
                    this.guiProgress[i] = 0;
                    this.progress[i] = 0;


                    operate(i, output);
                    needsInvUpdate = true;
                    initiate(2);
                }

            } else {
                if (this.progress[i] != 0 && getActive())
                    initiate(1);
                if (output == null)
                    this.progress[i] = 0;
            }
        }

        if (getActive() != isActive) {
            setActive(isActive);
        }

        for (int i = 0; i < this.upgradeSlot.size(); i++) {
            ItemStack stack = this.upgradeSlot.get(i);
            if (stack != null && stack.getItem() instanceof IUpgradeItem)
                if (((IUpgradeItem) stack.getItem()).onTick(stack, this))
                    needsInvUpdate = true;
        }

        if (needsInvUpdate)
            super.markDirty();

    }

    private void initiate(int soundEvent) {
        IC2.network.get().initiateTileEntityEvent(this, soundEvent, true);
    }

    public void setOverclockRates() {
        double stackOpLen = (this.defaultOperationsPerTick + this.upgradeSlot.extraProcessTime) * 64.0D * this.upgradeSlot.processTimeMultiplier;
        this.operationsPerTick = (int) Math.min(Math.ceil(64.0D / stackOpLen), 2.147483647E9D);
        this.operationLength = (int) Math.max(Math.round(stackOpLen * this.operationsPerTick / 64.0D), 1);
        this.energyConsume = applyModifier(this.defaultEnergyConsume, this.upgradeSlot.extraEnergyDemand, this.upgradeSlot.energyDemandMultiplier);
        this.setTier(applyModifier(this.defaultTier, this.upgradeSlot.extraTier, 1.0D));
        this.maxEnergy = applyModifier(this.defaultEnergyStorage, this.upgradeSlot.extraEnergyStorage + this.operationLength * this.energyConsume, this.upgradeSlot.energyStorageMultiplier);
    }

    /**
     * ������������ ��������� ��� ������, ��� ������ �������� ������
     *
     * @param slotId ����� ���� ���������� �����
     * @param output ����� ������
     */
    public void operate(int slotId, RecipeOutput output) {
        for (int i = 0; i < this.operationsPerTick; i++) {
            operateOnce(slotId, output.items);

            output = getOutput(slotId);
            if (output == null)
                break;
        }
    }

    /**
     * ��������� �������� �� ������ ��������
     *
     * @param slotId        ����� ���� ���������� �����
     * @param processResult ������ ��������� ����������
     */
    public void operateOnce(int slotId, List<ItemStack> processResult) {
        this.inputSlots.consume(slotId);
        if (IC2.random.nextInt(recycleChance()) == 0)
            this.outputSlots.add(processResult);
    }

    /**
     * ��������� ����� ���� � ������ �������� ���� ������ �������
     *
     * @param slotId ����� ���� �����������
     * @return object
     */

    public RecipeOutput getOutput(int slotId) {
        if (this.inputSlots.isEmpty(slotId))
            return null;
        RecipeOutput output = this.inputSlots.process(slotId);
        if (output == null)
            return null;
        if (this.outputSlots.canAdd(output.items))
            return output;

        return null;
    }

    public abstract String getInventoryName();

    public ContainerBase<? extends TileEntityMultiMachine1> getGuiContainer(EntityPlayer entityPlayer) {
        return new ContainerMultiMachine1(entityPlayer, this, this.sizeWorkingSlot);
    }

    @SideOnly(Side.CLIENT)
    public GuiScreen getGui(EntityPlayer entityPlayer, boolean isAdmin) {
        return new GuiMultiMachine1(new ContainerMultiMachine1(entityPlayer, this, sizeWorkingSlot));
    }

    /**
     * �������� ����� ������/������ �������
     *
     * @return
     */
    public String getStartSoundFile() {
        return null;
    }

    /**
     * �������� ����� ������ ������� (��������: ���-�� ������ �� �������� �����
     * ��������, � ������������ �������)
     *
     * @return
     */
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

    public int getMode() {
        return 0;
    }

    /**
     * ����� �������
     */
    public double getEnergy() {
        return this.energy;
    }

    /**
     * ������������ �������
     */
    public boolean useEnergy(double amount) {
        if (this.energy >= amount) {
            this.energy -= amount;
            return true;
        }
        return false;
    }

    public void onGuiClosed(EntityPlayer entityPlayer) {
    }
}
