package com.Denfop.integration.Avaritia;

import com.Denfop.SSPItem;
import com.Denfop.integration.DE.SSPDEItem;
import cpw.mods.fml.common.registry.GameRegistry;
import fox.spiteful.avaritia.items.LudicrousItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AvaritiaIntegration {

    public static Block blockAvSolarPanel;
    public static Item neutroncore;
    public static Item infinitycore;

    public static void init() {
        GameRegistry.registerBlock(blockAvSolarPanel = new blockAvSolarPanel(), ItemAvSolarPanel.class, "blockAvSolarPanel");
        GameRegistry.registerItem(neutroncore = new SSPDEItem().setMaxStackSize(64).setUnlocalizedName("neutroncore").setTextureName("supersolarpanel:neutroncore"), "neutroncore");
        GameRegistry.registerItem(infinitycore = new SSPDEItem().setMaxStackSize(64).setUnlocalizedName("infinitycore").setTextureName("supersolarpanel:infinitycore"), "infinitycore");
        GameRegistry.registerTileEntity(TileEntityNeutronSolarPanel.class, "Neutron Solar Panel Avaritia");
        GameRegistry.registerTileEntity(TileEntityInfinitySolarPanel.class, "Infinity Solar Panel");

    }

    public static void recipe() {
        GameRegistry.addRecipe(new ItemStack(blockAvSolarPanel, 1, 0), " B ", "BAB", " B ", 'B', new ItemStack(SSPItem.blockSSPSolarPanel, 1, 8), 'A', neutroncore);
        GameRegistry.addRecipe(new ItemStack(blockAvSolarPanel, 1, 1), " B ", "BAB", " B ", 'B', new ItemStack(blockAvSolarPanel, 1, 0), 'A', infinitycore);

        GameRegistry.addRecipe(new ItemStack(neutroncore, 1), " A ", "ABA", " A ", 'B', new ItemStack(SSPItem.protoncore, 1), 'A', new ItemStack(LudicrousItems.resource, 1, 4));
        GameRegistry.addRecipe(new ItemStack(infinitycore, 1), "BAB", "ABA", "BAB", 'B', new ItemStack(neutroncore, 1), 'A', new ItemStack(LudicrousItems.resource, 1, 6));

    }


}
