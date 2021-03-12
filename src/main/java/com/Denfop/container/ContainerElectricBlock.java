package com.Denfop.container;

import com.Denfop.tiles.base.TileEntityElectricBlock;
import ic2.core.ContainerFullInv;
import ic2.core.block.invslot.InvSlotArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;

import java.util.List;

public class ContainerElectricBlock extends ContainerFullInv<TileEntityElectricBlock> {
    private final TileEntityElectricBlock tileentity;

    public ContainerElectricBlock(EntityPlayer entityPlayer, TileEntityElectricBlock tileEntity) {
        super(entityPlayer, tileEntity, 196);
        for (int col = 0; col < 4; col++)
            addSlotToContainer(new InvSlotArmor(entityPlayer.inventory, col, 8 + col * 18, 84));
        this.tileentity = tileEntity;
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
        ret.add("personality");

        ret.add("output_plus");
        ret.add("output_plus1");
        return ret;
    }

}
