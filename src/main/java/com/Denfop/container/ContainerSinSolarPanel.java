package com.Denfop.container;

import com.Denfop.tiles.base.TileSintezator;
import com.Denfop.utils.ModUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSinSolarPanel extends Container {
    private final TileSintezator tileentity;
    private int storage = 0;
    private final int fuel = 0;
    private boolean sunIsUp;
    private boolean skyIsVisible;
    private int generating;

    public ContainerSinSolarPanel(InventoryPlayer inventoryplayer, TileSintezator tileentitysolarpanel) {
        this.tileentity = tileentitysolarpanel;

        for (int j = 0; j < 9; ++j) {

            this.addSlotToContainer(new Slot(this.tileentity, j, 17 + j * 18, 59));
        }

        for (int i = 0; i < 3; ++i) {
            for (int k = 0; k < 9; ++k) {
                this.addSlotToContainer(new Slot(inventoryplayer, k + i * 9 + 9, 17 + k * 18, 86 + i * 18));
            }
        }

        for (int j = 0; j < 9; ++j) {
            this.addSlotToContainer(new Slot(inventoryplayer, j, 17 + j * 18, 144));
        }


    }

    @Override
    public void addCraftingToCrafters(ICrafting icrafting) {
        super.addCraftingToCrafters(icrafting);

        icrafting.sendProgressBarUpdate(this, 0, this.tileentity.sunIsUp ? 1 : 0);
        icrafting.sendProgressBarUpdate(this, 1, this.tileentity.skyIsVisible ? 1 : 0);


        ModUtils.sendContainerInt(this, icrafting, 2, 5, this.tileentity.generating);
        ModUtils.sendContainerInt(this, icrafting, 3, 4, this.tileentity.storage);

    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = (ICrafting) this.crafters.get(i);

            if (this.sunIsUp != this.tileentity.sunIsUp)
                icrafting.sendProgressBarUpdate(this, 0, this.tileentity.sunIsUp ? 1 : 0);
            if (this.skyIsVisible != this.tileentity.skyIsVisible)
                icrafting.sendProgressBarUpdate(this, 1, this.tileentity.skyIsVisible ? 1 : 0);
            if (this.generating != this.tileentity.generating)
                ModUtils.sendContainerInt(this, icrafting, 2, 5, this.tileentity.generating);
            if (this.storage != this.tileentity.storage)
                ModUtils.sendContainerInt(this, icrafting, 3, 4, this.tileentity.storage);

        }

        this.sunIsUp = this.tileentity.sunIsUp;
        this.skyIsVisible = this.tileentity.skyIsVisible;
        this.generating = this.tileentity.generating;
        this.storage = this.tileentity.storage;
    }

    @Override
    public void updateProgressBar(int id, int val) {

        if (id == 0)
            this.tileentity.sunIsUp = val != 0;
        if (id == 1)
            this.tileentity.skyIsVisible = val != 0;

        this.tileentity.generating = ModUtils.recieveContainerInt(2, 5, id, val, this.tileentity.generating);
        this.tileentity.storage = ModUtils.recieveContainerInt(3, 4, id, val, this.tileentity.storage);

    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotNumber) {
        ItemStack stack = null;
        Slot slotObject = (Slot) this.inventorySlots.get(slotNumber);
        if (slotObject != null && slotObject.getHasStack()) {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();
            if (slotNumber >= 0 && slotNumber < 9) {
                if (!this.mergeItemStack(stackInSlot, 10, 45, true))
                    return null;
            } else if (slotNumber >= 9 && slotNumber <= 38) {
                if (!this.mergeItemStack(stackInSlot, 0, 9, false) && !this.mergeItemStack(stackInSlot, 39, 45, false))
                    return null;
            } else if (slotNumber >= 39 && slotNumber < 45) {
                if (!this.mergeItemStack(stackInSlot, 0, 9, false) && !this.mergeItemStack(stackInSlot, 9, 38, false))
                    return null;
            } else if (!this.mergeItemStack(stackInSlot, 0, 45, false))
                return null;

            if (stackInSlot.stackSize == 0)
                slotObject.putStack(null);
            else
                slotObject.onSlotChanged();

            if (stack.stackSize == stackInSlot.stackSize)
                return null;

            slotObject.onPickupFromSlot(player, stackInSlot);
        }

        return stack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return this.tileentity.isUseableByPlayer(entityplayer);
    }
}
