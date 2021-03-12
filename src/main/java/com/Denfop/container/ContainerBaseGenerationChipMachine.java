package com.Denfop.container;

import com.Denfop.tiles.base.TileEntityBaseGenerationMicrochip;
import ic2.core.block.invslot.InvSlot;
import ic2.core.block.machine.container.ContainerElectricMachine;
import ic2.core.slot.SlotInvSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;

import java.util.List;

public class ContainerBaseGenerationChipMachine<T extends TileEntityBaseGenerationMicrochip> extends ContainerElectricMachine<T> {
    public ContainerBaseGenerationChipMachine(EntityPlayer entityPlayer, T tileEntity1) {
        this(entityPlayer, tileEntity1, 166, 56, 53, 56, 17, 116, 35, 152, 8);
    }

    public ContainerBaseGenerationChipMachine(EntityPlayer entityPlayer, T tileEntity1, int height, int dischargeX, int dischargeY, int inputX, int inputY, int outputX, int outputY, int upgradeX, int upgradeY) {
        super(entityPlayer, tileEntity1, height, dischargeX, dischargeY);
        if ((tileEntity1).inputSlotB != null)
            addSlotToContainer(new SlotInvSlot((tileEntity1).inputSlotB, 0, inputX - 18, inputY));
        if ((tileEntity1).inputSlotC != null)
            addSlotToContainer(new SlotInvSlot((tileEntity1).inputSlotC, 0, inputX + 18, inputY + 18));
        if ((tileEntity1).inputSlotD != null)
            addSlotToContainer(new SlotInvSlot((tileEntity1).inputSlotD, 0, inputX + 18, inputY - 18));
        if ((tileEntity1).inputSlotA != null)
            addSlotToContainer(new SlotInvSlot((tileEntity1).inputSlotA, 0, inputX + 18, inputY));
        if ((tileEntity1).outputSlot != null)
            addSlotToContainer(new SlotInvSlot((tileEntity1).outputSlot, 0, outputX, outputY));
        if ((tileEntity1).inputSlotE != null)
            addSlotToContainer(new SlotInvSlot((tileEntity1).inputSlotE, 0, inputX - 18, inputY - 18));
        for (int i = 0; i < 4; i++)
            addSlotToContainer(new SlotInvSlot((tileEntity1).upgradeSlot, i, upgradeX, upgradeY + i * 18));
    }

    public List<String> getNetworkedFields() {
        List<String> ret = super.getNetworkedFields();
        ret.add("guiProgress");
        return ret;
    }
}
