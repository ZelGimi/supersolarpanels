package com.denfop.ssp.tiles.panels.moon;

import com.denfop.ssp.tiles.panels.entity.TileEntityNightPanel;
import com.denfop.ssp.tiles.panels.entity.TileEntitySunPanel;

public class TileEntityPhotonicMoon extends TileEntityNightPanel {
	public static TileEntityNightPanel.SolarConfig settings;

	public TileEntityPhotonicMoon() {
		super(TileEntityPhotonicMoon.settings);
	}


}
