package com.Denfop.gui;

import com.Denfop.Constants;
import com.Denfop.api.inv.IInvSlotProcessableMulti;
import com.Denfop.container.ContainerMultiMachine1;
import com.Denfop.tiles.base.TileEntityMultiMachine1;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.ContainerBase;
import ic2.core.GuiIC2;
import ic2.core.slot.SlotInvSlot;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiMultiMachine1 extends GuiIC2 {
    public ContainerMultiMachine1 container;

    public GuiMultiMachine1(ContainerMultiMachine1 container1) {
        super(container1);
        this.container = container1;
    }

    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        super.drawGuiContainerBackgroundLayer(f, x, y);
        TileEntityMultiMachine1 tile = (TileEntityMultiMachine1) this.container.base;

        int chargeLevel = (int) (14.0F * tile.getChargeLevel());

        int i = 0;
        for (Slot slot : (List<Slot>) this.container.inventorySlots) {
            if (slot instanceof SlotInvSlot) {
                int xX = this.xoffset + slot.xDisplayPosition;
                int yY = this.yoffset + slot.yDisplayPosition;
                SlotInvSlot slotInv = (SlotInvSlot) slot;
                if (slotInv.invSlot instanceof IInvSlotProcessableMulti) {
                    int down = 24 * (tile.blockMetadata / 3);
                    drawTexturedModalRect(xX, yY + 19, 176, 14 + down, 16, 24);
                    int progress = (int) (24.0F * tile.getProgress(i));
                    if (progress >= 0)
                        drawTexturedModalRect(xX, yY + 19, 192, 14 + down, 16, progress + 1);
                    i++;
                }
                drawTexturedModalRect(xX - 1, yY - 1, 238, 0, 18, 18);
            }
        }

        if (chargeLevel >= 0)
            drawTexturedModalRect(this.xoffset + 8, this.yoffset + 46 + 14 - chargeLevel, 176, 14 - chargeLevel, 14, chargeLevel);

    }

    public String getName() {
        return this.container.base.getInventoryName();
    }

    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(Constants.TEXTURES, "textures/gui/GUIMachine2.png");
    }
}
