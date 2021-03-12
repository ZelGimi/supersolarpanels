package com.Denfop.block.mechanism;

import com.Denfop.IUCore;
import com.Denfop.proxy.ClientProxy;
import com.Denfop.tiles.Mechanism.*;
import com.Denfop.tiles.NeutroniumGenerator.TileBitGen2;
import com.Denfop.tiles.base.TileEntityMolecularTransformer;
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

public class BlockMachine extends BlockContainer {
    private IIcon[][] iconBuffer;


    public BlockMachine() {
        super(Material.iron);
        setHardness(2.0F);
        setStepSound(soundTypeMetal);
        this.setCreativeTab(IUCore.tabssp);


    }

    @Override
    public TileEntity createTileEntity(World world, int meta) {
        switch (meta) {
            case 0:
                return new TileEntityMolecularTransformer();
            case 1:
                return new TileEntityAdvancedMatter();

            case 2:
                return new TileEntityImprovedMatter();
            case 3:
                return new TileEntityUltimateMatter();

            case 4:
                return new TileEntityAlloySmelter();
            case 5:
                return new TileBitGen2();
            case 6:
                return new TileEntityGenerationMicrochip();
            case 7:
                return new TileEntityGenerationStone();
            case 8:
                return new TileEntityQuantumQuarry();

        }
        return null;
    }

    @Override
    public void registerBlockIcons(final IIconRegister par1IconRegister) {
        this.iconBuffer = new IIcon[9][12];


        this.iconBuffer[0][0] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[0][1] = par1IconRegister.registerIcon("supersolarpanel:molecularblock");
        this.iconBuffer[0][2] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[0][3] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[0][4] = par1IconRegister.registerIcon("supersolarpanel:molecularside");
        this.iconBuffer[0][5] = par1IconRegister.registerIcon("supersolarpanel:molecularside");
        this.iconBuffer[0][6] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[0][7] = par1IconRegister.registerIcon("supersolarpanel:molecularblock1");
        this.iconBuffer[0][8] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[0][9] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[0][10] = par1IconRegister.registerIcon("supersolarpanel:molecularside1");
        this.iconBuffer[0][11] = par1IconRegister.registerIcon("supersolarpanel:molecularside1");


        this.iconBuffer[1][0] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[1][1] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[1][2] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[1][3] = par1IconRegister.registerIcon("supersolarpanel:blockMatter1");
        this.iconBuffer[1][4] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[1][5] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[1][6] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[1][7] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[1][8] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[1][9] = par1IconRegister.registerIcon("supersolarpanel:blockMatter2");
        this.iconBuffer[1][10] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[1][11] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");


        this.iconBuffer[2][0] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[2][1] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[2][2] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[2][3] = par1IconRegister.registerIcon("supersolarpanel:blockMatter3");
        this.iconBuffer[2][4] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[2][5] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[2][6] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[2][7] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[2][8] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[2][9] = par1IconRegister.registerIcon("supersolarpanel:blockMatter4");
        this.iconBuffer[2][10] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[2][11] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");

        this.iconBuffer[3][0] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[3][1] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[3][2] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[3][3] = par1IconRegister.registerIcon("supersolarpanel:blockMatter5");
        this.iconBuffer[3][4] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[3][5] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[3][6] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[3][7] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[3][8] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[3][9] = par1IconRegister.registerIcon("supersolarpanel:blockMatter6");
        this.iconBuffer[3][10] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[3][11] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");


        this.iconBuffer[4][0] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[4][1] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[4][2] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[4][3] = par1IconRegister.registerIcon("supersolarpanel:blocksmelter3");
        this.iconBuffer[4][4] = par1IconRegister.registerIcon("supersolarpanel:blocksmelter1");
        this.iconBuffer[4][5] = par1IconRegister.registerIcon("supersolarpanel:blocksmelter1");
        this.iconBuffer[4][6] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[4][7] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[4][8] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[4][9] = par1IconRegister.registerIcon("supersolarpanel:smelter5");
        this.iconBuffer[4][10] = par1IconRegister.registerIcon("supersolarpanel:smelter4");
        this.iconBuffer[4][11] = par1IconRegister.registerIcon("supersolarpanel:smelter4");

        this.iconBuffer[5][0] = par1IconRegister.registerIcon("supersolarpanel:Blockgenbase");
        this.iconBuffer[5][1] = par1IconRegister.registerIcon("supersolarpanel:blockgentop");
        this.iconBuffer[5][2] = par1IconRegister.registerIcon("supersolarpanel:Blockgenbase");
        this.iconBuffer[5][3] = par1IconRegister.registerIcon("supersolarpanel:Blockgenbase");
        this.iconBuffer[5][4] = par1IconRegister.registerIcon("supersolarpanel:Blockgenbase");
        this.iconBuffer[5][5] = par1IconRegister.registerIcon("supersolarpanel:Blockgenbase");
        this.iconBuffer[5][6] = par1IconRegister.registerIcon("supersolarpanel:blockgentop");
        this.iconBuffer[5][7] = par1IconRegister.registerIcon("supersolarpanel:blockgentop");
        this.iconBuffer[5][8] = par1IconRegister.registerIcon("supersolarpanel:Blockgenbase");
        this.iconBuffer[5][9] = par1IconRegister.registerIcon("supersolarpanel:Blockgenbase");
        this.iconBuffer[5][10] = par1IconRegister.registerIcon("supersolarpanel:Blockgenbase");
        this.iconBuffer[5][11] = par1IconRegister.registerIcon("supersolarpanel:Blockgenbase");

        this.iconBuffer[6][0] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[6][1] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[6][2] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[6][3] = par1IconRegister.registerIcon("supersolarpanel:genmicro");
        this.iconBuffer[6][4] = par1IconRegister.registerIcon("supersolarpanel:genmicro2");
        this.iconBuffer[6][5] = par1IconRegister.registerIcon("supersolarpanel:genmicro2");
        this.iconBuffer[6][6] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[6][7] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[6][8] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[6][9] = par1IconRegister.registerIcon("supersolarpanel:genmicro");
        this.iconBuffer[6][10] = par1IconRegister.registerIcon("supersolarpanel:genmicro1");
        this.iconBuffer[6][11] = par1IconRegister.registerIcon("supersolarpanel:genmicro1");

        this.iconBuffer[7][0] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[7][1] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[7][2] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[7][3] = par1IconRegister.registerIcon("supersolarpanel:genstone");
        this.iconBuffer[7][4] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[7][5] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[7][6] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[7][7] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[7][8] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[7][9] = par1IconRegister.registerIcon("supersolarpanel:genstone1");
        this.iconBuffer[7][10] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[7][11] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[8][0] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[8][1] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[8][2] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[8][3] = par1IconRegister.registerIcon("supersolarpanel:quantumquerry");
        this.iconBuffer[8][4] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[8][5] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[8][6] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[8][7] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[8][8] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[8][9] = par1IconRegister.registerIcon("supersolarpanel:quantumquerry1");
        this.iconBuffer[8][10] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
        this.iconBuffer[8][11] = par1IconRegister.registerIcon("supersolarpanel:blockAdvMachine");
    }

    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int blockSide) {
        int blockMeta = world.getBlockMetadata(x, y, z);
        TileEntity te = world.getTileEntity(x, y, z);
        int facing = (te instanceof TileEntityBlock) ? ((int) (((TileEntityBlock) te).getFacing())) : 0;

        if (isActive(world, x, y, z))
            return iconBuffer[blockMeta][ClientProxy.sideAndFacingToSpriteOffset[blockSide][facing] + 6];
        else
            return iconBuffer[blockMeta][ClientProxy.sideAndFacingToSpriteOffset[blockSide][facing]];
    }

    @Override
    public IIcon getIcon(int blockSide, int blockMeta) {
        return iconBuffer[blockMeta][ClientProxy.sideAndFacingToSpriteOffset[blockSide][3]];
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
        for (Iterator<ItemStack> iter = getDrops(world, x, y, z, world.getBlockMetadata(x, y, z), 0).iterator(); iter.hasNext(); var5 = false) {
            ItemStack var7 = iter.next();
            if (!var5) {
                if (var7 == null) {
                    return;
                }

                double var8 = 0.7D;
                double var10 = (double) world.rand.nextFloat() * var8 + (1.0D - var8) * 0.5D;
                double var12 = (double) world.rand.nextFloat() * var8 + (1.0D - var8) * 0.5D;
                double var14 = (double) world.rand.nextFloat() * var8 + (1.0D - var8) * 0.5D;
                EntityItem var16 = new EntityItem(world, (double) x + var10, (double) y + var12, (double) z + var14, var7);
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
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int par6, float par7, float par8, float par9) {
        if (!entityPlayer.isSneaking()) {
            entityPlayer.openGui(IUCore.instance, 0, world, x, y, z);
            return true;
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

    }

    public int getComparatorInputOverride(World world, int x, int y, int z, int side) {
        TileEntityBlock te = (TileEntityBlock) world.getTileEntity(x, y, z);

        if (te instanceof TileBitGen2)
            return (int) Math.floor(((TileBitGen2) te).energy / 1000000.0D * 15.0D);


        return 0;
    }

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }


}
