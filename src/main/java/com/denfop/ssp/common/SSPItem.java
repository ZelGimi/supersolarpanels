package com.denfop.ssp.common;

import com.denfop.ssp.SuperSolarPanels;

import net.minecraft.item.Item;

public class SSPItem extends Item {
    public SSPItem(String name) {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        setCreativeTab(SuperSolarPanels.SSPTab);
    } }  