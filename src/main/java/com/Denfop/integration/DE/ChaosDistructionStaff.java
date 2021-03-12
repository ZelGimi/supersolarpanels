package com.Denfop.integration.DE;

import com.Denfop.IUCore;
import com.brandon3055.brandonscore.common.utills.InfoHelper;
import com.brandon3055.draconicevolution.client.render.IRenderTweak;
import com.brandon3055.draconicevolution.common.handler.BalanceConfigHandler;
import com.brandon3055.draconicevolution.common.items.weapons.IEnergyContainerWeaponItem;
import com.brandon3055.draconicevolution.common.utills.IConfigurableItem;
import com.brandon3055.draconicevolution.common.utills.IInventoryTool;
import com.brandon3055.draconicevolution.common.utills.IUpgradableItem;
import com.brandon3055.draconicevolution.common.utills.ItemConfigField;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

import java.util.List;

public class ChaosDistructionStaff extends MiningTool implements IInventoryTool, IRenderTweak, IEnergyContainerWeaponItem {
    public ChaosDistructionStaff() {
        super(DraconicIntegration.CHAOS);
        setUnlocalizedName("ChaosDistructionStaff");
        setHarvestLevel("pickaxe", 15);
        setHarvestLevel("shovel", 15);
        setHarvestLevel("axe", 15);
        setCapacity(BalanceConfigHandler.draconicToolsBaseStorage * 2 + BalanceConfigHandler.draconicWeaponsBaseStorage * 2);
        setMaxExtract(BalanceConfigHandler.draconicToolsMaxTransfer * 2 + BalanceConfigHandler.draconicWeaponsMaxTransfer * 2);
        setMaxReceive(BalanceConfigHandler.draconicToolsMaxTransfer * 2 + BalanceConfigHandler.draconicWeaponsMaxTransfer * 2);
        this.energyPerOperation = BalanceConfigHandler.draconicToolsEnergyPerAction * 2;
        DraconicIntegration.register(this);
        setCreativeTab(IUCore.tabssp2);
    }

    public float func_150893_a(ItemStack stack, Block block) {
        return getEfficiency(stack);
    }

    public List<ItemConfigField> getFields(ItemStack stack, int slot) {
        List<ItemConfigField> list = super.getFields(stack, slot);
        list.add((new ItemConfigField(2, slot, "ToolDigAOE")).setMinMaxAndIncromente(Integer.valueOf(0), Integer.valueOf(IUpgradableItem.EnumUpgrade.DIG_AOE.getUpgradePoints(stack)), Integer.valueOf(1)).readFromItem(stack, Integer.valueOf(0)).setModifier("AOE"));
        list.add((new ItemConfigField(2, slot, "ToolDigDepth")).setMinMaxAndIncromente(Integer.valueOf(1), Integer.valueOf(IUpgradableItem.EnumUpgrade.DIG_DEPTH.getUpgradePoints(stack)), Integer.valueOf(1)).readFromItem(stack, Integer.valueOf(1)));
        list.add((new ItemConfigField(2, slot, "WeaponAttackAOE")).setMinMaxAndIncromente(Integer.valueOf(0), Integer.valueOf(IUpgradableItem.EnumUpgrade.ATTACK_AOE.getUpgradePoints(stack)), Integer.valueOf(1)).readFromItem(stack, Integer.valueOf(1)).setModifier("AOE"));
        list.add((new ItemConfigField(6, slot, "ToolVoidJunk")).readFromItem(stack, Boolean.valueOf(false)));
        return list;
    }

    public String getInventoryName() {
        return StatCollector.translateToLocal("info.de.toolInventoryOblit.txt");
    }

    public int getInventorySlots() {
        return 9;
    }

    public boolean isEnchantValid(Enchantment enchant) {
        return (enchant.type == EnumEnchantmentType.digger || enchant.type == EnumEnchantmentType.weapon);
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        entity.hurtResistantTime = 0;
        ToolHandler.damageEntityBasedOnHealth(entity, player, 0.3F);
        ToolHandler.AOEAttack(player, entity, stack, IConfigurableItem.ProfileHelper.getInteger(stack, "WeaponAttackAOE", 0));
        return true;
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        return super.onItemRightClick(stack, world, player);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean extended) {
        super.addInformation(stack, player, list, extended);
        list.add("");
        list.add(EnumChatFormatting.BLUE + "+" + ToolHandler.getBaseAttackDamage(stack) + " " + StatCollector.translateToLocal("info.de.attackDamage.txt"));
        list.add(EnumChatFormatting.BLUE + "+30%" + " " + StatCollector.translateToLocal("info.de.bonusHealthDamage.txt"));
    }

    public void tweakRender(IItemRenderer.ItemRenderType type) {
        GL11.glTranslated(0.77D, 0.19D, -0.15D);
        GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(-35.0F, 0.0F, -1.0F, 0.0F);
        GL11.glScaled(0.7D, 0.7D, 0.7D);
        if (type == IItemRenderer.ItemRenderType.INVENTORY) {
            GL11.glScalef(6.0F, 6.0F, 6.0F);
            GL11.glRotatef(145.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslated(-1.7D, 0.0D, 1.8D);
        } else if (type == IItemRenderer.ItemRenderType.ENTITY) {
            GL11.glRotatef(-34.5F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslated(-1.1D, 0.0D, -0.2D);
        } else if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glTranslated(0.0D, 0.4D, 0.0D);
        }
    }

    public int getUpgradeCap(ItemStack itemstack) {
        return BalanceConfigHandler.draconicStaffMaxUpgrades;
    }

    public int getMaxTier(ItemStack itemstack) {
        return 3;
    }

    public int getMaxUpgradePoints(int upgradeIndex) {
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.RF_CAPACITY.index)
            return BalanceConfigHandler.draconicStaffMaxCapacityUpgradePoints;
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.DIG_AOE.index)
            return 7;
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.DIG_DEPTH.index)
            return 15;
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.ATTACK_AOE.index)
            return 17;
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.ATTACK_DAMAGE.index)
            return 110;
        return BalanceConfigHandler.draconicStaffMaxUpgradePoints + 6;
    }

    public int getBaseUpgradePoints(int upgradeIndex) {
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.DIG_AOE.index)
            return 5;
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.DIG_DEPTH.index)
            return 11;
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.ATTACK_AOE.index)
            return 7;
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.ATTACK_DAMAGE.index)
            return 10;
        return 0;
    }

    public int getCapacity(ItemStack stack) {
        int points = IUpgradableItem.EnumUpgrade.RF_CAPACITY.getUpgradePoints(stack);
        return BalanceConfigHandler.draconicToolsBaseStorage * 2 + BalanceConfigHandler.draconicWeaponsBaseStorage * 2 + points * (BalanceConfigHandler.draconicToolsStoragePerUpgrade * 2 + BalanceConfigHandler.draconicWeaponsStoragePerUpgrade * 2);
    }

    public List<IUpgradableItem.EnumUpgrade> getUpgrades(ItemStack itemstack) {
        List<IUpgradableItem.EnumUpgrade> list = super.getUpgrades(itemstack);
        list.add(IUpgradableItem.EnumUpgrade.ATTACK_AOE);
        list.add(IUpgradableItem.EnumUpgrade.ATTACK_DAMAGE);
        list.add(IUpgradableItem.EnumUpgrade.DIG_DEPTH);
        list.add(IUpgradableItem.EnumUpgrade.DIG_AOE);
        list.add(IUpgradableItem.EnumUpgrade.DIG_SPEED);
        list.add(IUpgradableItem.EnumUpgrade.RF_CAPACITY);
        return list;
    }

    public boolean hasEffect(ItemStack par1ItemStack, int pass) {
        return true;
    }

    public List<String> getUpgradeStats(ItemStack stack) {
        List<String> list = super.getUpgradeStats(stack);
        list.add(InfoHelper.ITC() + StatCollector.translateToLocal("info.de.attackDamage.txt") + ": " + InfoHelper.HITC() + ToolHandler.getBaseAttackDamage(stack));
        return list;
    }

    public int getEnergyPerAttack() {
        return BalanceConfigHandler.draconicWeaponsEnergyPerAttack;
    }
}
