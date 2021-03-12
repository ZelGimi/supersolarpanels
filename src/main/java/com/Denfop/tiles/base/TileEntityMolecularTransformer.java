package com.Denfop.tiles.base;

import com.Denfop.Config;
import com.Denfop.SSPItem;
import com.Denfop.api.Recipes;
import com.Denfop.container.ContainerBaseMolecular;
import com.Denfop.gui.GuiMolecularTransformer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.network.INetworkClientTileEntityEventListener;
import ic2.api.recipe.IMachineRecipeManager;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.core.BasicMachineRecipeManager;
import ic2.core.Ic2Items;
import ic2.core.block.invslot.InvSlotProcessable;
import ic2.core.block.invslot.InvSlotProcessableGeneric;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;
import java.util.Map;
import java.util.Vector;

public class TileEntityMolecularTransformer extends TileEntityBaseMolecular implements INetworkClientTileEntityEventListener {
    public static List<Map.Entry<ItemStack, ItemStack>> recipes = new Vector<Map.Entry<ItemStack, ItemStack>>();
    public byte redstoneMode;
    private boolean isActive;

    public TileEntityMolecularTransformer() {
        super(1, 500, 1);
        this.inputSlot = new InvSlotProcessableGeneric(this, "input", 0, 1, Recipes.molecular);
        this.redstoneMode = 0;
        this.setTier(11);
    }

    public static void init() {
        Recipes.molecular = new BasicMachineRecipeManager();


        NBTTagCompound nbt = new NBTTagCompound();
        NBTTagCompound nbt1 = new NBTTagCompound();
        NBTTagCompound nbt2 = new NBTTagCompound();
        NBTTagCompound nbt3 = new NBTTagCompound();
        NBTTagCompound nbt4 = new NBTTagCompound();
        NBTTagCompound nbt5 = new NBTTagCompound();
        NBTTagCompound nbt6 = new NBTTagCompound();
        NBTTagCompound nbt7 = new NBTTagCompound();
        NBTTagCompound nbt8 = new NBTTagCompound();
        NBTTagCompound nbt9 = new NBTTagCompound();
        NBTTagCompound nbt10 = new NBTTagCompound();
        NBTTagCompound nbt11 = new NBTTagCompound();
        NBTTagCompound nbt12 = new NBTTagCompound();
        NBTTagCompound nbt13 = new NBTTagCompound();
        NBTTagCompound nbt14 = new NBTTagCompound();
        NBTTagCompound nbt15 = new NBTTagCompound();
        NBTTagCompound nbt16 = new NBTTagCompound();
        NBTTagCompound nbt17 = new NBTTagCompound();
        NBTTagCompound nbt18 = new NBTTagCompound();
        NBTTagCompound nbt19 = new NBTTagCompound();
        NBTTagCompound nbt20 = new NBTTagCompound();
        NBTTagCompound nbt21 = new NBTTagCompound();
        NBTTagCompound nbt22 = new NBTTagCompound();
        NBTTagCompound nbt23 = new NBTTagCompound();
        NBTTagCompound nbt24 = new NBTTagCompound();
        NBTTagCompound nbt25 = new NBTTagCompound();
        NBTTagCompound nbt26 = new NBTTagCompound();

        NBTTagCompound nbt27 = new NBTTagCompound();
        NBTTagCompound nbt28 = new NBTTagCompound();
        NBTTagCompound nbt29 = new NBTTagCompound();
        NBTTagCompound nbt30 = new NBTTagCompound();
        NBTTagCompound nbt31 = new NBTTagCompound();
        NBTTagCompound nbt32 = new NBTTagCompound();
        NBTTagCompound nbt33 = new NBTTagCompound();
        NBTTagCompound nbt34 = new NBTTagCompound();
        NBTTagCompound nbt35 = new NBTTagCompound();
        nbt.setInteger("energy", Config.molecular);
        nbt1.setInteger("energy", Config.molecular1);
        nbt2.setInteger("energy", Config.molecular2);
        nbt3.setInteger("energy", Config.molecular3);
        nbt4.setInteger("energy", Config.molecular4);
        nbt5.setInteger("energy", Config.molecular5);
        nbt6.setInteger("energy", Config.molecular6);
        nbt7.setInteger("energy", Config.molecular7);
        nbt8.setInteger("energy", Config.molecular8);
        nbt9.setInteger("energy", Config.molecular9);
        nbt10.setInteger("energy", Config.molecular10);
        nbt11.setInteger("energy", Config.molecular11);
        nbt12.setInteger("energy", Config.molecular12);
        nbt13.setInteger("energy", Config.molecular13);
        nbt14.setInteger("energy", Config.molecular14);
        nbt15.setInteger("energy", Config.molecular15);
        nbt16.setInteger("energy", Config.molecular16);
        nbt17.setInteger("energy", Config.molecular17);
        nbt18.setInteger("energy", Config.molecular18);
        nbt19.setInteger("energy", Config.molecular19);
        nbt20.setInteger("energy", Config.molecular20);
        nbt21.setInteger("energy", Config.molecular21);
        nbt22.setInteger("energy", Config.molecular22);
        nbt23.setInteger("energy", Config.molecular23);
        nbt24.setInteger("energy", Config.molecular24);
        nbt25.setInteger("energy", Config.molecular25);
        nbt26.setInteger("energy", Config.molecular26);
        //
        nbt27.setInteger("energy", Config.molecular27);
        nbt28.setInteger("energy", Config.molecular28);
        nbt29.setInteger("energy", Config.molecular29);
        nbt30.setInteger("energy", Config.molecular30);
        nbt31.setInteger("energy", Config.molecular31);
        nbt32.setInteger("energy", Config.molecular32);
        nbt33.setInteger("energy", Config.molecular33);
        nbt34.setInteger("energy", Config.molecular34);
        nbt35.setInteger("energy", Config.molecular35);
        addrecipe(new ItemStack(Items.skull, 1, 0), nbt, new ItemStack(Items.skull, 1, 1));
        addrecipe(new ItemStack(Items.skull, 1, 1), nbt1, new ItemStack(Items.nether_star, 1));
        addrecipe(new ItemStack(Items.iron_ingot, 1, 0), nbt2, Ic2Items.iridiumOre);
        addrecipe(Ic2Items.Plutonium, nbt3, new ItemStack(SSPItem.proton, 1));
        addrecipe(OreDictionary.getOres("ingotSpinel").get(0), nbt4, OreDictionary.getOres("ingotIridium").get(0));
        addrecipe(OreDictionary.getOres("Photon").get(0), nbt5, OreDictionary.getOres("ingotPhoton").get(0));

        addrecipe(new ItemStack(Blocks.netherrack), nbt6, new ItemStack(Items.gunpowder, 2));
        addrecipe(new ItemStack(Blocks.sand), nbt7, new ItemStack(Blocks.gravel, 1));
        addrecipe(OreDictionary.getOres("dustCoal").get(0), nbt8, OreDictionary.getOres("gemDiamond").get(0));
        addrecipe(OreDictionary.getOres("ingotCopper").get(0), nbt9, OreDictionary.getOres("ingotNickel").get(0));
        addrecipe(OreDictionary.getOres("ingotLead").get(0), nbt10, OreDictionary.getOres("ingotGold").get(0));
        if (OreDictionary.getOres("ingotSilver").size() >= 1)
            addrecipe(OreDictionary.getOres("ingotTin").get(0), nbt11, OreDictionary.getOres("ingotSilver").get(0));
        if (OreDictionary.getOres("ingotSilver").size() >= 1)
            addrecipe(OreDictionary.getOres("ingotSilver").get(0), nbt12, OreDictionary.getOres("ingotTungsten").get(0));

        addrecipe(OreDictionary.getOres("ingotTungsten").get(0), nbt13, OreDictionary.getOres("ingotSpinel").get(0));
        addrecipe(OreDictionary.getOres("ingotChromium").get(0), nbt14, OreDictionary.getOres("ingotMikhalov").get(0));
        addrecipe(OreDictionary.getOres("ingotPlatinum").get(0), nbt15, OreDictionary.getOres("ingotChromium").get(0));
        addrecipe(OreDictionary.getOres("ingotGold").get(0), nbt16, OreDictionary.getOres("ingotPlatinum").get(0));
        addrecipe(OreDictionary.getOres("ingotIridium").get(0), nbt17, new ItemStack(SSPItem.advanced_core, 1));

        addrecipe(new ItemStack(SSPItem.advanced_core, 4), nbt18, new ItemStack(SSPItem.hybrid_core, 1));
        addrecipe(new ItemStack(SSPItem.hybrid_core, 4), nbt19, new ItemStack(SSPItem.ultimate_core, 1));
        addrecipe(new ItemStack(SSPItem.ultimate_core, 4), nbt20, new ItemStack(SSPItem.itemSSP, 1, 10));
        addrecipe(new ItemStack(SSPItem.itemSSP, 4, 10), nbt21, new ItemStack(SSPItem.spectralcore, 1));
        addrecipe(new ItemStack(SSPItem.spectralcore, 4), nbt22, new ItemStack(SSPItem.protoncore, 1));
        addrecipe(new ItemStack(SSPItem.protoncore, 4), nbt23, new ItemStack(SSPItem.singularcore, 1));
        addrecipe(new ItemStack(SSPItem.singularcore, 4), nbt24, new ItemStack(SSPItem.admincore, 1));
        addrecipe(new ItemStack(SSPItem.admincore, 4), nbt25, new ItemStack(SSPItem.photoniccore, 1));
        addrecipe(new ItemStack(SSPItem.photoniccore, 4), nbt26, new ItemStack(SSPItem.blocksintezator, 1));
//
        addrecipe(new ItemStack(SSPItem.matter, 1, 1), nbt27, new ItemStack(SSPItem.sunlinse, 1));
        addrecipe(new ItemStack(SSPItem.matter, 1, 2), nbt28, new ItemStack(SSPItem.rainlinse, 1));
        addrecipe(new ItemStack(SSPItem.matter, 1, 3), nbt29, new ItemStack(SSPItem.netherlinse, 1));
        addrecipe(new ItemStack(SSPItem.matter, 1, 4), nbt30, new ItemStack(SSPItem.nightlinse, 1));
        addrecipe(new ItemStack(SSPItem.matter, 1, 5), nbt31, new ItemStack(SSPItem.earthlinse, 1));
        addrecipe(new ItemStack(SSPItem.matter, 1, 6), nbt32, new ItemStack(SSPItem.endlinse, 1));
        addrecipe(new ItemStack(SSPItem.matter, 1, 7), nbt33, new ItemStack(SSPItem.aerlinse, 1));
        addrecipe(Ic2Items.iridiumOre, nbt34, OreDictionary.getOres("Photon").get(0));
        addrecipe(OreDictionary.getOres("ingotMikhalov").get(0), nbt35, OreDictionary.getOres("ingotMagnesium").get(0));
    }

    public static void addrecipe(ItemStack stack, NBTTagCompound nbt, ItemStack stack1) {

        Recipes.molecular.addRecipe(new RecipeInputItemStack(stack), nbt, stack1);
    }

    public boolean isActive() {
        return this.isActive;
    }

    public String getInventoryName() {
        return "Molecular Transformer";
    }

    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        this.maxEnergy = nbttagcompound.getInteger("energy");

        this.redstoneMode = nbttagcompound.getByte("redstoneMode");
    }

    public void writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setInteger("energy", this.maxEnergy);
        nbttagcompound.setByte("redstoneMode", this.redstoneMode);
    }

    @SideOnly(Side.CLIENT)
    public GuiScreen getGui(EntityPlayer entityPlayer, boolean isAdmin) {
        return new GuiMolecularTransformer(new ContainerBaseMolecular(entityPlayer, this));
    }

    protected void updateEntityServer() {
        super.updateEntityServer();

    }

    public void onNetworkEvent(EntityPlayer player, int event) {

        this.redstoneMode = (byte) (this.redstoneMode + 1);
        if (this.redstoneMode >= 8)
            this.redstoneMode = 0;

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


}
