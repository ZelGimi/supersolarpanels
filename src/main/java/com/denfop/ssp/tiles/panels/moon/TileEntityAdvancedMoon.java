package com.denfop.ssp.tiles.panels.moon;

import com.denfop.ssp.tiles.panels.entity.TileEntityNightPanel;
import com.denfop.ssp.tiles.panels.entity.TileEntitySunPanel;

public class TileEntityAdvancedMoon extends TileEntityNightPanel {
	public static SolarConfig settings;

	public TileEntityAdvancedMoon() {
		super(TileEntityAdvancedMoon.settings);
	}
}
