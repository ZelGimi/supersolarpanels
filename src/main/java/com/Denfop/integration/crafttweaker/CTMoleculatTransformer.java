package com.Denfop.integration.crafttweaker;

import com.Denfop.api.Recipes;
import ic2.api.recipe.RecipeOutput;
import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.minecraft.MineTweakerMC;
import minetweaker.mods.ic2.IC2RecipeInput;
import minetweaker.mods.ic2.MachineAddRecipeAction;
import net.minecraft.nbt.NBTTagCompound;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.SuperSolarPanel.MoleculatTransformer")
public class CTMoleculatTransformer {
    @ZenMethod
    public static void addRecipe(IItemStack[] output, IIngredient ingredient, int minHeat) {
        if (ingredient.getAmount() < 0) {
            MineTweakerAPI.logWarning("invalid ingredient: " + ingredient + " - stack size not known");
        } else {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setInteger("energy", minHeat);
            MineTweakerAPI.apply(new MachineAddRecipeAction("MolecularTransformer", Recipes.molecular,

                    MineTweakerMC.getItemStacks(output), tag, new IC2RecipeInput(ingredient)));
        }
    }

    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient ingredient, int minHeat) {
        addRecipe(new IItemStack[]{output}, ingredient, minHeat);
    }

    @ZenMethod
    public static IItemStack[] getOutput(IItemStack input) {
        RecipeOutput output = Recipes.molecular.getOutputFor(MineTweakerMC.getItemStack(input), false);
        if (output == null || output.items.isEmpty())
            return null;
        return MineTweakerMC.getIItemStacks(output.items);
    }
}
