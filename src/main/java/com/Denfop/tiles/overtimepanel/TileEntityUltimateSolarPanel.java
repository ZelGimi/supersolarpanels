package com.Denfop.tiles.overtimepanel;

import com.Denfop.Config;
import com.Denfop.tiles.base.TileEntitySolarPanel;


public class TileEntityUltimateSolarPanel extends TileEntitySolarPanel {
    public TileEntityUltimateSolarPanel() {
        super("blockUltimateSolarPanel.name", 3, 0, Config.uhGenDay, Config.uhGenNight, Config.uhOutput, Config.uhStorage);
    }

    @Override
    public String getInvName() {
        return "Ult Solar Panel";
    }
}
