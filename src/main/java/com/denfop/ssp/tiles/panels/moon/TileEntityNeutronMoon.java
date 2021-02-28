package com.denfop.ssp.tiles.panels.moon;

import com.denfop.ssp.tiles.panels.entity.TileEntityNightPanel;
import com.denfop.ssp.tiles.panels.entity.TileEntitySunPanel;

public class TileEntityNeutronMoon extends TileEntityNightPanel {
	public static TileEntityNightPanel.SolarConfig settings;

	public TileEntityNeutronMoon() {
		super(TileEntityNeutronMoon.settings);
	}
}
