package com.Denfop.integration.DE;


import com.brandon3055.draconicevolution.common.items.weapons.BowHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class RenderBowModel implements IItemRenderer {
    private final boolean draconic;

    private final IModelCustom[] wyvernModels = new IModelCustom[4];

    private final IModelCustom[] draconicModels = new IModelCustom[4];

    private final IModelCustom arrow;

    public RenderBowModel(boolean draconic) {
        this.draconic = draconic;
        int i;
        for (i = 0; i < 4; i++)
            this.wyvernModels[i] = AdvancedModelLoader.loadModel(ResourceHandler.getResource("models/tools/WyvernBow0" + i + ".obj"));
        for (i = 0; i < 4; i++)
            this.draconicModels[i] = AdvancedModelLoader.loadModel(ResourceHandler.getResource("models/tools/DraconicBow0" + i + ".obj"));
        this.arrow = AdvancedModelLoader.loadModel(ResourceHandler.getResource("models/tools/ArrowCommon.obj"));
    }

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return false;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
        IModelCustom activeModel;
        GL11.glPushMatrix();
        float j = 0.0F;
        int selection = 0;
        BowHandler.BowProperties properties = null;
        if (data.length >= 2 && data[1] instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) data[1];
            j = player.getItemInUseDuration();
            if (j > 0.0F) {
                properties = new BowHandler.BowProperties(item, player);
                if (j > properties.getDrawTicks())
                    j = properties.getDrawTicks();
                j /= properties.getDrawTicks();
                int j2 = (int) (j * 3.0F);
                if (j2 < 0) {
                    j2 = 0;
                } else if (j2 > 3) {
                    j2 = 3;
                }
                selection = j2;
            }
        }
        if (this.draconic) {
            activeModel = this.draconicModels[selection];
            ResourceHandler.bindResource("textures/models/tools/DraconicBow0" + selection + ".png");
        } else {
            activeModel = this.draconicModels[selection];
            ResourceHandler.bindResource("textures/models/tools/WyvernBow0" + selection + ".png");
        }
        if (activeModel != null)
            doRender(activeModel, type, (j > 0.0F) ? selection : -1, properties);
        GL11.glPopMatrix();
    }

    private void doRender(IModelCustom modelCustom, IItemRenderer.ItemRenderType type, int drawState, BowHandler.BowProperties properties) {
        if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
            GL11.glScaled(0.8D, 0.8D, 0.8D);
            GL11.glTranslated(0.7D, 0.0D, 0.2D);
            GL11.glRotatef(87.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(190.0F, 1.0F, 0.0F, 0.0F);
        } else if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glScaled(0.8D, 0.8D, 0.8D);
            GL11.glTranslated(0.7D, 0.7D, 0.2D);
            GL11.glRotatef(130.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
        } else if (type == IItemRenderer.ItemRenderType.INVENTORY) {
            GL11.glScalef(6.0F, 6.0F, 6.0F);
            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslated(0.0D, 0.0D, 1.5D);
        } else if (type == IItemRenderer.ItemRenderType.ENTITY) {
            GL11.glScaled(0.8D, 0.8D, 0.8D);
            GL11.glTranslated(0.25D, 0.7D, 0.2D);
            GL11.glRotatef(130.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
        }
        modelCustom.renderAll();
        if (drawState != -1) {
            GL11.glTranslated(0.3D, 0.151D, -0.2D + ((drawState == 1) ? 0.0D : ((drawState == 2) ? 0.55D : ((drawState == 3) ? 1.0D : -0.7D))));
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            if (properties != null && properties.energyBolt) {
                ResourceHandler.bindResource("textures/models/reactorCore.png");
                this.arrow.renderAll();
                GL11.glTranslated(0.0D, -0.025D, 0.0D);
                GL11.glEnable(3042);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.6F);
                GL11.glScaled(1.05D, 1.05D, 1.05D);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.4F);
                GL11.glScaled(1.05D, 1.05D, 1.05D);
                this.arrow.renderAll();
            } else {
                ResourceHandler.bindResource("textures/models/tools/ArrowCommon.png");
                this.arrow.renderAll();
            }
        }
    }
}
