package com.Denfop.integration.Botania;

import com.Denfop.Config;
import com.Denfop.tiles.base.TileEntitySolarPanel;


public class TileEntityTerrastelSolarPanel extends TileEntitySolarPanel {
    public TileEntityTerrastelSolarPanel() {
        super("blockTerrastelSolarPanel.name", Config.terasteeltier, 0, Config.terasteelgenday, Config.terasteelgennight, Config.terasteeloutput, Config.terasteelstorage);
    }

    @Override
    public String getInvName() {
        return "Terrastel Solar Panel";
    }
}
