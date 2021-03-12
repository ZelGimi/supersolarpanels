package com.Denfop.events;


import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.item.ItemTossEvent;

import java.util.HashMap;

public class EventHandlerEntity {
    public HashMap<Integer, Float> prevStep = new HashMap<Integer, Float>();

    @SubscribeEvent
    public void droppedItem(ItemTossEvent event) {
        NBTTagCompound itemData = event.entityItem.getEntityData();
        itemData.setString("thrower", event.player.getCommandSenderName());
    }
}
