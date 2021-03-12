package com.Denfop.container;

import com.Denfop.tiles.base.TileEntityChargepadBlock;
import com.Denfop.tiles.base.TileEntityElectricBlock;
import ic2.core.ContainerFullInv;
import ic2.core.block.invslot.InvSlotArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;

import java.util.List;

public class ContainerChargepadBlock extends ContainerFullInv<TileEntityChargepadBlock> {
    private final TileEntityElectricBlock tileentity;

    public ContainerChargepadBlock(EntityPlayer entityPlayer, TileEntityChargepadBlock tileEntity1) {
        super(entityPlayer, tileEntity1, 196);
        this.tileentity = tileEntity1;
        for (int col = 0; col < 4; col++)
            addSlotToContainer(new InvSlotArmor(entityPlayer.inventory, col, 8 + col * 18, 84));
        for (int j = 0; j < 2; ++j) {

            this.addSlotToContainer(new Slot(this.tileentity, j, 56 - 36 + (j * (j + 1) * 18) * j, 17));
        }

        addSlotToContainer(new Slot(this.tileentity, 2, 56, 53));
        this.addSlotToContainer(new Slot(this.tileentity, 3, 56 - 36, 17 + 18));
    }


    public List<String> getNetworkedFields() {
        List<String> ret = super.getNetworkedFields();
        ret.add("energy2");
        ret.add("energy");
        ret.add("redstoneMode");
        ret.add("chargeSlots");
        return ret;
    }
}
