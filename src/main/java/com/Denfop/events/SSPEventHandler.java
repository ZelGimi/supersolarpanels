package com.Denfop.events;

import com.Denfop.Config;
import com.Denfop.DamageSource.SSPDamageSource;
import com.Denfop.SSPItem;
import com.Denfop.block.Base.BlockIC2Fluid;
import com.Denfop.block.cable.BlockCable;
import com.Denfop.item.armour.ItemArmorImprovemedQuantum;
import com.Denfop.item.energy.ultDDrill;
import com.Denfop.utils.NBTData;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.Ic2Items;
import ic2.core.block.BlockMetaData;
import ic2.core.item.armor.ItemArmorHazmat;
import ic2.core.util.Util;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class SSPEventHandler {
    @SubscribeEvent
    public void onBucketFill(FillBucketEvent event) {
        World world = event.world;
        MovingObjectPosition pos = event.target;
        Block block = world.getBlock(pos.blockX, pos.blockY, pos.blockZ);
        int meta = world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ);
        String fluidName = "ic3" + "fluidUuMatter".substring("fluid".length()).toLowerCase(Locale.ENGLISH);
        Fluid fluid = FluidRegistry.getFluid(fluidName);
        if (block == fluid.getBlock()) {
            world.setBlockToAir(pos.blockX, pos.blockY, pos.blockZ);

            event.result = SSPItem.uuMatterCell;
            event.setResult(Result.ALLOW);
        }
    }

    @SubscribeEvent
    public void UraniumOre(BreakEvent event) {
        if (!(event.block instanceof BlockMetaData))
            return;


        Block block = event.block;
        int meta = event.blockMetadata;
        ItemStack item = new ItemStack(block, 1, meta);
        if (!(Ic2Items.uraniumOre.getItem() == item.getItem()))
            return;


        if (Ic2Items.uraniumOre.getItem() == item.getItem()) {
            Random rand = new Random();
            int chance = (rand.nextInt(16) + 1);
            int chance1 = (rand.nextInt(16) + 1);
            double k = chance / 16;
            double k1 = chance1 / 8;
            double dob = (k * k1) * 100;
            ItemStack itemstack = Ic2Items.smallPlutonium;
            if (dob <= 1) {
                for (int i = 0; i < 36; i++) {
                    if (event.getPlayer().inventory.mainInventory[i] == Ic2Items.smallPlutonium) {
                        event.getPlayer().inventory.mainInventory[i].stackSize = event.getPlayer().inventory.mainInventory[i].stackSize + 1;
                        break;
                    } else if (event.getPlayer().inventory.mainInventory[i] == null) {
                        event.getPlayer().inventory.mainInventory[i] = Ic2Items.smallPlutonium;
                        break;
                    }
                }


            }
        }
    }
    //}

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onViewRenderFogDensity(EntityViewRenderEvent.FogDensity event) {
        if (!(event.block instanceof BlockIC2Fluid))
            return;
        event.setCanceled(true);
        Fluid fluid = ((BlockIC2Fluid) event.block).getFluid();
        GL11.glFogi(2917, 2048);
        event.density = (float) Util.map(Math.abs(fluid.getDensity()), 20000.0D, 2.0D);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onViewRenderFogColors(EntityViewRenderEvent.FogColors event) {
        if (!(event.block instanceof BlockIC2Fluid))
            return;
        int color = ((BlockIC2Fluid) event.block).getColor();
        event.red = (color >>> 16 & 0xFF) / 255.0F;
        event.green = (color >>> 8 & 0xFF) / 255.0F;
        event.blue = (color & 0xFF) / 255.0F;
    }

    @SubscribeEvent
    public void FlyUpdate(LivingEvent.LivingUpdateEvent event) {

        if (event.entityLiving == null || !(event.entityLiving instanceof EntityPlayer)) return;
        EntityPlayer player = (EntityPlayer) event.entity;

        if (event.entityLiving instanceof EntityPlayer) {
            if (player.capabilities.isCreativeMode == false) {

                NBTTagCompound nbtData = NBTData.getOrCreateNbtData1(player);
                boolean fly = nbtData.getBoolean("isFlyActive");
                if (!player.capabilities.isCreativeMode) {
                    if (player.inventory.armorInventory[2] != null) {
                        if (player.inventory.armorInventory[2].getItem() == SSPItem.quantumBodyarmor) {
                            NBTTagCompound nbtData1 = NBTData.getOrCreateNbtData(player.inventory.armorInventory[2]);
                            boolean jetpack = nbtData1.getBoolean("jetpack");
                            if (jetpack == false) {
                                player.capabilities.isFlying = false;
                                player.capabilities.allowFlying = false;
                                nbtData1.setBoolean("isFlyActive", false);
                                nbtData.setBoolean("isFlyActive", false);
                                player.capabilities.setFlySpeed((float) 0.05);
                            } else {
                                player.capabilities.isFlying = true;
                                player.capabilities.allowFlying = true;
                                nbtData.setBoolean("isFlyActive", true);
                                nbtData1.setBoolean("isFlyActive", true);
                                player.capabilities.setFlySpeed((float) 0.2);
                            }
                        } else if (player.inventory.armorInventory[2].getItem() != SSPItem.quantumBodyarmor && player.inventory.armorInventory[2] != null) {
                            if (nbtData.getBoolean("isFlyActive") == true) {
                                player.capabilities.isFlying = false;
                                player.capabilities.allowFlying = false;
                                nbtData.setBoolean("isFlyActive", false);
                                player.capabilities.setFlySpeed((float) 0.05);
                            }
                        }
                    } else {
                        if (nbtData.getBoolean("isFlyActive") == true) {
                            player.capabilities.isFlying = false;
                            player.capabilities.allowFlying = false;
                            nbtData.setBoolean("isFlyActive", false);
                            player.capabilities.setFlySpeed((float) 0.05);
                        }
                    }
                } else {
                    if (nbtData.getBoolean("isFlyActive") == true) {
                        player.capabilities.isFlying = false;
                        player.capabilities.allowFlying = false;
                        nbtData.setBoolean("isFlyActive", false);
                        player.capabilities.setFlySpeed((float) 0.05);
                    }
                }
            }


        }

    }


    @SubscribeEvent
    public void UpdateHelmet(LivingEvent.LivingUpdateEvent event) {

        if (event.entityLiving == null || !(event.entityLiving instanceof EntityPlayer)) return;
        EntityPlayer player = (EntityPlayer) event.entity;
        NBTTagCompound nbtData = NBTData.getOrCreateNbtData1(player);
        if (player.inventory.armorInventory[3] != null) {
            if (player.inventory.armorInventory[3].getItem() == SSPItem.quantumHelmet) {
                nbtData.setBoolean("isNightVision", true);
            } else if (player.inventory.armorInventory[3].getItem() == Ic2Items.nanoHelmet.getItem()) {
                nbtData.setBoolean("isNightVision", true);
            } else if (player.inventory.armorInventory[3].getItem() == Ic2Items.quantumHelmet.getItem()) {
                nbtData.setBoolean("isNightVision", true);
            } else if (player.inventory.armorInventory[3].getItem() == SSPItem.advancedSolarHelmet) {
                nbtData.setBoolean("isNightVision", true);
                nbtData.setBoolean("isNightVisionEnable", true);
            } else if (player.inventory.armorInventory[3].getItem() == SSPItem.hybridSolarHelmet) {
                nbtData.setBoolean("isNightVision", true);
                nbtData.setBoolean("isNightVisionEnable", true);
            } else if (player.inventory.armorInventory[3].getItem() == SSPItem.spectralSolarHelmet) {
                nbtData.setBoolean("isNightVision", true);
                nbtData.setBoolean("isNightVisionEnable", true);
            } else if (player.inventory.armorInventory[3].getItem() == SSPItem.singularSolarHelmet) {
                nbtData.setBoolean("isNightVision", true);
                nbtData.setBoolean("isNightVisionEnable", true);
            } else if (player.inventory.armorInventory[3].getItem() == Ic2Items.nightvisionGoggles.getItem()) {
                nbtData.setBoolean("isNightVision", true);
            } else if (player.inventory.armorInventory[3].getItem() == SSPItem.ultimateSolarHelmet) {
                nbtData.setBoolean("isNightVision", true);
                nbtData.setBoolean("isNightVisionEnable", true);
            }
        } else if (nbtData.getBoolean("isNightVision")) {
            nbtData.setBoolean("isNightVision", false);
        }
    }

    @SubscribeEvent
    public void UpdateNightVision(LivingEvent.LivingUpdateEvent event) {
        if (Config.nightvision) {
            if (event.entityLiving == null || !(event.entityLiving instanceof EntityPlayer)) return;
            EntityPlayer player = (EntityPlayer) event.entity;
            int x = MathHelper.floor_double(player.posX);
            int z = MathHelper.floor_double(player.posZ);
            int y = MathHelper.floor_double(player.posY);
            int skylight = player.worldObj.getBlockLightValue(x, y, z);
            NBTTagCompound nbtData = NBTData.getOrCreateNbtData1(player);
            if (nbtData.getBoolean("isNightVision")) {
                if (player.posY < 60 && skylight < 8) {
                    player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 300, 0, true));
                }
                if (!player.worldObj.isDaytime() && nbtData.getBoolean("isNightVisionEnable")) {
                    player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 300, 0, true));
                }
            }

        }
    }

    @SubscribeEvent
    public void checkdrill(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving == null || !(event.entityLiving instanceof EntityPlayer))
            return;
        EntityPlayer player = (EntityPlayer) event.entity;
        for (int i = 0; i < player.inventory.mainInventory.length; i++) {
            //  TODO start Check inventory
            if (player.inventory.mainInventory[i] != null && (player.inventory.mainInventory[i].getItem() == SSPItem.ultDDrill)) {
                ItemStack input = player.inventory.mainInventory[i];
                NBTTagCompound nbtData = NBTData.getOrCreateNbtData(input);
                if (nbtData.getBoolean("create") == true) {
                    Map<Integer, Integer> enchantmentMap4 = new HashMap<Integer, Integer>();
                    ultDDrill drill = (ultDDrill) input.getItem();
                    if (Config.enableefficiency && drill.mode == 0) {
                        enchantmentMap4.put(Integer.valueOf(Enchantment.efficiency.effectId), Integer.valueOf(Config.efficiencylevel));
                        nbtData.setBoolean("create", false);
                        EnchantmentHelper.setEnchantments(enchantmentMap4, input);
                    }


                }
            }
        }
    }

    @SubscribeEvent
    public void check(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving == null || !(event.entityLiving instanceof EntityPlayer))
            return;
        EntityPlayer player = (EntityPlayer) event.entity;
        for (int i = 0; i < 36; i++) {
            if (player.inventory.mainInventory[i] != null && player.inventory.mainInventory[i].getItem() instanceof ic2.core.item.block.ItemElectricBlock) {
                int meta = player.inventory.mainInventory[i].getItemDamage();
                if (meta == 0) {
                    player.inventory.mainInventory[i] = new ItemStack(SSPItem.electricblock, 1, 2);
                }
                if (meta == 7) {
                    player.inventory.mainInventory[i] = new ItemStack(SSPItem.electricblock, 1, 5);
                }
                if (meta == 1) {
                    player.inventory.mainInventory[i] = new ItemStack(SSPItem.electricblock, 1, 3);
                }
                if (meta == 2) {
                    player.inventory.mainInventory[i] = new ItemStack(SSPItem.electricblock, 1, 4);
                }
            }
        }
    }

    @SubscribeEvent
    public void DamageCable(LivingEvent.LivingUpdateEvent event) {

        if (event.entityLiving == null || !(event.entityLiving instanceof EntityPlayer))
            return;
        for (int i = -1; i < 1; i++) {
            for (int j = -1; j < 1; j++) {
                EntityPlayer player = (EntityPlayer) event.entity;
                int x = (int) player.posX;
                int y = (int) player.posY;
                int z = (int) player.posZ;
                Block block = player.worldObj.getBlock(x + i, y, z + j);

                if (Config.damagecable)
                    if (block instanceof BlockCable) {
                        //if(player.inventory.armorInventory[2].getItem() != SuperSolarPanels.quantumBodyarmor && player.inventory.armorInventory[3].getItem() != SuperSolarPanels.quantumHelmet && player.inventory.armorInventory[1].getItem() != SuperSolarPanels.quantumLeggings && player.inventory.armorInventory[0].getItem() != SuperSolarPanels.quantumBoots) {
                        if (!ItemArmorImprovemedQuantum.hasCompleteHazmat(player) && !ItemArmorHazmat.hasCompleteHazmat(player)) {
                            player.attackEntityFrom(SSPDamageSource.current, 1.0F);
                        } else {
                            return;
                        }


                    } else if (block instanceof ic2.core.block.wiring.BlockCable) {
                        int blockmeta = player.worldObj.getBlockMetadata(x + i, y, z + j);
                        if (blockmeta != 0 && blockmeta != 13 && blockmeta != 3 && blockmeta != 6) {
                            if (!ItemArmorImprovemedQuantum.hasCompleteHazmat(player) && !ItemArmorHazmat.hasCompleteHazmat(player)) {
                                player.attackEntityFrom(SSPDamageSource.current, 1.0F);
                            } else {
                                return;
                            }
                        }
                    }
            }
        }
    }

    @SubscribeEvent
    public void Potion(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving == null || !(event.entityLiving instanceof EntityPlayer))
            return;
        EntityPlayer player = (EntityPlayer) event.entity;
        NBTTagCompound nbtData = NBTData.getOrCreateNbtData1(player);
        if (player.inventory.armorInventory[0] != null && player.inventory.armorInventory[0].getItem() == SSPItem.quantumBoots) {
            nbtData.setBoolean("stepHeight", true);
            player.stepHeight = 1.0F;

            nbtData.setBoolean("falldamage", true);
            player.fallDistance = 0;
            if (Config.disableeffect1) {

            } else {

                player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 300));
            }
            if (Config.disableeffect2) {

            } else {
                player.addPotionEffect(new PotionEffect(Potion.jump.id, 300));
            }
            player.addPotionEffect(new PotionEffect(Potion.jump.id, 300, 0, true));
        } else {
            if (nbtData.getBoolean("stepHeight") == true) {
                player.stepHeight = 0F;
                nbtData.setBoolean("stepHeight", false);
            }
            if (nbtData.getBoolean("falldamage") == true) {
                player.fallDistance = 1;
                nbtData.setBoolean("falldamage", false);
            }


        }
    }

    @SubscribeEvent
    public void jump(LivingEvent.LivingJumpEvent event) {
        if (event.entityLiving == null || !(event.entityLiving instanceof EntityPlayer))
            return;
        EntityPlayer player = (EntityPlayer) event.entity;
        if (player.inventory.armorInventory[0] != null && player.inventory.armorInventory[0].getItem() == SSPItem.quantumBoots) {
            player.motionY = +0.8;

        }
    }

    //
    @SubscribeEvent
    public void DamageCable_entity(LivingEvent.LivingUpdateEvent event) {

        if (event.entityLiving == null)
            return;
        for (int i = -1; i < 1; i++) {
            for (int j = -1; j < 1; j++) {
                Entity player = event.entity;
                int x = (int) player.posX;
                int y = (int) player.posY;
                int z = (int) player.posZ;
                Block block = player.worldObj.getBlock(x + i, y, z + j);

                if (Config.damagecable)
                    if (block instanceof BlockCable) {
                        //if(player.inventory.armorInventory[2].getItem() != SuperSolarPanels.quantumBodyarmor && player.inventory.armorInventory[3].getItem() != SuperSolarPanels.quantumHelmet && player.inventory.armorInventory[1].getItem() != SuperSolarPanels.quantumLeggings && player.inventory.armorInventory[0].getItem() != SuperSolarPanels.quantumBoots) {

                        player.attackEntityFrom(SSPDamageSource.current, 1.0F);


                    } else if (block instanceof ic2.core.block.wiring.BlockCable) {
                        int blockmeta = player.worldObj.getBlockMetadata(x + i, y, z + j);
                        if (blockmeta != 0 && blockmeta != 13 && blockmeta != 3 && blockmeta != 6) {

                            player.attackEntityFrom(SSPDamageSource.current, 1.0F);
                        }
                    }
            }
        }
    }
}