package com.Denfop.api.inv;

import ic2.api.recipe.RecipeOutput;

public interface IInvSlotProcessableMulti {

    RecipeOutput process(int slotId);

    void consume(int slotId);

    boolean isEmpty(int slotId);

}
