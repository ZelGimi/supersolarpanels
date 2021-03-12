package com.Denfop.InvSlot;


import com.Denfop.api.IAlloySmelterRecipeManager;
import com.Denfop.api.Recipes;
import com.Denfop.tiles.Mechanism.TileEntityAlloySmelter;
import ic2.api.recipe.RecipeOutput;
import ic2.core.block.TileEntityInventory;
import ic2.core.block.invslot.InvSlotProcessable;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class InvSlotProcessableAlloy extends InvSlotProcessable {


    public InvSlotProcessableAlloy(TileEntityInventory base1, String name1, int oldStartIndex1, int count, IAlloySmelterRecipeManager recipeManager1) {
        super(base1, name1, oldStartIndex1, count);

    }

    public boolean accepts(ItemStack itemStack) {
        return itemStack == null ||
                !(itemStack.getItem() instanceof ic2.core.item.ItemUpgradeModule);

    }

    protected RecipeOutput getOutput(ItemStack container, ItemStack fill, boolean adjustInput, boolean forAccept) {

        return Recipes.Alloysmelter.getOutputFor(container, fill, adjustInput, forAccept);


    }

    protected RecipeOutput getOutputFor(ItemStack input, ItemStack input1, boolean adjustInput, boolean forAccept) {
        return getOutput(input, input1, adjustInput, forAccept);
    }

    public RecipeOutput process() {
        ItemStack input = ((TileEntityAlloySmelter) this.base).inputSlotA.get();
        ItemStack input1 = ((TileEntityAlloySmelter) this.base).inputSlotB.get();
        if (input == null && !allowEmptyInput())
            return null;
        if (input1 == null && !allowEmptyInput())
            return null;
        RecipeOutput output = getOutputFor(input, input1, false, false
        );
        if (output == null)
            return null;
        List<ItemStack> itemsCopy = new ArrayList<ItemStack>(output.items.size());
        for (ItemStack itemStack : output.items)
            itemsCopy.add(itemStack);
        return new RecipeOutput(output.metadata, itemsCopy);
    }

    public void consume() {

        ItemStack input = ((TileEntityAlloySmelter) this.base).inputSlotA.get();
        ItemStack input1 = ((TileEntityAlloySmelter) this.base).inputSlotB.get();
        if (input != null && input.stackSize <= 1)
            ((TileEntityAlloySmelter) this.base).inputSlotA.put(null);
        if (input1 != null && input1.stackSize <= 1)
            ((TileEntityAlloySmelter) this.base).inputSlotB.put(null);


        RecipeOutput output = getOutputFor(input, input1, true, false);

    }


    protected boolean allowEmptyInput() {
        return false;
    }
}
