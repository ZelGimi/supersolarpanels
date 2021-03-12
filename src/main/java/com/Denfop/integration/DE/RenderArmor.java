package com.Denfop.integration.DE;

import com.Denfop.Constants;
import com.brandon3055.draconicevolution.common.handler.ConfigHandler;
import com.brandon3055.draconicevolution.common.utills.LogHelper;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class RenderArmor implements IItemRenderer {
    private ItemArmor armor;

    public RenderArmor() {
    }

    public RenderArmor(ItemArmor armor) {
        this.armor = armor;
    }

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack stack, Object... data) {
        if (ConfigHandler.useOldArmorModel) {
            LogHelper.error("You must restart the game for armor model change to effect the armor items!!!");
            return;
        }
        GL11.glPushMatrix();
        ResourceHandler.bindResource(this.armor.getArmorTexture(stack, null, 0, null).replace(Constants.TEXTURES + ":", ""));
        if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON || type == IItemRenderer.ItemRenderType.EQUIPPED) {
            GL11.glTranslated(0.5D, 0.5D, 0.5D);
            GL11.glRotated(180.0D, 0.0D, 1.0D, 0.0D);
        }
        GL11.glTranslated(0.0D, (this.armor.armorType == 0) ? -0.25D : ((this.armor.armorType == 1) ? 0.42D : ((this.armor.armorType == 2) ? 1.05D : 1.5D)), 0.0D);
        GL11.glRotated(180.0D, -1.0D, 0.0D, 1.0D);
        this.armor.getArmorModel(null, stack, 0).render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }
}
