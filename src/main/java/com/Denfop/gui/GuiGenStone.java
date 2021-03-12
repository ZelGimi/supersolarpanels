package com.Denfop.gui;

import com.Denfop.Constants;
import com.Denfop.container.ContainerGenStone;
import com.Denfop.tiles.Mechanism.TileEntityGenerationStone;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.ContainerBase;
import ic2.core.GuiIC2;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GuiGenStone extends GuiIC2 {
    public ContainerGenStone<? extends TileEntityGenerationStone> container;

    public GuiGenStone(ContainerGenStone<? extends TileEntityGenerationStone> container1) {
        super(container1);
        this.container = container1;
    }

    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        super.drawGuiContainerBackgroundLayer(f, x, y);
        int chargeLevel = (int) (14.0F * this.container.base.getChargeLevel());
        int progress = (int) (32.0F * this.container.base.getProgress());
        if (chargeLevel > 0)
            drawTexturedModalRect(this.xoffset + 56 + 1 - 48, this.yoffset + 36 + 14 - chargeLevel, 176, 14 - chargeLevel, 14, chargeLevel);
        if (progress > 0) {

            drawTexturedModalRect(this.xoffset + 51, this.yoffset + 17 + 17 + 1 - 8 + 1, 176 + 1, 32, 25, progress + 1);
        }
    }

    public String getName() {
        return this.container.base.getInventoryName();
    }

    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(Constants.TEXTURES, "textures/gui/GUIGenStone.png");
    }
}
