package com.Denfop.block.mechanism;

import com.Denfop.IUCore;
import com.Denfop.api.utils.textures.TextureAtlasSheet;
import com.Denfop.tiles.Mechanism.TileEntityDoubleRecycler;
import com.Denfop.tiles.Mechanism.TileEntityQuadRecycler;
import com.Denfop.tiles.Mechanism.TileEntityTripleRecycler;
import com.Denfop.tiles.base.TileEntityMultiMachine;
import com.Denfop.utils.ExperienceUtils;
import ic2.api.item.IC2Items;
import ic2.api.tile.IWrenchable;
import ic2.core.IC2;
import ic2.core.block.TileEntityBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class BlockMachines1 extends BlockContainer {
    public static final String[] names = new String[]{
            "Recycler", "Recycler1", "Recycler2"
    };

    private static final int[][] sideAndFacingToSpriteOffset = new int[][]{
            {3, 2, 0, 0, 0, 0},
            {2, 3, 1, 1, 1, 1},
            {1, 1, 3, 5, 2, 4},
            {0, 0, 5, 3, 4, 2},
            {4, 5, 4, 2, 3, 5},
            {5, 4, 2, 4, 5, 3}};
    private IIcon[][] iconBuffer;

    public BlockMachines1() {
        super(Material.iron);
        setHardness(2.0F);
        setStepSound(soundTypeMetal);
        this.setCreativeTab(IUCore.tabssp);
    }

    @Override
    public TileEntity createTileEntity(World world, int meta) {
        switch (meta) {
            case 0:
                return new TileEntityDoubleRecycler();
            case 1:
                return new TileEntityTripleRecycler();
            case 2:
                return new TileEntityQuadRecycler();


        }
        return null;
    }

    @Override
    public void registerBlockIcons(final IIconRegister par1IconRegister) {
        this.iconBuffer = new IIcon[16][12];

        for (int i = 0; i < names.length; i++) {
            IIcon[] icons = TextureAtlasSheet.unstitchIcons(par1IconRegister, "supersolarpanel:block" + names[i], 12, 1);
            for (int k = 0; k < icons.length; k++) {
                iconBuffer[i][k] = icons[k];
            }
        }
    }

    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int blockSide) {
        int blockMeta = world.getBlockMetadata(x, y, z);
        TileEntity te = world.getTileEntity(x, y, z);
        int facing = (te instanceof TileEntityBlock) ? ((int) (((TileEntityBlock) te).getFacing())) : 0;

        if (isActive(world, x, y, z))
            return iconBuffer[blockMeta][sideAndFacingToSpriteOffset[blockSide][facing] + 6];
        else
            return iconBuffer[blockMeta][sideAndFacingToSpriteOffset[blockSide][facing]];
    }

    @Override
    public IIcon getIcon(int blockSide, int blockMeta) {
        return iconBuffer[blockMeta][sideAndFacingToSpriteOffset[blockSide][3]];
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return null;
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> dropList = super.getDrops(world, x, y, z, metadata, fortune);
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof IInventory) {
            IInventory iinv = (IInventory) te;
            for (int index = 0; index < iinv.getSizeInventory(); ++index) {
                ItemStack itemstack = iinv.getStackInSlot(index);
                if (itemstack != null) {
                    dropList.add(itemstack);
                    iinv.setInventorySlotContents(index, null);
                }
            }
        }

        return dropList;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block blockID, int blockMeta) {
        super.breakBlock(world, x, y, z, blockID, blockMeta);
        boolean var5 = true;
        for (Iterator<ItemStack> iter = getDrops(world, x, y, z, world.getBlockMetadata(x, y, z), 0).iterator(); iter
                .hasNext(); var5 = false) {
            ItemStack var7 = iter.next();
            if (!var5) {
                if (var7 == null) {
                    return;
                }

                double var8 = 0.7D;
                double var10 = (double) world.rand.nextFloat() * var8 + (1.0D - var8) * 0.5D;
                double var12 = (double) world.rand.nextFloat() * var8 + (1.0D - var8) * 0.5D;
                double var14 = (double) world.rand.nextFloat() * var8 + (1.0D - var8) * 0.5D;
                EntityItem var16 = new EntityItem(world, (double) x + var10, (double) y + var12, (double) z + var14,
                        var7);
                var16.delayBeforeCanPickup = 10;
                world.spawnEntityInWorld(var16);
                return;
            }
        }
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return IC2Items.getItem("advancedMachine").getItem();
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z); // advanced machine item meta
        // exactly equals the block meta
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
        super.onBlockPlacedBy(world, x, y, z, player, stack);
        int heading = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        TileEntityBlock te = (TileEntityBlock) world.getTileEntity(x, y, z);
        switch (heading) {
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

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int par6, float par7,
                                    float par8, float par9) {
        if (!entityPlayer.isSneaking()) {
            entityPlayer.openGui(IUCore.instance, 0, world, x, y, z);
            return true;
        } else {
            TileEntityMultiMachine tile = (TileEntityMultiMachine) world.getTileEntity(x, y, z);
            int currentXP = ExperienceUtils.getPlayerXP(entityPlayer);
            int nextLevelXP = ExperienceUtils.getExperienceForLevel(entityPlayer.experienceLevel + 1);
            int requiredXP = nextLevelXP - currentXP;

            int requiredXPJuice = 20 * requiredXP;
            ExperienceUtils.addPlayerXP(entityPlayer, tile.expstorage);
            tile.expstorage = 0;
        }

        return false;
    }

    private boolean isActive(IBlockAccess iba, int x, int y, int z) {
        return ((TileEntityBlock) iba.getTileEntity(x, y, z)).getActive();
    }

    @Override
    public boolean rotateBlock(World worldObj, int x, int y, int z, ForgeDirection axis) {
        if (axis == ForgeDirection.UNKNOWN) {
            return false;
        }
        TileEntity tileEntity = worldObj.getTileEntity(x, y, z);

        if ((tileEntity instanceof IWrenchable)) {
            IWrenchable te = (IWrenchable) tileEntity;

            int newFacing = ForgeDirection.getOrientation(te.getFacing()).getRotation(axis).ordinal();

            if (te.wrenchCanSetFacing(null, newFacing)) {
                te.setFacing((short) newFacing);
            }
        }

        return false;
    }

    @Override
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        if (!IC2.platform.isRendering()) {
            return;
        }
        int meta = world.getBlockMetadata(i, j, k);

        if ((meta == 1) && (isActive(world, i, j, k))) {
            TileEntity te = world.getTileEntity(i, j, k);
            int facing = (te instanceof TileEntityBlock) ? ((TileEntityBlock) te).getFacing() : 0;

            float f = i + 0.5F;
            float f1 = j + 0.0F + random.nextFloat() * 6.0F / 16.0F;
            float f2 = k + 0.5F;
            float f3 = 0.52F;
            float f4 = random.nextFloat() * 0.6F - 0.3F;


        }
        if ((meta == 3) && (isActive(world, i, j, k))) {
            float f = i + 1.0F;
            float f1 = j + 1.0F;
            float f2 = k + 1.0F;
            for (int z = 0; z < 4; z++) {
                float fmod = -0.2F - random.nextFloat() * 0.6F;
                float f1mod = -0.1F + random.nextFloat() * 0.2F;
                float f2mod = -0.2F - random.nextFloat() * 0.6F;
                world.spawnParticle("smoke", f + fmod, f1 + f1mod, f2 + f2mod, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    public int getComparatorInputOverride(World world, int x, int y, int z, int side) {
        return 0;
    }

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

}
