package com.Denfop.integration.DE;

import com.Denfop.IUCore;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SSPDEItem extends Item {

    public SSPDEItem() {
        this.setCreativeTab(IUCore.tabssp3);
    }

    public int getItemStackLimit() {
        return this.maxStackSize;
    }


    public boolean hasEffect(ItemStack par1ItemStack, int pass) {
        return true;
    }
}

