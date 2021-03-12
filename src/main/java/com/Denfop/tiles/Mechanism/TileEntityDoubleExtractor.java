package com.Denfop.tiles.Mechanism;

import com.Denfop.InvSlot.InvSlotProcessableMultiGeneric;
import com.Denfop.tiles.base.TileEntityMultiMachine;
import ic2.api.recipe.Recipes;
import ic2.core.upgrade.UpgradableProperty;

import java.util.EnumSet;
import java.util.Set;

public class TileEntityDoubleExtractor extends TileEntityMultiMachine {
    public TileEntityDoubleExtractor() {
        super();
        this.inputSlots = new InvSlotProcessableMultiGeneric(this, "input", 2, Recipes.extractor);
    }

    @Override
    protected EnumMultiMachine getMachine() {
        return EnumMultiMachine.DOUBLE_EXTRACTOR;
    }

    public String getInventoryName() {
        return "Double Extractor";
    }

    public String getStartSoundFile() {
        return "Machines/ExtractorOp.ogg";
    }

    public String getInterruptSoundFile() {
        return "Machines/InterruptOne.ogg";
    }

    public float getWrenchDropRate() {
        return 0.85F;
    }

    public Set<UpgradableProperty> getUpgradableProperties() {
        return EnumSet.of(UpgradableProperty.Processing,
                UpgradableProperty.RedstoneSensitive, UpgradableProperty.Transformer,
                UpgradableProperty.EnergyStorage, UpgradableProperty.ItemConsuming,
                UpgradableProperty.ItemProducing);
    }

}
