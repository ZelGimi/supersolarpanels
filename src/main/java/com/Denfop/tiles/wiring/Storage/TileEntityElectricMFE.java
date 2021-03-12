package com.Denfop.tiles.wiring.Storage;

import com.Denfop.Config;
import com.Denfop.tiles.base.TileEntityElectricBlock;


public class TileEntityElectricMFE extends TileEntityElectricBlock {
    public TileEntityElectricMFE() {
        super(Config.tier3_MFSU, Config.enegry1, Config.storage1);
    }

    public String getInventoryName() {
        return "MFES";
    }
}
