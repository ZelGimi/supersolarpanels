package com.Denfop.item.Modules;

import com.Denfop.Config;
import com.Denfop.IUCore;
import com.Denfop.api.module.IModulStorage;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

public class module3 extends Item implements IModulStorage {
    public module3() {
        this.setCreativeTab(IUCore.tabssp1);
    }

    public int getItemStackLimit() {
        return this.maxStackSize;
    }

    public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean b) {
        info.add(StatCollector.translateToLocal("ssp.module3") + " " + IModulStorage.getData(itemStack).get(0) + "% " + StatCollector.translateToLocal("ssp.module"));
        info.add(StatCollector.translateToLocal("ssp.modules"));

    }

    public void getSubItems(final Item item, final CreativeTabs tabs, final List itemList) {
        for (int meta = 0; meta <= 0; ++meta) {
            final ItemStack stack = new ItemStack(this, 1, meta);
            IModulStorage.setData(stack, Config.percent_storage);
            itemList.add(stack);
        }
    }
}
