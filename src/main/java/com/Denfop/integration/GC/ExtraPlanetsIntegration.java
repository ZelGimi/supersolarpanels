package com.Denfop.integration.GC;

import com.mjr.extraplanets.Config;
import net.minecraft.world.World;

public class ExtraPlanetsIntegration {


    public static double GetPlanetefficienty(int i, World world) {
        if (world.isRemote)
            return 1;
        if (i == Config.mercuryID) {
            return 2;
        } else if (i == Config.venusID) {
            return 1.5;
        } else if (i == Config.ceresID) {
            return 0.8;
        } else if (i == Config.deimosID) {
            return 1.1;
        } else if (i == Config.phobosID) {
            return 1.1;
        } else if (i == Config.plutoID) {
            return 0.1;
        } else if (i == Config.ioID) {
            return 0.7;
        } else if (i == Config.callistoID) {
            return 0.7;
        } else if (i == Config.europaID) {
            return 0.7;
        } else if (i == Config.ganymedeID) {
            return 0.7;
        } else if (i == Config.jupiterID) {
            return 0.7;
        } else if (i == Config.saturnID) {
            return 0.5;
        } else if (i == Config.rheaID) {
            return 0.5;
        } else if (i == Config.titanID) {
            return 0.5;
        } else if (i == Config.iapetusID) {
            return 0.5;
        } else if (i == Config.oberonID) {
            return 0.4;
        } else if (i == Config.uranusID) {
            return 0.4;
        } else if (i == Config.titaniaID) {
            return 0.4;
        } else if (i == Config.neptuneID) {
            return 0.2;
        } else if (i == Config.tritonID) {
            return 0.2;
        } else if (i == Config.erisID) {
            return 0.05;
        } else if (i == Config.kepler22bID) {
            return 1.2;
        } else {
            return 1;
        }

    }


}
