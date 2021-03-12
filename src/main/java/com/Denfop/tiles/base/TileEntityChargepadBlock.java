package com.Denfop.tiles.base;

import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyReceiver;
import com.Denfop.container.ContainerChargepadBlock;
import com.Denfop.gui.GuiChargepadBlock;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.item.ElectricItem;
import ic2.api.network.INetworkClientTileEntityEventListener;
import ic2.api.tile.IEnergyStorage;
import ic2.core.ContainerBase;
import ic2.core.IC2;
import ic2.core.IHasGui;
import ic2.core.Ic2Items;
import ic2.core.init.MainConfig;
import ic2.core.util.ConfigUtil;
import ic2.core.util.EntityIC2FX;
import ic2.core.util.StackUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public abstract class TileEntityChargepadBlock extends TileEntityElectricBlock implements IEnergySink, IEnergySource, IHasGui, INetworkClientTileEntityEventListener, IEnergyStorage, IEnergyHandler, IEnergyReceiver {
    public static byte redstoneModes = 2;
    public boolean addedToEnergyNet;
    private int updateTicker;
    private EntityPlayer player;
    private boolean isEmittingRedstone;

    public TileEntityChargepadBlock(int tier1, int output1, int maxStorage1) {
        super(tier1, output1, maxStorage1);
        this.player = null;
        this.isEmittingRedstone = false;
        this.addedToEnergyNet = false;
        this.updateTicker = IC2.random.nextInt(getTickRate());
    }

    public void playerstandsat(EntityPlayer entity) {
        if (this.player == null) {
            this.player = entity;
        } else if (this.player.getUniqueID() != entity.getUniqueID()) {
            this.player = entity;
        }
    }

    protected int getTickRate() {
        return 2;
    }

    public boolean wrenchCanSetFacing(EntityPlayer entityPlayer, int side) {
        if (side == 1)
            return false;
        return (getFacing() != side);
    }

    protected void updateEntityServer() {
        super.updateEntityServer();
        boolean needsInvUpdate = false;
        if (this.updateTicker++ % getTickRate() != 0)
            return;
        if (this.player != null && this.energy >= 1.0D) {
            if (!getActive())
                setActive(true);
            getItems(this.player);
            this.player = null;
            needsInvUpdate = true;
        } else if (getActive() == true) {
            setActive(false);
            needsInvUpdate = true;
        }
        if ((this.redstoneMode == 0 && getActive()) || (this.redstoneMode == 1 && !getActive())) {
            this.isEmittingRedstone = true;
            this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord));
        } else {
            this.isEmittingRedstone = false;
            this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord));
        }
        if (needsInvUpdate)
            markDirty();
    }

    protected abstract void getItems(EntityPlayer paramEntityPlayer);

    public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction) {
        if (direction == ForgeDirection.UP)
            return false;
        return !facingMatchesDirection(direction);
    }

    public ContainerBase<TileEntityChargepadBlock> getGuiContainer(EntityPlayer entityPlayer) {
        return new ContainerChargepadBlock(entityPlayer, this);
    }

    @SideOnly(Side.CLIENT)
    public GuiScreen getGui(EntityPlayer entityPlayer, boolean isAdmin) {
        return new GuiChargepadBlock(new ContainerChargepadBlock(entityPlayer, this));
    }

    public void onGuiClosed(EntityPlayer entityPlayer) {
    }

    public boolean isEmittingRedstone() {
        return this.isEmittingRedstone;
    }

    @SideOnly(Side.CLIENT)
    public void spawnParticles(World world, int blockX, int blockY, int blockZ, Random rand) {
        if (getActive()) {
            EffectRenderer effect = (FMLClientHandler.instance().getClient()).effectRenderer;
            for (int particles = 20; particles > 0; particles--) {
                double x = (blockX + 0.0F + rand.nextFloat());
                double y = (blockY + 0.9F + rand.nextFloat());
                double z = (blockZ + 0.0F + rand.nextFloat());
                effect.addEffect(new EntityIC2FX(world, x, y, z, 60, new double[]{0.0D, 0.1D, 0.0D}, new float[]{0.2F, 0.2F, 1.0F}));
            }
        }
    }

    public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
        ItemStack ret = super.getWrenchDrop(entityPlayer);
        float energyRetainedInStorageBlockDrops = ConfigUtil.getFloat(MainConfig.get(), "balance/energyRetainedInStorageBlockDrops");
        if (energyRetainedInStorageBlockDrops > 0.0F) {
            NBTTagCompound nbttagcompound = StackUtil.getOrCreateNbtData(ret);
            nbttagcompound.setDouble("energy", this.energy * energyRetainedInStorageBlockDrops);
            nbttagcompound.setDouble("energy2", this.energy2 * energyRetainedInStorageBlockDrops);
        }
        return ret;
    }

    public void onNetworkEvent(EntityPlayer player, int event) {
        this.rfeu = !this.rfeu;


    }

    public String getredstoneMode() {
        if (this.redstoneMode > 1 || this.redstoneMode < 0)
            return "";
        return StatCollector.translateToLocal("ic2.blockChargepad.gui.mod.redstone" + this.redstoneMode);
    }

    protected void chargeitems(ItemStack itemstack, int chargefactor) {
        if (!(itemstack.getItem() instanceof ic2.api.item.IElectricItem))
            return;
        if (itemstack.getItem() == Ic2Items.debug.getItem())
            return;
        double freeamount = ElectricItem.manager.charge(itemstack, Double.POSITIVE_INFINITY, this.tier, true, true);
        double charge = 0.0D;
        if (freeamount >= 0.0D) {
            if (freeamount >= (chargefactor * getTickRate())) {
                charge = (chargefactor * getTickRate());
            } else {
                charge = freeamount;
            }
            if (this.energy < charge)
                charge = this.energy;
            this.energy -= ElectricItem.manager.charge(itemstack, charge, this.tier, true, false);
        }
    }
}
