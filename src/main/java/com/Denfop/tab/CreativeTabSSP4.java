package com.Denfop.tab;

import com.Denfop.SSPItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabSSP4 extends CreativeTabs {
    public CreativeTabSSP4() {
        super("sspores");
    }

    public Item getTabIconItem() {
        return Item.getItemFromBlock(SSPItem.toriyore);
    }
}
