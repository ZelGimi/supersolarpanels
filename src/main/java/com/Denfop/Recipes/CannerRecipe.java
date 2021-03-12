package com.Denfop.Recipes;

import com.Denfop.SSPItem;
import ic2.api.item.IC2Items;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.Recipes;
import net.minecraft.item.ItemStack;

public class CannerRecipe {
    public static void recipe() {
        Recipes.cannerBottle.addRecipe(new RecipeInputItemStack(IC2Items.getItem("fuelRod"), 1), new RecipeInputItemStack(new ItemStack(SSPItem.proton, 1), 1), SSPItem.reactorprotonSimple);
        Recipes.cannerBottle.addRecipe(new RecipeInputItemStack(IC2Items.getItem("fuelRod"), 1), new RecipeInputItemStack(new ItemStack(SSPItem.toriy, 1), 1), SSPItem.reactortoriySimple);

    }
}
