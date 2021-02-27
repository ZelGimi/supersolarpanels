package com.Denfop.container;

import ic2.core.ContainerFullInv;
import ic2.core.block.invslot.InvSlot;
import ic2.core.block.machine.tileentity.TileEntityMatter;
import ic2.core.slot.SlotInvSlot;
import java.util.List;

import com.Denfop.tiles.Mechanism.TileEntityAdvancedMatter;
import com.Denfop.tiles.base.TileEntityMultiMatter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ContainerMultiMatter extends ContainerFullInv<TileEntityMultiMatter> {
	public ContainerMultiMatter(EntityPlayer entityPlayer, TileEntityMultiMatter tileEntity1) {
		super(entityPlayer, tileEntity1, 166);
		addSlotToContainer((Slot) new SlotInvSlot((InvSlot) tileEntity1.amplifierSlot, 0, 72, 40));
		addSlotToContainer((Slot) new SlotInvSlot((InvSlot) tileEntity1.outputSlot, 0, 125, 59));
		addSlotToContainer((Slot) new SlotInvSlot((InvSlot) tileEntity1.containerslot, 0, 125, 23));
		for (int i = 0; i < 4; i++)
			addSlotToContainer((Slot) new SlotInvSlot((InvSlot) tileEntity1.upgradeSlot, i, 152, 8 + i * 18));
	}

	public List<String> getNetworkedFields() {
		List<String> ret = super.getNetworkedFields();
		ret.add("energy");
		ret.add("scrap");
		ret.add("fluidTank");
		return ret;
	}
}