package com.Denfop.integration.GC;

import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.planets.asteroids.ConfigManagerAsteroids;
import micdoodle8.mods.galacticraft.planets.mars.ConfigManagerMars;
import net.minecraft.world.World;

public class GalacticraftIntegration {


    public static double GetPlanetefficienty(int i, World world) {
        if (i == ConfigManagerCore.idDimensionMoon) {
            return 1.1;
        } else if (i == ConfigManagerMars.dimensionIDMars) {
            return 1.2;
        } else if (i == ConfigManagerAsteroids.dimensionIDAsteroids) {
            return 0.9;
        } else {
            return 1;
        }


    }
}
