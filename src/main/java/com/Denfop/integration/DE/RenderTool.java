package com.Denfop.integration.DE;


import com.brandon3055.draconicevolution.client.render.IRenderTweak;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class RenderTool implements IItemRenderer {
    private final IModelCustom toolModel;

    private final String toolTexture;

    private final IRenderTweak tool;

    public RenderTool(String model, String texture, IRenderTweak tool) {
        this.tool = tool;
        this.toolModel = AdvancedModelLoader.loadModel(ResourceHandler.getResource(model));
        this.toolTexture = texture;
    }

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return (type == IItemRenderer.ItemRenderType.ENTITY && helper == IItemRenderer.ItemRendererHelper.ENTITY_ROTATION);
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
        GL11.glPushMatrix();
        ResourceHandler.bindResource(this.toolTexture);
        this.tool.tweakRender(type);
        this.toolModel.renderAll();
        GL11.glPopMatrix();
    }
}
