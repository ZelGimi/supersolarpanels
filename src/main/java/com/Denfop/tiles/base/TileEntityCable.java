package com.Denfop.tiles.base;

import com.Denfop.block.cable.BlockCable;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.registry.GameData;
import ic2.api.Direction;
import ic2.api.energy.EnergyNet;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergyConductor;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergyTile;
import ic2.api.network.INetworkTileEntityEventListener;
import ic2.core.IC2;
import ic2.core.ITickCallback;
import ic2.core.block.IObscurable;
import ic2.core.block.TileEntityBlock;
import ic2.core.block.wiring.TileEntityLuminator;
import ic2.core.network.ClientModifiable;
import ic2.core.network.NetworkManager;
import ic2.core.util.ReflectionUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Vector;

public class TileEntityCable extends TileEntityBlock implements IEnergyConductor, INetworkTileEntityEventListener, IObscurable {
    private static final int EventRemoveConductor = 0;
    public short cableType;
    public short color;
    public byte foamed;
    public byte foamColor;
    @ClientModifiable
    public Block[] retextureRef;
    @ClientModifiable
    public int[] retextureRefMeta;
    @ClientModifiable
    public int[] retextureRefSide;
    public byte connectivity;
    public byte renderSide;
    public boolean addedToEnergyNet;
    private byte prevFoamed;
    private ITickCallback continuousTickCallback;

    public TileEntityCable(short type) {
        this.cableType = 0;
        this.color = 0;
        this.foamed = 0;
        this.foamColor = 0;
        this.connectivity = 0;
        this.renderSide = 0;
        this.prevFoamed = 0;
        this.addedToEnergyNet = false;
        this.continuousTickCallback = null;
        this.cableType = type;
    }

    public TileEntityCable() {
        this.cableType = 0;
        this.color = 0;
        this.foamed = 0;
        this.foamColor = 0;
        this.connectivity = 0;
        this.renderSide = 0;
        this.prevFoamed = 0;
        this.addedToEnergyNet = false;
        this.continuousTickCallback = null;
    }

    public static float getCableThickness(int cableType) {
        float p = 1.0F;
        switch (cableType) {
            case 0:
                p = 4.0F;
                break;
            case 1:
                p = 4.0F;
                break;
            case 2:
                p = 4.0F;
                break;
            case 3:
                p = 4.0F;
                break;
            case 4:
                p = 4.0F;
                break;
            case 5:
                p = 4.0F;
                break;
            case 6:
                p = 4.0F;
                break;
            case 7:
                p = 4.0F;
                break;
            case 8:
                p = 4.0F;
                break;
            case 9:
                p = 4.0F;
                break;
            case 10:
                p = 4.0F;
                break;
            case 11:
                p = 4.0F;
                break;
            case 12:
                p = 8.0F;
                break;
            case 13:
                p = 16.0F;
                break;
            case 14:
                p = 6.0F;
                break;
        }
        return p / 16.0F;
    }

    public static int getMaxCapacity(int type) {
        switch (type) {
            case 0:
                return 16384;
            case 1:
                return 65536;
            case 2:
                return 131072;
            case 3:
                return 262144;
            case 4:
                return 1048576;
            case 5:
                return 2097152;
            case 6:
                return 4194304;
            case 7:
                return 8388608;
            case 8:
                return 16777216;
            case 9:
                return 33554432;
            case 10:
                return 67108864;
            case 11:
                return 0;
            case 12:
                return 0;
            case 13:
                return 0;
            case 14:
                return 0;
        }
        return 0;
    }

    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        this.cableType = nbttagcompound.getShort("cableType");
        this.color = nbttagcompound.getShort("color");
        this.foamColor = nbttagcompound.getByte("foamColor");
        this.foamed = nbttagcompound.getByte("foamed");
        if (nbttagcompound.hasKey("retextureRefMeta")) {
            this.retextureRef = new Block[6];
            boolean found = false;
            for (int i = 0; i < 6; i++) {
                if (nbttagcompound.hasKey("retextureRef" + i)) {
                    this.retextureRef[i] = GameData.getBlockRegistry().getRaw(nbttagcompound.getString("retextureRef" + i));
                    found = (found || this.retextureRef[i] != null);
                }
            }
            if (found) {
                this.retextureRefMeta = nbttagcompound.getIntArray("retextureRefMeta");
                this.retextureRefSide = nbttagcompound.getIntArray("retextureRefSide");
                if (this.retextureRefMeta.length != 6 || this.retextureRefSide.length != 6)
                    clearRetexture();
            } else {
                clearRetexture();
            }
        }
    }

    public void writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setShort("cableType", this.cableType);
        nbttagcompound.setShort("color", this.color);
        nbttagcompound.setByte("foamed", this.foamed);
        nbttagcompound.setByte("foamColor", this.foamColor);
        if (this.retextureRef != null) {
            for (int i = 0; i < this.retextureRef.length; i++) {
                if (this.retextureRef[i] != null)
                    nbttagcompound.setString("retextureRef" + i, GameData.getBlockRegistry().getNameForObject(this.retextureRef[i]));
            }
            nbttagcompound.setIntArray("retextureRefMeta", this.retextureRefMeta);
            nbttagcompound.setIntArray("retextureRefSide", this.retextureRefSide);
        }
    }

    public void onLoaded() {
        super.onLoaded();
        if (IC2.platform.isSimulating()) {
            int meta = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);

            MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
            this.addedToEnergyNet = true;
            onNeighborBlockChange();
            if (this.foamed == 1)
                changeFoam(this.foamed, true);
        }


    }

    public void onUnloaded() {
        if (IC2.platform.isSimulating() && this.addedToEnergyNet) {
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
            this.addedToEnergyNet = false;
        }
        if (this.continuousTickCallback != null) {
            IC2.tickHandler.removeContinuousTickCallback(this.worldObj, this.continuousTickCallback);
            this.continuousTickCallback = null;
        }
        super.onUnloaded();
    }

    public void onNeighborBlockChange() {
        byte newConnectivity = 0;
        byte newRenderSide = 0;
        int mask = 1;
        for (Direction direction : Direction.directions) {
            TileEntity neighbor = EnergyNet.instance.getNeighbor(this, direction.toForgeDirection());
            if (((neighbor instanceof IEnergyAcceptor && ((IEnergyAcceptor) neighbor).acceptsEnergyFrom(this, direction.getInverse().toForgeDirection())) || (neighbor instanceof IEnergyEmitter && ((IEnergyEmitter) neighbor).emitsEnergyTo(this, direction.getInverse().toForgeDirection()))) && canInteractWith(neighbor)) {
                newConnectivity = (byte) (newConnectivity | mask);
                ForgeDirection dir = direction.toForgeDirection();
                int x = this.xCoord + dir.offsetX;
                int y = this.yCoord + dir.offsetY;
                int z = this.zCoord + dir.offsetZ;
                if (neighbor instanceof TileEntityCable) {
                    if (((TileEntityCable) neighbor).getCableThickness() < getCableThickness())
                        newRenderSide = (byte) (newRenderSide | mask);
                } else if (neighbor instanceof IEnergyConductor || !this.worldObj.getBlock(x, y, z).isBlockSolid(this.worldObj, x, y, z, direction.getInverse().toSideValue())) {
                    newRenderSide = (byte) (newRenderSide | mask);
                }
            }
            mask *= 2;
        }
        if (this.connectivity != newConnectivity) {
            this.connectivity = newConnectivity;
            IC2.network.get().updateTileEntityField(this, "connectivity");
        }
        if (this.renderSide != newRenderSide) {
            this.renderSide = newRenderSide;
            IC2.network.get().updateTileEntityField(this, "renderSide");
        }
    }

    public boolean shouldRefresh(Block oldBlock, Block newBlock, int oldMeta, int newMeta, World world, int x, int y, int z) {
        if (oldBlock != newBlock)
            return super.shouldRefresh(oldBlock, newBlock, oldMeta, newMeta, world, x, y, z);
        return false;
    }

    public boolean changeColor(int newColor) {
        if ((this.foamed == 0 && (this.color == newColor)) || (this.foamed > 0 && this.foamColor == newColor))
            return false;
        if (IC2.platform.isSimulating())
            if (this.foamed == 0) {
                if (this.addedToEnergyNet)
                    MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
                this.addedToEnergyNet = false;
                this.color = (short) newColor;
                MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
                this.addedToEnergyNet = true;
                IC2.network.get().updateTileEntityField(this, "color");
                onNeighborBlockChange();
            } else {
                this.foamColor = (byte) newColor;
                IC2.network.get().updateTileEntityField(this, "foamColor");
                clearRetexture();
                IC2.network.get().updateTileEntityField(this, "retextureRef");
                IC2.network.get().updateTileEntityField(this, "retextureRefMeta");
                IC2.network.get().updateTileEntityField(this, "retextureRefSide");
            }
        return true;
    }

    public boolean isFoamed() {
        return (this.foamed != 0);
    }

    public boolean changeFoam(byte foamed1) {
        return changeFoam(foamed1, false);
    }

    public boolean tryAddInsulation() {
        short target;
        switch (this.cableType) {

            default:
                target = this.cableType;
                break;
        }
        if (target != this.cableType) {
            if (IC2.platform.isSimulating())
                changeType(target);
            return true;
        }
        return false;
    }

    public boolean tryRemoveInsulation() {
        short target;
        switch (this.cableType) {

            default:
                target = this.cableType;
                break;
        }
        if (target != this.cableType) {
            if (IC2.platform.isSimulating())
                changeType(target);
            return true;
        }
        return false;
    }

    public void changeType(short cableType1) {
        this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, cableType1, 7);
        if (this.addedToEnergyNet)
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
        this.addedToEnergyNet = false;
        this.cableType = cableType1;
        MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
        this.addedToEnergyNet = true;
        IC2.network.get().updateTileEntityField(this, "cableType");
    }

    public boolean wrenchCanSetFacing(EntityPlayer entityPlayer, int side) {
        return false;
    }

    public boolean wrenchCanRemove(EntityPlayer entityPlayer) {
        return false;
    }

    public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction) {
        return canInteractWith(emitter);
    }

    public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction) {
        return canInteractWith(receiver);
    }

    public boolean canInteractWith(TileEntity te) {
        if (!(te instanceof IEnergyTile))
            return false;
        if (te instanceof TileEntityCable)
            return canInteractWithCable((TileEntityCable) te);
        if (te instanceof TileEntityLuminator)
            return ((TileEntityLuminator) te).canCableConnectFrom(this.xCoord, this.yCoord, this.zCoord);
        return true;
    }

    public boolean canInteractWithCable(TileEntityCable cable) {
        return (this.color == 0 || cable.color == 0 || this.color == cable.color);
    }

    public float getCableThickness() {
        if (this.foamed == 2)
            return 1.0F;
        return getCableThickness(this.cableType);
    }

    public double getConductionLoss() {
        switch (this.cableType) {
            case 0:
                return 0.025D;
            case 1:
                return 0.025D;
            case 2:
                return 0.025D;
            case 3:
                return 0.025D;
            case 4:
                return 0.025D;
            case 5:
                return 0.025D;
            case 6:
                return 0.025D;
            case 7:
                return 0.025D;
            case 8:
                return 0.025D;
            case 9:
                return 0.025D;
            case 10:
                return 0.025D;
            case 11:
                return 0.025D;
            case 12:
                return 0.025D;
            case 14:
                return 0.15D;
        }
        return 0.025D;
    }

    public double getInsulationEnergyAbsorption() {
        switch (this.cableType) {
            case 1:
                return 5.0D;
            case 2:
                return 6.0D;
            case 5:
                return 3.0D;
            case 10:
                return 8.0D;
        }
        return 0.0D;
    }

    public double getInsulationBreakdownEnergy() {
        return 0.0D;
    }

    public double getConductorBreakdownEnergy() {
        return (getMaxCapacity(this.cableType));
    }

    public void removeInsulation() {
    }

    public void removeConductor() {
        this.worldObj.setBlockToAir(this.xCoord, this.yCoord, this.zCoord);
        IC2.network.get().initiateTileEntityEvent(this, 0, true);
    }

    public List<String> getNetworkedFields() {
        List<String> ret = new Vector<String>();
        ret.add("cableType");
        ret.add("color");
        ret.add("foamed");
        ret.add("foamColor");
        ret.add("retextureRef");
        ret.add("retextureRefMeta");
        ret.add("retextureRefSide");
        ret.add("connectivity");
        ret.add("renderSide");
        ret.addAll(super.getNetworkedFields());
        return ret;
    }

    public void onNetworkUpdate(String field) {
        if (field.equals("foamed")) {
            if (this.prevFoamed != this.foamed) {
                if ((this.foamed == 0 && this.prevFoamed != 1) || this.foamed == 2)
                    relight();
                this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                this.prevFoamed = this.foamed;
            }
        } else {
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        }
        super.onNetworkUpdate(field);
    }

    private void relight() {
        Method relightMethod = ReflectionUtil.getMethod(Chunk.class, new String[]{"relightBlock", "func_76615_h"}, int.class, int.class, int.class);
        Method propagateSkylightOcclusionMethod = ReflectionUtil.getMethod(Chunk.class, new String[]{"propagateSkylightOcclusion", "func_76595_e"}, int.class, int.class);
        Chunk chunk = this.worldObj.getChunkFromBlockCoords(this.xCoord, this.zCoord);
        int height = chunk.getHeightValue(this.xCoord & 0xF, this.zCoord & 0xF);
        try {
            if (this.foamed == 2 && this.yCoord >= height) {
                relightMethod.invoke(chunk, Integer.valueOf(this.xCoord & 0xF), Integer.valueOf(this.yCoord + 1), Integer.valueOf(this.zCoord & 0xF));
            } else if (this.yCoord == height - 1) {
                relightMethod.invoke(chunk, Integer.valueOf(this.xCoord & 0xF), Integer.valueOf(this.yCoord), Integer.valueOf(this.zCoord & 0xF));
            }
            propagateSkylightOcclusionMethod.invoke(chunk, Integer.valueOf(this.xCoord & 0xF), Integer.valueOf(this.zCoord & 0xF));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.worldObj.func_147451_t(this.xCoord, this.yCoord, this.zCoord);
    }

    public void onNetworkEvent(int event) {
        int l;
        switch (event) {
            case 0:
                this.worldObj.playSoundEffect((this.xCoord + 0.5F), (this.yCoord + 0.5F), (this.zCoord + 0.5F), "random.fizz", 0.5F, 2.6F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.8F);
                for (l = 0; l < 8; l++)
                    this.worldObj.spawnParticle("largesmoke", this.xCoord + Math.random(), this.yCoord + 1.2D, this.zCoord + Math.random(), 0.0D, 0.0D, 0.0D);
                return;
        }
        IC2.platform.displayError("An unknown event type was received over multiplayer.\nThis could happen due to corrupted data or a bug.\n\n(Technical information: event ID " + event + ", tile entity below)\nT: " + this + " (" + this.xCoord + ", " + this.yCoord + ", " + this.zCoord + ")");
    }

    public float getWrenchDropRate() {
        return 0.0F;
    }

    private boolean changeFoam(byte foamed1, boolean duringLoad) {
        if (this.foamed == foamed1 && !duringLoad)
            return false;
        if (!IC2.platform.isSimulating())
            return true;
        byte prevFoamed1 = this.foamed;
        this.foamed = foamed1;
        if (this.continuousTickCallback != null) {
            IC2.tickHandler.removeContinuousTickCallback(this.worldObj, this.continuousTickCallback);
            this.continuousTickCallback = null;
        }
        if (foamed1 == 0 || foamed1 == 1) {
            if (this.retextureRef != null) {
                clearRetexture();
                if (!duringLoad) {
                    IC2.network.get().updateTileEntityField(this, "retextureRef");
                    IC2.network.get().updateTileEntityField(this, "retextureRefMeta");
                    IC2.network.get().updateTileEntityField(this, "retextureRefSide");
                }
            }
            if (this.foamColor != 7) {
                this.foamColor = 7;
                if (!duringLoad)
                    IC2.network.get().updateTileEntityField(this, "foamColor");
            }
        }
        if ((foamed1 == 0 && prevFoamed1 != 1) || foamed1 == 2) {
            relight();
        } else if (foamed1 == 1) {
            this.continuousTickCallback = new ITickCallback() {
                public void tickCallback(World world) {
                    if (world.rand.nextInt(500) == 0 && world.getBlockLightValue(TileEntityCable.this.xCoord, TileEntityCable.this.yCoord, TileEntityCable.this.zCoord) * 6 >= (TileEntityCable.this.getWorldObj()).rand.nextInt(1000))
                        TileEntityCable.this.changeFoam((byte) 2);
                }
            };
            IC2.tickHandler.addContinuousTickCallback(this.worldObj, this.continuousTickCallback);
        }
        if (!duringLoad)
            IC2.network.get().updateTileEntityField(this, "foamed");
        return true;
    }

    public boolean retexture(int side, Block referencedBlock, int referencedMeta, int referencedSide) {
        if (this.foamed != 2)
            return false;
        boolean ret = false;
        boolean updateAll = false;
        if (this.retextureRef == null) {
            this.retextureRef = new Block[6];
            this.retextureRefMeta = new int[6];
            this.retextureRefSide = new int[6];
            updateAll = true;
        }
        if (this.retextureRef[side] != referencedBlock || updateAll) {
            this.retextureRef[side] = referencedBlock;
            IC2.network.get().updateTileEntityField(this, "retextureRef");
            ret = true;
        }
        if (this.retextureRefMeta[side] != referencedMeta || updateAll) {
            this.retextureRefMeta[side] = referencedMeta;
            IC2.network.get().updateTileEntityField(this, "retextureRefMeta");
            ret = true;
        }
        if (this.retextureRefSide[side] != referencedSide || updateAll) {
            this.retextureRefSide[side] = referencedSide;
            IC2.network.get().updateTileEntityField(this, "retextureRefSide");
            ret = true;
        }
        return ret;
    }

    public Block getReferencedBlock(int side) {
        if (this.retextureRef != null)
            return this.retextureRef[side];
        return null;
    }

    public int getReferencedMeta(int side) {
        if (this.retextureRefMeta != null)
            return this.retextureRefMeta[side];
        return 0;
    }

    public void setColorMultiplier(int colorMultiplier) {
        ((BlockCable) getBlockType()).colorMultiplier = colorMultiplier;
    }

    public void setRenderMask(int mask) {
        ((BlockCable) getBlockType()).renderMask = mask;
    }

    private void clearRetexture() {
        this.retextureRef = null;
        this.retextureRefMeta = null;
        this.retextureRefSide = null;
    }
}
