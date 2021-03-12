package com.Denfop.tiles.base;

public class TileEntityAdminSolarPanel extends TileEntitySolarPanel {
    public TileEntityAdminSolarPanel() {
        super("blockAdministatorSolarPanel.name", 11, 0, 999999999, 999999999, 999999999, 999999999);
    }

    @Override
    public String getInvName() {
        return "Manasteel Solar Panel";
    }


}
