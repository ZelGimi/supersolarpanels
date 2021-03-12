package com.Denfop.tiles.Mechanism;

import com.Denfop.Config;
import com.Denfop.InvSlot.InvSlotQQ;
import com.Denfop.container.ContainerQuantumQuarry;
import com.Denfop.gui.GuiQuantumQuarry;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.network.INetworkTileEntityEventListener;
import ic2.core.ContainerBase;
import ic2.core.IC2;
import ic2.core.IHasGui;
import ic2.core.audio.AudioSource;
import ic2.core.block.invslot.InvSlotOutput;
import ic2.core.block.machine.tileentity.TileEntityElectricMachine;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TileEntityQuantumQuarry extends TileEntityElectricMachine implements IHasGui, INetworkTileEntityEventListener {

    public AudioSource audioSource;
    public int energyconsume;
    public int getblock;
    public InvSlotOutput outputSlot;
    public InvSlotQQ inputslot;
    Random rand = new Random();
    private int progress;


    public TileEntityQuantumQuarry() {
        super(10000000, 11, 1);
        this.progress = 0;
        this.getblock = 0;
        this.energyconsume = Config.enerycost;

        this.outputSlot = new InvSlotOutput(this, "output", 2, 24);
        this.inputslot = new InvSlotQQ(this, 3);
    }

    public List<ItemStack> list() {

        List<ItemStack> list = new ArrayList<ItemStack>();


        for (int i = 0; i < OreDictionary.getOres("oreCopper").size(); i++) {
            list.add(OreDictionary.getOres("oreCopper").get(i));
        }

        for (int i = 0; i < OreDictionary.getOres("oreGold").size(); i++) {
            list.add(OreDictionary.getOres("oreGold").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreSilver").size(); i++) {
            list.add(OreDictionary.getOres("oreSilver").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreIridium").size(); i++) {
            list.add(OreDictionary.getOres("oreIridium").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreIron").size(); i++) {
            list.add(OreDictionary.getOres("oreIron").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreBauxite").size(); i++) {
            list.add(OreDictionary.getOres("oreBauxite").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreLead").size(); i++) {
            list.add(OreDictionary.getOres("oreLead").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreRuby").size(); i++) {
            list.add(OreDictionary.getOres("oreRuby").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreCoal").size(); i++) {
            list.add(OreDictionary.getOres("oreCoal").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreDiamond").size(); i++) {
            list.add(OreDictionary.getOres("oreDiamond").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreEmerald").size(); i++) {
            list.add(OreDictionary.getOres("oreEmerald").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreLapis").size(); i++) {
            list.add(OreDictionary.getOres("oreLapis").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreQuartz").size(); i++) {
            list.add(OreDictionary.getOres("oreQuartz").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreRedstone").size(); i++) {
            list.add(OreDictionary.getOres("oreRedstone").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreNickel").size(); i++) {
            list.add(OreDictionary.getOres("oreNickel").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreChromium").size(); i++) {
            list.add(OreDictionary.getOres("oreChromium").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreSpinel").size(); i++) {
            list.add(OreDictionary.getOres("oreSpinel").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreMikhalov").size(); i++) {
            list.add(OreDictionary.getOres("oreMikhalov").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreMagnesium").size(); i++) {
            list.add(OreDictionary.getOres("oreMagnesium").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreTungsten").size(); i++) {
            list.add(OreDictionary.getOres("oreTungsten").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("orePlatinum").size(); i++) {
            list.add(OreDictionary.getOres("orePlatinum").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreAluminium").size(); i++) {
            list.add(OreDictionary.getOres("oreAluminium").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreDraconium").size(); i++) {
            list.add(OreDictionary.getOres("oreDraconium").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreEssence").size(); i++) {
            list.add(OreDictionary.getOres("oreEssence").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreSteel").size(); i++) {
            list.add(OreDictionary.getOres("oreSteel").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreCobalt").size(); i++) {
            list.add(OreDictionary.getOres("oreCobalt").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreCertusQuartz").size(); i++) {
            list.add(OreDictionary.getOres("oreCertusQuartz").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreChargedCertusQuartz").size(); i++) {
            list.add(OreDictionary.getOres("oreChargedCertusQuartz").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreSapphire").size(); i++) {
            list.add(OreDictionary.getOres("oreSapphire").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreSulfur").size(); i++) {
            list.add(OreDictionary.getOres("oreSulfur").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreTin").size(); i++) {
            list.add(OreDictionary.getOres("oreTin").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreInfusedAir").size(); i++) {
            list.add(OreDictionary.getOres("oreInfusedAir").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreInfusedEarth").size(); i++) {
            list.add(OreDictionary.getOres("oreInfusedEarth").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreInfusedEntropy").size(); i++) {
            list.add(OreDictionary.getOres("oreInfusedEntropy").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreInfusedEntropy").size(); i++) {
            list.add(OreDictionary.getOres("oreInfusedEntropy").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreInfusedOrder").size(); i++) {
            list.add(OreDictionary.getOres("oreInfusedOrder").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreInfusedWater").size(); i++) {
            list.add(OreDictionary.getOres("oreInfusedWater").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreAmber").size(); i++) {
            list.add(OreDictionary.getOres("oreAmber").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreCinnabar").size(); i++) {
            list.add(OreDictionary.getOres("oreCinnabar").get(i));
        }
        for (int i = 0; i < OreDictionary.getOres("oreDark").size(); i++) {
            list.add(OreDictionary.getOres("oreDark").get(i));
        }

        return list;

    }

    //
    public List<ItemStack> list_ingot() {

        List<ItemStack> list = new ArrayList<ItemStack>();


        list.add(OreDictionary.getOres("ingotCopper").get(0));
        list.add(OreDictionary.getOres("ingotGold").get(0));
        list.add(OreDictionary.getOres("ingotSilver").get(0));
        list.add(OreDictionary.getOres("ingotIridium").get(0));
        list.add(OreDictionary.getOres("ingotIron").get(0));
        if (OreDictionary.getOres("oreBauxite").size() > 0)
            list.add(OreDictionary.getOres("ingotBauxite").get(0));

        list.add(OreDictionary.getOres("ingotLead").get(0));
        if (OreDictionary.getOres("oreRuby").size() > 0)
            list.add(OreDictionary.getOres("gemRuby").get(0));
        list.add(new ItemStack(Items.coal));
        list.add(OreDictionary.getOres("gemDiamond").get(0));
        list.add(OreDictionary.getOres("gemEmerald").get(0));
        list.add(OreDictionary.getOres("gemLapis").get(0));

        list.add(new ItemStack(Items.quartz));
        list.add(new ItemStack(Items.redstone));
        list.add(OreDictionary.getOres("ingotNickel").get(0));
        list.add(OreDictionary.getOres("ingotChromium").get(0));
        list.add(OreDictionary.getOres("ingotSpinel").get(0));
        list.add(OreDictionary.getOres("ingotMikhalov").get(0));
        list.add(OreDictionary.getOres("ingotMagnesium").get(0));
        list.add(OreDictionary.getOres("ingotTungsten").get(0));
        list.add(OreDictionary.getOres("ingotPlatinum").get(0));
        if (OreDictionary.getOres("oreAluminium").size() > 0)
            list.add(OreDictionary.getOres("ingotAluminium").get(0));
        if (OreDictionary.getOres("oreDraconium").size() > 0)
            list.add(OreDictionary.getOres("ingotDraconium").get(0));
        if (OreDictionary.getOres("oreEssence").size() > 0)
            list.add(OreDictionary.getOres("gemEssence").get(0));
        if (OreDictionary.getOres("oreCobalt").size() > 0)
            list.add(OreDictionary.getOres("ingotCobalt").get(0));
        if (OreDictionary.getOres("oreSapphire").size() > 0)
            list.add(OreDictionary.getOres("gemSapphire").get(0));
        if (OreDictionary.getOres("oreSulfur").size() > 0)
            list.add(OreDictionary.getOres("gemSulfur").get(0));
        list.add(OreDictionary.getOres("ingotTin").get(0));
        if (Loader.isModLoaded("Thaumcraft")) {
            list.add(OreDictionary.getOres("shardAir").get(0));
            list.add(OreDictionary.getOres("shardEarth").get(0));
            list.add(OreDictionary.getOres("shardEntropy").get(0));
            list.add(OreDictionary.getOres("shardEntropy").get(0));
            list.add(OreDictionary.getOres("shardOrder").get(0));
            list.add(OreDictionary.getOres("shardWater").get(0));
            list.add(OreDictionary.getOres("gemAmber").get(0));
            list.add(OreDictionary.getOres("clusterCinnabar").get(0));
        }
        if (Loader.isModLoaded("evilcraft"))
            list.add(OreDictionary.getOres("gemDark").get(0));


        return list;

    }

    protected void updateEntityServer() {

        super.updateEntityServer();
        if (this.energy > this.maxEnergy)
            this.energy = this.maxEnergy;
        double proccent = Config.enerycost;
        if (!this.inputslot.isEmpty() && this.inputslot.get().getItemDamage() != 0) {
            proccent = this.inputslot.get().getItemDamage();

            proccent = (proccent * 0.1);

            proccent *= Config.enerycost;
            proccent = (Config.enerycost - proccent);

        }


        if (this.energy >= proccent) {

            this.setActive(true);
            this.energy -= (proccent);

            int chance = rand.nextInt(100) + 1;
            if (chance <= 95) {
                this.getblock++;
            } else if (chance >= 95 && chance <= 100) {
                this.getblock++;
                if (!this.inputslot.isEmpty() && this.inputslot.get().getItemDamage() == 0) {
                    List<ItemStack> list = list_ingot();
                    int num = list.size();
                    int chance1 = rand.nextInt(num);

                    if (this.outputSlot.canAdd(list.get(chance1))) {

                        this.outputSlot.add(list.get(chance1));

                    }
                } else {
                    List<ItemStack> list = list();
                    int num = list.size();
                    int chance1 = rand.nextInt(num);

                    if (this.outputSlot.canAdd(list.get(chance1))) {

                        this.outputSlot.add(list.get(chance1));

                    }
                }
            }
        } else {
            this.setActive(false);
        }

    }

    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        this.progress = nbttagcompound.getInteger("progress");
        NBTTagList nbttaglist = nbttagcompound.getTagList("Items", 10);
        this.getblock = nbttagcompound.getInteger("getblock");

    }

    public void writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        NBTTagList nbttaglist = new NBTTagList();
        nbttagcompound.setInteger("getblock", this.getblock);
        nbttagcompound.setInteger("progress", this.progress);

    }

    public int getSizeInventory() {
        return 24;
    }

    public double getEnergy() {
        return this.energy;
    }

    public boolean useEnergy(double amount) {
        if (this.energy >= amount) {
            this.energy -= amount;
            return true;
        }
        return false;
    }


    public boolean isItemValidForSlot(final int i, final ItemStack itemstack) {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public GuiScreen getGui(EntityPlayer entityPlayer, boolean isAdmin) {
        return new GuiQuantumQuarry(new ContainerQuantumQuarry(entityPlayer, this));
    }

    public ContainerBase<? extends TileEntityQuantumQuarry> getGuiContainer(EntityPlayer entityPlayer) {
        return (ContainerBase<? extends TileEntityQuantumQuarry>) new ContainerQuantumQuarry(entityPlayer, this);
    }

    public void onLoaded() {
        super.onLoaded();

    }

    public void onUnloaded() {
        super.onUnloaded();
        if (IC2.platform.isRendering() && this.audioSource != null) {
            IC2.audioManager.removeSources(this);
            this.audioSource = null;
        }
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


    @Override
    public void onNetworkEvent(int event) {
        if (this.audioSource == null && getStartSoundFile() != null)
            this.audioSource = IC2.audioManager.createSource(this, getStartSoundFile());
        switch (event) {
            case 0:
                if (this.audioSource != null)
                    this.audioSource.play();
                break;
            case 1:
                if (this.audioSource != null) {
                    this.audioSource.stop();
                    if (getInterruptSoundFile() != null)
                        IC2.audioManager.playOnce(this, getInterruptSoundFile());
                }
                break;
            case 2:
                if (this.audioSource != null)
                    this.audioSource.stop();
                break;
        }
    }


    @Override
    public void onGuiClosed(EntityPlayer arg0) {
    }

    @Override
    public String getInventoryName() {
        // TODO Auto-generated method stub
        return null;
    }


}
