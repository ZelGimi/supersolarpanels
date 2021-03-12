package com.Denfop.tiles.overtimepanel;


import com.Denfop.Config;
import com.Denfop.tiles.base.TileEntitySolarPanel;


public class TileAdminSolarPanel extends TileEntitySolarPanel {
    public TileAdminSolarPanel() {
        super("blockAdminSolarPanel.name", 8, 0, Config.adminpanelGenDay, Config.adminpanelGenNight, Config.AdminpanelOutput, Config.AdminpanelStorage);
    }

}
