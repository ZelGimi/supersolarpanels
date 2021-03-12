package com.Denfop.tiles.Mechanism;

import com.Denfop.InvSlot.InvSlotProcessableMultiGeneric;
import com.Denfop.tiles.base.TileEntityMultiMachine;
import ic2.api.recipe.Recipes;
import ic2.core.upgrade.UpgradableProperty;

import java.util.EnumSet;
import java.util.Set;

public class TileEntityTripleCompressor extends TileEntityMultiMachine {
    public TileEntityTripleCompressor() {
        super();
        this.inputSlots = new InvSlotProcessableMultiGeneric(this, "input", sizeWorkingSlot, Recipes.compressor);
    }

    @Override
    protected EnumMultiMachine getMachine() {
        return EnumMultiMachine.TRIPLE_COMPRESSER;
    }

    public String getInventoryName() {
        return "Triple Compressor";
    }

    public String getStartSoundFile() {
        return "Machines/CompressorOp.ogg";
    }

    public String getInterruptSoundFile() {
        return "Machines/InterruptOne.ogg";
    }

    public float getWrenchDropRate() {
        return 0.85F;
    }

    public Set<UpgradableProperty> getUpgradableProperties() {
        return EnumSet.of(UpgradableProperty.Processing, UpgradableProperty.Transformer,
                UpgradableProperty.EnergyStorage, UpgradableProperty.ItemConsuming, UpgradableProperty.ItemProducing);
    }

}
