package com.Denfop.item.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;

import com.Denfop.SuperSolarPanels;
import com.Denfop.block.Base.BlockElectric;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
public class ItemElectricBlock extends ItemBlock {
  private Block Block;

public ItemElectricBlock(Block block) {
    super(block);
    this.Block = block;
    setMaxDamage(0);
    setHasSubtypes(true);
    setMaxStackSize(1);
    setCreativeTab(SuperSolarPanels.tabssp);
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
    } 
    return null;
  }
  
  @SideOnly(Side.CLIENT)
  public EnumRarity getRarity(ItemStack stack) {
    if (this.Block instanceof BlockElectric)
      return ((BlockElectric)this.Block).getRarity(stack); 
    return super.getRarity(stack);
  }
  @Override
  public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean b) {
    NBTTagCompound nbttagcompound;
    int meta = itemStack.getItemDamage();
    switch (meta) {
      case 0:
        info.add(StatCollector.translateToLocal("ic2.item.tooltip.Output") + " " + SuperSolarPanels.enegry1  + " EU/t " );
        info.add(StatCollector.translateToLocal("ic2.item.tooltip.Capacity") + " " + SuperSolarPanels.storage1  + " EU ");
        info.add(StatCollector.translateToLocal("ssp.tier") + SuperSolarPanels.tier1);
        
        break;
      case 1:
    	   info.add(StatCollector.translateToLocal("ic2.item.tooltip.Output") + " " + SuperSolarPanels.enegry2  + " EU/t " );
           info.add(StatCollector.translateToLocal("ic2.item.tooltip.Capacity") + " " + SuperSolarPanels.storage2  + " EU ");
           info.add(StatCollector.translateToLocal("ssp.tier") + SuperSolarPanels.tier2);
         break;
    } 
    switch (meta) {
      case 0:
      case 1:
        nbttagcompound = SuperSolarPanels.getOrCreateNbtData(itemStack);
        info.add(StatCollector.translateToLocal("ic2.item.tooltip.Store") + " " + nbttagcompound.getInteger("energy") + " EU");
        info.add(StatCollector.translateToLocal("ic2.item.tooltip.Store") + " " + nbttagcompound.getInteger("energy2") + " RF");
        break;
    } 
  }
  
  @Override
  public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List itemList) {
    itemList.add(new ItemStack(item, 1, 0));
    itemList.add(new ItemStack(item, 1, 1));
    ItemStack  itemStack = new ItemStack(item, 1, 0);
    NBTTagCompound nbttagcompound = SuperSolarPanels.getOrCreateNbtData(itemStack);
    itemStack.setItemDamage(0);
    nbttagcompound = SuperSolarPanels.getOrCreateNbtData(itemStack);
    nbttagcompound.setDouble("energy",  SuperSolarPanels.storage1);
    nbttagcompound.setDouble("energy2",  SuperSolarPanels.storage1);
    itemList.add(itemStack);
    itemStack = new ItemStack(item, 1, 1);
    itemStack.setItemDamage(1);
    nbttagcompound = SuperSolarPanels.getOrCreateNbtData(itemStack);
    nbttagcompound.setDouble("energy",  SuperSolarPanels.storage2);
    nbttagcompound.setDouble("energy2",  SuperSolarPanels.storage2);
    itemList.add(itemStack);
  }

  
}