package com.Denfop.tiles.wiring.Storage;

import com.Denfop.tiles.base.TileEntityElectricBlock;

public class TileEntityElectricMFSUMin extends TileEntityElectricBlock {
    public TileEntityElectricMFSUMin() {
        super(4, 2048, 40000000);
    }

    public String getInventoryName() {
        return "MFSU";
    }
}
