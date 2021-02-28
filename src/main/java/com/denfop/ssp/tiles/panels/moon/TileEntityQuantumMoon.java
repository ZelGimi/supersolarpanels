package com.denfop.ssp.tiles.panels.moon;

import com.denfop.ssp.tiles.panels.entity.TileEntityNightPanel;
import com.denfop.ssp.tiles.panels.entity.TileEntitySunPanel;

public class TileEntityQuantumMoon extends TileEntityNightPanel {
	public static SolarConfig settings;

	public TileEntityQuantumMoon() {
		super(TileEntityQuantumMoon.settings);
	}
}
