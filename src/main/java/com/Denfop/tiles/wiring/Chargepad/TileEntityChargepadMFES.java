package com.Denfop.tiles.wiring.Chargepad;

import com.Denfop.Config;
import com.Denfop.tiles.base.TileEntityChargepadBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileEntityChargepadMFES extends TileEntityChargepadBlock {
    public TileEntityChargepadMFES() {
        super(Config.tier3_MFSU, Config.enegry1, Config.storage1);
    }

    public String getInventoryName() {
        return "Chargepad MFES";
    }

    protected void getItems(EntityPlayer player) {
        if (player != null) {
            for (ItemStack current : player.inventory.armorInventory) {
                if (current != null)
                    chargeitems(current, Config.enegry1);
            }
            for (ItemStack current : player.inventory.mainInventory) {
                if (current != null)
                    chargeitems(current, Config.enegry1);
            }
        }
    }
}
