package com.Denfop.item.base;

import com.Denfop.Config;
import com.Denfop.IUCore;
import com.Denfop.block.Base.BlockElectric;
import com.Denfop.utils.NBTData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemElectricBlock extends ItemBlock {
    private final Block Block;

    public ItemElectricBlock(Block block) {
        super(block);
        this.Block = block;
        setMaxDamage(0);
        setHasSubtypes(true);
        setMaxStackSize(1);
        setCreativeTab(IUCore.tabssp);
    }

    @Override
    public int getMetadata(int i) {
        return i;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        int meta = itemstack.getItemDamage();
        switch (meta) {
            case 0:
                return "ssp.blockMFE";
            case 1:
                return "ssp.blockMFSU";
            case 2:
                return "ssp.blockBatBox";
            case 3:
                return "ssp.blockMFE1";
            case 4:
                return "ssp.blockMFSU1";
            case 5:
                return "ssp.blockCESU";
        }
        return null;
    }

    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack stack) {
        if (this.Block instanceof BlockElectric)
            return ((BlockElectric) this.Block).getRarity(stack);
        return super.getRarity(stack);
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean b) {
        NBTTagCompound nbttagcompound;
        int meta = itemStack.getItemDamage();
        switch (meta) {
            case 0:
                info.add(StatCollector.translateToLocal("ic2.item.tooltip.Output") + " " + Config.enegry1 + " EU/t ");
                info.add(StatCollector.translateToLocal("ic2.item.tooltip.Capacity") + " " + Config.storage1 + " EU ");
                info.add(StatCollector.translateToLocal("ssp.tier") + Config.tier3_MFSU);

                break;
            case 1:
                info.add(StatCollector.translateToLocal("ic2.item.tooltip.Output") + " " + Config.enegry2 + " EU/t ");
                info.add(StatCollector.translateToLocal("ic2.item.tooltip.Capacity") + " " + Config.storage2 + " EU ");
                info.add(StatCollector.translateToLocal("ssp.tier") + Config.tier2_MFSU);
                break;
        }
        switch (meta) {
            case 0:
            case 1:
                nbttagcompound = NBTData.getOrCreateNbtData(itemStack);
                info.add(StatCollector.translateToLocal("ic2.item.tooltip.Store") + " " + nbttagcompound.getInteger("energy") + " EU");
                info.add(StatCollector.translateToLocal("ic2.item.tooltip.Store") + " " + nbttagcompound.getInteger("energy2") + " RF");
                break;
        }
    }

    @Override
    public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List itemList) {
        itemList.add(new ItemStack(item, 1, 0));
        itemList.add(new ItemStack(item, 1, 1));
        itemList.add(new ItemStack(item, 1, 2));
        itemList.add(new ItemStack(item, 1, 3));
        itemList.add(new ItemStack(item, 1, 4));
        itemList.add(new ItemStack(item, 1, 5));
        ItemStack itemStack = new ItemStack(item, 1, 0);
        NBTTagCompound nbttagcompound = NBTData.getOrCreateNbtData(itemStack);
        itemStack.setItemDamage(0);
        nbttagcompound = NBTData.getOrCreateNbtData(itemStack);
        nbttagcompound.setDouble("energy", Config.storage1);
        nbttagcompound.setDouble("energy2", Config.storage1);
        itemList.add(itemStack);
        itemStack = new ItemStack(item, 1, 1);
        itemStack.setItemDamage(1);
        nbttagcompound = NBTData.getOrCreateNbtData(itemStack);
        nbttagcompound.setDouble("energy", Config.storage2);
        nbttagcompound.setDouble("energy2", Config.storage2);
        itemList.add(itemStack);
        itemStack = new ItemStack(item, 1, 2);
        itemStack.setItemDamage(2);
        nbttagcompound = NBTData.getOrCreateNbtData(itemStack);
        nbttagcompound.setDouble("energy", 40000);
        nbttagcompound.setDouble("energy2", 40000);
        itemList.add(itemStack);
        itemStack = new ItemStack(item, 1, 5);
        itemStack.setItemDamage(5);
        nbttagcompound = NBTData.getOrCreateNbtData(itemStack);
        nbttagcompound.setDouble("energy", 300000);
        nbttagcompound.setDouble("energy2", 300000);
        itemList.add(itemStack);
        itemStack = new ItemStack(item, 1, 3);
        itemStack.setItemDamage(3);
        nbttagcompound = NBTData.getOrCreateNbtData(itemStack);
        nbttagcompound.setDouble("energy", 4000000);
        nbttagcompound.setDouble("energy2", 4000000);
        itemList.add(itemStack);
        itemStack = new ItemStack(item, 1, 4);
        itemStack.setItemDamage(4);
        nbttagcompound = NBTData.getOrCreateNbtData(itemStack);
        nbttagcompound.setDouble("energy", 4.0E7D);
        nbttagcompound.setDouble("energy2", 4.0E7D);
        itemList.add(itemStack);
        //
        //

    }


}
