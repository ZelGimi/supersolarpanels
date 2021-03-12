package com.Denfop.tiles.Mechanism;

import com.Denfop.InvSlot.InvSlotProcessableMultiSmelting;
import com.Denfop.tiles.base.TileEntityMultiMachine;
import ic2.core.upgrade.UpgradableProperty;

import java.util.EnumSet;
import java.util.Set;

public class TileEntityTripleElectricFurnace extends TileEntityMultiMachine {
    public TileEntityTripleElectricFurnace() {
        super();
        this.inputSlots = new InvSlotProcessableMultiSmelting(this, "input", sizeWorkingSlot);
    }

    @Override
    protected EnumMultiMachine getMachine() {
        return EnumMultiMachine.TRIPLE_ELECTRIC_FURNACE;
    }

    public String getInventoryName() {
        return "Triple Electric Furnace";
    }

    public String getStartSoundFile() {
        return "Machines/Electro Furnace/ElectroFurnaceLoop.ogg";
    }

    public String getInterruptSoundFile() {
        return null;
    }

    public Set<UpgradableProperty> getUpgradableProperties() {
        return EnumSet.of(UpgradableProperty.Processing, UpgradableProperty.Transformer,
                UpgradableProperty.EnergyStorage, UpgradableProperty.ItemConsuming, UpgradableProperty.ItemProducing);
    }
}
