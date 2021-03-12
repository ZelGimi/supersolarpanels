package com.Denfop.api.module;

import com.Denfop.utils.NBTData;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import java.util.List;

public interface IModulGenDay {
    static void setData(ItemStack stack, int day) {
        NBTTagCompound nbt = NBTData.getOrCreateNbtData(stack);


        nbt.setInteger("percentday", day);

    }

    static List<Integer> getData(ItemStack stack) {
        NBTTagCompound nbt = NBTData.getOrCreateNbtData(stack);
        List<Integer> list = new ArrayList<Integer>();

        list.add(nbt.getInteger("percentday"));

        return list;

    }
}
