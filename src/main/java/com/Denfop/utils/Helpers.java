package com.Denfop.utils;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.List;

public class Helpers {
    public static boolean setBlockToAir(World w, int x, int y, int z) {
        return w.setBlock(x, y, z, Blocks.air, 0, 0);
    }

    public static Block getBlock(ItemStack stack) {
        Item item = stack.getItem();
        if (item instanceof ItemBlock)
            return ((ItemBlock) item).field_150939_a;
        return null;
    }

    public static boolean equals(Block block, ItemStack stack) {
        return (block == getBlock(stack));
    }

    public static String formatMessage(String inputString) {
        ChatComponentTranslation cht = new ChatComponentTranslation(inputString);
        return StatCollector.translateToLocal(cht.getUnformattedTextForChat());
    }

    public static int convertRGBcolorToInt(int r, int g, int b) {
        float divColor = 255.0F;
        Color tmpColor = new Color(r / divColor, g / divColor, b / divColor);
        return tmpColor.getRGB();
    }

    public static Color convertRGBtoColor(int r, int g, int b) {
        float divColor = 255.0F;
        Color tmpColor = new Color(r / divColor, g / divColor, b / divColor);
        return tmpColor;
    }

    public static void renderTooltip(int x, int y, List<String> tooltipData) {
        int color = convertRGBcolorToInt(0, 149, 218);
        int color2 = convertRGBcolorToInt(119, 187, 218);
        GL11.glDisable(32826);
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(2896);
        GL11.glDisable(2929);
        if (!tooltipData.isEmpty()) {
            int var5 = 0;
            FontRenderer fontRenderer = (Minecraft.getMinecraft()).fontRenderer;
            int var6;
            for (var6 = 0; var6 < tooltipData.size(); var6++) {
                int i = fontRenderer.getStringWidth(tooltipData.get(var6));
                if (i > var5)
                    var5 = i;
            }
            var6 = x + 12;
            int var7 = y - 12;
            int var9 = 8;
            if (tooltipData.size() > 1)
                var9 += 2 + (tooltipData.size() - 1) * 10;
            float z = 300.0F;
            drawGradientRect(var6 - 3, var7 - 4, z, var6 + var5 + 3, var7 - 3, color2, color2);
            drawGradientRect(var6 - 3, var7 + var9 + 3, z, var6 + var5 + 3, var7 + var9 + 4, color2, color2);
            drawGradientRect(var6 - 3, var7 - 3, z, var6 + var5 + 3, var7 + var9 + 3, color2, color2);
            drawGradientRect(var6 - 4, var7 - 3, z, var6 - 3, var7 + var9 + 3, color2, color2);
            drawGradientRect(var6 + var5 + 3, var7 - 3, z, var6 + var5 + 4, var7 + var9 + 3, color2, color2);
            int var12 = (color & 0xFFFFFF) >> 1 | color & 0xFF000000;
            drawGradientRect(var6 - 3, var7 - 3 + 1, z, var6 - 3 + 1, var7 + var9 + 3 - 1, color, var12);
            drawGradientRect(var6 + var5 + 2, var7 - 3 + 1, z, var6 + var5 + 3, var7 + var9 + 3 - 1, color, var12);
            drawGradientRect(var6 - 3, var7 - 3, z, var6 + var5 + 3, var7 - 3 + 1, color, color);
            drawGradientRect(var6 - 3, var7 + var9 + 2, z, var6 + var5 + 3, var7 + var9 + 3, var12, var12);
            for (int var13 = 0; var13 < tooltipData.size(); var13++) {
                String var14 = tooltipData.get(var13);
                fontRenderer.drawStringWithShadow(var14, var6, var7, -1);
                if (var13 == 0)
                    var7 += 2;
                var7 += 10;
            }
        }
    }

    public static void drawGradientRect(int par1, int par2, float z, int par3, int par4, int par5, int par6) {
        float var7 = (par5 >> 24 & 0xFF) / 255.0F;
        float var8 = (par5 >> 16 & 0xFF) / 255.0F;
        float var9 = (par5 >> 8 & 0xFF) / 255.0F;
        float var10 = (par5 & 0xFF) / 255.0F;
        float var11 = (par6 >> 24 & 0xFF) / 255.0F;
        float var12 = (par6 >> 16 & 0xFF) / 255.0F;
        float var13 = (par6 >> 8 & 0xFF) / 255.0F;
        float var14 = (par6 & 0xFF) / 255.0F;
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glDisable(3008);
        GL11.glBlendFunc(770, 771);
        GL11.glShadeModel(7425);
        Tessellator var15 = Tessellator.instance;
        var15.startDrawingQuads();
        var15.setColorRGBA_F(var8, var9, var10, var7);
        var15.addVertex(par3, par2, z);
        var15.addVertex(par1, par2, z);
        var15.setColorRGBA_F(var12, var13, var14, var11);
        var15.addVertex(par1, par4, z);
        var15.addVertex(par3, par4, z);
        var15.draw();
        GL11.glShadeModel(7424);
        GL11.glDisable(3042);
        GL11.glEnable(3008);
        GL11.glEnable(3553);
    }


    private static void removeEntityFromWorld(World world, Entity entity) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            player.closeScreen();
            world.playerEntities.remove(player);
            world.updateAllPlayersSleepingFlag();
            int i = entity.chunkCoordX;
            int j = entity.chunkCoordZ;
            if (entity.addedToChunk && world.getChunkProvider().chunkExists(i, j)) {
                world.getChunkFromChunkCoords(i, j).removeEntity(entity);
                (world.getChunkFromChunkCoords(i, j)).isModified = true;
            }
            world.loadedEntityList.remove(entity);
            world.onEntityRemoved(entity);
        }
        entity.isDead = false;
    }
}
