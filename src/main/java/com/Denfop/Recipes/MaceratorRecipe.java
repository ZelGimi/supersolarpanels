package com.Denfop.Recipes;

import com.Denfop.SSPItem;
import cpw.mods.fml.common.Loader;
import ic2.api.item.IC2Items;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.Recipes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class MaceratorRecipe {
    public static void recipe() {
        if (!Loader.isModLoaded("aobd")) {
            Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(SSPItem.spinelore)), null, new ItemStack(SSPItem.spinelcrushedore, 2));
            Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(SSPItem.platiumore)), null, new ItemStack(SSPItem.platiumcrushedore, 2));
            Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(SSPItem.chromiumore)), null, new ItemStack(SSPItem.chromiumcrushedore, 2));
            Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(SSPItem.iridiumore)), null, new ItemStack(SSPItem.iridiumcrushedore, 2));
            Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(SSPItem.magnesiumore)), null, new ItemStack(SSPItem.magnesiumcrushedore, 2));
            Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(SSPItem.mikhail_ore)), null, new ItemStack(SSPItem.michalovcrushedore, 2));
            Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(SSPItem.nicelore)), null, new ItemStack(SSPItem.nickelcrushedore, 2));
            Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(SSPItem.wolframore)), null, new ItemStack(SSPItem.wolframcrushedore, 2));
        }//
        Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(SSPItem.endiron_stone)), null, new ItemStack(IC2Items.getItem("crushedIronOre").getItem(), 2, 0));
        Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(SSPItem.netherironrack)), null, new ItemStack(IC2Items.getItem("crushedIronOre").getItem(), 2, 0));
        Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(SSPItem.endgold_stone)), null, new ItemStack(IC2Items.getItem("crushedGoldOre").getItem(), 2, 2));
        Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(SSPItem.nethergoldrack)), null, new ItemStack(IC2Items.getItem("crushedGoldOre").getItem(), 2, 2));
        Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(SSPItem.enduran_stone)), null, new ItemStack(IC2Items.getItem("crushedUraniumOre").getItem(), 2, 4));
        Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(SSPItem.netheruranrack)), null, new ItemStack(IC2Items.getItem("crushedUraniumOre").getItem(), 2, 4));
        Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(SSPItem.endcopper_stone)), null, new ItemStack(IC2Items.getItem("crushedCopperOre").getItem(), 2, 1));
        Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(SSPItem.nethercopperrack)), null, new ItemStack(IC2Items.getItem("crushedCopperOre").getItem(), 2, 1));
        Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(SSPItem.endtin_stone)), null, new ItemStack(IC2Items.getItem("crushedTinOre").getItem(), 2, 3));
        Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(SSPItem.nethertinrack)), null, new ItemStack(IC2Items.getItem("crushedTinOre").getItem(), 2, 3));
        Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(SSPItem.endlead_stone)), null, new ItemStack(IC2Items.getItem("crushedLeadOre").getItem(), 2, 6));
        Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(SSPItem.netherleadrack)), null, new ItemStack(IC2Items.getItem("crushedLeadOre").getItem(), 2, 6));
        //
        //  Recipes.macerator.addRecipe((IRecipeInput)new RecipeInputItemStack(new ItemStack(Ic2Items.advIronIngot.getItem())), (NBTTagCompound)null, new ItemStack[] {new ItemStack(SuperSolarPanels.blast) });
        Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(SSPItem.electriumingot)), null, new ItemStack(SSPItem.electriumdust));
        Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(SSPItem.invaringot)), null, new ItemStack(SSPItem.invardust));

    }

    public static void addmacerator(ItemStack input, int n, ItemStack output) {
        Recipes.macerator.addRecipe(new RecipeInputItemStack(input, n), null, output);

    }
}
