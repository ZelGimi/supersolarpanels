package com.Denfop.item.base;

import com.Denfop.IUCore;
import com.Denfop.SSPItem;
import com.Denfop.utils.NBTData;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

public class SSPItemBase extends Item {

    public SSPItemBase() {
        this.setCreativeTab(IUCore.tabssp3);
    }

    public int getItemStackLimit() {
        return this.maxStackSize;
    }

    public void getSubItems(final Item item, final CreativeTabs tabs, final List itemList) {
        for (int meta = 0; meta <= 0; ++meta) {
            final ItemStack stack = new ItemStack(this, 1, 0);
            NBTTagCompound nbttagcompound = NBTData.getOrCreateNbtData(stack);
            if (stack.getItem() == SSPItem.magnesium_ingot)
                nbttagcompound.setDouble("StoredEMC", 100);
            if (stack.getItem() == SSPItem.wolfram_ingot)
                nbttagcompound.setDouble("StoredEMC", 80);
            if (stack.getItem() == OreDictionary.getOres("ingotIridium").get(0).getItem())
                nbttagcompound.setDouble("StoredEMC", 280);
            if (stack.getItem() == SSPItem.spinel_ingot)
                nbttagcompound.setDouble("StoredEMC", 120);
            if (stack.getItem() == SSPItem.mikhail_ingot)
                nbttagcompound.setDouble("StoredEMC", 90);
            if (stack.getItem() == SSPItem.chromium_ingot)
                nbttagcompound.setDouble("StoredEMC", 110);
            itemList.add(stack);
        }
    }


}
