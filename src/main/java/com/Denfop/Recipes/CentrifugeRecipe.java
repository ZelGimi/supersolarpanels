package com.Denfop.Recipes;

import com.Denfop.SSPItem;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.Recipes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class CentrifugeRecipe {

    public static void init() {
        addcentrifuge(SSPItem.reactorDepletedtoriyDual, 1, new ItemStack(SSPItem.toriy, 2));
        addcentrifuge(SSPItem.reactorDepletedtoriyQuad, 1, new ItemStack(SSPItem.toriy, 4));
        addcentrifuge(SSPItem.reactorDepletedtoriySimple, 1, new ItemStack(SSPItem.toriy, 1));


        addcentrifuge(SSPItem.reactorDepletedprotonDual, 1, new ItemStack(SSPItem.proton, 2));
        addcentrifuge(SSPItem.reactorDepletedprotonQuad, 1, new ItemStack(SSPItem.proton, 4));
        addcentrifuge(SSPItem.reactorDepletedprotoneit, 1, new ItemStack(SSPItem.proton, 8));
        addcentrifuge(SSPItem.reactorDepletedprotonSimple, 1, new ItemStack(SSPItem.proton, 1));

    }

    public static void addcentrifuge(ItemStack stack, int n, ItemStack output) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setShort("minHeat", (short) 5000);
        Recipes.centrifuge.addRecipe(new RecipeInputItemStack(stack, n), nbt, output);

    }
}
