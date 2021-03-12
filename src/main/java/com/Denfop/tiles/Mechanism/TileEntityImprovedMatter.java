package com.Denfop.tiles.Mechanism;

import com.Denfop.tiles.base.TileEntityMultiMatter;

public class TileEntityImprovedMatter extends TileEntityMultiMatter {

    public TileEntityImprovedMatter() {
        super(666666.6F, 8);
    }

    @Override
    public String getInventoryName() {
        return "Improved Mass Fabricator";
    }

}
