package com.Denfop.integration.EMC;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class EMCHelper {

    public static void setEMC(ItemStack stack, double amount) {
        NBTTagCompound nbttagcompound = stack.getTagCompound();
        nbttagcompound.setDouble("StoredEMC", amount);
    }
}
