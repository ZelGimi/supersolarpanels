package com.Denfop.tab;

import com.Denfop.SSPItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabSSP extends CreativeTabs {
    public CreativeTabSSP() {
        super("sspblocks");
    }

    public Item getTabIconItem() {
        return Item.getItemFromBlock(SSPItem.blockSSPSolarPanel);
    }
}
