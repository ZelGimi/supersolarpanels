package com.Denfop.integration.nei;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeOutput;
import ic2.core.util.StackUtil;
import ic2.neiIntegration.core.PositionedStackIc2;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class MolecularRecipeHandler extends TemplateRecipeHandler {
    protected int ticks;


    public abstract String getRecipeName();

    public abstract String getRecipeId();

    public abstract String getGuiTexture();

    public abstract String getOverlayIdentifier();

    public abstract Map<IRecipeInput, RecipeOutput> getRecipeList();

    public void drawBackground(int i) {

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GuiDraw.changeTexture(getGuiTexture());
        GuiDraw.drawTexturedModalRect(0, 0, 5, 11, 140, 65);

    }

    public void drawExtras(int i) {
        float f = (this.ticks >= 20) ? (((this.ticks - 20) % 20) / 20.0F) : 0.0F;
        drawProgressBar(23, 26, 221, 7, 25, 20, f, 1);
        MolecularRecipeHandler.CachedIORecipe recipe = (CachedIORecipe) this.arecipes.get(i);


        String energyPerTick = I18n.format("gui.MolecularTransformer.energyPerOperation") + ": ";
        String input2 = I18n.format("gui.MolecularTransformer.input") + ": ";
        String output2 = I18n.format("gui.MolecularTransformer.output") + ": ";
        GuiDraw.drawString(energyPerTick + recipe.meta.getInteger("energy") + " EU", 60 + 10, 25 + 11, 13487565);

        GuiDraw.drawString(input2 + recipe.getIngredients().get(0).item.getDisplayName(), 60 + 10, 14, 13487565);
        GuiDraw.drawString(output2 + recipe.getResult().item.getDisplayName(), 60 + 10, 25, 13487565);


    }

    public void onUpdate() {
        super.onUpdate();
        this.ticks++;
    }

    public void loadTransferRects() {
        this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(21, 34, 10, 19), getRecipeId()));
    }

    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals(getRecipeId())) {
            for (Map.Entry<IRecipeInput, RecipeOutput> entry : getRecipeList().entrySet())
                this.arecipes.add(new CachedIORecipe(entry.getKey(), entry.getValue()));
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    public void loadCraftingRecipes(ItemStack result) {
        for (Map.Entry<IRecipeInput, RecipeOutput> entry : getRecipeList().entrySet()) {
            for (ItemStack output : entry.getValue().items) {
                if (NEIServerUtils.areStacksSameTypeCrafting(output, result))
                    this.arecipes.add(new CachedIORecipe(entry.getKey(), entry.getValue()));
            }
        }
    }

    public void loadUsageRecipes(ItemStack ingredient) {
        for (Map.Entry<IRecipeInput, RecipeOutput> entry : getRecipeList().entrySet()) {
            if (entry.getKey().matches(ingredient))
                this.arecipes.add(new CachedIORecipe(entry.getKey(), entry.getValue()));
        }
    }

    protected int getInputPosX() {
        return 20;
    }

    protected int getInputPosY() {
        return 5;
    }

    protected int getOutputPosX() {
        return 20;
    }

    protected int getOutputPosY() {
        return 46;
    }

    protected boolean isOutputsVertical() {
        return true;
    }

    public class CachedIORecipe extends TemplateRecipeHandler.CachedRecipe {
        final NBTTagCompound meta;
        private final List<PositionedStack> ingredients;
        private final PositionedStack output;
        private final List<PositionedStack> otherStacks;
        private int energy;

        public CachedIORecipe(ItemStack input, ItemStack output1) {
            super();
            this.ingredients = new ArrayList<PositionedStack>();
            this.otherStacks = new ArrayList<PositionedStack>();
            if (input == null)
                throw new NullPointerException("Input must not be null (recipe " + input + " -> " + output1 + ").");
            if (output1 == null)
                throw new NullPointerException("Output must not be null (recipe " + input + " -> " + output1 + ").");
            this.ingredients.add(new PositionedStack(input, MolecularRecipeHandler.this.getInputPosX(), MolecularRecipeHandler.this.getInputPosY()));
            this.output = new PositionedStack(output1, MolecularRecipeHandler.this.getOutputPosX(), MolecularRecipeHandler.this.getOutputPosY());
            this.meta = null;
        }

        public CachedIORecipe(IRecipeInput input, RecipeOutput output1) {
            super();
            this.ingredients = new ArrayList<PositionedStack>();
            this.otherStacks = new ArrayList<PositionedStack>();
            this.energy = output1.metadata.getInteger("energy");
            if (input == null)
                throw new NullPointerException("Input must not be null (recipe " + input + " -> " + output1 + ").");
            if (output1 == null)
                throw new NullPointerException("Output must not be null (recipe " + input + " -> " + output1 + ").");
            if (output1.items.isEmpty())
                throw new IllegalArgumentException("Output must not be empty (recipe " + input + " -> " + output1 + ").");
            if (output1.items.contains(null))
                throw new IllegalArgumentException("Output must not contain null (recipe " + input + " -> " + output1 + ").");
            List<ItemStack> items = new ArrayList<ItemStack>();
            for (ItemStack item : input.getInputs())
                items.add(StackUtil.copyWithSize(item, input.getAmount()));
            this.ingredients.add(new PositionedStackIc2(items, MolecularRecipeHandler.this.getInputPosX(), MolecularRecipeHandler.this.getInputPosY()));
            this.output = new PositionedStackIc2(output1.items.get(0), MolecularRecipeHandler.this.getOutputPosX(), MolecularRecipeHandler.this.getOutputPosY());


            for (int i = 1; i < output1.items.size(); i++) {
                if (MolecularRecipeHandler.this.isOutputsVertical()) {
                    this.otherStacks.add(new PositionedStack(output1.items.get(i), MolecularRecipeHandler.this.getOutputPosX(), MolecularRecipeHandler.this.getOutputPosY() + i * 18));
                } else {
                    this.otherStacks.add(new PositionedStack(output1.items.get(i), MolecularRecipeHandler.this.getOutputPosX() + i * 18, MolecularRecipeHandler.this.getOutputPosY()));
                }
            }
            this.meta = output1.metadata;
        }

        public List<PositionedStack> getIngredients() {
            return getCycledIngredients(MolecularRecipeHandler.this.cycleticks / 20, this.ingredients);
        }

        public PositionedStack getResult() {
            return this.output;
        }

        public int getEnergy() {
            return this.energy;
        }

        public List<PositionedStack> getOtherStacks() {
            return this.otherStacks;
        }
    }
}
