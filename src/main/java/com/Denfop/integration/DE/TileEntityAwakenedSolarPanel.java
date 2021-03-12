package com.Denfop.integration.DE;

import com.Denfop.Config;
import com.Denfop.tiles.base.TileEntitySolarPanel;


public class TileEntityAwakenedSolarPanel extends TileEntitySolarPanel {
    public TileEntityAwakenedSolarPanel() {
        super("blockAwakenedSolarPanel.name", Config.awakenedtier, 0, Config.awakenedgenday, Config.awakenedgennight, Config.awakenedoutput, Config.awakenedstorage);
    }
}
