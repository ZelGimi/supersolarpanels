package com.Denfop.tiles.wiring.Storage;

import com.Denfop.tiles.base.TileEntityElectricBlock;

public class TileEntityElectricMFEMin extends TileEntityElectricBlock {
    public TileEntityElectricMFEMin() {
        super(3, 512, 4000000);
    }

    public String getInventoryName() {
        return "MFE";
    }
}
