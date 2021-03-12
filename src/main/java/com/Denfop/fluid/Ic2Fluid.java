package com.Denfop.fluid;

import net.minecraftforge.fluids.Fluid;

public class Ic2Fluid extends Fluid {
    public Ic2Fluid(String fluidName) {
        super(fluidName);
    }

    public String getUnlocalizedName() {
        return "ssp." + super.getUnlocalizedName().substring(6);
    }
}
