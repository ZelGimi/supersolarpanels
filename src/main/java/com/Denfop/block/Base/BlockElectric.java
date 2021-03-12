package com.Denfop.block.Base;

import cofh.api.energy.IEnergyContainerItem;
import com.Denfop.IUCore;
import com.Denfop.proxy.ClientProxy;
import com.Denfop.tiles.base.TileEntityElectricBlock;
import com.Denfop.tiles.wiring.Storage.*;
import com.Denfop.utils.NBTData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import ic2.api.item.IC2Items;
import ic2.api.item.IElectricItem;
import ic2.api.tile.IWrenchable;
import ic2.core.block.TileEntityBlock;
import ic2.core.util.Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class BlockElectric extends BlockContainer {
    private IIcon[][] iconBuffer;

    public BlockElectric() {
        super(Material.iron);
        setHardness(1.5F);
        setStepSound(soundTypeMetal);
        this.setCreativeTab(IUCore.tabssp);
        setBlockUnbreakable();

    }

    @Override
    public TileEntity createTileEntity(World world, int meta) {
        switch (meta) {
            case 0:
                return new TileEntityElectricMFE();
            case 1:
                return new TileEntityElectricMFSU();
            case 2:
                return new TileEntityElectricBatBox();
            case 3:
                return new TileEntityElectricMFEMin();
            case 4:
                return new TileEntityElectricMFSUMin();
            case 5:
                return new TileEntityElectricCESU();

        }
        return null;
    }

    @Override
    public void registerBlockIcons(final IIconRegister par1IconRegister) {
        this.iconBuffer = new IIcon[6][6];
        this.iconBuffer[1][0] = par1IconRegister.registerIcon("supersolarpanel:blockmfsu2");
        this.iconBuffer[1][1] = par1IconRegister.registerIcon("supersolarpanel:blockmfsu2");
        this.iconBuffer[1][2] = par1IconRegister.registerIcon("supersolarpanel:blockmfsu2");
        this.iconBuffer[1][3] = par1IconRegister.registerIcon("supersolarpanel:hv_transformer_bottomtop");
        this.iconBuffer[1][4] = par1IconRegister.registerIcon("supersolarpanel:blockmfsu2");
        this.iconBuffer[1][5] = par1IconRegister.registerIcon("supersolarpanel:blockmfsu2");
        this.iconBuffer[0][0] = par1IconRegister.registerIcon("supersolarpanel:blockMFSU");
        this.iconBuffer[0][1] = par1IconRegister.registerIcon("supersolarpanel:blockMFSU");
        this.iconBuffer[0][2] = par1IconRegister.registerIcon("supersolarpanel:blockMFSU");
        this.iconBuffer[0][3] = par1IconRegister.registerIcon("supersolarpanel:blockChargepadMFSUtop");
        this.iconBuffer[0][4] = par1IconRegister.registerIcon("supersolarpanel:blockMFSU");
        this.iconBuffer[0][5] = par1IconRegister.registerIcon("supersolarpanel:blockMFSU");

        this.iconBuffer[2][0] = par1IconRegister.registerIcon("supersolarpanel:blockBatBox_bottom");
        this.iconBuffer[2][1] = par1IconRegister.registerIcon("supersolarpanel:blockBatBox_top");
        this.iconBuffer[2][2] = par1IconRegister.registerIcon("supersolarpanel:blockBatBox_side");
        this.iconBuffer[2][3] = par1IconRegister.registerIcon("supersolarpanel:blockBatBox_main");
        this.iconBuffer[2][4] = par1IconRegister.registerIcon("supersolarpanel:blockBatBox_side");
        this.iconBuffer[2][5] = par1IconRegister.registerIcon("supersolarpanel:blockBatBox_side_1");
    }

    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int blockSide) {
        int blockMeta = world.getBlockMetadata(x, y, z);
        TileEntity te = world.getTileEntity(x, y, z);
        int facing = (te instanceof TileEntityBlock) ? ((int) (((TileEntityBlock) te).getFacing())) : 0;


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
        TileEntityElectricBlock te = (TileEntityElectricBlock) world.getTileEntity(x, y, z);

        NBTTagCompound nbttagcompound1 = NBTData.getOrCreateNbtData(stack);
        double energy1 = nbttagcompound1.getDouble("energy");
        double energy2 = nbttagcompound1.getDouble("energy2");
        te.energy = energy1;
        te.energy2 = energy2;
        if (player instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer) player;
            te.UUID = entityplayer.getDisplayName();
        }
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
        if (entityPlayer.isSneaking()) {
            TileEntityElectricBlock tile = (TileEntityElectricBlock) world.getTileEntity(x, y, z);
            if (tile.movementcharge) {

                for (ItemStack armorcharged : entityPlayer.inventory.armorInventory) {
                    if (armorcharged != null) {
                        if (armorcharged.getItem() instanceof IElectricItem && tile.energy > 0) {
                            double sent = ElectricItem.manager.charge(armorcharged, tile.energy, 2147483647, true, false);
                            entityPlayer.inventoryContainer.detectAndSendChanges();
                            tile.energy -= sent;

                            tile.needsInvUpdate = (sent > 0.0D);
                            if (sent > 0) {

                                entityPlayer.addChatMessage(new ChatComponentTranslation(StatCollector.translateToLocal("successfully.charged") + armorcharged.getDisplayName() + " " + StatCollector.translateToLocal("ssp.sendenergy") + MathHelper.floor_double(sent) + " EU"));

                            }
                        }

                    }

                }
            }
            if (tile.movementchargerf) {

                for (ItemStack charged : entityPlayer.inventory.armorInventory) {
                    if (charged != null) {

                        ItemStack stack = charged;
                        if (stack != null && stack.getItem() instanceof IEnergyContainerItem && tile.energy2 > 0) {
                            int sent = 0;

                            IEnergyContainerItem item = (IEnergyContainerItem) stack.getItem();
                            for (; item.getEnergyStored(charged) < item.getMaxEnergyStored(charged) && tile.energy2 > 0; ) {
                                sent = sent + tile.extractEnergy(null, item.receiveEnergy(stack, (int) tile.energy2, false), false);

                                tile.setTransfer(tile.extractEnergy(null, item.receiveEnergy(stack, (int) tile.energy2, false), false) > 0);
                            }
                            entityPlayer.inventoryContainer.detectAndSendChanges();

                        }


                    }
                }
            }
            if (tile.movementchargeitem) {
                for (ItemStack charged : entityPlayer.inventory.mainInventory) {
                    if (charged != null) {
                        if (charged.getItem() instanceof IElectricItem && tile.energy > 0) {
                            double sent = ElectricItem.manager.charge(charged, tile.energy, 2147483647, true, false);

                            tile.energy -= sent;
                            entityPlayer.inventoryContainer.detectAndSendChanges();
                            tile.needsInvUpdate = (sent > 0.0D);
                            if (sent > 0) {

                                entityPlayer.addChatMessage(new ChatComponentTranslation(StatCollector.translateToLocal("successfully.charged") + charged.getDisplayName() + " " + StatCollector.translateToLocal("ssp.sendenergy") + MathHelper.floor_double(sent) + " EU"));

                            }
                        }

                    }

                }

            }
            if (tile.movementchargeitemrf) {
                for (ItemStack charged : entityPlayer.inventory.mainInventory) {
                    if (charged != null) {

                        ItemStack stack = charged;
                        if (stack != null && stack.getItem() instanceof IEnergyContainerItem && tile.energy2 > 0) {
                            int sent = 0;
                            IEnergyContainerItem item = (IEnergyContainerItem) stack.getItem();
                            for (; item.getEnergyStored(charged) < item.getMaxEnergyStored(charged) && tile.energy2 > 0; ) {
                                tile.setTransfer(tile.extractEnergy(null, item.receiveEnergy(stack, (int) tile.energy2, false), false) > 0);
                                sent = +tile.extractEnergy(null, item.receiveEnergy(stack, (int) tile.energy2, false), false);
                            }

                            entityPlayer.inventoryContainer.detectAndSendChanges();

                        }


                    }
                }
            }
        }
        if (!entityPlayer.isSneaking()) {
            final TileEntity tileentity = world.getTileEntity(x, y, z);
            if (tileentity != null) {

                if (world.getTileEntity(x, y, z) instanceof TileEntityElectricBlock) {
                    TileEntityElectricBlock tile = (TileEntityElectricBlock) world.getTileEntity(x, y, z);

                    if (tile.personality && tile.UUID == entityPlayer.getDisplayName()) {
                        entityPlayer.openGui(IUCore.instance, 1, world, x, y, z);


                    } else {
                        if (tile.personality == false) {

                            entityPlayer.openGui(IUCore.instance, 1, world, x, y, z);
                        } else {
                            if (tile.personality && tile.UUID != entityPlayer.getDisplayName())
                                entityPlayer.addChatMessage(new ChatComponentTranslation(String.format("ssp.error")));

                        }
                    }


                }

            }

        } else {


            return false;


        }

        return true;
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
    public boolean canProvidePower() {
        return true;
    }


    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }


    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack stack) {
        return (stack.getItemDamage() == 0 || stack.getItemDamage() == 1) ? EnumRarity.uncommon : EnumRarity.common;
    }

    @Override
    public int getComparatorInputOverride(World world, int x, int y, int z, int side) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (!(te instanceof TileEntityBlock))
            return 0;
        TileEntityElectricBlock teb = (TileEntityElectricBlock) te;
        return (int) Math.round(Util.map(teb.energy, teb.maxStorage, 15.0D));

    }

}
