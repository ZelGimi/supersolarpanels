package com.Denfop.render;

import com.Denfop.Constants;
import com.Denfop.events.EventDarkQuantumSuitEffect;
import com.Denfop.handler.EntityStreak;
import com.Denfop.item.armour.ItemArmorImprovemedQuantum;
import com.Denfop.utils.StreakLocation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

public class EntityRendererStreak extends Render {
    private static final ResourceLocation texture = new ResourceLocation(Constants.TEXTURES_ITEMS + "effect.png");

    public void doRender(Entity entity, double par2, double par3, double par4, float par5, float par6) {
        renderStreak((EntityStreak) entity, par2, par3, par4, par5, par6);
    }

    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return texture;
    }

    private void renderStreak(EntityStreak entity, double par2, double par3, double par4, float par5, float par6) {
        if (entity.parent instanceof AbstractClientPlayer && !entity.isInvisible()) {
            AbstractClientPlayer player = (AbstractClientPlayer) entity.parent;
            Minecraft mc = Minecraft.getMinecraft();
            if (!entity.isInvisible() && (player != mc.thePlayer || mc.gameSettings.thirdPersonView != 0)) {
                if (player.inventory.armorInventory[2] == null)
                    return;
                if (!(player.inventory.armorInventory[2].getItem() instanceof ItemArmorImprovemedQuantum))
                    return;
                ArrayList<StreakLocation> loc = EventDarkQuantumSuitEffect.getPlayerStreakLocationInfo(player);
                GL11.glPushMatrix();
                GL11.glDisable(2884);
                GL11.glDisable(3008);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                GL11.glShadeModel(7425);
                float startGrad = 5.0F - par6;
                float endGrad = 20.0F - par6;
                for (int i = loc.size() - 2; i >= 0; i--) {
                    int start = i;
                    StreakLocation infoStart = loc.get(i);
                    float startAlpha = (i < endGrad) ? MathHelper.clamp_float(0.8F * i / endGrad, 0.0F, 0.8F) : ((i > (loc.size() - 2) - startGrad) ? MathHelper.clamp_float(0.8F * (loc.size() - 2 - i) / startGrad, 0.0F, 0.8F) : 0.8F);
                    if (player.worldObj.getWorldTime() - infoStart.lastTick > 40L)
                        break;
                    StreakLocation infoEnd = null;
                    double grad = 500.0D;
                    i--;
                    while (i >= 0) {
                        StreakLocation infoPoint = loc.get(i);
                        if (infoStart.isSprinting && loc.size() - 2 - i < 6) {
                            infoEnd = infoPoint;
                            start--;
                            i--;
                            break;
                        }
                        if (infoPoint.hasSameCoords(infoStart)) {
                            start--;
                            i--;
                            continue;
                        }
                        double grad1 = infoPoint.posZ - infoStart.posZ / (infoPoint.posX - infoStart.posX);
                        if (grad == grad1 && infoPoint.posY == infoStart.posY) {
                            infoEnd = infoPoint;
                            start--;
                            i--;
                            continue;
                        }
                        if (grad != 500.0D)
                            break;
                        grad = grad1;
                        infoEnd = infoPoint;
                        i--;
                    }
                    if (infoEnd != null) {
                        i += 2;
                        float endAlpha = (i < endGrad) ? MathHelper.clamp_float(0.8F * (i - 1) / endGrad, 0.0F, 0.8F) : ((i > (loc.size() - 1) - startGrad) ? MathHelper.clamp_float(0.8F * (loc.size() - 1 - i) / startGrad, 0.0F, 0.8F) : 0.8F);
                        double grad1 = infoStart.posX - RenderManager.renderPosX;
                        double posY = infoStart.posY - RenderManager.renderPosY;
                        double posZ = infoStart.posZ - RenderManager.renderPosZ;
                        double nextPosX = infoEnd.posX - RenderManager.renderPosX;
                        double nextPosY = infoEnd.posY - RenderManager.renderPosY;
                        double nextPosZ = infoEnd.posZ - RenderManager.renderPosZ;
                        Tessellator tessellator = Tessellator.instance;
                        GL11.glPushMatrix();
                        GL11.glTranslated(grad1, posY, posZ);
                        int ii = entity.getBrightnessForRender(0.0F);
                        int j = ii % 65536;
                        int k = ii / 65536;
                        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j / 1.0F, k / 1.0F);
                        RenderHelper.disableStandardItemLighting();
                        GL11.glDisable(2896);

                        mc.renderEngine.bindTexture(texture);
                        tessellator.startDrawingQuads();
                        tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, startAlpha);
                        tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, infoStart.startU, 1.0D);
                        tessellator.addVertexWithUV(0.0D, (0.0F + infoStart.height), 0.0D, infoStart.startU, 0.0D);
                        tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, endAlpha);
                        double endTex = infoEnd.startU - start + i;
                        if (endTex > infoStart.startU)
                            endTex--;
                        double distX = infoStart.posX - infoEnd.posX;
                        double distZ = infoStart.posZ - infoEnd.posZ;
                        double scales = Math.sqrt(distX * distX + distZ * distZ) / infoStart.height;
                        boolean far = (scales > 1.0D);
                        if (scales < 1.0D) ;
                        while (scales > 1.0D) {
                            endTex++;
                            scales--;
                        }
                        tessellator.addVertexWithUV(nextPosX - grad1, nextPosY - posY + infoEnd.height, nextPosZ - posZ, endTex, 0.0D);
                        tessellator.addVertexWithUV(nextPosX - grad1, nextPosY - posY, nextPosZ - posZ, endTex, 1.0D);
                        tessellator.draw();
                        GL11.glEnable(2896);
                        RenderHelper.enableStandardItemLighting();
                        GL11.glPopMatrix();
                    }
                }
                GL11.glShadeModel(7424);
                GL11.glDisable(3042);
                GL11.glEnable(3008);
                GL11.glEnable(2884);
                GL11.glPopMatrix();
            }
        }
    }
}
