package com.Denfop.RecipeManager;


import com.Denfop.api.IMicrochipFarbricatorRecipeManager;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeOutput;
import ic2.core.util.StackUtil;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class MicrochipRecipeManager implements IMicrochipFarbricatorRecipeManager {
    private final Map<IMicrochipFarbricatorRecipeManager.Input, RecipeOutput> recipes = new HashMap<IMicrochipFarbricatorRecipeManager.Input, RecipeOutput>();

    public void addRecipe(IRecipeInput container, IRecipeInput fill, IRecipeInput container1, IRecipeInput fill1, IRecipeInput fill2, ItemStack output) {
        if (container == null)
            throw new NullPointerException("The slot 1 recipe input is null");
        if (fill == null)
            throw new NullPointerException("The slot 2 recipe input is null");
        if (fill1 == null)
            throw new NullPointerException("The slot 3 recipe input is null");
        if (fill2 == null)
            throw new NullPointerException("The slot 4 recipe input is null");
        if (container1 == null)
            throw new NullPointerException("The slot 5 recipe input is null");
        if (output == null)
            throw new NullPointerException("The recipe output is null");
        if (!StackUtil.check(output))
            throw new IllegalArgumentException("The recipe output " + StackUtil.toStringSafe(output) + " is invalid");
        for (IMicrochipFarbricatorRecipeManager.Input input : this.recipes.keySet()) {
            for (ItemStack containerStack : container.getInputs()) {
                for (ItemStack fillStack : fill.getInputs()) {
                    for (ItemStack fillStack1 : fill1.getInputs()) {
                        for (ItemStack fillStack2 : fill2.getInputs()) {
                            for (ItemStack containerStack1 : container1.getInputs()) {
                                if (input.matches(containerStack, fillStack, fillStack1, fillStack2, containerStack1))
                                    throw new RuntimeException("ambiguous recipe: [" + container.getInputs() + "+" + fill.getInputs() + "+" + fill1.getInputs() + "+" + fill2.getInputs() + "+" + container1.getInputs() + " -> " + output + "], conflicts with [" + input.container
                                            .getInputs() + "+" + input.fill.getInputs() + "+" + input.fill1.getInputs() + "+" + input.fill2.getInputs() + "+" + input.container1.getInputs() + " -> " + this.recipes.get(input) + "]");
                            }
                        }
                    }
                }
            }
        }
        this.recipes.put(new IMicrochipFarbricatorRecipeManager.Input(container, fill, fill1, fill2, container1), new RecipeOutput(null, output));
    }

    public RecipeOutput getOutputFor(ItemStack container, ItemStack fill, ItemStack container1, ItemStack fill1, ItemStack fill2, boolean adjustInput, boolean acceptTest) {
        if (acceptTest) {
            if (container == null && fill == null)
                return null;
        } else if (container == null || fill == null || container1 == null || fill1 == null || fill2 == null) {
            return null;
        }
        for (Map.Entry<IMicrochipFarbricatorRecipeManager.Input, RecipeOutput> entry : this.recipes.entrySet()) {
            IMicrochipFarbricatorRecipeManager.Input recipeInput = entry.getKey();
            if (acceptTest && container == null) {
                if (recipeInput.fill.matches(fill))
                    return entry.getValue();
                continue;
            }
            if (acceptTest && container1 == null) {
                if (recipeInput.fill.matches(fill))
                    return entry.getValue();
                continue;
            }
            if (acceptTest && fill == null) {
                if (recipeInput.container.matches(container))
                    return entry.getValue();
                continue;
            }
            if (acceptTest && fill1 == null) {
                if (recipeInput.container.matches(container))
                    return entry.getValue();
                continue;
            }

            if (acceptTest && fill2 == null) {
                if (recipeInput.container.matches(container))
                    return entry.getValue();
                continue;
            }
            if (recipeInput.matches(container, fill, fill1, fill2, container1)) {
                if (acceptTest || (container != null && container.stackSize >= recipeInput.container.getAmount() && container1 != null && container1.stackSize >= recipeInput.container1.getAmount() && fill != null && fill.stackSize >= recipeInput.fill.getAmount() && fill1 != null && fill1.stackSize >= recipeInput.fill1.getAmount() && fill2 != null && fill2.stackSize >= recipeInput.fill2.getAmount())) {
                    if (adjustInput) {

                        container.stackSize -= recipeInput.container.getAmount();
                        container1.stackSize -= recipeInput.container1.getAmount();
                        fill.stackSize -= recipeInput.fill.getAmount();
                        fill1.stackSize -= recipeInput.fill1.getAmount();
                        fill2.stackSize -= recipeInput.fill2.getAmount();
                    }
                    return entry.getValue();
                }
                break;
            }
        }
        return null;
    }

    public Map<IMicrochipFarbricatorRecipeManager.Input, RecipeOutput> getRecipes() {
        return this.recipes;
    }


}
