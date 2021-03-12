package com.Denfop.gui;

import com.Denfop.Constants;
import com.Denfop.container.ContainerStandardMachine;
import com.Denfop.tiles.Mechanism.TileEntityAlloySmelter;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.ContainerBase;
import ic2.core.GuiIC2;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GuiAlloySmelter extends GuiIC2 {
    public ContainerStandardMachine<? extends TileEntityAlloySmelter> container;

    public GuiAlloySmelter(ContainerStandardMachine<? extends TileEntityAlloySmelter> container1) {
        super(container1);
        this.container = container1;
    }

    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        super.drawGuiContainerBackgroundLayer(f, x, y);
        int chargeLevel = (int) (14.0F * this.container.base.getChargeLevel());
        int progress = (int) (24.0F * this.container.base.getProgress());
        if (chargeLevel > 0)
            drawTexturedModalRect(this.xoffset + 56 + 1, this.yoffset + 36 + 14 - chargeLevel, 176, 14 - chargeLevel, 14, chargeLevel);
        if (progress > 0)
            drawTexturedModalRect(this.xoffset + 79, this.yoffset + 34, 176, 14, progress + 1, 16);
    }

    public String getName() {
        return this.container.base.getInventoryName();
    }

    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(Constants.TEXTURES, "textures/gui/GUIAlloySmelter.png");
    }
}
