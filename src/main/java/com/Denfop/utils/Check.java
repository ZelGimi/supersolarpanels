package com.Denfop.utils;

import com.Denfop.proxy.CommonProxy;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.LoaderException;

public class Check {


    public static void check() {
        if (Loader.isModLoaded("VictoryAnchors")) {
            CommonProxy.throwInitException(new LoaderException(""));

        } else if (Loader.isModLoaded("als")) {
            CommonProxy.throwInitException(new LoaderException(""));
        } else if (Loader.isModLoaded("VictorySolarPanel")) {
            CommonProxy.throwInitException(new LoaderException(""));
        } else if (Loader.isModLoaded("VictorySolarSynthesis")) {
            CommonProxy.throwInitException(new LoaderException(""));
        } else if (Loader.isModLoaded("VictorySolarSynthesis")) {
            CommonProxy.throwInitException(new LoaderException(""));
        } else if (Loader.isModLoaded("MPDuels")) {
            CommonProxy.throwInitException(new LoaderException(""));
        }
    }
}
