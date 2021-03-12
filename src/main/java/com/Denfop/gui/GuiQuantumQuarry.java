package com.Denfop.gui;

import com.Denfop.Constants;
import com.Denfop.container.ContainerQuantumQuarry;
import com.Denfop.tiles.Mechanism.TileEntityQuantumQuarry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.ContainerBase;
import ic2.core.GuiIC2;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GuiQuantumQuarry extends GuiIC2 {
    public ContainerQuantumQuarry container;

    public GuiQuantumQuarry(ContainerQuantumQuarry container1) {
        super(container1);
        this.container = container1;
    }

    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        super.drawGuiContainerBackgroundLayer(f, x, y);

        int chargeLevel = (int) (48.0F * ((TileEntityQuantumQuarry) this.container.base).getEnergy() / ((TileEntityQuantumQuarry) this.container.base).maxEnergy);

        if (chargeLevel > 0)
            drawTexturedModalRect(this.xoffset + 140 + 1 + 5, this.yoffset + 28 + 48 - chargeLevel, 176, 48 - chargeLevel, 48, chargeLevel);

        this.fontRendererObj.drawString("" + ((TileEntityQuantumQuarry) this.container.base).getblock, this.xoffset + 146, this.yoffset + 7, 7718655);

    }

    public String getName() {
        return this.container.base.getInventoryName();
    }

    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(Constants.TEXTURES, "textures/gui/GUIQuantumQuerry.png");
    }
}
