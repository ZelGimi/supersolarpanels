package com.Denfop.item.Modules;

import com.Denfop.IUCore;
import com.Denfop.utils.NBTData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemWirelessModule extends Item {

    public ItemWirelessModule() {
        setCreativeTab(IUCore.tabssp);
        this.setUnlocalizedName("WirelessModule");
        this.setTextureName("supersolarpanel:wirelessmodule");
        this.setMaxStackSize(64);
        this.setCreativeTab(IUCore.tabssp1);


    }

    public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean b) {
        NBTTagCompound nbttagcompound = NBTData.getOrCreateNbtData(itemStack);
        info.add(StatCollector.translateToLocal("ssp.modules"));
        info.add(StatCollector.translateToLocal("wirelles"));
        info.add(StatCollector.translateToLocal("ssp.Name") + ": " + nbttagcompound.getString("Name"));
        info.add(StatCollector.translateToLocal("ssp.World") + ": " + nbttagcompound.getString("World"));

        info.add(StatCollector.translateToLocal("ssp.tier") + ": " + nbttagcompound.getInteger("tier"));
        info.add(StatCollector.translateToLocal("ssp.Xcoord") + ": " + nbttagcompound.getInteger("Xcoord"));
        info.add(StatCollector.translateToLocal("ssp.Ycoord") + ": " + nbttagcompound.getInteger("Ycoord"));
        info.add(StatCollector.translateToLocal("ssp.Zcoord") + ": " + nbttagcompound.getInteger("Zcoord"));

    }


}
