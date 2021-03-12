package com.Denfop.block.Base;

import com.Denfop.Config;
import com.Denfop.IUCore;
import com.Denfop.SSPItem;
import com.Denfop.SSPItem.FluidXP;
import com.Denfop.block.expgen.BlockExpGen;
import com.Denfop.block.expgen.TextureHooks;
import com.Denfop.block.mechanism.BlockMachine;
import com.Denfop.fluid.Ic2Fluid;
import com.Denfop.integration.Botania.BotaniaIntegration;
import com.Denfop.integration.Botania.ItemReactorTerasteel;
import com.Denfop.item.Modules.ItemWirelessModule;
import com.Denfop.item.Upgrade.ItemUpgradeModule;
import com.Denfop.item.armour.ItemArmorImprovemedQuantum;
import com.Denfop.item.base.ItemCable;
import com.Denfop.item.energy.ItemBattery;
import com.Denfop.item.energy.ItemNanoSaber;
import com.Denfop.item.energy.ItemNanoSaber1;
import com.Denfop.item.energy.ultDDrill;
import com.Denfop.item.reactor.ItemRadioactive;
import com.Denfop.item.reactor.ItemReactorHeatStorage;
import com.Denfop.item.reactor.ItemReactorproton;
import com.Denfop.item.rotor.ItemAdvancedWindRotor;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.*;

public class BlocksItems {
    private static final Map<String, Fluid> fluids = new HashMap<String, Fluid>();
    private static final Map<String, Block> fluidBlocks = new HashMap<String, Block>();
    private static final Map<String, String> renames = new HashMap<String, String>();
    private static final Set<String> dropped = new HashSet<String>();

    public static void init() {
        initPotions();
        initBlocks();
        initFluids();
        initItems();
        initMigration();
    }

    private static void initPotions() {
    }

    private static void initBlocks() {


    }

    private static void initFluids() {
        MaterialLiquid materialLiquid = new MaterialLiquid(MapColor.silverColor);
        registerIC2fluid("fluidUuMatter", Material.water, 3867955, 3000, 3000, 0, 300, false);
        if (!Loader.isModLoaded("OpenBlocks")) {
            FluidRegistry.registerFluid(FluidXP.xpJuice);
            FluidXP.xpJuice.setIcons(TextureHooks.Icons.xpJuiceStill, TextureHooks.Icons.xpJuiceFlowing);
        } else {

            FluidXP.xpJuice = FluidRegistry.getFluid("xpjuice");
        }
    }

    private static void initItems() {
        SSPItem.photon = new ItemStack(new ItemAdvancedWindRotor("photon", Config.Radius3, Config.durability3, Config.efficiency3, Config.minWindStrength3, Config.maxWindStrength3, new ResourceLocation("supersolarpanel", "textures/items/carbon_rotor_model6.png")));
        SSPItem.neutron = new ItemStack(new ItemAdvancedWindRotor("neutron", Config.Radius4, Config.durability4, Config.efficiency4, Config.minWindStrength4, Config.maxWindStrength4, new ResourceLocation("supersolarpanel", "textures/items/carbon_rotor_model5.png")));
        SSPItem.myphical = new ItemStack(new ItemAdvancedWindRotor("myphical", Config.Radius5, Config.durability5, Config.efficiency5, Config.minWindStrength5, Config.maxWindStrength5, new ResourceLocation("supersolarpanel", "textures/items/carbon_rotor_model4.png")));
        SSPItem.electricblock = new BlockElectric();
        SSPItem.mfeUnit = new ItemStack(SSPItem.electricblock, 1, 0);
        SSPItem.mfsUnit = new ItemStack(SSPItem.electricblock, 1, 1);
        SSPItem.mfeUnit1 = new ItemStack(SSPItem.electricblock1, 1, 0);
        SSPItem.mfsUnit1 = new ItemStack(SSPItem.electricblock1, 1, 1);
        SSPItem.electricblock.setCreativeTab(IUCore.tabssp);
        SSPItem.machines = new BlockMachine();
        SSPItem.machines.setCreativeTab(IUCore.tabssp);
        SSPItem.ultDDrill = new ultDDrill(Item.ToolMaterial.EMERALD).setUnlocalizedName("advDDrill");

        SSPItem.lapotronCrystal = new ItemBattery("itemBatLamaCrystal", Config.Storagequantumsuit, 8092.0D, 4);
        SSPItem.nanoSaber = new ItemNanoSaber("itemNanoSaber", Config.maxCharge, Config.transferLimit, Config.tier, Config.spectralsaberactive, Config.spectralsabernotactive);
        SSPItem.nanoSaber1 = new ItemNanoSaber1("itemNanoSaber1", Config.maxCharge1, Config.transferLimit1, Config.tier1, Config.spectralsaberactive1, Config.spectralsabernotactive1);
        SSPItem.quantumHelmet = new ItemArmorImprovemedQuantum("itemArmorQuantumHelmet", 0, Config.armor_maxcharge, Config.armor_transferlimit, Config.tier);
        SSPItem.quantumBodyarmor = new ItemArmorImprovemedQuantum("itemArmorQuantumChestplate", 1, Config.armor_maxcharge_body, Config.armor_transferlimit, Config.tier);
        SSPItem.quantumLeggings = new ItemArmorImprovemedQuantum("itemArmorQuantumLegs", 2, Config.armor_maxcharge, Config.armor_transferlimit, Config.tier);
        SSPItem.quantumBoots = new ItemArmorImprovemedQuantum("itemArmorQuantumBoots", 3, Config.armor_maxcharge, Config.armor_transferlimit, Config.tier);


        SSPItem.expgen = new BlockExpGen("expGen", Material.rock);
        SSPItem.module8 = new ItemWirelessModule();
        //
        SSPItem.iridium = new ItemStack(new ItemAdvancedWindRotor("iridium", Config.Radius, Config.durability, Config.efficiency, Config.minWindStrength, Config.maxWindStrength, new ResourceLocation("supersolarpanel", "textures/items/carbon_rotor_model1.png")));
        SSPItem.compressiridium = new ItemStack(new ItemAdvancedWindRotor("compressiridium", Config.Radius1, Config.durability1, Config.efficiency1, Config.minWindStrength1, Config.maxWindStrength1, new ResourceLocation("supersolarpanel", "textures/items/carbon_rotor_model2.png")));
        SSPItem.spectral = new ItemStack(new ItemAdvancedWindRotor("spectral", Config.Radius2, Config.durability2, Config.efficiency2, Config.minWindStrength2, Config.maxWindStrength2, new ResourceLocation("supersolarpanel", "textures/items/carbon_rotor_model3.png")));
        SSPItem.reactorDepletedprotonSimple = new ItemStack(new ItemRadioactive("reactorDepletedprotonSimple", 10, 100));
        SSPItem.reactorDepletedprotonDual = new ItemStack(new ItemRadioactive("reactorDepletedprotonDual", 10, 100));
        SSPItem.reactorDepletedprotonQuad = new ItemStack(new ItemRadioactive("reactorDepletedprotonQuad", 10, 100));
        SSPItem.reactorDepletedprotoneit = new ItemStack(new ItemRadioactive("reactorDepletedprotoneit", 10, 100));
        SSPItem.reactorprotonSimple = new ItemStack(new ItemReactorproton("reactorprotonSimple", 1, Config.ProtonRodCells, Config.ProtonRodHeat, Config.ProtonPower));
        SSPItem.reactorprotonDual = new ItemStack(new ItemReactorproton("reactorprotonDual", 2, Config.ProtonRodCells, Config.ProtonRodHeat, Config.ProtonPower));
        SSPItem.reactorprotonQuad = new ItemStack(new ItemReactorproton("reactorprotonQuad", 4, Config.ProtonRodCells, Config.ProtonRodHeat, Config.ProtonPower));
        SSPItem.reactorprotoneit = new ItemStack(new ItemReactorproton("reactorprotoneit", 8, Config.ProtonRodCells, Config.ProtonRodHeat, Config.ProtonPower));
        SSPItem.proton = new ItemRadioactive("proton", 150, 100);
        SSPItem.toriy = new ItemRadioactive("toriy", 150, 100);
        //
        SSPItem.reactortoriySimple = new ItemStack(new ItemReactorproton("reactortoriySimple", 1, Config.toriyRodCells, Config.toriyRodHeat, Config.toriyPower));
        SSPItem.reactortoriyDual = new ItemStack(new ItemReactorproton("reactortoriyDual", 2, Config.toriyRodCells, Config.toriyRodHeat, Config.toriyPower));
        SSPItem.reactortoriyQuad = new ItemStack(new ItemReactorproton("reactortoriyQuad", 4, Config.toriyRodCells, Config.toriyRodHeat, Config.toriyPower));
        SSPItem.reactorDepletedtoriySimple = new ItemStack(new ItemRadioactive("reactorDepletedtoriySimple", 10, 100));
        SSPItem.reactorDepletedtoriyDual = new ItemStack(new ItemRadioactive("reactorDepletedtoriyDual", 10, 100));
        SSPItem.reactorDepletedtoriyQuad = new ItemStack(new ItemRadioactive("reactorDepletedtoriyQuad", 10, 100));
        //

        SSPItem.protonshard = new ItemRadioactive("protonshard", 150, 100);
        SSPItem.reactorCoolantmax = new ItemStack(new ItemReactorHeatStorage("reactorCoolantmax", 240000));
        SSPItem.reactorCoolanttwelve = new ItemStack(new ItemReactorHeatStorage("reactorCoolanttwelve", 120000));
        SSPItem.module = new ItemUpgradeModule();
        SSPItem.cable = new ItemCable();

        if (Config.BotaniaLoaded) {
            BotaniaIntegration.reactorterastrellSimple = new ItemStack(new ItemReactorTerasteel("reactorterastrellSimple", 1));
            BotaniaIntegration.reactorterastrellDual = new ItemStack(new ItemReactorTerasteel("reactorterastrellDual", 2));
            BotaniaIntegration.reactorterastrellQuad = new ItemStack(new ItemReactorTerasteel("reactorterastrellQuad", 4));
            BotaniaIntegration.reactorDepletedterastrellSimple = new ItemStack(new ItemRadioactive("reactorDepletedterastrellSimple", 10, 100));
            BotaniaIntegration.reactorDepletedterastrellDual = new ItemStack(new ItemRadioactive("reactorDepletedterastrellDual", 10, 100));
            BotaniaIntegration.reactorDepletedterastrellQuad = new ItemStack(new ItemRadioactive("reactorDepletedterastrellQuad", 10, 100));

        }
    }

    private static void initMigration() {
        renames.put("blockfluidUuMatter", "fluidUuMatter");

    }

    private static void registerIC2fluid(String internalName, Material material, int color, int density, int viscosity, int luminosity, int temperature, boolean isGaseous) {
        Block block = null;
        if (!internalName.startsWith("fluid"))
            throw new IllegalArgumentException("Invalid fluid block name: " + internalName);
        String fluidName = "ic3" + internalName.substring("fluid".length()).toLowerCase(Locale.ENGLISH);
        Fluid fluid = (new Ic2Fluid(fluidName)).setDensity(density).setViscosity(viscosity).setLuminosity(luminosity).setTemperature(temperature).setGaseous(isGaseous);
        if (!FluidRegistry.registerFluid(fluid))
            fluid = FluidRegistry.getFluid(fluidName);
        if (!fluid.canBePlacedInWorld()) {
            BlockIC2Fluid blockIC2Fluid = new BlockIC2Fluid(internalName, fluid, material, color);
            fluid.setBlock(blockIC2Fluid);
            fluid.setUnlocalizedName(blockIC2Fluid.getUnlocalizedName());
        } else {
            block = fluid.getBlock();
        }
        fluids.put(internalName, fluid);
        fluidBlocks.put(internalName, block);
    }

    public static void onMissingMappings(FMLMissingMappingsEvent event) {
        for (FMLMissingMappingsEvent.MissingMapping mapping : event.get()) {
            if (!mapping.name.startsWith("IC2:"))
                continue;
            String subName = mapping.name.substring("IC2".length() + 1);
            String newName = renames.get(subName);
            if (newName != null) {
                String name = "IC2:" + newName;
                if (mapping.type == GameRegistry.Type.BLOCK) {
                    mapping.remap(GameData.getBlockRegistry().getRaw(name));
                    continue;
                }
                mapping.remap(GameData.getItemRegistry().getRaw(name));
                continue;
            }
            if (dropped.contains(subName))
                mapping.ignore();
        }
    }

    public static Fluid getFluid(String name) {
        return fluids.get(name);
    }

    public static Block getFluidBlock(String blockName) {
        return fluidBlocks.get(blockName);
    }

    public static Collection<String> getIc2FluidNames() {
        return fluids.keySet();
    }

    public static void registermetadata() {
        SSPItem.itemIrradiantUranium = new ItemStack(SSPItem.itemSSP.setUnlocalizedName("itemIrradiantUranium"), 1, 0);
        SSPItem.itemEnrichedPhotoniyAlloy = new ItemStack(SSPItem.itemSSP.setUnlocalizedName("itemEnrichedSunnariumAlloy"), 1, 1);
        SSPItem.itemIrradiantGlassPane = new ItemStack(SSPItem.itemSSP.setUnlocalizedName("itemIrradiantGlassPlane"), 1, 3);
        SSPItem.itemIridiumIronPlate = new ItemStack(SSPItem.itemSSP.setUnlocalizedName("itemIridiumIronPlate"), 1, 4);
        SSPItem.itemReinforcedIridiumIronPlate = new ItemStack(SSPItem.itemSSP.setUnlocalizedName("itemReinforcedIridiumIronPlate"), 1, 5);
        SSPItem.itemIrradiantReinforcedPlate = new ItemStack(SSPItem.itemSSP.setUnlocalizedName("itemIrradiantReinforcedPlate"), 1, 6);

        SSPItem.ingotIridium = new ItemStack(SSPItem.itemSSP.setUnlocalizedName("ingotIridium"), 1, 7);
        SSPItem.itemUranIngot = new ItemStack(SSPItem.itemSSP.setUnlocalizedName("itemUranIngot"), 1, 8);
        SSPItem.itemMTCore = new ItemStack(SSPItem.itemSSP.setUnlocalizedName("itemMTCore"), 1, 9);
        SSPItem.itemQuantumCore = new ItemStack(SSPItem.itemSSP.setUnlocalizedName("itemQuantumCore"), 1, 10);


        SSPItem.module61 = new ItemStack(SSPItem.module6.setUnlocalizedName("module61"), 1, 0);
        SSPItem.module62 = new ItemStack(SSPItem.module6.setUnlocalizedName("module62"), 1, 1);
        SSPItem.module63 = new ItemStack(SSPItem.module6.setUnlocalizedName("module63"), 1, 2);
        SSPItem.module64 = new ItemStack(SSPItem.module6.setUnlocalizedName("module64"), 1, 3);
        SSPItem.module65 = new ItemStack(SSPItem.module6.setUnlocalizedName("module65"), 1, 4);
        SSPItem.module66 = new ItemStack(SSPItem.module6.setUnlocalizedName("module66"), 1, 5);
        SSPItem.module67 = new ItemStack(SSPItem.module6.setUnlocalizedName("module67"), 1, 6);
        SSPItem.module68 = new ItemStack(SSPItem.module6.setUnlocalizedName("module68"), 1, 7);
        SSPItem.module69 = new ItemStack(SSPItem.module6.setUnlocalizedName("module69"), 1, 8);
        SSPItem.module70 = new ItemStack(SSPItem.module6.setUnlocalizedName("module70"), 1, 9);
        SSPItem.module71 = new ItemStack(SSPItem.module7.setUnlocalizedName("module71"), 1, 0);
        SSPItem.module72 = new ItemStack(SSPItem.module7.setUnlocalizedName("module72"), 1, 1);
        SSPItem.module73 = new ItemStack(SSPItem.module7.setUnlocalizedName("module73"), 1, 2);
        SSPItem.copperCableBlock = new ItemStack(SSPItem.BlockCable, 1, 1);
        SSPItem.insulatedCopperCableBlock = new ItemStack(SSPItem.BlockCable, 1, 0);
        SSPItem.goldCableBlock = new ItemStack(SSPItem.BlockCable, 1, 2);
        SSPItem.insulatedGoldCableBlock = new ItemStack(SSPItem.BlockCable, 1, 3);
        SSPItem.doubleInsulatedGoldCableBlock = new ItemStack(SSPItem.BlockCable, 1, 4);
        SSPItem.ironCableBlock = new ItemStack(SSPItem.BlockCable, 1, 5);
        SSPItem.insulatedIronCableBlock = new ItemStack(SSPItem.BlockCable, 1, 6);
        SSPItem.doubleInsulatedIronCableBlock = new ItemStack(SSPItem.BlockCable, 1, 7);
        SSPItem.trippleInsulatedIronCableBlock = new ItemStack(SSPItem.BlockCable, 1, 8);
        SSPItem.glassFiberCableBlock = new ItemStack(SSPItem.BlockCable, 1, 9);

        SSPItem.glassFiberCableItem1 = new ItemStack(SSPItem.BlockCable, 1, 10);
        SSPItem.copperCableItem = new ItemStack(SSPItem.cable, 1, 1);
        SSPItem.insulatedCopperCableItem = new ItemStack(SSPItem.cable, 1, 0);
        SSPItem.goldCableItem = new ItemStack(SSPItem.cable, 1, 2);
        SSPItem.insulatedGoldCableItem = new ItemStack(SSPItem.cable, 1, 3);
        SSPItem.doubleInsulatedGoldCableItem = new ItemStack(SSPItem.cable, 1, 4);
        SSPItem.ironCableItem = new ItemStack(SSPItem.cable, 1, 5);
        SSPItem.insulatedIronCableItem = new ItemStack(SSPItem.cable, 1, 6);
        SSPItem.doubleInsulatedIronCableItem = new ItemStack(SSPItem.cable, 1, 7);
        SSPItem.trippleInsulatedIronCableItem = new ItemStack(SSPItem.cable, 1, 8);
        SSPItem.glassFiberCableItem = new ItemStack(SSPItem.cable, 1, 9);
        //  SuperSolarPanels.macerator = new ItemStack(SuperSolarPanels.machines, 1, 1);
        //  SuperSolarPanels.extractor = new ItemStack(SuperSolarPanels.machines, 1, 2);
        //   SuperSolarPanels.compressor = new ItemStack(SuperSolarPanels.machines, 1, 3);


        SSPItem.massFabricator = new ItemStack(SSPItem.machines, 1, 5);
        //   SuperSolarPanels.compressor1 = new ItemStack(SuperSolarPanels.machines, 1, 4);
        SSPItem.massFabricator1 = new ItemStack(SSPItem.machines, 1, 1);
        // SuperSolarPanels.macerator1 = new ItemStack(SuperSolarPanels.machines, 1, 6);
        //  SuperSolarPanels.electroFurnace = new ItemStack(SuperSolarPanels.machines, 1, 7);
        //   SuperSolarPanels.electroFurnace1 = new ItemStack(SuperSolarPanels.machines, 1, 8);
        SSPItem.massFabricator2 = new ItemStack(SSPItem.machines, 1, 2);
        SSPItem.massFabricator3 = new ItemStack(SSPItem.machines, 1, 3);
        //   SuperSolarPanels.metalformer = new ItemStack(SuperSolarPanels.machines, 1, 11);
        //   SuperSolarPanels.metalformer1 = new ItemStack(SuperSolarPanels.machines, 1, 12);
        SSPItem.alloymachine = new ItemStack(SSPItem.machines, 1, 4);
        SSPItem.generationmicrochip = new ItemStack(SSPItem.machines, 1, 6);
        SSPItem.moleculartransformer = new ItemStack(SSPItem.machines, 1, 0);


    }
}
