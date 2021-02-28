package com.denfop.ssp.items;


import com.denfop.ssp.SuperSolarPanels;
import com.denfop.ssp.common.Constants;
import com.google.common.base.CaseFormat;
import ic2.api.item.ElectricItem;
import ic2.core.init.BlocksItems;
import ic2.core.item.BaseElectricItem;
import ic2.core.item.ItemIC2;
import ic2.core.ref.ItemName;
import ic2.core.util.StackUtil;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class ItemModule extends ItemIC2 {
	private static final int maxLevel = 4;
	private final String name;

	public ItemModule(String name) {
		super(null);
this.setCreativeTab(SuperSolarPanels.SSPTab);
		this.setMaxStackSize(16);
		BlocksItems.registerItem(this, SuperSolarPanels.getIdentifier(this.name = name)).setUnlocalizedName(name);
	}

	@SideOnly(Side.CLIENT)
	public void registerModels(ItemName name) {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(Constants.MOD_ID + ":" + CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.name), null));
	}

	public String getUnlocalizedName() {
		return Constants.MOD_ID + "." + super.getUnlocalizedName().substring(4);
	}

	
		
	
}
