package com.Denfop.integration.DE;

import com.Denfop.Config;

public class modules {
    public static int GenDay(int meta) {

        if (meta == 0) {
            return Config.draconicgenday;
        } else if (meta == 1) {
            return Config.awakenedgenday;
        } else if (meta == 2) {
            return Config.chaosgenday;
        }

        return 0;

    }

    public static int GenNight(int meta) {

        if (meta == 0) {
            return Config.draconicgennight;
        } else if (meta == 1) {
            return Config.awakenedgennight;
        } else if (meta == 2) {
            return Config.chaosgennight;
        }
        return 0;

    }

    public static int Output(int meta) {

        if (meta == 0) {
            return Config.draconicoutput;
        } else if (meta == 1) {
            return Config.awakenedoutput;
        } else if (meta == 2) {
            return Config.chaosoutput;
        }

        return 0;

    }

    public static int storage(int meta) {

        if (meta == 0) {
            return Config.draconicstorage;
        } else if (meta == 1) {
            return Config.awakenedstorage;
        } else if (meta == 2) {
            return Config.chaosstorage;
        }
        return 0;

    }
}
