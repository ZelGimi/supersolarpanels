package com.Denfop.tab;

import com.Denfop.SSPItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabSSP2 extends CreativeTabs {
    public CreativeTabSSP2() {
        super("ssptools");
    }

    public Item getTabIconItem() {
        return SSPItem.quantumHelmet;
    }
}
