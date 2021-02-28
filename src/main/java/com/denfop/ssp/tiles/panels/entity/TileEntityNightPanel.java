package com.denfop.ssp.tiles.panels.entity;


import com.denfop.ssp.common.Constants;
import com.denfop.ssp.tiles.panels.entity.BasePanelTE.GenerationState;

import ic2.core.init.Localization;
import ic2.core.util.Util;

import javax.annotation.Nonnull;

public abstract class TileEntityNightPanel extends BasePanelTE {
	protected final int nightPower;

	public TileEntityNightPanel(SolarConfig config) {
		super(config);
		this.nightPower = config.nightPower;
	}

	@Nonnull
	@Override
	protected String getGuiDef() {
		return "solar_panel_night";
	}

	protected void updateEntityServer() {
		super.updateEntityServer();

		switch (this.active) {
			case NIGHT:
			tryGenerateEnergy((int) (this.nightPower+((this.nightPower)*0.2*this.chargeSlots.GenNight())));
			break;
		case NIGHTRAIN:
			tryGenerateEnergy((int) ((this.nightPower+((this.nightPower)*0.2*this.chargeSlots.GenNight()))*0.65));
			break;	

	}
		if (this.storage > 0)
			this.storage = (int) (this.storage - this.chargeSlots.charge(this.storage));
	}

	public boolean getGuiState(String name) {
		if ("moonlight".equals(name))
			return (this.active == GenerationState.NIGHT);
		if ("module2".equals(name))
			return (this.chargeSlots.module_generate_night() == true);
		if ("moonlightrain".equals(name))
			return (this.active == GenerationState.NIGHTRAIN);
		

		return super.getGuiState(name);
	}

	@Override
	public void checkTheSky() {
		 if(canSeeSky(this.pos.up()) && !this.world.isDaytime() &&!(this.canRain && (this.world.isRaining() || this.world.isThundering()))) {
			this.active = GenerationState.NIGHT;
		
		}else if(canSeeSky(this.pos.up()) && !this.world.isDaytime() && (this.canRain && (this.world.isRaining() || this.world.isThundering()))) {
			this.active = GenerationState.NIGHTRAIN;
		}else {
			this.active = GenerationState.NONE;
		}
	}

	@Override
	public String getOutput() {
		switch (this.active) {
			case NIGHT:
			return String.format("%s %s %s", Localization.translate(Constants.MOD_ID + ".gui.generating"), Util.toSiString(this.nightPower+((this.nightPower)*0.2*this.chargeSlots.GenNight()), 3), Localization.translate("ic2.generic.text.EUt"));
			case NIGHTRAIN:
			return String.format("%s %s %s", Localization.translate(Constants.MOD_ID + ".gui.generating"), Util.toSiString( (this.nightPower+((this.nightPower)*0.2*this.chargeSlots.GenNight()))*0.65, 3), Localization.translate("ic2.generic.text.EUt"));

	}
	return String.format("%s 0 %s", Localization.translate(Constants.MOD_ID + ".gui.generating"), Localization.translate("ic2.generic.text.EUt"));
	}

	public static class SolarConfig extends BasePanelTE.SolarConfig {

		private final int nightPower;

		public SolarConfig(int nightPower, int maxStorage, int tier) {
			super(maxStorage, tier);
			this.nightPower = nightPower;
		}
	}
}
