package com.denfop.ssp.tiles;

import com.denfop.ssp.items.SSPItems;
import com.denfop.ssp.items.resource.CraftingThings;

import ic2.api.energy.tile.IChargingSlot;
import ic2.api.item.ElectricItem;
import ic2.core.block.TileEntityInventory;
import ic2.core.block.invslot.InvSlot;
import net.minecraft.item.ItemStack;

public class InvSlotMultiCharge extends InvSlot implements IChargingSlot {
	public final int tier;

	public InvSlotMultiCharge(final TileEntityInventory base, final int tier, final int slotNumbers, final InvSlot.Access access) {
		super(base, "charge", access, slotNumbers, InvSlot.InvSide.TOP);
		this.tier = tier;
	}

	public boolean accepts(final ItemStack stack) {
		return ElectricItem.manager.charge(stack, Double.POSITIVE_INFINITY, this.tier, false, true) > 0.0 
				|| stack.getItem() == SSPItems.module1.instance
				|| stack.getItem() == SSPItems.module2.instance
				|| stack.getItem() == SSPItems.module3.instance;
	}
	public boolean module_generate_day() {
		boolean module = false;
		for (final ItemStack stack : this) {
			if (stack == null)
				continue;
			if(stack.getItem() == SSPItems.module1.instance) {
				module = true;
			
		}}
		return module;
	}
	public boolean module_generate_night() {
		boolean module = false;
		for (final ItemStack stack : this) {
			if (stack == null)
				continue;
			if(stack.getItem() == SSPItems.module2.instance) {
				module = true;
			
		}}
		return module;
	}
	public boolean module_storage() {
		boolean module = false;
		for (final ItemStack stack : this) {
			if (stack == null)
				continue;
			if(stack.getItem() == SSPItems.module3.instance) {
				module = true;
			
		}}
		return module;
	}
	
	public int GenDay() {
		int GenDay = 0;
		for (final ItemStack stack : this) {
			if (stack == null)
				continue;
			if(stack.getItem() == SSPItems.module1.instance) {
			 GenDay++;
			
		}}
		
		return GenDay;
		
	}
	public int storage() {
		int storage = 0;
		for (final ItemStack stack : this) {
			if (stack == null)
				continue;
			if(stack.getItem() == SSPItems.module3.instance) {
				storage++;
			
		}}
		
		return storage;
		
	}

	public int GenNight() {
		int GenNight = 0;
		for (final ItemStack stack : this) {
			if (stack == null)
				continue;
			if(stack.getItem() == SSPItems.module2.instance) {
				GenNight++;
			
		}}
		
		return GenNight;
		
	}
	public double charge(double amount) {
		if (amount <= 0.0) {
			throw new IllegalArgumentException("Amount must be > 0.");
		}
		double charged = 0.0;
		for (final ItemStack stack : this) {
			if (stack == null)
				continue;
			final double energyIn = ElectricItem.manager.charge(stack, amount, this.tier, false, false);
			amount -= energyIn;
			charged += energyIn;
			if (amount <= 0.0)
				break;
		}
		return charged;
	}
}
