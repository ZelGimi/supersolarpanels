package com.Denfop.integration.Botania;

import com.Denfop.Config;
import com.Denfop.tiles.base.TileEntitySolarPanel;


public class TileEntityElementumSolarPanel extends TileEntitySolarPanel {
    public TileEntityElementumSolarPanel() {
        super("blockElementumSolarPanel.name", Config.elementiumtier, 0, Config.elementiumgenday, Config.elementiumgennight, Config.elementiumoutput, Config.elementiumstorage);
    }

    @Override
    public String getInvName() {
        return "Elementum Solar Panel";
    }
}
