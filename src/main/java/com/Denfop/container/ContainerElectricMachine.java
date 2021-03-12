package com.Denfop.container;

import ic2.core.ContainerBase;
import ic2.core.block.machine.tileentity.TileEntityElectricMachine;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;

public abstract class ContainerElectricMachine<T extends TileEntityElectricMachine> extends ContainerBase<T> {
    public ContainerElectricMachine(EntityPlayer entityPlayer, T base1, int height, int dischargeX, int dischargeY) {
        super(base1);

    }

    public List<String> getNetworkedFields() {
        List<String> ret = super.getNetworkedFields();
        ret.add("guiChargeLevel");
        ret.add("tier");
        return ret;
    }
}
