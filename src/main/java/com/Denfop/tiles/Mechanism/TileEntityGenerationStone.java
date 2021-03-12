package com.Denfop.tiles.Mechanism;

import com.Denfop.InvSlot.InvSlotProcessableStone;
import com.Denfop.RecipeManager.GenStoneRecipeManager;
import com.Denfop.api.Recipes;
import com.Denfop.container.ContainerGenStone;
import com.Denfop.gui.GuiGenStone;
import com.Denfop.tiles.base.TileEntityBaseGenStone;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.core.block.invslot.InvSlotProcessable;
import ic2.core.upgrade.UpgradableProperty;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.*;

public class TileEntityGenerationStone extends TileEntityBaseGenStone {

    public static List<Map.Entry<ItemStack, ItemStack>> recipes = new Vector<Map.Entry<ItemStack, ItemStack>>();

    public TileEntityGenerationStone() {
        super(1, 100, 1);
        this.inputSlotA = new InvSlotProcessableStone(this, "inputA", 0, 1, Recipes.GenStone);
        this.inputSlotB = new InvSlotProcessableStone(this, "inputB", 1, 1, Recipes.GenStone);
    }

    public static void init() {
        Recipes.GenStone = new GenStoneRecipeManager();
        addGen(new RecipeInputItemStack(new ItemStack(Items.lava_bucket), 1), new RecipeInputItemStack(new ItemStack(Items.water_bucket), 1), new ItemStack(Blocks.cobblestone, 4));
    }

    public static void addGen(IRecipeInput container, IRecipeInput fill, ItemStack output) {
        Recipes.GenStone.addRecipe(container, fill, output);
        Recipes.GenStone.addRecipe(fill, container, output);
    }

    public String getInventoryName() {
        return null;

        //  return StatCollector.translateToLocal("ssp.genstone");
    }

    @SideOnly(Side.CLIENT)
    public GuiScreen getGui(EntityPlayer entityPlayer, boolean isAdmin) {
        return new GuiGenStone(new ContainerGenStone(entityPlayer, this));
    }

    public String getStartSoundFile() {
        return "Machines/MaceratorOp.ogg";
    }

    public String getInterruptSoundFile() {
        return "Machines/InterruptOne.ogg";
    }

    public float getWrenchDropRate() {
        return 0.85F;
    }

    public Set<UpgradableProperty> getUpgradableProperties() {
        return EnumSet.of(UpgradableProperty.Processing, UpgradableProperty.Transformer, UpgradableProperty.EnergyStorage, UpgradableProperty.ItemConsuming, UpgradableProperty.ItemProducing);
    }
}
