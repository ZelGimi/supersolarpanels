package com.Denfop.InvSlot;

import com.Denfop.api.inv.IInvSlotProcessableMulti;
import ic2.api.recipe.IMachineRecipeManager;
import ic2.api.recipe.RecipeOutput;
import ic2.core.block.TileEntityInventory;
import ic2.core.block.invslot.InvSlot;
import ic2.core.item.ItemUpgradeModule;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InvSlotProcessableMultiGeneric extends InvSlot implements IInvSlotProcessableMulti {
    public IMachineRecipeManager recipeManager;
    private final int SIZE;

    public InvSlotProcessableMultiGeneric(TileEntityInventory base1, String name1, int count, IMachineRecipeManager recipeManager1) {
        super(base1, name1, 1, Access.I, count, InvSide.TOP);
        this.recipeManager = recipeManager1;
        this.SIZE = count;
    }

    public boolean accepts(ItemStack itemStack) {
        if (itemStack != null && itemStack.getItem() instanceof ItemUpgradeModule) {
            return false;
        } else {
            ItemStack tmp = itemStack.copy();
            tmp.stackSize = Integer.MAX_VALUE;
            return this.getOutputFor(tmp, false, true) != null;
        }
    }

    public RecipeOutput process(int slotId) {
        ItemStack input = this.get(slotId);
        if (input == null && !this.allowEmptyInput()) {
            return null;
        } else {
            RecipeOutput output = this.getOutputFor(input, false, false);
            if (output == null) {
                return null;
            } else {
                List<ItemStack> itemsCopy = new ArrayList(output.items.size());
                Iterator var4 = output.items.iterator();

                while (var4.hasNext()) {
                    ItemStack itemStack = (ItemStack) var4.next();
                    itemsCopy.add(itemStack.copy());
                }

                return new RecipeOutput(output.metadata, itemsCopy);
            }
        }
    }

    public void consume(int slotId) {
        ItemStack input = this.get(slotId);
        if (input == null && !this.allowEmptyInput()) {
            throw new IllegalStateException("consume from empty slot");
        } else {
            RecipeOutput output = this.getOutputFor(input, true, false);
            if (output == null) {
                throw new IllegalStateException("consume without a processing result");
            } else {
                if (input != null && input.stackSize <= 0) {
                    this.put(slotId, null);
                }

            }
        }
    }

    public boolean isEmpty(int slotId) {
        return this.get(slotId) == null;
    }

    public void setRecipeManager(IMachineRecipeManager recipeManager1) {
        this.recipeManager = recipeManager1;
    }

    protected RecipeOutput getOutputFor(ItemStack input, boolean adjustInput, boolean forAccept) {
        return this.recipeManager.getOutputFor(input, adjustInput);
    }

    protected boolean allowEmptyInput() {
        return false;
    }
}