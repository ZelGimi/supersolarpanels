package com.Denfop.item.reactor;

import com.Denfop.IUCore;
import com.Denfop.item.base.ReactorItemCore;
import ic2.api.reactor.IReactor;
import ic2.api.reactor.IReactorComponent;
import ic2.core.IC2Potion;
import ic2.core.Ic2Items;
import ic2.core.item.armor.ItemArmorHazmat;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

public class ItemReactorUranium extends ReactorItemCore implements IReactorComponent {
    public final int numberOfCells;

    public ItemReactorUranium(String internalName, int cells) {
        this(internalName, cells, 20000);
    }

    protected ItemReactorUranium(String internalName, int cells, int duration) {
        super(internalName, duration);
        setMaxStackSize(64);
        this.numberOfCells = cells;
        this.setCreativeTab(IUCore.tabssp3);
    }

    protected static int checkPulseable(IReactor reactor, int x, int y, ItemStack me, int mex, int mey, boolean heatrun) {
        ItemStack other = reactor.getItemAt(x, y);
        if (other != null && other.getItem() instanceof IReactorComponent && (
                (IReactorComponent) other.getItem()).acceptUraniumPulse(reactor, other, me, x, y, mex, mey, heatrun))
            return 1;
        return 0;
    }

    protected static int triangularNumber(int x) {
        return (x * x + x) / 2;
    }

    public void processChamber(IReactor reactor, ItemStack stack, int x, int y, boolean heatRun) {
        if (!reactor.produceEnergy())
            return;
        int basePulses = 1 + this.numberOfCells / 2;
        for (int iteration = 0; iteration < this.numberOfCells; iteration++) {
            int pulses = basePulses;
            if (!heatRun) {
                for (int i = 0; i < pulses; i++)
                    acceptUraniumPulse(reactor, stack, stack, x, y, x, y, heatRun);
                pulses += checkPulseable(reactor, x - 1, y, stack, x, y, heatRun) +
                        checkPulseable(reactor, x + 1, y, stack, x, y, heatRun) +
                        checkPulseable(reactor, x, y - 1, stack, x, y, heatRun) +
                        checkPulseable(reactor, x, y + 1, stack, x, y, heatRun);
            } else {
                pulses += checkPulseable(reactor, x - 1, y, stack, x, y, heatRun) +
                        checkPulseable(reactor, x + 1, y, stack, x, y, heatRun) +
                        checkPulseable(reactor, x, y - 1, stack, x, y, heatRun) +
                        checkPulseable(reactor, x, y + 1, stack, x, y, heatRun);
                int heat = triangularNumber(pulses) * 4;
                heat = getFinalHeat(reactor, stack, x, y, heat);
                Queue<ItemStackCoord> heatAcceptors = new ArrayDeque<ItemStackCoord>();
                checkHeatAcceptor(reactor, x - 1, y, heatAcceptors);
                checkHeatAcceptor(reactor, x + 1, y, heatAcceptors);
                checkHeatAcceptor(reactor, x, y - 1, heatAcceptors);
                checkHeatAcceptor(reactor, x, y + 1, heatAcceptors);
                while (!heatAcceptors.isEmpty() && heat > 0) {
                    int dheat = heat / heatAcceptors.size();
                    heat -= dheat;
                    ItemStackCoord acceptor = heatAcceptors.remove();
                    IReactorComponent acceptorComp = (IReactorComponent) acceptor.stack.getItem();
                    dheat = acceptorComp.alterHeat(reactor, acceptor.stack, acceptor.x, acceptor.y, dheat);
                    heat += dheat;
                }
                if (heat > 0)
                    reactor.addHeat(heat);
            }
        }
        if (getCustomDamage(stack) >= getMaxCustomDamage(stack) - 1) {
            reactor.setItemAt(x, y, getDepletedStack(reactor, stack));
        } else if (heatRun) {
            applyCustomDamage(stack, 1, null);
        }
    }

    protected int getFinalHeat(IReactor reactor, ItemStack stack, int x, int y, int heat) {
        return heat;
    }

    protected ItemStack getDepletedStack(IReactor reactor, ItemStack stack) {
        ItemStack ret;
        switch (this.numberOfCells) {
            case 1:
                ret = Ic2Items.reactorDepletedUraniumSimple;
                return new ItemStack(ret.getItem(), 1);
            case 2:
                ret = Ic2Items.reactorDepletedUraniumDual;
                return new ItemStack(ret.getItem(), 1);
            case 4:
                ret = Ic2Items.reactorDepletedUraniumQuad;
                return new ItemStack(ret.getItem(), 1);
        }
        throw new RuntimeException("invalid cell count: " + this.numberOfCells);
    }

    protected void checkHeatAcceptor(IReactor reactor, int x, int y, Collection<ItemStackCoord> heatAcceptors) {
        ItemStack thing = reactor.getItemAt(x, y);
        if (thing != null && thing.getItem() instanceof IReactorComponent && (
                (IReactorComponent) thing.getItem()).canStoreHeat(reactor, thing, x, y))
            heatAcceptors.add(new ItemStackCoord(thing, x, y));
    }

    public boolean acceptUraniumPulse(IReactor reactor, ItemStack yourStack, ItemStack pulsingStack, int youX, int youY, int pulseX, int pulseY, boolean heatrun) {
        if (!heatrun)
            reactor.addOutput(1.0F);
        return true;
    }

    public boolean canStoreHeat(IReactor reactor, ItemStack yourStack, int x, int y) {
        return false;
    }

    public int getMaxHeat(IReactor reactor, ItemStack yourStack, int x, int y) {
        return 0;
    }

    public int getCurrentHeat(IReactor reactor, ItemStack yourStack, int x, int y) {
        return 0;
    }

    public int alterHeat(IReactor reactor, ItemStack yourStack, int x, int y, int heat) {
        return heat;
    }

    public float influenceExplosion(IReactor reactor, ItemStack yourStack) {
        return (2 * this.numberOfCells);
    }

    public void onUpdate(ItemStack stack, World world, Entity entity, int slotIndex, boolean isCurrentItem) {
        if (entity instanceof EntityLivingBase) {
            EntityLivingBase entityLiving = (EntityLivingBase) entity;
            if (!ItemArmorHazmat.hasCompleteHazmat(entityLiving))
                IC2Potion.radiation.applyTo(entityLiving, 200, 100);
        }
    }

    private static class ItemStackCoord {
        public final ItemStack stack;

        public final int x;

        public final int y;

        public ItemStackCoord(ItemStack stack, int x, int y) {
            this.stack = stack;
            this.x = x;
            this.y = y;
        }
    }
}
