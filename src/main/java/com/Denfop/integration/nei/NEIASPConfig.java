package com.Denfop.integration.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.recipe.ICraftingHandler;
import codechicken.nei.recipe.IUsageHandler;

public class NEIASPConfig implements IConfigureNEI {
    public void loadConfig() {
        System.out.println("[Super Solar Panels]: Loading NEI compatibility...");

        API.registerRecipeHandler(new NEIAlloySmelter());
        API.registerUsageHandler(new NEIAlloySmelter());
        API.registerRecipeHandler(new NEIGenStone());
        API.registerUsageHandler(new NEIGenStone());
        API.registerRecipeHandler(new NeiMolecularTransfomator());
        API.registerUsageHandler(new NeiMolecularTransfomator());
    }

    public String getName() {
        return "Super Solar Panels";
    }

    public String getVersion() {
        return "v1.0";
    }
}
