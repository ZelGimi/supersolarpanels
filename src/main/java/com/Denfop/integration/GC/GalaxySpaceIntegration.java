package com.Denfop.integration.GC;


import galaxyspace.core.configs.GSConfigDimensions;
import net.minecraft.world.World;

public class GalaxySpaceIntegration {

    public static double GetPlanetefficienty(int i, World world) {
        if (world.isRemote)
            return 1;
        if (i == GSConfigDimensions.dimensionIDMercury) {
            return 2;
        }
        if (i == GSConfigDimensions.dimensionIDVenus) {
            return 1.2;
        }
        if (i == GSConfigDimensions.dimensionIDVenusOrbit) {
            return 1.2;
        }
        if (i == GSConfigDimensions.dimensionIDVenusOrbitStatic) {
            return 1.2;
        }
        if (i == GSConfigDimensions.dimensionIDDeimos) {
            return 1.1;
        }
        if (i == GSConfigDimensions.dimensionIDPhobos) {
            return 1.1;
        }
        if (i == GSConfigDimensions.dimensionIDCeres) {
            return 0.8;
        }
        if (i == GSConfigDimensions.dimensionIDIo) {
            return 0.7;
        }

        if (i == GSConfigDimensions.dimensionIDEuropa) {
            return 0.7;
        }
        if (i == GSConfigDimensions.dimensionIDCallisto) {
            return 0.7;
        }
        if (i == GSConfigDimensions.dimensionIDGanymede) {
            return 0.7;
        }
        if (i == GSConfigDimensions.dimensionIDEnceladus) {
            return 0.5;
        }
        if (i == GSConfigDimensions.dimensionIDTitan) {
            return 0.5;
        }
        if (i == GSConfigDimensions.dimensionIDMiranda) {
            return 0.4;
        }

        if (i == GSConfigDimensions.dimensionIDTriton) {
            return 0.2;
        }
        if (i == GSConfigDimensions.dimensionIDPluto) {
            return 0.1;
        }
        if (i == GSConfigDimensions.dimensionIDKuiperBelt) {
            return 0.1;
        }
        if (i == GSConfigDimensions.dimensionIDHaumea) {
            return 0.05;
        } else {
            return 1;
        }


    }
}
