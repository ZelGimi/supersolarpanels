package com.Denfop.gui;

import com.Denfop.api.module.*;
import com.Denfop.container.ContainerAdvSolarPanel;
import com.Denfop.item.Modules.ItemWirelessModule;
import com.Denfop.item.Modules.module7;
import com.Denfop.tiles.base.TileEntitySolarPanel;
import com.Denfop.utils.GUIUtilsStorage;
import com.Denfop.utils.GuiNumberUtils;
import com.Denfop.utils.NBTData;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.List;

public class GuiAdvSolarPanel extends GuiContainer {
    private static final ResourceLocation tex = new ResourceLocation("supersolarpanel", "textures/gui/GUIAdvancedSolarPanel.png");
    private static final ResourceLocation tex1 = new ResourceLocation("supersolarpanel", "textures/gui/GUInetherSolarPanel.png");
    private static final ResourceLocation tex2 = new ResourceLocation("supersolarpanel", "textures/gui/GUIendSolarPanel.png");
    private static final ResourceLocation tex3 = new ResourceLocation("supersolarpanel", "textures/gui/GUIearthSolarPanel.png");
    private static final ResourceLocation tex4 = new ResourceLocation("supersolarpanel", "textures/gui/GUIaerSolarPanel.png");
    private static final ResourceLocation tex5 = new ResourceLocation("supersolarpanel", "textures/gui/GUIrainSolarPanel.png");
    public TileEntitySolarPanel tileentity;

    public GuiAdvSolarPanel(InventoryPlayer inventoryplayer, TileEntitySolarPanel tileentitysolarpanel) {
        super(new ContainerAdvSolarPanel(inventoryplayer, tileentitysolarpanel));
        this.tileentity = tileentitysolarpanel;
        this.allowUserInput = false;
        this.xSize = 194;
        this.ySize = 238;
    }

    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10.0D, places);
        return Math.round(value * scale) / scale;
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        String formatPanelName = I18n.format(this.tileentity.panelName);
        int nmPos = (this.xSize - this.fontRendererObj.getStringWidth(formatPanelName)) / 2;
        this.fontRendererObj.drawString(formatPanelName, nmPos, 4, 7718655);
        String storageString = I18n.format("gui.SuperSolarPanel.storage") + ": ";
        String maxOutputString = I18n.format("gui.SuperSolarPanel.maxOutput") + ": ";
        String generatingString = I18n.format("gui.SuperSolarPanel.generating") + ": ";
        String energyPerTickString = I18n.format("gui.SuperSolarPanel.energyPerTick");
        String energyPerTickString1 = I18n.format("gui.SuperSolarPanel.energyPerTick1");
        String tierString = I18n.format("gui.ssp.tier") + ": ";
        String ModulesString = I18n.format("ssp.genday");
        String ModulesString1 = I18n.format("ssp.gennight");
        String ModulesString2 = I18n.format("ssp.storage");
        String ModulesString3 = I18n.format("ssp.output");
        String ModulesString4 = I18n.format("ssp.tier1");
        String ModulesString5 = I18n.format("ssp.tier2");
        String ModulesString6 = I18n.format("ssp.moduletype1");
        String ModulesString61 = I18n.format("ssp.moduletype2");
        String ModulesString62 = I18n.format("ssp.moduletype3");
        String ModulesString63 = I18n.format("ssp.moduletype4");
        String ModulesString64 = I18n.format("ssp.moduletype5");
        String ModulesString65 = I18n.format("ssp.moduletype6");
        String ModulesString66 = I18n.format("ssp.moduletype7");
        String ModulesString7 = I18n.format("ssp.rfmodule");
        String rfstorageString = I18n.format("ssp.rfstorage");
        String ModulesString8 = I18n.format("ssp.modulewirelles");
        String ModulesString9 = I18n.format("ssp.modulewirelles1");
        String ModulesString10 = I18n.format("ssp.modulewirelles2");

        int tierplus = 0;
        int minus = 0;
        for (int i = 0; i < 9; i++) {


            if (this.tileentity.chargeSlots[i] != null && this.tileentity.chargeSlots[i].getItem() instanceof com.Denfop.item.Modules.module7) {
                int kk = this.tileentity.chargeSlots[i].getItemDamage();
                if (kk == 1) {
                    tierplus++;
                } else if (kk == 2) {
                    minus++;
                }
            }
        }
        int[] c;
        c = new int[9];
        int[] d;
        d = new int[9];
        for (int i = 0; i < 9; i++) {
            if (this.tileentity.chargeSlots[i] != null && this.tileentity.chargeSlots[i].getItem() instanceof IModulPanel) {
                int g = this.tileentity.chargeSlots[i].getItemDamage();
                if (this.tileentity.tier + tierplus - minus >= g + 1) {


                    List<Integer> list = IModulPanel.getData(this.tileentity.chargeSlots[i]);
                    c[i] = list.get(2);
                    d[i] = list.get(3);

                }
            }
        }
        int sum1 = 0;
        int sum2 = 0;

        for (int i = 0; i < 9; i++) {
            sum1 = sum1 + c[i];
            sum2 = sum2 + d[i];
        }
        int[] maxstorage1;
        maxstorage1 = new int[9];
        double maxstorage_dob = 0;
        for (int j = 0; j < 9; j++) {
            if (this.tileentity.chargeSlots[j] != null && this.tileentity.chargeSlots[j].getItem() instanceof IModulStorage) {
                maxstorage1[j] = IModulStorage.getData(this.tileentity.chargeSlots[j]).get(0);
                if (maxstorage1[j] != 0) {
                    maxstorage_dob = maxstorage_dob + maxstorage1[j];
                }

            }
        }
        float[] a = new float[4];
        a[0] = (float) ((this.tileentity.p + sum1) + (this.tileentity.p + sum1) * (maxstorage_dob / 100));
        if (a[0] > 2000000000) {
            a[0] = 2000000000;
        } else if (a[0] < 0) {
            a[0] = 0;
        }


        String maxstorage_1 = GUIUtilsStorage.getString(a[0]);
        String maxstorage_2 = GUIUtilsStorage.getString(this.tileentity.storage);

        this.fontRendererObj.drawString(storageString + maxstorage_2 + "/" + maxstorage_1, 50, 16 - 2, 13487565);
        String rf = GUIUtilsStorage.getString(this.tileentity.storage2);
        String rf1 = GUIUtilsStorage.getString(this.tileentity.maxStorage2);
        this.fontRendererObj.drawString(rfstorageString + rf + "/" + rf1, 50, 20 + 2, 13487565);
        if (maxstorage_dob != 0)
            this.fontRendererObj.drawString(ModulesString2 + maxstorage_dob + "%", 15, 182 - 2, 13487565);
        int[] output;
        output = new int[9];
        int[] gend;
        gend = new int[9];
        int[] genn;
        genn = new int[9];
        for (int i = 0; i < 9; i++) {

            if (this.tileentity.chargeSlots[i] != null && this.tileentity.chargeSlots[i].getItem() instanceof IModulOutput) {
                output[i] = IModulOutput.getData(this.tileentity.chargeSlots[i]).get(0);

            }
            if (this.tileentity.chargeSlots[i] != null && this.tileentity.chargeSlots[i].getItem() instanceof IModulGenDay) {
                gend[i] = IModulGenDay.getData(this.tileentity.chargeSlots[i]).get(0);

            }
            if (this.tileentity.chargeSlots[i] != null && this.tileentity.chargeSlots[i].getItem() instanceof IModulGenNight) {
                genn[i] = IModulGenNight.getData(this.tileentity.chargeSlots[i]).get(0);

            }

        }
        double output_dob = 0;
        double gend_dob = 0;
        double genn_dob = 0;
        for (int i = 0; i < 9; i++) {
            if (output[i] != 0) {
                output_dob = output_dob + output[i];
            }
            if (gend[i] != 0) {
                gend_dob = gend_dob + gend[i];
            }
            if (genn[i] != 0) {
                genn_dob = genn_dob + genn[i];
            }
        }
        a[1] = (float) ((this.tileentity.u + sum2) + (this.tileentity.u + sum2) * (output_dob / 100));
        if (a[1] > 2000000000) {
            a[1] = 2000000000;
        } else if (a[1] < 0) {
            a[1] = 0;
        }


        this.fontRendererObj.drawString(maxOutputString + GuiNumberUtils.getString(a[1]) + " " + energyPerTickString, 50, 26 + 4, 13487565);
        if (output_dob != 0)
            this.fontRendererObj.drawString(ModulesString3 + (output_dob) + "%", 15, 175 - 2, 13487565);


        String generation = GuiNumberUtils.getString(this.tileentity.generating);

        if (this.tileentity.chargeSlots[8] != null && this.tileentity.chargeSlots[8].getItem() instanceof IModuleType) {


            if (this.tileentity.chargeSlots[8].getItemDamage() == 0)
                this.fontRendererObj.drawString(ModulesString6, 15, 196 - 2, 13487565);
            if (this.tileentity.chargeSlots[8].getItemDamage() == 1)
                this.fontRendererObj.drawString(ModulesString61, 15, 196 - 2, 13487565);
            if (this.tileentity.chargeSlots[8].getItemDamage() == 2)
                this.fontRendererObj.drawString(ModulesString62, 15, 196 - 2, 13487565);
            if (this.tileentity.chargeSlots[8].getItemDamage() == 3)
                this.fontRendererObj.drawString(ModulesString63, 15, 196 - 2, 13487565);
            if (this.tileentity.chargeSlots[8].getItemDamage() == 4)
                this.fontRendererObj.drawString(ModulesString64, 15, 196 - 2, 13487565);
            if (this.tileentity.chargeSlots[8].getItemDamage() == 5)
                this.fontRendererObj.drawString(ModulesString65, 15, 196 - 2, 13487565);
            if (this.tileentity.chargeSlots[8].getItemDamage() == 6)
                this.fontRendererObj.drawString(ModulesString66, 15, 196 - 2, 13487565);


        }
        for (int i = 0; i < 9; i++) {
            if (this.tileentity.chargeSlots[i] != null && this.tileentity.chargeSlots[i].getItem() instanceof module7) {
                if (this.tileentity.chargeSlots[i].getItemDamage() == 4)
                    this.fontRendererObj.drawString(ModulesString7, 15, 203 - 2, 13487565);

            }


        }
        for (int i = 0; i < 9; i++) {
            if (this.tileentity.chargeSlots[i] != null && this.tileentity.chargeSlots[i].getItem() instanceof ItemWirelessModule) {
                NBTTagCompound nbttagcompound = NBTData.getOrCreateNbtData(this.tileentity.chargeSlots[i]);

                if (this.tileentity.panelx != 0 && this.tileentity.panely != 0 && this.tileentity.panelz != 0) {
                    this.fontRendererObj.drawString(ModulesString8, 15, 209 - 2, 13487565);
                    this.fontRendererObj.drawString(ModulesString9 + "X:" + this.tileentity.panelx + " Y:" + this.tileentity.panely + " Z:" + this.tileentity.panelz, 15, 215 - 2, 13487565);

                } else if (this.tileentity.panelx == 0 && this.tileentity.panely == 0 && this.tileentity.panelz == 0)
                    this.fontRendererObj.drawString(ModulesString10, 15, 209 - 2, 13487565);

            }


        }
        this.fontRendererObj.drawString(generatingString + generation + " " + energyPerTickString, 50, 36 + 2, 13487565);
        if (gend_dob != 0 && this.tileentity.sunIsUp)
            this.fontRendererObj.drawString(ModulesString + (gend_dob) + "%", 15, 189 - 2, 13487565);
        if (genn_dob != 0 && !this.tileentity.sunIsUp)
            this.fontRendererObj.drawString(ModulesString1 + (genn_dob) + "%", 15, 189 - 2, 13487565);

        if (tierplus != 0 && tierplus - minus > 0 && this.tileentity.o > 0) {
            this.fontRendererObj.drawString(tierString + this.tileentity.o, 50, 46, 13487565);
            this.fontRendererObj.drawString(ModulesString4 + (tierplus - minus), 15, 209 - 2 + 6 + 6, 13487565);
        } else if (minus != 0 && tierplus - minus < 0 && this.tileentity.o > 0) {
            this.fontRendererObj.drawString(tierString + this.tileentity.o, 50, 46, 13487565);
            this.fontRendererObj.drawString(ModulesString5 + (minus - tierplus), 15, 209 - 2 + 6 + 6, 13487565);
        } else if (this.tileentity.o > 0) {
            this.fontRendererObj.drawString(tierString + this.tileentity.o, 50, 46, 13487565);
        } else if (this.tileentity.o == 0) {
            this.fontRendererObj.drawString(tierString + Character.MIN_VALUE, 50, 46, 13487565);
        } else {
            this.fontRendererObj.drawString(tierString + Character.MIN_VALUE, 50, 46, 13487565);
        }
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (this.tileentity.chargeSlots[8] != null && this.tileentity.chargeSlots[8].getItem() instanceof com.Denfop.item.Modules.module5) {
            int g = this.tileentity.chargeSlots[8].getItemDamage();
            if (g == 0) {
                this.tileentity.solarType = 1;
                if (this.tileentity.yCoord >= 130) {
                    this.mc.renderEngine.bindTexture(tex4);
                } else {
                    this.mc.renderEngine.bindTexture(tex);
                }
            } else if (g == 1) {
                this.tileentity.solarType = 2;
                if (this.tileentity.yCoord <= 40) {
                    this.mc.renderEngine.bindTexture(tex3);
                } else {
                    this.mc.renderEngine.bindTexture(tex);
                }
            } else if (g == 2) {
                this.tileentity.solarType = 3;
                if ((this.tileentity.getWorldObj()).provider.dimensionId == -1) {
                    this.mc.renderEngine.bindTexture(tex1);
                } else {
                    this.mc.renderEngine.bindTexture(tex);
                }
            } else if (g == 3) {
                this.tileentity.solarType = 4;
                if ((this.tileentity.getWorldObj()).provider.dimensionId == 1) {
                    this.mc.renderEngine.bindTexture(tex2);
                } else {
                    this.mc.renderEngine.bindTexture(tex);
                }
            } else if (g == 4) {
                this.tileentity.solarType = 5;
                this.mc.renderEngine.bindTexture(tex);
            } else if (g == 5) {
                this.tileentity.solarType = 6;
                this.mc.renderEngine.bindTexture(tex);
            } else if (g == 6) {
                this.tileentity.solarType = 7;
                if (this.tileentity.rain) {
                    this.mc.renderEngine.bindTexture(tex5);
                } else {
                    this.mc.renderEngine.bindTexture(tex);
                }
            } else {
                this.tileentity.solarType = 0;
                this.mc.renderEngine.bindTexture(tex);
            }
        } else {
            this.tileentity.solarType = 0;
            this.mc.renderEngine.bindTexture(tex);
        }
        int h = (this.width - this.xSize) / 2;
        int k = (this.height - this.ySize) / 2;
        drawTexturedModalRect(h, k, 0, 0, this.xSize, this.ySize);
        if (this.tileentity.storage > 0 || this.tileentity.storage <= this.tileentity.maxStorage) {
            float l = this.tileentity.gaugeEnergyScaled(24.0F);
            drawTexturedModalRect(h + 18, k + 24, 194, 0, (int) (l + 1.0F), 14);
        }
        if (this.tileentity.storage2 > 0 || this.tileentity.storage2 <= this.tileentity.maxStorage2) {
            float l = this.tileentity.gaugeEnergyScaled2(25.0F);

            drawTexturedModalRect(h + 19 + 72 + 40 + 23 + 1, k + 24, 219, 0, (int) (l), 14);
        }
        if (this.tileentity.skyIsVisible && this.tileentity.solarType != 3 && this.tileentity.solarType != 4) {
            if (this.tileentity.rain == false) {
                if (this.tileentity.sunIsUp) {
                    drawTexturedModalRect(h + 24, k + 42, 195, 15, 14, 14);
                } else if (!this.tileentity.sunIsUp) {
                    drawTexturedModalRect(h + 24, k + 42, 210, 15, 14, 14);
                }
            } else {
                if (this.tileentity.sunIsUp) {
                    drawTexturedModalRect(h + 24, k + 42, 225, 15, 14, 14);
                } else if (!this.tileentity.sunIsUp) {
                    drawTexturedModalRect(h + 24, k + 42, 240, 15, 14, 14);
                }
            }
        }
        if (this.tileentity.solarType == 3 && (this.tileentity.getWorldObj()).provider.dimensionId == -1)
            if (this.tileentity.sunIsUp) {
                drawTexturedModalRect(h + 24, k + 42, 195, 15, 14, 14);
            } else if (!this.tileentity.sunIsUp) {
                drawTexturedModalRect(h + 24, k + 42, 210, 15, 14, 14);
            }
        if (this.tileentity.solarType == 3 && (this.tileentity.getWorldObj()).provider.dimensionId != -1)
            if (this.tileentity.rain == false) {
                if (this.tileentity.sunIsUp) {
                    drawTexturedModalRect(h + 24, k + 42, 195, 15, 14, 14);
                } else if (!this.tileentity.sunIsUp) {
                    drawTexturedModalRect(h + 24, k + 42, 210, 15, 14, 14);
                }
            } else {
                if (this.tileentity.sunIsUp) {
                    drawTexturedModalRect(h + 24, k + 42, 225, 15, 14, 14);
                } else if (!this.tileentity.sunIsUp) {
                    drawTexturedModalRect(h + 24, k + 42, 240, 15, 14, 14);
                }
            }
        if (this.tileentity.solarType == 4 && (this.tileentity.getWorldObj()).provider.dimensionId == 1)
            if (this.tileentity.sunIsUp) {
                drawTexturedModalRect(h + 24, k + 42, 195, 15, 14, 14);
            } else if (!this.tileentity.sunIsUp) {
                drawTexturedModalRect(h + 24, k + 42, 210, 15, 14, 14);
            }
        if (this.tileentity.solarType == 4 && (this.tileentity.getWorldObj()).provider.dimensionId != 1)
            if (this.tileentity.rain == false) {
                if (this.tileentity.sunIsUp) {
                    drawTexturedModalRect(h + 24, k + 42, 195, 15, 14, 14);
                } else if (!this.tileentity.sunIsUp) {
                    drawTexturedModalRect(h + 24, k + 42, 210, 15, 14, 14);
                }
            } else {
                if (this.tileentity.sunIsUp) {
                    drawTexturedModalRect(h + 24, k + 42, 225, 15, 14, 14);
                } else if (!this.tileentity.sunIsUp) {
                    drawTexturedModalRect(h + 24, k + 42, 240, 15, 14, 14);
                }
            }
    }
}
