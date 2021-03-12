package com.Denfop.integration.Avaritia;


import com.Denfop.Config;
import com.Denfop.tiles.base.TileEntitySolarPanel;


public class TileEntityNeutronSolarPanel extends TileEntitySolarPanel {
    public TileEntityNeutronSolarPanel() {
        super("blockNeutronSolarPanel.name", Config.tier, 0, Config.neutrongenday, Config.neutronGenNight, Config.neutronOutput, Config.neutronStorage);
    }
}
