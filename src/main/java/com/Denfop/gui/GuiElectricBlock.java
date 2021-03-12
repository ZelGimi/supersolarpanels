package com.Denfop.gui;

import com.Denfop.Constants;
import com.Denfop.container.ContainerElectricBlock;
import com.Denfop.tiles.base.TileEntityElectricBlock;
import com.Denfop.utils.GuiNumberUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.IC2;
import ic2.core.network.NetworkManager;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiElectricBlock extends GuiContainer {
    private static final ResourceLocation background = new ResourceLocation(Constants.TEXTURES, "textures/gui/GUIElectricBlockEuRf.png");
    private final ContainerElectricBlock container;
    private final String armorInv;
    private final String level;
    private final String name;

    public GuiElectricBlock(ContainerElectricBlock container1) {
        super(container1);
        this.ySize = 196;
        this.container = container1;
        this.armorInv = StatCollector.translateToLocal("ic2.EUStorage.gui.info.armor");
        this.level = StatCollector.translateToLocal("ic2.EUStorage.gui.info.level");

        this.name = container1.base.getInventoryName();
    }

    public void initGui() {
        super.initGui();
        this.buttonList.add(new GuiButton(0, (this.width - this.xSize) / 2 + 110, (this.height - this.ySize) / 2 + 34, 22, 17, I18n.format("button.rg")));
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRendererObj.drawString(this.name, (this.xSize - this.fontRendererObj.getStringWidth(this.name)) / 2, 6, 4210752);
        this.fontRendererObj.drawString(this.armorInv, 8, this.ySize - 126 + 3, 4210752);


        this.fontRendererObj.drawString(GuiNumberUtils.getString((float) this.container.base.energy), 90, 55, 4210752);
        this.fontRendererObj.drawString(GuiNumberUtils.getString((float) this.container.base.energy2), 138, 55, 4210752);
        String output = StatCollector.translateToLocalFormatted("ic2.EUStorage.gui.info.output", Integer.valueOf(this.container.base.getOutput()));
        this.fontRendererObj.drawString(output, 85, 70, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(background);
        int j = (this.width - this.xSize) / 2;
        int k = (this.height - this.ySize) / 2;
        drawTexturedModalRect(j, k, 0, 0, this.xSize, this.ySize);
        if (this.container.base.energy > 0.0D) {
            int i1 = (int) (24.0F * this.container.base.getChargeLevel());
            drawTexturedModalRect(j + 79 + 6 - 2 - 1, k + 34, 176, 14, i1 + 1, 16);
        }
        if (this.container.base.energy2 > 0.0D) {

            int i1 = (int) (24.0F * this.container.base.getChargeLevel1());
            drawTexturedModalRect(j + 79 + 54 + 2, k + 34, 176, 31, i1 + 1, 16);
        }


    }

    protected void actionPerformed(GuiButton guibutton) {
        super.actionPerformed(guibutton);
        if (guibutton.id == 0)
            IC2.network.get().initiateClientTileEntityEvent(this.container.base, 0);
    }
}
