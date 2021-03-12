package com.Denfop.integration.DE;

import com.Denfop.Constants;
import com.Denfop.IUCore;
import com.brandon3055.brandonscore.BrandonsCore;
import com.brandon3055.brandonscore.common.utills.InfoHelper;
import com.brandon3055.brandonscore.common.utills.ItemNBTHelper;
import com.brandon3055.brandonscore.common.utills.Utills;
import com.brandon3055.draconicevolution.DraconicEvolution;
import com.brandon3055.draconicevolution.client.render.IRenderTweak;
import com.brandon3055.draconicevolution.common.entity.EntityPersistentItem;
import com.brandon3055.draconicevolution.common.handler.BalanceConfigHandler;
import com.brandon3055.draconicevolution.common.items.tools.baseclasses.ToolHandler;
import com.brandon3055.draconicevolution.common.items.weapons.IEnergyContainerWeaponItem;
import com.brandon3055.draconicevolution.common.network.ToolModePacket;
import com.brandon3055.draconicevolution.common.utills.*;
import com.google.common.collect.Multimap;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class ChaosSword extends ItemSword implements IEnergyContainerWeaponItem, IInventoryTool, IRenderTweak, IUpgradableItem, IHudDisplayItem {
    protected int capacity = (int) (BalanceConfigHandler.draconicWeaponsBaseStorage * 1.5);

    protected int maxReceive = BalanceConfigHandler.draconicWeaponsMaxTransfer;

    protected int maxExtract = BalanceConfigHandler.draconicWeaponsMaxTransfer;

    public ChaosSword() {
        super(DraconicIntegration.CHAOS);
        setUnlocalizedName("ChaosSword");

        setCreativeTab(IUCore.tabssp2);
        GameRegistry.registerItem(this, "ChaosSword");
    }

    public boolean isItemTool(ItemStack p_77616_1_) {
        return true;
    }

    public void getSubItems(Item item, CreativeTabs tab, List list) {
        list.add(ItemNBTHelper.setInteger(new ItemStack(item, 1, 0), "Energy", 0));
        list.add(ItemNBTHelper.setInteger(new ItemStack(item, 1, 0), "Energy", this.capacity));
    }

    public String getUnlocalizedName() {
        return String.format("item.%s%s", "supersolarpanel".toLowerCase() + ":", super.getUnlocalizedName().substring(super.getUnlocalizedName().indexOf(".") + 1));
    }

    public String getUnlocalizedName(ItemStack itemStack) {
        return getUnlocalizedName();
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(Constants.TEXTURES + ":" + "ChaosSword");
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        entity.hurtResistantTime = 0;
        ToolHandler.AOEAttack(player, entity, stack, IConfigurableItem.ProfileHelper.getInteger(stack, "WeaponAttackAOE", 0));
        ToolHandler.damageEntityBasedOnHealth(entity, player, 0.2F);
        return true;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean extraInformation) {
        if (InfoHelper.holdShiftForDetails(list)) {
            List<ItemConfigField> l = getFields(stack, 0);
            for (ItemConfigField f : l)
                list.add(f.getTooltipInfo());
            list.add(InfoHelper.ITC() + StatCollector.translateToLocal("info.de.sword.txt"));
            InfoHelper.addLore(stack, list);
        }
        ToolBase.holdCTRLForUpgrades(list, stack);
        InfoHelper.addEnergyInfo(stack, list);
        list.add("");
        list.add(EnumChatFormatting.BLUE + "+" + ToolHandler.getBaseAttackDamage(stack) + " " + StatCollector.translateToLocal("info.de.attackDamage.txt"));
        list.add(EnumChatFormatting.BLUE + "+20%" + " " + StatCollector.translateToLocal("info.de.bonusHealthDamage.txt"));
    }

    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.rare;
    }

    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        if (container.stackTagCompound == null)
            container.stackTagCompound = new NBTTagCompound();
        int energy = container.stackTagCompound.getInteger("Energy");
        int energyReceived = Math.min(getMaxEnergyStored(container) - energy, Math.min(this.maxReceive, maxReceive));
        if (!simulate) {
            energy += energyReceived;
            container.stackTagCompound.setInteger("Energy", energy);
        }
        return energyReceived;
    }

    public int getCapacity(ItemStack stack) {
        int points = IUpgradableItem.EnumUpgrade.RF_CAPACITY.getUpgradePoints(stack);
        return (int) (BalanceConfigHandler.draconicToolsBaseStorage * 1.5 + points * BalanceConfigHandler.draconicToolsStoragePerUpgrade);
    }

    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy"))
            return 0;
        int energy = container.stackTagCompound.getInteger("Energy");
        int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
        if (!simulate) {
            energy -= energyExtracted;
            container.stackTagCompound.setInteger("Energy", energy);
        }
        return energyExtracted;
    }

    public int getEnergyStored(ItemStack container) {
        if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy"))
            return 0;
        return container.stackTagCompound.getInteger("Energy");
    }

    public int getMaxEnergyStored(ItemStack container) {
        int points = IUpgradableItem.EnumUpgrade.RF_CAPACITY.getUpgradePoints(container);
        return (int) (BalanceConfigHandler.draconicToolsBaseStorage * 1.5 + points * BalanceConfigHandler.draconicToolsStoragePerUpgrade);

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

    public String getInventoryName() {
        return StatCollector.translateToLocal("info.de.toolInventoryEnch.txt");
    }

    public int getInventorySlots() {
        return 0;
    }

    public boolean isEnchantValid(Enchantment enchant) {
        return (enchant.type == EnumEnchantmentType.weapon);
    }

    public List<ItemConfigField> getFields(ItemStack stack, int slot) {
        List<ItemConfigField> list = new ArrayList<ItemConfigField>();
        list.add((new ItemConfigField(2, slot, "WeaponAttackAOE")).setMinMaxAndIncromente(Integer.valueOf(0), Integer.valueOf(IUpgradableItem.EnumUpgrade.ATTACK_AOE.getUpgradePoints(stack)), Integer.valueOf(1)).readFromItem(stack, Integer.valueOf(1)).setModifier("AOE"));
        return list;
    }

    public Multimap getAttributeModifiers(ItemStack stack) {
        Multimap map = super.getAttributeModifiers(stack);
        map.clear();
        return map;
    }

    public void tweakRender(IItemRenderer.ItemRenderType type) {
        GL11.glTranslated(-0.01D, 1.11D, -0.15D);
        GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(140.0F, 0.0F, -1.0F, 0.0F);
        GL11.glScaled(0.7D, 0.7D, 0.7D);
        if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslated(0.0D, -0.4D, 0.0D);
        } else if (type == IItemRenderer.ItemRenderType.INVENTORY) {
            GL11.glScalef(8.0F, 8.0F, 8.0F);
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslated(1.9D, 0.0D, 0.0D);
        } else if (type == IItemRenderer.ItemRenderType.ENTITY) {
            GL11.glRotatef(-90.5F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslated(-0.8D, 0.0D, 0.0D);
        }
    }

    public List<IUpgradableItem.EnumUpgrade> getUpgrades(ItemStack itemstack) {
        List<IUpgradableItem.EnumUpgrade> list = new ArrayList<IUpgradableItem.EnumUpgrade>();

        list.add(IUpgradableItem.EnumUpgrade.ATTACK_AOE);
        list.add(IUpgradableItem.EnumUpgrade.ATTACK_DAMAGE);
        list.add(IUpgradableItem.EnumUpgrade.RF_CAPACITY);
        return list;
    }

    public int getUpgradeCap(ItemStack itemstack) {
        return BalanceConfigHandler.draconicWeaponsMaxUpgrades;
    }

    public int getMaxTier(ItemStack itemstack) {
        return 3;
    }

    public int getMaxUpgradePoints(int upgradeIndex) {
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.RF_CAPACITY.index)
            return BalanceConfigHandler.draconicWeaponsMaxCapacityUpgradePoints;
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.ATTACK_AOE.index)
            return 12;
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.ATTACK_DAMAGE.index)
            return 28;
        return BalanceConfigHandler.draconicWeaponsMaxUpgradePoints;
    }

    public int getMaxUpgradePoints(int upgradeIndex, ItemStack stack) {
        return getMaxUpgradePoints(upgradeIndex);
    }

    public int getBaseUpgradePoints(int upgradeIndex) {

        if (upgradeIndex == IUpgradableItem.EnumUpgrade.ATTACK_AOE.index)
            return 2;
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.ATTACK_DAMAGE.index)
            return 0;
        return 0;
    }

    public List<String> getUpgradeStats(ItemStack stack) {
        List<String> strings = new ArrayList<String>();
        int attackaoe = 0;
        for (ItemConfigField field : getFields(stack, 0)) {
            if (field.name.equals("WeaponAttackAOE"))
                attackaoe = 1 + ((Integer) field.max).intValue() * 2;
        }
        strings.add(InfoHelper.ITC() + StatCollector.translateToLocal("gui.de.RFCapacity.txt") + ": " + InfoHelper.HITC() + Utills.formatNumber(getMaxEnergyStored(stack)));
        strings.add(InfoHelper.ITC() + StatCollector.translateToLocal("info.de.attackDamage.txt") + ": " + InfoHelper.HITC() + ToolHandler.getBaseAttackDamage(stack));
        strings.add(InfoHelper.ITC() + StatCollector.translateToLocal("gui.de.max.txt") + " " + StatCollector.translateToLocal("gui.de.AttackAOE.txt") + ": " + InfoHelper.HITC() + attackaoe + "x" + attackaoe);
        return strings;
    }

    public List<String> getDisplayData(ItemStack stack) {
        List<String> list = new ArrayList<String>();
        for (ItemConfigField field : getFields(stack, 0))
            list.add(field.getTooltipInfo());
        return list;
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote && !BrandonsCore.proxy.isDedicatedServer()) {
            ToolBase.handleModeChange(stack, player, InfoHelper.isShiftKeyDown(), InfoHelper.isCtrlKeyDown());
        } else if (world.isRemote && BrandonsCore.proxy.getMCServer() == null) {
            ToolBase.handleModeChange(stack, player, InfoHelper.isShiftKeyDown(), InfoHelper.isCtrlKeyDown());
            DraconicEvolution.network.sendToServer(new ToolModePacket(InfoHelper.isShiftKeyDown(), InfoHelper.isCtrlKeyDown()));
        }
        return super.onItemRightClick(stack, world, player);
    }

    public boolean hasProfiles() {
        return true;
    }

    public int getEnergyPerAttack() {
        return BalanceConfigHandler.draconicWeaponsEnergyPerAttack;
    }
}
