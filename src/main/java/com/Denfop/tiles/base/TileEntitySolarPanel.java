package com.Denfop.tiles.base;

import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.IEnergyHandler;
import com.Denfop.api.module.*;
import com.Denfop.container.ContainerAdvSolarPanel;
import com.Denfop.integration.GC.ExtraPlanetsIntegration;
import com.Denfop.integration.GC.GalacticraftIntegration;
import com.Denfop.integration.GC.GalaxySpaceIntegration;
import com.Denfop.integration.GC.MorePlanetsIntegration;
import com.Denfop.item.Modules.ItemWirelessModule;
import com.Denfop.item.Modules.module5;
import com.Denfop.item.Modules.module6;
import com.Denfop.item.Modules.module7;
import com.Denfop.utils.NBTData;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.Event;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.energy.tile.IEnergyTile;
import ic2.api.item.ElectricItem;
import ic2.api.network.INetworkClientTileEntityEventListener;
import ic2.api.network.INetworkDataProvider;
import ic2.api.network.INetworkUpdateListener;
import ic2.api.tile.IWrenchable;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class TileEntitySolarPanel extends TileEntityBase implements IEnergyTile, INetworkDataProvider, INetworkUpdateListener, IWrenchable, IEnergySource, IInventory, IEnergyHandler, INetworkClientTileEntityEventListener {
    public static Random randomizer;
    private static List<String> fields = Arrays.asList(new String[0]);

    static {
        TileEntitySolarPanel.randomizer = new Random();
        TileEntitySolarPanel.fields = Arrays.asList(new String[0]);
    }

    public int ticker;
    public int generating;
    public int genDay;
    public int genNight;
    public boolean initialized;
    public boolean sunIsUp;
    public boolean skyIsVisible;
    public boolean wetBiome;
    public boolean addedToEnergyNet;
    public ItemStack[] chargeSlots;
    public int fuel;
    public boolean personality = false;
    public int storage;
    public int solarType;
    public String panelName;
    public int production;
    public int maxStorage;
    public int p;
    public boolean loaded;
    public int k;
    public int m;
    public int u;
    public int tier;
    public int convertState = 0;
    public int elapsedTicks;
    public boolean changesolartype = false;
    public int panelx = 0;
    public int panely = 0;
    public int panelz = 0;
    public int outputEnergyBuffer;
    public long lastTimeStamp;
    public int inputEnergyBuffer;
    public String player = null;
    public int o;
    public int storage2;
    public boolean rain;
    public int tickRate = 40;
    public int maxStorage2;
    public int tickRateFlush = 5;
    public boolean running = false;
    public boolean transfer = false;
    private TileEntitySolarPanel tileentity;
    private short facing;
    private boolean noSunWorld;
    private final int machineTire;
    private final boolean created;
    private int lastX;
    private int lastY;
    private int lastZ;
    private module6 panel;
    private int ticksSinceSync;
    private String nameblock;
    private int world1;
    private int blocktier;
    public TileEntitySolarPanel(final String gName, final int tier, final int typeSolar, final int gDay, final int gNight, final int gOutput, final int gmaxStorage) {

        this.loaded = false;
        this.created = false;
        this.facing = 2;
        this.solarType = typeSolar;
        this.genDay = gDay;
        this.genNight = gNight;
        this.storage = 0;
        this.panelName = gName;
        this.sunIsUp = false;
        this.skyIsVisible = false;
        this.maxStorage = gmaxStorage;
        this.p = gmaxStorage;
        this.k = gDay;
        this.m = gNight;

        this.maxStorage2 = this.maxStorage;
        this.chargeSlots = new ItemStack[9];
        this.initialized = false;
        this.production = gOutput;
        this.u = gOutput;

        this.ticker = TileEntitySolarPanel.randomizer.nextInt(this.tickRate());
        this.lastX = this.xCoord;
        this.lastY = this.yCoord;
        this.lastZ = this.zCoord;
        this.machineTire = this.o;
        this.tier = tier;
        this.rain = false;


    }

    public void validate() {
        super.validate();
        if (this.isInvalid() || !this.worldObj.blockExists(this.xCoord, this.yCoord, this.zCoord)) {
            return;
        }
        this.onLoaded();
    }

    public void invalidate() {
        if (this.loaded) {
            this.onUnloaded();
        }
        super.invalidate();
    }

    public void onLoaded() {
        if (!this.worldObj.isRemote) {
            MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
            this.addedToEnergyNet = true;
        }
        this.loaded = true;
    }

    public void onChunkUnload() {
        if (this.loaded) {
            this.onUnloaded();
        }
        super.onChunkUnload();
    }

    public void onUnloaded() {
        if (!this.worldObj.isRemote && this.addedToEnergyNet) {
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
            this.addedToEnergyNet = false;
        }
        this.loaded = false;
    }

    public void intialize() {
        this.wetBiome = (this.worldObj.getWorldChunkManager().getBiomeGenAt(this.xCoord, this.zCoord).getIntRainfall() > 0);
        this.noSunWorld = this.worldObj.provider.hasNoSky;

        this.updateVisibility();
        this.initialized = true;
        if (!this.addedToEnergyNet) {
            this.onLoaded();
        }
        this.lastTimeStamp = System.currentTimeMillis();
    }

    public void updateEntity() {

        super.updateEntity();
        if (!this.initialized && this.worldObj != null) {
            this.intialize();
        }
        if (this.worldObj.isRemote) {
            return;
        }


        if (this.lastX != this.xCoord || this.lastZ != this.zCoord || this.lastY != this.yCoord) {
            this.lastX = this.xCoord;
            this.lastY = this.yCoord;
            this.lastZ = this.zCoord;
            this.onUnloaded();
            this.intialize();
        }
        this.gainFuel();
        if (this.generating > 0) {

            if (this.storage + this.generating <= this.maxStorage) {
                this.storage += this.generating;
            } else {
                this.storage = this.maxStorage;
            }
        }
        boolean needInvUpdate = false;
        double sentPacket = 0.0;


        int tierplus = 0;
        int minus = 0;
        int[] output;
        output = new int[9];
        //
        int[] maxstorage1;
        maxstorage1 = new int[9];
        int[] gend;
        gend = new int[9];
        int[] genn;
        genn = new int[9];
        for (int i = 0; i < 9; i++) {
            if (this.chargeSlots[i] != null && this.chargeSlots[i].getItem() instanceof IModulGenDay) {

                gend[i] = IModulGenDay.getData(this.chargeSlots[i]).get(0);
            }
            if (this.chargeSlots[i] != null && this.chargeSlots[i].getItem() instanceof IModulGenNight) {
                genn[i] = IModulGenNight.getData(this.chargeSlots[i]).get(0);
            }
            if (this.chargeSlots[i] != null && this.chargeSlots[i].getItem() instanceof IModulStorage) {
                maxstorage1[i] = IModulStorage.getData(this.chargeSlots[i]).get(0);

            }
            if (this.chargeSlots[i] != null && this.chargeSlots[i].getItem() instanceof IModulOutput) {
                output[i] = IModulOutput.getData(this.chargeSlots[i]).get(0);

            }


            if (this.chargeSlots[i] != null && this.chargeSlots[i].getItem() instanceof module7) {
                int kk = chargeSlots[i].getItemDamage();
                if (kk == 0) {
                    personality = true;
                }
                if (kk == 1) {
                    tierplus++;
                } else if (kk == 2) {
                    minus++;
                } else if (kk == 3) {
                    for (int j = 0; j < 9; j++) {
                        if (this.chargeSlots[j] != null && this.chargeSlots[j].getItem() instanceof ic2.api.item.IElectricItem && this.storage > 0.0D) {
                            sentPacket = ElectricItem.manager.charge(this.chargeSlots[j], this.storage, 2147483647, false, false);
                            if (sentPacket > 0.0D)
                                needInvUpdate = true;
                            this.storage -= (int) sentPacket;
                        }
                    }
                    for (int j = 0; j < 9; j++) {
                        if (this.chargeSlots[j] != null && this.chargeSlots[j].getItem() instanceof module7) {
                            int meta = chargeSlots[j].getItemDamage();
                            if (meta == 4) {
                                for (int jj = 0; jj < 9; jj++) {
                                    if (this.chargeSlots[jj] != null && this.chargeSlots[jj].getItem() instanceof IEnergyContainerItem && this.storage2 > 0.0D) {
                                        if (jj == j || jj == i)
                                            continue;

                                        IEnergyContainerItem item = (IEnergyContainerItem) this.chargeSlots[jj].getItem();
                                        setTransfer((extractEnergy(null, item.receiveEnergy(this.chargeSlots[jj], this.storage2, false), false) > 0));
                                    } else {
                                        setTransfer(false);
                                    }


                                }

                            }
                        }
                    }
                } else if (kk == 4) {

                    if (this.convertState == 0 && this.worldObj.canBlockSeeTheSky(this.xCoord, this.yCoord + 1, this.zCoord) || this.noSunWorld) {
                        if (this.storage >= 0 && this.storage2 < this.maxStorage2) {


                            this.storage2 += this.storage * 4;
                            this.storage -= this.storage;
                            if (this.storage2 > this.maxStorage2) {
                                int temp = this.storage2 - this.maxStorage2;
                                this.storage += temp / 4;
                            }
                        }


                        for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
                            TileEntity tile = this.worldObj.getTileEntity(this.xCoord + side.offsetX, this.yCoord + side.offsetY, this.zCoord + side.offsetZ);
                            if (tile instanceof IEnergyHandler)
                                extractEnergy(side.getOpposite(), ((IEnergyHandler) tile).receiveEnergy(side.getOpposite(), extractEnergy(side.getOpposite(), (this.production * 8), true), false), false);
                        }

                    }
                }
            } else {

            }


        }
        for (int m = 0; m < 9; m++) {
            if (this.chargeSlots[m] != null && this.chargeSlots[m].getItem() instanceof module7) {
                int kk = chargeSlots[m].getItemDamage();
                if (kk == 0) {
                    personality = true;
                    break;
                } else {
                    this.personality = false;
                }
            } else {
                this.personality = false;
            }
        }
        if (this.storage2 >= this.maxStorage2) {
            this.storage2 = this.maxStorage2;
        } else if (this.storage2 < 0) {
            this.storage2 = 0;
        }
        if (this.tier + tierplus - minus > 0) {
            this.o = this.tier + tierplus - minus;
        } else {
            this.o = 0;
        }
        int[] a;
        a = new int[9];
        int[] b;
        b = new int[9];
        int[] c;
        c = new int[9];
        int[] d;
        d = new int[9];
        Block block = this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord);
        NBTTagCompound nbttagcompound1 = NBTData.getOrCreateNbtData(new ItemStack(block));
        nbttagcompound1.setInteger("type", -1);
        if (this.chargeSlots[8] != null && this.chargeSlots[8].getItem() instanceof module5) {
            int g = chargeSlots[8].getItemDamage();


            if (g < 7 && g >= 0) {
                nbttagcompound1.setInteger("type", g + 1);

                this.solarType = g + 1;


            }
        } else {

            nbttagcompound1.setInteger("type", 0);
            this.solarType = 0;
        }
        for (int i = 0; i < 9; i++) {
            if (this.chargeSlots[i] != null && this.chargeSlots[i].getItem() instanceof IModulPanel) {
                int g = chargeSlots[i].getItemDamage();
                if (o >= g + 1) {
                    List<Integer> list = IModulPanel.getData(chargeSlots[i]);
                    a[i] = list.get(0);
                    b[i] = list.get(1);
                    c[i] = list.get(2);
                    d[i] = list.get(3);

                }

            }
        }
        int wirelees = 0;
        for (int i = 0; i < 9; i++) {
            if (this.chargeSlots[i] != null && this.chargeSlots[i].getItem() instanceof ItemWirelessModule) {

                wirelees = 1;
                int x = 0;
                int y = 0;
                int z = 0;
                String name = null;
                int tier1 = 0;

                NBTTagCompound nbttagcompound = NBTData.getOrCreateNbtData(this.chargeSlots[i]);

                x = nbttagcompound.getInteger("Xcoord");
                y = nbttagcompound.getInteger("Ycoord");
                z = nbttagcompound.getInteger("Zcoord");
                tier1 = nbttagcompound.getInteger("tier");
                name = nbttagcompound.getString("Name");
                int world = nbttagcompound.getInteger("World1");

                if (nbttagcompound != null && x != 0 && y != 0 && z != 0) {
                    this.panelx = x;
                    this.panely = y;
                    this.panelz = z;
                    this.nameblock = name;
                    this.world1 = world;
                    this.blocktier = tier1;
                }
                break;
            }
        }
        if (this.worldObj.getTileEntity(panelx, panely, panelz) != null && this.worldObj.getTileEntity(panelx, panely, panelz) instanceof TileEntityElectricBlock && panelx != 0 && panely != 0 && panelz != 0 && wirelees != 0) {
            TileEntityElectricBlock tile = (TileEntityElectricBlock) this.worldObj.getTileEntity(panelx, panely, panelz);
            if (tile.tier == this.blocktier && tile.getWorldObj().provider.dimensionId == this.world1) {
                if (this.storage > 0 && tile.energy < tile.maxStorage
                ) {
                    int temp = (int) (tile.maxStorage - tile.energy);
                    if (this.storage > temp) {
                        tile.energy = temp;
                        this.storage -= temp;
                    } else if (temp > this.storage) {

                        tile.energy += (this.storage);
                        this.storage = 0;
                    }

                }
            }
        } else {
            this.panelx = 0;
            this.panely = 0;
            this.panelz = 0;
        }

        int sum = 0, sum1 = 0, sum2 = 0, sum3 = 0;
        for (int i = 0; i < 9; i++) {
            sum = sum + a[i];
            sum1 = sum1 + b[i];
            sum2 = sum2 + c[i];
            sum3 = sum3 + d[i];
        }
        double gend_dob = 0;

        for (int i = 0; i < 9; i++) {
            if (gend[i] != 0) {
                gend_dob = gend_dob + gend[i];
            }

        }
        double genn_dob = 0;

        for (int i = 0; i < 9; i++) {
            if (genn[i] != 0) {
                genn_dob = genn_dob + genn[i];
            }

        }
        if ((int) ((this.k + sum) + (this.k + sum) * (gend_dob / 100)) < 2000000000) {
            this.genDay = (int) ((this.k + sum) + (this.k + sum) * (gend_dob / 100));
        } else {
            this.genDay = 2000000000;
        }
        if ((int) ((this.m + sum1) + (this.m + sum1) * (genn_dob / 100)) < 2000000000) {
            this.genNight = (int) ((this.m + sum1) + (this.m + sum1) * (genn_dob / 100));
        } else {
            this.genNight = 2000000000;
        }//

        double maxstorage_dob = 0;

        for (int i = 0; i < 9; i++) {
            if (maxstorage1[i] != 0) {
                maxstorage_dob = maxstorage_dob + maxstorage1[i];
            }

        }
        if ((int) ((this.p + sum2) + (this.p + sum2) * (maxstorage_dob / 100)) < 0) {
            this.maxStorage = 0;
        } else if ((int) ((this.p + sum2) + (this.p + sum2) * (maxstorage_dob / 100)) > 2000000000) {
            this.maxStorage = 2000000000;
        } else {
            this.maxStorage = (int) ((this.p + sum2) + (this.p + sum2) * (maxstorage_dob / 100));
        }
        //
        double output_dob = 0;

        for (int i = 0; i < 9; i++) {
            if (output[i] != 0) {
                output_dob = output_dob + output[i];
            }

        }
        if ((int) ((this.u + sum3) + (this.u + sum3) * (output_dob / 100)) < 2000000000) {
            this.production = (int) ((this.u + sum3) + (this.u + sum3) * output_dob);
        } else {
            this.production = 2000000000;
        }


        if (needInvUpdate) {
            super.markDirty();
        }
    }

    private void setTransfer(boolean t) {
        this.transfer = t;
    }

    public int gainFuel() {
        double planet_efficenty = 1;
        if (Loader.isModLoaded("GalacticraftCore")) {
            planet_efficenty = GalacticraftIntegration.GetPlanetefficienty(this.worldObj.provider.dimensionId, this.worldObj);
        }
        if (Loader.isModLoaded("ExtraPlanets")) {
            planet_efficenty = ExtraPlanetsIntegration.GetPlanetefficienty(this.worldObj.provider.dimensionId, this.worldObj);
        }
        if (Loader.isModLoaded("MorePlanet")) {
            planet_efficenty = MorePlanetsIntegration.GetPlanetefficienty(this.worldObj.provider.dimensionId, this.worldObj);
        }
        if (Loader.isModLoaded("GalaxySpace")) {
            planet_efficenty = GalaxySpaceIntegration.GetPlanetefficienty(this.worldObj.provider.dimensionId, this.worldObj);
        }
        double rain_efficenty = 1;
        if (this.rain == true)
            rain_efficenty = 0.65;

        if (solarType == 0) {
            if (this.ticker++ % this.tickRate() == 0) {
                this.updateVisibility();
            }


            if (this.sunIsUp && this.skyIsVisible) {
                return this.generating = (int) (0 + this.genDay * rain_efficenty * planet_efficenty);
            }
            if (this.skyIsVisible) {
                return this.generating = (int) (0 + this.genNight * rain_efficenty * planet_efficenty);
            }

            return this.generating = 0;
        }
        if (solarType == 1) {
            if (this.yCoord >= 130) {
                if (this.ticker++ % this.tickRate() == 0) {
                    this.updateVisibility();
                }
                if (this.sunIsUp && this.skyIsVisible) {
                    return this.generating = (int) (0 + 2 * this.genDay * rain_efficenty * planet_efficenty);
                }
                if (this.skyIsVisible) {
                    return this.generating = (int) (0 + 2 * this.genNight * rain_efficenty * planet_efficenty);
                }


            } else {
                if (this.ticker++ % this.tickRate() == 0) {
                    this.updateVisibility();
                }
                if (this.sunIsUp && this.skyIsVisible) {
                    return this.generating = (int) (0 + this.genDay * rain_efficenty * planet_efficenty);
                }
                if (this.skyIsVisible) {
                    return this.generating = (int) (0 + this.genNight * rain_efficenty * planet_efficenty);
                }
                return this.generating = 0;
            }
        }

        if (solarType == 2) {
            if (this.yCoord <= 40) {
                if (this.ticker++ % this.tickRate() == 0) {
                    this.updateVisibility();
                }
                if (this.sunIsUp && this.skyIsVisible) {
                    return this.generating = (int) (0 + 2 * this.genDay * rain_efficenty * planet_efficenty);
                }
                if (this.skyIsVisible) {
                    return this.generating = (int) (0 + 2 * this.genNight * rain_efficenty * planet_efficenty);
                }


            } else {
                if (this.ticker++ % this.tickRate() == 0) {
                    this.updateVisibility();
                }
                if (this.sunIsUp && this.skyIsVisible) {
                    return this.generating = (int) (0 + this.genDay * rain_efficenty * planet_efficenty);
                }
                if (this.skyIsVisible) {
                    return this.generating = (int) (0 + this.genNight * rain_efficenty * planet_efficenty);
                }
                return this.generating = 0;
            }
        }
        if (solarType == 3) {
            if (this.worldObj.provider.dimensionId == -1) {
                return this.generating = 0 + 2 * this.genDay;
            } else {
                if (this.ticker++ % this.tickRate() == 0) {
                    this.updateVisibility();
                }
                if (this.sunIsUp && this.skyIsVisible) {
                    return this.generating = (int) (0 + this.genDay * rain_efficenty * planet_efficenty);
                }
                if (this.skyIsVisible) {
                    return this.generating = (int) (0 + this.genNight * rain_efficenty * planet_efficenty);
                }
                return this.generating = 0;
            }
        }
        if (solarType == 4) {
            if (this.worldObj.provider.dimensionId == 1) {
                return this.generating = 0 + 2 * this.genDay;
            } else {
                if (this.ticker++ % this.tickRate() == 0) {
                    this.updateVisibility();
                }
                if (this.sunIsUp && this.skyIsVisible) {
                    return this.generating = (int) (0 + this.genDay * rain_efficenty * planet_efficenty);
                }
                if (this.skyIsVisible) {
                    return this.generating = (int) (0 + this.genNight * rain_efficenty * planet_efficenty);
                }
                return this.generating = 0;
            }
        }

        if (solarType == 5) {

            if (this.ticker++ % this.tickRate() == 0) {
                this.updateVisibility();
            }
            if (this.sunIsUp && this.skyIsVisible) {
                return this.generating = 0 + 0 * this.genDay;
            }
            if (this.skyIsVisible) {
                return this.generating = (int) (2 * this.genNight * rain_efficenty * planet_efficenty);
            }
        }
        if (solarType == 6) {

            if (this.ticker++ % this.tickRate() == 0) {
                this.updateVisibility();
            }
            if (this.sunIsUp && this.skyIsVisible) {
                return this.generating = (int) (0 + 2 * this.genDay * rain_efficenty * planet_efficenty);
            }
            if (this.skyIsVisible) {
                return this.generating = 0 + 0 * this.genNight;
            }
        }
        if (solarType == 7) {
            if ((this.worldObj.isRaining() || this.worldObj.isThundering()) && this.worldObj.provider.dimensionId != 1 && this.worldObj.provider.dimensionId != -1) {


                if (this.ticker++ % this.tickRate() == 0) {
                    this.updateVisibility();
                }
                if (this.sunIsUp && this.skyIsVisible) {
                    return this.generating = (int) (0 + 1.5 * this.genDay * planet_efficenty);
                }
                if (this.skyIsVisible) {
                    return this.generating = 0 + (int) (1.5 * this.genNight * planet_efficenty);
                }
            } else {
                if (this.ticker++ % this.tickRate() == 0) {
                    this.updateVisibility();
                }

                if (this.sunIsUp && this.skyIsVisible) {
                    return this.generating = (int) (0 + this.genDay * planet_efficenty);
                }
                if (this.skyIsVisible) {
                    return this.generating = (int) (0 + this.genNight * planet_efficenty);
                }
            }

        }
        return this.generating = 0;
    }

    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        if (this.convertState == 0 && this.storage2 > 0) {
            int energyExtracted = Math.min(this.storage2, maxExtract);
            if (!simulate) {
                this.storage2 -= energyExtracted;
                this.outputEnergyBuffer += energyExtracted;
            }
            return energyExtracted;
        }
        if (this.convertState == 0) ;
        return 0;
    }

    public void updateVisibility() {

        this.rain = this.wetBiome && (this.worldObj.isRaining() || this.worldObj.isThundering());
        this.sunIsUp = this.worldObj.isDaytime();
        this.skyIsVisible = this.worldObj.canBlockSeeTheSky(this.xCoord, this.yCoord + 1, this.zCoord) && !this.noSunWorld;

    }

    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        this.storage = nbttagcompound.getInteger("storage");
        this.storage2 = nbttagcompound.getInteger("storage2");
        this.lastX = nbttagcompound.getInteger("lastX");
        this.lastY = nbttagcompound.getInteger("lastY");
        this.lastZ = nbttagcompound.getInteger("lastZ");

        //TODO


        this.solarType = nbttagcompound.getInteger("solarType");

        this.panelx = nbttagcompound.getInteger("panelx");
        this.panely = nbttagcompound.getInteger("panely");
        this.panelz = nbttagcompound.getInteger("panelz");
        this.nameblock = nbttagcompound.getString("nameblock");
        this.world1 = nbttagcompound.getInteger("worldid");
        this.player = nbttagcompound.getString("player");
        blocktier = nbttagcompound.getInteger("blocktier");

        NBTTagList nbttaglist = nbttagcompound.getTagList("Items", 10);

        this.chargeSlots = new ItemStack[getSizeInventory()];
        for (int i = 0; i < nbttaglist.tagCount(); i++) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 0xFF;
            if (j >= 0 && j < this.chargeSlots.length)
                this.chargeSlots[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);

        }

    }

    public void writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);


        NBTTagList nbttaglist = new NBTTagList();

        nbttagcompound.setInteger("panelx", this.panelx);
        nbttagcompound.setInteger("panely", this.panely);
        nbttagcompound.setInteger("panelz", this.panelz);
        if (nameblock != null)
            nbttagcompound.setString("nameblock", nameblock);

        nbttagcompound.setInteger("worldid", world1);
        nbttagcompound.setInteger("blocktier", this.blocktier);


        if (player != null) {
            nbttagcompound.setString("player", player);
        }
        nbttagcompound.setInteger("solarType", this.solarType);
        nbttagcompound.setInteger("storage", this.storage);
        nbttagcompound.setInteger("storage2", this.storage2);
        nbttagcompound.setInteger("lastX", this.lastX);
        nbttagcompound.setInteger("lastY", this.lastY);
        nbttagcompound.setInteger("lastZ", this.lastZ);
        for (int i = 0; i < this.chargeSlots.length; i++) {
            if (this.chargeSlots[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();

                nbttagcompound1.setByte("Slot", (byte) i);

                this.chargeSlots[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        nbttagcompound.setTag("Items", nbttaglist);

        nbttagcompound.setDouble("energy", this.storage);
    }

    public boolean isAddedToEnergyNet() {
        return this.addedToEnergyNet;
    }

    public int getMaxEnergyOutput() {
        return this.production;
    }

    public float gaugeEnergyScaled(final float i) {
        int tierplus = 0;
        int minus = 0;
        int gg = 0;
        for (int j = 0; j < 9; j++) {
            if (this.chargeSlots[j] != null && this.chargeSlots[j].getItem() instanceof module7) {
                int kk = chargeSlots[j].getItemDamage();

                if (kk == 1) {
                    tierplus++;
                } else if (kk == 2) {
                    minus++;
                }

            }
        }
        this.o = this.tier + tierplus - minus;

        int[] maxstorage1;
        maxstorage1 = new int[9];
        double maxstorage_dob = 0;
        for (int j = 0; j < 9; j++) {
            if (this.chargeSlots[j] != null && this.chargeSlots[j].getItem() instanceof IModulStorage) {
                maxstorage1[j] = IModulStorage.getData(this.chargeSlots[j]).get(0);
                if (maxstorage1[j] != 0) {
                    maxstorage_dob = maxstorage_dob + maxstorage1[j];
                }

            }
        }

        int[] c;
        c = new int[9];
        for (int j = 0; j < 9; j++) {
            if (this.chargeSlots[j] != null && this.chargeSlots[j].getItem() instanceof IModulPanel) {
                int g = chargeSlots[j].getItemDamage();
                if (o >= g + 1) {
                    List<Integer> list = IModulPanel.getData(chargeSlots[j]);
                    c[j] = list.get(2);
                }

            }

        }

        int sum = 0, sum1 = 0, sum2 = 0, sum3 = 0;
        for (int j = 0; j < 9; j++) {

            sum2 = sum2 + c[j];
        }

        if ((float) (this.storage * i / (((this.p + sum2) + (this.p + sum2) * (maxstorage_dob / 100)))) > 24)
            return 24;

        return (float) (this.storage * i / (((this.p + sum2) + (this.p + sum2) * (maxstorage_dob / 100))));

    }

    public float gaugeEnergyScaled2(final float i) {
        if ((this.storage2 * i / this.maxStorage2) > 24)
            return 24;

        return this.storage2 * i / (this.maxStorage2);

    }

    public boolean canConnectEnergy(ForgeDirection arg0) {
        return true;
    }

    public int getEnergyStored(ForgeDirection from) {
        return this.storage2;
    }

    public int getMaxEnergyStored(ForgeDirection from) {
        return this.maxStorage2;
    }

    public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction) {
        return this.convertState == 0;
    }

    public int gaugeFuelScaled(final int i) {
        return i;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {

        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && player.getDistance((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
    }

    public short getFacing() {
        return this.facing;
    }

    public void setFacing(short facing) {
        this.facing = facing;
    }

    public void openInventory() {

    }

    public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
        ItemStack ret = super.getWrenchDrop(entityPlayer);

        NBTTagCompound nbttagcompound = NBTData.getOrCreateNbtData(ret);
        nbttagcompound.setInteger("storage", this.storage);
        nbttagcompound.setInteger("storage2", this.storage2);

        return ret;
    }

    public boolean wrenchCanSetFacing(EntityPlayer entityPlayer, int side) {
        return (getFacing() != side);
    }

    public void closeInventory() {


    }

    public int tickRate() {
        return 80;
    }

    public ItemStack[] getContents() {
        return this.chargeSlots;
    }

    public int getSizeInventory() {
        return 9;
    }

    public ItemStack getStackInSlot(final int i) {
        return this.chargeSlots[i];
    }

    public ItemStack decrStackSize(final int i, final int j) {
        if (this.chargeSlots[i] == null) {
            return null;
        }
        if (this.chargeSlots[i].stackSize <= j) {
            final ItemStack itemstack = this.chargeSlots[i];
            this.chargeSlots[i] = null;
            return itemstack;
        }
        final ItemStack itemstack2 = this.chargeSlots[i].splitStack(j);
        if (this.chargeSlots[i].stackSize == 0) {
            this.chargeSlots[i] = null;
        }
        return itemstack2;
    }

    public void setInventorySlotContents(final int i, final ItemStack itemstack) {
        this.chargeSlots[i] = itemstack;
        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
    }

    public String getInventoryName() {
        return "Super Solar Panel";
    }

    public boolean hasCustomInventoryName() {
        return false;
    }

    public int getInventoryStackLimit() {
        return 1;
    }

    public Container getGuiContainer(final InventoryPlayer inventoryplayer) {

        return new ContainerAdvSolarPanel(inventoryplayer, this);


    }

    public String getInvName() {
        return null;
    }

    public ItemStack getStackInSlotOnClosing(final int var1) {
        if (this.chargeSlots[var1] != null) {
            final ItemStack var2 = this.chargeSlots[var1];
            this.chargeSlots[var1] = null;
            return var2;
        }
        return null;
    }

    public boolean isItemValidForSlot(final int i, final ItemStack itemstack) {
        return true;
    }

    public void onNetworkUpdate(final String field) {
    }

    @Override
    public List<String> getNetworkedFields() {
        List<String> ret = new Vector<String>(1);
        ret.add("owner");
        return TileEntitySolarPanel.fields;

    }

    public boolean emitsEnergyTo(final TileEntity receiver, final ForgeDirection direction) {
        return true;
    }

    public double getOfferedEnergy() {
        return Math.min(this.production, this.storage);
    }

    public void drawEnergy(final double amount) {
        this.storage -= (int) amount;
    }

    public void onGuiClosed(EntityPlayer entityPlayer) {
    }

    public int getSourceTier() {
        return this.machineTire;
    }

    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        if (this.convertState == 1) {
            if (this.storage2 >= this.maxStorage2)
                return 0;
            if (this.storage2 + maxReceive > this.maxStorage2) {
                int energyReceived = this.maxStorage2 - this.storage2;
                if (!simulate) {
                    this.storage2 = this.maxStorage2;
                    this.inputEnergyBuffer += energyReceived;
                }
                return energyReceived;
            }
            if (!simulate) {
                this.inputEnergyBuffer += maxReceive;
                this.storage2 += maxReceive;
            }
            return maxReceive;
        }
        return 0;
    }

    public int getSolarType() {
        int type = this.solarType;
        return type;
    }

    @Override
    public void onNetworkEvent(EntityPlayer player, int event) {
        // TODO Auto-generated method stub

    }


}
