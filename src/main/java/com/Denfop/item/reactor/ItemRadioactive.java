package com.Denfop.item.reactor;

import com.Denfop.Constants;
import com.Denfop.IUCore;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.IC2Potion;
import ic2.core.item.armor.ItemArmorHazmat;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemRadioactive extends Item {
    protected final int radiationLength;

    protected final int amplifier;
    private int rarity;

    public ItemRadioactive(String name, int radiationLength1, int amplifier1) {
        super();
        this.rarity = 0;
        this.radiationLength = radiationLength1;
        this.amplifier = amplifier1;
        setUnlocalizedName(name);
        this.setCreativeTab(IUCore.tabssp3);
        this.setTextureName(Constants.TEXTURES_MAIN + name);
        GameRegistry.registerItem(this, name);

    }

    public void onUpdate(ItemStack stack, World world, Entity entity, int slotIndex, boolean isCurrentItem) {
        if (entity instanceof EntityLivingBase) {
            EntityLivingBase entityLiving = (EntityLivingBase) entity;
            if (!ItemArmorHazmat.hasCompleteHazmat(entityLiving))
                IC2Potion.radiation.applyTo(entityLiving, 200, 100);
        }
    }

    public String getUnlocalizedName() {
        return "ssp." + super.getUnlocalizedName().substring(5);
    }

    public String getUnlocalizedName(ItemStack itemStack) {
        return getUnlocalizedName();
    }

    public String getItemStackDisplayName(ItemStack itemStack) {
        return StatCollector.translateToLocal(getUnlocalizedName(itemStack));
    }

    public ItemRadioactive setRarity(int aRarity) {
        this.rarity = aRarity;
        return this;
    }

    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.values()[this.rarity];
    }
}
