package com.Denfop.integration.DE;

import com.Denfop.Constants;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemDC extends Item {
    public String getUnwrappedUnlocalizedName(String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    public String getUnlocalizedName() {
        return String.format("item.%s%s", "supersolarpanel".toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    public String getUnlocalizedName(ItemStack itemStack) {
        return getUnlocalizedName();
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(Constants.TEXTURES + ":" + getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    public boolean hasCustomEntity(ItemStack stack) {
        return false;
    }
}
