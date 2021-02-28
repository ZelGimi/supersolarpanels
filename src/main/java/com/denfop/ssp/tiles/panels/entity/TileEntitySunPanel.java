package com.denfop.ssp.tiles.panels.entity;


import com.denfop.ssp.common.Constants;
import com.denfop.ssp.tiles.panels.entity.BasePanelTE.GenerationState;

import ic2.core.init.Localization;
import ic2.core.util.Util;

import javax.annotation.Nonnull;

public abstract class TileEntitySunPanel extends BasePanelTE {
	protected final int dayPower;

	public TileEntitySunPanel(SolarConfig config) {
		super(config);
		this.dayPower = config.dayPower;
	}

	@Nonnull
	@Override
	protected String getGuiDef() {
		return "solar_panel_sun";
	}

	protected void updateEntityServer() {
		super.updateEntityServer();

		switch (this.active) {
		case DAY:
			tryGenerateEnergy((int) (this.dayPower+((this.dayPower)*0.2*this.chargeSlots.GenDay())));
			break;
			case DAYRAIN:
			tryGenerateEnergy((int) ((this.dayPower+((this.dayPower)*0.2*this.chargeSlots.GenDay()))*0.65));
			break;
		
	}
		if (this.storage > 0)
			this.storage = (int) (this.storage - this.chargeSlots.charge(this.storage));
	}

	public boolean getGuiState(String name) {
		if ("sunlight".equals(name))
			return (this.active == GenerationState.DAY);
		if ("sunlightrain".equals(name))
			return (this.active == GenerationState.DAYRAIN);
		if ("module1".equals(name))
			return (this.chargeSlots.module_generate_day() == true);

		return super.getGuiState(name);
	}

	@Override
	public void checkTheSky() {
		if(canSeeSky(this.pos.up()) && this.world.isDaytime() &&!(this.canRain && (this.world.isRaining() || this.world.isThundering()))) {
			this.active = GenerationState.DAY;
		
		}else if(canSeeSky(this.pos.up()) && this.world.isDaytime() && (this.canRain && (this.world.isRaining() || this.world.isThundering()))) {
			this.active = GenerationState.DAYRAIN;
		
		}else {
			this.active = GenerationState.NONE;
		}
	}

	@Override
	public String getOutput() {
		switch (this.active) {
		case DAY:
			return String.format("%s %s %s", Localization.translate(Constants.MOD_ID + ".gui.generating"), Util.toSiString(this.dayPower+((this.dayPower)*0.2*this.chargeSlots.GenDay()), 3), Localization.translate("ic2.generic.text.EUt"));
			case DAYRAIN:
			return String.format("%s %s %s", Localization.translate(Constants.MOD_ID + ".gui.generating"), Util.toSiString((this.dayPower+((this.dayPower)*0.2*this.chargeSlots.GenDay()))*0.65, 3), Localization.translate("ic2.generic.text.EUt"));
		
	}
	return String.format("%s 0 %s", Localization.translate(Constants.MOD_ID + ".gui.generating"), Localization.translate("ic2.generic.text.EUt"));
}

	public static class SolarConfig extends BasePanelTE.SolarConfig {

		private final int dayPower;

		public SolarConfig(int dayPower, int maxStorage, int tier) {
			super(maxStorage, tier);
			this.dayPower = dayPower;
		}
	}
}
