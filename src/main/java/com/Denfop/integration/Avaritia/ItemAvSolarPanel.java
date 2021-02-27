
package com.Denfop.integration.Avaritia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

import java.util.List;

import com.Denfop.SuperSolarPanels;
import com.Denfop.api.IPanel;
import com.Denfop.block.Base.BlockSSPSolarPanel;
import com.Denfop.tiles.base.TileEntitySolarPanel;

import net.minecraft.item.ItemBlock;

public class ItemAvSolarPanel extends ItemBlock implements IPanel
{
    private List<String> itemNames;
    
    public ItemAvSolarPanel(final Block b) {
        super(b);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.itemNames = new ArrayList<String>();
        this.addItemsNames();
        this.setCreativeTab(SuperSolarPanels.tabssp);
    }
    
    public int getMetadata(final int i) {
        return i;
    }
    
    public String getUnlocalizedName(final ItemStack itemstack) {
        return this.itemNames.get(itemstack.getItemDamage());
    }
    
    public void addItemsNames() {
        this.itemNames.add("blockNeutronSolarPanel");
        this.itemNames.add("blockInfinitySolarPanel");
    }
    public void getSubItems(final Item item, final CreativeTabs tabs, final List itemList) {
        for (int meta = 0; meta <= this.itemNames.size() - 1; ++meta) {
            final ItemStack stack = new ItemStack((Item)this, 1, meta);
            
        	TileEntitySolarPanel tile = (TileEntitySolarPanel) blockAvSolarPanel.getBlockEntity(meta);
        	IPanel.setData(stack, meta,tile);
            itemList.add(stack);
        }
    }
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(final ItemStack itemstack) {
        final int i = itemstack.getItemDamage();
        switch (i) {
            case 0: {
                return EnumRarity.epic;
            }
            case 1: {
                return EnumRarity.epic;
            }
            default: {
                return EnumRarity.uncommon;
            }
        }
    }
}
