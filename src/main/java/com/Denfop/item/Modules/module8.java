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

public class module8 extends Item {
    private final List<String> itemNames;
    private final IIcon[] IIconsList;
    private int itemsCount;

    public module8() {
        this.itemNames = new ArrayList<String>();
        this.IIconsList = new IIcon[6];

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
        this.itemNames.add("module81");
        this.itemNames.add("module82");
        this.itemNames.add("module83");
        this.itemNames.add("module84");
        this.itemNames.add("module85");
        this.itemNames.add("module86");
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister IIconRegister) {
        this.IIconsList[0] = IIconRegister.registerIcon("supersolarpanel:per");
        this.IIconsList[1] = IIconRegister.registerIcon("supersolarpanel:ef");
        this.IIconsList[2] = IIconRegister.registerIcon("supersolarpanel:ef2");
        this.IIconsList[3] = IIconRegister.registerIcon("supersolarpanel:ef3");
        this.IIconsList[4] = IIconRegister.registerIcon("supersolarpanel:ef4");
        this.IIconsList[5] = IIconRegister.registerIcon("supersolarpanel:ef5");
    }

    public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean b) {
        NBTTagCompound nbttagcompound;
        int meta = itemStack.getItemDamage();
        switch (meta) {
            case 0:
                info.add(StatCollector.translateToLocal("ssp.quarry"));
                info.add(StatCollector.translateToLocal("ssp.quarry1"));
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                info.add(StatCollector.translateToLocal("ssp.quarry"));
                info.add(StatCollector.translateToLocal("ssp.quarry2"));
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
