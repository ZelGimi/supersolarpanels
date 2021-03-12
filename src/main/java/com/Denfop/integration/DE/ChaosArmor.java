package com.Denfop.integration.DE;

import com.Denfop.Constants;
import com.Denfop.IUCore;
import com.brandon3055.brandonscore.BrandonsCore;
import com.brandon3055.brandonscore.common.utills.InfoHelper;
import com.brandon3055.brandonscore.common.utills.ItemNBTHelper;
import com.brandon3055.brandonscore.common.utills.Utills;
import com.brandon3055.draconicevolution.common.ModItems;
import com.brandon3055.draconicevolution.common.entity.EntityPersistentItem;
import com.brandon3055.draconicevolution.common.handler.BalanceConfigHandler;
import com.brandon3055.draconicevolution.common.handler.ConfigHandler;
import com.brandon3055.draconicevolution.common.items.armor.ICustomArmor;
import com.brandon3055.draconicevolution.common.items.tools.baseclasses.ToolBase;
import com.brandon3055.draconicevolution.common.utills.IConfigurableItem;
import com.brandon3055.draconicevolution.common.utills.IInventoryTool;
import com.brandon3055.draconicevolution.common.utills.IUpgradableItem;
import com.brandon3055.draconicevolution.common.utills.ItemConfigField;
import com.brandon3055.draconicevolution.integration.ModHelper;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional.Interface;
import cpw.mods.fml.common.Optional.InterfaceList;
import cpw.mods.fml.common.Optional.Method;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import thaumcraft.api.IGoggles;
import thaumcraft.api.nodes.IRevealer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@InterfaceList({@Interface(iface = "thaumcraft.api.IGoggles", modid = "Thaumcraft"), @Interface(iface = "thaumcraft.api.nodes.IRevealer", modid = "Thaumcraft")})
public class ChaosArmor extends ItemArmor implements ISpecialArmor, IConfigurableItem, IInventoryTool, IGoggles, IRevealer, IUpgradableItem, ICustomArmor {
    @SideOnly(Side.CLIENT)
    public ModelBiped model;
    @SideOnly(Side.CLIENT)
    private IIcon helmIcon;
    @SideOnly(Side.CLIENT)
    private IIcon chestIcon;
    @SideOnly(Side.CLIENT)
    private IIcon leggsIcon;
    @SideOnly(Side.CLIENT)
    private IIcon bootsIcon;
    private final int maxEnergy = BalanceConfigHandler.draconicArmorBaseStorage * 2;
    private final int maxTransfer = BalanceConfigHandler.draconicArmorMaxTransfer;

    public ChaosArmor(ItemArmor.ArmorMaterial material, int armorType, String name) {
        super(material, 0, armorType);
        setUnlocalizedName(name);
        setCreativeTab(IUCore.tabssp2);
        if (ModItems.isEnabled(this))
            GameRegistry.registerItem(this, name);
    }

    public boolean hasEffect(ItemStack par1ItemStack, int pass) {
        return true;
    }

    public boolean isItemTool(ItemStack p_77616_1_) {
        return true;
    }

    public void getSubItems(Item item, CreativeTabs p_150895_2_, List list) {
        list.add(ItemNBTHelper.setInteger(new ItemStack(item), "Energy", 0));
        list.add(ItemNBTHelper.setInteger(new ItemStack(item), "Energy", this.maxEnergy));
    }

    public String getUnlocalizedName() {
        return String.format("item.%s%s", "ssp".toLowerCase() + ":", super.getUnlocalizedName().substring(super.getUnlocalizedName().indexOf(".") + 1));
    }

    public String getUnlocalizedName(ItemStack itemStack) {
        return getUnlocalizedName();
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.helmIcon = iconRegister.registerIcon(Constants.TEXTURES + ":" + "chaos_helmet");
        this.chestIcon = iconRegister.registerIcon(Constants.TEXTURES + ":" + "chaos_chestplate");
        this.leggsIcon = iconRegister.registerIcon(Constants.TEXTURES + ":" + "chaos_leggings");
        this.bootsIcon = iconRegister.registerIcon(Constants.TEXTURES + ":" + "chaos_boots");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        if (stack.getItem() == DraconicIntegration.ChaosHelm)
            return this.helmIcon;
        if (stack.getItem() == DraconicIntegration.ChaosChest)
            return this.chestIcon;
        if (stack.getItem() == DraconicIntegration.ChaosLeggs)
            return this.leggsIcon;
        return this.bootsIcon;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconIndex(ItemStack stack) {
        if (stack.getItem() == DraconicIntegration.ChaosHelm)
            return this.helmIcon;
        if (stack.getItem() == DraconicIntegration.ChaosChest)
            return this.chestIcon;
        if (stack.getItem() == DraconicIntegration.ChaosLeggs)
            return this.leggsIcon;
        return this.bootsIcon;
    }

    @SideOnly(Side.CLIENT)
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        if (!ConfigHandler.useOldArmorModel)
            return "supersolarpanel:textures/armor/armorChaos.png";
        if (stack.getItem() == DraconicIntegration.ChaosHelm || stack.getItem() == DraconicIntegration.ChaosChest || stack.getItem() == DraconicIntegration.ChaosBoots)

            return "supersolarpanel:textures/armor/chaos_layer_1.png";
        return "supersolarpanel:textures/armor/chaos_layer_2.png";
    }

    public EnumRarity getRarity(ItemStack p_77613_1_) {
        return EnumRarity.epic;
    }

    public double getDurabilityForDisplay(ItemStack stack) {
        return 1.0D - ItemNBTHelper.getInteger(stack, "Energy", 0) / getMaxEnergyStored(stack);
    }

    public boolean showDurabilityBar(ItemStack stack) {
        return (getEnergyStored(stack) < getMaxEnergyStored(stack));
    }

    protected float getProtectionShare() {
        switch (this.armorType) {
            case 0:
                return 0.35F;
            case 1:
                return 0.8F;
            case 2:
                return 0.6F;
            case 3:
                return 0.3F;
        }
        return 0.0F;
    }

    public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        if (source.isUnblockable() || source.isDamageAbsolute() || source.isMagicDamage())
            return new ISpecialArmor.ArmorProperties(0, this.damageReduceAmount / 100.0D, 15);
        return new ISpecialArmor.ArmorProperties(0, this.damageReduceAmount / 24.5D, 1000);
    }

    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        return (int) (getProtectionShare() * 20.0D);
    }

    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
    }

    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        if (stack == null)
            return;
        if (stack.getItem() == DraconicIntegration.ChaosHelm) {
            if (world.isRemote)
                return;
            if (getEnergyStored(stack) >= BalanceConfigHandler.draconicArmorEnergyToRemoveEffects && clearNegativeEffects(player))
                extractEnergy(stack, BalanceConfigHandler.draconicArmorEnergyToRemoveEffects, false);
            if (player.worldObj.getBlockLightValue((int) Math.floor(player.posX), (int) player.posY + 1, (int) Math.floor(player.posZ)) < 5 && IConfigurableItem.ProfileHelper.getBoolean(stack, "ArmorNVActive", false)) {
                player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 419, 0, true));
            } else if (IConfigurableItem.ProfileHelper.getBoolean(stack, "ArmorNVActive", false) && IConfigurableItem.ProfileHelper.getBoolean(stack, "ArmorNVLock", true)) {
                player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 419, 0, true));
            } else if (player.isPotionActive(Potion.nightVision.id)) {
                player.removePotionEffect(Potion.nightVision.id);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        InfoHelper.addEnergyAndLore(stack, list);
        ToolBase.holdCTRLForUpgrades(list, stack);
    }

    public boolean clearNegativeEffects(Entity par3Entity) {
        boolean flag = false;
        if (par3Entity.ticksExisted % 20 == 0 &&
                par3Entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) par3Entity;
            Collection<PotionEffect> potions = player.getActivePotionEffects();
            if (player.isBurning())
                player.extinguish();
            for (PotionEffect potion : potions) {
                int id = potion.getPotionID();
                if (((Boolean) ReflectionHelper.getPrivateValue(Potion.class, Potion.potionTypes[id], new String[]{"isBadEffect", "field_76418_K", "J"})).booleanValue()) {
                    if ((potion.getPotionID() != Potion.digSlowdown.id || !ModHelper.isHoldingCleaver(player)) && (
                            player.getHeldItem() == null || (player.getHeldItem().getItem() != ModItems.wyvernBow && player.getHeldItem().getItem() != ModItems.draconicBow) || id != 2)) {
                        player.removePotionEffect(id);
                        flag = true;
                    }
                    break;
                }
            }
        }
        return flag;
    }

    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        int stored = ItemNBTHelper.getInteger(container, "Energy", 0);
        int receive = Math.min(maxReceive, Math.min(getMaxEnergyStored(container) - stored, this.maxTransfer));
        if (!simulate) {
            stored += receive;
            ItemNBTHelper.setInteger(container, "Energy", stored);
        }
        return receive;
    }

    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        int stored = ItemNBTHelper.getInteger(container, "Energy", 0);
        int extract = Math.min(maxExtract, Math.min(this.maxTransfer, stored));
        if (!simulate) {
            stored -= extract;
            ItemNBTHelper.setInteger(container, "Energy", stored);
        }
        return extract;
    }

    public int getEnergyStored(ItemStack container) {
        return ItemNBTHelper.getInteger(container, "Energy", 0);
    }

    public int getMaxEnergyStored(ItemStack container) {
        int points = IUpgradableItem.EnumUpgrade.RF_CAPACITY.getUpgradePoints(container);
        return BalanceConfigHandler.draconicArmorBaseStorage * 2 + points * BalanceConfigHandler.draconicArmorStoragePerUpgrade;
    }

    public Entity createEntity(World world, Entity location, ItemStack itemstack) {
        return new EntityPersistentItem(world, location, itemstack);
    }

    public boolean hasCustomEntity(ItemStack stack) {
        return true;
    }

    public List<ItemConfigField> getFields(ItemStack stack, int slot) {
        List<ItemConfigField> list = new ArrayList<ItemConfigField>();
        if (this.armorType == 0) {
            list.add((new ItemConfigField(6, slot, "ArmorNVActive")).readFromItem(stack, Boolean.valueOf(false)));
            list.add((new ItemConfigField(6, slot, "ArmorNVLock")).readFromItem(stack, Boolean.valueOf(true)));
            if (Loader.isModLoaded("Thaumcraft"))
                list.add((new ItemConfigField(6, slot, "GogglesOfRevealing")).readFromItem(stack, Boolean.valueOf(true)));
        } else if (this.armorType == 1) {
            list.add((new ItemConfigField(4, slot, "VerticalAcceleration")).setMinMaxAndIncromente(Float.valueOf(0.0F), Float.valueOf(8.0F), Float.valueOf(0.1F)).readFromItem(stack, Float.valueOf(0.0F)).setModifier("PLUSPERCENT"));
            list.add((new ItemConfigField(4, slot, "ArmorFlightSpeedMult")).setMinMaxAndIncromente(Float.valueOf(0.0F), Float.valueOf(6.0F), Float.valueOf(0.1F)).readFromItem(stack, Float.valueOf(0.0F)).setModifier("PLUSPERCENT"));
            list.add((new ItemConfigField(6, slot, "EffectiveOnSprint")).readFromItem(stack, Boolean.valueOf(false)));
            list.add((new ItemConfigField(6, slot, "ArmorFlightLock")).readFromItem(stack, Boolean.valueOf(false)));
            list.add((new ItemConfigField(6, slot, "ArmorInertiaCancellation")).readFromItem(stack, Boolean.valueOf(false)));
        } else if (this.armorType == 2) {
            list.add((new ItemConfigField(4, slot, "ArmorSpeedMult")).setMinMaxAndIncromente(Float.valueOf(0.0F), Float.valueOf(8.0F), Float.valueOf(0.1F)).readFromItem(stack, Float.valueOf(0.0F)).setModifier("PLUSPERCENT"));
            list.add((new ItemConfigField(6, slot, "ArmorSprintOnly")).readFromItem(stack, Boolean.valueOf(false)));
        } else if (this.armorType == 3) {
            list.add((new ItemConfigField(4, slot, "ArmorJumpMult")).setMinMaxAndIncromente(Float.valueOf(0.0F), Float.valueOf(15.0F), Float.valueOf(0.1F)).readFromItem(stack, Float.valueOf(0.0F)).setModifier("PLUSPERCENT"));
            list.add((new ItemConfigField(6, slot, "ArmorSprintOnly")).readFromItem(stack, Boolean.valueOf(false)));
            list.add((new ItemConfigField(6, slot, "ArmorHillStep")).readFromItem(stack, Boolean.valueOf(true)));
        }
        return list;
    }

    public String getInventoryName() {
        return StatCollector.translateToLocal("info.de.toolInventoryEnch.txt");
    }

    public int getInventorySlots() {
        return 0;
    }

    public boolean isEnchantValid(Enchantment enchant) {
        return (enchant.type == EnumEnchantmentType.armor || (this.armorType == 0 && enchant.type == EnumEnchantmentType.armor_head) || (this.armorType == 1 && enchant.type == EnumEnchantmentType.armor_torso) || (this.armorType == 2 && enchant.type == EnumEnchantmentType.armor_legs) || (this.armorType == 3 && enchant.type == EnumEnchantmentType.armor_feet));
    }

    @Method(modid = "Thaumcraft")
    public boolean showIngamePopups(ItemStack itemstack, EntityLivingBase player) {
        return IConfigurableItem.ProfileHelper.getBoolean(itemstack, "GogglesOfRevealing", true);
    }

    @Method(modid = "Thaumcraft")
    public boolean showNodes(ItemStack itemstack, EntityLivingBase player) {
        return IConfigurableItem.ProfileHelper.getBoolean(itemstack, "GogglesOfRevealing", true);
    }

    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        if (ConfigHandler.useOldArmorModel)
            return super.getArmorModel(entityLiving, itemStack, armorSlot);
        if (!ConfigHandler.useOriginal3DArmorModel) {
            if (this.model == null) {
                if (this.armorType == 0) {
                    this.model = new ModelChaosArmor(1.1F, true, false, false, false);
                } else if (this.armorType == 1) {
                    this.model = new ModelChaosArmor(1.1F, false, true, false, false);
                } else if (this.armorType == 2) {
                    this.model = new ModelChaosArmor(1.1F, false, false, true, false);
                } else {
                    this.model = new ModelChaosArmor(1.1F, false, false, false, true);
                }
                this.model.bipedHead.showModel = (this.armorType == 0);
                this.model.bipedHeadwear.showModel = (this.armorType == 0);
                this.model.bipedBody.showModel = (this.armorType == 1 || this.armorType == 2);
                this.model.bipedLeftArm.showModel = (this.armorType == 1);
                this.model.bipedRightArm.showModel = (this.armorType == 1);
                this.model.bipedLeftLeg.showModel = (this.armorType == 2 || this.armorType == 3);
                this.model.bipedRightLeg.showModel = (this.armorType == 2 || this.armorType == 3);
            }
        } else if (this.model == null) {
            if (this.armorType == 0) {
                this.model = new ModelChaosArmorOld(1.1F, true, false, false, false, true);
            } else if (this.armorType == 1) {
                this.model = new ModelChaosArmorOld(1.1F, false, true, false, false, true);
            } else if (this.armorType == 2) {
                this.model = new ModelChaosArmorOld(1.1F, false, false, true, false, true);
            } else {
                this.model = new ModelChaosArmorOld(1.1F, false, false, false, true, true);
            }
            this.model.bipedHead.showModel = (this.armorType == 0);
            this.model.bipedHeadwear.showModel = (this.armorType == 0);
            this.model.bipedBody.showModel = (this.armorType == 1 || this.armorType == 2);
            this.model.bipedLeftArm.showModel = (this.armorType == 1);
            this.model.bipedRightArm.showModel = (this.armorType == 1);
            this.model.bipedLeftLeg.showModel = (this.armorType == 2 || this.armorType == 3);
            this.model.bipedRightLeg.showModel = (this.armorType == 2 || this.armorType == 3);
        }
        if (entityLiving == null)
            return this.model;
        this.model.isSneak = entityLiving.isSneaking();
        this.model.isRiding = entityLiving.isRiding();
        this.model.isChild = entityLiving.isChild();
        this.model.aimedBow = false;
        this.model.heldItemRight = (entityLiving.getHeldItem() != null) ? 1 : 0;
        if (entityLiving instanceof EntityPlayer && (
                (EntityPlayer) entityLiving).getItemInUseDuration() > 0) {
            EnumAction enumaction = ((EntityPlayer) entityLiving).getItemInUse().getItemUseAction();
            if (enumaction == EnumAction.block) {
                this.model.heldItemRight = 3;
            } else if (enumaction == EnumAction.bow) {
                this.model.aimedBow = true;
            }
        }
        return this.model;
    }

    public List<IUpgradableItem.EnumUpgrade> getUpgrades(ItemStack itemstack) {

        List<IUpgradableItem.EnumUpgrade> list = new ArrayList<IUpgradableItem.EnumUpgrade>();

        list.add(IUpgradableItem.EnumUpgrade.SHIELD_RECOVERY);
        list.add(IUpgradableItem.EnumUpgrade.SHIELD_CAPACITY);
        list.add(IUpgradableItem.EnumUpgrade.RF_CAPACITY);
        return list;

    }

    public int getUpgradeCap(ItemStack itemstack) {
        return BalanceConfigHandler.draconicArmorMaxUpgrades;
    }

    public int getMaxTier(ItemStack itemstack) {
        return 3;
    }

    public List<String> getUpgradeStats(ItemStack stack) {
        List<String> strings = new ArrayList<String>();
        strings.add(InfoHelper.ITC() + StatCollector.translateToLocal("gui.de.RFCapacity.txt") + ": " + InfoHelper.HITC() + Utills.formatNumber(getMaxEnergyStored(stack)));
        strings.add(InfoHelper.ITC() + StatCollector.translateToLocal("gui.de.ShieldCapacity.txt") + ": " + InfoHelper.HITC() + (int) getProtectionPoints(stack));
        strings.add(InfoHelper.ITC() + StatCollector.translateToLocal("gui.de.ShieldRecovery.txt") + ": " + InfoHelper.HITC() + Utills.round(getRecoveryPoints(stack) * 0.2D, 10.0D) + " EPS");
        return strings;
    }

    public int getMaxUpgradePoints(int upgradeIndex) {
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.RF_CAPACITY.index)
            return BalanceConfigHandler.draconicArmorMaxCapacityUpgradePoints;
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.SHIELD_CAPACITY.index)
            return (int) (getProtectionShare() * 65.0F) + ((this.armorType == 2) ? 2 : 0);
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.SHIELD_RECOVERY.index)
            return 30;
        return BalanceConfigHandler.draconicArmorMaxUpgradePoints;
    }

    public int getMaxUpgradePoints(int upgradeIndex, ItemStack stack) {
        return getMaxUpgradePoints(upgradeIndex);
    }

    public int getBaseUpgradePoints(int upgradeIndex) {
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.SHIELD_CAPACITY.index)
            return (int) (getProtectionShare() * 25.0F) + ((this.armorType == 2) ? 2 : 0);
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.SHIELD_RECOVERY.index)
            return 5;
        return 0;
    }

    public float getProtectionPoints(ItemStack stack) {
        return (IUpgradableItem.EnumUpgrade.SHIELD_CAPACITY.getUpgradePoints(stack) * 20);
    }

    public int getRecoveryPoints(ItemStack stack) {
        return IUpgradableItem.EnumUpgrade.SHIELD_RECOVERY.getUpgradePoints(stack);
    }

    public float getSpeedModifier(ItemStack stack, EntityPlayer player) {
        if (IConfigurableItem.ProfileHelper.getBoolean(stack, "ArmorSprintOnly", false))
            return player.isSprinting() ? IConfigurableItem.ProfileHelper.getFloat(stack, "ArmorSpeedMult", 0.0F) : (IConfigurableItem.ProfileHelper.getFloat(stack, "ArmorSpeedMult", 0.0F) / 5.0F);
        return IConfigurableItem.ProfileHelper.getFloat(stack, "ArmorSpeedMult", 0.0F);
    }

    public float getJumpModifier(ItemStack stack, EntityPlayer player) {
        if (IConfigurableItem.ProfileHelper.getBoolean(stack, "ArmorSprintOnly", false))
            return (player.isSprinting() || BrandonsCore.proxy.isCtrlDown()) ? IConfigurableItem.ProfileHelper.getFloat(stack, "ArmorJumpMult", 0.0F) : (IConfigurableItem.ProfileHelper.getFloat(stack, "ArmorJumpMult", 0.0F) / 5.0F);
        return IConfigurableItem.ProfileHelper.getFloat(stack, "ArmorJumpMult", 0.0F);
    }

    public boolean hasHillStep(ItemStack stack, EntityPlayer player) {
        if (IConfigurableItem.ProfileHelper.getBoolean(stack, "ArmorSprintOnly", false))
            return ((player.isSprinting() || BrandonsCore.proxy.isCtrlDown()) && IConfigurableItem.ProfileHelper.getBoolean(stack, "ArmorHillStep", true));
        return IConfigurableItem.ProfileHelper.getBoolean(stack, "ArmorHillStep", true);
    }

    public float getFireResistance(ItemStack stack) {
        return 1.0F;
    }

    public boolean[] hasFlight(ItemStack stack) {
        return new boolean[]{true, IConfigurableItem.ProfileHelper.getBoolean(stack, "ArmorFlightLock", false), IConfigurableItem.ProfileHelper.getBoolean(stack, "ArmorInertiaCancellation", false)};
    }

    public float getFlightSpeedModifier(ItemStack stack, EntityPlayer player) {
        if (IConfigurableItem.ProfileHelper.getBoolean(stack, "EffectiveOnSprint", false))
            return BrandonsCore.proxy.isCtrlDown() ? IConfigurableItem.ProfileHelper.getFloat(stack, "ArmorFlightSpeedMult", 0.0F) : 0.0F;
        return IConfigurableItem.ProfileHelper.getFloat(stack, "ArmorFlightSpeedMult", 0.0F);
    }

    public float getFlightVModifier(ItemStack stack, EntityPlayer player) {
        if (IConfigurableItem.ProfileHelper.getBoolean(stack, "EffectiveOnSprint", false))
            return BrandonsCore.proxy.isCtrlDown() ? IConfigurableItem.ProfileHelper.getFloat(stack, "VerticalAcceleration", 0.0F) : 0.0F;
        return IConfigurableItem.ProfileHelper.getFloat(stack, "VerticalAcceleration", 0.0F);
    }

    public int getEnergyPerProtectionPoint() {
        return BalanceConfigHandler.draconicArmorEnergyPerProtectionPoint;
    }

    public boolean hasProfiles() {
        return false;
    }
}
