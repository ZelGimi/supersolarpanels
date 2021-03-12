package com.Denfop.Recipes;

import com.Denfop.SSPItem;
import cpw.mods.fml.common.registry.GameRegistry;
import ic2.core.Ic2Items;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class FurnaceRecipes {
    public static void recipe() {
        GameRegistry.addSmelting(SSPItem.platiumore, new ItemStack(SSPItem.platium_ingot), 5.0F);

        GameRegistry.addSmelting(SSPItem.wolframore, new ItemStack(SSPItem.wolfram_ingot), 5.0F);
        GameRegistry.addSmelting(SSPItem.spinelore, new ItemStack(SSPItem.spinel_ingot), 5.0F);
        GameRegistry.addSmelting(SSPItem.mikhail_ore, new ItemStack(SSPItem.mikhail_ingot), 5.0F);
        GameRegistry.addSmelting(SSPItem.iridiumore, new ItemStack(SSPItem.itemSSP, 1, 7), 5.0F);
        GameRegistry.addSmelting(SSPItem.chromiumore, new ItemStack(SSPItem.chromium_ingot), 5.0F);

        GameRegistry.addSmelting(SSPItem.magnesiumore, new ItemStack(SSPItem.magnesium_ingot, 1), 5.0F);
        GameRegistry.addSmelting(SSPItem.nicelore, new ItemStack(SSPItem.nickel), 5.0F);
        //
        GameRegistry.addSmelting(SSPItem.platiumcrushedore, new ItemStack(SSPItem.platium_ingot), 5.0F);

        GameRegistry.addSmelting(SSPItem.wolframcrushedore, new ItemStack(SSPItem.wolfram_ingot), 5.0F);
        GameRegistry.addSmelting(SSPItem.spinelcrushedore, new ItemStack(SSPItem.spinel_ingot), 5.0F);
        GameRegistry.addSmelting(SSPItem.michalovcrushedore, new ItemStack(SSPItem.mikhail_ingot), 5.0F);
        GameRegistry.addSmelting(SSPItem.iridiumcrushedore, new ItemStack(SSPItem.itemSSP, 1, 7), 5.0F);
        GameRegistry.addSmelting(SSPItem.chromiumcrushedore, new ItemStack(SSPItem.chromium_ingot), 5.0F);

        GameRegistry.addSmelting(SSPItem.magnesiumcrushedore, new ItemStack(SSPItem.magnesium_ingot, 1), 5.0F);
        GameRegistry.addSmelting(SSPItem.nickelcrushedore, new ItemStack(SSPItem.nickel), 5.0F);

        //
        GameRegistry.addSmelting(SSPItem.nethercoalrack, new ItemStack(Items.coal), 5.0F);
        GameRegistry.addSmelting(SSPItem.endcoal_stone, new ItemStack(Items.coal), 5.0F);
        GameRegistry.addSmelting(SSPItem.nethercopperrack, Ic2Items.copperIngot, 5.0F);
        GameRegistry.addSmelting(SSPItem.endcopper_stone, Ic2Items.copperIngot, 5.0F);
        GameRegistry.addSmelting(SSPItem.nethertinrack, Ic2Items.tinIngot, 5.0F);
        GameRegistry.addSmelting(SSPItem.endtin_stone, Ic2Items.tinIngot, 5.0F);
        GameRegistry.addSmelting(SSPItem.netherironrack, new ItemStack(Items.iron_ingot), 5.0F);
        GameRegistry.addSmelting(SSPItem.endiron_stone, new ItemStack(Items.iron_ingot), 5.0F);
        GameRegistry.addSmelting(SSPItem.nethergoldrack, new ItemStack(Items.gold_ingot), 5.0F);
        GameRegistry.addSmelting(SSPItem.endgold_stone, new ItemStack(Items.gold_ingot), 5.0F);
        GameRegistry.addSmelting(SSPItem.netherdiamondrack, new ItemStack(Items.diamond), 5.0F);
        GameRegistry.addSmelting(SSPItem.enddiamond_stone, new ItemStack(Items.diamond), 5.0F);
        GameRegistry.addSmelting(SSPItem.netheremeraldrack, new ItemStack(Items.emerald), 5.0F);
        GameRegistry.addSmelting(SSPItem.endemerald_stone, new ItemStack(Items.emerald), 5.0F);
        GameRegistry.addSmelting(SSPItem.netherredstonerack, new ItemStack(Items.redstone), 5.0F);
        GameRegistry.addSmelting(SSPItem.endredstone_stone, new ItemStack(Items.redstone), 5.0F);
        GameRegistry.addSmelting(SSPItem.netherlapisrack, new ItemStack(Items.dye, 1, 4), 5.0F);
        GameRegistry.addSmelting(SSPItem.endlapis_stone, new ItemStack(Items.dye, 1, 4), 5.0F);
        GameRegistry.addSmelting(SSPItem.netherleadrack, Ic2Items.leadIngot, 5.0F);
        GameRegistry.addSmelting(SSPItem.endlead_stone, Ic2Items.leadIngot, 5.0F);
        //
        GameRegistry.addSmelting(SSPItem.invardust, new ItemStack(SSPItem.invaringot), 5.0F);
        GameRegistry.addSmelting(SSPItem.electriumdust, new ItemStack(SSPItem.electriumingot), 5.0F);
        GameRegistry.addSmelting(SSPItem.blast, Ic2Items.advIronIngot, 5.0F);


    }
}
