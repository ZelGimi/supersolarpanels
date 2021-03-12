package com.Denfop.Recipes;

import com.Denfop.SSPItem;
import ic2.api.item.IC2Items;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.Recipes;
import ic2.core.Ic2Items;
import net.minecraft.item.ItemStack;

public class CompressorRecipe {
    public static void recipe() {


        addcompressor(Ic2Items.uuMatterCell, new ItemStack(SSPItem.matter, 2, 0));
        addcompressor(Ic2Items.carbonFiber, 9, new ItemStack(SSPItem.coal_chunk1));
        addcompressor(Ic2Items.carbonPlate, 9, new ItemStack(SSPItem.compresscarbon));
        addcompressor(Ic2Items.advancedAlloy, 9, new ItemStack(SSPItem.compresscarbonultra));
        addcompressor(Ic2Items.iridiumPlate, 4, new ItemStack(SSPItem.QuantumItems2));
        addcompressor(SSPItem.uuMatterCell, 1, new ItemStack(SSPItem.neutronium));
        addcompressor(new ItemStack(SSPItem.QuantumItems2), 9, new ItemStack(SSPItem.QuantumItems4));
        addcompressor(new ItemStack(SSPItem.wolfram_plate), 9, SSPItem.cell);
        addcompressor(new ItemStack(SSPItem.neutronium), 9, new ItemStack(SSPItem.neutroniumingot, 1));
        addcompressor(new ItemStack(SSPItem.spinel_ingot), 9, new ItemStack(SSPItem.spinel_plate, 1));
        addcompressor(new ItemStack(SSPItem.chromium_ingot), 9, new ItemStack(SSPItem.chromium_plate, 1));
        addcompressor(new ItemStack(SSPItem.wolfram_ingot), 9, new ItemStack(SSPItem.wolfram_plate, 1));
        addcompressor(new ItemStack(SSPItem.mikhail_ingot), 9, new ItemStack(SSPItem.michail_plate, 1));
        addcompressor(new ItemStack(SSPItem.nickel), 9, new ItemStack(SSPItem.nickelplate, 1));
        addcompressor(new ItemStack(SSPItem.electriumingot), 9, new ItemStack(SSPItem.electriumplate, 1));
        addcompressor(new ItemStack(SSPItem.invaringot), 9, new ItemStack(SSPItem.invarplate, 1));
        addcompressor(new ItemStack(SSPItem.magnesium_ingot), 9, new ItemStack(SSPItem.magnesium_plate, 1));
        addcompressor(new ItemStack(SSPItem.platium_ingot), 9, new ItemStack(SSPItem.platium_plate, 1));
        addcompressor(Ic2Items.uraniumOre, 1, new ItemStack(SSPItem.itemSSP, 1, 8));
        addcompressor(IC2Items.getItem("UranFuel"), 1, new ItemStack(SSPItem.itemSSP, 1, 8));
        addcompressor(IC2Items.getItem("crushedUraniumOre"), 1, new ItemStack(SSPItem.itemSSP, 1, 8));
    }

    public static void addcompressor(ItemStack input, int n, ItemStack output) {

        Recipes.compressor.addRecipe(new RecipeInputItemStack(input, n), null, output);
    }

    public static void addcompressor(ItemStack input, ItemStack output) {

        Recipes.compressor.addRecipe(new RecipeInputItemStack(input, 1), null, output);
    }
}
