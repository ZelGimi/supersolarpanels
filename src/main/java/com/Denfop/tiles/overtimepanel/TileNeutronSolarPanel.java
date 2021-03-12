package com.Denfop.tiles.overtimepanel;


import com.Denfop.Config;
import com.Denfop.tiles.base.TileEntitySolarPanel;


public class TileNeutronSolarPanel extends TileEntitySolarPanel {
    public TileNeutronSolarPanel() {
        super("blockNeutronSolarPanel.name", 10, 0, Config.neutronpanelGenDay, Config.neutronpanelGenNight, Config.neutronpanelOutput, Config.neutronpanelStorage);
    }
}