package com.Denfop.block.cable;

import com.Denfop.Constants;
import com.Denfop.IUCore;
import com.Denfop.SSPItem;
import com.Denfop.tiles.base.TileEntityCable;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.Direction;
import ic2.api.event.PaintEvent;
import ic2.api.event.RetextureEvent;
import ic2.api.tile.IWrenchable;
import ic2.core.ContainerBase;
import ic2.core.IC2;
import ic2.core.IHasGui;
import ic2.core.block.BlockTextureStitched;
import ic2.core.block.TileEntityBlock;
import ic2.core.item.tool.ItemToolCutter;
import ic2.core.util.AabbUtil;
import ic2.core.util.LogCategory;
import ic2.core.util.StackUtil;
import ic2.core.util.Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import org.apache.commons.lang3.mutable.MutableObject;

import java.lang.reflect.Constructor;
import java.util.*;

public class BlockCable extends Block {


    private static final int[][] facingAndSideToSpriteOffset = new int[][]{{3, 5, 1, 0, 4, 2}, {5, 3, 1, 0, 2, 4}, {0, 1, 3, 5, 4, 2}, {0, 1, 5, 3, 2, 4}, {0, 1, 2, 4, 3, 5}, {0, 1, 4, 2, 5, 3}};
    private static final Class<?>[] emptyClassArray = new Class[0];
    private static final Object[] emptyObjArray = new Object[0];
    private static final int tesBeforeBreakLimit = 8;
    private static final ArrayDeque<TileEntity> tesBeforeBreak = new ArrayDeque<TileEntity>(8);
    public int colorMultiplier;
    public int renderMask;
    public String internalName;
    @SideOnly(Side.CLIENT)
    protected IIcon[][] textures;
    @SideOnly(Side.CLIENT)
    private IIcon[][] coloredTextures;

    public BlockCable() {
        super(Material.iron);
        this.colorMultiplier = -1;
        setHardness(0.2F);
        this.renderMask = 63;
        setStepSound(soundTypeCloth);
        setCreativeTab(IUCore.tabssp);


        MinecraftForge.EVENT_BUS.register(this);
        setBlockName("blockCable");
        setCreativeTab(IUCore.tabssp);


    }

    public static final int getTextureSubIndex(int facing, int side) {
        return facingAndSideToSpriteOffset[facing][side];
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        int metaCount = getMetaCount();
        this.textures = new IIcon[metaCount][12];
        for (int index = 0; index < metaCount; index++) {
            String textureFolder = getTextureFolder(index);
            textureFolder = (textureFolder == null) ? "" : (textureFolder + "/");
            String name = Constants.TEXTURES + ":" + getTextureName(index);
            for (int active = 0; active < 2; active++) {
                for (int side = 0; side < 6; side++) {
                    int subIndex = active * 6 + side;
                    String subName = name + ":" + subIndex;
                    TextureAtlasSprite texture = new BlockTextureStitched(subName, subIndex);
                    this.textures[index][subIndex] = texture;
                    ((TextureMap) iconRegister).setTextureEntry(subName, texture);
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        int facing = getFacing(meta);
        int index = getTextureIndex(meta);
        int subIndex = getTextureSubIndex(facing, side);
        if (index >= this.textures.length)
            return null;
        try {
            return this.textures[index][subIndex];
        } catch (Exception e) {
            IC2.platform.displayError(e, "Side: " + side + "\nBlock: " + this + "\nMeta: " + meta + "\nFacing: " + facing + "\nIndex: " + index + "\nSubIndex: " + subIndex);
            return null;
        }
    }

    public String getUnlocalizedName() {
        return super.getUnlocalizedName().substring(5);
    }

    protected int getFacing(int meta) {
        return 3;
    }

    @SideOnly(Side.CLIENT)
    public void onRender(IBlockAccess blockAccess, int x, int y, int z) {
        TileEntity te = getOwnTe(blockAccess, x, y, z);
        if (te instanceof TileEntityBlock)
            ((TileEntityBlock) te).onRender();
    }

    public int getFacing(IBlockAccess iBlockAccess, int x, int y, int z) {
        TileEntity te = getOwnTe(iBlockAccess, x, y, z);
        if (te instanceof TileEntityBlock)
            return ((TileEntityBlock) te).getFacing();
        int meta = iBlockAccess.getBlockMetadata(x, y, z);
        return getFacing(meta);
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side) {
        if ((this.renderMask & 1 << side) != 0)
            return super.shouldSideBeRendered(blockAccess, x, y, z, side);
        return false;
    }

    public String getTextureFolder(int id) {
        return null;
    }

    public boolean canBeReplacedByLeaves(IBlockAccess aWorld, int aX, int aY, int aZ) {
        return false;
    }

    protected int getTextureIndex(int meta) {
        return meta;
    }

    protected int getMetaCount() {
        int metaCount = 0;
        for (; getTextureName(metaCount) != null; metaCount++) ;
        return metaCount;
    }

    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.common;
    }

    public void onBlockPreDestroy(World world, int x, int y, int z, int meta) {
        TileEntity te = getOwnTe(world, x, y, z);
        if (te instanceof TileEntityBlock) {
            TileEntityBlock teb = (TileEntityBlock) te;
            teb.onBlockBreak(this, meta);
            teb.onUnloaded();
        }
        if (te != null) {
            if (te instanceof IHasGui)
                for (Object obj : world.playerEntities) {
                    if (!(obj instanceof EntityPlayerMP))
                        continue;
                    EntityPlayerMP player = (EntityPlayerMP) obj;
                    if (player.openContainer instanceof ContainerBase) {
                        ContainerBase<?> container = (ContainerBase) player.openContainer;
                        if (container.base == te)
                            player.closeScreen();
                    }
                }
            if (tesBeforeBreak.size() >= 8)
                tesBeforeBreak.pop();
            tesBeforeBreak.push(te);
        }

    }

    public final boolean hasTileEntity(int metadata) {
        return true;
    }

    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
        return false;
    }

    public TileEntity getOwnTe(IBlockAccess blockAccess, int x, int y, int z) {
        Block block;
        int meta;
        TileEntity te;
        if (blockAccess instanceof World) {
            Chunk chunk = Util.getLoadedChunk((World) blockAccess, x >> 4, z >> 4);
            if (chunk == null)
                return null;
            block = chunk.getBlock(x & 0xF, y, z & 0xF);
            meta = chunk.getBlockMetadata(x & 0xF, y, z & 0xF);
            te = blockAccess.getTileEntity(x, y, z);
        } else {
            block = blockAccess.getBlock(x, y, z);
            meta = blockAccess.getBlockMetadata(x, y, z);
            te = blockAccess.getTileEntity(x, y, z);
        }
        Class<? extends TileEntity> expectedClass = getTeClass(meta, null, null);
        Class<? extends TileEntity> actualClass = (te != null) ? te.getClass() : null;
        if (actualClass != expectedClass) {
            if (block != this) {
                if (Util.inDev()) {
                    StackTraceElement[] st = (new Throwable()).getStackTrace();
                    IC2.log.warn(LogCategory.Block, "Own tile entity query from %s to foreign block %s instead of %s at %s.", (st.length > 1) ? st[1] : "?", (block != null) ? block.getClass() : null, getClass(), Util.formatPosition(blockAccess, x, y, z));
                }
                return null;
            }
            IC2.log.warn(LogCategory.Block, "Mismatched tile entity at %s, got %s, expected %s.", Util.formatPosition(blockAccess, x, y, z), actualClass, expectedClass);
            if (blockAccess instanceof World) {
                World world = (World) blockAccess;
                te = createTileEntity(world, meta);
                world.setTileEntity(x, y, z, te);
            } else {
                return null;
            }
        }
        return te;
    }

    public final boolean isActive(IBlockAccess blockAccess, int x, int y, int z) {
        TileEntity te = getOwnTe(blockAccess, x, y, z);
        if (te instanceof TileEntityBlock)
            return ((TileEntityBlock) te).getActive();
        return false;
    }

    public final TileEntity createTileEntity(World world, int metadata) {
        MutableObject<Class<?>[]> ctorArgTypes = new MutableObject(emptyClassArray);
        MutableObject<Object[]> ctorArgs = new MutableObject(emptyObjArray);
        Class<? extends TileEntity> teClass = getTeClass(metadata, ctorArgTypes, ctorArgs);
        if (teClass == null)
            return null;
        try {
            Constructor<? extends TileEntity> ctor = teClass.getConstructor(ctorArgTypes.getValue());
            return ctor.newInstance(ctorArgs.getValue());
        } catch (Throwable t) {
            throw new RuntimeException("Error constructing " + teClass + " with " + Arrays.asList((Object[]) ctorArgTypes.getValue()) + ", " + Arrays.asList(ctorArgs.getValue()) + ".", t);
        }
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack itemStack) {
        if (!IC2.platform.isSimulating())
            return;
        TileEntity tileEntity = getOwnTe(world, x, y, z);
        if (tileEntity instanceof IWrenchable) {
            IWrenchable te = (IWrenchable) tileEntity;
            if (entityliving == null) {
                te.setFacing((short) 2);
            } else {
                int l = MathHelper.floor_double((entityliving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 0x3;
                switch (l) {
                    case 0:
                        te.setFacing((short) 2);
                        break;
                    case 1:
                        te.setFacing((short) 5);
                        break;
                    case 2:
                        te.setFacing((short) 3);
                        break;
                    case 3:
                        te.setFacing((short) 4);
                        break;
                }
            }
        }
    }

    public void onBlockAdded(World world, int x, int y, int z) {
        for (Iterator<TileEntity> it = tesBeforeBreak.descendingIterator(); it.hasNext(); ) {
            TileEntity te = it.next();
            if (te.getWorldObj() == world && te.xCoord == x && te.yCoord == y && te.zCoord == z) {
                it.remove();
                break;
            }
        }
    }

    public String getTextureName(int index) {

        ItemStack itemStack = new ItemStack(this, 1, index);
        String ret = null;

        switch (index) {
            case 0:
                ret = "blockCable";

                return ret;
            case 1:
                ret = "blockCableO";
                return ret;
            case 2:
                ret = "blockGoldCable";
                return ret;
            case 3:
                ret = "blockGoldCableI";
                return ret;
            case 4:
                ret = "blockGoldCableII";
                return ret;
            case 5:
                ret = "blockIronCable";
                return ret;
            case 6:
                ret = "blockIronCableI";
                return ret;
            case 7:
                ret = "blockIronCableII";
                return ret;
            case 8:
                ret = "blockIronCableIIII";
                return ret;
            case 9:
                ret = "blockGlassCable";
                return ret;
            case 10:
                ret = "blockGlassCableI";
                return ret;
        }

        return ret;

    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side) {
        TileEntityCable te = (TileEntityCable) getOwnTe(blockAccess, x, y, z);
        if (te == null)
            return null;
        if (te.foamed == 0) {
            if (te.color == 0)
                return super.getIcon(blockAccess, x, y, z, side);

        }
        if (te.foamed == 1)
            return StackUtil.getBlock(SSPItem.constructionFoam).getIcon(side, 0);
        Block referencedBlock = te.getReferencedBlock(side);
        if (referencedBlock != null)
            try {
                return referencedBlock.getIcon(te.retextureRefSide[side], te.retextureRefMeta[side]);
            } catch (Exception exception) {
            }
        return StackUtil.getBlock(SSPItem.constructionFoamWall).getIcon(side, te.foamColor);
    }

    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 origin, Vec3 absDirection) {
        TileEntityCable te = (TileEntityCable) getOwnTe(world, x, y, z);
        if (te == null)
            return null;
        TileEntityCable tileEntityCable = te;
        Vec3 direction = Vec3.createVectorHelper(absDirection.xCoord - origin.xCoord, absDirection.yCoord - origin.yCoord, absDirection.zCoord - origin.zCoord);
        double maxLength = direction.lengthVector();
        double halfThickness = (tileEntityCable.foamed > 0) ? 0.5D : (tileEntityCable.getCableThickness() / 2.0D);
        boolean hit = false;
        Vec3 intersection = Vec3.createVectorHelper(0.0D, 0.0D, 0.0D);
        Direction intersectingDirection = AabbUtil.getIntersection(origin, direction, AxisAlignedBB.getBoundingBox(x + 0.5D - halfThickness, y + 0.5D - halfThickness, z + 0.5D - halfThickness, x + 0.5D + halfThickness, y + 0.5D + halfThickness, z + 0.5D + halfThickness), intersection);
        if (intersectingDirection != null && intersection.distanceTo(origin) <= maxLength) {
            hit = true;
        } else if (halfThickness < 0.5D) {
            int mask = 1;
            for (Direction dir : Direction.directions) {
                if ((tileEntityCable.connectivity & mask) == 0) {
                    mask *= 2;
                } else {
                    mask *= 2;
                    AxisAlignedBB bbox = null;
                    switch (dir) {
                        case XN:
                            bbox = AxisAlignedBB.getBoundingBox(x, y + 0.5D - halfThickness, z + 0.5D - halfThickness, x + 0.5D, y + 0.5D + halfThickness, z + 0.5D + halfThickness);
                            break;
                        case XP:
                            bbox = AxisAlignedBB.getBoundingBox(x + 0.5D, y + 0.5D - halfThickness, z + 0.5D - halfThickness, x + 1.0D, y + 0.5D + halfThickness, z + 0.5D + halfThickness);
                            break;
                        case YN:
                            bbox = AxisAlignedBB.getBoundingBox(x + 0.5D - halfThickness, y, z + 0.5D - halfThickness, x + 0.5D + halfThickness, y + 0.5D, z + 0.5D + halfThickness);
                            break;
                        case YP:
                            bbox = AxisAlignedBB.getBoundingBox(x + 0.5D - halfThickness, y + 0.5D, z + 0.5D - halfThickness, x + 0.5D + halfThickness, y + 1.0D, z + 0.5D + halfThickness);
                            break;
                        case ZN:
                            bbox = AxisAlignedBB.getBoundingBox(x + 0.5D - halfThickness, y + 0.5D - halfThickness, z, x + 0.5D + halfThickness, y + 0.5D, z + 0.5D);
                            break;
                        case ZP:
                            bbox = AxisAlignedBB.getBoundingBox(x + 0.5D - halfThickness, y + 0.5D - halfThickness, z + 0.5D, x + 0.5D + halfThickness, y + 0.5D + halfThickness, z + 1.0D);
                            break;
                    }
                    intersectingDirection = AabbUtil.getIntersection(origin, direction, bbox, intersection);
                    if (intersectingDirection != null && intersection.distanceTo(origin) <= maxLength) {
                        hit = true;
                        break;
                    }
                }
            }
        }
        if (hit && intersectingDirection != null)
            return new MovingObjectPosition(x, y, z, intersectingDirection.toSideValue(), intersection);
        return null;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z, int meta) {
        double halfThickness = TileEntityCable.getCableThickness(meta);
        if (meta == 13)
            halfThickness = TileEntityCable.getCableThickness(14);
        return AxisAlignedBB.getBoundingBox(x + 0.5D - halfThickness, y + 0.5D - halfThickness, z + 0.5D - halfThickness, x + 0.5D + halfThickness, y + 0.5D + halfThickness, z + 0.5D + halfThickness);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return getCommonBoundingBoxFromPool(world, x, y, z, false);
    }

    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        return getCommonBoundingBoxFromPool(world, x, y, z, true);
    }

    public AxisAlignedBB getCommonBoundingBoxFromPool(World world, int x, int y, int z, boolean selectionBoundingBox) {
        TileEntityCable te = (TileEntityCable) getOwnTe(world, x, y, z);
        if (te == null)
            return getCollisionBoundingBoxFromPool(world, x, y, z, 3);
        double halfThickness = (te.foamed == 1 && selectionBoundingBox) ? 0.5D : (te.getCableThickness() / 2.0D);
        double minX1 = x + 0.5D - halfThickness;
        double minY1 = y + 0.5D - halfThickness;
        double minZ1 = z + 0.5D - halfThickness;
        double maxX1 = x + 0.5D + halfThickness;
        double maxY1 = y + 0.5D + halfThickness;
        double maxZ1 = z + 0.5D + halfThickness;
        if ((te.connectivity & 0x1) != 0)
            minX1 = x;
        if ((te.connectivity & 0x4) != 0)
            minY1 = y;
        if ((te.connectivity & 0x10) != 0)
            minZ1 = z;
        if ((te.connectivity & 0x2) != 0)
            maxX1 = (x + 1);
        if ((te.connectivity & 0x8) != 0)
            maxY1 = (y + 1);
        if ((te.connectivity & 0x20) != 0)
            maxZ1 = (z + 1);
        return AxisAlignedBB.getBoundingBox(minX1, minY1, minZ1, maxX1, maxY1, maxZ1);
    }

    public boolean isNormalCube(IBlockAccess world, int x, int y, int z) {
        TileEntityCable te = (TileEntityCable) getOwnTe(world, x, y, z);
        if (te == null)
            return false;
        return (te.foamed > 0);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float xOffset, float yOffset, float zOffset) {
        ItemStack cur = player.getCurrentEquippedItem();
        if (cur != null && (StackUtil.equals(Blocks.sand, cur))) {
            TileEntityCable te = (TileEntityCable) getOwnTe(world, x, y, z);
            if (te == null)
                return false;


            if ((StackUtil.equals(Blocks.sand, cur) && te.foamed == 1 && te.changeFoam((byte) 2)) || (cur.getItem() == SSPItem.constructionFoam.getItem() && te.foamed == 0 && te.changeFoam((byte) 1))) {
                if (IUCore.proxy.isSimulating() && !player.capabilities.isCreativeMode) {
                    cur.stackSize--;
                    if (cur.stackSize <= 0)
                        player.inventory.mainInventory[player.inventory.currentItem] = null;
                }
                return true;
            }
        }
        return false;
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
        super.onNeighborBlockChange(world, x, y, z, neighbor);
        if (IUCore.proxy.isSimulating()) {
            TileEntityCable te = (TileEntityCable) getOwnTe(world, x, y, z);
            if (te == null)
                return;
            te.onNeighborBlockChange();
        }
    }

    public boolean removedByPlayer(World world, EntityPlayer entityPlayer, int x, int y, int z) {
        TileEntityCable te = (TileEntityCable) getOwnTe(world, x, y, z);
        if (te == null)
            world.setBlockToAir(x, y, z);
        if (te.foamed > 0) {
            te.changeFoam((byte) 0);
            world.notifyBlocksOfNeighborChange(x, y, z, this);
            return false;
        }
        return world.setBlockToAir(x, y, z);
    }

    public int getCableColor(IBlockAccess blockAccess, int x, int y, int z) {
        TileEntityCable te = (TileEntityCable) getOwnTe(blockAccess, x, y, z);
        if (te == null)
            return 0;
        return te.color;
    }

    public boolean recolourBlock(World world, int x, int y, int z, ForgeDirection side, int color) {
        TileEntityCable te = (TileEntityCable) getOwnTe(world, x, y, z);
        if (te == null)
            return false;
        color = BlockColored.func_150031_c(color);
        return te.changeColor(color);
    }

    @SubscribeEvent
    public void colorBlock(PaintEvent event) {
        if (event.world.getBlock(event.x, event.y, event.z) != this)
            return;
        TileEntityCable te = (TileEntityCable) getOwnTe(event.world, event.x, event.y, event.z);
        if (te == null)
            return;
        event.painted = te.changeColor(event.color);
    }

    public boolean canHarvestBlock(EntityPlayer player, int md) {
        return true;
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        TileEntityCable te = (TileEntityCable) getOwnTe(world, x, y, z);
        if (te != null) {
            if (te.cableType == 14) {
                ret.add(new ItemStack(SSPItem.insulatedCopperCableItem.getItem(), 1, 13));
                return ret;
            }
            ret.add(new ItemStack(SSPItem.insulatedCopperCableItem.getItem(), 1, te.cableType));
        } else {
            ret.add(new ItemStack(SSPItem.insulatedCopperCableItem.getItem(), 1, metadata));
        }
        return ret;
    }

    public Class<? extends TileEntity> getTeClass(int meta, MutableObject<Class<?>[]> ctorArgTypes, MutableObject<Object[]> ctorArgs) {
        if (meta >= 13)
            meta++;
        if (ctorArgTypes != null)
            ctorArgTypes.setValue(new Class[]{short.class});
        if (ctorArgs != null)
            ctorArgs.setValue(new Object[]{Short.valueOf((short) meta)});

        return TileEntityCable.class;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return IUCore.proxy.getRenderId("cable");
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public void onBlockClicked(World world, int i, int j, int k, EntityPlayer entityplayer) {
        if (entityplayer.getCurrentEquippedItem() != null && entityplayer.getCurrentEquippedItem().getItem() instanceof ItemToolCutter)
            ItemToolCutter.cutInsulationFrom(entityplayer.getCurrentEquippedItem(), world, i, j, k);
    }

    public int isProvidingWeakPower(IBlockAccess blockAccess, int x, int y, int z, int side) {
        TileEntityCable te = (TileEntityCable) getOwnTe(blockAccess, x, y, z);
        if (te == null)
            return 0;

        return 0;
    }

    public void getSubBlocks(Item j, CreativeTabs tabs, List itemList) {
        Item item = Item.getItemFromBlock(this);
        if (!item.getHasSubtypes()) {
            itemList.add(new ItemStack(this));
        } else {
            for (int i = 0; i < 16; i++) {
                ItemStack is = new ItemStack(this, 1, i);
                if (is.getItem().getUnlocalizedName(is) == null)
                    break;
                itemList.add(is);
            }
        }
    }

    public float getBlockHardness(World world, int x, int y, int z) {
        TileEntityCable te = (TileEntityCable) getOwnTe(world, x, y, z);
        if (te == null)
            return 0.0F;
        if (te.foamed == 1)
            return 0.01F;
        if (te.foamed == 2)
            return 3.0F;
        return 0.2F;
    }

    public float getExplosionResistance(Entity exploder, World world, int x, int y, int z, double src_x, double src_y, double src_z) {
        TileEntityCable te = (TileEntityCable) getOwnTe(world, x, y, z);
        if (te == null)
            return 0.0F;

        return 0.0F;
    }

    public int getLightOpacity(IBlockAccess world, int x, int y, int z) {
        TileEntityCable te = (TileEntityCable) getOwnTe(world, x, y, z);
        if (te == null)
            return 0;
        if (te.foamed == 2)
            return 255;
        return 0;
    }

    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int direction) {
        int meta = world.getBlockMetadata(x, y, z);
        return (meta == 11 || meta == 12);
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        List<ItemStack> ret = getDrops(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
        if (ret.isEmpty())
            return null;
        return ret.get(0);
    }

    public boolean hasComparatorInputOverride() {
        return true;
    }

    public int getComparatorInputOverride(World world, int x, int y, int z, int side) {
        if (IUCore.proxy.isSimulating()) {
            TileEntityCable te = (TileEntityCable) getOwnTe(world, x, y, z);
            if (te == null)
                return 0;

        }
        return 0;
    }

    public boolean rotateBlock(World worldObj, int x, int y, int z, ForgeDirection axis) {
        if (axis == ForgeDirection.UNKNOWN)
            return false;
        TileEntity tileEntity = getOwnTe(worldObj, x, y, z);
        if (tileEntity instanceof IWrenchable) {
            IWrenchable te = (IWrenchable) tileEntity;
            int newFacing = ForgeDirection.getOrientation(te.getFacing()).getRotation(axis).ordinal();
            if (te.wrenchCanSetFacing(null, newFacing))
                te.setFacing((short) newFacing);
        }
        return false;
    }

    @SubscribeEvent
    public void onRetexture(RetextureEvent event) {
        if (event.world.getBlock(event.x, event.y, event.z) != this)
            return;
        TileEntityCable te = (TileEntityCable) getOwnTe(event.world, event.x, event.y, event.z);
        if (te == null)
            return;
        if (te.retexture(event.side, event.referencedBlock, event.referencedMeta, event.referencedSide))
            event.applied = true;
    }

    public int colorMultiplier(IBlockAccess par1iBlockAccess, int x, int y, int z) {
        if (this.colorMultiplier != -1)
            return this.colorMultiplier;
        return super.colorMultiplier(par1iBlockAccess, x, y, z);
    }
}
