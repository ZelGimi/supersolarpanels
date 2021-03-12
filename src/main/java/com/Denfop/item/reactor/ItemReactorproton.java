package com.Denfop.item.reactor;

import com.Denfop.IUCore;
import com.Denfop.SSPItem;
import ic2.api.reactor.IReactor;
import net.minecraft.item.ItemStack;

public class ItemReactorproton extends ItemReactorUranium {
    private final int time;
    private final int heat;
    private final float power;

    public ItemReactorproton(String internalName, int cells, int time, int heat, float power) {
        super(internalName, cells, time);
        this.time = time;
        this.heat = heat;
        this.power = power;
        this.setCreativeTab(IUCore.tabssp3);
    }

    protected int getFinalHeat(IReactor reactor, ItemStack stack, int x, int y, int heat) {
        if (reactor.isFluidCooled()) {
            float breedereffectiveness = reactor.getHeat() / reactor.getMaxHeat();
            if (breedereffectiveness > 0.5D)
                heat *= this.heat;
        }
        return heat;
    }

    protected ItemStack getDepletedStack(IReactor reactor, ItemStack stack) {
        ItemStack ret;
        switch (this.numberOfCells) {
            case 1:
                ret = SSPItem.reactorDepletedprotonSimple;
                return new ItemStack(ret.getItem(), 1);
            case 2:
                ret = SSPItem.reactorDepletedprotonDual;
                return new ItemStack(ret.getItem(), 1);
            case 4:
                ret = SSPItem.reactorDepletedprotonQuad;
                return new ItemStack(ret.getItem(), 1);
            case 8:
                ret = SSPItem.reactorDepletedprotoneit;
                return new ItemStack(ret.getItem(), 1);
        }
        throw new RuntimeException("invalid cell count: " + this.numberOfCells);
    }

    public boolean acceptUraniumPulse(IReactor reactor, ItemStack yourStack, ItemStack pulsingStack, int youX, int youY, int pulseX, int pulseY, boolean heatrun) {
        if (!heatrun) {
            float breedereffectiveness = reactor.getHeat() / reactor.getMaxHeat();
            float ReaktorOutput = this.power * breedereffectiveness + 3.0F;
            reactor.addOutput(ReaktorOutput);
        }
        return true;
    }
}
