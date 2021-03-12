package com.Denfop.gui;

import ic2.core.ContainerBase;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public abstract class GuiSSP extends GuiContainer {
    public ContainerBase<? extends IInventory> container;

    protected int xoffset;

    protected int yoffset;

    public GuiSSP(ContainerBase<? extends IInventory> container) {
        this(container, 220, 193);
    }

    public GuiSSP(ContainerBase<? extends IInventory> container, int ySize) {
        this(container, 220, 193);
    }

    public GuiSSP(ContainerBase<? extends IInventory> container, int xSize, int ySize) {
        super(container);
        this.container = container;
        this.ySize = ySize;
        this.xSize = xSize;
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRendererObj.drawString(getName(), (this.xSize - this.fontRendererObj.getStringWidth(getName())) / 2, 6, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(getResourceLocation());
        this.xoffset = (this.width - this.xSize) / 2;
        this.yoffset = (this.height - this.ySize) / 2;
        drawTexturedModalRect(this.xoffset, this.yoffset, 0, 0, this.xSize, this.ySize);

    }

    public abstract String getName();

    public abstract ResourceLocation getResourceLocation();
}
