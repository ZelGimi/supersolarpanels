package com.Denfop.item.armour;

import com.Denfop.Config;
import com.Denfop.Constants;
import com.Denfop.IUCore;
import com.Denfop.SSPItem;
import com.Denfop.utils.NBTData;
import com.brandon3055.draconicevolution.common.utills.IConfigurableItem;
import cpw.mods.fml.common.Optional.Method;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.*;
import ic2.core.IC2;
import ic2.core.IC2Potion;
import ic2.core.audio.AudioSource;
import ic2.core.init.MainConfig;
import ic2.core.util.ConfigUtil;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingFallEvent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ItemArmorImprovemedQuantum extends ItemArmor implements ISpecialArmor, IElectricItem, IItemHudInfo, ICustomDamageItem, IMetalArmor {
    protected static final Map<Integer, Integer> potionRemovalCost = new HashMap<Integer, Integer>();
    public static int status = 0;
    public static AudioSource audioSource;
    private static final int minCharge = 10000;
    private static final boolean lastJetpackUsed = false;
    protected final double maxCharge;
    protected final double transferLimit;
    protected final int tier;
    private final ThreadLocal<Boolean> allowDamaging;
    private final String armorName;
    private final Object repairMaterial;
    private final int dischargeInFlight = 278;
    private final int dischargeIdleMode = 1;
    private final float boostSpeed = 0.2F;
    private int hoverModeFallSpeed;
    private final int boostMultiplier = 3;
    private float jumpCharge;

    public ItemArmorImprovemedQuantum(String name, int armorType1, double maxCharge1, double transferLimit1, int tier1) {
        super(ItemArmor.ArmorMaterial.DIAMOND, IUCore.proxy.addArmor(name), armorType1);
        if (armorType1 == 3)
            MinecraftForge.EVENT_BUS.register(this);
        potionRemovalCost.put(Integer.valueOf(Potion.poison.id), Integer.valueOf(100));
        potionRemovalCost.put(Integer.valueOf(IC2Potion.radiation.id), Integer.valueOf(20));
        potionRemovalCost.put(Integer.valueOf(Potion.wither.id), Integer.valueOf(100));
        potionRemovalCost.put(Integer.valueOf(Potion.hunger.id), Integer.valueOf(200));
        this.allowDamaging = new ThreadLocal<Boolean>();
        this.maxCharge = maxCharge1;
        this.tier = tier1;
        this.transferLimit = transferLimit1;
        setMaxDamage(27);
        setMaxStackSize(1);
        setNoRepair();
        this.repairMaterial = null;
        this.armorName = name;
        setMaxDamage(ItemArmor.ArmorMaterial.DIAMOND.getDurability(armorType1));
        setUnlocalizedName(name);
        setCreativeTab(IUCore.tabssp2);
        GameRegistry.registerItem(this, name);
    }

    public static int getCharge(ItemStack itemstack) {
        NBTTagCompound nbttagcompound = NBTData.getOrCreateNbtData(itemstack);
        int k = nbttagcompound.getInteger("charge");
        return k;
    }

    public static boolean hasCompleteHazmat(EntityLivingBase living) {

        for (int i = 1; i < 5; i++) {
            ItemStack stack = living.getEquipmentInSlot(i);

            if (stack == null ||
                    !(stack.getItem() instanceof ItemArmorImprovemedQuantum))
                return false;
        }


        return true;
    }

    public List<String> getHudInfo(ItemStack itemStack) {
        List<String> info = new LinkedList<String>();
        info.add(ElectricItem.manager.getToolTip(itemStack));
        info.add(StatCollector.translateToLocal("ic2.item.tooltip.PowerTier") + " " + this.tier);
        return info;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        String name = getUnlocalizedName();
        if (name != null && name.length() > 4)

            this.itemIcon = iconRegister.registerIcon(Constants.TEXTURES + ":" + name);
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        int suffix = (this.armorType == 2) ? 2 : 1;
        return Constants.TEXTURES + ":textures/armor/" + this.armorName + "_" + suffix + ".png";
    }

    public String getUnlocalizedName() {
        return "supersolarpanels." + super.getUnlocalizedName().substring(5);
    }

    public String getUnlocalizedName(ItemStack itemStack) {
        return getUnlocalizedName();
    }

    public String getItemStackDisplayName(ItemStack itemStack) {
        return StatCollector.translateToLocal(getUnlocalizedName(itemStack));
    }

    public boolean isMetalArmor(ItemStack itemstack, EntityPlayer player) {
        return true;
    }

    public void Information(ItemStack itemStack, EntityPlayer player, List<String> info, boolean b) {
        info.add(StatCollector.translateToLocal("ic2.item.tooltip.PowerTier") + " " + this.tier);
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(final Item item, final CreativeTabs var2, final List var3) {
        final ItemStack var4 = new ItemStack(this, 1);
        ElectricItem.manager.charge(var4, 2.147483647E9, Integer.MAX_VALUE, true, false);
        var3.add(var4);
        var3.add(new ItemStack(this, 1, this.getMaxDamage()));
    }

    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    public boolean hasColor(ItemStack aStack) {
        return (getColor(aStack) != 10511680);
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
    public IIcon getIconFromDamageForRenderPass(int par1, int par2) {
        return getIconFromDamage(par1);
    }

    public void removeColor(ItemStack par1ItemStack) {
        NBTTagCompound tNBT = par1ItemStack.getTagCompound();
        if (tNBT != null) {
            tNBT = tNBT.getCompoundTag("display");
            if (tNBT.hasKey("color"))
                tNBT.removeTag("color");
        }
    }

    public int getColor(ItemStack aStack) {
        NBTTagCompound tNBT = aStack.getTagCompound();
        if (tNBT == null)
            return 10511680;
        tNBT = tNBT.getCompoundTag("display");
        return (tNBT == null) ? 10511680 : (tNBT.hasKey("color") ? tNBT.getInteger("color") : 10511680);
    }

    public void colorQArmor(ItemStack aStack, int par2) {
        NBTTagCompound tNBT = aStack.getTagCompound();
        if (tNBT == null) {
            tNBT = new NBTTagCompound();
            aStack.setTagCompound(tNBT);
        }
        if (!tNBT.hasKey("display"))
            tNBT.setTag("display", tNBT);
        tNBT = tNBT.getCompoundTag("display");
        tNBT.setInteger("color", par2);
    }

    public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        double absorptionRatio = getBaseAbsorptionRatio() * getDamageAbsorptionRatio();
        int energyPerDamage = getEnergyPerDamage();
        int damageLimit = (int) ((energyPerDamage > 0) ? (25.0D * ElectricItem.manager.getCharge(armor) / energyPerDamage) : 0.0D);
        return new ISpecialArmor.ArmorProperties(0, absorptionRatio, damageLimit);
    }

    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
        ElectricItem.manager.discharge(stack, (damage * getEnergyPerDamage() * 2), 2147483647, true, false, false);
    }

    public Item getChargedItem(ItemStack itemStack) {
        return this;
    }

    public Item getEmptyItem(ItemStack itemStack) {
        return this;
    }

    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return true;
    }

    @SubscribeEvent
    public void onEntityLivingFallEvent(LivingFallEvent event) {
        if (IC2.platform.isSimulating() && event.entity instanceof EntityLivingBase) {
            EntityLivingBase entity = (EntityLivingBase) event.entity;
            ItemStack armor = entity.getEquipmentInSlot(1);
            if (armor != null && armor.getItem() == this) {
                int fallDamage = Math.max((int) event.distance - 10, 0);
                double energyCost = (getEnergyPerDamage() * fallDamage);
                if (energyCost <= ElectricItem.manager.getCharge(armor)) {
                    ElectricItem.manager.discharge(armor, energyCost, 2147483647, true, false, false);
                    event.setCanceled(true);
                }
            }
        }
    }

    public double getDamageAbsorptionRatio() {
        if (this.armorType == 1)
            return 1.1D;
        return 1.0D;
    }

    private double getBaseAbsorptionRatio() {
        return 0.4D;
    }

    public int getEnergyPerDamage() {
        return 20000;
    }

    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.epic;
    }

    public boolean canProvideEnergy(ItemStack itemStack) {
        return true;
    }

    public int getItemEnchantability() {
        return super.getItemEnchantability();
    }

    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        int air;
        boolean Nightvision;
        short hubmode;
        boolean jetpack, hoverMode, jetpackUsed, enableQuantumSpeedOnSprint;
        NBTTagCompound nbtData = NBTData.getOrCreateNbtData(itemStack);
        byte toggleTimer = nbtData.getByte("toggleTimer");
        boolean ret = false;

        switch (this.armorType) {
            case 0:
                IC2.platform.profilerStartSection("QuantumHelmet");
                air = player.getAir();
                if (ElectricItem.manager.canUse(itemStack, 1000.0D) && air < 100) {
                    player.setAir(air + 200);
                    ElectricItem.manager.use(itemStack, 1000.0D, null);
                    ret = true;
                } else if (air <= 0) {
                    IC2.achievements.issueAchievement(player, "starveWithQHelmet");
                }
                if (ElectricItem.manager.canUse(itemStack, 1000.0D) && player.getFoodStats().needFood()) {
                    int slot = -1;
                    for (int i = 0; i < player.inventory.mainInventory.length; i++) {
                        if (player.inventory.mainInventory[i] != null && player.inventory.mainInventory[i]
                                .getItem() instanceof ItemFood) {
                            slot = i;
                            break;
                        }
                    }
                    if (slot > -1) {
                        ItemStack stack = player.inventory.mainInventory[slot];
                        ItemFood can = (ItemFood) stack.getItem();
                        stack = can.onEaten(stack, world, player);
                        if (stack.stackSize <= 0)
                            player.inventory.mainInventory[slot] = null;
                        ElectricItem.manager.use(itemStack, 1000.0D, null);
                        ret = true;
                    }
                } else if (player.getFoodStats().getFoodLevel() <= 0) {
                    IC2.achievements.issueAchievement(player, "starveWithQHelmet");
                }

                for (Object effect : new LinkedList(player.getActivePotionEffects())) {
                    int id = ((PotionEffect) effect).getPotionID();
                    Integer cost = potionRemovalCost.get(Integer.valueOf(id));
                    if (cost != null) {
                        cost = Integer.valueOf(cost.intValue() * (((PotionEffect) effect).getAmplifier() + 1));
                        if (ElectricItem.manager.canUse(itemStack, cost.intValue())) {
                            ElectricItem.manager.use(itemStack, cost.intValue(), null);
                            IC2.platform.removePotion(player, id);
                        }
                    }
                }

                hubmode = nbtData.getShort("HudMode");

                if (IC2.keyboard.isAltKeyDown(player) && IC2.keyboard.isHudModeKeyDown(player) && toggleTimer == 0) {
                    toggleTimer = 10;
                    if (hubmode == 2) {
                        hubmode = 0;
                    } else {
                        hubmode = (short) (hubmode + 1);
                    }
                    if (IC2.platform.isSimulating()) {
                        nbtData.setShort("HudMode", hubmode);
                        switch (hubmode) {
                            case 0:
                                IC2.platform.messagePlayer(player, "HUD disabled.");
                                break;
                            case 1:
                                IC2.platform.messagePlayer(player, "HUD (basic) enabled.");
                                break;
                            case 2:
                                IC2.platform.messagePlayer(player, "HUD (extended) enabled");
                                break;
                        }
                    }
                }
                if (IC2.platform.isSimulating() && toggleTimer > 0) {
                    toggleTimer = (byte) (toggleTimer - 1);
                    nbtData.setByte("toggleTimer", toggleTimer);
                }


                IC2.platform.profilerEndSection();
                break;
            case 1:
                if (player.capabilities.isFlying) {
                    if (ElectricItem.manager.canUse(itemStack, 3000)) {
                        ElectricItem.manager.use(itemStack, 3000, null);
                    }
                }
                jetpack = nbtData.getBoolean("jetpack");
                hoverMode = nbtData.getBoolean("hoverMode");
                jetpackUsed = false;
                if (IC2.keyboard.isJumpKeyDown(player) && IC2.keyboard.isModeSwitchKeyDown(player) && toggleTimer == 0) {
                    ItemStack jetpack1 = player.inventory.armorInventory[2];
                    ElectricItem.manager.discharge(jetpack1, 3000, 2147483647, true, false, false);
                    toggleTimer = 10;
                    hoverMode = !hoverMode;
                    if (IC2.platform.isSimulating()) {
                        nbtData.setBoolean("hoverMode", hoverMode);
                        if (hoverMode) {
                            IC2.platform.messagePlayer(player, "Quantum Hover Mode enabled.");
                        } else {
                            IC2.platform.messagePlayer(player, "Quantum Hover Mode disabled.");
                        }
                    }
                }

                if (IC2.keyboard.isBoostKeyDown(player) && IC2.keyboard.isModeSwitchKeyDown(player) && toggleTimer == 0) {
                    toggleTimer = 10;
                    jetpack = !jetpack;
                    if (IC2.platform.isSimulating()) {

                        nbtData.setBoolean("jetpack", jetpack);
                        if (jetpack) {
                            IC2.platform.messagePlayer(player, "Quantum Jetpack enabled.");
                            player.capabilities.isFlying = true;

                            player.capabilities.allowFlying = true;
                            player.fallDistance = 0.0F;
                            player.distanceWalkedModified = 0.0F;
                        } else if (!jetpack) {
                            IC2.platform.messagePlayer(player, "Quantum Jetpack disabled.");


                        }
                    }
                }

                if (audioSource != null)
                    audioSource.updatePosition();

                if (Config.disableeffect) {

                } else {


                    player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 300));
                }
                ret = jetpackUsed;
                player.extinguish();
                break;
            case 2:
                IC2.platform.profilerStartSection("QuantumLeggings");
                if (IC2.platform.isRendering()) {
                    enableQuantumSpeedOnSprint = ConfigUtil.getBool(MainConfig.get(), "misc/quantumSpeedOnSprint");
                } else {
                    enableQuantumSpeedOnSprint = true;
                }

                if (ElectricItem.manager.canUse(itemStack, 1000.0D) && (player.onGround || player
                        .isInWater()) && IC2.keyboard
                        .isForwardKeyDown(player) && ((enableQuantumSpeedOnSprint && player
                        .isSprinting()) || (!enableQuantumSpeedOnSprint && IC2.keyboard
                        .isBoostKeyDown(player)))) {
                    byte speedTicker = nbtData.getByte("speedTicker");
                    speedTicker = (byte) (speedTicker + 1);
                    if (speedTicker >= 10) {
                        speedTicker = 0;
                        ElectricItem.manager.use(itemStack, 1000.0D, null);
                        ret = true;
                    }
                    nbtData.setByte("speedTicker", speedTicker);
                    float speed = 0.22F;
                    if (player.isInWater()) {
                        speed = 0.1F;
                        if (IC2.keyboard.isJumpKeyDown(player))
                            player.motionY += 0.10000000149011612D;
                    }
                    if (speed > 0.0F)
                        player.moveFlying(0.0F, 1.0F, speed);
                }
                if (Config.disableeffect3) {

                } else {
                    player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 300));

                }
                IC2.platform.profilerEndSection();
                break;
            case 3:
                IC2.platform.profilerStartSection("QuantumBoots");


                IC2.platform.profilerEndSection();
                break;

        }


        if (ret)
            player.inventoryContainer.detectAndSendChanges();
    }

    public double getMaxCharge(ItemStack itemStack) {
        return this.maxCharge;
    }

    public int getTier(ItemStack itemStack) {
        return this.tier;
    }

    public double getTransferLimit(ItemStack itemStack) {
        return this.transferLimit;
    }

    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return false;
    }

    public void addInformation(final ItemStack itemStack, final EntityPlayer player, final List info, final boolean b) {
        NBTTagCompound nbtData = NBTData.getOrCreateNbtData(itemStack);


        if (itemStack.getItem() == SSPItem.quantumBodyarmor) {
            info.add(StatCollector.translateToLocal("ssp.fly") + " " + nbtData.getBoolean("jetpack"));
            for (Object effect : new LinkedList(player.getActivePotionEffects())) {
                int id = ((PotionEffect) effect).getPotionID();
                if (id == Potion.fireResistance.id) {
                    info.add(StatCollector.translateToLocal("ssp.effect") + " " + "True");
                }
            }


        }

    }

    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        if (ElectricItem.manager.getCharge(armor) >= getEnergyPerDamage())
            return (int) Math.round(20.0D * getBaseAbsorptionRatio() * getDamageAbsorptionRatio());
        return 0;
    }

    public int getCustomDamage(ItemStack stack) {
        return stack.getItemDamage();
    }

    public int getMaxCustomDamage(ItemStack stack) {
        return stack.getMaxDamage();
    }

    public void setCustomDamage(ItemStack stack, int damage) {
        this.allowDamaging.set(Boolean.valueOf(true));
        stack.setItemDamage(damage);
        this.allowDamaging.set(Boolean.valueOf(false));
    }

    public boolean applyCustomDamage(ItemStack stack, int damage, EntityLivingBase src) {
        if (src != null) {
            stack.damageItem(damage, src);
            return true;
        }
        return stack.attemptDamageItem(damage, IC2.random);
    }
}
