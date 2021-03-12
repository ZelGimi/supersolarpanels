package com.Denfop.integration.Avaritia;

import com.Denfop.Config;

public class modules2 {
    public static int GenDay(int meta) {

        if (meta == 0) {
            return Config.neutrongenday;
        } else if (meta == 1) {
            return Config.InfinityGenDay;
        }

        return 0;

    }

    public static int GenNight(int meta) {

        if (meta == 0) {
            return Config.neutronGenNight;
        } else if (meta == 1) {
            return Config.InfinityGenNight;
        }

        return 0;

    }

    public static int Output(int meta) {

        if (meta == 0) {
            return Config.neutronOutput;
        } else if (meta == 1) {
            return Config.InfinityOutput;
        }

        return 0;

    }

    public static int storage(int meta) {

        if (meta == 0) {
            return Config.neutronStorage;
        } else if (meta == 1) {
            return Config.InfinityStorage;
        }

        return 0;

    }
}
