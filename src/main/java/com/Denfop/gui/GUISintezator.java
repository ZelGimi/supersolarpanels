package com.Denfop.gui;

import com.Denfop.Config;
import com.Denfop.api.IPanel;
import com.Denfop.container.ContainerSinSolarPanel;
import com.Denfop.tiles.base.TileSintezator;
import com.Denfop.utils.GuiNumberUtils;
import com.Denfop.utils.NBTData;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GUISintezator extends GuiContainer {
    private static ResourceLocation tex;

    static {
        GUISintezator.tex = new ResourceLocation("supersolarpanel", "textures/gui/GUI_Sintezator_Slots.png");
    }

    public TileSintezator tileentity;

    public GUISintezator(final InventoryPlayer inventoryplayer, final TileSintezator tileentitysolarpanel) {
        super(new ContainerSinSolarPanel(inventoryplayer, tileentitysolarpanel));
        this.tileentity = tileentitysolarpanel;
        this.allowUserInput = false;
        this.xSize = 194;
        this.ySize = 168;
    }

    protected void drawGuiContainerForegroundLayer(final int par1, final int par2) {
        final String formatPanelName = I18n.format(this.tileentity.panelName);
        final int nmPos = (this.xSize - this.fontRendererObj.getStringWidth(formatPanelName)) / 2;
        this.fontRendererObj.drawString(formatPanelName, nmPos, 7, 7718655);
        final String storageString = I18n.format("gui.AdvancedSolarPanel.storage") + ": ";
        final String maxOutputString = I18n.format("gui.AdvancedSolarPanel.maxOutput") + ": ";
        final String generatingString = I18n.format("gui.AdvancedSolarPanel.generating") + ": ";
        final String energyPerTickString = I18n.format("gui.AdvancedSolarPanel.energyPerTick");


        int[] myArray2;
        int[] myArray3;

        myArray2 = new int[10];
        myArray3 = new int[10];
        for (int i = 0; i < 10; i++) {
            if (this.tileentity.chargeSlots[i] != null && this.tileentity.chargeSlots[i].getItem() instanceof IPanel) {
                ItemStack itemstack = this.tileentity.chargeSlots[i];
                int meta = itemstack.getMaxDamage();
                NBTTagCompound nbt = NBTData.getOrCreateNbtData(itemstack);


                int storage = nbt.getInteger("basestorage");
                int output = nbt.getInteger("output");

                int p = this.tileentity.chargeSlots[i].stackSize;
                if (p <= Config.limit) {

                    myArray2[i] = storage * p;
                    myArray3[i] = output * p;

                } else {

                    myArray2[i] = storage * Config.limit;
                    myArray3[i] = output * Config.limit;

                }
            }
        }


        int sum2 = 0;
        int sum3 = 0;
        for (int i = 0; i < 9; i++) {
            sum2 = sum2 + myArray2[i];
            sum3 = sum3 + myArray3[i];

        }
        if (sum2 >= 2000000000) {
            sum2 = 2000000000;
        }

        if (sum3 >= 2000000000) {
            sum3 = 2000000000;
        }
        if (sum2 <= 0) {
            sum2 = 0;
        }
        if (sum3 <= 0) {
            sum3 = 0;
        }

        String maxstorage_1 = GuiNumberUtils.getString(sum2);

        String maxstorage_2 = GuiNumberUtils.getString(this.tileentity.storage);
        this.fontRendererObj.drawString(storageString + maxstorage_2 + "/" + maxstorage_1, 50, 22, 13487565);


//TODO

        String output = GuiNumberUtils.getString(sum3);
        this.fontRendererObj.drawString(maxOutputString + output + (" " + energyPerTickString), 50, 32, 13487565);


//

        String generation = GuiNumberUtils.getString(this.tileentity.generating);
        this.fontRendererObj.drawString(generatingString + generation + " " + energyPerTickString, 50, 42, 13487565);


    }

    protected void drawGuiContainerBackgroundLayer(final float f, final int i, final int j) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);


        this.mc.renderEngine.bindTexture(GUISintezator.tex);


        final int h = (this.width - this.xSize) / 2;
        final int k = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(h, k, 0, 0, this.xSize, this.ySize);
        if (this.tileentity.storage > 0 || this.tileentity.storage <= this.tileentity.maxStorage) {
            final float l = this.tileentity.gaugeEnergyScaled(24);

            this.drawTexturedModalRect(h + 19, k + 24, 195, 0, (int) (l + 1), 14);
        }
        if (this.tileentity.skyIsVisible) {
            if (this.tileentity.sunIsUp) {
                this.drawTexturedModalRect(h + 24, k + 42, 195, 15, 14, 14);
            } else if (!this.tileentity.sunIsUp) {
                this.drawTexturedModalRect(h + 24, k + 42, 210, 15, 14, 14);
            }
        }


    }
}
