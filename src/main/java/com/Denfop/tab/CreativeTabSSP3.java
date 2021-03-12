package com.Denfop.tab;

import com.Denfop.SSPItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabSSP3 extends CreativeTabs {
    public CreativeTabSSP3() {
        super("sspitems");
    }

    public Item getTabIconItem() {
        return SSPItem.QuantumItems5;
    }
}
