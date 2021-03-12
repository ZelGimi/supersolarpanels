package com.Denfop.tiles.ExpGen;

import com.Denfop.SSPItem;
import com.Denfop.block.expgen.IStandOn;
import com.Denfop.container.ContainerExpGen;
import com.Denfop.utils.ExperienceUtils;
import cpw.mods.fml.common.eventhandler.Event;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySink;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

public class TileExpGen2 extends TileEntity implements IEnergySink, IInventory, IFluidHandler, IStandOn {


    public int energy;
    public int maxEnergy;
    public FluidTank fluidTank;
    public boolean initialized;
    public String nametile;
    public boolean active;
    public boolean lastState;
    private boolean addedToEnergyNet = false;
    private final int tier;
    private boolean loaded;
    private EntityPlayer player = null;

    public TileExpGen2(int maxenergy, String tileName, int maxtankcapacity) {
        this.loaded = false;
        this.fluidTank = new FluidTank(1000 * maxtankcapacity);
        this.initialized = false;
        this.loaded = false;
        this.energy = 0;
        this.maxEnergy = maxenergy;
        this.tier = 11;
        this.nametile = tileName;
        this.lastState = false;
        this.active = false;


    }

    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, nbt);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        readFromNBT(packet.func_148857_g());
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
    }

    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        this.active = nbttagcompound.getBoolean("active");
        this.energy = nbttagcompound.getInteger("energy");
        this.fluidTank.readFromNBT(nbttagcompound.getCompoundTag("fluidTank"));


    }


    public int gaugeLiquidScaled(int i) {
        if (getFluidTank().getFluidAmount() <= 0) return 0;

        return getFluidTank().getFluidAmount() * i / getFluidTank().getCapacity();
    }


    public void writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);

        nbttagcompound.setDouble("energy", this.energy);
        NBTTagCompound fluidTankTag = new NBTTagCompound();
        this.fluidTank.writeToNBT(fluidTankTag);
        nbttagcompound.setTag("fluidTank", fluidTankTag);
        nbttagcompound.setBoolean("active", this.active);

    }


    public void validate() {
        super.validate();
        onLoaded();

    }


    public void onLoaded() {
        if (!this.worldObj.isRemote) {
            MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
            this.addedToEnergyNet = true;


        }

        this.loaded = true;


    }

    public void invalidate() {
        if (this.loaded) {
            onUnloaded();

        }
        super.invalidate();
    }

    public void onUnloaded() {
        if (!this.worldObj.isRemote && this.addedToEnergyNet) {
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
            this.addedToEnergyNet = false;
        }
        this.loaded = false;
        this.active = false;
    }


    public void updateEntity() {
        super.updateEntity();

        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);

        if (!this.initialized && this.worldObj != null) {
            intialize();


        }
        if (!this.worldObj.isRemote) {

            if (this.energy > this.maxEnergy) {

                this.energy = this.maxEnergy;
            }
            if (this.energy >= 9000) {
                attemptGeneration();
                this.energy -= 9000;
            }


            if (this.player != null & this.active) {

                transferExpToPlayer(this.player);
                this.player = null;
                markDirty();
                this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);

            } else {
                this.active = false;
                markDirty();
                this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
            }


            markDirty();

        }

    }

    protected void transferExpToPlayer(EntityPlayer entityplayer) {
        if (!this.worldObj.isRemote) {

            FluidStack fluid = getFluidTank().getFluid();
            if (fluid != null && fluid.isFluidEqual(new FluidStack(SSPItem.FluidXP.xpJuice, 0))) {
                if (fluid.amount > 0) {

                    int currentXP = ExperienceUtils.getPlayerXP(entityplayer);
                    int nextLevelXP = ExperienceUtils.getExperienceForLevel(entityplayer.experienceLevel + 1);
                    int requiredXP = nextLevelXP - currentXP;

                    int requiredXPJuice = 20 * requiredXP;
                    FluidStack drained = this.fluidTank.drain(requiredXPJuice, true);
                    if (drained != null) {
                        int xp = drained.amount / 2;
                        ExperienceUtils.addPlayerXP(entityplayer, xp);

                    }
                    markDirty();


                }
            }
        }
    }

    public void setActiveVoid(boolean bool, int x, int y, int z, World world) {
        if (world.getTileEntity(x, y, z) instanceof TileExpGen2) {


            this.active = bool;
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);


        }
    }

    public boolean getActive() {
        return this.active;
    }


    public void isPlayerStandingOnBlock(EntityPlayer entity, int x, int y, int z, World world) {


        if (this.player == null) {
            this.player = entity;

            setActiveVoid(true, x, y, z, world);

            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);

        } else if (this.player.getUniqueID() != entity.getUniqueID()) {
            this.player = entity;

            setActiveVoid(true, x, y, z, world);

            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);

        } else {


            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        }


        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);


    }


    public void intialize() {


        this.initialized = true;
        if (!this.addedToEnergyNet) {
            onLoaded();

        }

    }


    public void attemptGeneration() {

        if (this.fluidTank.getFluidAmount() + 1 > this.fluidTank.getCapacity()) {
            return;
        }

        if (this.energy > 0) {
            this.fluidTank.fill(new FluidStack(SSPItem.FluidXP.xpJuice, 3), true);

            markDirty();


        }
        if (this.energy < 0) {
            this.energy = 0;
        }

    }


    public Container getGuiContainer(InventoryPlayer inventory) {


        return new ContainerExpGen(inventory, this);

    }


    @Override
    public boolean acceptsEnergyFrom(TileEntity nameTileEntity, ForgeDirection nameForgeDirection) {

        return true;
    }

    @Override
    public double getDemandedEnergy() {
        return this.maxEnergy - this.energy;
    }

    @Override
    public int getSinkTier() {

        return this.tier;
    }

    @Override
    public double injectEnergy(ForgeDirection directionFrom, double amount, double voltage) {
        if (this.energy >= this.maxEnergy) {
            return amount;
        }
        this.energy += amount;
        return 0.0D;
    }


    public FluidTank getFluidTank() {
        return this.fluidTank;
    }


    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        if (!canFill(from, resource.getFluid())) return 0;
        markDirty();
        return getFluidTank().fill(resource, doFill);
    }


    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        if (resource == null || !resource.isFluidEqual(getFluidTank().getFluid())) {
            return null;
        }
        markDirty();
        if (!canDrain(from, resource.getFluid())) return null;

        return getFluidTank().drain(resource.amount, doDrain);
    }


    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        if (!canDrain(from, null)) return null;
        markDirty();
        return getFluidTank().drain(maxDrain, doDrain);
    }


    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {

        return (fluid == SSPItem.FluidXP.xpJuice);
    }


    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {

        return true;
    }


    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[]{getFluidTank().getInfo()};
    }

    @Override
    public int getSizeInventory() {

        return 0;
    }

    @Override
    public ItemStack getStackInSlot(int p_70301_1_) {

        return null;
    }

    @Override
    public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {

        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_) {

        return null;
    }

    @Override
    public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {


    }

    @Override
    public String getInventoryName() {

        return null;
    }

    @Override
    public boolean hasCustomInventoryName() {

        return false;
    }

    @Override
    public int getInventoryStackLimit() {

        return 0;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {

        return (player.getDistance(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D);
    }

    @Override
    public void openInventory() {


    }

    @Override
    public void closeInventory() {


    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {

        return true;
    }


}
