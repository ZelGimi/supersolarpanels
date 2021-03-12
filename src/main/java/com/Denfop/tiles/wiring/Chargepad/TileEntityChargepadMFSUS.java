package com.Denfop.tiles.wiring.Chargepad;

import com.Denfop.Config;
import com.Denfop.tiles.base.TileEntityChargepadBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileEntityChargepadMFSUS extends TileEntityChargepadBlock {
    public TileEntityChargepadMFSUS() {
        super(Config.tier2_MFSU, Config.enegry2, Config.storage2);
    }

    public String getInventoryName() {
        return "Chargepad MFSS";
    }

    protected void getItems(EntityPlayer player) {
        for (ItemStack current : player.inventory.armorInventory) {
            if (current != null)
                chargeitems(current, Config.enegry2);
        }
        for (ItemStack current : player.inventory.mainInventory) {
            if (current != null)
                chargeitems(current, Config.enegry2);
        }
    }
}
