package com.Denfop.api.module;

import com.Denfop.utils.NBTData;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import java.util.List;

public interface IModulStorage {
    static void setData(ItemStack stack, int storage) {
        NBTTagCompound nbt = NBTData.getOrCreateNbtData(stack);


        nbt.setInteger("percentstorage", storage);

    }

    static List<Integer> getData(ItemStack stack) {
        NBTTagCompound nbt = NBTData.getOrCreateNbtData(stack);
        List<Integer> list = new ArrayList<Integer>();

        list.add(nbt.getInteger("percentstorage"));

        return list;

    }
}
