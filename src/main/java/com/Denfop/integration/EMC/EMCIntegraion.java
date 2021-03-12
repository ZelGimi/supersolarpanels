package com.Denfop.integration.EMC;

import com.Denfop.SSPItem;
import net.minecraft.item.ItemStack;

public class EMCIntegraion {

    public static void init() {
        emc(new ItemStack(SSPItem.chromium_ingot), 100);
        emc(SSPItem.ingotIridium, 150);
        emc(new ItemStack(SSPItem.wolfram_ingot), 80);

    }

    public static void emc(ItemStack stack, double amount) {
        EMCHelper.setEMC(stack, amount);
    }
}
