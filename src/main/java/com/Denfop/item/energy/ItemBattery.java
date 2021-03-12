package com.Denfop.item.energy;


import com.Denfop.Constants;
import com.Denfop.IUCore;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import ic2.api.item.IItemHudInfo;
import ic2.core.IC2;
import ic2.core.Ic2Items;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.LinkedList;
import java.util.List;

public class ItemBattery extends Item implements IElectricItem, IItemHudInfo {
    protected final double maxCharge;
    protected final double transferLimit;
    protected final int tier;
    protected IIcon[] textures;
    private int rarity;

    public ItemBattery(String name, double maxCharge, double transferLimit, int tier) {
        super();
        this.maxCharge = maxCharge;
        this.transferLimit = transferLimit;
        this.tier = tier;
        setMaxDamage(27);
        setMaxStackSize(1);
        setNoRepair();
        setUnlocalizedName(name);
        this.setRarity(1);
        this.setCreativeTab(IUCore.tabssp3);
        GameRegistry.registerItem(this, name);
    }

    public ItemBattery setRarity(int aRarity) {
        this.rarity = aRarity;
        return this;
    }

    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.values()[this.rarity];
    }

    public String getTextureName(int index) {
        if (index < 5)
            return getUnlocalizedName().substring(4) + "." + index;
        return null;
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

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        int indexCount = 0;
        while (getTextureName(indexCount) != null) {
            indexCount++;
            if (indexCount > 32767)
                throw new RuntimeException("More Item Icons than actually possible @ " + getUnlocalizedName());
        }
        this.textures = new IIcon[indexCount];

        for (int index = 0; index < indexCount; index++)
            this.textures[index] = iconRegister.registerIcon(Constants.TEXTURES + ":" + getTextureName(index));
    }


    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        if (meta <= 1)
            return this.textures[4];
        if (meta >= getMaxDamage() - 1)
            return this.textures[0];
        return this.textures[3 - 3 * (meta - 2) / (getMaxDamage() - 4 + 1)];
    }

    public boolean canProvideEnergy(ItemStack itemStack) {
        return true;
    }

    public double getTransferLimit(ItemStack itemStack) {
        return this.transferLimit;
    }


    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityplayer) {
        if (IC2.platform.isSimulating() && itemStack.getItem() == Ic2Items.chargedReBattery.getItem()) {
            boolean transferred = false;
            for (int i = 0; i < 9; i++) {
                ItemStack stack = entityplayer.inventory.mainInventory[i];
                if (stack != null && !(stack.getItem() instanceof ItemBattery)) {
                    double transfer = ElectricItem.manager.discharge(itemStack, 2.0D * this.transferLimit, 2147483647, true, true, true);
                    if (transfer > 0.0D) {
                        transfer = ElectricItem.manager.charge(stack, transfer, this.tier, true, false);
                        if (transfer > 0.0D) {
                            ElectricItem.manager.discharge(itemStack, transfer, 2147483647, true, true, false);
                            transferred = true;
                        }
                    }
                }
            }
            if (transferred && !IC2.platform.isRendering())
                entityplayer.openContainer.detectAndSendChanges();
        }
        return itemStack;
    }

    public Item setRarity() {

        return null;
    }


    public Item getChargedItem(ItemStack itemStack) {
        return this;
    }


    public List<String> getHudInfo(ItemStack itemStack) {
        List<String> info = new LinkedList<String>();
        info.add(ElectricItem.manager.getToolTip(itemStack));
        return info;
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List itemList) {
        ItemStack itemStack = new ItemStack(this, 1);
        if (getChargedItem(itemStack) == this) {
            ItemStack charged = new ItemStack(this, 1);
            ElectricItem.manager.charge(charged, Double.POSITIVE_INFINITY, 2147483647, true, false);
            itemList.add(charged);

        }

        if (getEmptyItem(itemStack) == this) {
            ItemStack charged = new ItemStack(this, 1);
            ElectricItem.manager.charge(charged, 0.0D, 2147483647, true, false);
            itemList.add(charged);
        }
    }

    @Override
    public double getMaxCharge(ItemStack itemStack) {
        return this.maxCharge;
    }

    @Override
    public int getTier(ItemStack itemStack) {

        return this.tier;
    }

    @Override
    public Item getEmptyItem(ItemStack itemStack) {
        // TODO Auto-generated method stub
        return itemStack.getItem();
    }

}
