package com.Denfop.tiles.overtimepanel;

import com.Denfop.Config;
import com.Denfop.tiles.base.TileEntitySolarPanel;


public class TileEntityAdvancedSolarPanel extends TileEntitySolarPanel {
    public TileEntityAdvancedSolarPanel() {
        super("blockAdvancedSolarPanel.name", 1, 0, Config.advGenDay, Config.advGenNight, Config.advOutput, Config.advStorage);
    }

    @Override
    public String getInvName() {
        return "Adv Solar Panel";
    }
}
