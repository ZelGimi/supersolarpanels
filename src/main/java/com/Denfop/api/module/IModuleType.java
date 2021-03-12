package com.Denfop.api.module;

import com.Denfop.utils.NBTData;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import java.util.List;

public interface IModuleType {
    static void setData(ItemStack stack, String type) {
        NBTTagCompound nbt = NBTData.getOrCreateNbtData(stack);


        nbt.setString("type", type);

    }

    static List<String> getData(ItemStack stack) {
        NBTTagCompound nbt = NBTData.getOrCreateNbtData(stack);
        List<String> list = new ArrayList<String>();

        list.add(nbt.getString("type"));

        return list;

    }
}
