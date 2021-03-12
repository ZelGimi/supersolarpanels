package com.Denfop.block.Base;

import com.Denfop.IUCore;
import com.Denfop.SSPItem;
import com.Denfop.item.Modules.module7;
import com.Denfop.proxy.ClientProxy;
import com.Denfop.tiles.base.TileEntityBase;
import com.Denfop.tiles.base.TileEntitySolarPanel;
import com.Denfop.tiles.overtimepanel.*;
import com.Denfop.utils.NBTData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class BlockSSPSolarPanel extends BlockContainer {
    public boolean qgActive;
    private IIcon[][] iconBuffer;
    private IIcon[][] iconBuffer1;
    private IIcon[][] iconBuffer2;
    private IIcon[][] iconBuffer3;
    private IIcon[][] iconBuffer4;
    private IIcon[][] iconBuffer5;
    private IIcon[][] iconBuffer6;
    private IIcon[][] iconBuffer7;

    public BlockSSPSolarPanel() {
        super(Material.iron);
        this.setHardness(3.0f);
        this.setCreativeTab(IUCore.tabssp);
        this.qgActive = false;
        this.setBlockUnbreakable();

    }

    public static TileEntity getBlockEntity(final int i) {
        switch (i) {
            case 0:
                return new TileEntityAdvancedSolarPanel();
            case 1:
                return new TileEntityHybridSolarPanel();
            case 2:
                return new TileEntityUltimateSolarPanel();
            case 3:
                return new TileEntityQuantumSolarPanel();
            case 4: {
                return new TileSpectralSolarPanel();
            }
            case 5: {
                return new TileProtonSolarPanel();
            }
            case 6: {
                return new TileSingularSolarPanel();
            }
            case 8: {
                return new TilePhotonicSolarPanel();
            }
            case 7: {
                return new TileAdminSolarPanel();
            }
            case 9: {
                return new TileNeutronSolarPanel();
            }


        }
        return null;
    }

    @Override
    public void registerBlockIcons(final IIconRegister par1IconRegister) {
        this.iconBuffer = new IIcon[10][12];
        this.iconBuffer1 = new IIcon[10][12];
        this.iconBuffer2 = new IIcon[10][12];
        this.iconBuffer3 = new IIcon[10][12];
        this.iconBuffer4 = new IIcon[10][12];
        this.iconBuffer5 = new IIcon[10][12];
        this.iconBuffer6 = new IIcon[10][12];
        this.iconBuffer7 = new IIcon[10][12];
        this.iconBuffer[0][0] = par1IconRegister.registerIcon("supersolarpanel:asp_bottom");
        this.iconBuffer[0][1] = par1IconRegister.registerIcon("supersolarpanel:asp_top");
        this.iconBuffer[0][2] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer[0][3] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer[0][4] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer[0][5] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer[0][6] = par1IconRegister.registerIcon("supersolarpanel:asp_bottom");
        this.iconBuffer[1][0] = par1IconRegister.registerIcon("supersolarpanel:hsp_bottom");
        this.iconBuffer[1][1] = par1IconRegister.registerIcon("supersolarpanel:hsp_top");
        this.iconBuffer[1][2] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer[1][3] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer[1][4] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer[1][5] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer[1][6] = par1IconRegister.registerIcon("supersolarpanel:hsp_bottom");
        this.iconBuffer[1][7] = par1IconRegister.registerIcon("supersolarpanel:hsp_top");
        this.iconBuffer[1][8] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer[1][9] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer[1][10] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer[1][11] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer[2][0] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer[2][1] = par1IconRegister.registerIcon("supersolarpanel:usp_top");
        this.iconBuffer[2][2] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer[2][3] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer[2][4] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer[2][5] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer[2][6] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer[2][7] = par1IconRegister.registerIcon("supersolarpanel:usp_top");
        this.iconBuffer[2][8] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer[2][9] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer[2][10] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer[2][11] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer[3][0] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer[3][1] = par1IconRegister.registerIcon("supersolarpanel:qsp_top");
        this.iconBuffer[3][2] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer[3][3] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer[3][4] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer[3][5] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer[3][6] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer[3][7] = par1IconRegister.registerIcon("supersolarpanel:qsp_top");
        this.iconBuffer[3][8] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer[3][9] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer[3][10] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer[3][11] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer1[0][0] = par1IconRegister.registerIcon("supersolarpanel:asp_bottom");
        this.iconBuffer1[0][1] = par1IconRegister.registerIcon("supersolarpanel:asp_topaer");
        this.iconBuffer1[0][2] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer1[0][3] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer1[0][4] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer1[0][5] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer1[0][6] = par1IconRegister.registerIcon("supersolarpanel:asp_bottom");
        this.iconBuffer1[0][7] = par1IconRegister.registerIcon("supersolarpanel:asp_topaer");
        this.iconBuffer1[0][8] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer1[0][9] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer1[0][10] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer1[0][11] = par1IconRegister.registerIcon("superolarpanel:asp_side");
        this.iconBuffer1[1][0] = par1IconRegister.registerIcon("supersolarpanel:hsp_bottom");
        this.iconBuffer1[1][1] = par1IconRegister.registerIcon("supersolarpanel:hsp_topaer");
        this.iconBuffer1[1][2] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer1[1][3] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer1[1][4] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer1[1][5] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer1[1][6] = par1IconRegister.registerIcon("supersolarpanel:hsp_bottom");
        this.iconBuffer1[1][7] = par1IconRegister.registerIcon("supersolarpanel:hsp_topaer");
        this.iconBuffer1[1][8] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer1[1][9] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer1[1][10] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer1[1][11] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer1[2][0] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer1[2][1] = par1IconRegister.registerIcon("supersolarpanel:usp_topaer");
        this.iconBuffer1[2][2] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer1[2][3] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer1[2][4] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer1[2][5] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer1[2][6] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer1[2][7] = par1IconRegister.registerIcon("supersolarpanel:usp_topaer");
        this.iconBuffer1[2][8] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer1[2][9] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer1[2][10] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer1[2][11] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer1[3][0] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer1[3][1] = par1IconRegister.registerIcon("supersolarpanel:qsp_topaer");
        this.iconBuffer1[3][2] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer1[3][3] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer1[3][4] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer1[3][5] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer1[3][6] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer1[3][7] = par1IconRegister.registerIcon("supersolarpanel:qsp_topaer");
        this.iconBuffer1[3][8] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer1[3][9] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer1[3][10] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer1[3][11] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer2[0][0] = par1IconRegister.registerIcon("supersolarpanel:asp_bottom");
        this.iconBuffer2[0][1] = par1IconRegister.registerIcon("supersolarpanel:asp_topearth");
        this.iconBuffer2[0][2] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer2[0][3] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer2[0][4] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer2[0][5] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer2[0][6] = par1IconRegister.registerIcon("supersolarpanel:asp_bottom");
        this.iconBuffer2[0][7] = par1IconRegister.registerIcon("supersolarpanel:asp_topaer");
        this.iconBuffer2[0][8] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer2[0][9] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer2[0][10] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer2[0][11] = par1IconRegister.registerIcon("superolarpanel:asp_side");
        this.iconBuffer2[1][0] = par1IconRegister.registerIcon("supersolarpanel:hsp_bottom");
        this.iconBuffer2[1][1] = par1IconRegister.registerIcon("supersolarpanel:hsp_topearth");
        this.iconBuffer2[1][2] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer2[1][3] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer2[1][4] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer2[1][5] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer2[1][6] = par1IconRegister.registerIcon("supersolarpanel:hsp_bottom");
        this.iconBuffer2[1][7] = par1IconRegister.registerIcon("supersolarpanel:hsp_topearth");
        this.iconBuffer2[1][8] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer2[1][9] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer2[1][10] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer2[1][11] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer2[2][0] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer2[2][1] = par1IconRegister.registerIcon("supersolarpanel:usp_topearth");
        this.iconBuffer2[2][2] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer2[2][3] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer2[2][4] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer2[2][5] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer2[2][6] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer2[2][7] = par1IconRegister.registerIcon("supersolarpanel:usp_topearth");
        this.iconBuffer2[2][8] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer2[2][9] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer2[2][10] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer2[2][11] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer2[3][0] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer2[3][1] = par1IconRegister.registerIcon("supersolarpanel:qsp_topearth");
        this.iconBuffer2[3][2] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer2[3][3] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer2[3][4] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer2[3][5] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer2[3][6] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer2[3][7] = par1IconRegister.registerIcon("supersolarpanel:qsp_topearth");
        this.iconBuffer2[3][8] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer2[3][9] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer2[3][10] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer2[3][11] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer3[0][0] = par1IconRegister.registerIcon("supersolarpanel:asp_bottom");
        this.iconBuffer3[0][1] = par1IconRegister.registerIcon("supersolarpanel:asp_topnether");
        this.iconBuffer3[0][2] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer3[0][3] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer3[0][4] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer3[0][5] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer3[0][6] = par1IconRegister.registerIcon("supersolarpanel:asp_bottom");
        this.iconBuffer3[0][7] = par1IconRegister.registerIcon("supersolarpanel:asp_topnether");
        this.iconBuffer3[0][8] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer3[0][9] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer3[0][10] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer3[0][11] = par1IconRegister.registerIcon("superolarpanel:asp_side");
        this.iconBuffer3[1][0] = par1IconRegister.registerIcon("supersolarpanel:hsp_bottom");
        this.iconBuffer3[1][1] = par1IconRegister.registerIcon("supersolarpanel:hsp_topnether");
        this.iconBuffer3[1][2] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer3[1][3] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer3[1][4] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer3[1][5] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer3[1][6] = par1IconRegister.registerIcon("supersolarpanel:hsp_bottom");
        this.iconBuffer3[1][7] = par1IconRegister.registerIcon("supersolarpanel:hsp_topnether");
        this.iconBuffer3[1][8] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer3[1][9] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer3[1][10] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer3[1][11] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer3[2][0] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer3[2][1] = par1IconRegister.registerIcon("supersolarpanel:usp_topnether");
        this.iconBuffer3[2][2] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer3[2][3] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer3[2][4] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer3[2][5] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer3[2][6] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer3[2][7] = par1IconRegister.registerIcon("supersolarpanel:usp_topnether");
        this.iconBuffer3[2][8] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer3[2][9] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer3[2][10] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer3[2][11] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer3[3][0] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer3[3][1] = par1IconRegister.registerIcon("supersolarpanel:qsp_topnether");
        this.iconBuffer3[3][2] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer3[3][3] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer3[3][4] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer3[3][5] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer3[3][6] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer3[3][7] = par1IconRegister.registerIcon("supersolarpanel:qsp_topnether");
        this.iconBuffer3[3][8] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer3[3][9] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer3[3][10] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer3[3][11] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer4[0][0] = par1IconRegister.registerIcon("supersolarpanel:asp_bottom");
        this.iconBuffer4[0][1] = par1IconRegister.registerIcon("supersolarpanel:asp_topend");
        this.iconBuffer4[0][2] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer4[0][3] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer4[0][4] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer4[0][5] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer4[0][6] = par1IconRegister.registerIcon("supersolarpanel:asp_bottom");
        this.iconBuffer4[0][7] = par1IconRegister.registerIcon("supersolarpanel:asp_topend");
        this.iconBuffer4[0][8] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer4[0][9] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer4[0][10] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer4[0][11] = par1IconRegister.registerIcon("superolarpanel:asp_side");
        this.iconBuffer4[1][0] = par1IconRegister.registerIcon("supersolarpanel:hsp_bottom");
        this.iconBuffer4[1][1] = par1IconRegister.registerIcon("supersolarpanel:hsp_topend");
        this.iconBuffer4[1][2] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer4[1][3] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer4[1][4] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer4[1][5] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer4[1][6] = par1IconRegister.registerIcon("supersolarpanel:hsp_bottom");
        this.iconBuffer4[1][7] = par1IconRegister.registerIcon("supersolarpanel:hsp_topend");
        this.iconBuffer4[1][8] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer4[1][9] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer4[1][10] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer4[1][11] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer4[2][0] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer4[2][1] = par1IconRegister.registerIcon("supersolarpanel:usp_topend");
        this.iconBuffer4[2][2] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer4[2][3] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer4[2][4] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer4[2][5] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer4[2][6] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer4[2][7] = par1IconRegister.registerIcon("supersolarpanel:usp_topend");
        this.iconBuffer4[2][8] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer4[2][9] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer4[2][10] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer4[2][11] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer4[3][0] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer4[3][1] = par1IconRegister.registerIcon("supersolarpanel:qsp_topend");
        this.iconBuffer4[3][2] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer4[3][3] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer4[3][4] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer4[3][5] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer4[3][6] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer4[3][7] = par1IconRegister.registerIcon("supersolarpanel:qsp_topend");
        this.iconBuffer4[3][8] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer4[3][9] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer4[3][10] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer4[3][11] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer5[0][0] = par1IconRegister.registerIcon("supersolarpanel:asp_bottom");
        this.iconBuffer5[0][1] = par1IconRegister.registerIcon("supersolarpanel:asp_topmoon");
        this.iconBuffer5[0][2] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer5[0][3] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer5[0][4] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer5[0][5] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer5[0][6] = par1IconRegister.registerIcon("supersolarpanel:asp_bottom");
        this.iconBuffer5[0][7] = par1IconRegister.registerIcon("supersolarpanel:asp_topmoon");
        this.iconBuffer5[0][8] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer5[0][9] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer5[0][10] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer5[0][11] = par1IconRegister.registerIcon("superolarpanel:asp_side");
        this.iconBuffer5[1][0] = par1IconRegister.registerIcon("supersolarpanel:hsp_bottom");
        this.iconBuffer5[1][1] = par1IconRegister.registerIcon("supersolarpanel:hsp_topmoon");
        this.iconBuffer5[1][2] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer5[1][3] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer5[1][4] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer5[1][5] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer5[1][6] = par1IconRegister.registerIcon("supersolarpanel:hsp_bottom");
        this.iconBuffer5[1][7] = par1IconRegister.registerIcon("supersolarpanel:hsp_topmoon");
        this.iconBuffer5[1][8] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer5[1][9] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer5[1][10] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer5[1][11] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer5[2][0] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer5[2][1] = par1IconRegister.registerIcon("supersolarpanel:usp_topmoon");
        this.iconBuffer5[2][2] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer5[2][3] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer5[2][4] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer5[2][5] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer5[2][6] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer5[2][7] = par1IconRegister.registerIcon("supersolarpanel:usp_topmoon");
        this.iconBuffer5[2][8] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer5[2][9] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer5[2][10] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer5[2][11] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer5[3][0] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer5[3][1] = par1IconRegister.registerIcon("supersolarpanel:qsp_topmoon");
        this.iconBuffer5[3][2] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer5[3][3] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer5[3][4] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer5[3][5] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer5[3][6] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer5[3][7] = par1IconRegister.registerIcon("supersolarpanel:qsp_topmoon");
        this.iconBuffer5[3][8] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer5[3][9] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer5[3][10] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer5[3][11] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer6[0][0] = par1IconRegister.registerIcon("supersolarpanel:asp_bottom");
        this.iconBuffer6[0][1] = par1IconRegister.registerIcon("supersolarpanel:asp_topsun");
        this.iconBuffer6[0][2] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer6[0][3] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer6[0][4] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer6[0][5] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer6[0][6] = par1IconRegister.registerIcon("supersolarpanel:asp_bottom");
        this.iconBuffer6[0][7] = par1IconRegister.registerIcon("supersolarpanel:asp_topsun");
        this.iconBuffer6[0][8] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer6[0][9] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer6[0][10] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer6[0][11] = par1IconRegister.registerIcon("superolarpanel:asp_side");
        this.iconBuffer6[1][0] = par1IconRegister.registerIcon("supersolarpanel:hsp_bottom");
        this.iconBuffer6[1][1] = par1IconRegister.registerIcon("supersolarpanel:hsp_topsun");
        this.iconBuffer6[1][2] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer6[1][3] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer6[1][4] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer6[1][5] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer6[1][6] = par1IconRegister.registerIcon("supersolarpanel:hsp_bottom");
        this.iconBuffer6[1][7] = par1IconRegister.registerIcon("supersolarpanel:hsp_topsun");
        this.iconBuffer6[1][8] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer6[1][9] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer6[1][10] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer6[1][11] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer6[2][0] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer6[2][1] = par1IconRegister.registerIcon("supersolarpanel:usp_topsun");
        this.iconBuffer6[2][2] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer6[2][3] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer6[2][4] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer6[2][5] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer6[2][6] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer6[2][7] = par1IconRegister.registerIcon("supersolarpanel:usp_topsun");
        this.iconBuffer6[2][8] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer6[2][9] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer6[2][10] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer6[2][11] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer6[3][0] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer6[3][1] = par1IconRegister.registerIcon("supersolarpanel:qsp_topsun");
        this.iconBuffer6[3][2] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer6[3][3] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer6[3][4] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer6[3][5] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer6[3][6] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer6[3][7] = par1IconRegister.registerIcon("supersolarpanel:qsp_topsun");
        this.iconBuffer6[3][8] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer6[3][9] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer6[3][10] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer6[3][11] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer7[0][0] = par1IconRegister.registerIcon("supersolarpanel:asp_bottom");
        this.iconBuffer7[0][1] = par1IconRegister.registerIcon("supersolarpanel:asp_toprain");
        this.iconBuffer7[0][2] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer7[0][3] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer7[0][4] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer7[0][5] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer7[0][6] = par1IconRegister.registerIcon("supersolarpanel:asp_bottom");
        this.iconBuffer7[0][7] = par1IconRegister.registerIcon("supersolarpanel:asp_toprain");
        this.iconBuffer7[0][8] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer7[0][9] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer7[0][10] = par1IconRegister.registerIcon("supersolarpanel:asp_side");
        this.iconBuffer7[0][11] = par1IconRegister.registerIcon("superolarpanel:asp_side");
        this.iconBuffer7[1][0] = par1IconRegister.registerIcon("supersolarpanel:hsp_bottom");
        this.iconBuffer7[1][1] = par1IconRegister.registerIcon("supersolarpanel:hsp_toprain");
        this.iconBuffer7[1][2] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer7[1][3] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer7[1][4] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer7[1][5] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer7[1][6] = par1IconRegister.registerIcon("supersolarpanel:hsp_bottom");
        this.iconBuffer7[1][7] = par1IconRegister.registerIcon("supersolarpanel:hsp_toprain");
        this.iconBuffer7[1][8] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer7[1][9] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer7[1][10] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer7[1][11] = par1IconRegister.registerIcon("supersolarpanel:hsp_side");
        this.iconBuffer7[2][0] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer7[2][1] = par1IconRegister.registerIcon("supersolarpanel:usp_toprain");
        this.iconBuffer7[2][2] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer7[2][3] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer7[2][4] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer7[2][5] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer7[2][6] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer7[2][7] = par1IconRegister.registerIcon("supersolarpanel:usp_toprain");
        this.iconBuffer7[2][8] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer7[2][9] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer7[2][10] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer7[2][11] = par1IconRegister.registerIcon("supersolarpanel:usp_side");
        this.iconBuffer7[3][0] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer7[3][1] = par1IconRegister.registerIcon("supersolarpanel:qsp_toprain");
        this.iconBuffer7[3][2] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer7[3][3] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer7[3][4] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer7[3][5] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer7[3][6] = par1IconRegister.registerIcon("supersolarpanel:usp_bottom");
        this.iconBuffer7[3][7] = par1IconRegister.registerIcon("supersolarpanel:qsp_toprain");
        this.iconBuffer7[3][8] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer7[3][9] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer7[3][10] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer7[3][11] = par1IconRegister.registerIcon("supersolarpanel:qsp_side");
        this.iconBuffer[6][0] = par1IconRegister.registerIcon("supersolarpanel:ssp_bottom");
        this.iconBuffer[6][1] = par1IconRegister.registerIcon("supersolarpanel:ssp_top");
        this.iconBuffer[6][2] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");
        this.iconBuffer[6][3] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");
        this.iconBuffer[6][4] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");
        this.iconBuffer[6][5] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");

        this.iconBuffer[4][0] = par1IconRegister.registerIcon("supersolarpanel:spsp_bottom");
        this.iconBuffer[4][1] = par1IconRegister.registerIcon("supersolarpanel:spsp_top");
        this.iconBuffer[4][2] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");
        this.iconBuffer[4][3] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");
        this.iconBuffer[4][4] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");
        this.iconBuffer[4][5] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");

        this.iconBuffer[7][0] = par1IconRegister.registerIcon("supersolarpanel:admsp_bottom");
        this.iconBuffer[7][1] = par1IconRegister.registerIcon("supersolarpanel:admsp_top");
        this.iconBuffer[7][2] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");
        this.iconBuffer[7][3] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");
        this.iconBuffer[7][4] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");
        this.iconBuffer[7][5] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");

        this.iconBuffer[8][0] = par1IconRegister.registerIcon("supersolarpanel:phsp_bottom");
        this.iconBuffer[8][1] = par1IconRegister.registerIcon("supersolarpanel:phsp_top");
        this.iconBuffer[8][2] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");
        this.iconBuffer[8][3] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");
        this.iconBuffer[8][4] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");
        this.iconBuffer[8][5] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");

        this.iconBuffer[9][0] = par1IconRegister.registerIcon("supersolarpanel:nsp_bottom");
        this.iconBuffer[9][1] = par1IconRegister.registerIcon("supersolarpanel:nsp_top");
        this.iconBuffer[9][2] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");
        this.iconBuffer[9][3] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");
        this.iconBuffer[9][4] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");
        this.iconBuffer[9][5] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");

        this.iconBuffer[5][0] = par1IconRegister.registerIcon("supersolarpanel:nsp_bottom");
        this.iconBuffer[5][1] = par1IconRegister.registerIcon("supersolarpanel:psp_top");
        this.iconBuffer[5][2] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");
        this.iconBuffer[5][3] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");
        this.iconBuffer[5][4] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");
        this.iconBuffer[5][5] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");


        this.iconBuffer1[6][0] = par1IconRegister.registerIcon("supersolarpanel:ssp_bottom");
        this.iconBuffer1[6][1] = par1IconRegister.registerIcon("supersolarpanel:ssp_topaer");
        this.iconBuffer1[6][2] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");
        this.iconBuffer1[6][3] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");
        this.iconBuffer1[6][4] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");
        this.iconBuffer1[6][5] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");

        this.iconBuffer1[4][0] = par1IconRegister.registerIcon("supersolarpanel:spsp_bottom");
        this.iconBuffer1[4][1] = par1IconRegister.registerIcon("supersolarpanel:spsp_topaer");
        this.iconBuffer1[4][2] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");
        this.iconBuffer1[4][3] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");
        this.iconBuffer1[4][4] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");
        this.iconBuffer1[4][5] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");

        this.iconBuffer1[7][0] = par1IconRegister.registerIcon("supersolarpanel:admsp_bottom");
        this.iconBuffer1[7][1] = par1IconRegister.registerIcon("supersolarpanel:admsp_topaer");
        this.iconBuffer1[7][2] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");
        this.iconBuffer1[7][3] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");
        this.iconBuffer1[7][4] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");
        this.iconBuffer1[7][5] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");

        this.iconBuffer1[8][0] = par1IconRegister.registerIcon("supersolarpanel:phsp_bottom");
        this.iconBuffer1[8][1] = par1IconRegister.registerIcon("supersolarpanel:phsp_topaer");
        this.iconBuffer1[8][2] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");
        this.iconBuffer1[8][3] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");
        this.iconBuffer1[8][4] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");
        this.iconBuffer1[8][5] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");

        this.iconBuffer1[9][0] = par1IconRegister.registerIcon("supersolarpanel:nsp_bottom");
        this.iconBuffer1[9][1] = par1IconRegister.registerIcon("supersolarpanel:nsp_topaer");
        this.iconBuffer1[9][2] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");
        this.iconBuffer1[9][3] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");
        this.iconBuffer1[9][4] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");
        this.iconBuffer1[9][5] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");

        this.iconBuffer1[5][0] = par1IconRegister.registerIcon("supersolarpanel:nsp_bottom");
        this.iconBuffer1[5][1] = par1IconRegister.registerIcon("supersolarpanel:psp_topaer");
        this.iconBuffer1[5][2] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");
        this.iconBuffer1[5][3] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");
        this.iconBuffer1[5][4] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");
        this.iconBuffer1[5][5] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");
        this.iconBuffer2[6][0] = par1IconRegister.registerIcon("supersolarpanel:ssp_bottom");
        this.iconBuffer2[6][1] = par1IconRegister.registerIcon("supersolarpanel:ssp_topearth");
        this.iconBuffer2[6][2] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");
        this.iconBuffer2[6][3] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");
        this.iconBuffer2[6][4] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");
        this.iconBuffer2[6][5] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");

        this.iconBuffer2[4][0] = par1IconRegister.registerIcon("supersolarpanel:spsp_bottom");
        this.iconBuffer2[4][1] = par1IconRegister.registerIcon("supersolarpanel:spsp_topearth");
        this.iconBuffer2[4][2] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");
        this.iconBuffer2[4][3] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");
        this.iconBuffer2[4][4] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");
        this.iconBuffer2[4][5] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");

        this.iconBuffer2[7][0] = par1IconRegister.registerIcon("supersolarpanel:admsp_bottom");
        this.iconBuffer2[7][1] = par1IconRegister.registerIcon("supersolarpanel:admsp_topearth");
        this.iconBuffer2[7][2] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");
        this.iconBuffer2[7][3] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");
        this.iconBuffer2[7][4] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");
        this.iconBuffer2[7][5] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");

        this.iconBuffer2[8][0] = par1IconRegister.registerIcon("supersolarpanel:phsp_bottom");
        this.iconBuffer2[8][1] = par1IconRegister.registerIcon("supersolarpanel:phsp_topearth");
        this.iconBuffer2[8][2] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");
        this.iconBuffer2[8][3] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");
        this.iconBuffer2[8][4] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");
        this.iconBuffer2[8][5] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");

        this.iconBuffer2[9][0] = par1IconRegister.registerIcon("supersolarpanel:nsp_bottom");
        this.iconBuffer2[9][1] = par1IconRegister.registerIcon("supersolarpanel:nsp_topearth");
        this.iconBuffer2[9][2] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");
        this.iconBuffer2[9][3] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");
        this.iconBuffer2[9][4] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");
        this.iconBuffer2[9][5] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");

        this.iconBuffer2[5][0] = par1IconRegister.registerIcon("supersolarpanel:nsp_bottom");
        this.iconBuffer2[5][1] = par1IconRegister.registerIcon("supersolarpanel:psp_topearth");
        this.iconBuffer2[5][2] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");
        this.iconBuffer2[5][3] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");
        this.iconBuffer2[5][4] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");
        this.iconBuffer2[5][5] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");
        this.iconBuffer3[6][0] = par1IconRegister.registerIcon("supersolarpanel:ssp_bottom");
        this.iconBuffer3[6][1] = par1IconRegister.registerIcon("supersolarpanel:ssp_topnether");
        this.iconBuffer3[6][2] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");
        this.iconBuffer3[6][3] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");
        this.iconBuffer3[6][4] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");
        this.iconBuffer3[6][5] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");

        this.iconBuffer3[4][0] = par1IconRegister.registerIcon("supersolarpanel:spsp_bottom");
        this.iconBuffer3[4][1] = par1IconRegister.registerIcon("supersolarpanel:spsp_topnether");
        this.iconBuffer3[4][2] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");
        this.iconBuffer3[4][3] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");
        this.iconBuffer3[4][4] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");
        this.iconBuffer3[4][5] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");

        this.iconBuffer3[7][0] = par1IconRegister.registerIcon("supersolarpanel:admsp_bottom");
        this.iconBuffer3[7][1] = par1IconRegister.registerIcon("supersolarpanel:admsp_topnether");
        this.iconBuffer3[7][2] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");
        this.iconBuffer3[7][3] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");
        this.iconBuffer3[7][4] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");
        this.iconBuffer3[7][5] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");

        this.iconBuffer3[8][0] = par1IconRegister.registerIcon("supersolarpanel:phsp_bottom");
        this.iconBuffer3[8][1] = par1IconRegister.registerIcon("supersolarpanel:phsp_topnether");
        this.iconBuffer3[8][2] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");
        this.iconBuffer3[8][3] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");
        this.iconBuffer3[8][4] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");
        this.iconBuffer3[8][5] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");

        this.iconBuffer3[9][0] = par1IconRegister.registerIcon("supersolarpanel:nsp_bottom");
        this.iconBuffer3[9][1] = par1IconRegister.registerIcon("supersolarpanel:nsp_topnether");
        this.iconBuffer3[9][2] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");
        this.iconBuffer3[9][3] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");
        this.iconBuffer3[9][4] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");
        this.iconBuffer3[9][5] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");

        this.iconBuffer3[5][0] = par1IconRegister.registerIcon("supersolarpanel:nsp_bottom");
        this.iconBuffer3[5][1] = par1IconRegister.registerIcon("supersolarpanel:psp_topnether");
        this.iconBuffer3[5][2] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");
        this.iconBuffer3[5][3] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");
        this.iconBuffer3[5][4] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");
        this.iconBuffer3[5][5] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");
        this.iconBuffer4[6][0] = par1IconRegister.registerIcon("supersolarpanel:ssp_bottom");
        this.iconBuffer4[6][1] = par1IconRegister.registerIcon("supersolarpanel:ssp_topend");
        this.iconBuffer4[6][2] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");
        this.iconBuffer4[6][3] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");
        this.iconBuffer4[6][4] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");
        this.iconBuffer4[6][5] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");

        this.iconBuffer4[4][0] = par1IconRegister.registerIcon("supersolarpanel:spsp_bottom");
        this.iconBuffer4[4][1] = par1IconRegister.registerIcon("supersolarpanel:spsp_topend");
        this.iconBuffer4[4][2] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");
        this.iconBuffer4[4][3] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");
        this.iconBuffer4[4][4] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");
        this.iconBuffer4[4][5] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");

        this.iconBuffer4[7][0] = par1IconRegister.registerIcon("supersolarpanel:admsp_bottom");
        this.iconBuffer4[7][1] = par1IconRegister.registerIcon("supersolarpanel:admsp_topend");
        this.iconBuffer4[7][2] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");
        this.iconBuffer4[7][3] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");
        this.iconBuffer4[7][4] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");
        this.iconBuffer4[7][5] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");

        this.iconBuffer4[8][0] = par1IconRegister.registerIcon("supersolarpanel:phsp_bottom");
        this.iconBuffer4[8][1] = par1IconRegister.registerIcon("supersolarpanel:phsp_topend");
        this.iconBuffer4[8][2] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");
        this.iconBuffer4[8][3] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");
        this.iconBuffer4[8][4] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");
        this.iconBuffer4[8][5] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");

        this.iconBuffer4[9][0] = par1IconRegister.registerIcon("supersolarpanel:nsp_bottom");
        this.iconBuffer4[9][1] = par1IconRegister.registerIcon("supersolarpanel:nsp_topend");
        this.iconBuffer4[9][2] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");
        this.iconBuffer4[9][3] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");
        this.iconBuffer4[9][4] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");
        this.iconBuffer4[9][5] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");

        this.iconBuffer4[5][0] = par1IconRegister.registerIcon("supersolarpanel:nsp_bottom");
        this.iconBuffer4[5][1] = par1IconRegister.registerIcon("supersolarpanel:psp_topend");
        this.iconBuffer4[5][2] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");
        this.iconBuffer4[5][3] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");
        this.iconBuffer4[5][4] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");
        this.iconBuffer4[5][5] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");
        this.iconBuffer5[6][0] = par1IconRegister.registerIcon("supersolarpanel:ssp_bottom");
        this.iconBuffer5[6][1] = par1IconRegister.registerIcon("supersolarpanel:ssp_topmoon");
        this.iconBuffer5[6][2] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");
        this.iconBuffer5[6][3] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");
        this.iconBuffer5[6][4] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");
        this.iconBuffer5[6][5] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");

        this.iconBuffer5[4][0] = par1IconRegister.registerIcon("supersolarpanel:spsp_bottom");
        this.iconBuffer5[4][1] = par1IconRegister.registerIcon("supersolarpanel:spsp_topmoon");
        this.iconBuffer5[4][2] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");
        this.iconBuffer5[4][3] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");
        this.iconBuffer5[4][4] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");
        this.iconBuffer5[4][5] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");

        this.iconBuffer5[7][0] = par1IconRegister.registerIcon("supersolarpanel:admsp_bottom");
        this.iconBuffer5[7][1] = par1IconRegister.registerIcon("supersolarpanel:admsp_topmoon");
        this.iconBuffer5[7][2] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");
        this.iconBuffer5[7][3] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");
        this.iconBuffer5[7][4] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");
        this.iconBuffer5[7][5] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");

        this.iconBuffer5[8][0] = par1IconRegister.registerIcon("supersolarpanel:phsp_bottom");
        this.iconBuffer5[8][1] = par1IconRegister.registerIcon("supersolarpanel:phsp_topmoon");
        this.iconBuffer5[8][2] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");
        this.iconBuffer5[8][3] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");
        this.iconBuffer5[8][4] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");
        this.iconBuffer5[8][5] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");

        this.iconBuffer5[9][0] = par1IconRegister.registerIcon("supersolarpanel:nsp_bottom");
        this.iconBuffer5[9][1] = par1IconRegister.registerIcon("supersolarpanel:nsp_topmoon");
        this.iconBuffer5[9][2] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");
        this.iconBuffer5[9][3] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");
        this.iconBuffer5[9][4] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");
        this.iconBuffer5[9][5] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");

        this.iconBuffer5[5][0] = par1IconRegister.registerIcon("supersolarpanel:nsp_bottom");
        this.iconBuffer5[5][1] = par1IconRegister.registerIcon("supersolarpanel:psp_topmoon");
        this.iconBuffer5[5][2] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");
        this.iconBuffer5[5][3] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");
        this.iconBuffer5[5][4] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");
        this.iconBuffer5[5][5] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");
        this.iconBuffer6[6][0] = par1IconRegister.registerIcon("supersolarpanel:ssp_bottom");
        this.iconBuffer6[6][1] = par1IconRegister.registerIcon("supersolarpanel:ssp_topsun");
        this.iconBuffer6[6][2] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");
        this.iconBuffer6[6][3] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");
        this.iconBuffer6[6][4] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");
        this.iconBuffer6[6][5] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");

        this.iconBuffer6[4][0] = par1IconRegister.registerIcon("supersolarpanel:spsp_bottom");
        this.iconBuffer6[4][1] = par1IconRegister.registerIcon("supersolarpanel:spsp_topsun");
        this.iconBuffer6[4][2] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");
        this.iconBuffer6[4][3] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");
        this.iconBuffer6[4][4] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");
        this.iconBuffer6[4][5] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");

        this.iconBuffer6[7][0] = par1IconRegister.registerIcon("supersolarpanel:admsp_bottom");
        this.iconBuffer6[7][1] = par1IconRegister.registerIcon("supersolarpanel:admsp_topsun");
        this.iconBuffer6[7][2] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");
        this.iconBuffer6[7][3] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");
        this.iconBuffer6[7][4] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");
        this.iconBuffer6[7][5] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");

        this.iconBuffer6[8][0] = par1IconRegister.registerIcon("supersolarpanel:phsp_bottom");
        this.iconBuffer6[8][1] = par1IconRegister.registerIcon("supersolarpanel:phsp_topsun");
        this.iconBuffer6[8][2] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");
        this.iconBuffer6[8][3] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");
        this.iconBuffer6[8][4] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");
        this.iconBuffer6[8][5] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");

        this.iconBuffer6[9][0] = par1IconRegister.registerIcon("supersolarpanel:nsp_bottom");
        this.iconBuffer6[9][1] = par1IconRegister.registerIcon("supersolarpanel:nsp_topsun");
        this.iconBuffer6[9][2] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");
        this.iconBuffer6[9][3] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");
        this.iconBuffer6[9][4] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");
        this.iconBuffer6[9][5] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");

        this.iconBuffer6[5][0] = par1IconRegister.registerIcon("supersolarpanel:nsp_bottom");
        this.iconBuffer6[5][1] = par1IconRegister.registerIcon("supersolarpanel:psp_topsun");
        this.iconBuffer6[5][2] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");
        this.iconBuffer6[5][3] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");
        this.iconBuffer6[5][4] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");
        this.iconBuffer6[5][5] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");
        this.iconBuffer7[6][0] = par1IconRegister.registerIcon("supersolarpanel:ssp_bottom");
        this.iconBuffer7[6][1] = par1IconRegister.registerIcon("supersolarpanel:ssp_toprain");
        this.iconBuffer7[6][2] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");
        this.iconBuffer7[6][3] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");
        this.iconBuffer7[6][4] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");
        this.iconBuffer7[6][5] = par1IconRegister.registerIcon("supersolarpanel:ssp_side");

        this.iconBuffer7[4][0] = par1IconRegister.registerIcon("supersolarpanel:spsp_bottom");
        this.iconBuffer7[4][1] = par1IconRegister.registerIcon("supersolarpanel:spsp_toprain");
        this.iconBuffer7[4][2] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");
        this.iconBuffer7[4][3] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");
        this.iconBuffer7[4][4] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");
        this.iconBuffer7[4][5] = par1IconRegister.registerIcon("supersolarpanel:spectral_side");

        this.iconBuffer7[7][0] = par1IconRegister.registerIcon("supersolarpanel:admsp_bottom");
        this.iconBuffer7[7][1] = par1IconRegister.registerIcon("supersolarpanel:admsp_toprain");
        this.iconBuffer7[7][2] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");
        this.iconBuffer7[7][3] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");
        this.iconBuffer7[7][4] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");
        this.iconBuffer7[7][5] = par1IconRegister.registerIcon("supersolarpanel:admsp_side");

        this.iconBuffer7[8][0] = par1IconRegister.registerIcon("supersolarpanel:phsp_bottom");
        this.iconBuffer7[8][1] = par1IconRegister.registerIcon("supersolarpanel:phsp_toprain");
        this.iconBuffer7[8][2] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");
        this.iconBuffer7[8][3] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");
        this.iconBuffer7[8][4] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");
        this.iconBuffer7[8][5] = par1IconRegister.registerIcon("supersolarpanel:phsp_side");

        this.iconBuffer7[9][0] = par1IconRegister.registerIcon("supersolarpanel:nsp_bottom");
        this.iconBuffer7[9][1] = par1IconRegister.registerIcon("supersolarpanel:nsp_toprain");
        this.iconBuffer7[9][2] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");
        this.iconBuffer7[9][3] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");
        this.iconBuffer7[9][4] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");
        this.iconBuffer7[9][5] = par1IconRegister.registerIcon("supersolarpanel:nsp_side");

        this.iconBuffer7[5][0] = par1IconRegister.registerIcon("supersolarpanel:nsp_bottom");
        this.iconBuffer7[5][1] = par1IconRegister.registerIcon("supersolarpanel:psp_toprain");
        this.iconBuffer7[5][2] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");
        this.iconBuffer7[5][3] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");
        this.iconBuffer7[5][4] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");
        this.iconBuffer7[5][5] = par1IconRegister.registerIcon("supersolarpanel:psp_bottom");

    }

    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int blockSide) {
        int blockMeta = world.getBlockMetadata(x, y, z);
        TileEntity te = world.getTileEntity(x, y, z);
        TileEntitySolarPanel tile = (TileEntitySolarPanel) world.getTileEntity(x, y, z);
        int facing = (te instanceof TileEntityBase) ? ((TileEntityBase) te).getFacing() : 0;

        int g = tile.solarType;
        if (g == 1)
            return this.iconBuffer1[blockMeta][ClientProxy.sideAndFacingToSpriteOffset[blockSide][facing]];
        if (g == 2)
            return this.iconBuffer2[blockMeta][ClientProxy.sideAndFacingToSpriteOffset[blockSide][facing]];
        if (g == 3)
            return this.iconBuffer3[blockMeta][ClientProxy.sideAndFacingToSpriteOffset[blockSide][facing]];
        if (g == 4)
            return this.iconBuffer4[blockMeta][ClientProxy.sideAndFacingToSpriteOffset[blockSide][facing]];
        if (g == 5)
            return this.iconBuffer5[blockMeta][ClientProxy.sideAndFacingToSpriteOffset[blockSide][facing]];
        if (g == 6)
            return this.iconBuffer6[blockMeta][ClientProxy.sideAndFacingToSpriteOffset[blockSide][facing]];
        if (g == 7)
            return this.iconBuffer7[blockMeta][ClientProxy.sideAndFacingToSpriteOffset[blockSide][facing]];
        if (g == 0)
            return this.iconBuffer[blockMeta][ClientProxy.sideAndFacingToSpriteOffset[blockSide][facing]];

        return null;


    }

    @Override
    public IIcon getIcon(final int blockSide, final int blockMeta) {

        return this.iconBuffer[blockMeta][ClientProxy.sideAndFacingToSpriteOffset[blockSide][3]];
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> dropList = super.getDrops(world, x, y, z, metadata, fortune);
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof IInventory) {
            IInventory iinv = (IInventory) te;
            for (int index = 0; index < iinv.getSizeInventory(); index++) {
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
                if (var7 == null)
                    return;
                double var8 = 0.7D;
                double var10 = world.rand.nextFloat() * var8 + (1.0D - var8) * 0.5D;
                double var12 = world.rand.nextFloat() * var8 + (1.0D - var8) * 0.5D;
                double var14 = world.rand.nextFloat() * var8 + (1.0D - var8) * 0.5D;
                EntityItem var16 = new EntityItem(world, x + var10, y + var12, z + var14, var7);
                var16.delayBeforeCanPickup = 10;
                world.spawnEntityInWorld(var16);
                return;
            }
        }
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.getItemFromBlock(SSPItem.blockSSPSolarPanel);
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z);
    }

    @Override
    public boolean onBlockActivated(final World world, final int i, final int j, final int k, final EntityPlayer player, final int s, final float f1, final float f2, final float f3) {
        if (player.isSneaking()) {
            return false;
        }
        if (world.isRemote) {
            return true;
        }
        final TileEntity tileentity = world.getTileEntity(i, j, k);
        if (tileentity != null) {

            if (world.getTileEntity(i, j, k) instanceof TileEntitySolarPanel) {
                TileEntitySolarPanel tile = (TileEntitySolarPanel) world.getTileEntity(i, j, k);
                for (int m = 0; m < 9; m++) {
                    if (tile.chargeSlots[m] != null && tile.chargeSlots[m].getItem() instanceof module7 && tile.chargeSlots[m].getItemDamage() == 0 && tile.player == player.getDisplayName()) {
                        player.openGui(IUCore.instance, 1, world, i, j, k);


                        break;

                    } else {
                        if (tile.personality == false) {

                            player.openGui(IUCore.instance, 1, world, i, j, k);
                        } else {
                            if (tile.personality && tile.player != player.getDisplayName())
                                player.addChatMessage(new ChatComponentTranslation(String.format("ssp.error")));
                        }
                    }


                }
            }

        }
        return true;
    }


    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
        super.onBlockPlacedBy(world, x, y, z, player, stack);
        int heading = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        TileEntitySolarPanel te = (TileEntitySolarPanel) world.getTileEntity(x, y, z);

        NBTTagCompound nbttagcompound1 = NBTData.getOrCreateNbtData(stack);
        int storage1 = nbttagcompound1.getInteger("storage");
        int storage2 = nbttagcompound1.getInteger("storage2");
        te.storage = storage1;
        te.storage2 = storage2;
        if (player instanceof EntityPlayer)
            te.player = ((EntityPlayer) player).getDisplayName();
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
    public TileEntity createNewTileEntity(final World var1, final int var2) {
        return getBlockEntity(var2);
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(final Item item, final CreativeTabs tab, final List subItems) {
        for (int ix = 0; ix < this.iconBuffer.length; ++ix) {
            ItemStack itemstack = new ItemStack(this, 1, ix);
            NBTTagCompound nbt = NBTData.getOrCreateNbtData(itemstack);
            TileEntitySolarPanel tile = (TileEntitySolarPanel) getBlockEntity(ix);
            nbt.setInteger("genday", tile.genDay);
            subItems.add(new ItemStack(this, 1, ix));
        }
    }
}
