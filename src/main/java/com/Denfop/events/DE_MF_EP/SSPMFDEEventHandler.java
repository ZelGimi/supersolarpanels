package com.Denfop.events.DE_MF_EP;

import com.Denfop.SSPItem;
import com.Denfop.utils.NBTData;
import com.aesireanempire.eplus.inventory.ContainerEnchantTable;
import com.brandon3055.draconicevolution.common.container.ContainerDissEnchanter;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import ic2.core.Ic2Items;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingEvent;
import powercrystals.minefactoryreloaded.gui.container.ContainerAutoDisenchanter;
import powercrystals.minefactoryreloaded.gui.container.ContainerAutoEnchanter;

import java.util.Map;

public class SSPMFDEEventHandler {


    @SubscribeEvent
    public void onPlayerInteract(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving == null || !(event.entityLiving instanceof EntityPlayer)) return;

        EntityPlayer player = (EntityPlayer) event.entity;
        //TODO start for
        for (int i = 0; i < player.inventory.mainInventory.length; i++) {
            //  TODO start Check inventory
            if (player.inventory.mainInventory[i] != null && (player.inventory.mainInventory[i].getItem() == SSPItem.ultDDrill || player.inventory.mainInventory[i].getItem() == Ic2Items.iridiumDrill.getItem())) {
                ItemStack input = player.inventory.mainInventory[i];
                Map<Integer, Integer> map = null;
                ItemStack input1 = new ItemStack(SSPItem.ultDDrill);
                NBTTagCompound nbtData = NBTData.getOrCreateNbtData(input);
                if ((input.getItem() == SSPItem.ultDDrill || input.getItem() == Ic2Items.iridiumDrill.getItem())) {

                    if (EnchantmentHelper.getEnchantments(input) != null) {
                        map = EnchantmentHelper.getEnchantments(input);
                        int id = 0;
                        int lvl = 0;
                        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                            id = entry.getKey();
                            lvl = entry.getValue();
                        }
                        if (id != 0)
                            nbtData.setInteger("ID", id);
                        if (lvl != 0)
                            nbtData.setInteger("Level", lvl);
                        //		   map.put(arg0, arg1)

                    }

                }
                Map<Integer, Integer> map1 = null;
                if (map != null) {
                    map1 = map;
                }
                if (player.openContainer != null && player.openContainer instanceof ContainerAutoDisenchanter) {
                    if (map1 != null) {
                        map1.clear();
                        EnchantmentHelper.setEnchantments(map1, input);

                    }
                    if (player.openContainer.getSlot(2).getStack() != null) {
                        ItemStack input2 = player.openContainer.getSlot(2).getStack();
                        nbtData = NBTData.getOrCreateNbtData(input2);
                        if ((input2.getItem() == SSPItem.ultDDrill || input2.getItem() == Ic2Items.iridiumDrill.getItem())) {

                            if (EnchantmentHelper.getEnchantments(input2) != null) {
                                map = EnchantmentHelper.getEnchantments(input2);
                                int id = 0;
                                int lvl = 0;

                                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                                    id = entry.getKey();
                                    lvl = entry.getValue();
                                }
                                if (id != 0)
                                    nbtData.setInteger("ID", id);
                                if (lvl != 0)
                                    nbtData.setInteger("Level", lvl);
                                //		   map.put(arg0, arg1)
                                if (map != null) {
                                    map1 = map;
                                }
                                map1.clear();
                                EnchantmentHelper.setEnchantments(map1, input2);
                            }

                        }

                    }
                } else if (player.openContainer != null && player.openContainer instanceof ContainerAutoEnchanter) {
                    if (map1 != null) {
                        map1.clear();
                        EnchantmentHelper.setEnchantments(map1, input);

                    }
                } else if (player.openContainer != null && player.openContainer instanceof ContainerDissEnchanter) {
                    if (map1 != null) {
                        map1.clear();
                        EnchantmentHelper.setEnchantments(map1, input);

                    }
                } else if (player.openContainer != null && player.openContainer instanceof ContainerEnchantTable) {
                    if (map1 != null) {
                        map1.clear();
                        EnchantmentHelper.setEnchantments(map1, input);

                    }
                } else {
                    if (nbtData.getInteger("ID") != 0 && nbtData.getInteger("Level") != 0) {
                        map.put(nbtData.getInteger("ID"), nbtData.getInteger("Level"));
                        EnchantmentHelper.setEnchantments(map, input);
                    }
                }
            }

            //  TODO end Check inventory

            //TODO end for
        }
    }

}
