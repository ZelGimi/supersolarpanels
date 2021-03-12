package com.Denfop.tiles.base;

import com.Denfop.container.ContainerMultiMatter;
import com.Denfop.gui.GuiMultiMatter;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.recipe.RecipeOutput;
import ic2.api.recipe.Recipes;
import ic2.core.ContainerBase;
import ic2.core.IC2;
import ic2.core.IHasGui;
import ic2.core.audio.AudioSource;
import ic2.core.audio.PositionSpec;
import ic2.core.block.TileEntityLiquidTankElectricMachine;
import ic2.core.block.comp.Redstone;
import ic2.core.block.invslot.*;
import ic2.core.init.BlocksItems;
import ic2.core.init.InternalName;
import ic2.core.init.MainConfig;
import ic2.core.network.NetworkManager;
import ic2.core.upgrade.IUpgradableBlock;
import ic2.core.upgrade.IUpgradeItem;
import ic2.core.upgrade.UpgradableProperty;
import ic2.core.util.ConfigUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public abstract class TileEntityMultiMatter extends TileEntityLiquidTankElectricMachine implements IHasGui, IUpgradableBlock {
    /**
     * ��������� ���������
     */
    public final InvSlotUpgrade upgradeSlot;
    /**
     * ��������� ��� ����������
     */
    public final InvSlotProcessableGeneric amplifierSlot;
    /**
     * ��������� ������ (��� ������ ��� ������� ��������� ��������)
     */
    public final InvSlotOutput outputSlot;
    /**
     * ������ ��������� (��� ������ ��� ������� ��������� ��������)
     */
    public final InvSlotConsumableLiquid containerslot;
    protected final Redstone redstone;
    public int soundTicker, scrap;
    public boolean redstonePowered;
    private int state, prevState;
    private AudioSource audioSource, audioSourceScrap;

    /**
     * ����������� ���������� �������, � ��������� ���������� �������� ������� (��� �� ���������� ������� ��� 1 mb �������),
     * � ������ ������������ ���� � ������
     */
    public TileEntityMultiMatter(float storageEnergy, int sizeTank) {
        super(Math.round(storageEnergy * ConfigUtil.getFloat(MainConfig.get(), "balance/uuEnergyFactor")), 3, -1, sizeTank);
        this.redstonePowered = false;
        this.soundTicker = IC2.random.nextInt(32);
        this.amplifierSlot = new InvSlotProcessableGeneric(this, "scrap", 0, 1, Recipes.matterAmplifier);
        this.outputSlot = new InvSlotOutput(this, "output", 1, 1);
        this.containerslot = new InvSlotConsumableLiquidByList(this, "containerslot", 2, InvSlot.Access.I, 1, InvSlot.InvSide.TOP, InvSlotConsumableLiquid.OpType.Fill, BlocksItems.getFluid(InternalName.fluidUuMatter));
        this.upgradeSlot = new InvSlotUpgrade(this, "upgrade", 3, 4);
        this.redstone = addComponent(new Redstone(this));
    }

    private static int applyModifier(int base, int extra, double multiplier) {
        double ret = Math.round((base + extra) * multiplier);
        return (ret > 2.147483647E9D) ? Integer.MAX_VALUE : (int) ret;
    }

    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        try {
            this.scrap = nbttagcompound.getInteger("scrap");
        } catch (Throwable e) {
            this.scrap = nbttagcompound.getShort("scrap");
        }
    }

    public void writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setInteger("scrap", this.scrap);
    }

    protected void updateEntityServer() {
        super.updateEntityServer();
        this.redstonePowered = false;
        boolean needsInvUpdate = onUpdateUpgrade();
        if (this.redstone.hasRedstoneInput() || this.energy <= 0.0D) {
            setState(0);
            setActive(false);
        } else {
            setState((this.scrap > 0) ? 2 : 1);
            setActive(true);

            if (this.scrap < 10000) {
                RecipeOutput amplifier = this.amplifierSlot.process();
                if (amplifier != null) {
                    this.amplifierSlot.consume();
                    this.scrap += amplifier.metadata.getInteger("amplification");
                }
            }

            if (this.energy >= this.maxEnergy)
                needsInvUpdate = attemptGeneration();

            MutableObject<ItemStack> output = new MutableObject();
            if (this.containerslot.transferFromTank(this.fluidTank, output, true)
                    && (output.getValue() == null || this.outputSlot.canAdd(output.getValue()))) {
                this.containerslot.transferFromTank(this.fluidTank, output, false);
                if (output.getValue() != null)
                    this.outputSlot.add(output.getValue());
            }

            if (needsInvUpdate)
                markDirty();
        }
    }

    public boolean onUpdateUpgrade() {
        for (int i = 0; i < this.upgradeSlot.size(); i++) {
            ItemStack stack = this.upgradeSlot.get(i);
            if (stack != null && stack.getItem() instanceof IUpgradeItem)
                if (((IUpgradeItem) stack.getItem()).onTick(stack, this))
                    return true;
        }
        return false;
    }

    public void onUnloaded() {
        if (IC2.platform.isRendering() && this.audioSource != null) {
            IC2.audioManager.removeSources(this);
            this.audioSource = null;
            this.audioSourceScrap = null;
        }
        super.onUnloaded();
    }

    public boolean attemptGeneration() {
        if (this.fluidTank.getFluidAmount() + 1 > this.fluidTank.getCapacity())
            return false;
        fill(null, new FluidStack(BlocksItems.getFluid(InternalName.fluidUuMatter), 1), true);
        this.energy -= this.maxEnergy;
        return true;
    }

    public double getDemandedEnergy() {
        if (this.redstone.hasRedstoneInput())
            return 0.0D;
        return this.maxEnergy - this.energy;
    }

    public double injectEnergy(ForgeDirection directionFrom, double amount, double voltage) {
        if (this.energy >= this.maxEnergy || this.redstone.hasRedstoneInput())
            return amount;
        int bonus = Math.min((int) amount, this.scrap);
        this.scrap -= bonus;
        this.energy += amount + (5 * bonus);
        return 0.0D;
    }

    public String getProgressAsString() {
        int p = Math.min((int) (this.energy * 100.0D / this.maxEnergy), 100);
        return "" + p + "%";
    }

    public ContainerBase<TileEntityMultiMatter> getGuiContainer(EntityPlayer entityPlayer) {
        return new ContainerMultiMatter(entityPlayer, this);
    }

    @SideOnly(Side.CLIENT)
    public GuiScreen getGui(EntityPlayer entityPlayer, boolean isAdmin) {
        return new GuiMultiMatter(new ContainerMultiMatter(entityPlayer, this));
    }

    public void onGuiClosed(EntityPlayer entityPlayer) {
    }

    private void setState(int aState) {
        this.state = aState;
        if (this.prevState != this.state)
            IC2.network.get().updateTileEntityField(this, "state");
        this.prevState = this.state;
    }

    public List<String> getNetworkedFields() {
        List<String> ret = new Vector<String>(1);
        ret.add("state");
        ret.addAll(super.getNetworkedFields());
        return ret;
    }

    public void onNetworkUpdate(String field) {
        if (field.equals("state") && this.prevState != this.state) {
            switch (this.state) {
                case 0:
                    if (this.audioSource != null)
                        this.audioSource.stop();
                    if (this.audioSourceScrap != null)
                        this.audioSourceScrap.stop();
                    break;
                case 1:
                    if (this.audioSource == null)
                        this.audioSource = IC2.audioManager.createSource(this, PositionSpec.Center, "Generators/MassFabricator/MassFabLoop.ogg", true, false, IC2.audioManager.getDefaultVolume());
                    if (this.audioSource != null)
                        this.audioSource.play();
                    if (this.audioSourceScrap != null)
                        this.audioSourceScrap.stop();
                    break;
                case 2:
                    if (this.audioSource == null)
                        this.audioSource = IC2.audioManager.createSource(this, PositionSpec.Center, "Generators/MassFabricator/MassFabLoop.ogg", true, false, IC2.audioManager.getDefaultVolume());
                    if (this.audioSourceScrap == null)
                        this.audioSourceScrap = IC2.audioManager.createSource(this, PositionSpec.Center, "Generators/MassFabricator/MassFabScrapSolo.ogg", true, false, IC2.audioManager.getDefaultVolume());
                    if (this.audioSource != null)
                        this.audioSource.play();
                    if (this.audioSourceScrap != null)
                        this.audioSourceScrap.play();
                    break;
            }
            this.prevState = this.state;
        }
        super.onNetworkUpdate(field);
    }

    public float getWrenchDropRate() {
        return 0.7F;
    }

    public boolean amplificationIsAvailable() {
        if (this.scrap > 0)
            return true;
        RecipeOutput amplifier = this.amplifierSlot.process();
        return (amplifier != null && amplifier.metadata.getInteger("amplification") > 0);
    }

    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return (fluid == BlocksItems.getFluid(InternalName.fluidUuMatter));
    }

    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return true;
    }

    public void onLoaded() {
        super.onLoaded();
        if (IC2.platform.isSimulating())
            setUpgradestat();
    }

    public void markDirty() {
        super.markDirty();
        if (IC2.platform.isSimulating())
            setUpgradestat();
    }

    public void setUpgradestat() {
        this.upgradeSlot.onChanged();
        setTier(applyModifier(this.getSinkTier(), this.upgradeSlot.extraTier, 1.0D));
    }

    public double getEnergy() {
        return this.energy;
    }

    public boolean useEnergy(double amount) {
        if (this.energy >= amount) {
            this.energy -= amount;
            return true;
        }
        return false;
    }

    public Set<UpgradableProperty> getUpgradableProperties() {
        return EnumSet.of(UpgradableProperty.RedstoneSensitive, UpgradableProperty.Transformer,
                UpgradableProperty.ItemConsuming, UpgradableProperty.ItemProducing, UpgradableProperty.FluidProducing);
    }
}
