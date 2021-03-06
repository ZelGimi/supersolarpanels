package com.Denfop.tiles.Mechanism;

import java.util.EnumSet;
import java.util.Set;

import com.Denfop.InvSlot.InvSlotProcessableMultiGeneric;
import com.Denfop.container.ContainerMultiMetalFormer;
import com.Denfop.gui.GuiMultiMachine;
import com.Denfop.gui.GuiMultiMetalFormer;
import com.Denfop.tiles.base.TileEntityMultiMachine;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.network.INetworkClientTileEntityEventListener;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.Recipes;
import ic2.core.ContainerBase;
import ic2.core.upgrade.UpgradableProperty;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityDoubleMetalFormer extends TileEntityMultiMachine
		implements INetworkClientTileEntityEventListener {
	private int mode;

	public TileEntityDoubleMetalFormer() {
		super();
		this.inputSlots = new InvSlotProcessableMultiGeneric(this, "input", 2, Recipes.metalformerExtruding);
	}

	@Override
	protected EnumMultiMachine getMachine() {
		return EnumMultiMachine.DOUBLE_METAL_FORMER;
	}
	
	public ContainerBase<? extends TileEntityMultiMachine> getGuiContainer(EntityPlayer entityPlayer) {
		return new ContainerMultiMetalFormer(entityPlayer, this, this.sizeWorkingSlot);
	}

	@SideOnly(Side.CLIENT)
	public GuiScreen getGui(EntityPlayer entityPlayer, boolean isAdmin) {
		return new GuiMultiMetalFormer(new ContainerMultiMetalFormer(entityPlayer, this, sizeWorkingSlot));
	}

	public static void addRecipeCutting(ItemStack input, int amount, ItemStack output) {
		addRecipeCutting((IRecipeInput) new RecipeInputItemStack(input, amount), output);
	}

	public static void addRecipeCutting(IRecipeInput input, ItemStack output) {
		Recipes.metalformerCutting.addRecipe(input, null, new ItemStack[] { output });
	}

	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		setMode(nbttagcompound.getInteger("mode"));
	}

	public void writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		nbttagcompound.setInteger("mode", this.mode);
	}

	public String getInventoryName() {
		return "Double MetalFormer";
	}

	public float getWrenchDropRate() {
		return 0.85F;
	}

	public void onNetworkEvent(EntityPlayer player, int event) {
		switch (event) {
		case 0:
			cycleMode();
			break;
		}
	}

	public void onNetworkUpdate(String field) {
		super.onNetworkUpdate(field);
		if (field.equals("mode"))
			setMode(this.mode);
	}

	public int getMode() {
		return this.mode;
	}

	public void setMode(int mode1) {
		InvSlotProcessableMultiGeneric slot = (InvSlotProcessableMultiGeneric) this.inputSlots;
		switch (mode1) {
		case 0:
			slot.setRecipeManager(Recipes.metalformerExtruding);
			break;
		case 1:
			slot.setRecipeManager(Recipes.metalformerRolling);
			break;
		case 2:
			slot.setRecipeManager(Recipes.metalformerCutting);
			break;
		default:
			throw new RuntimeException("invalid mode: " + mode1);
		}
		this.mode = mode1;
	}

	private void cycleMode() {
		setMode((getMode() + 1) % 3);
	}

	public Set<UpgradableProperty> getUpgradableProperties() {
		return EnumSet.of(UpgradableProperty.Processing, UpgradableProperty.Transformer,
				UpgradableProperty.EnergyStorage, UpgradableProperty.ItemConsuming, UpgradableProperty.ItemProducing);
	}
}