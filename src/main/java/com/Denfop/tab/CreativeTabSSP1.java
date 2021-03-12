package com.Denfop.tab;

import com.Denfop.SSPItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabSSP1 extends CreativeTabs {
    public CreativeTabSSP1() {
        super("sspmodules");
    }

    public Item getTabIconItem() {
        return SSPItem.module1;
    }
}
