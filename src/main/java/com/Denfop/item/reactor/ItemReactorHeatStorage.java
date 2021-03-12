package com.Denfop.item.reactor;

import com.Denfop.IUCore;
import com.Denfop.item.base.ReactorItemCore;
import ic2.api.reactor.IReactor;
import ic2.api.reactor.IReactorComponent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemReactorHeatStorage extends ReactorItemCore implements IReactorComponent {
    public ItemReactorHeatStorage(String internalName, int heatStorage1) {
        super(internalName, heatStorage1);
        this.setCreativeTab(IUCore.tabssp3);
    }

    public void processChamber(IReactor reactor, ItemStack yourStack, int x, int y, boolean heatrun) {
    }

    public boolean acceptUraniumPulse(IReactor reactor, ItemStack yourStack, ItemStack pulsingStack, int youX, int youY, int pulseX, int pulseY, boolean heatrun) {
        return false;
    }

    public boolean canStoreHeat(IReactor reactor, ItemStack yourStack, int x, int y) {
        return true;
    }

    public int getMaxHeat(IReactor reactor, ItemStack stack, int x, int y) {
        return getMaxCustomDamage(stack);
    }

    public int getCurrentHeat(IReactor reactor, ItemStack stack, int x, int y) {
        return getCustomDamage(stack);
    }

    public int alterHeat(IReactor reactor, ItemStack stack, int x, int y, int heat) {
        int myHeat = getCurrentHeat(reactor, stack, x, y);
        myHeat += heat;
        int max = getMaxCustomDamage(stack);
        if (myHeat > max) {
            reactor.setItemAt(x, y, null);
            heat = max - myHeat + 1;
        } else {
            if (myHeat < 0) {
                heat = myHeat;
                myHeat = 0;
            } else {
                heat = 0;
            }
            setCustomDamage(stack, myHeat);
        }
        return heat;
    }

    public float influenceExplosion(IReactor reactor, ItemStack yourStack) {
        return 0.0F;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean b) {
        super.addInformation(stack, player, info, b);
        if (getCustomDamage(stack) > 0) {
            info.add(StatCollector.translateToLocal("ic2.reactoritem.heatwarning.line1"));
            info.add(StatCollector.translateToLocal("ic2.reactoritem.heatwarning.line2"));
        }
    }
}
