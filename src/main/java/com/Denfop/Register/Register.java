package com.Denfop.Register;

import com.Denfop.IUCore;
import com.Denfop.SSPItem;
import com.Denfop.block.AdminPanel.Adminsolarpanel;
import com.Denfop.block.AdminPanel.ItemAdminSolarPanel;
import com.Denfop.block.Base.BlockElectric;
import com.Denfop.block.Base.BlockSSPSolarPanel;
import com.Denfop.block.Chargepad.BlockChargepad;
import com.Denfop.block.Chargepad.ItemBlockChargepad;
import com.Denfop.block.RadiationBlock.RadiationBlock;
import com.Denfop.block.Sintezator.ItemSintezator;
import com.Denfop.block.Sintezator.Sintezator;
import com.Denfop.block.cable.BlockCable;
import com.Denfop.block.expgen.ItemBlockEG;
import com.Denfop.block.mechanism.BlockMachine;
import com.Denfop.block.mechanism.BlockMachines;
import com.Denfop.block.mechanism.BlockMachines1;
import com.Denfop.block.ore.*;
import com.Denfop.item.ItemCell;
import com.Denfop.item.ItemSSPCrafring;
import com.Denfop.item.Machina.ItemMachine1;
import com.Denfop.item.Machina.ItemMachines;
import com.Denfop.item.Machina.ItemMachines2;
import com.Denfop.item.Modules.module8;
import com.Denfop.item.RadionBlock.ItemToriyOre;
import com.Denfop.item.armour.ItemSolarPanelHelmet;
import com.Denfop.item.base.ItemBlockIC2;
import com.Denfop.item.base.ItemElectricBlock;
import com.Denfop.item.base.ItemSSPSolarPanel;
import com.Denfop.item.base.SSPItemBase;
import com.Denfop.item.block.ItemBlockOre;
import com.Denfop.item.matter.matter;
import com.Denfop.tiles.ExpGen.TileXPGenPublic;
import com.Denfop.tiles.Mechanism.*;
import com.Denfop.tiles.NeutroniumGenerator.TileBitGen2;
import com.Denfop.tiles.Sintezator.TileEntitySintezator;
import com.Denfop.tiles.base.TileEntityAdminSolarPanel;
import com.Denfop.tiles.base.TileEntityCable;
import com.Denfop.tiles.base.TileEntityMolecularTransformer;
import com.Denfop.tiles.base.TileRadioation;
import com.Denfop.tiles.overtimepanel.*;
import com.Denfop.tiles.wiring.Chargepad.TileEntityChargepadMFES;
import com.Denfop.tiles.wiring.Chargepad.TileEntityChargepadMFSUS;
import com.Denfop.tiles.wiring.Storage.TileEntityElectricBatBox;
import com.Denfop.tiles.wiring.Storage.TileEntityElectricMFE;
import com.Denfop.tiles.wiring.Storage.TileEntityElectricMFSU;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemArmor;

public class Register {
    public static void register() {

        GameRegistry.registerBlock(SSPItem.toriyore = new RadiationBlock()
                .setBlockTextureName("supersolarpanel:toriyore").setHardness(0.6f)
                .setStepSound(Block.soundTypeStone).setBlockName("toriyore"), ItemToriyOre.class, "toriyore");

        GameRegistry.registerItem(SSPItem.magnesium_ingot = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("magnesium_ingot").setTextureName("supersolarpanel:magnesium_ingot"), "magnesium_ingot");
        GameRegistry.registerItem(SSPItem.magnesium_plate = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("magnesium_plate").setTextureName("supersolarpanel:magnesium_plate"), "magnesium_plate");
        GameRegistry.registerItem(SSPItem.magnesium_nugget = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("magnesium_nugget").setTextureName("supersolarpanel:magnesium_nugget"), "magnesium_nugget");
        GameRegistry.registerItem(SSPItem.caravky_ingot = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("caravky").setTextureName("supersolarpanel:caravky"), "caravky");


        GameRegistry.registerItem(SSPItem.advanced_core = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("advanced_core").setTextureName("supersolarpanel:advanced_core"), "advanced_core");
        GameRegistry.registerItem(SSPItem.hybrid_core = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("hybrid_core").setTextureName("supersolarpanel:hybrid_core"), "hybrid_core");
        GameRegistry.registerItem(SSPItem.ultimate_core = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("ultimate_core").setTextureName("supersolarpanel:ultimate_core"), "ultimate_core");
        GameRegistry.registerItem(SSPItem.spectralSolarHelmet = new ItemSolarPanelHelmet(ItemArmor.ArmorMaterial.DIAMOND, IUCore.proxy.addArmor("spectralSolarHelmet"), 0, 4).setUnlocalizedName("spectralSolarHelmet"), "spectral_solar_helmet");
        GameRegistry.registerItem(SSPItem.singularSolarHelmet = new ItemSolarPanelHelmet(ItemArmor.ArmorMaterial.DIAMOND, IUCore.proxy.addArmor("singularSolarHelmet"), 0, 5).setUnlocalizedName("singularSolarHelmet"), "singular_solar_helmet");
        GameRegistry.registerBlock(SSPItem.blockSSPSolarPanel = new BlockSSPSolarPanel(), ItemSSPSolarPanel.class, "BlockSSPSolarPanel");

        GameRegistry.registerItem(SSPItem.enderquantumcomponent = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("Ender-QuantumComponent").setTextureName("supersolarpanel:enderquantumcomponent"), "enderquantumcomponent");


        GameRegistry.registerBlock(SSPItem.blockadmin = new Adminsolarpanel(), ItemAdminSolarPanel.class, "Aminpanel");
        GameRegistry.registerBlock(SSPItem.machines = new BlockMachine(), ItemMachine1.class, "machines");
        GameRegistry.registerBlock(SSPItem.electricblock = new BlockElectric(), ItemElectricBlock.class, "electricblock");
        GameRegistry.registerBlock(SSPItem.Chargepadelectricblock = new BlockChargepad(), ItemBlockChargepad.class, "BlockChargepad");


        GameRegistry.registerItem(SSPItem.singularcore = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("singularcore").setTextureName("supersolarpanel:singularcore"), "singularcore");
        GameRegistry.registerItem(SSPItem.spectralcore = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("spectralcore").setTextureName("supersolarpanel:spectralcore"), "spectralcore");
        GameRegistry.registerItem(SSPItem.photoniy = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("photoniy").setTextureName("supersolarpanel:photoniy"), "photoniy");
        GameRegistry.registerItem(SSPItem.photoniy_ingot = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("photoniy_ingot").setTextureName("supersolarpanel:photoniy_ingot"), "photoniy_ingot");
        GameRegistry.registerItem(SSPItem.dust = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("dust").setTextureName("supersolarpanel:dust"), "dust");
        //
        GameRegistry.registerItem(SSPItem.spinelcrushedore = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("spinelcrushedore").setTextureName("supersolarpanel:spinelcrushedore"), "spinelcrushedore");
        GameRegistry.registerItem(SSPItem.platiumcrushedore = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("platiumcrushedore").setTextureName("supersolarpanel:platiumcrushedore"), "platiumcrushedore");
        GameRegistry.registerItem(SSPItem.nickelcrushedore = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("nickelcrushedore").setTextureName("supersolarpanel:nickelcrushedore"), "nickelcrushedore");
        GameRegistry.registerItem(SSPItem.magnesiumcrushedore = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("magnesiumcrushedore").setTextureName("supersolarpanel:magnesiumcrushedore"), "magnesiumcrushedore");
        GameRegistry.registerItem(SSPItem.wolframcrushedore = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("wolframcrushedore").setTextureName("supersolarpanel:wolframcrushedore"), "wolframcrushedore");
        GameRegistry.registerItem(SSPItem.iridiumcrushedore = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("iridiumcrushedore").setTextureName("supersolarpanel:iridiumcrushedore"), "iridiumcrushedore");
        //

        GameRegistry.registerItem(SSPItem.iridium_nugget = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("iridium_nugget").setTextureName("supersolarpanel:iridium_nugget"), "iridium_nugget");
        GameRegistry.registerItem(SSPItem.michail_plate = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("michail_plate").setTextureName("supersolarpanel:michail_plate"), "michail_plate");
        GameRegistry.registerItem(SSPItem.mikhail_ingot = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("mikhail_ingot").setTextureName("supersolarpanel:mikhail_ingot"), "mikhail_ingot");
        GameRegistry.registerItem(SSPItem.mikhail_nugget = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("mikhail_nugget").setTextureName("supersolarpanel:mikhail_nugget"), "mikhail_nugget");
        GameRegistry.registerItem(SSPItem.platium_ingot = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("platium_ingot").setTextureName("supersolarpanel:platium_ingot"), "platium_ingot");
        GameRegistry.registerItem(SSPItem.platium_nugget = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("platium_nugget").setTextureName("supersolarpanel:platium_nugget"), "platium_nugget");
        GameRegistry.registerItem(SSPItem.platium_plate = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("platium_plate").setTextureName("supersolarpanel:platium_plate"), "platium_plate");
        GameRegistry.registerItem(SSPItem.spinel_ingot = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("spinel_ingot").setTextureName("supersolarpanel:spinel_ingot"), "spinel_ingot");
        GameRegistry.registerItem(SSPItem.spinel_nugget = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("spinel_nugget").setTextureName("supersolarpanel:spinel_nugget"), "spinel_nugget");
        GameRegistry.registerItem(SSPItem.spinel_plate = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("spinel_plate").setTextureName("supersolarpanel:spinel_plate"), "spinel_plate");
        GameRegistry.registerItem(SSPItem.wolfram_ingot = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("wolfram_ingot").setTextureName("supersolarpanel:wolfram_ingot"), "wolfram_ingot");
        GameRegistry.registerItem(SSPItem.wolfram_nugget = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("wolfram_nugget").setTextureName("supersolarpanel:wolfram_nugget"), "wolfram_nugget");
        GameRegistry.registerItem(SSPItem.chromium_ingot = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("chromium_ingot").setTextureName("supersolarpanel:chromium_ingot"), "chromium_ingot");
        GameRegistry.registerItem(SSPItem.chromium_nugget = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("chromium_nugget").setTextureName("supersolarpanel:chromium_nugget"), "chromium_nugget");
        GameRegistry.registerItem(SSPItem.chromium_plate = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("chromium_plate").setTextureName("supersolarpanel:chromium_plate"), "chromium_plate");

        GameRegistry.registerBlock(SSPItem.iridiumore = new BlockOre(Material.rock)
                .setBlockTextureName("supersolarpanel:iridiumore")
                .setStepSound(Block.soundTypeStone).setBlockName("iridiumore"), ItemBlockOre.class, "iridiumore");
        GameRegistry.registerBlock(SSPItem.wolframore = new BlockOre(Material.rock)
                .setBlockTextureName("supersolarpanel:wolframore")
                .setStepSound(Block.soundTypeStone).setBlockName("wolframore"), ItemBlockOre.class, "wolframore");
        GameRegistry.registerBlock(SSPItem.chromiumore = new BlockOre(Material.rock)
                .setBlockTextureName("supersolarpanel:chromiumore")
                .setStepSound(Block.soundTypeStone).setBlockName("chromiumore"), ItemBlockOre.class, "chromiumore");
        GameRegistry.registerBlock(SSPItem.platiumore = new BlockOre(Material.rock)
                .setBlockTextureName("supersolarpanel:platiumore")
                .setStepSound(Block.soundTypeStone).setBlockName("platiumore"), ItemBlockOre.class, "platiumore");
        GameRegistry.registerBlock(SSPItem.magnesiumore = new BlockOre(Material.rock)
                .setBlockTextureName("supersolarpanel:magnesiumore")
                .setStepSound(Block.soundTypeStone).setBlockName("magnesiumore"), ItemBlockOre.class, "magnesiumore");

        GameRegistry.registerBlock(SSPItem.mikhail_ore = new BlockOre(Material.rock)
                .setBlockTextureName("supersolarpanel:mikhail_ore").setStepSound(Block.soundTypeStone).setBlockName("mikhail_ore"), ItemBlockOre.class, "mikhail_ore");
        GameRegistry.registerBlock(SSPItem.spinelore = new BlockOre(Material.rock)
                .setBlockTextureName("supersolarpanel:spinelore")
                .setStepSound(Block.soundTypeStone).setBlockName("spinelore"), ItemBlockOre.class, "spinelore");

        GameRegistry.registerBlock(SSPItem.endgold_stone = new BlockOre(Material.rock)
                .setBlockTextureName("supersolarpanel:endgold_stone")
                .setStepSound(Block.soundTypeStone).setBlockName("endgold_stone"), "endgold_stone");
        //
        GameRegistry.registerBlock(SSPItem.magnetitore = new BlockOre(Material.rock)
                .setBlockTextureName("supersolarpanel:magnetitore")
                .setStepSound(Block.soundTypeStone).setBlockName("magnetitore"), ItemBlockOre.class, "magnetitore");
        GameRegistry.registerBlock(SSPItem.nicelore = new BlockOre(Material.rock)
                .setBlockTextureName("supersolarpanel:nicelore")
                .setStepSound(Block.soundTypeStone).setBlockName("nicelore"), ItemBlockOre.class, "nicelore");

        //
        GameRegistry.registerBlock(SSPItem.nethergoldrack = new BlockOre(Material.rock)
                .setBlockTextureName("supersolarpanel:nethergoldrack")
                .setStepSound(Block.soundTypeStone).setBlockName("nethergoldrack"), "nethergoldrack");

        GameRegistry.registerBlock(SSPItem.netherironrack = new BlockOre(Material.rock)
                .setBlockTextureName("supersolarpanel:netherironrack")
                .setStepSound(Block.soundTypeStone).setBlockName("netherironrack"), "netherironrack");

        GameRegistry.registerBlock(SSPItem.endiron_stone = new BlockOre(Material.rock)
                .setBlockTextureName("supersolarpanel:endiron_stone")
                .setStepSound(Block.soundTypeStone).setBlockName("endiron_stone"), "endiron_stone");

        GameRegistry.registerBlock(SSPItem.netherlapisrack = new BlockSSPLapis(Material.rock)
                .setBlockTextureName("supersolarpanel:netherlapisrack")
                .setStepSound(Block.soundTypeStone).setBlockName("netherlapisrack"), "netherlapisrack");


        GameRegistry.registerBlock(SSPItem.endlapis_stone = new BlockSSPLapis(Material.rock)
                .setBlockTextureName("supersolarpanel:endlapis_stone")
                .setStepSound(Block.soundTypeStone).setBlockName("endlapis_stone"), "endlapis_stone");

        GameRegistry.registerBlock(SSPItem.netheremeraldrack = new BlockSSPEmerald(Material.rock)
                .setBlockTextureName("supersolarpanel:netheremeraldrack")
                .setStepSound(Block.soundTypeStone).setBlockName("netheremeraldrack"), "netheremeraldrack");

        GameRegistry.registerBlock(SSPItem.nethercopperrack = new BlockOre(Material.rock)
                .setBlockTextureName("supersolarpanel:nethercopperrack")
                .setStepSound(Block.soundTypeStone).setBlockName("nethercopperrack"), "nethercopperrack");

        GameRegistry.registerBlock(SSPItem.enduran_stone = new BlockOre(Material.rock)
                .setBlockTextureName("supersolarpanel:enduran_stone")
                .setStepSound(Block.soundTypeStone).setBlockName("enduran_stone"), "enduran_stone");

        GameRegistry.registerBlock(SSPItem.enddiamond_stone = new BlockSSPDiamond(Material.rock)
                .setBlockTextureName("supersolarpanel:enddiamond_stone")
                .setStepSound(Block.soundTypeStone).setBlockName("enddiamond_stone"), "enddiamond_stone");

        GameRegistry.registerBlock(SSPItem.netheruranrack = new BlockOre(Material.rock)
                .setBlockTextureName("supersolarpanel:netheruranrack")
                .setStepSound(Block.soundTypeStone).setBlockName("netheruranrack"), "netheruranrack");

        GameRegistry.registerBlock(SSPItem.endtin_stone = new BlockOre(Material.rock)
                .setBlockTextureName("supersolarpanel:endtin_stone")
                .setStepSound(Block.soundTypeStone).setBlockName("endtin_stone"), "endtin_stone");

        GameRegistry.registerBlock(SSPItem.netherdiamondrack = new BlockSSPDiamond(Material.rock)
                .setBlockTextureName("supersolarpanel:netherdiamondrack")
                .setStepSound(Block.soundTypeStone).setBlockName("netherdiamondrack"), "netherdiamondrack");

        GameRegistry.registerBlock(SSPItem.endcoal_stone = new BlockSSPCoal(Material.rock)
                .setBlockTextureName("supersolarpanel:endcoal_stone")
                .setStepSound(Block.soundTypeStone).setBlockName("endcoal_stone"), "endcoal_stone");

        GameRegistry.registerBlock(SSPItem.nethertinrack = new BlockOre(Material.rock)
                .setBlockTextureName("supersolarpanel:nethertinrack")
                .setStepSound(Block.soundTypeStone).setBlockName("nethertinrack"), "nethertinrack");
        GameRegistry.registerBlock(SSPItem.nethercoalrack = new BlockSSPCoal(Material.rock)
                .setBlockTextureName("supersolarpanel:nethercoalrack")
                .setStepSound(Block.soundTypeStone).setBlockName("nethercoalrack"), "nethercoalrack");
        GameRegistry.registerBlock(SSPItem.endlead_stone = new BlockOre(Material.rock)
                .setBlockTextureName("supersolarpanel:endlead_stone")
                .setStepSound(Block.soundTypeStone).setBlockName("endlead_stone"), "endlead_stone");
        GameRegistry.registerBlock(SSPItem.endredstone_stone = new BlockSSPRedstone(Material.rock)
                .setBlockTextureName("supersolarpanel:endredstone_stone")
                .setStepSound(Block.soundTypeStone).setBlockName("endredstone_stone"), "endredstone_stone");
        GameRegistry.registerBlock(SSPItem.netherleadrack = new BlockOre(Material.rock)
                .setBlockTextureName("supersolarpanel:netherleadrack")
                .setStepSound(Block.soundTypeStone).setBlockName("netherleadrack"), "netherleadrack");
        GameRegistry.registerBlock(SSPItem.netherredstonerack = new BlockSSPRedstone(Material.rock)
                .setBlockTextureName("supersolarpanel:netherredstonerack")
                .setStepSound(Block.soundTypeStone).setBlockName("netherredstonerack"), "netherredstonerack");
        GameRegistry.registerBlock(SSPItem.endemerald_stone = new BlockSSPEmerald(Material.rock)
                .setBlockTextureName("supersolarpanel:endemerald_stone")
                .setStepSound(Block.soundTypeStone).setBlockName("endemerald_stone"), "endemerald_stone");
        GameRegistry.registerBlock(SSPItem.endcopper_stone = new BlockOre(Material.rock)
                .setBlockTextureName("supersolarpanel:endcopper_stone")
                .setStepSound(Block.soundTypeStone).setBlockName("endcopper_stone"), "endcopper_stone");
        //

        //
        GameRegistry.registerItem(SSPItem.QuantumItems = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("QuantumItems").setTextureName("supersolarpanel:QuantumItems"), "QuantumItems");
        GameRegistry.registerItem(SSPItem.QuantumItems2 = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("QuantumItems2").setTextureName("supersolarpanel:QuantumItems2"), "QuantumItems2");
        GameRegistry.registerItem(SSPItem.QuantumItems3 = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("QuantumItems3").setTextureName("supersolarpanel:QuantumItems3"), "QuantumItems3");
        GameRegistry.registerItem(SSPItem.QuantumItems4 = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("QuantumItems4").setTextureName("supersolarpanel:QuantumItems4"), "QuantumItems4");
        GameRegistry.registerItem(SSPItem.QuantumItems5 = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("QuantumItems5").setTextureName("supersolarpanel:QuantumItems5"), "QuantumItems5");
        GameRegistry.registerItem(SSPItem.nanoBox = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("nanobox").setTextureName("supersolarpanel:nanobox"), "nanobox");
        GameRegistry.registerItem(SSPItem.photoniccore = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("photoniccore").setTextureName("supersolarpanel:photoniccore"), "photoniccore");
        GameRegistry.registerItem(SSPItem.admincore = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("admincore").setTextureName("supersolarpanel:admincore"), "admincore");
        GameRegistry.registerItem(SSPItem.wolfram_plate = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("wolfram_plate").setTextureName("supersolarpanel:wolfram_plate"), "wolfram_plate");
        GameRegistry.registerItem(SSPItem.QuantumItems6 = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("QuantumItems6").setTextureName("supersolarpanel:QuantumItems6"), "QuantumItems6");
        GameRegistry.registerItem(SSPItem.QuantumItems7 = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("QuantumItems7").setTextureName("supersolarpanel:QuantumItems7"), "QuantumItems7");
        GameRegistry.registerItem(SSPItem.neutronium = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("neutronium").setTextureName("supersolarpanel:neutronium"), "neutronium");
        GameRegistry.registerItem(SSPItem.neutroniumingot = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("neutroniumingot").setTextureName("supersolarpanel:neutroniumingot"), "neutroniumingot");
        GameRegistry.registerItem(SSPItem.neutroniumcore = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("neutroniumcore").setTextureName("supersolarpanel:neutroniumcore"), "neutroniumcore");
        GameRegistry.registerBlock(SSPItem.blocksintezator = new Sintezator(), ItemSintezator.class, "BlockSintezator");

        GameRegistry.registerItem(SSPItem.advancedSolarHelmet = new ItemSolarPanelHelmet(ItemArmor.ArmorMaterial.DIAMOND, IUCore.proxy.addArmor("advancedSolarHelmet"), 0, 1).setUnlocalizedName("advancedSolarHelmet"), "advanced_solar_helmet");
        GameRegistry.registerItem(SSPItem.hybridSolarHelmet = new ItemSolarPanelHelmet(ItemArmor.ArmorMaterial.DIAMOND, IUCore.proxy.addArmor("hybridSolarHelmet"), 0, 2).setUnlocalizedName("hybridSolarHelmet"), "hybrid_solar_helmet");
        GameRegistry.registerItem(SSPItem.ultimateSolarHelmet = new ItemSolarPanelHelmet(ItemArmor.ArmorMaterial.DIAMOND, IUCore.proxy.addArmor("ultimateSolarHelmet"), 0, 3).setUnlocalizedName("ultimateSolarHelmet"), "ultimate_solar_helmet");
        GameRegistry.registerItem(SSPItem.itemSSP = new ItemSSPCrafring(), "ssp_crafting_items");
        GameRegistry.registerItem(SSPItem.QuantumItems8 = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("QuantumItems8").setTextureName("supersolarpanel:QuantumItems8"), "QuantumItems8");
        GameRegistry.registerItem(SSPItem.QuantumItems9 = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("QuantumItems9").setTextureName("supersolarpanel:QuantumItems9"), "QuantumItems9");
        GameRegistry.registerItem(SSPItem.coal_chunk1 = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("coal_chunk").setTextureName("supersolarpanel:coal_chunk"), "coal_chunk");
        GameRegistry.registerItem(SSPItem.compresscarbon = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("compresscarbon").setTextureName("supersolarpanel:compresscarbon"), "compresscarbon");
        GameRegistry.registerItem(SSPItem.compresscarbonultra = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("compresscarbonultra").setTextureName("supersolarpanel:compresscarbonultra"), "compresscarbonultra");
        GameRegistry.registerItem(SSPItem.protoncore = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("protoncore").setTextureName("supersolarpanel:protoncore"), "protoncore");
        GameRegistry.registerItem(SSPItem.nightlinse = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("nightlinse").setTextureName("supersolarpanel:nightlinse"), "nightlinse");
        GameRegistry.registerItem(SSPItem.sunlinse = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("sunlinse").setTextureName("supersolarpanel:sunlinse"), "sunlinse");
        GameRegistry.registerItem(SSPItem.rainlinse = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("rainlinse").setTextureName("supersolarpanel:rainlinse"), "rainlinse");
        GameRegistry.registerItem(SSPItem.aerlinse = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("aerlinse").setTextureName("supersolarpanel:aerlinse"), "aerlinse");
        GameRegistry.registerItem(SSPItem.earthlinse = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("earthlinse").setTextureName("supersolarpanel:earthlinse"), "earthlinse");
        GameRegistry.registerItem(SSPItem.netherlinse = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("netherlinse").setTextureName("supersolarpanel:netherlinse"), "netherlinse");
        GameRegistry.registerItem(SSPItem.endlinse = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("endlinse").setTextureName("supersolarpanel:endlinse"), "endlinse");

        GameRegistry.registerItem(SSPItem.module7 = new com.Denfop.item.Modules.module7(), "module7");
        GameRegistry.registerItem(SSPItem.module9 = new module8(), "module8");
        GameRegistry.registerItem(SSPItem.photoniyglass1 = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("photoniyglass1").setTextureName("supersolarpanel:photoniyglass1"), "photoniyglass1");
        GameRegistry.registerItem(SSPItem.photoniyglass2 = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("photoniyglass2").setTextureName("supersolarpanel:photoniyglass2"), "photoniyglass2");
        GameRegistry.registerItem(SSPItem.photoniyglass3 = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("photoniyglass3").setTextureName("supersolarpanel:photoniyglass3"), "photoniyglass3");
        GameRegistry.registerItem(SSPItem.photoniyglass4 = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("photoniyglass4").setTextureName("supersolarpanel:photoniyglass4"), "photoniyglass4");
        GameRegistry.registerItem(SSPItem.photoniyglass5 = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("photoniyglass5").setTextureName("supersolarpanel:photoniyglass5"), "photoniyglass5");
        GameRegistry.registerItem(SSPItem.photoniyglass6 = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("photoniyglass6").setTextureName("supersolarpanel:photoniyglass6"), "photoniyglass6");
        GameRegistry.registerItem(SSPItem.photoniyglass7 = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("photoniyglass7").setTextureName("supersolarpanel:photoniyglass7"), "photoniyglass7");
        GameRegistry.registerItem(SSPItem.photoniyglass8 = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("photoniyglass8").setTextureName("supersolarpanel:photoniyglass8"), "photoniyglass8");
        GameRegistry.registerItem(SSPItem.photoniyglass9 = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("photoniyglass9").setTextureName("supersolarpanel:photoniyglass9"), "photoniyglass9");
        GameRegistry.registerItem(SSPItem.photoniyglass10 = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("photoniyglass10").setTextureName("supersolarpanel:photoniyglass10"), "photoniyglass10");

        GameRegistry.registerBlock(SSPItem.machines_base = new BlockMachines(), ItemMachines.class, "machines_base");
        GameRegistry.registerBlock(SSPItem.machines_base1 = new BlockMachines1(), ItemMachines2.class, "machines_base1");
        if (!Loader.isModLoaded("wirelessvajra"))
            GameRegistry.registerBlock(SSPItem.expgen, ItemBlockEG.class, "ExpGen1");
        GameRegistry.registerItem(SSPItem.module8, "WirelessModule1");

        GameRegistry.registerItem(SSPItem.ultDDrill, "ultDDrill");
        GameRegistry.registerItem(SSPItem.module1 = new com.Denfop.item.Modules.module1().setMaxStackSize(64).setUnlocalizedName("module1").setTextureName("supersolarpanel:module1"), "module1");
        GameRegistry.registerItem(SSPItem.module2 = new com.Denfop.item.Modules.module2().setMaxStackSize(64).setUnlocalizedName("module2").setTextureName("supersolarpanel:module2"), "module2");
        GameRegistry.registerItem(SSPItem.module3 = new com.Denfop.item.Modules.module3().setMaxStackSize(64).setUnlocalizedName("module3").setTextureName("supersolarpanel:module3"), "module3");
        GameRegistry.registerItem(SSPItem.module4 = new com.Denfop.item.Modules.module4().setMaxStackSize(64).setUnlocalizedName("module4").setTextureName("supersolarpanel:module4"), "module4");
        GameRegistry.registerItem(SSPItem.module5 = new com.Denfop.item.Modules.module5().setMaxStackSize(64).setUnlocalizedName("module5").setTextureName("supersolarpanel:module5"), "module5");

        GameRegistry.registerItem(SSPItem.module6 = new com.Denfop.item.Modules.module6(), "module6");
        GameRegistry.registerBlock(SSPItem.cableblock = new BlockCable(), ItemBlockIC2.class, "blockCable");
        GameRegistry.registerItem(SSPItem.cable, "cable");

        GameRegistry.registerItem(SSPItem.module, "upgrademodule");
        GameRegistry.registerItem(SSPItem.cell_all = new ItemCell(), "cell");
//
        GameRegistry.registerItem(SSPItem.matter = new matter().setMaxStackSize(64).setUnlocalizedName("matter").setTextureName("supersolarpanel:matter"), "matter");
        //
        GameRegistry.registerItem(SSPItem.chromiumcrushedore = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("chromiumcrushedore").setTextureName("supersolarpanel:chromiumcrushedore"), "chromiumcrushedore");
        GameRegistry.registerItem(SSPItem.electriumdust = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("electriumdust").setTextureName("supersolarpanel:electriumdust"), "electriumdust");
        GameRegistry.registerItem(SSPItem.electriumingot = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("electriumingot").setTextureName("supersolarpanel:electriumingot"), "electriumingot");
        GameRegistry.registerItem(SSPItem.electriumplate = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("electriumplate").setTextureName("supersolarpanel:electriumplate"), "electriumplate");
        GameRegistry.registerItem(SSPItem.blast = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("blast").setTextureName("supersolarpanel:blast"), "blast");
        GameRegistry.registerItem(SSPItem.invardust = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("invardust").setTextureName("supersolarpanel:invardust"), "invardust");
        GameRegistry.registerItem(SSPItem.invaringot = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("invaringot").setTextureName("supersolarpanel:invaringot"), "invaringot");
        GameRegistry.registerItem(SSPItem.invarplate = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("invarplate").setTextureName("supersolarpanel:invarplate"), "invarplate");
        GameRegistry.registerItem(SSPItem.nickel = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("nickel").setTextureName("supersolarpanel:nickel"), "nickel");
        GameRegistry.registerItem(SSPItem.nickelplate = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("nickelplate").setTextureName("supersolarpanel:nickelplate"), "nickelplate");
        GameRegistry.registerItem(SSPItem.michalovcrushedore = new SSPItemBase().setMaxStackSize(64).setUnlocalizedName("michalovcrushedore").setTextureName("supersolarpanel:michalovcrushedore"), "michalovcrushedore");


    }

    public static void registertiles() {
        GameRegistry.registerTileEntity(TileBitGen2.class, "Mass Fabricator1");

        GameRegistry.registerTileEntity(TileEntityMolecularTransformer.class, "MolecularTransformer");
        GameRegistry.registerTileEntity(TileEntityQuantumQuarry.class, "QuantumQuarry");
        GameRegistry.registerTileEntity(TileEntityGenerationStone.class, "Generation Stone");
        GameRegistry.registerTileEntity(TileEntityAdvancedMatter.class, "Mass Fabricator Advanced");
        GameRegistry.registerTileEntity(TileEntityImprovedMatter.class, "Mass Fabricator Improved");
        GameRegistry.registerTileEntity(TileEntityUltimateMatter.class, "Mass Fabricator Ultimate");
        GameRegistry.registerTileEntity(TileEntityAlloySmelter.class, "AlloySmelter");
        GameRegistry.registerTileEntity(TileEntityGenerationMicrochip.class, "GenerationMicrochip");
        GameRegistry.registerTileEntity(TileRadioation.class, "RadiationOre");
        GameRegistry.registerTileEntity(TileEntityAdminSolarPanel.class, "TileEntityAdminSolarPanel");
        GameRegistry.registerTileEntity(TileEntitySintezator.class, "TileEntitySintezator");
        GameRegistry.registerTileEntity(TileXPGenPublic.class, "GeneratorXP");
        GameRegistry.registerTileEntity(TileEntityElectricMFE.class, "MFES");
        GameRegistry.registerTileEntity(TileEntityElectricMFSU.class, "MFSUS");
        GameRegistry.registerTileEntity(TileEntityElectricBatBox.class, "MFSUS1");
        EnumMultiMachine.registerTile();
        GameRegistry.registerTileEntity(TileAdminSolarPanel.class, "Admin Solar Panel");
        GameRegistry.registerTileEntity(TilePhotonicSolarPanel.class, "Photonic Solar Panel");
        GameRegistry.registerTileEntity(TileSingularSolarPanel.class, "Singular Solar Panel");
        GameRegistry.registerTileEntity(TileSpectralSolarPanel.class, "Spectral Solar Panel");
        GameRegistry.registerTileEntity(TileNeutronSolarPanel.class, "Neutron Solar Panel");
        GameRegistry.registerTileEntity(TileProtonSolarPanel.class, "Proton Solar Panel");
        GameRegistry.registerTileEntity(TileEntityChargepadMFES.class, "CMFE");
        GameRegistry.registerTileEntity(TileEntityChargepadMFSUS.class, "CMFSU");
        GameRegistry.registerTileEntity(TileEntityAdvancedSolarPanel.class, "Advanced Solar Panel1");
        GameRegistry.registerTileEntity(TileEntityHybridSolarPanel.class, "Hybrid Solar Panel1");
        GameRegistry.registerTileEntity(TileEntityUltimateSolarPanel.class, "Ultimate Hybrid Solar Panel1");
        GameRegistry.registerTileEntity(TileEntityQuantumSolarPanel.class, "Quantum Solar Panel1");
        GameRegistry.registerTileEntity(TileEntityMolecularTransformer.class, "Molecular Transformer1");
        GameRegistry.registerTileEntity(TileEntityCable.class, "Cable1");

    }
}
