package com.Denfop.integration.Waila;

import com.Denfop.tiles.base.TileEntitySolarPanel;
import cpw.mods.fml.common.Optional.Interface;
import cpw.mods.fml.common.Optional.Method;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

@Interface(iface = "mcp.mobius.waila.api.IWailaDataProvider", modid = "Waila")
public class WailaHandler implements IWailaDataProvider {
    public static final NumberFormat commanator = new DecimalFormat("#,###");

    @Method(modid = "Waila")
    public static void callbackRegister(IWailaRegistrar register) {
        register.registerBodyProvider(new WailaHandler(), Block.class);
        register.addConfig("IU", "eu.showEU", true);
    }

    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return null;
    }

    public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }

    public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {

        Item item = accessor.getBlock().getItem(accessor.getWorld(), (accessor.getPosition()).blockX, (accessor.getPosition()).blockY, (accessor.getPosition()).blockZ);
        if (item != null) {
            Block blockx = (item instanceof net.minecraft.item.ItemBlock && !accessor.getBlock().isFlowerPot()) ? Block.getBlockFromItem(item) : accessor.getBlock();
            Block blockEMC = accessor.getWorld().getBlock(accessor.getPosition().blockX, accessor.getPosition().blockY, accessor.getPosition().blockZ);

        }

        if (accessor.getWorld().getTileEntity(accessor.getPosition().blockX, accessor.getPosition().blockY, accessor.getPosition().blockZ) instanceof TileEntitySolarPanel) {
            int energy = accessor.getNBTInteger(accessor.getNBTData(), "storage");
            int solartype = accessor.getNBTInteger(accessor.getNBTData(), "solartype");

            TileEntitySolarPanel tile = (TileEntitySolarPanel) accessor.getWorld().getTileEntity(accessor.getPosition().blockX, accessor.getPosition().blockY, accessor.getPosition().blockZ);
            currenttip.add(StatCollector.translateToLocal("gui.SuperSolarPanel.generating") + " " + tile.generating + " EU/t" + EnumChatFormatting.RESET);
            currenttip.add(StatCollector.translateToLocal("gui.SuperSolarPanel.maxOutput") + " " + tile.production + " EU/t" + EnumChatFormatting.RESET);
            currenttip.add(StatCollector.translateToLocal("ssp.tier") + " " + tile.tier + EnumChatFormatting.RESET);
            String ModulesString6 = I18n.format("ssp.moduletype");
            String ModulesString61 = I18n.format("ssp.moduletype1");
            String ModulesString62 = I18n.format("ssp.moduletype2");
            String ModulesString63 = I18n.format("ssp.moduletype3");
            String ModulesString64 = I18n.format("ssp.moduletype4");
            String ModulesString65 = I18n.format("ssp.moduletype5");
            String ModulesString66 = I18n.format("ssp.moduletype6");
            String ModulesString67 = I18n.format("ssp.moduletype7");
            if (solartype == 0)
                currenttip.add(ModulesString6 + EnumChatFormatting.RESET);
            if (solartype == 1)
                currenttip.add(ModulesString61 + EnumChatFormatting.RESET);
            if (solartype == 2)
                currenttip.add(ModulesString62 + EnumChatFormatting.RESET);
            if (solartype == 3)
                currenttip.add(ModulesString63 + EnumChatFormatting.RESET);
            if (solartype == 4)
                currenttip.add(ModulesString64 + EnumChatFormatting.RESET);
            if (solartype == 5)
                currenttip.add(ModulesString65 + EnumChatFormatting.RESET);
            if (solartype == 6)
                currenttip.add(ModulesString66 + EnumChatFormatting.RESET);
            if (solartype == 7)
                currenttip.add(ModulesString67 + EnumChatFormatting.RESET);
            currenttip.add(energy + " / " + tile.maxStorage + " EU" + EnumChatFormatting.RESET);
        }
        return currenttip;
    }

    public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }

    public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, int x, int y, int z) {
        return tag;
    }
}
