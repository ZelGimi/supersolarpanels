package com.Denfop.container;

import com.Denfop.tiles.Mechanism.TileEntityGenerationStone;
import com.Denfop.tiles.base.TileEntityBaseGenStone;
import ic2.core.block.invslot.InvSlot;
import ic2.core.block.machine.container.ContainerElectricMachine;
import ic2.core.slot.SlotInvSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;

import java.util.List;

public class ContainerGenStone<T extends TileEntityBaseGenStone> extends ContainerElectricMachine<T> {
    public ContainerGenStone(EntityPlayer entityPlayer, T tileEntity1) {
        this(entityPlayer, tileEntity1, 166, 56 - 48, 53, 56, 17, 116, 35, 152, 8);
    }

    public ContainerGenStone(EntityPlayer entityPlayer, T tileEntity1, int height, int dischargeX, int dischargeY, int inputX, int inputY, int outputX, int outputY, int upgradeX, int upgradeY) {
        super(entityPlayer, tileEntity1, height, dischargeX, dischargeY);
        if (tileEntity1.inputSlotB != null)
            addSlotToContainer(new SlotInvSlot(tileEntity1.inputSlotB, 0, inputX - 18, inputY - 8 + 1));

        if (tileEntity1.inputSlotA != null)
            addSlotToContainer(new SlotInvSlot(tileEntity1.inputSlotA, 0, inputX + 18 - 1, inputY - 8 + 1));
        if (tileEntity1.outputSlot != null)
            addSlotToContainer(new SlotInvSlot(tileEntity1.outputSlot, 0, 54 + 1 + 1, 35 + 28 + 3));
        for (int i = 0; i < 4; i++)
            addSlotToContainer(new SlotInvSlot(tileEntity1.upgradeSlot, i, upgradeX, upgradeY + i * 18));
    }

    public List<String> getNetworkedFields() {
        List<String> ret = super.getNetworkedFields();
        ret.add("guiProgress");
        return ret;
    }
}
