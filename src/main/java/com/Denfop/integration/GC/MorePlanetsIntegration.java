package com.Denfop.integration.GC;


import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;

public class MorePlanetsIntegration {

    public static double GetPlanetefficienty(int i, World world) {
        if (world.isRemote)
            return 1;
        if (i == ConfigManagerMP.idDimensionMercury) {
            return 2;
        } else if (i == ConfigManagerMP.idDimensionVenus) {
            return 1.5;
        } else if (i == ConfigManagerMP.idDimensionKapteynB) {
            return 1.2;
        } else if (i == ConfigManagerMP.idDimensionFronos) {
            return 0.8;
        } else if (i == ConfigManagerMP.idDimensionNibiru) {
            return 0.6;
        } else if (i == ConfigManagerMP.idDimensionPolongnius) {
            return 0.5;
        } else if (i == ConfigManagerMP.idDimensionDiona) {
            return 0.3;
        } else if (i == ConfigManagerMP.idDimensionSiriusB) {
            return 2;
        } else {
            return 1;
        }


    }
}
