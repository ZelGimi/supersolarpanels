package com.Denfop.integration.Botania;

import com.Denfop.SSPItem;
import com.Denfop.item.base.SSPItemBase;
import cpw.mods.fml.common.registry.GameRegistry;
import ic2.api.item.IC2Items;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.Recipes;
import ic2.core.Ic2Items;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lib.LibOreDict;

public class BotaniaIntegration {

    public static Block blockBotSolarPanel;
    public static Item manasteel_plate;
    public static Item manasteel_core;
    public static Item elementium_plate;
    public static Item elementium_core;
    public static Item terrasteel_plate;
    public static Item terrasteel_core;
    public static ItemStack reactorterastrellSimple;
    public static ItemStack reactorDepletedterastrellSimple;
    public static ItemStack reactorterastrellQuad;
    public static ItemStack reactorterastrellDual;
    public static ItemStack reactorDepletedterastrellQuad;
    public static ItemStack reactorDepletedterastrellDual;
    public static Item rune_sun;
    public static Item rune_night;
    public static Item rune_energy;

    public static Item teraDDrill;

    public static void init() {
        GameRegistry.registerBlock(blockBotSolarPanel = new blockBotSolarPanel(), ItemBotSolarPanel.class, "blockBotSolarPanel");
        GameRegistry.registerItem(manasteel_plate = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("manasteel_plate").setTextureName("supersolarpanel:manasteel_plate"), "manasteel_plate");
        GameRegistry.registerItem(manasteel_core = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("manasteel_core").setTextureName("supersolarpanel:manasteel_core"), "manasteel_core");
        GameRegistry.registerItem(elementium_plate = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("elementium_plate").setTextureName("supersolarpanel:elementium_plate"), "elementium_plate");
        GameRegistry.registerItem(elementium_core = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("elementium_core").setTextureName("supersolarpanel:elementium_core"), "elementium_core");
        GameRegistry.registerItem(terrasteel_plate = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("terrasteel_plate").setTextureName("supersolarpanel:terrasteel_plate"), "terrasteel_plate");
        GameRegistry.registerItem(terrasteel_core = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("terrasteel_core").setTextureName("supersolarpanel:terrasteel_core"), "terrasteel_core");
        GameRegistry.registerItem(rune_sun = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("rune_sun").setTextureName("supersolarpanel:rune_sun"), "rune_sun");
        GameRegistry.registerItem(rune_night = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("rune_night").setTextureName("supersolarpanel:rune_night"), "rune_night");
        GameRegistry.registerItem(rune_energy = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("rune_energy").setTextureName("supersolarpanel:rune_energy"), "rune_energy");
        GameRegistry.registerItem(teraDDrill = new Terradrill(Item.ToolMaterial.EMERALD).setUnlocalizedName("teraDDrill"), "teraDDrill");
        GameRegistry.registerTileEntity(TileEntityManasteelSolarPanel.class, "Manasteel Solar Panel");
        GameRegistry.registerTileEntity(TileEntityElementumSolarPanel.class, "Elementum Solar Panel");
        GameRegistry.registerTileEntity(TileEntityTerrastelSolarPanel.class, "Terrastel Solar Panel");


    }

    public static void recipe() {
        BotaniaAPI.registerRuneAltarRecipe(new ItemStack(rune_energy, 1, 0), 12000, LibOreDict.RUNE[0], LibOreDict.RUNE[1], new ItemStack(SSPItem.photoniy), new ItemStack(SSPItem.itemSSP, 1, 0), new ItemStack(SSPItem.itemSSP, 1, 7), new ItemStack(elementium_plate), new ItemStack(SSPItem.compresscarbonultra));
        BotaniaAPI.registerRuneAltarRecipe(new ItemStack(rune_sun, 1, 0), 12000, LibOreDict.RUNE[4], LibOreDict.RUNE[3], new ItemStack(SSPItem.photoniy_ingot), new ItemStack(SSPItem.itemSSP, 1, 0), new ItemStack(SSPItem.itemSSP, 1, 7), new ItemStack(elementium_plate), new ItemStack(SSPItem.compresscarbon));
        BotaniaAPI.registerRuneAltarRecipe(new ItemStack(rune_night, 1, 0), 12000, LibOreDict.RUNE[7], LibOreDict.RUNE[5], new ItemStack(SSPItem.dust), new ItemStack(SSPItem.itemSSP, 1, 0), new ItemStack(SSPItem.itemSSP, 1, 7), new ItemStack(manasteel_plate), new ItemStack(SSPItem.coal_chunk1));
        Recipes.compressor.addRecipe(new RecipeInputItemStack(new ItemStack(ModItems.manaResource, 1, 0), 1), null, new ItemStack(manasteel_plate));
        Recipes.compressor.addRecipe(new RecipeInputItemStack(new ItemStack(ModItems.manaResource, 1, 7), 1), null, new ItemStack(elementium_plate));
        Recipes.compressor.addRecipe(new RecipeInputItemStack(new ItemStack(ModItems.manaResource, 1, 4), 1), null, new ItemStack(terrasteel_plate));
        GameRegistry.addRecipe(new ItemStack(teraDDrill, 1, OreDictionary.WILDCARD_VALUE), " L ", "ODO", "COC", 'O', SSPItem.overclockerUpgrade, 'D', new ItemStack(Ic2Items.diamondDrill.getItem(), 1, OreDictionary.WILDCARD_VALUE), 'C', terrasteel_plate, 'L', ModItems.terraPick);
        GameRegistry.addRecipe(new ItemStack(terrasteel_core), "KLM", "DOD", "CHC", 'C', terrasteel_plate, 'D', SSPItem.itemIrradiantGlassPane, 'O', terrasteel_plate, 'L', IC2Items.getItem("advancedAlloy"), 'K', rune_night, 'M', rune_sun, 'H', rune_energy);
        GameRegistry.addRecipe(new ItemStack(elementium_core), "KLM", "DOD", "CHC", 'C', elementium_plate, 'D', SSPItem.photoniy_ingot, 'O', manasteel_core, 'L', IC2Items.getItem("advancedCircuit"), 'K', rune_night, 'M', rune_sun, 'H', rune_energy);
        GameRegistry.addRecipe(new ItemStack(manasteel_core), "KLM", "DOD", "CHC", 'C', manasteel_plate, 'D', SSPItem.photoniy_ingot, 'O', SSPItem.advanced_core, 'L', IC2Items.getItem("advancedCircuit"), 'K', rune_night, 'M', rune_sun, 'H', rune_energy);
        GameRegistry.addRecipe(new ItemStack(blockBotSolarPanel, 1, 0), "BBB", "BAB", "BBB", 'A', manasteel_core, 'B', new ItemStack(SSPItem.blockSSPSolarPanel, 1, 0));
        GameRegistry.addRecipe(new ItemStack(blockBotSolarPanel, 1, 1), "BBB", "BAB", "BBB", 'A', elementium_core, 'B', new ItemStack(blockBotSolarPanel, 1, 0));
        GameRegistry.addRecipe(new ItemStack(blockBotSolarPanel, 1, 2), "BBB", "BAB", "BBB", 'A', terrasteel_core, 'B', new ItemStack(blockBotSolarPanel, 1, 1));

        Recipes.advRecipes.addRecipe(reactorterastrellDual, "SQS", Character.valueOf('S'), reactorterastrellSimple, Character.valueOf('Q'), "plateIron");
        Recipes.advRecipes.addRecipe(reactorterastrellQuad, "SQS", "CQC", "SQS", Character.valueOf('S'), reactorterastrellSimple, Character.valueOf('Q'), "plateIron", Character.valueOf('C'), "plateCopper");

        Recipes.advRecipes.addRecipe(reactorterastrellQuad, "SQS", Character.valueOf('S'), reactorterastrellDual, Character.valueOf('Q'), "plateIron", Character.valueOf('C'), "plateCopper");


        Recipes.cannerBottle.addRecipe(new RecipeInputItemStack(IC2Items.getItem("fuelRod"), 1), new RecipeInputItemStack(new ItemStack(ModItems.manaResource, 1, 4), 1), reactorterastrellSimple);
        Recipes.compressor.addRecipe(new RecipeInputItemStack(reactorDepletedterastrellSimple, 1), null, new ItemStack(ModItems.manaResource, 1, 4));
        Recipes.compressor.addRecipe(new RecipeInputItemStack(reactorDepletedterastrellDual, 1), null, new ItemStack(ModItems.manaResource, 4, 4));
        Recipes.compressor.addRecipe(new RecipeInputItemStack(reactorterastrellQuad, 1), null, new ItemStack(ModItems.manaResource, 2, 4));


    }

    private static void addcentrifugeRecipe(IRecipeInput input, ItemStack itemStack) {
        Recipes.centrifuge.addRecipe(input, null, itemStack);
    }
}
