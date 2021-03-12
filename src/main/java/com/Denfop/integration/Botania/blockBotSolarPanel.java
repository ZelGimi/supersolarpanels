package com.Denfop.integration.Botania;

import com.Denfop.IUCore;
import com.Denfop.proxy.ClientProxy;
import com.Denfop.tiles.base.TileEntityBase;
import com.Denfop.tiles.base.TileEntitySolarPanel;
import com.Denfop.tiles.overtimepanel.TileEntityAdvancedSolarPanel;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class blockBotSolarPanel extends BlockContainer {
    public boolean qgActive;

    private IIcon[][] iconBuffer;

    public blockBotSolarPanel() {
        super(Material.iron);
        setHardness(3.0F);
        setCreativeTab(IUCore.tabssp);
        this.qgActive = false;
    }

    public static boolean isActive(IBlockAccess var0, int var1, int var2, int var3) {
        return ((TileEntityBase) var0.getTileEntity(var1, var2, var3)).getActive();
    }

    public static TileEntity getBlockEntity(int i) {
        switch (i) {
            case 0:
                return new TileEntityManasteelSolarPanel();
            case 1:
                return new TileEntityElementumSolarPanel();
            case 2:
                return new TileEntityTerrastelSolarPanel();


        }
        return new TileEntityAdvancedSolarPanel();
    }

    public void registerBlockIcons(IIconRegister par1IconRegister) {
        this.iconBuffer = new IIcon[3][12];
        this.iconBuffer[0][0] = par1IconRegister.registerIcon("supersolarpanel:manasteel_side");
        this.iconBuffer[0][1] = par1IconRegister.registerIcon("supersolarpanel:manasteel_top");
        this.iconBuffer[0][2] = par1IconRegister.registerIcon("supersolarpanel:manasteel_side");
        this.iconBuffer[0][3] = par1IconRegister.registerIcon("supersolarpanel:manasteel_side");
        this.iconBuffer[0][4] = par1IconRegister.registerIcon("supersolarpanel:manasteel_side");
        this.iconBuffer[0][5] = par1IconRegister.registerIcon("supersolarpanel:manasteel_side");
        this.iconBuffer[0][6] = par1IconRegister.registerIcon("supersolarpanel:manasteel_side");
        this.iconBuffer[0][7] = par1IconRegister.registerIcon("supersolarpanel:manasteel_top");
        this.iconBuffer[0][8] = par1IconRegister.registerIcon("supersolarpanel:manasteel_side");
        this.iconBuffer[0][9] = par1IconRegister.registerIcon("supersolarpanel:manasteel_side");
        this.iconBuffer[0][10] = par1IconRegister.registerIcon("supersolarpanel:manasteel_side");
        this.iconBuffer[0][11] = par1IconRegister.registerIcon("supersolarpanel:manasteel_side");
        this.iconBuffer[1][0] = par1IconRegister.registerIcon("supersolarpanel:manasteel_side");
        this.iconBuffer[1][1] = par1IconRegister.registerIcon("supersolarpanel:elementium_top");
        this.iconBuffer[1][2] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer[1][3] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer[1][4] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer[1][5] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer[1][6] = par1IconRegister.registerIcon("supersolarpanel:hsp_bottom");
        this.iconBuffer[1][7] = par1IconRegister.registerIcon("supersolarpanel:elementium_top");
        this.iconBuffer[1][8] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer[1][9] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer[1][10] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer[1][11] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer[2][0] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer[2][1] = par1IconRegister.registerIcon("supersolarpanel:terasteel_top");
        this.iconBuffer[2][2] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer[2][3] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer[2][4] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer[2][5] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer[2][6] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer[2][7] = par1IconRegister.registerIcon("supersolarpanel:terasteel_top");
        this.iconBuffer[2][8] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer[2][9] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer[2][10] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer[2][11] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
    }

    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int blockSide) {
        int blockMeta = world.getBlockMetadata(x, y, z);
        TileEntity te = world.getTileEntity(x, y, z);
        int facing = (te instanceof TileEntityBase) ? ((com.Denfop.tiles.base.TileEntityBase) te).getFacing() : 0;
        if (isActive(world, x, y, z))
            return this.iconBuffer[blockMeta][ClientProxy.sideAndFacingToSpriteOffset[blockSide][facing] + 6];
        return this.iconBuffer[blockMeta][ClientProxy.sideAndFacingToSpriteOffset[blockSide][facing]];
    }

    public IIcon getIcon(int blockSide, int blockMeta) {
        return this.iconBuffer[blockMeta][ClientProxy.sideAndFacingToSpriteOffset[blockSide][3]];
    }

    public void breakBlock(World world, int i, int j, int k, Block par5, int par6) {
        TileEntity tileentity = world.getTileEntity(i, j, k);
        if (tileentity != null)

            dropItems((TileEntitySolarPanel) tileentity, world);
        world.removeTileEntity(i, j, k);
        super.breakBlock(world, i, j, k, par5, par6);
    }

    public int quantityDropped(Random random) {
        return 1;
    }

    public int damageDropped(int i) {
        return i;
    }

    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int s, float f1, float f2, float f3) {
        if (player.isSneaking())
            return false;
        if (world.isRemote)
            return true;
        TileEntity tileentity = world.getTileEntity(i, j, k);
        if (tileentity != null)
            player.openGui(IUCore.instance, 1, world, i, j, k);
        return true;
    }

    private void dropItems(TileEntitySolarPanel tileentity, World world) {
        Random rand = new Random();
        if (!(tileentity instanceof net.minecraft.inventory.IInventory))
            return;
        TileEntitySolarPanel tileEntitySolarPanel = tileentity;
        for (int i = 0; i < tileEntitySolarPanel.getSizeInventory(); i++) {
            ItemStack item = tileEntitySolarPanel.getStackInSlot(i);
            if (item != null && item.stackSize > 0) {
                float rx = rand.nextFloat() * 0.8F + 0.1F;
                float ry = rand.nextFloat() * 0.8F + 0.1F;
                float rz = rand.nextFloat() * 0.8F + 0.1F;
                EntityItem entityItem = new EntityItem(world, (tileentity.xCoord + rx), (tileentity.yCoord + ry), (tileentity.zCoord + rz), new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));
                if (item.hasTagCompound())
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.20000000298023224D;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                item.stackSize = 0;
            }
        }
    }

    public TileEntity createNewTileEntity(World var1, int var2) {
        return getBlockEntity(var2);
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(final Item item, final CreativeTabs tab, final List subItems) {
        for (int ix = 0; ix < this.iconBuffer.length; ++ix) {
            subItems.add(new ItemStack(this, 1, ix));
        }
    }
}
