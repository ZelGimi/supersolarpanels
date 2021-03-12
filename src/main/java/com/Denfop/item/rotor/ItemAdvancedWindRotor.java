package com.Denfop.item.rotor;

import com.Denfop.IUCore;
import com.Denfop.item.base.ReactorItemCore;
import ic2.api.item.IKineticRotor;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemAdvancedWindRotor extends ReactorItemCore implements IKineticRotor {
    private final int maxWindStrength;

    private final int minWindStrength;

    private final int radius;

    private final float efficiency;

    private final ResourceLocation renderTexture;

    private final boolean water;

    public ItemAdvancedWindRotor(String internalName, int Radius, int durability, float efficiency, int minWindStrength, int maxWindStrength, ResourceLocation RenderTexture) {
        super(internalName, durability);
        setMaxStackSize(1);
        setMaxDamage(durability);
        this.radius = Radius;
        this.efficiency = efficiency;
        this.renderTexture = RenderTexture;
        this.minWindStrength = minWindStrength;
        this.maxWindStrength = maxWindStrength;
        this.water = false;
        this.setCreativeTab(IUCore.tabssp3);
    }

    public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean b) {
        info.add(StatCollector.translateToLocalFormatted("ic2.itemrotor.wind.info", Integer.valueOf(this.minWindStrength), Integer.valueOf(this.maxWindStrength)));
        IKineticRotor.GearboxType type = null;
        if ((Minecraft.getMinecraft()).currentScreen != null && (Minecraft.getMinecraft()).currentScreen instanceof ic2.core.block.kineticgenerator.gui.GuiWaterKineticGenerator) {
            type = IKineticRotor.GearboxType.WATER;
        } else if ((Minecraft.getMinecraft()).currentScreen != null && (Minecraft.getMinecraft()).currentScreen instanceof ic2.core.block.kineticgenerator.gui.GuiWindKineticGenerator) {
            type = IKineticRotor.GearboxType.WIND;
        }
        if (type != null)
            info.add(StatCollector.translateToLocal("ic2.itemrotor.fitsin." + isAcceptedType(itemStack, type)));
    }

    public String getTextureFolder() {
        return null;
    }

    public int getDiameter(ItemStack stack) {
        return this.radius;
    }

    public ResourceLocation getRotorRenderTexture(ItemStack stack) {
        return this.renderTexture;
    }

    public float getEfficiency(ItemStack stack) {
        return this.efficiency;
    }

    public int getMinWindStrength(ItemStack stack) {
        return this.minWindStrength;
    }

    public int getMaxWindStrength(ItemStack stack) {
        return this.maxWindStrength;
    }

    public boolean isAcceptedType(ItemStack stack, IKineticRotor.GearboxType type) {
        return (type == IKineticRotor.GearboxType.WIND || this.water);
    }
}
