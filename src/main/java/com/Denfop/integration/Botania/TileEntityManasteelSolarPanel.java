package com.Denfop.integration.Botania;

import com.Denfop.Config;
import com.Denfop.tiles.base.TileEntitySolarPanel;


public class TileEntityManasteelSolarPanel extends TileEntitySolarPanel {
    public TileEntityManasteelSolarPanel() {
        super("blockManasteelSolarPanel.name", Config.manasteeltier, 0, Config.manasteelgenday, Config.manasteelgennight, Config.manasteeloutput, Config.manasteelstorage);
    }

    @Override
    public String getInvName() {
        return "Manasteel Solar Panel";
    }
}
