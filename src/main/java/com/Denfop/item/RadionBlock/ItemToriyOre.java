package com.Denfop.item.RadionBlock;

import com.Denfop.IUCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.IC2Potion;
import ic2.core.item.armor.ItemArmorHazmat;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ItemToriyOre extends ItemBlock {
    private final List<String> itemNames;
    private final int radiationLength;
    private final int amplifier;

    public ItemToriyOre(final Block b) {
        super(b);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.itemNames = new ArrayList<String>();
        this.addItemsNames();
        this.setCreativeTab(IUCore.tabssp);
        this.radiationLength = 10;
        this.amplifier = 100;
    }

    public int getMetadata(final int i) {
        return i;
    }

    public void onUpdate(ItemStack stack, World world, Entity entity, int slotIndex, boolean isCurrentItem) {
        if (entity instanceof EntityLivingBase) {
            EntityLivingBase entityLiving = (EntityLivingBase) entity;
            if (!ItemArmorHazmat.hasCompleteHazmat(entityLiving))
                IC2Potion.radiation.applyTo(entityLiving, 200, 100);
        }
    }

    public String getUnlocalizedName(final ItemStack itemstack) {
        return this.itemNames.get(itemstack.getItemDamage());
    }

    public void addItemsNames() {
        this.itemNames.add("ToriyBlock");
    }

    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(final ItemStack itemstack) {
        final int i = itemstack.getItemDamage();
        switch (i) {
            case 0: {
                return EnumRarity.epic;
            }


            default: {
                return EnumRarity.uncommon;
            }
        }
    }
}
