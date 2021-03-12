package com.Denfop.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NBTData {
    public static NBTTagCompound getOrCreateNbtData(final ItemStack itemstack) {
        NBTTagCompound nbttagcompound = itemstack.getTagCompound();
        if (nbttagcompound == null) {
            nbttagcompound = new NBTTagCompound();
            itemstack.setTagCompound(nbttagcompound);
            nbttagcompound.setInteger("charge", 0);
            nbttagcompound.setInteger("Fly", 0);
            nbttagcompound.setInteger("solarType", 0);
            nbttagcompound.setInteger("energy", 0);
            nbttagcompound.setInteger("energy2", 0);
            nbttagcompound.setBoolean("isFlyActive", false);
            nbttagcompound.setString("World", "");
            nbttagcompound.setInteger("World1", 0);
            nbttagcompound.setInteger("Xcoord", 0);
            nbttagcompound.setInteger("Ycoord", 0);
            nbttagcompound.setInteger("Zcoord", 0);
            nbttagcompound.setInteger("tier", 0);
            nbttagcompound.setString("Name", "");


        }
        return nbttagcompound;
    }

    public static NBTTagCompound getOrCreateNbtData1(final EntityPlayer player) {
        NBTTagCompound nbttagcompound = player.getEntityData();

        if (nbttagcompound == null) {
            nbttagcompound = new NBTTagCompound();
            nbttagcompound.setBoolean("isFlyActive", false);
            nbttagcompound.setBoolean("isNightVision", false);
            nbttagcompound.setBoolean("stepHeight", false);
        }
        return nbttagcompound;
    }
}
