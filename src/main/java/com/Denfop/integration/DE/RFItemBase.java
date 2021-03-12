package com.Denfop.integration.DE;

import cofh.api.energy.IEnergyContainerItem;
import com.Denfop.IUCore;
import com.brandon3055.brandonscore.common.utills.InfoHelper;
import com.brandon3055.brandonscore.common.utills.ItemNBTHelper;
import com.brandon3055.brandonscore.common.utills.Utills;
import com.brandon3055.draconicevolution.common.entity.EntityPersistentItem;
import com.brandon3055.draconicevolution.common.utills.IConfigurableItem;
import com.brandon3055.draconicevolution.common.utills.IHudDisplayItem;
import com.brandon3055.draconicevolution.common.utills.ItemConfigField;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class RFItemBase extends ItemDC implements IEnergyContainerItem, IConfigurableItem, IHudDisplayItem {
    protected int capacity = 0;

    protected int maxReceive = 0;

    protected int maxExtract = 0;

    public RFItemBase() {
        setMaxStackSize(1);
        setCreativeTab(IUCore.tabssp);
    }

    public boolean hasCustomEntity(ItemStack stack) {
        return true;
    }

    public Entity createEntity(World world, Entity location, ItemStack itemstack) {
        return new EntityPersistentItem(world, location, itemstack);
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setMaxReceive(int maxReceive) {
        this.maxReceive = maxReceive;
    }

    public void setMaxExtract(int maxExtract) {
        this.maxExtract = maxExtract;
    }

    public int getCapacity(ItemStack stack) {
        return this.capacity;
    }

    public int getMaxExtract(ItemStack stack) {
        return this.maxExtract;
    }

    public int getMaxReceive(ItemStack stack) {
        return this.maxReceive;
    }

    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        int energy = ItemNBTHelper.getInteger(container, "Energy", 0);
        int energyReceived = Math.min(getCapacity(container) - energy, Math.min(getMaxReceive(container), maxReceive));
        if (!simulate) {
            energy += energyReceived;
            ItemNBTHelper.setInteger(container, "Energy", energy);
        }
        return energyReceived;
    }

    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        int energy = ItemNBTHelper.getInteger(container, "Energy", 0);
        int energyExtracted = Math.min(energy, Math.min(getMaxExtract(container), maxExtract));
        if (!simulate) {
            energy -= energyExtracted;
            ItemNBTHelper.setInteger(container, "Energy", energy);
        }
        return energyExtracted;
    }

    public int getEnergyStored(ItemStack container) {
        return ItemNBTHelper.getInteger(container, "Energy", 0);
    }

    public int getMaxEnergyStored(ItemStack container) {
        return getCapacity(container);
    }

    public boolean showDurabilityBar(ItemStack stack) {
        return (getEnergyStored(stack) != getMaxEnergyStored(stack));
    }

    public double getDurabilityForDisplay(ItemStack stack) {
        return 1.0D - getEnergyStored(stack) / getMaxEnergyStored(stack);
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        list.add(ItemNBTHelper.setInteger(new ItemStack(item, 1, 0), "Energy", 0));
        if (this.capacity > 0)
            list.add(ItemNBTHelper.setInteger(new ItemStack(item, 1, 0), "Energy", this.capacity));
    }

    public boolean getHasSubtypes() {
        return (this.capacity > 0);
    }

    public List<ItemConfigField> getFields(ItemStack stack, int slot) {
        return new ArrayList<ItemConfigField>();
    }

    public List<String> getDisplayData(ItemStack stack) {
        List<String> list = new ArrayList<String>();
        if (hasProfiles()) {
            int preset = ItemNBTHelper.getInteger(stack, "ConfigProfile", 0);
            list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("info.de.capacitorMode.txt") + ": " + ItemNBTHelper.getString(stack, "ProfileName" + preset, "Profile " + preset));
        }
        for (ItemConfigField field : getFields(stack, 0))
            list.add(field.getTooltipInfo());
        if (getCapacity(stack) > 0)
            list.add(InfoHelper.ITC() + StatCollector.translateToLocal("info.de.charge.txt") + ": " + InfoHelper.HITC() + Utills.formatNumber(getEnergyStored(stack)) + " / " + Utills.formatNumber(this.capacity));
        return list;
    }

    public boolean hasProfiles() {
        return true;
    }
}
