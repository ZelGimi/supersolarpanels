package com.Denfop.Recipes;

import com.Denfop.SSPItem;
import cpw.mods.fml.common.registry.GameRegistry;
import ic2.api.item.IC2Items;
import ic2.api.recipe.Recipes;
import ic2.core.Ic2Items;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class BasicRecipe {
    public static void recipe() {
        GameRegistry.addRecipe(new ItemStack(SSPItem.itemSSP, 1, 4), "ABA", "DCD", "AFA", Character.valueOf('A'), Items.redstone, Character.valueOf('B'), SSPItem.photoniy, Character.valueOf('D'), new ItemStack(Items.dye, 1, 4), Character.valueOf('C'),
                new ItemStack(SSPItem.itemSSP, 1, 5), Character.valueOf('F'), Items.diamond);

        //TODO craft matter`s
        GameRegistry.addRecipe(new ItemStack(SSPItem.matter, 1, 1), "A A", "AAA", "A A", 'A', new ItemStack(SSPItem.matter, 1, 0));
        GameRegistry.addRecipe(new ItemStack(SSPItem.matter, 1, 2), "AAA", "A A", "AAA", 'A', new ItemStack(SSPItem.matter, 1, 0));
        GameRegistry.addRecipe(new ItemStack(SSPItem.matter, 1, 3), "A A", "A A", "A A", 'A', new ItemStack(SSPItem.matter, 1, 0));
        GameRegistry.addRecipe(new ItemStack(SSPItem.matter, 1, 4), "A A", " A ", "A A", 'A', new ItemStack(SSPItem.matter, 1, 0));

        GameRegistry.addRecipe(new ItemStack(SSPItem.matter, 1, 5), "A  ", "AAA", "  A", 'A', new ItemStack(SSPItem.matter, 1, 0));
        GameRegistry.addRecipe(new ItemStack(SSPItem.matter, 1, 6), "A A", " A ", " A ", 'A', new ItemStack(SSPItem.matter, 1, 0));
        GameRegistry.addRecipe(new ItemStack(SSPItem.matter, 1, 7), "A A", "A A", " A ", 'A', new ItemStack(SSPItem.matter, 1, 0));

        //TODO craft lenses
        GameRegistry.addRecipe(new ItemStack(SSPItem.module5, 1, 5), " B ", "AAA", "   ", 'A', new ItemStack(SSPItem.sunlinse, 1), 'B', new ItemStack(SSPItem.QuantumItems9, 1));
        GameRegistry.addRecipe(new ItemStack(SSPItem.module5, 1, 6), " B ", "AAA", "   ", 'A', new ItemStack(SSPItem.rainlinse, 1), 'B', new ItemStack(SSPItem.QuantumItems9, 1));
        GameRegistry.addRecipe(new ItemStack(SSPItem.module5, 1, 2), " B ", "AAA", "   ", 'A', new ItemStack(SSPItem.netherlinse, 1), 'B', new ItemStack(SSPItem.QuantumItems8, 1));
        GameRegistry.addRecipe(new ItemStack(SSPItem.module5, 1, 4), " B ", "AAA", "   ", 'A', new ItemStack(SSPItem.nightlinse, 1), 'B', new ItemStack(SSPItem.QuantumItems8, 1));

        GameRegistry.addRecipe(new ItemStack(SSPItem.module5, 1, 1), " B ", "AAA", "   ", 'A', new ItemStack(SSPItem.earthlinse, 1), 'B', new ItemStack(SSPItem.QuantumItems9, 1));
        GameRegistry.addRecipe(new ItemStack(SSPItem.module5, 1, 3), " B ", "AAA", "   ", 'A', new ItemStack(SSPItem.endlinse, 1), 'B', new ItemStack(SSPItem.QuantumItems8, 1));
        GameRegistry.addRecipe(new ItemStack(SSPItem.module5, 1, 0), " B ", "AAA", "   ", 'A', new ItemStack(SSPItem.aerlinse, 1), 'B', new ItemStack(SSPItem.QuantumItems8, 1));


        //TODO Recipe Ultimate Drill
        GameRegistry.addRecipe(new ItemStack(SSPItem.ultDDrill, 1, OreDictionary.WILDCARD_VALUE), " L ", "ODO", "COC", 'O', SSPItem.overclockerUpgrade1, 'D', new ItemStack(Ic2Items.diamondDrill.getItem(), 1, OreDictionary.WILDCARD_VALUE), 'C', SSPItem.QuantumItems5, 'L', SSPItem.QuantumItems3);

        //TODO Recipe Advanced and Improvemed Overclockers
        GameRegistry.addRecipe(SSPItem.overclockerUpgrade, "BAB", 'A', Ic2Items.overclockerUpgrade, 'B', SSPItem.QuantumItems9);
        GameRegistry.addRecipe(SSPItem.overclockerUpgrade1, "BAB", 'A', SSPItem.overclockerUpgrade, 'B', SSPItem.QuantumItems8);
        //TODO Reciper Rotor`s
        GameRegistry.addRecipe(SSPItem.myphical, " B ", "BAB", " B ", 'A', SSPItem.spectral, 'B', SSPItem.QuantumItems5);
        GameRegistry.addRecipe(SSPItem.photon, " B ", "BAB", " B ", 'A', SSPItem.myphical, 'B', SSPItem.QuantumItems8);
        GameRegistry.addRecipe(SSPItem.neutron, "CBC", "BAB", "CBC", 'A', SSPItem.photon, 'B', SSPItem.QuantumItems9, 'C', Ic2Items.iridiumPlate);

        GameRegistry.addRecipe(SSPItem.iridium, " B ", "BAB", " B ", 'A', Ic2Items.carbonrotor, 'B', Ic2Items.iridiumPlate);
        GameRegistry.addRecipe(SSPItem.compressiridium, " B ", "BAB", " B ", 'A', SSPItem.iridium, 'B', SSPItem.compresscarbon);
        GameRegistry.addRecipe(SSPItem.spectral, "C C", "BAB", "C C", 'A', SSPItem.compressiridium, 'B', SSPItem.QuantumItems5, 'C', Ic2Items.iridiumPlate);
        GameRegistry.addRecipe(SSPItem.reactorCoolanttwelve, "CCC", "ABA", "CCC", 'A', Ic2Items.reactorCoolantSix, 'B', Ic2Items.plateiron, 'C', Ic2Items.platetin);
        GameRegistry.addRecipe(SSPItem.reactorCoolantmax, "CCC", "ABA", "CCC", 'A', SSPItem.reactorCoolanttwelve, 'B', Ic2Items.plateiron, 'C', Ic2Items.platetin);

        //TODO Recipes Machines and Quantum/Nano Chip
        Recipes.advRecipes.addRecipe(new ItemStack(SSPItem.QuantumItems9, 1), " D ", "BAB", " C ", Character.valueOf('A'), Ic2Items.advancedCircuit, Character.valueOf('B'), new ItemStack(SSPItem.nanoBox, 1), Character.valueOf('C'), OreDictionary.getOres("ingotNickel").get(0), Character.valueOf('D'),
                SSPItem.compresscarbon);

        GameRegistry.addRecipe(new ItemStack(SSPItem.QuantumItems8, 1), "DCD", "HAH", 'A', SSPItem.QuantumItems9, 'C', SSPItem.magnesium_ingot, 'D', SSPItem.compresscarbonultra, 'H', SSPItem.QuantumItems6);
        GameRegistry.addRecipe(new ItemStack(SSPItem.machines_base, 1, 0), "AAA", "BCB", "DDD", 'A', SSPItem.platium_plate, 'B', SSPItem.QuantumItems9, 'C', Ic2Items.macerator, 'D', SSPItem.QuantumItems6);
        GameRegistry.addRecipe(new ItemStack(SSPItem.machines_base, 1, 6), "AAA", "BCB", "DDD", 'A', SSPItem.chromium_plate, 'B', SSPItem.QuantumItems9, 'C', Ic2Items.electroFurnace, 'D', SSPItem.QuantumItems6);
        GameRegistry.addRecipe(new ItemStack(SSPItem.machines_base, 1, 7), "AAA", "BCB", "DDD", 'A', SSPItem.wolfram_plate, 'B', SSPItem.QuantumItems8, 'C', new ItemStack(SSPItem.machines_base, 1, 6), 'D', SSPItem.QuantumItems3);
        GameRegistry.addRecipe(new ItemStack(SSPItem.machines_base, 1, 8), "AAA", "BCB", "DDD", 'A', SSPItem.michail_plate, 'B', SSPItem.QuantumItems5, 'C', new ItemStack(SSPItem.machines_base, 1, 7), 'D', SSPItem.QuantumItems3);

        GameRegistry.addRecipe(new ItemStack(SSPItem.machines_base, 1, 3), "AAA", "BCB", "DDD", 'A', SSPItem.wolfram_plate, 'B', SSPItem.QuantumItems9, 'C', Ic2Items.compressor, 'D', SSPItem.QuantumItems6);
        GameRegistry.addRecipe(new ItemStack(SSPItem.machines_base, 1, 4), "AAA", "BCB", "DDD", 'A', SSPItem.magnesium_plate, 'B', SSPItem.QuantumItems8, 'C', new ItemStack(SSPItem.machines_base, 1, 3), 'D', SSPItem.QuantumItems3);
        GameRegistry.addRecipe(new ItemStack(SSPItem.machines_base, 1, 5), "AAA", "BCB", "DDD", 'A', SSPItem.chromium_plate, 'B', SSPItem.QuantumItems5, 'C', new ItemStack(SSPItem.machines_base, 1, 4), 'D', SSPItem.QuantumItems3);

        GameRegistry.addRecipe(SSPItem.massFabricator1, "AAA", "BCB", "DDD", 'A', SSPItem.magnesium_plate, 'B', SSPItem.QuantumItems9, 'C', Ic2Items.massFabricator, 'D', SSPItem.QuantumItems6);
        GameRegistry.addRecipe(new ItemStack(SSPItem.machines_base, 1, 1), "AAA", "BCB", "DDD", 'A', SSPItem.chromium_plate, 'B', SSPItem.QuantumItems8, 'C', new ItemStack(SSPItem.machines_base, 1, 0), 'D', SSPItem.QuantumItems3);
        GameRegistry.addRecipe(new ItemStack(SSPItem.machines_base, 1, 2), "AAA", "BCB", "DDD", 'A', SSPItem.magnesium_plate, 'B', SSPItem.QuantumItems5, 'C', new ItemStack(SSPItem.machines_base, 1, 1), 'D', SSPItem.QuantumItems3);

        GameRegistry.addRecipe(new ItemStack(SSPItem.machines_base, 1, 9), "AAA", "BCB", "DDD", 'A', SSPItem.chromium_plate, 'B', SSPItem.QuantumItems9, 'C', Ic2Items.extractor, 'D', SSPItem.QuantumItems6);
        GameRegistry.addRecipe(new ItemStack(SSPItem.machines_base, 1, 10), "AAA", "BCB", "DDD", 'A', SSPItem.wolfram_plate, 'B', SSPItem.QuantumItems8, 'C', new ItemStack(SSPItem.machines_base, 1, 9), 'D', SSPItem.QuantumItems3);
        GameRegistry.addRecipe(new ItemStack(SSPItem.machines_base, 1, 11), "AAA", "BCB", "DDD", 'A', SSPItem.michail_plate, 'B', SSPItem.QuantumItems5, 'C', new ItemStack(SSPItem.machines_base, 1, 10), 'D', SSPItem.QuantumItems3);

        GameRegistry.addRecipe(SSPItem.massFabricator2, "AAA", "BCB", "DDD", 'A', SSPItem.platium_plate, 'B', SSPItem.QuantumItems8, 'C', SSPItem.massFabricator1, 'D', SSPItem.QuantumItems6);
        GameRegistry.addRecipe(SSPItem.massFabricator3, "AAA", "BCB", "DDD", 'A', SSPItem.spinel_plate, 'B', SSPItem.QuantumItems5, 'C', SSPItem.massFabricator2, 'D', SSPItem.QuantumItems3);
        GameRegistry.addRecipe(new ItemStack(SSPItem.machines_base, 1, 12), "AAA", "BCB", "DDD", 'A', SSPItem.magnesium_plate, 'B', SSPItem.QuantumItems9, 'C', Ic2Items.metalformer, 'D', SSPItem.QuantumItems6);
        GameRegistry.addRecipe(new ItemStack(SSPItem.machines_base, 1, 13), "AAA", "BCB", "DDD", 'A', SSPItem.platium_plate, 'B', SSPItem.QuantumItems8, 'C', new ItemStack(SSPItem.machines_base, 1, 12), 'D', SSPItem.QuantumItems3);
        GameRegistry.addRecipe(new ItemStack(SSPItem.machines_base, 1, 14), "AAA", "BCB", "DDD", 'A', SSPItem.chromium_plate, 'B', SSPItem.QuantumItems5, 'C', new ItemStack(SSPItem.machines_base, 1, 13), 'D', SSPItem.QuantumItems3);

        GameRegistry.addRecipe(new ItemStack(SSPItem.machines_base1, 1, 0), "AAA", "BCB", "DDD", 'A', SSPItem.wolfram_plate, 'B', SSPItem.QuantumItems9, 'C', Ic2Items.recycler, 'D', SSPItem.QuantumItems6);
        GameRegistry.addRecipe(new ItemStack(SSPItem.machines_base1, 1, 1), "AAA", "BCB", "DDD", 'A', SSPItem.chromium_plate, 'B', SSPItem.QuantumItems8, 'C', new ItemStack(SSPItem.machines_base1, 1, 0), 'D', SSPItem.QuantumItems3);
        GameRegistry.addRecipe(new ItemStack(SSPItem.machines_base1, 1, 2), "AAA", "BCB", "DDD", 'A', SSPItem.michail_plate, 'B', SSPItem.QuantumItems5, 'C', new ItemStack(SSPItem.machines_base1, 1, 1), 'D', SSPItem.QuantumItems3);

        GameRegistry.addRecipe(new ItemStack(SSPItem.machines, 1, 4), "AAA", "BCB", "DDD", 'A', Ic2Items.plateadviron, 'B', SSPItem.QuantumItems5, 'C', Ic2Items.inductionFurnace, 'D', SSPItem.QuantumItems6);
        GameRegistry.addRecipe(new ItemStack(SSPItem.machines, 1, 7), "AAA", "BCB", "DDD", 'A', Blocks.cobblestone, 'B', Ic2Items.advancedCircuit, 'C', Ic2Items.generator, 'D', SSPItem.QuantumItems6);
        GameRegistry.addRecipe(new ItemStack(SSPItem.machines, 1, 8), "A A", "BCB", "A A", 'A', SSPItem.protoncore, 'B', SSPItem.QuantumItems6, 'C', new ItemStack(SSPItem.machines, 1, 0));

        // TODO Recipe Neutron Generator
        GameRegistry.addRecipe(SSPItem.massFabricator, " B ", "ACA", " B ", 'C', Ic2Items.massFabricator, 'A', new ItemStack(SSPItem.QuantumItems5, 1), 'B', new ItemStack(SSPItem.enderquantumcomponent, 1));


        //TODO Old Recipe from Advanced Solar Panels
        GameRegistry.addRecipe(new ShapedOreRecipe(SSPItem.itemIrradiantUranium, new Object[]{" R ", "RSR", " R ", Character.valueOf('R'), Items.glowstone_dust, Character.valueOf('S'), new ItemStack(SSPItem.itemSSP, 1, 8)}));
        GameRegistry.addRecipe(new ItemStack(SSPItem.itemSSP, 6, 3), "RRR", "ASA", "RRR", Character.valueOf('R'), IC2Items.getItem("reinforcedGlass"), Character.valueOf('A'), new ItemStack(SSPItem.itemSSP, 1, 0), Character.valueOf('S'), Items.glowstone_dust);
        //TODO recipe neutron core

        GameRegistry.addRecipe(new ItemStack(SSPItem.neutroniumcore, 1), " A ", "ACA", " A ", 'C', SSPItem.photoniccore, 'A', SSPItem.neutroniumingot);
        //TODO Recipe Advanced and Improvemed MFSU
        GameRegistry.addRecipe(new ItemStack(SSPItem.electricblock, 1, 0), "ACA", 'C', Ic2Items.mfsUnit, 'A', SSPItem.photoniy_ingot);
        GameRegistry.addRecipe(new ItemStack(SSPItem.electricblock, 1, 1), "ACA", 'C', new ItemStack(SSPItem.electricblock, 1, 0), 'A', SSPItem.QuantumItems5);
        //TODO Recipe dust
        GameRegistry.addRecipe(new ItemStack(SSPItem.dust, 1), "AAA", "AAA", "AAA", 'A', IC2Items.getItem("energiumDust"));
        //TODO Old Recipe from Advanced Solar Panels
        GameRegistry.addRecipe(SSPItem.itemReinforcedIridiumIronPlate, "ABA", "BCB", "ABA", Character.valueOf('A'), IC2Items.getItem("advancedAlloy"), Character.valueOf('B'), IC2Items.getItem("carbonPlate"), Character.valueOf('C'), Ic2Items.iridiumPlate);
        Recipes.advRecipes.addRecipe(SSPItem.itemIridiumIronPlate, "AAA", "ABA", "AAA", Character.valueOf('A'), "plateIron", Character.valueOf('B'), new ItemStack(SSPItem.itemSSP, 1, 7));
        // TODO Recipe Modules
        GameRegistry.addRecipe(new ItemStack(SSPItem.module1, 1), "AAA", "BDB", " C ", 'A', SSPItem.chromium_plate, 'B', SSPItem.wolfram_plate, 'C', SSPItem.michail_plate, 'D', new ItemStack(SSPItem.itemSSP, 1, 4));
        GameRegistry.addRecipe(new ItemStack(SSPItem.module2, 1), "AAA", "BDB", " C ", 'A', SSPItem.platium_plate, 'B', SSPItem.chromium_plate, 'C', SSPItem.wolfram_plate, 'D', new ItemStack(SSPItem.itemSSP, 1, 4));
        GameRegistry.addRecipe(new ItemStack(SSPItem.module3, 1), "AAA", "BDB", " C ", 'A', SSPItem.QuantumItems2, 'B', SSPItem.michail_plate, 'C', SSPItem.chromium_plate, 'D', new ItemStack(SSPItem.itemSSP, 1, 4));
        GameRegistry.addRecipe(new ItemStack(SSPItem.module4, 1), "AAA", "BDB", " C ", 'A', SSPItem.michail_plate, 'B', SSPItem.chromium_plate, 'C', SSPItem.platium_plate, 'D', new ItemStack(SSPItem.itemSSP, 1, 4));
        //TODO Recipes Photoniy Glasses
        GameRegistry.addRecipe(new ItemStack(SSPItem.photoniyglass1, 1), "CAC", "BBB", " C ", 'A', Ic2Items.platelead, 'B', new ItemStack(SSPItem.itemSSP, 1, 3), 'C', Ic2Items.platecopper, 'D', Ic2Items.platetin);
        GameRegistry.addRecipe(new ItemStack(SSPItem.photoniyglass2, 1), "DCD", "HCH", 'A', Ic2Items.platelead, 'C', Ic2Items.platecopper, 'D', Ic2Items.platetin, 'H', SSPItem.photoniyglass1);
        GameRegistry.addRecipe(new ItemStack(SSPItem.photoniyglass3, 1), "DCD", "HCH", 'A', Ic2Items.platelead, 'C', Ic2Items.platetin, 'D', Items.iron_ingot, 'H', SSPItem.photoniyglass2);
        GameRegistry.addRecipe(new ItemStack(SSPItem.photoniyglass4, 1), "DCD", "HCH", 'A', Ic2Items.platecopper, 'C', SSPItem.wolfram_ingot, 'D', Items.redstone, 'H', SSPItem.photoniyglass3);
        GameRegistry.addRecipe(new ItemStack(SSPItem.photoniyglass5, 1), "DCD", "HCH", 'A', Ic2Items.platecopper, 'C', SSPItem.magnesium_ingot, 'D', Ic2Items.platecopper, 'H', SSPItem.photoniyglass4);
        GameRegistry.addRecipe(new ItemStack(SSPItem.photoniyglass6, 1), "DCD", "HCH", 'A', Ic2Items.platecopper, 'C', SSPItem.chromium_ingot, 'D', Ic2Items.plategold, 'H', SSPItem.photoniyglass5);
        GameRegistry.addRecipe(new ItemStack(SSPItem.photoniyglass7, 1), "DCD", "HCH", 'A', Ic2Items.platecopper, 'C', SSPItem.chromium_plate, 'D', Ic2Items.tinBlock, 'H', SSPItem.photoniyglass6);

        GameRegistry.addRecipe(new ItemStack(SSPItem.photoniyglass8, 1), "DCD", "HCH", 'A', Ic2Items.platecopper, 'C', SSPItem.platium_plate, 'D', Blocks.iron_block, 'H', SSPItem.photoniyglass7);
        GameRegistry.addRecipe(new ItemStack(SSPItem.photoniyglass9, 1), "DCD", "HCH", 'A', Ic2Items.platecopper, 'C', Ic2Items.leadBlock, 'D', Ic2Items.plateadviron, 'H', SSPItem.photoniyglass8);
        GameRegistry.addRecipe(new ItemStack(SSPItem.photoniyglass10, 1), "DCD", "HCH", 'A', Ic2Items.platecopper, 'C', SSPItem.michail_plate, 'D', SSPItem.chromium_plate, 'H', SSPItem.photoniyglass9);

        //TODO Recipe Connector and Exp Generator


        GameRegistry.addRecipe(new ItemStack(SSPItem.expgen, 1), "BAB",
                "BCB",
                "BAB", Character.valueOf('A'), IC2Items.getItem("advancedMachine"), Character.valueOf('B'), IC2Items.getItem("cell"), Character.valueOf('C'), IC2Items.getItem("massFabricator"));

        //TODO Start Recipe  Helmet
        Recipes.advRecipes.addRecipe(new ItemStack(SSPItem.spectralSolarHelmet, 1, OreDictionary.WILDCARD_VALUE), "A", "B", 'A', new ItemStack(SSPItem.blockSSPSolarPanel, 1, 4), 'B', new ItemStack(SSPItem.ultimateSolarHelmet, 1, OreDictionary.WILDCARD_VALUE));
        Recipes.advRecipes.addRecipe(new ItemStack(SSPItem.singularSolarHelmet, 1, OreDictionary.WILDCARD_VALUE), "A", "B", 'A', new ItemStack(SSPItem.blockSSPSolarPanel, 1, 6), 'B', new ItemStack(SSPItem.spectralSolarHelmet, 1, OreDictionary.WILDCARD_VALUE));
        Recipes.advRecipes.addRecipe(new ItemStack(SSPItem.advancedSolarHelmet, 1, OreDictionary.WILDCARD_VALUE), " A ", "RBR", "FDF", Character.valueOf('A'), new ItemStack(SSPItem.blockSSPSolarPanel, 1, 0), Character.valueOf('B'), new ItemStack(IC2Items.getItem("nanoHelmet").getItem(), 1, OreDictionary.WILDCARD_VALUE), Character.valueOf('R'), IC2Items.getItem("advancedCircuit"), Character.valueOf('D'),
                IC2Items.getItem("lvTransformer"), Character.valueOf('F'), IC2Items.getItem("insulatedGoldCableItem"));


        Recipes.advRecipes.addRecipe(new ItemStack(SSPItem.hybridSolarHelmet, 1, OreDictionary.WILDCARD_VALUE), " A ", "RBR", "FDF", Character.valueOf('A'), new ItemStack(SSPItem.blockSSPSolarPanel, 1, 1), Character.valueOf('B'), new ItemStack(IC2Items.getItem("quantumHelmet").getItem(), 1, OreDictionary.WILDCARD_VALUE), Character.valueOf('R'), IC2Items.getItem("advancedCircuit"), Character.valueOf('D'),
                IC2Items.getItem("hvTransformer"), Character.valueOf('F'), IC2Items.getItem("glassFiberCableItem"));


        Recipes.advRecipes.addRecipe(new ItemStack(SSPItem.ultimateSolarHelmet, 1, OreDictionary.WILDCARD_VALUE), " A ", "RBR", "FDF", Character.valueOf('A'), new ItemStack(SSPItem.blockSSPSolarPanel, 1, 2), Character.valueOf('B'), new ItemStack(IC2Items.getItem("quantumHelmet").getItem(), 1, OreDictionary.WILDCARD_VALUE), Character.valueOf('R'), IC2Items.getItem("advancedCircuit"), Character.valueOf('D'),
                IC2Items.getItem("hvTransformer"), Character.valueOf('F'), IC2Items.getItem("glassFiberCableItem"));
        //TODO End Recipe Helmet
        GameRegistry.addRecipe(new ItemStack(SSPItem.QuantumItems5, 1), "CBC", "BAB", "CBC", 'A', SSPItem.QuantumItems8, 'B', SSPItem.itemIridiumIronPlate, 'C', SSPItem.QuantumItems3);
        GameRegistry.addRecipe(new ItemStack(SSPItem.nanoSaber1, 1, OreDictionary.WILDCARD_VALUE), "CB ", "CA ", "DEB", 'A', new ItemStack(IC2Items.getItem("nanoSaber").getItem(), 1, OreDictionary.WILDCARD_VALUE), 'B', new ItemStack(SSPItem.QuantumItems8, 1), 'C', IC2Items.getItem("carbonPlate"), 'D', Items.glowstone_dust, 'E', new ItemStack(SSPItem.lapotronCrystal, 1, OreDictionary.WILDCARD_VALUE));
        GameRegistry.addRecipe(new ItemStack(SSPItem.nanoSaber, 1, OreDictionary.WILDCARD_VALUE), "CB ", "CA ", "DEB", 'A', new ItemStack(SSPItem.nanoSaber1, 1, OreDictionary.WILDCARD_VALUE), 'B', new ItemStack(SSPItem.QuantumItems5, 1), 'C', IC2Items.getItem("carbonPlate"), 'D', Items.glowstone_dust, 'E', new ItemStack(SSPItem.lapotronCrystal, 1, OreDictionary.WILDCARD_VALUE));

        GameRegistry.addRecipe(new ItemStack(SSPItem.lapotronCrystal, 1, OreDictionary.WILDCARD_VALUE), "CBC", "BAB", "CBC", 'A', new ItemStack(IC2Items.getItem("lapotronCrystal").getItem(), 1, OreDictionary.WILDCARD_VALUE), 'B', new ItemStack(SSPItem.QuantumItems3, 1), 'C', IC2Items.getItem("iridiumPlate"));
        GameRegistry.addRecipe(new ItemStack(SSPItem.quantumHelmet, 1, OreDictionary.WILDCARD_VALUE), " B ", "ACA", " A ", 'A', SSPItem.QuantumItems6, 'B', new ItemStack(SSPItem.lapotronCrystal, 1, OreDictionary.WILDCARD_VALUE), 'C', new ItemStack(IC2Items.getItem("quantumHelmet").getItem(), 1, OreDictionary.WILDCARD_VALUE));
        GameRegistry.addRecipe(new ItemStack(SSPItem.quantumLeggings, 1, OreDictionary.WILDCARD_VALUE), " B ", "ACA", " A ", 'A', SSPItem.QuantumItems6, 'B', new ItemStack(SSPItem.lapotronCrystal, 1, OreDictionary.WILDCARD_VALUE), 'C', new ItemStack(IC2Items.getItem("quantumLeggings").getItem(), 1, OreDictionary.WILDCARD_VALUE));
        GameRegistry.addRecipe(new ItemStack(SSPItem.quantumBodyarmor, 1, OreDictionary.WILDCARD_VALUE), " B ", "ACA", " A ", 'A', SSPItem.QuantumItems6, 'B', new ItemStack(SSPItem.lapotronCrystal, 1, OreDictionary.WILDCARD_VALUE), 'C', new ItemStack(IC2Items.getItem("quantumBodyarmor").getItem(), 1, OreDictionary.WILDCARD_VALUE));
        GameRegistry.addRecipe(new ItemStack(SSPItem.quantumBoots, 1, OreDictionary.WILDCARD_VALUE), " B ", "ACA", " A ", 'A', SSPItem.QuantumItems6, 'B', new ItemStack(SSPItem.lapotronCrystal, 1, OreDictionary.WILDCARD_VALUE), 'C', new ItemStack(IC2Items.getItem("quantumBoots").getItem(), 1, OreDictionary.WILDCARD_VALUE));
        GameRegistry.addRecipe(new ItemStack(SSPItem.nanoBox, 1), " C ", "CBC", " C ", 'B', SSPItem.dust, 'C', IC2Items.getItem("carbonPlate"));
        GameRegistry.addRecipe(new ItemStack(SSPItem.QuantumItems6, 1), " A ", "ACA", " A ", 'A', IC2Items.getItem("iridiumPlate"), 'C', SSPItem.nanoBox);
        GameRegistry.addRecipe(new ItemStack(SSPItem.QuantumItems7, 1), " A ", "ACA", " A ", 'A', SSPItem.photoniy, 'C', SSPItem.nanoBox);

        //
        GameRegistry.addRecipe(new ItemStack(SSPItem.enderquantumcomponent, 1), "ABA", "BCB", "ABA", 'A', IC2Items.getItem("iridiumPlate"), 'B', Items.ender_eye, 'C', Items.nether_star);
        GameRegistry.addRecipe(new ItemStack(SSPItem.QuantumItems3, 1), "CBC", "BAB", "CBC", 'A', new ItemStack(SSPItem.QuantumItems7, 1), 'B', new ItemStack(SSPItem.itemSSP, 1, 4), 'C', IC2Items.getItem("carbonPlate"));

        // TODO Recipes Chargepad MFSU
        GameRegistry.addRecipe(new ItemStack(SSPItem.Chargepadelectricblock, 1, 0), "ABA", "CDC", 'B', Blocks.stone_pressure_plate, 'A', SSPItem.QuantumItems9, 'D', new ItemStack(SSPItem.electricblock, 1, 0), 'C', Ic2Items.rubber);
        GameRegistry.addRecipe(new ItemStack(SSPItem.Chargepadelectricblock, 1, 1), "ABA", "CDC", 'B', Blocks.stone_pressure_plate, 'A', SSPItem.QuantumItems8, 'D', new ItemStack(SSPItem.electricblock, 1, 1), 'C', Ic2Items.rubber);

        //
        // TODO Panels --> modules
        GameRegistry.addShapelessRecipe(new ItemStack(SSPItem.blockSSPSolarPanel, 1, 0), new ItemStack(SSPItem.module6, 1, 0));
        GameRegistry.addShapelessRecipe(new ItemStack(SSPItem.blockSSPSolarPanel, 1, 1), new ItemStack(SSPItem.module6, 1, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(SSPItem.blockSSPSolarPanel, 1, 2), new ItemStack(SSPItem.module6, 1, 2));
        GameRegistry.addShapelessRecipe(new ItemStack(SSPItem.blockSSPSolarPanel, 1, 3), new ItemStack(SSPItem.module6, 1, 3));
        GameRegistry.addShapelessRecipe(new ItemStack(SSPItem.blockSSPSolarPanel, 1, 4), new ItemStack(SSPItem.module6, 1, 4));
        GameRegistry.addShapelessRecipe(new ItemStack(SSPItem.blockSSPSolarPanel, 1, 5), new ItemStack(SSPItem.module6, 1, 5));
        GameRegistry.addShapelessRecipe(new ItemStack(SSPItem.blockSSPSolarPanel, 1, 6), new ItemStack(SSPItem.module6, 1, 6));
        GameRegistry.addShapelessRecipe(new ItemStack(SSPItem.blockSSPSolarPanel, 1, 7), new ItemStack(SSPItem.module6, 1, 7));
        GameRegistry.addShapelessRecipe(new ItemStack(SSPItem.blockSSPSolarPanel, 1, 8), new ItemStack(SSPItem.module6, 1, 8));
        GameRegistry.addShapelessRecipe(new ItemStack(SSPItem.blockSSPSolarPanel, 1, 9), new ItemStack(SSPItem.module6, 1, 9));
        // TODO modules --> Panels
        GameRegistry.addShapelessRecipe(new ItemStack(SSPItem.module6, 1, 0), new ItemStack(SSPItem.blockSSPSolarPanel, 1, 0));
        GameRegistry.addShapelessRecipe(new ItemStack(SSPItem.module6, 1, 1), new ItemStack(SSPItem.blockSSPSolarPanel, 1, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(SSPItem.module6, 1, 2), new ItemStack(SSPItem.blockSSPSolarPanel, 1, 2));
        GameRegistry.addShapelessRecipe(new ItemStack(SSPItem.module6, 1, 3), new ItemStack(SSPItem.blockSSPSolarPanel, 1, 3));
        GameRegistry.addShapelessRecipe(new ItemStack(SSPItem.module6, 1, 4), new ItemStack(SSPItem.blockSSPSolarPanel, 1, 4));
        GameRegistry.addShapelessRecipe(new ItemStack(SSPItem.module6, 1, 5), new ItemStack(SSPItem.blockSSPSolarPanel, 1, 5));
        GameRegistry.addShapelessRecipe(new ItemStack(SSPItem.module6, 1, 6), new ItemStack(SSPItem.blockSSPSolarPanel, 1, 6));
        GameRegistry.addShapelessRecipe(new ItemStack(SSPItem.module6, 1, 7), new ItemStack(SSPItem.blockSSPSolarPanel, 1, 7));
        GameRegistry.addShapelessRecipe(new ItemStack(SSPItem.module6, 1, 8), new ItemStack(SSPItem.blockSSPSolarPanel, 1, 8));
        GameRegistry.addShapelessRecipe(new ItemStack(SSPItem.module6, 1, 9), new ItemStack(SSPItem.blockSSPSolarPanel, 1, 9));
        //TODO modules wirelles and transformer
        GameRegistry.addRecipe(new ItemStack(SSPItem.module8, 1), "AAA", " D ", "ECE", 'A', SSPItem.spinel_plate, 'C', SSPItem.michail_plate, 'D', new ItemStack(SSPItem.itemSSP, 1, 4), 'E', SSPItem.QuantumItems4);
        GameRegistry.addRecipe(new ItemStack(SSPItem.module7, 1, 1), "AAA", "BDB", "ECE", 'A', SSPItem.compresscarbonultra, 'B', SSPItem.chromium_plate, 'C', SSPItem.wolfram_plate, 'D', new ItemStack(SSPItem.itemSSP, 1, 4), 'E', SSPItem.QuantumItems4);
        GameRegistry.addRecipe(new ItemStack(SSPItem.module7, 1, 2), "AAA", "BDB", "ECE", 'A', SSPItem.compresscarbon, 'B', SSPItem.michail_plate, 'C', SSPItem.chromium_plate, 'D', new ItemStack(SSPItem.itemSSP, 1, 4), 'E', SSPItem.QuantumItems4);

        GameRegistry.addRecipe(new ItemStack(SSPItem.module7, 1, 3), "AAA", "BDB", "ECE", 'A', SSPItem.compresscarbon, 'B', SSPItem.nickelplate, 'C', SSPItem.invarplate, 'D', new ItemStack(SSPItem.itemSSP, 1, 4), 'E', SSPItem.QuantumItems8);
        GameRegistry.addRecipe(new ItemStack(SSPItem.module7, 1, 4), "AAA", "BDB", "ECE", 'A', SSPItem.compresscarbonultra, 'B', SSPItem.spinel_plate, 'C', SSPItem.electriumplate, 'D', new ItemStack(SSPItem.itemSSP, 1, 4), 'E', SSPItem.QuantumItems9);

        GameRegistry.addRecipe(new ItemStack(SSPItem.module7, 1, 0), "ACB", "A B", "   ", 'A', SSPItem.invarplate, 'B', SSPItem.electriumplate, 'C', SSPItem.module3);
        GameRegistry.addRecipe(new ItemStack(SSPItem.module7, 1, 6), "AC ", "   ", "   ", 'A', new ItemStack(SSPItem.module8), 'C', new ItemStack(SSPItem.module7, 1, 3));
        GameRegistry.addRecipe(new ItemStack(SSPItem.module7, 1, 8), "AC ", "   ", "   ", 'A', new ItemStack(SSPItem.module7, 1, 6), 'C', new ItemStack(SSPItem.module7, 1, 4));
        GameRegistry.addRecipe(new ItemStack(SSPItem.module7, 1, 5), "ACA", "   ", "   ", 'A', new ItemStack(SSPItem.module7, 1, 6), 'C', new ItemStack(SSPItem.module8));
        GameRegistry.addRecipe(new ItemStack(SSPItem.module7, 1, 7), "ACA", "   ", "   ", 'A', new ItemStack(SSPItem.module7, 1, 8), 'C', new ItemStack(SSPItem.module8));

        //TODO Recipes cables
        GameRegistry.addRecipe(SSPItem.insulatedCopperCableItem, "BBB", "AAA", "BBB", 'B', Ic2Items.glassFiberCableItem, 'A', SSPItem.photoniy);
        GameRegistry.addRecipe(SSPItem.copperCableItem, "BBB", "AAA", "BBB", 'B', SSPItem.insulatedCopperCableItem, 'A', SSPItem.photoniy);
        GameRegistry.addRecipe(SSPItem.goldCableItem, "BBB", "AAA", "BBB", 'B', SSPItem.copperCableItem, 'A', SSPItem.photoniy);
        GameRegistry.addRecipe(SSPItem.insulatedGoldCableItem, "BBB", "AAA", "BBB", 'B', SSPItem.goldCableItem, 'A', SSPItem.photoniy);
        GameRegistry.addRecipe(SSPItem.doubleInsulatedGoldCableItem, "BBB", "AAA", "BBB", 'B', SSPItem.insulatedGoldCableItem, 'A', SSPItem.photoniy);
        GameRegistry.addRecipe(SSPItem.ironCableItem, "BBB", "AAA", "BBB", 'B', SSPItem.doubleInsulatedGoldCableItem, 'A', SSPItem.photoniy);
        GameRegistry.addRecipe(SSPItem.insulatedIronCableItem, "BBB", "AAA", "BBB", 'B', SSPItem.ironCableItem, 'A', SSPItem.photoniy);
        GameRegistry.addRecipe(SSPItem.doubleInsulatedIronCableItem, "BBB", "AAA", "BBB", 'B', SSPItem.insulatedIronCableItem, 'A', SSPItem.photoniy);
        GameRegistry.addRecipe(SSPItem.trippleInsulatedIronCableItem, "BBB", "AAA", "BBB", 'B', SSPItem.doubleInsulatedIronCableItem, 'A', SSPItem.photoniy);
        GameRegistry.addRecipe(SSPItem.glassFiberCableItem, "BBB", "AAA", "BBB", 'B', SSPItem.trippleInsulatedIronCableItem, 'A', SSPItem.photoniy_ingot);
        GameRegistry.addRecipe(new ItemStack(SSPItem.cable, 1, 10), "BBB", "AAA", "BBB", 'B', SSPItem.glassFiberCableItem, 'A', SSPItem.photoniy_ingot);

        // TODO recipes Molecolar Core and Recipe Molecular Transformer
        GameRegistry.addRecipe(SSPItem.itemMTCore, "MXM", "M M", "MXM", 'M', SSPItem.itemIrradiantGlassPane, 'X', new ItemStack(Ic2Items.reactorReflector.getItem(), 1, OreDictionary.WILDCARD_VALUE));
        GameRegistry.addRecipe(SSPItem.moleculartransformer, "MXM", "ABA", "MXM", Character.valueOf('M'), IC2Items.getItem("advancedMachine"), Character.valueOf('X'), IC2Items.getItem("evTransformer"), Character.valueOf('A'), IC2Items.getItem("advancedCircuit"), Character.valueOf('B'),
                SSPItem.itemMTCore);
        //TODO Recipes Panels
        GameRegistry.addRecipe(new ItemStack(SSPItem.blockSSPSolarPanel, 1, 0), "ABA", "RHR", " L ", 'B', SSPItem.advanced_core, 'A', SSPItem.photoniyglass1, 'H', IC2Items.getItem("advancedCircuit"), 'G', IC2Items.getItem("iridiumPlate"), 'R', IC2Items.getItem("carbonPlate"), 'Y', SSPItem.photoniy, 'L', IC2Items.getItem("solarPanel"));
        GameRegistry.addRecipe(new ItemStack(SSPItem.blockSSPSolarPanel, 1, 1), "ABA", "YDY", "DDD", 'B', SSPItem.hybrid_core, 'A', SSPItem.photoniyglass2, 'D', new ItemStack(SSPItem.blockSSPSolarPanel, 1, 0), 'Y', Ic2Items.iridiumPlate);
        GameRegistry.addRecipe(new ItemStack(SSPItem.blockSSPSolarPanel, 1, 2), "ABA", "YDY", "DDD", 'B', SSPItem.ultimate_core, 'A', SSPItem.photoniyglass3, 'D', new ItemStack(SSPItem.blockSSPSolarPanel, 1, 1), 'Y', Ic2Items.iridiumPlate);
        GameRegistry.addRecipe(new ItemStack(SSPItem.blockSSPSolarPanel, 1, 3), "ABA", "YDY", "DDD", 'B', SSPItem.itemQuantumCore, 'A', SSPItem.photoniyglass4, 'D', new ItemStack(SSPItem.blockSSPSolarPanel, 1, 2), 'Y', Ic2Items.iridiumPlate);
        GameRegistry.addRecipe(new ItemStack(SSPItem.blockSSPSolarPanel, 1, 4), "ABA", "YDY", "DDD", 'B', SSPItem.spectralcore, 'A', SSPItem.photoniyglass5, 'D', new ItemStack(SSPItem.blockSSPSolarPanel, 1, 3), 'Y', SSPItem.QuantumItems2);
        GameRegistry.addRecipe(new ItemStack(SSPItem.blockSSPSolarPanel, 1, 5), "ABA", "YDY", "DDD", 'B', SSPItem.protoncore, 'A', SSPItem.photoniyglass6, 'D', new ItemStack(SSPItem.blockSSPSolarPanel, 1, 4), 'Y', SSPItem.QuantumItems2);
        GameRegistry.addRecipe(new ItemStack(SSPItem.blockSSPSolarPanel, 1, 6), "ABA", "YDY", "DDD", 'B', SSPItem.singularcore, 'A', SSPItem.photoniyglass7, 'D', new ItemStack(SSPItem.blockSSPSolarPanel, 1, 5), 'Y', SSPItem.QuantumItems4);
        GameRegistry.addRecipe(new ItemStack(SSPItem.blockSSPSolarPanel, 1, 7), "ABA", "YDY", "DDD", 'B', SSPItem.admincore, 'A', SSPItem.photoniyglass8, 'D', new ItemStack(SSPItem.blockSSPSolarPanel, 1, 6), 'Y', SSPItem.QuantumItems4);
        GameRegistry.addRecipe(new ItemStack(SSPItem.blockSSPSolarPanel, 1, 8), "ABA", "YDY", "DDD", 'B', SSPItem.photoniccore, 'A', SSPItem.photoniyglass9, 'D', new ItemStack(SSPItem.blockSSPSolarPanel, 1, 7), 'Y', SSPItem.QuantumItems4);
        GameRegistry.addRecipe(new ItemStack(SSPItem.blockSSPSolarPanel, 1, 9), "ABA", "YDY", "DDD", 'B', SSPItem.neutroniumcore, 'A', SSPItem.photoniyglass10, 'D', new ItemStack(SSPItem.blockSSPSolarPanel, 1, 8), 'Y', SSPItem.QuantumItems4);
        //TODO Recipes Proton Rods
        Recipes.advRecipes.addRecipe(SSPItem.reactorprotonDual, "SQS", Character.valueOf('S'), SSPItem.reactorprotonSimple, Character.valueOf('Q'), Ic2Items.plateiron);
        Recipes.advRecipes.addRecipe(SSPItem.reactorprotonQuad, "SQS", "CQC", "SQS", Character.valueOf('S'), SSPItem.reactorprotonSimple, Character.valueOf('Q'), Ic2Items.plateiron, Character.valueOf('C'), Ic2Items.platecopper);

        Recipes.advRecipes.addRecipe(SSPItem.reactorprotonQuad, "SQS", Character.valueOf('S'), SSPItem.reactorprotonDual, Character.valueOf('Q'), Ic2Items.plateiron, Character.valueOf('C'), Ic2Items.platecopper);
        Recipes.advRecipes.addRecipe(SSPItem.reactorprotoneit, "SQS", Character.valueOf('S'), SSPItem.reactorprotonQuad, Character.valueOf('Q'), Ic2Items.plateiron, Character.valueOf('C'), Ic2Items.platecopper);
        Recipes.advRecipes.addRecipe(SSPItem.reactorprotoneit, "SQS", "CQC", "SQS", Character.valueOf('S'), SSPItem.reactorprotonDual, Character.valueOf('Q'), Ic2Items.plateiron, Character.valueOf('C'), Ic2Items.platecopper);
        //TODO Recipes Toriy Rods
        Recipes.advRecipes.addRecipe(SSPItem.reactortoriyDual, "SQS", Character.valueOf('S'), SSPItem.reactortoriySimple, Character.valueOf('Q'), Ic2Items.plateiron);
        Recipes.advRecipes.addRecipe(SSPItem.reactortoriyQuad, "SQS", "CQC", "SQS", Character.valueOf('S'), SSPItem.reactortoriySimple, Character.valueOf('Q'), Ic2Items.plateiron, Character.valueOf('C'), Ic2Items.platecopper);

        Recipes.advRecipes.addRecipe(SSPItem.reactortoriyQuad, "SQS", Character.valueOf('S'), SSPItem.reactortoriyDual, Character.valueOf('Q'), Ic2Items.plateiron, Character.valueOf('C'), Ic2Items.platecopper);


    }
}
