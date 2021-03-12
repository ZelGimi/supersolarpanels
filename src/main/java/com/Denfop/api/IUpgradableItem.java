package com.Denfop.api;

import cofh.api.energy.IEnergyContainerItem;
import com.brandon3055.brandonscore.common.utills.ItemNBTHelper;
import com.brandon3055.draconicevolution.common.utills.IConfigurableItem;
import com.brandon3055.draconicevolution.common.utills.LogHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

import java.util.List;

public interface IUpgradableItem {
    List<EnumUpgrade> getUpgrades(ItemStack paramItemStack);

    int getUpgradeCap(ItemStack paramItemStack);

    int getMaxTier(ItemStack paramItemStack);

    int getMaxUpgradePoints(int paramInt);

    int getMaxUpgradePoints(int paramInt, ItemStack paramItemStack);

    int getBaseUpgradePoints(int paramInt);

    List<String> getUpgradeStats(ItemStack paramItemStack);

    enum EnumUpgrade {
        RF_CAPACITY(0, 1, "RFCapacity") {
            public void onRemovedFromItem(ItemStack itemStack) {
                if (itemStack != null && itemStack.getItem() instanceof IEnergyContainerItem) {
                    IEnergyContainerItem item = (IEnergyContainerItem) itemStack.getItem();
                    for (int i = 0; i < 500 && item.getEnergyStored(itemStack) > item.getMaxEnergyStored(itemStack); i++)
                        item.extractEnergy(itemStack, item.getEnergyStored(itemStack) - item.getMaxEnergyStored(itemStack), false);
                }
            }
        },
        DIG_SPEED(1, 1, "DigSpeed"),
        DIG_AOE(2, 4, "DigAOE") {
            public void onRemovedFromItem(ItemStack itemStack) {
                int profile = ItemNBTHelper.getInteger(itemStack, "ConfigProfile", 0);
                for (int i = 0; i < 8; i++) {
                    ItemNBTHelper.setInteger(itemStack, "ConfigProfile", i);
                    if (IConfigurableItem.ProfileHelper.getInteger(itemStack, "ToolDigAOE", 0) > getUpgradePoints(itemStack))
                        IConfigurableItem.ProfileHelper.setInteger(itemStack, "ToolDigAOE", getUpgradePoints(itemStack));
                }
                ItemNBTHelper.setInteger(itemStack, "ConfigProfile", profile);
            }
        },
        DIG_DEPTH(3, 2, "DigDepth") {
            public void onRemovedFromItem(ItemStack itemStack) {
                int profile = ItemNBTHelper.getInteger(itemStack, "ConfigProfile", 0);
                for (int i = 0; i < 8; i++) {
                    ItemNBTHelper.setInteger(itemStack, "ConfigProfile", i);
                    if (IConfigurableItem.ProfileHelper.getInteger(itemStack, "ToolDigDepth", 0) > getUpgradePoints(itemStack))
                        IConfigurableItem.ProfileHelper.setInteger(itemStack, "ToolDigDepth", getUpgradePoints(itemStack));
                }
                ItemNBTHelper.setInteger(itemStack, "ConfigProfile", profile);
            }
        },
        ATTACK_DAMAGE(4, 1, "AttackDamage"),
        ATTACK_AOE(5, 2, "AttackAOE") {
            public void onRemovedFromItem(ItemStack itemStack) {
                int profile = ItemNBTHelper.getInteger(itemStack, "ConfigProfile", 0);
                for (int i = 0; i < 8; i++) {
                    ItemNBTHelper.setInteger(itemStack, "ConfigProfile", i);
                    if (IConfigurableItem.ProfileHelper.getInteger(itemStack, "WeaponAttackAOE", 0) > getUpgradePoints(itemStack))
                        IConfigurableItem.ProfileHelper.setInteger(itemStack, "WeaponAttackAOE", getUpgradePoints(itemStack));
                }
                ItemNBTHelper.setInteger(itemStack, "ConfigProfile", profile);
            }
        },
        ARROW_DAMAGE(6, 1, "ArrowDamage") {
            public void onRemovedFromItem(ItemStack itemStack) {
                int profile = ItemNBTHelper.getInteger(itemStack, "ConfigProfile", 0);
                for (int i = 0; i < 8; i++) {
                    ItemNBTHelper.setInteger(itemStack, "ConfigProfile", i);
                    if (IConfigurableItem.ProfileHelper.getInteger(itemStack, "BowArrowDamage", 0) > getUpgradePoints(itemStack))
                        IConfigurableItem.ProfileHelper.setInteger(itemStack, "BowArrowDamage", getUpgradePoints(itemStack));
                }
                ItemNBTHelper.setInteger(itemStack, "ConfigProfile", profile);
            }
        },
        DRAW_SPEED(7, 2, "DrawSpeed"),
        ARROW_SPEED(8, 2, "ArrowSpeed") {
            public void onRemovedFromItem(ItemStack itemStack) {
                int profile = ItemNBTHelper.getInteger(itemStack, "ConfigProfile", 0);
                for (int i = 0; i < 5; i++) {
                    ItemNBTHelper.setInteger(itemStack, "ConfigProfile", i);
                    if (IConfigurableItem.ProfileHelper.getInteger(itemStack, "BowArrowSpeedModifier", 0) > getUpgradePoints(itemStack))
                        IConfigurableItem.ProfileHelper.setInteger(itemStack, "BowArrowSpeedModifier", getUpgradePoints(itemStack));
                }
                ItemNBTHelper.setInteger(itemStack, "ConfigProfile", profile);
            }
        },
        SHIELD_CAPACITY(9, 1, "ShieldCapacity"),
        SHIELD_RECOVERY(10, 1, "ShieldRecovery"),
        MOVE_SPEED(11, 1, "MoveSpeed"),
        JUMP_BOOST(12, 1, "JumpBoost");

        private final String COMPOUND_NAME = "Upgrades";

        public int index;

        public int pointConversion;

        public String name;

        EnumUpgrade(int index, int pointConversion, String name) {
            this.index = index;
            this.pointConversion = pointConversion;
            this.name = name;
        }

        public static EnumUpgrade getUpgradeByIndex(int index) {
            for (EnumUpgrade upgrade : values()) {
                if (upgrade.index == index)
                    return upgrade;
            }
            return null;
        }

        public int[] getCoresApplied(ItemStack stack) {
            if (stack == null)
                return new int[]{0, 0, 0, 0};
            NBTTagCompound compound = ItemNBTHelper.getCompound(stack);
            if (!compound.hasKey("Upgrades"))
                return new int[]{0, 0, 0, 0};
            NBTTagCompound upgrades = compound.getCompoundTag("Upgrades");
            if (upgrades.hasKey(this.name) && (upgrades.getIntArray(this.name)).length == 4)
                return upgrades.getIntArray(this.name);
            return new int[]{0, 0, 0, 0};
        }

        public void setCoresApplied(ItemStack stack, int[] cores) {
            NBTTagCompound upgrades;
            if (cores.length != 4) {
                LogHelper.error("[EnumUpgrade] Error applying upgrades to stack.");
                return;
            }
            NBTTagCompound compound = ItemNBTHelper.getCompound(stack);
            if (compound.hasKey("Upgrades")) {
                upgrades = compound.getCompoundTag("Upgrades");
            } else {
                upgrades = new NBTTagCompound();
            }
            upgrades.setIntArray(this.name, cores);
            compound.setTag("Upgrades", upgrades);
        }

        public String getLocalizedName() {
            return StatCollector.translateToLocal("gui.de." + this.name + ".txt");
        }

        public int getUpgradePoints(ItemStack itemStack) {
            int[] applied = getCoresApplied(itemStack);
            int totalPoints = applied[0] + applied[1] * 2 + applied[2] * 4 + applied[3] * 8;
            if (itemStack != null && itemStack.getItem() instanceof IUpgradableItem) {
                int points = ((IUpgradableItem) itemStack.getItem()).getBaseUpgradePoints(this.index) + totalPoints / this.pointConversion;
                return Math.min(points, ((IUpgradableItem) itemStack.getItem()).getMaxUpgradePoints(this.index, itemStack));
            }
            return 0;
        }

        public void onAppliedToItem(ItemStack itemStack) {
        }

        public void onRemovedFromItem(ItemStack itemStack) {
        }
    }
}
