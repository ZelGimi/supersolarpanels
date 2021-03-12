package com.Denfop.integration.DE;

import com.Denfop.Config;
import com.Denfop.tiles.base.TileEntitySolarPanel;

public class TileEntityChaosSolarPanel extends TileEntitySolarPanel {
    public TileEntityChaosSolarPanel() {
        super("blockChaosSolarPanel.name", Config.chaostier, 0, Config.chaosgenday, Config.chaosgennight, Config.chaosoutput, Config.chaosstorage);
    }
}
