package com.Denfop.InvSlot;


import com.Denfop.api.IMicrochipFarbricatorRecipeManager;
import com.Denfop.api.Recipes;
import com.Denfop.tiles.Mechanism.TileEntityGenerationMicrochip;
import ic2.api.recipe.RecipeOutput;
import ic2.core.block.TileEntityInventory;
import ic2.core.block.invslot.InvSlotProcessable;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class InvSlotProcessableGenerationMicrochip extends InvSlotProcessable {


    public InvSlotProcessableGenerationMicrochip(TileEntityInventory base1, String name1, int oldStartIndex1, int count, IMicrochipFarbricatorRecipeManager recipeManager1) {
        super(base1, name1, oldStartIndex1, count);

    }

    public boolean accepts(ItemStack itemStack) {
        return itemStack == null ||
                !(itemStack.getItem() instanceof ic2.core.item.ItemUpgradeModule);

    }

    protected RecipeOutput getOutput(ItemStack container, ItemStack fill, ItemStack fill1, ItemStack fill2, ItemStack container1, boolean adjustInput, boolean forAccept) {

        return Recipes.GenerationMicrochip.getOutputFor(container, fill, fill1, fill2, container1, adjustInput, forAccept);


    }

    protected RecipeOutput getOutputFor(ItemStack input, ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4, boolean adjustInput, boolean forAccept) {
        return getOutput(input, input1, input2, input3, input4, adjustInput, forAccept);
    }

    public RecipeOutput process() {
        ItemStack input = ((TileEntityGenerationMicrochip) this.base).inputSlotA.get();
        ItemStack input1 = ((TileEntityGenerationMicrochip) this.base).inputSlotB.get();
        ItemStack input2 = ((TileEntityGenerationMicrochip) this.base).inputSlotC.get();
        ItemStack input3 = ((TileEntityGenerationMicrochip) this.base).inputSlotD.get();
        ItemStack input4 = ((TileEntityGenerationMicrochip) this.base).inputSlotE.get();
        if (input == null && !allowEmptyInput())
            return null;
        if (input1 == null && !allowEmptyInput())
            return null;
        if (input2 == null && !allowEmptyInput())
            return null;
        if (input3 == null && !allowEmptyInput())
            return null;
        if (input4 == null && !allowEmptyInput())
            return null;
        RecipeOutput output = getOutputFor(input, input1, input2, input3, input4, false, false
        );
        if (output == null)
            return null;
        List<ItemStack> itemsCopy = new ArrayList<ItemStack>(output.items.size());
        for (ItemStack itemStack : output.items)
            itemsCopy.add(itemStack);
        return new RecipeOutput(output.metadata, itemsCopy);
    }

    public void consume() {

        ItemStack input = ((TileEntityGenerationMicrochip) this.base).inputSlotA.get();
        ItemStack input1 = ((TileEntityGenerationMicrochip) this.base).inputSlotB.get();
        ItemStack input2 = ((TileEntityGenerationMicrochip) this.base).inputSlotC.get();
        ItemStack input3 = ((TileEntityGenerationMicrochip) this.base).inputSlotD.get();
        ItemStack input4 = ((TileEntityGenerationMicrochip) this.base).inputSlotE.get();
        if (input != null && input.stackSize <= 1)
            ((TileEntityGenerationMicrochip) this.base).inputSlotA.put(null);
        if (input1 != null && input1.stackSize <= 1)
            ((TileEntityGenerationMicrochip) this.base).inputSlotB.put(null);
        if (input2 != null && input2.stackSize <= 1)
            ((TileEntityGenerationMicrochip) this.base).inputSlotC.put(null);
        if (input3 != null && input3.stackSize <= 1)
            ((TileEntityGenerationMicrochip) this.base).inputSlotD.put(null);
        if (input4 != null && input4.stackSize <= 1)
            ((TileEntityGenerationMicrochip) this.base).inputSlotE.put(null);

        RecipeOutput output = getOutputFor(input, input1, input2, input3, input4, true, false);

    }


    protected boolean allowEmptyInput() {
        return false;
    }
}
