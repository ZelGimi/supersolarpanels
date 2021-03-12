package com.Denfop.container;

import com.Denfop.tiles.ExpGen.TileExpGen2;
import ic2.core.IC2;
import ic2.core.network.NetworkManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;


public class ContainerExpGen extends Container {
    public TileExpGen2 tileentity;


    public ContainerExpGen(InventoryPlayer inventoryplayer, TileExpGen2 tileExpGen2) {
        this.tileentity = tileExpGen2;
        for (int i = 0; i < 3; i++) {

            for (int m = 0; m < 9; m++) {
                addSlotToContainer(new Slot(inventoryplayer, m + i * 9 + 9, 8 + m * 18, 84 + i * 18));
            }
        }


        for (int j = 0; j < 9; j++) {
            addSlotToContainer(new Slot(inventoryplayer, j, 8 + j * 18, 142));
        }


    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        int slot = par2;
        ItemStack stack = null;

        Slot slotObject = (Slot) this.inventorySlots.get(par2);

        if (slotObject != null && slotObject.getHasStack()) {

            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();


            if (stackInSlot.stackSize == 0) {

                slotObject.putStack(null);
            } else {

                slotObject.onSlotChanged();
            }

            if (stack.stackSize != stackInSlot.stackSize) {

                slotObject.onPickupFromSlot(par1EntityPlayer, stackInSlot);
            } else {

                return null;
            }
        }

        return stack;
    }

/*	public void onCraftGuiOpened(ICrafting icrafting) {
		super.addCraftingToCrafters(icrafting);
	
		icrafting.sendProgressBarUpdate(this, 1, (int)this.tileentity.energy);
	
		
		icrafting.sendProgressBarUpdate(this, 2, this.tileentity.getFluidTank().getFluidAmount());
		System.out.println("container craft gui amount " + this.tileentity.getFluidTank().getFluidAmount());
		
	
	}
	*/


    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        if (this.tileentity != null) {
            for (String name : getNetworkedFields()) {
                for (Object crafter : this.crafters) {
                    if (crafter instanceof EntityPlayerMP) {

                        IC2.network.get().updateTileEntityFieldTo(this.tileentity, name, (EntityPlayerMP) crafter);


                    }
                }
            }

        }


        //	for(int i = 0; i < this.crafters.size(); i++) {

        //		ICrafting icrafting = (ICrafting) this.crafters.get(i);

        //	icrafting.sendProgressBarUpdate(this, 1, (int)this.tileentity.energy);

        //	icrafting.sendProgressBarUpdate(this, 2, this.tileentity.getFluidTank().getFluidAmount());
        //	System.out.println("detect send changes " + this.tileentity.getFluidTank().getFluidAmount());


    }


    @Override
    public boolean canInteractWith(EntityPlayer player) {

        return this.tileentity.isUseableByPlayer(player);
    }


    public void updateProgressBar(int i, int j) {
        super.updateProgressBar(i, j);
        if (i == 1) {

            //	this.tileentity.energy = (int)this.tileentity.energy | j;

        }

        if (i == 2) {


            //	this.tileentity.getFluidTank().getFluid().amount = j;


        }


    }

    public List<String> getNetworkedFields() {
        List<String> ret = new ArrayList();
        ret.add("fluidTank");
        ret.add("energy");

        return ret;

    }


}
	
