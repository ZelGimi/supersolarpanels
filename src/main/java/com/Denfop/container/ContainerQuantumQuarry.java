package com.Denfop.container;

import com.Denfop.tiles.Mechanism.TileEntityQuantumQuarry;
import ic2.core.ContainerFullInv;
import ic2.core.block.invslot.InvSlot;
import ic2.core.slot.SlotInvSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;

import java.util.List;

public class ContainerQuantumQuarry<T extends TileEntityQuantumQuarry> extends ContainerFullInv<T> {
    private TileEntityQuantumQuarry tileentity;

    public ContainerQuantumQuarry(EntityPlayer entityPlayer, TileEntityQuantumQuarry tileEntity1) {
        this(entityPlayer, (T) tileEntity1, 166);
        this.tileentity = tileEntity1;
        if (tileEntity1.outputSlot != null) {

            for (int j = 0; j < 6; ++j) {

                addSlotToContainer(new SlotInvSlot(tileEntity1.outputSlot, j, 30 + 18 * j, 6));
            }
            for (int j = 0; j < 6; ++j) {

                addSlotToContainer(new SlotInvSlot(tileEntity1.outputSlot, j + 6, 30 + 18 * j, 6 + 18));
            }
            for (int j = 0; j < 6; ++j) {

                addSlotToContainer(new SlotInvSlot(tileEntity1.outputSlot, j + 12, 30 + 18 * j, 6 + 18 + 18));
            }
            for (int j = 0; j < 6; ++j) {

                addSlotToContainer(new SlotInvSlot(tileEntity1.outputSlot, j + 18, 30 + 18 * j, 6 + 18 + 18 + 18));
            }
            addSlotToContainer(new SlotInvSlot(tileEntity1.inputslot, 0, 8, 6 + 18 + 9));
        }
    }

    public ContainerQuantumQuarry(EntityPlayer entityPlayer, T tileEntity1, int height) {
        super(entityPlayer, tileEntity1, height);
    }


    public List<String> getNetworkedFields() {
        List<String> ret = super.getNetworkedFields();
        ret.add("energyconsume");
        ret.add("energy");
        ret.add("progress");
        ret.add("getblock");
        ret.add("maxEnergy");
        return ret;
    }
}
