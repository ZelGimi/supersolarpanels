package com.denfop.ssp.tiles.panels.moon;

import com.denfop.ssp.tiles.panels.entity.TileEntitySunPanel;

public class TileEntityQuantumSolarMoon extends TileEntitySunPanel {
	public static SolarConfig settings;

	public TileEntityQuantumSolarMoon() {
		super(TileEntityQuantumSolarMoon.settings);
	}
}
