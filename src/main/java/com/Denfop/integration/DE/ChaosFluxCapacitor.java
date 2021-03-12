package com.Denfop.integration.DE;

import cofh.api.energy.IEnergyContainerItem;
import com.Denfop.IUCore;
import com.brandon3055.brandonscore.common.utills.InfoHelper;
import com.brandon3055.brandonscore.common.utills.ItemNBTHelper;
import com.brandon3055.brandonscore.common.utills.Utills;
import com.brandon3055.draconicevolution.common.handler.BalanceConfigHandler;
import com.brandon3055.draconicevolution.common.utills.IUpgradableItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ChaosFluxCapacitor extends RFItemBase implements IUpgradableItem {
    IIcon[] icons = new IIcon[2];

    public ChaosFluxCapacitor() {
        setUnlocalizedName("ChaosFluxCapacitor");
        setCreativeTab(IUCore.tabssp2);
        setHasSubtypes(true);
        setMaxStackSize(1);
        DraconicIntegration.register(this);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.icons[0] = iconRegister.registerIcon(getUnwrappedUnlocalizedName(getUnlocalizedName()));
        this.icons[1] = iconRegister.registerIcon(getUnwrappedUnlocalizedName(getUnlocalizedName()));
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        return this.icons[damage];
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        list.add(ItemNBTHelper.setInteger(new ItemStack(item, 1, 0), "Energy", 0));
        list.add(ItemNBTHelper.setInteger(new ItemStack(item, 1, 0), "Energy", (int) (BalanceConfigHandler.draconicCapacitorBaseStorage * 1.5)));
    }

    public String getUnlocalizedName(ItemStack itemStack) {
        return super.getUnlocalizedName(itemStack) + itemStack.getItemDamage();
    }

    public int getCapacity(ItemStack stack) {
        int points = IUpgradableItem.EnumUpgrade.RF_CAPACITY.getUpgradePoints(stack);
        return (int) ((stack.getItemDamage() == 0) ? (BalanceConfigHandler.draconicCapacitorBaseStorage * 1.5 + points * BalanceConfigHandler.draconicCapacitorStoragePerUpgrade) : 0);
    }

    public int getMaxExtract(ItemStack stack) {
        return (stack.getItemDamage() == 0) ? BalanceConfigHandler.draconicCapacitorMaxExtract : ((stack.getItemDamage() == 1) ? BalanceConfigHandler.draconicCapacitorMaxExtract : 0);
    }

    public int getMaxReceive(ItemStack stack) {
        return (stack.getItemDamage() == 0) ? BalanceConfigHandler.draconicCapacitorMaxExtract : ((stack.getItemDamage() == 1) ? BalanceConfigHandler.draconicCapacitorMaxReceive : 0);
    }

    public void onUpdate(ItemStack container, World world, Entity entity, int var1, boolean b) {
        if (!(entity instanceof EntityPlayer))
            return;
        EntityPlayer player = (EntityPlayer) entity;
        int mode = ItemNBTHelper.getShort(container, "Mode", (short) 0);
        if (mode == 1 || mode == 3)
            for (int i = 0; i < 9; i++) {
                int max = Math.min(getEnergyStored(container), getMaxExtract(container));
                ItemStack stack = player.inventory.getStackInSlot(i);
                if (stack != null && stack.getItem() instanceof IEnergyContainerItem && stack.getItem() != DraconicIntegration.ChaosFluxCapacitor) {
                    IEnergyContainerItem item = (IEnergyContainerItem) stack.getItem();
                    extractEnergy(container, item.receiveEnergy(stack, max, false), false);
                }
            }
        if (mode == 2 || mode == 3)
            for (int i = (mode == 3) ? 1 : 0; i < 5; i++) {
                int max = Math.min(getEnergyStored(container), getMaxExtract(container));
                ItemStack stack = player.getEquipmentInSlot(i);
                if (stack != null && stack.getItem() instanceof IEnergyContainerItem && stack.getItem() != DraconicIntegration.ChaosFluxCapacitor) {
                    IEnergyContainerItem item = (IEnergyContainerItem) stack.getItem();
                    extractEnergy(container, item.receiveEnergy(stack, max, false), false);
                }
            }
    }

    public boolean hasEffect(ItemStack stack, int pass) {
        return (ItemNBTHelper.getShort(stack, "Mode", (short) 0) > 0);
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (player.isSneaking()) {
            int mode = ItemNBTHelper.getShort(stack, "Mode", (short) 0);
            int newMode = (mode == 3) ? 0 : (mode + 1);
            ItemNBTHelper.setShort(stack, "Mode", (short) newMode);
            if (world.isRemote)
                player.addChatComponentMessage(new ChatComponentTranslation(InfoHelper.ITC() + StatCollector.translateToLocal("info.de.capacitorMode.txt") + ": " + InfoHelper.HITC() + StatCollector.translateToLocal("info.de.capacitorMode" + ItemNBTHelper.getShort(stack, "Mode", (short) 0) + ".txt"), new Object[0]));
        }
        return stack;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean extraInformation) {
        if (InfoHelper.holdShiftForDetails(list)) {
            list.add(StatCollector.translateToLocal("info.de.changwMode.txt"));
            list.add(InfoHelper.ITC() + StatCollector.translateToLocal("info.de.capacitorMode.txt") + ": " + InfoHelper.HITC() + StatCollector.translateToLocal("info.de.capacitorMode" + ItemNBTHelper.getShort(stack, "Mode", (short) 0) + ".txt"));
        }
        ToolBase.holdCTRLForUpgrades(list, stack);
        InfoHelper.addEnergyInfo(stack, list);
    }

    public boolean hasProfiles() {
        return false;
    }

    public List<IUpgradableItem.EnumUpgrade> getUpgrades(ItemStack itemstack) {
        List<IUpgradableItem.EnumUpgrade> list = new ArrayList<IUpgradableItem.EnumUpgrade>();
        list.add(IUpgradableItem.EnumUpgrade.RF_CAPACITY);
        return list;
    }

    public int getUpgradeCap(ItemStack stack) {
        return (stack.getItemDamage() == 0) ? BalanceConfigHandler.draconicCapacitorMaxUpgrades : ((stack.getItemDamage() == 1) ? BalanceConfigHandler.draconicCapacitorMaxUpgrades : 0);
    }

    public int getMaxTier(ItemStack stack) {
        return (stack.getItemDamage() == 0) ? 3 : 4;
    }

    public int getMaxUpgradePoints(int upgradeIndex) {
        return Math.max(BalanceConfigHandler.draconicCapacitorMaxUpgrades, BalanceConfigHandler.draconicCapacitorMaxUpgradePoints);
    }

    public int getMaxUpgradePoints(int upgradeIndex, ItemStack stack) {
        if (stack == null)
            return getMaxUpgradePoints(upgradeIndex);
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.RF_CAPACITY.index)
            return (stack.getItemDamage() == 0) ? BalanceConfigHandler.draconicCapacitorMaxCapacityUpgradePoints : ((stack.getItemDamage() == 1) ? BalanceConfigHandler.draconicCapacitorMaxCapacityUpgradePoints : getMaxUpgradePoints(upgradeIndex));
        return (stack.getItemDamage() == 0) ? BalanceConfigHandler.draconicCapacitorMaxUpgradePoints : ((stack.getItemDamage() == 1) ? BalanceConfigHandler.draconicCapacitorMaxUpgradePoints : getMaxUpgradePoints(upgradeIndex));
    }

    public int getBaseUpgradePoints(int upgradeIndex) {
        return 0;
    }

    public List<String> getUpgradeStats(ItemStack stack) {
        List<String> strings = new ArrayList<String>();
        strings.add(InfoHelper.ITC() + StatCollector.translateToLocal("gui.de.RFCapacity.txt") + ": " + InfoHelper.HITC() + Utills.formatNumber(getMaxEnergyStored(stack)));
        return strings;
    }
}
