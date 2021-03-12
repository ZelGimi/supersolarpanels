package com.Denfop.integration.crafttweaker;

import com.Denfop.api.Recipes;
import ic2.api.recipe.IRecipeInput;
import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.OneWayAction;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.minecraft.MineTweakerMC;
import minetweaker.mods.ic2.IC2RecipeInput;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.SuperSolarPanel.AlloySmelter")
public class CTAlloySmelter {
    @ZenMethod
    public static void addAlloSmelterRecipe(IItemStack output, IIngredient container, IIngredient fill) {
        MineTweakerAPI.apply(new AddAlloSmelterIngredientAction(container, fill, output));
    }


    private static class AddAlloSmelterIngredientAction extends OneWayAction {
        private final IIngredient container;

        private final IIngredient fill;

        private final IItemStack output;

        public AddAlloSmelterIngredientAction(IIngredient container, IIngredient fill, IItemStack output) {
            this.container = container;
            this.fill = fill;
            this.output = output;
        }

        public void apply() {
            Recipes.Alloysmelter.addRecipe(new IC2RecipeInput(this.container), new IC2RecipeInput(this.fill),

                    MineTweakerMC.getItemStack(this.output));
            Recipes.Alloysmelter.addRecipe(new IC2RecipeInput(this.fill), new IC2RecipeInput(this.container),

                    MineTweakerMC.getItemStack(this.output));
        }

        public String describe() {
            return "Adding canner bottle recipe " + this.container + " + " + this.fill + " => " + this.output;
        }

        public Object getOverrideKey() {
            return null;
        }

        public int hashCode() {
            int hash = 7;
            hash = 67 * hash + ((this.container != null) ? this.container.hashCode() : 0);
            hash = 67 * hash + ((this.fill != null) ? this.fill.hashCode() : 0);
            hash = 67 * hash + ((this.output != null) ? this.output.hashCode() : 0);
            return hash;
        }

        public boolean equals(Object obj) {
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            AddAlloSmelterIngredientAction other = (AddAlloSmelterIngredientAction) obj;
            if (this.container != other.container && (this.container == null || !this.container.equals(other.container)))
                return false;
            if (this.fill != other.fill && (this.fill == null || !this.fill.equals(other.fill)))
                return false;
            return this.output == other.output || (this.output != null && this.output.equals(other.output));
        }
    }


}
