package com.Denfop.item.Modules;

import com.Denfop.Config;
import com.Denfop.IUCore;
import com.Denfop.api.module.IModulGenNight;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

public class module2 extends Item implements IModulGenNight {
    public module2() {
        this.setCreativeTab(IUCore.tabssp1);
    }

    public int getItemStackLimit() {
        return this.maxStackSize;
    }

    public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean b) {
        info.add(StatCollector.translateToLocal("ssp.module2") + " +" + IModulGenNight.getData(itemStack).get(0) + "% " + StatCollector.translateToLocal("ssp.module"));
        info.add(StatCollector.translateToLocal("ssp.modules"));

    }

    public void getSubItems(final Item item, final CreativeTabs tabs, final List itemList) {
        for (int meta = 0; meta <= 0; ++meta) {
            final ItemStack stack = new ItemStack(this, 1, meta);
            IModulGenNight.setData(stack, Config.percent_night);
            itemList.add(stack);
        }
    }
}
