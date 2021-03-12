package com.Denfop.gui;

import com.Denfop.container.ContainerBaseMolecular;
import com.Denfop.tiles.base.TileEntityMolecularTransformer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.recipe.RecipeOutput;
import ic2.core.ContainerBase;
import ic2.core.IC2;
import ic2.core.network.NetworkManager;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

@SideOnly(Side.CLIENT)
public class GuiMolecularTransformer extends GuiSSP {
    public ContainerBaseMolecular<? extends TileEntityMolecularTransformer> container;

    public GuiMolecularTransformer(ContainerBaseMolecular<? extends TileEntityMolecularTransformer> container1) {
        super(container1);
        this.container = container1;
    }

    public void initGui() {
        super.initGui();
        this.buttonList.add(new GuiButton(0, (this.width - this.xSize) / 2 + 180, (this.height - this.ySize) / 2 + 3, 17, 17, I18n.format("button.rg")));
    }

    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        super.drawGuiContainerBackgroundLayer(f, x, y);
        String input = I18n.format("gui.MolecularTransformer.input") + ": ";
        String output = I18n.format("gui.MolecularTransformer.output") + ": ";
        String energyPerOperation = I18n.format("gui.MolecularTransformer.energyPerOperation") + ": ";

        //
        String energyPerTick = I18n.format("gui.AdvancedSolarPanel.energyPerTick") + ": ";
        String progress = I18n.format("gui.MolecularTransformer.progress") + ": ";
        float chargeLevel = (15.0F * this.container.base.getChargeLevel());

        RecipeOutput output1 = this.container.base.getOutput();
        if (chargeLevel > 0 && !this.container.base.inputSlot.isEmpty() && output1 != null) {
            drawTexturedModalRect(this.xoffset + 23, this.yoffset + 48, 221, 7, 10, (int) chargeLevel);
            this.fontRendererObj.drawString(input + this.container.base.inputSlot.get().getDisplayName(), this.xoffset + 60 + 10, this.yoffset + 25, 13487565);


            this.fontRendererObj.drawString(output + output1.items.get(0).getDisplayName(), this.xoffset + 60 + 10, this.yoffset + 25 + 11, 13487565);

            this.fontRendererObj.drawString(energyPerOperation + output1.metadata.getInteger("energy") + " EU", this.xoffset + 60 + 10, this.yoffset + 25 + 22, 13487565);
            if (this.container.base.getProgress() * 100 <= 100)
                this.fontRendererObj.drawString(progress + MathHelper.floor_double(this.container.base.getProgress() * 100) + "%", this.xoffset + 60 + 10, this.yoffset + 25 + 33, 13487565);
            if (this.container.base.getProgress() * 100 > 100)
                this.fontRendererObj.drawString(progress + MathHelper.floor_double(this.container.base.getProgress() * 100) + "%", this.xoffset + 60 + 10, this.yoffset + 25 + 33, 13487565);

            this.fontRendererObj.drawString(energyPerTick + (int) this.container.base.getPower(), this.xoffset + 60 + 10, this.yoffset + 25 + 44, 13487565);


        }

    }

    protected void actionPerformed(GuiButton guibutton) {
        super.actionPerformed(guibutton);
        if (guibutton.id == 0) {
            IC2.network.get().initiateClientTileEntityEvent(this.container.base, 0);

        }
    }

    public String getName() {
        return StatCollector.translateToLocal("blockMolecularTransformer.name");
    }

    public ResourceLocation getResourceLocation() {

        //   return  new ResourceLocation("supersolarpanel", "textures/gui/guiMolecularTransformerNew.png");}
        if (this.container.base.redstoneMode == 1) {

            return new ResourceLocation("supersolarpanel", "textures/gui/guiMolecularTransformerNew_chemical_green.png");
        } else if (this.container.base.redstoneMode == 2) {

            return new ResourceLocation("supersolarpanel", "textures/gui/guiMolecularTransformerNew_gold.png");
        } else if (this.container.base.redstoneMode == 3) {

            return new ResourceLocation("supersolarpanel", "textures/gui/guiMolecularTransformerNew_red.png");
        } else if (this.container.base.redstoneMode == 4) {

            return new ResourceLocation("supersolarpanel", "textures/gui/guiMolecularTransformerNew_silver.png");
        } else if (this.container.base.redstoneMode == 5) {

            return new ResourceLocation("supersolarpanel", "textures/gui/guiMolecularTransformerNew_violet.png");
        } else if (this.container.base.redstoneMode == 6) {

            return new ResourceLocation("supersolarpanel", "textures/gui/guiMolecularTransformerNew_blue.png");
        } else if (this.container.base.redstoneMode == 7) {

            return new ResourceLocation("supersolarpanel", "textures/gui/guiMolecularTransformerNew_green.png");
        } else {

            return new ResourceLocation("supersolarpanel", "textures/gui/guiMolecularTransformerNew.png");
        }
    }
}
