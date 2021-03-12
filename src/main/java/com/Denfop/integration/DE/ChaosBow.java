package com.Denfop.integration.DE;

import com.Denfop.Constants;
import com.Denfop.IUCore;
import com.brandon3055.brandonscore.BrandonsCore;
import com.brandon3055.brandonscore.common.utills.InfoHelper;
import com.brandon3055.brandonscore.common.utills.ItemNBTHelper;
import com.brandon3055.brandonscore.common.utills.Utills;
import com.brandon3055.draconicevolution.DraconicEvolution;
import com.brandon3055.draconicevolution.common.entity.EntityPersistentItem;
import com.brandon3055.draconicevolution.common.handler.BalanceConfigHandler;
import com.brandon3055.draconicevolution.common.handler.ConfigHandler;
import com.brandon3055.draconicevolution.common.items.tools.baseclasses.ToolBase;
import com.brandon3055.draconicevolution.common.items.weapons.BowHandler;
import com.brandon3055.draconicevolution.common.items.weapons.IEnergyContainerWeaponItem;
import com.brandon3055.draconicevolution.common.utills.IHudDisplayItem;
import com.brandon3055.draconicevolution.common.utills.IInventoryTool;
import com.brandon3055.draconicevolution.common.utills.IUpgradableItem;
import com.brandon3055.draconicevolution.common.utills.ItemConfigField;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ChaosBow extends ItemBow implements IInventoryTool, IUpgradableItem, IEnergyContainerWeaponItem, IHudDisplayItem {
    public static final String[] bowPullIconNameArray = new String[]{"pulling_0", "pulling_1", "pulling_2"};

    protected int capacity = (int) (BalanceConfigHandler.draconicWeaponsBaseStorage * 1.5);

    protected int maxReceive = BalanceConfigHandler.draconicWeaponsMaxTransfer;

    protected int maxExtract = BalanceConfigHandler.draconicWeaponsMaxTransfer;

    @SideOnly(Side.CLIENT)
    private IIcon[] iconArray;

    public ChaosBow() {
        this.maxStackSize = 1;
        setMaxDamage(-1);
        setCreativeTab(IUCore.tabssp2);
        setUnlocalizedName("ChaosBow");
        GameRegistry.registerItem(this, "ChaosBow");
    }

    public boolean isItemTool(ItemStack p_77616_1_) {
        return true;
    }

    public boolean hasEffect(ItemStack par1ItemStack, int pass) {
        return true;
    }

    public String getUnlocalizedName() {
        return String.format("item.%s%s", "supersolarpanel".toLowerCase() + ":", super.getUnlocalizedName().substring(super.getUnlocalizedName().indexOf(".") + 1));
    }

    public String getUnlocalizedName(ItemStack itemStack) {
        return getUnlocalizedName();
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(Constants.TEXTURES + ":" + "ChaosBow" + "_standby");
        this.iconArray = new IIcon[bowPullIconNameArray.length];
        for (int i = 0; i < this.iconArray.length; i++)
            this.iconArray[i] = iconRegister.registerIcon(Constants.TEXTURES + ":" + "ChaosBow" + "_" + bowPullIconNameArray[i]);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        float j = stack.getMaxItemUseDuration() - useRemaining;
        if (usingItem == null)
            return this.itemIcon;
        BowHandler.BowProperties properties = new BowHandler.BowProperties(stack, player);
        if (j > properties.getDrawTicks())
            j = properties.getDrawTicks();
        j /= properties.getDrawTicks();
        int j2 = (int) (j * 2.0F);
        if (j2 < 0) {
            j2 = 0;
        } else if (j2 > 2) {
            j2 = 2;
        }
        return getItemIconForUseDuration(j2);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getItemIconForUseDuration(int par1) {
        return this.iconArray[par1];
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean extraInformation) {
        boolean show = InfoHelper.holdShiftForDetails(list);
        if (show) {
            int preset = ItemNBTHelper.getInteger(stack, "ConfigProfile", 0);
            list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("info.de.capacitorMode.txt") + ": " + ItemNBTHelper.getString(stack, "ProfileName" + preset, "Profile " + preset));
            List<ItemConfigField> l = getFields(stack, 0);
            for (ItemConfigField f : l)
                list.add(f.getTooltipInfo());
        }
        ToolBase.holdCTRLForUpgrades(list, stack);
        InfoHelper.addEnergyInfo(stack, list);
        if (show && !ConfigHandler.disableLore)
            InfoHelper.addLore(stack, list, true);
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        list.add(ItemNBTHelper.setInteger(new ItemStack(item, 1, 0), "Energy", 0));
        list.add(ItemNBTHelper.setInteger(new ItemStack(item, 1, 0), "Energy", this.capacity));
    }

    public boolean getHasSubtypes() {
        return true;
    }

    public boolean showDurabilityBar(ItemStack stack) {
        return (getEnergyStored(stack) != getMaxEnergyStored(stack));
    }

    public double getDurabilityForDisplay(ItemStack stack) {
        return 1.0D - getEnergyStored(stack) / getMaxEnergyStored(stack);
    }

    public boolean hasCustomEntity(ItemStack stack) {
        return true;
    }

    public Entity createEntity(World world, Entity location, ItemStack itemstack) {
        return new EntityPersistentItem(world, location, itemstack);
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        return BowHandler.onBowRightClick(this, stack, world, player);
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        BowHandler.onBowUsingTick(stack, player, count);
    }

    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int count) {
        BowHandler.onPlayerStoppedUsingBow(stack, world, player, count);
    }

    public String getInventoryName() {
        return StatCollector.translateToLocal("info.de.toolInventoryEnch.txt");
    }

    public int getInventorySlots() {
        return 0;
    }

    public boolean isEnchantValid(Enchantment enchant) {
        return (enchant.type == EnumEnchantmentType.bow || enchant.effectId == DraconicEvolution.reaperEnchant.effectId);
    }

    public List<ItemConfigField> getFields(ItemStack stack, int slot) {
        List<ItemConfigField> list = new ArrayList<ItemConfigField>();
        list.add((new ItemConfigField(4, slot, "BowArrowDamage")).setMinMaxAndIncromente(Float.valueOf(getBaseUpgradePoints(IUpgradableItem.EnumUpgrade.ARROW_DAMAGE.index)), Float.valueOf(IUpgradableItem.EnumUpgrade.ARROW_DAMAGE.getUpgradePoints(stack)), Float.valueOf(0.1F)).readFromItem(stack, Float.valueOf(IUpgradableItem.EnumUpgrade.ARROW_DAMAGE.getUpgradePoints(stack))));
        list.add((new ItemConfigField(4, slot, "BowArrowSpeedModifier")).setMinMaxAndIncromente(Float.valueOf(0.0F), Float.valueOf(IUpgradableItem.EnumUpgrade.ARROW_SPEED.getUpgradePoints(stack)), Float.valueOf(0.01F)).readFromItem(stack, Float.valueOf(0.0F)).setModifier("PLUSPERCENT"));
        list.add((new ItemConfigField(6, slot, "BowAutoFire")).readFromItem(stack, Boolean.valueOf(false)));
        list.add((new ItemConfigField(4, slot, "BowExplosionPower")).setMinMaxAndIncromente(Float.valueOf(0.0F), Float.valueOf(6.0F), Float.valueOf(0.1F)).readFromItem(stack, Float.valueOf(0.0F)));
        list.add((new ItemConfigField(4, slot, "BowShockWavePower")).setMinMaxAndIncromente(Float.valueOf(0.0F), Float.valueOf(10.0F), Float.valueOf(0.1F)).readFromItem(stack, Float.valueOf(0.0F)));
        list.add((new ItemConfigField(6, slot, "BowEnergyBolt")).readFromItem(stack, Boolean.valueOf(false)));
        list.add((new ItemConfigField(4, slot, "BowZoomModifier")).setMinMaxAndIncromente(Float.valueOf(0.0F), Float.valueOf(6.0F), Float.valueOf(0.01F)).readFromItem(stack, Float.valueOf(0.0F)).setModifier("PLUSPERCENT"));
        return list;
    }

    public List<IUpgradableItem.EnumUpgrade> getUpgrades(ItemStack itemstack) {
        List<IUpgradableItem.EnumUpgrade> list = new ArrayList<IUpgradableItem.EnumUpgrade>();

        list.add(IUpgradableItem.EnumUpgrade.ARROW_DAMAGE);
        list.add(IUpgradableItem.EnumUpgrade.ARROW_SPEED);
        list.add(IUpgradableItem.EnumUpgrade.DRAW_SPEED);
        list.add(IUpgradableItem.EnumUpgrade.RF_CAPACITY);
        return list;

    }

    public int getUpgradeCap(ItemStack itemstack) {
        return BalanceConfigHandler.draconicBowMaxUpgrades;
    }

    public int getMaxTier(ItemStack itemstack) {
        return 3;
    }

    public int getMaxUpgradePoints(int upgradeIndex) {
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.RF_CAPACITY.index)
            return BalanceConfigHandler.draconicBowMaxCapacityUpgradePoints;
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.DRAW_SPEED.index)
            return 11;
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.ARROW_SPEED.index)
            return 17;
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.ARROW_DAMAGE.index)
            return 50;
        return BalanceConfigHandler.draconicBowMaxUpgradePoints;
    }

    public int getMaxUpgradePoints(int upgradeIndex, ItemStack stack) {
        return getMaxUpgradePoints(upgradeIndex);
    }

    public int getBaseUpgradePoints(int upgradeIndex) {
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.DRAW_SPEED.index)
            return 7;
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.ARROW_SPEED.index)
            return 7;
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.ARROW_DAMAGE.index)
            return 15;
        return 0;
    }

    public List<String> getUpgradeStats(ItemStack stack) {
        BowHandler.BowProperties properties = new BowHandler.BowProperties(stack, null);
        List<String> list = new ArrayList<String>();
        list.add(InfoHelper.ITC() + StatCollector.translateToLocal("gui.de.RFCapacity.txt") + ": " + InfoHelper.HITC() + Utills.formatNumber(getMaxEnergyStored(stack)));
        list.add(InfoHelper.ITC() + StatCollector.translateToLocal("gui.de.max.txt") + " " + StatCollector.translateToLocal("gui.de.ArrowSpeed.txt") + ": " + InfoHelper.HITC() + "+" + (IUpgradableItem.EnumUpgrade.ARROW_SPEED.getUpgradePoints(stack) * 100) + "%");
        list.add(InfoHelper.ITC() + StatCollector.translateToLocal("gui.de.max.txt") + " " + StatCollector.translateToLocal("gui.de.ArrowDamage.txt") + ": " + InfoHelper.HITC() + IUpgradableItem.EnumUpgrade.ARROW_DAMAGE.getUpgradePoints(stack) + "");
        list.add(InfoHelper.ITC() + StatCollector.translateToLocal("gui.de.DrawSpeed.txt") + ": " + InfoHelper.HITC() + (properties.getDrawTicks() / 20.0D) + "s");
        return list;
    }

    public boolean hasProfiles() {
        return true;
    }

    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        int energy = ItemNBTHelper.getInteger(container, "Energy", 0);
        int energyReceived = Math.min(getMaxEnergyStored(container) - energy, Math.min(this.maxReceive, maxReceive));
        if (!simulate) {
            energy += energyReceived;
            ItemNBTHelper.setInteger(container, "Energy", energy);
        }
        return energyReceived;
    }

    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        int energy = ItemNBTHelper.getInteger(container, "Energy", 0);
        int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
        if (!simulate) {
            energy -= energyExtracted;
            ItemNBTHelper.setInteger(container, "Energy", energy);
        }
        return energyExtracted;
    }

    public int getEnergyStored(ItemStack container) {
        return ItemNBTHelper.getInteger(container, "Energy", 0);
    }

    public int getMaxEnergyStored(ItemStack stack) {
        int points = IUpgradableItem.EnumUpgrade.RF_CAPACITY.getUpgradePoints(stack);
        return (int) (BalanceConfigHandler.draconicWeaponsBaseStorage * 1.5 + points * BalanceConfigHandler.draconicWeaponsStoragePerUpgrade);
    }

    public List<String> getDisplayData(ItemStack stack) {
        List<String> list = new ArrayList<String>();
        if (BrandonsCore.proxy.getClientPlayer() != null && BrandonsCore.proxy.getClientPlayer().getItemInUse() != null && BrandonsCore.proxy.getClientPlayer().getItemInUseDuration() > 2) {
            EntityPlayer player = BrandonsCore.proxy.getClientPlayer();
            BowHandler.BowProperties properties = new BowHandler.BowProperties(stack, player);
            int power = (int) Math.min(player.getItemInUseDuration() / properties.getDrawTicks() * 100.0F, 100.0F);
            list.add(InfoHelper.ITC() + StatCollector.translateToLocal("info.de.power.txt") + ": " + InfoHelper.HITC() + power + "%");
        } else {
            int preset = ItemNBTHelper.getInteger(stack, "ConfigProfile", 0);
            list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("info.de.capacitorMode.txt") + ": " + ItemNBTHelper.getString(stack, "ProfileName" + preset, "Profile " + preset));
            for (ItemConfigField field : getFields(stack, 0)) {
                if ((field.datatype == 4 && ((Float) field.value).floatValue() > 0.0F) || (field.datatype == 6 && ((Boolean) field.value).booleanValue()))
                    list.add(field.getTooltipInfo());
            }
            list.add(InfoHelper.ITC() + StatCollector.translateToLocal("info.de.charge.txt") + ": " + InfoHelper.HITC() + Utills.formatNumber(getEnergyStored(stack)) + " / " + Utills.formatNumber(getMaxEnergyStored(stack)));
            if (BrandonsCore.proxy.getClientPlayer() != null) {
                BowHandler.BowProperties properties = new BowHandler.BowProperties(stack, BrandonsCore.proxy.getClientPlayer());
                list.add(InfoHelper.ITC() + StatCollector.translateToLocal("gui.de.rfPerShot.txt") + ": " + InfoHelper.HITC() + Utills.addCommas(properties.calculateEnergyCost()));
                if (!properties.canFire() && properties.cantFireMessage != null)
                    list.add(EnumChatFormatting.DARK_RED + StatCollector.translateToLocal(properties.cantFireMessage));
            }
        }
        return list;
    }

    public int getEnergyPerAttack() {
        return BalanceConfigHandler.draconicBowEnergyPerShot;
    }
}
