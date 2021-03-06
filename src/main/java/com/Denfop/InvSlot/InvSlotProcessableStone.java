package com.Denfop.InvSlot;



import ic2.api.recipe.RecipeOutput;
import ic2.core.block.TileEntityInventory;
import ic2.core.block.invslot.InvSlotProcessable;

import java.util.ArrayList;
import java.util.List;


import com.Denfop.api.IGenStoneRecipeManager;
import com.Denfop.api.Recipes;
import com.Denfop.tiles.Mechanism.TileEntityGenerationStone;

import net.minecraft.item.ItemStack;

public class InvSlotProcessableStone extends InvSlotProcessable {
 
  
  public InvSlotProcessableStone(TileEntityInventory base1, String name1, int oldStartIndex1, int count, IGenStoneRecipeManager recipeManager1) {
    super(base1, name1, oldStartIndex1, count);
  
  }
  
  public boolean accepts(ItemStack itemStack) {
	    if (itemStack != null && 
	      itemStack.getItem() instanceof ic2.core.item.ItemUpgradeModule )
	      return false;
		return true; 
	  
	  }

  protected RecipeOutput getOutput(ItemStack container, ItemStack fill, boolean adjustInput, boolean forAccept) {
    
        return Recipes.GenStone.getOutputFor(container, fill, adjustInput, forAccept);
      
    
    
  }
  protected RecipeOutput getOutputFor(ItemStack input,ItemStack input1, boolean adjustInput, boolean forAccept) {
	    return getOutput(input, input1, adjustInput, forAccept);
	  }
  public RecipeOutput process() {
	  ItemStack input = ((TileEntityGenerationStone)this.base).inputSlotA.get();
    ItemStack input1 = ((TileEntityGenerationStone)this.base).inputSlotB.get();
    if (input == null && !allowEmptyInput())
      return null; 
    if (input1 == null && !allowEmptyInput())
        return null;
    RecipeOutput output = getOutputFor(input,input1, false, false
    		);
  ;
    if (output == null)
      return null; 
    List<ItemStack> itemsCopy = new ArrayList<ItemStack>(output.items.size());
    for (ItemStack itemStack : output.items)
      itemsCopy.add(itemStack); 
    return new RecipeOutput(output.metadata, itemsCopy);
  }
 
  public void consume() {
	
    ItemStack input = ((TileEntityGenerationStone)this.base).inputSlotA.get();
    ItemStack input1 = ((TileEntityGenerationStone)this.base).inputSlotB.get();
    if (input != null && input.stackSize <= 1)
	      ((TileEntityGenerationStone)this.base).inputSlotA.put(null); 
    if (input1 != null && input1.stackSize <= 1)
	      ((TileEntityGenerationStone)this.base).inputSlotB.put(null); 
   
    
    RecipeOutput output = getOutputFor(input,input1, true, false);
   
  }
  
 
  

  
  protected boolean allowEmptyInput() {
    return false;
  }
}
