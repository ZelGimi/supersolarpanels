package com.Denfop.integration.nei;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import com.Denfop.Constants;
import com.Denfop.api.IGenStoneRecipeManager;
import com.Denfop.api.Recipes;
import com.Denfop.gui.GuiGenStone;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeOutput;
import ic2.core.util.StackUtil;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NEIGenStone extends TemplateRecipeHandler {
    int ticks;

    public Class<? extends GuiContainer> getGuiClass() {
        return GuiGenStone.class;
    }

    public String getRecipeName() {
        return StatCollector.translateToLocal("ssp.genstone");
    }

    public String getRecipeId() {
        return StatCollector.translateToLocal("ssp.genstone");
    }

    public String getGuiTexture() {

        return Constants.TEXTURES + ":textures/gui/GUIGenStone.png";
    }

    public String getOverlayIdentifier() {
        return "genstine";
    }

    public Map<IGenStoneRecipeManager.Input, RecipeOutput> getRecipeList() {
        return Recipes.GenStone.getRecipes();
    }

    public void drawBackground(int i) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GuiDraw.changeTexture(getGuiTexture());
        GuiDraw.drawTexturedModalRect(0, 0, 0, 0, 140, 83);
    }

    public void drawExtras(int i) {
        float f = (this.ticks >= 20) ? (((this.ticks - 20) % 20) / 20.0F) : 0.0F;

        drawProgressBar(51, 17 + 17 + 1 - 8 + 1, 176 + 1, 32, 25, 32, f, 1);
        f = (this.ticks <= 20) ? (this.ticks / 20.0F) : 1.0F;

        drawProgressBar(56 + 1 - 48, 20 + 14 + 2, 176, 0, 14, 14, f, 3);
    }

    public void onUpdate() {
        super.onUpdate();
        this.ticks++;
    }

    public void loadTransferRects() {
        this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(51, 17 + 17 + 1, 25, 32), getRecipeId()));
    }

    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals(getRecipeId())) {
            for (Map.Entry<IGenStoneRecipeManager.Input, RecipeOutput> entry : getRecipeList().entrySet())
                this.arecipes.add(new GenStoneRecipe(entry.getKey().container, entry.getKey().fill, entry.getValue()));
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    public void loadCraftingRecipes(ItemStack result) {
        for (Map.Entry<IGenStoneRecipeManager.Input, RecipeOutput> entry : getRecipeList().entrySet()) {
            for (ItemStack output : entry.getValue().items) {
                if (NEIServerUtils.areStacksSameTypeCrafting(output, result))
                    this.arecipes.add(new GenStoneRecipe(entry.getKey().container, entry.getKey().fill, entry.getValue()));
            }
        }
    }

    public void loadUsageRecipes(ItemStack ingredient) {
        for (Map.Entry<IGenStoneRecipeManager.Input, RecipeOutput> entry : getRecipeList().entrySet()) {
            if (entry.getKey().container.matches(ingredient) || entry.getKey().fill.matches(ingredient))
                this.arecipes.add(new GenStoneRecipe(entry.getKey().container, entry.getKey().fill, entry.getValue()));
        }
    }

    public int recipiesPerPage() {
        return 1;
    }

    public class GenStoneRecipe extends TemplateRecipeHandler.CachedRecipe {
        public PositionedStack output;

        public List<PositionedStack> ingredients = new ArrayList<PositionedStack>();

        public GenStoneRecipe(IRecipeInput container, IRecipeInput fill, RecipeOutput output1) {
            super();
            List<ItemStack> containerItems = new ArrayList<ItemStack>();
            List<ItemStack> fillItems = new ArrayList<ItemStack>();
            for (ItemStack item : container.getInputs())
                containerItems.add(StackUtil.copyWithSize(item, container.getAmount()));
            for (ItemStack item : fill.getInputs())
                fillItems.add(StackUtil.copyWithSize(item, fill.getAmount()));
            this.ingredients.add(new PositionedStack(containerItems, 38, 10));
            this.ingredients.add(new PositionedStack(fillItems, 73, 10));
            this.output = new PositionedStack(output1.items.get(0), 56, 66);
        }

        public List<PositionedStack> getIngredients() {
            return getCycledIngredients(NEIGenStone.this.cycleticks / 20, this.ingredients);
        }

        public PositionedStack getResult() {
            return this.output;
        }
    }
}
