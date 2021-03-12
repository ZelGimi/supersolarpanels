package com.Denfop.integration.Botania;

import com.Denfop.Config;

public class modules1 {
    public static int GenDay(int meta) {

        if (meta == 0) {
            return Config.manasteelgenday;
        } else if (meta == 1) {
            return Config.elementiumgenday;
        } else if (meta == 2) {
            return Config.terasteelgenday;
        }

        return 0;

    }

    public static int GenNight(int meta) {

        if (meta == 0) {
            return Config.manasteelgennight;
        } else if (meta == 1) {
            return Config.elementiumgennight;
        } else if (meta == 2) {
            return Config.terasteelgennight;
        }
        return 0;

    }

    public static int Output(int meta) {

        if (meta == 0) {
            return Config.manasteeloutput;
        } else if (meta == 1) {
            return Config.elementiumoutput;
        } else if (meta == 2) {
            return Config.terasteeloutput;
        }

        return 0;

    }

    public static int storage(int meta) {

        if (meta == 0) {
            return Config.manasteelstorage;
        } else if (meta == 1) {
            return Config.elementiumstorage;
        } else if (meta == 2) {
            return Config.terasteelstorage;
        }
        return 0;

    }
}
