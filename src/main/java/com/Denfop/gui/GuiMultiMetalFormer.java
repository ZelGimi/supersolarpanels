package com.Denfop.gui;

import com.Denfop.container.ContainerMultiMetalFormer;
import com.Denfop.tiles.base.TileEntityMultiMachine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.IC2;
import ic2.core.Ic2Items;
import ic2.core.network.NetworkManager;
import ic2.core.util.GuiTooltipHelper;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiMultiMetalFormer extends GuiMultiMachine {

    public GuiMultiMetalFormer(ContainerMultiMetalFormer container1) {
        super(container1);
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String tooltip = "";
        GL11.glPushAttrib(64);
        RenderItem renderItem = new RenderItem();
        RenderHelper.enableGUIStandardItemLighting();
        switch (((TileEntityMultiMachine) this.container.base).getMode()) {
            case 0:
                renderItem.renderItemIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, Ic2Items.copperCableItem, 18, 8);
                tooltip = StatCollector.translateToLocal("ic2.MetalFormer.gui.switch.Extruding");
                break;
            case 1:
                renderItem.renderItemIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, Ic2Items.ForgeHammer, 18, 8);
                tooltip = StatCollector.translateToLocal("ic2.MetalFormer.gui.switch.Rolling");
                break;
            case 2:
                renderItem.renderItemIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, Ic2Items.cutter, 18, 8);
                tooltip = StatCollector.translateToLocal("ic2.MetalFormer.gui.switch.Cutting");
                break;
        }
        GuiTooltipHelper.drawAreaTooltip(mouseX - this.guiLeft + 4, mouseY - this.guiTop, tooltip, 6, 22, 26, 42);
        GL11.glPopAttrib();
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    protected void actionPerformed(GuiButton guibutton) {
        super.actionPerformed(guibutton);
        IC2.network.get().initiateClientTileEntityEvent((TileEntity) this.container.base,
                guibutton.id);
    }

    public void initGui() {
        super.initGui();
        this.buttonList.add(new GuiButton(0, (this.width - this.xSize) / 2 + 6 + 10, (this.height - this.ySize + 1) / 2 + 6, 20, 20, ""));
    }

}
