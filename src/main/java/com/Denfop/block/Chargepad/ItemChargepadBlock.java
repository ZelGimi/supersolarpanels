package com.Denfop.block.Chargepad;

import com.Denfop.Config;
import com.Denfop.SSPItem;
import com.Denfop.item.base.ItemBlockIC2;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.util.StackUtil;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemChargepadBlock extends ItemBlockIC2 {
    public ItemChargepadBlock(Block block) {
        super(block);
        setMaxDamage(0);
        setHasSubtypes(true);
        setMaxStackSize(1);
    }

    public int getMetadata(int i) {
        return i;
    }

    public String getUnlocalizedName(ItemStack itemstack) {
        int meta = itemstack.getItemDamage();
        switch (meta) {
            case 0:
                return "ssp.blockChargepadMFE";
            case 1:
                return "ssp.blockChargepadMFES";

        }
        return null;
    }

    public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean b) {
        NBTTagCompound nbttagcompound;
        int meta = itemStack.getItemDamage();
        switch (meta) {
            case 0:
                info.add(StatCollector.translateToLocal("ic2.item.tooltip.Output") + " " + Config.enegry1 + " EU/t " + StatCollector.translateToLocal("ic2.item.tooltip.Capacity") + Config.storage1 + " EU");
                break;
            case 1:
                info.add(StatCollector.translateToLocal("ic2.item.tooltip.Output") + " " + Config.enegry2 + " EU/t " + StatCollector.translateToLocal("ic2.item.tooltip.Capacity") + Config.storage2 + " EU");
                break;
        }
        switch (meta) {
            case 0:
            case 1:
            case 2:
            case 3:
                nbttagcompound = StackUtil.getOrCreateNbtData(itemStack);
                info.add(StatCollector.translateToLocal("ic2.item.tooltip.Store") + " " + nbttagcompound.getInteger("energy") + " EU");
                break;
        }
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List itemList) {
        itemList.add(SSPItem.ChargepadmfeUnit);
        itemList.add(SSPItem.ChargepadmfsUnit);
        ItemStack itemStack = new ItemStack(SSPItem.ChargepadmfeUnit.getItem(), 1);
        NBTTagCompound nbttagcompound = StackUtil.getOrCreateNbtData(itemStack);
        itemStack.setItemDamage(0);
        nbttagcompound = StackUtil.getOrCreateNbtData(itemStack);
        nbttagcompound.setDouble("energy", Config.storage1);
        itemList.add(itemStack);
        itemStack = new ItemStack(SSPItem.ChargepadmfsUnit.getItem(), 1);
        itemStack.setItemDamage(1);
        nbttagcompound = StackUtil.getOrCreateNbtData(itemStack);
        nbttagcompound.setDouble("energy", Config.storage2);
        itemList.add(itemStack);
    }


} 
  
  
 
