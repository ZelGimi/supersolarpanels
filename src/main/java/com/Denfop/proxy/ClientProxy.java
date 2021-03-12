package com.Denfop.proxy;


import com.Denfop.Config;
import com.Denfop.IUCore;
import com.Denfop.Recipes.*;
import com.Denfop.SSPItem;
import com.Denfop.block.expgen.TextureHooks;
import com.Denfop.container.*;
import com.Denfop.events.DE.SSPDEEventHandler;
import com.Denfop.events.DE_MF.SSPDEMFEventHandler;
import com.Denfop.events.DE_MF_EP.SSPMFDEEventHandler;
import com.Denfop.events.EP.SSPEPEventHandler;
import com.Denfop.events.EP_DE.SSPDEEPEventHandler;
import com.Denfop.events.EventDarkQuantumSuitEffect;
import com.Denfop.events.MF.SSPMFEventHandler;
import com.Denfop.events.MF_EP.SSPMPMFEventHandler;
import com.Denfop.events.SSPEventHandler;
import com.Denfop.gui.*;
import com.Denfop.handler.EntityStreak;
import com.Denfop.integration.Avaritia.AvaritiaIntegration;
import com.Denfop.integration.Botania.BotaniaIntegration;
import com.Denfop.integration.DE.DraconicIntegration;
import com.Denfop.integration.DE.RenderArmor;
import com.Denfop.integration.DE.RenderBowModel;
import com.Denfop.integration.DE.RenderTool;
import com.Denfop.render.Cable.RenderBlock;
import com.Denfop.render.Cable.RenderBlockCable;
import com.Denfop.render.Cable.RenderBlockWall;
import com.Denfop.render.EntityRendererStreak;
import com.Denfop.render.Sintezaor.TileEntitySintezatorItemRender;
import com.Denfop.render.Sintezaor.TileEntitySintezatorRender;
import com.Denfop.render.tile.TileEntityPanelItemRender;
import com.Denfop.render.tile.TileEntityPanelRender;
import com.Denfop.tiles.ExpGen.TileExpGen2;
import com.Denfop.tiles.Mechanism.TileEntityAlloySmelter;
import com.Denfop.tiles.Mechanism.TileEntityGenerationMicrochip;
import com.Denfop.tiles.Mechanism.TileEntityGenerationStone;
import com.Denfop.tiles.Mechanism.TileEntityQuantumQuarry;
import com.Denfop.tiles.NeutroniumGenerator.TileBitGen2;
import com.Denfop.tiles.Sintezator.TileEntitySintezator;
import com.Denfop.tiles.base.*;
import com.Denfop.tiles.wiring.Storage.TileEntityElectricMFE;
import com.Denfop.utils.Check;
import com.brandon3055.draconicevolution.client.render.IRenderTweak;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import ic2.core.Ic2Items;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ClientProxy extends CommonProxy implements IGuiHandler {

    public static int adminpanel;
    public static Logger logger = LogManager.getLogger("supersolarpanel");
    public static int[][] sideAndFacingToSpriteOffset;
    Map<String, RenderBlock> renders;

    public boolean isClient() {

        return true;
    }

    public void registerTabs(CreativeTabs tab, final ItemStack icon) {

    }

    public RenderBlock getRender(String name) {
        return this.renders.get(name);
    }

    public void check() {
        Check.check();
    }

    @Override
    public void load() {

        try {
            sideAndFacingToSpriteOffset = (int[][]) Class.forName("ic2.core.block.BlockMultiID").getField("sideAndFacingToSpriteOffset").get(null);
        } catch (Exception e) {
            sideAndFacingToSpriteOffset = new int[][]{
                    {
                            3, 2, 0, 0, 0, 0
                    }, {
                    2, 3, 1, 1, 1, 1
            }, {
                    1, 1, 3, 2, 5, 4
            }, {
                    0, 0, 2, 3, 4, 5
            }, {
                    4, 5, 4, 5, 3, 2
            }, {
                    5, 4, 5, 4, 2, 3
            }
            };
        }
    }

    private void addBlockRenderer(String name, RenderBlock renderer) {
        RenderingRegistry.registerBlockHandler(renderer);
        this.renders.put(name, renderer);
    }

    public void initCore() {

        TileEntityAlloySmelter.init();
        TileEntityMolecularTransformer.init();
        TileEntityGenerationMicrochip.init();
        TileEntityGenerationStone.init();

    }

    public void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityStreak.class, new EntityRendererStreak());
        //

        //

        this.renders = new HashMap<String, RenderBlock>();
        addBlockRenderer("cable", new RenderBlockCable());
        addBlockRenderer("wall", new RenderBlockWall());
        if (Config.DraconicLoaded) {
            MinecraftForgeClient.registerItemRenderer(DraconicIntegration.ChaosBow, new RenderBowModel(true));
            MinecraftForgeClient.registerItemRenderer(DraconicIntegration.ChaosSword, new RenderTool("models/tools/DraconicSword.obj", "textures/models/tools/DraconicSword.png", (IRenderTweak) DraconicIntegration.ChaosSword));
            MinecraftForgeClient.registerItemRenderer(DraconicIntegration.ChaosPickaxe, new RenderTool("models/tools/DraconicPickaxe.obj", "textures/models/tools/DraconicPickaxe.png", (IRenderTweak) DraconicIntegration.ChaosPickaxe));
            MinecraftForgeClient.registerItemRenderer(DraconicIntegration.ChaosAxe, new RenderTool("models/tools/DraconicLumberAxe.obj", "textures/models/tools/DraconicLumberAxe.png", (IRenderTweak) DraconicIntegration.ChaosAxe));
            MinecraftForgeClient.registerItemRenderer(DraconicIntegration.ChaosShovel, new RenderTool("models/tools/DraconicShovel.obj", "textures/models/tools/DraconicShovel.png", (IRenderTweak) DraconicIntegration.ChaosShovel));
            MinecraftForgeClient.registerItemRenderer(DraconicIntegration.ChaosDestructionStaff, new RenderTool("models/tools/DraconicStaffOfPower.obj", "textures/models/tools/DraconicStaffOfPower.png", (IRenderTweak) DraconicIntegration.ChaosDestructionStaff));
            MinecraftForgeClient.registerItemRenderer(DraconicIntegration.ChaosHelm, new RenderArmor(DraconicIntegration.ChaosHelm));
            MinecraftForgeClient.registerItemRenderer(DraconicIntegration.ChaosChest, new RenderArmor(DraconicIntegration.ChaosChest));
            MinecraftForgeClient.registerItemRenderer(DraconicIntegration.ChaosLeggs, new RenderArmor(DraconicIntegration.ChaosLeggs));
            MinecraftForgeClient.registerItemRenderer(DraconicIntegration.ChaosBoots, new RenderArmor(DraconicIntegration.ChaosBoots));
        }
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAdminSolarPanel.class, new TileEntityPanelRender());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(SSPItem.blockadmin),
                new TileEntityPanelItemRender());
        //
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySintezator.class, new TileEntitySintezatorRender());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(SSPItem.blocksintezator),
                new TileEntitySintezatorItemRender());
        MinecraftForge.EVENT_BUS.register(new TextureHooks());
    }

    public void registerRecipe() {

        if (Config.BotaniaLoaded && Config.Botania)
            BotaniaIntegration.recipe();
        BasicRecipe.recipe();
        if (Config.DraconicLoaded && Config.Draconic)
            DraconicIntegration.Recipes();
        if (Config.AvaritiaLoaded && Config.Avaritia)
            AvaritiaIntegration.recipe();

        AlloySmelterRecipe.recipe();
        CompressorRecipe.recipe();
        CannerRecipe.recipe();
        FurnaceRecipes.recipe();
        CentrifugeRecipe.init();
        MaceratorRecipe.recipe();

    }

    public void registerEvents() {
        MinecraftForge.EVENT_BUS.register(new EventDarkQuantumSuitEffect());
        if (Config.Streak == true) {
            FMLCommonHandler.instance().bus().register(new EventDarkQuantumSuitEffect());
        }
        if (Config.newsystem)
            IUCore.initENet();

        if (Config.DraconicLoaded && Config.EnchantingPlus && Config.MineFactory) {
            MinecraftForge.EVENT_BUS.register(new SSPMFDEEventHandler());

        } else if (Config.DraconicLoaded && Config.EnchantingPlus) {
            MinecraftForge.EVENT_BUS.register(new SSPDEEPEventHandler());
        } else if (Config.DraconicLoaded && Config.MineFactory) {
            MinecraftForge.EVENT_BUS.register(new SSPDEMFEventHandler());
        } else if (Config.EnchantingPlus && Config.MineFactory) {
            MinecraftForge.EVENT_BUS.register(new SSPMPMFEventHandler());
        } else {
            if (Config.DraconicLoaded) {
                MinecraftForge.EVENT_BUS.register(new SSPDEEventHandler());
            }

            if (Config.EnchantingPlus) {
                MinecraftForge.EVENT_BUS.register(new SSPEPEventHandler());
            }
            if (Config.MineFactory) {
                MinecraftForge.EVENT_BUS.register(new SSPMFEventHandler());
            }
        }
        MinecraftForge.EVENT_BUS.register(new SSPEventHandler());
    }

    @Override
    public Object getClientGuiElement(final int ID, final EntityPlayer player, final World world, final int X, final int Y, final int Z) {
        final TileEntity te = world.getTileEntity(X, Y, Z);

        if (te == null) {
            return null;
        }
        if (te instanceof TileExpGen2) {

            return new GuiExpGen(player.inventory, (TileExpGen2) te, X, Y, Z, world);

        }

        if (te instanceof TileEntitySolarPanel) {
            return new GuiAdvSolarPanel(player.inventory, (TileEntitySolarPanel) te);
        }
        if (te instanceof TileSintezator) {
            return new GUISintezator(player.inventory, (TileSintezator) te);
        }


        if (te instanceof TileEntityMolecularTransformer) {
            return new GuiMolecularTransformer(new ContainerBaseMolecular(player, (TileEntityMolecularTransformer) te));
        }
        if (player.getHeldItem() != Ic2Items.electricWrench) {
            if (te instanceof TileEntityMultiMachine) {
                return ((TileEntityMultiMachine) te).getGui(player, false);
            }

            if (player.getHeldItem() != Ic2Items.electricWrench) {
                if (te instanceof TileEntityMultiMachine1) {
                    return ((TileEntityMultiMachine1) te).getGui(player, false);
                }
            }
            if (player.getHeldItem() != Ic2Items.electricWrench) {
                if (te instanceof TileEntityMultiMatter) {
                    return ((TileEntityMultiMatter) te).getGui(player, false);
                }
            }
            if (te instanceof TileEntityAlloySmelter) {
                return new GuiAlloySmelter(new ContainerStandardMachine(player, (TileEntityAlloySmelter) te));
            }
            if (te instanceof TileEntityElectricMFE) {

                return new GuiElectricBlock(new ContainerElectricBlock(player, (TileEntityElectricMFE) te));
            }

            if (te instanceof TileEntityElectricBlock) {

                return new GuiElectricBlock(new ContainerElectricBlock(player, (TileEntityElectricBlock) te));
            }
            if (te instanceof TileBitGen2) {

                return new GuiMatter(new ContainerNeutrniumGenerator(player, (TileBitGen2) te));
            }
            if (te instanceof TileEntityGenerationMicrochip) {
                return new GuiGenerationMicrochip(new ContainerBaseGenerationChipMachine(player, (TileEntityGenerationMicrochip) te));
            }
            if (te instanceof TileEntityChargepadBlock) {

                return new GuiChargepadBlock(new ContainerChargepadBlock(player, (TileEntityChargepadBlock) te));
            }
            if (te instanceof TileEntityGenerationStone)
                return new GuiGenStone(new ContainerGenStone(player, (TileEntityGenerationStone) te));
            if (te instanceof TileEntityQuantumQuarry)
                return new GuiQuantumQuarry(new ContainerQuantumQuarry(player, (TileEntityQuantumQuarry) te));
        }
        return null;
    }


    @Override
    public int addArmor(final String armorName) {
        return RenderingRegistry.addNewArmourRendererPrefix(armorName);
    }

    public int getRenderId(String name) {
        return this.renders.get(name).getRenderId();
    }
}
