package com.Denfop.integration.DE;

import com.Denfop.IUCore;
import com.Denfop.api.IPanel;
import com.Denfop.tiles.base.TileEntitySolarPanel;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

import java.util.ArrayList;
import java.util.List;

public class ItemDESolarPanel extends ItemBlock implements IPanel {
    private final List<String> itemNames;

    public ItemDESolarPanel(final Block b) {
        super(b);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.itemNames = new ArrayList<String>();
        this.addItemsNames();
        this.setCreativeTab(IUCore.tabssp);
    }

    public int getMetadata(final int i) {
        return i;
    }

    public String getUnlocalizedName(final ItemStack itemstack) {
        return this.itemNames.get(itemstack.getItemDamage());
    }

    public void getSubItems(final Item item, final CreativeTabs tabs, final List itemList) {
        for (int meta = 0; meta <= this.itemNames.size() - 1; ++meta) {
            final ItemStack stack = new ItemStack(this, 1, meta);

            TileEntitySolarPanel tile = (TileEntitySolarPanel) blockDESolarPanel.getBlockEntity(meta);
            IPanel.setData(stack, tile);
            itemList.add(stack);
        }
    }

    public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean b) {
        NBTTagCompound nbttagcompound;
        int meta = itemStack.getItemDamage();
        TileEntitySolarPanel tile = (TileEntitySolarPanel) blockDESolarPanel.getBlockEntity(meta);
        info.add(StatCollector.translateToLocal("supsolpans.SSP.GenerationDay.tooltip") + " " + tile.genDay + " EU/t ");
        info.add(StatCollector.translateToLocal("supsolpans.SSP.GenerationNight.tooltip") + " " + tile.genNight + " EU/t ");

        info.add(StatCollector.translateToLocal("ic2.item.tooltip.Output") + " " + tile.production + " EU/t ");
        info.add(StatCollector.translateToLocal("ic2.item.tooltip.Capacity") + " " + tile.maxStorage + " EU ");
        info.add(StatCollector.translateToLocal("ssp.tier") + tile.tier);

    }

    public void addItemsNames() {
        this.itemNames.add("blockDraconSolarPanel");
        this.itemNames.add("blockAwakenedSolarPanel");
        this.itemNames.add("blockChaosSolarPanel");
    }

    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(final ItemStack itemstack) {
        final int i = itemstack.getItemDamage();
        switch (i) {
            case 0: {
                return EnumRarity.uncommon;
            }
            case 1: {
                return EnumRarity.rare;
            }
            case 2: {
                return EnumRarity.epic;
            }

            default: {
                return EnumRarity.uncommon;
            }
        }
    }
}
