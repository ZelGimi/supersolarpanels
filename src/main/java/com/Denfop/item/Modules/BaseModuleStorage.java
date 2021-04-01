package com.Denfop.item.Modules;



import java.util.List;

import com.Denfop.IUCore;
import com.Denfop.api.module.IModulStorage;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class BaseModuleStorage extends Item implements IModulStorage {
	private static int percent;
	public BaseModuleStorage(int percent) {
		this.setCreativeTab((CreativeTabs) IUCore.tabssp1);
		this.percent = percent;
	}

	public int getItemStackLimit() {
		return this.maxStackSize;
	}
	public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean b) {
		info.add(StatCollector.translateToLocal("ssp.module3") + " " + percent + "% "
				+ StatCollector.translateToLocal("ssp.module"));
		info.add(StatCollector.translateToLocal("ssp.modules"));

	}
	public void getSubItems(final Item item, final CreativeTabs tabs, final List itemList) {
		for (int meta = 0; meta <= 0; ++meta) {
			final ItemStack stack = new ItemStack((Item) this, 1, meta);
			
			itemList.add(stack);
		}
	}

	public static int getpercent() {
		
		return percent;
	}

}
