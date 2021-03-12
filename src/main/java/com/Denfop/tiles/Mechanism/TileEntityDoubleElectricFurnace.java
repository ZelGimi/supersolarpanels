package com.Denfop.tiles.Mechanism;

import com.Denfop.InvSlot.InvSlotProcessableMultiSmelting;
import com.Denfop.tiles.base.TileEntityMultiMachine;
import ic2.core.upgrade.UpgradableProperty;

import java.util.EnumSet;
import java.util.Set;

public class TileEntityDoubleElectricFurnace extends TileEntityMultiMachine {
    public TileEntityDoubleElectricFurnace() {
        super();
        this.inputSlots = new InvSlotProcessableMultiSmelting(this, "input", 2);
    }

    @Override
    protected EnumMultiMachine getMachine() {
        return EnumMultiMachine.DOUBLE_ELECTRIC_FURNACE;
    }

    public String getInventoryName() {
        return "Double Electric Furnace";
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
