package com.Denfop.item.Modules;

import com.Denfop.IUCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

import java.util.ArrayList;
import java.util.List;

public class module7 extends Item {
    private final List<String> itemNames;
    private final IIcon[] IIconsList;
    private int itemsCount;

    public module7() {
        this.itemNames = new ArrayList<String>();
        this.IIconsList = new IIcon[9];

        this.setHasSubtypes(true);
        this.setCreativeTab(IUCore.tabssp1);
        this.setMaxStackSize(64);
        this.addItemsNames();
    }

    public String getUnlocalizedName(final ItemStack stack) {
        return this.itemNames.get(stack.getItemDamage());
    }

    public IIcon getIconFromDamage(final int par1) {
        return this.IIconsList[par1];
    }

    public void addItemsNames() {
        this.itemNames.add("module71");
        this.itemNames.add("module72");
        this.itemNames.add("module73");
        this.itemNames.add("module74");
        this.itemNames.add("module75");
        this.itemNames.add("module76");
        this.itemNames.add("module77");
        this.itemNames.add("module78");
        this.itemNames.add("module79");
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister IIconRegister) {
        this.IIconsList[0] = IIconRegister.registerIcon("supersolarpanel:module71");
        this.IIconsList[1] = IIconRegister.registerIcon("supersolarpanel:module72");
        this.IIconsList[2] = IIconRegister.registerIcon("supersolarpanel:module73");
        this.IIconsList[3] = IIconRegister.registerIcon("supersolarpanel:module74");
        this.IIconsList[4] = IIconRegister.registerIcon("supersolarpanel:module75");
        this.IIconsList[5] = IIconRegister.registerIcon("supersolarpanel:module76");
        this.IIconsList[6] = IIconRegister.registerIcon("supersolarpanel:module77");
        this.IIconsList[7] = IIconRegister.registerIcon("supersolarpanel:module78");
        this.IIconsList[8] = IIconRegister.registerIcon("supersolarpanel:module79");
    }

    public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean b) {
        NBTTagCompound nbttagcompound;
        int meta = itemStack.getItemDamage();
        switch (meta) {
            case 0:
                info.add(StatCollector.translateToLocal("ssp.modules"));
                info.add(StatCollector.translateToLocal("personality"));
                info.add(StatCollector.translateToLocal("personality1"));


                break;
            case 1:
                info.add(StatCollector.translateToLocal("ssp.modules"));
                info.add(StatCollector.translateToLocal("transformator"));
                break;
            case 2:
                info.add(StatCollector.translateToLocal("ssp.modules"));
                info.add(StatCollector.translateToLocal("transformator1"));
                break;


            case 5:

            case 6:

            case 7:

            case 8:
                info.add(StatCollector.translateToLocal("storagemodul"));
                info.add(StatCollector.translateToLocal("storagemodul1"));
                break;


        }
    }

    public void getSubItems(final Item item, final CreativeTabs tabs, final List itemList) {
        for (int meta = 0; meta <= this.itemNames.size() - 1; ++meta) {
            final ItemStack stack = new ItemStack(this, 1, meta);
            itemList.add(stack);
        }
    }

    public void getSubItems(final int par1, final CreativeTabs par2CreativeTabs, final List par3List) {
        for (int i = 0; i <= this.IIconsList.length - 1; ++i) {
            par3List.add(new ItemStack(this, 1, i));
        }
    }
}
