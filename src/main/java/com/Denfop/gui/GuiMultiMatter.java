package com.Denfop.gui;

import com.Denfop.container.ContainerMultiMatter;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.ContainerBase;
import ic2.core.GuiIC2;
import ic2.core.IC2;
import ic2.core.util.DrawUtil;
import ic2.core.util.GuiTooltipHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.FluidStack;

@SideOnly(Side.CLIENT)
public class GuiMultiMatter extends GuiIC2 {
    public ContainerMultiMatter container;
    public String progressLabel, amplifierLabel;

    public GuiMultiMatter(ContainerMultiMatter container1) {
        super(container1);
        this.container = container1;
        this.progressLabel = StatCollector.translateToLocal("ic2.Matter.gui.info.progress");
        this.amplifierLabel = StatCollector.translateToLocal("ic2.Matter.gui.info.amplifier");
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRendererObj.drawString(this.progressLabel, 8, 22, 4210752);
        this.fontRendererObj.drawString((this.container.base).getProgressAsString(), 18, 31, 4210752);
        if ((this.container.base).scrap > 0) {
            this.fontRendererObj.drawString(this.amplifierLabel, 8, 46, 4210752);
            this.fontRendererObj.drawString("" + (this.container.base).scrap, 8, 58, 4210752);
        }
        super.drawGuiContainerForegroundLayer(par1, par2);
        FluidStack fluidstack = (this.container.base).getFluidStackfromTank();
        if (fluidstack != null) {
            String tooltip = fluidstack.getFluid().getName() + ": " + fluidstack.amount
                    + StatCollector.translateToLocal("ic2.generic.text.mb");
            GuiTooltipHelper.drawAreaTooltip(par1 - this.guiLeft, par2 - this.guiTop, tooltip, 99, 25, 112, 73);
        }
    }

    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        super.drawGuiContainerBackgroundLayer(f, x, y);
        if ((this.container.base).getTankAmount() > 0) {
            IIcon fluidIcon = (this.container.base).getFluidTank().getFluid().getFluid().getIcon();
            if (fluidIcon != null) {
                drawTexturedModalRect(this.xoffset + 96, this.yoffset + 22, 176, 0, 20, 55);
                this.mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
                int liquidHeight = (this.container.base).gaugeLiquidScaled(47);
                DrawUtil.drawRepeated(fluidIcon, (this.xoffset + 100), (this.yoffset + 26 + 47 - liquidHeight), 12.0D,
                        liquidHeight, this.zLevel);
                this.mc.renderEngine.bindTexture(getResourceLocation());
                drawTexturedModalRect(this.xoffset + 100, this.yoffset + 26, 176, 55, 12, 47);
            }
        }
    }

    public String getName() {
        return this.container.base.getInventoryName();
    }

    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(IC2.textureDomain, "textures/gui/GUIMatter.png");
    }
}
