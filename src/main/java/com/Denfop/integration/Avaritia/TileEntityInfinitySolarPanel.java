package com.Denfop.integration.Avaritia;


import com.Denfop.Config;
import com.Denfop.tiles.base.TileEntitySolarPanel;


public class TileEntityInfinitySolarPanel extends TileEntitySolarPanel {
    public TileEntityInfinitySolarPanel() {
        super("blockInfinitySolarPanel.name", 10, 0, Config.InfinityGenDay, Config.InfinityGenNight, Config.InfinityOutput, Config.InfinityStorage);
    }
}