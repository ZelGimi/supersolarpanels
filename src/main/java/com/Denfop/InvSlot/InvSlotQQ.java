package com.Denfop.InvSlot;

import com.Denfop.item.Modules.module8;
import ic2.core.block.TileEntityInventory;
import ic2.core.block.invslot.InvSlot;
import net.minecraft.item.ItemStack;

public class InvSlotQQ extends InvSlot {


    public InvSlotQQ(TileEntityInventory base1, int oldStartIndex1) {
        super(base1, "input2", oldStartIndex1, InvSlot.Access.IO, 1, InvSlot.InvSide.TOP);

    }

    public boolean accepts(ItemStack itemStack) {
        return itemStack.getItem() instanceof module8;
    }


}
