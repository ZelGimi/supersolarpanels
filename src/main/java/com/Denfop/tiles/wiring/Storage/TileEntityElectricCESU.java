package com.Denfop.tiles.wiring.Storage;

import com.Denfop.tiles.base.TileEntityElectricBlock;

public class TileEntityElectricCESU extends TileEntityElectricBlock {
    public TileEntityElectricCESU() {
        super(2, 128, 300000);
    }

    public String getInventoryName() {
        return "CESU";
    }
}
