package com.Denfop.integration.DE;

import com.Denfop.Config;
import com.Denfop.tiles.base.TileEntitySolarPanel;


public class TileEntityDraconSolarPanel extends TileEntitySolarPanel {
    public TileEntityDraconSolarPanel() {
        super("blockDraconSolarPanel.name", Config.draconictier, 0, Config.draconicgenday, Config.draconicgennight, Config.draconicoutput, Config.draconicstorage);
    }

    @Override
    public String getInvName() {
        return "Dracon Solar Panel";
    }
}
