package com.Denfop.events;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.Denfop.Config;
import com.Denfop.IUItem;
import com.Denfop.IUCore;
import com.Denfop.DamageSource.SSPDamageSource;
import com.Denfop.api.IPanel;
import com.Denfop.api.module.*;
import com.Denfop.block.Base.BlockIC2Fluid;
import com.Denfop.block.Base.BlockSSP;
import com.Denfop.block.Base.BlockSSPSolarPanel;
import com.Denfop.block.Base.BlocksItems;
import com.Denfop.block.cable.BlockCable;
import com.Denfop.integration.Avaritia.ItemAvaritiaSolarPanel;
import com.Denfop.integration.Avaritia.blockAvaritiaSolarPanel;
import com.Denfop.integration.Botania.ItemBotSolarPanel;
import com.Denfop.integration.Botania.blockBotSolarPanel;
import com.Denfop.integration.DE.ItemDESolarPanel;
import com.Denfop.integration.DE.blockDESolarPanel;
import com.Denfop.item.Modules.AdditionModule;
import com.Denfop.item.Modules.*;
import com.Denfop.item.armour.ItemArmorImprovemedQuantum;
import com.Denfop.item.base.ItemSSPSolarPanel;
import com.Denfop.item.energy.AdvancedMultiTool;
import com.Denfop.item.energy.EnergyAxe;
import com.Denfop.item.energy.EnergyPickaxe;
import com.Denfop.item.energy.EnergyShovel;
import com.Denfop.tiles.base.TileEntityCable;
import com.Denfop.tiles.base.TileEntitySolarPanel;
import com.Denfop.utils.NBTData;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.energy.EnergyNet;
import ic2.api.energy.NodeStats;
import ic2.api.energy.event.EnergyTileEvent;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.core.IC2;
import ic2.core.Ic2Items;
import ic2.core.WorldData;
import ic2.core.block.BlockMetaData;
import ic2.core.item.armor.ItemArmorHazmat;
import ic2.core.item.tool.ItemDrill;
import ic2.core.util.LogCategory;
import ic2.core.util.Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEventData;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.ChunkWatchEvent;
import net.minecraftforge.event.world.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.entity.item.*;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class SSPEventHandler {

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
	public void modulecheck(LivingEvent.LivingUpdateEvent event) {

		if (event.entityLiving == null || !(event.entityLiving instanceof EntityPlayer))
			return;
		EntityPlayer player = (EntityPlayer) event.entity;
		for(int i = 0;i<36;i++) {
			if(player.inventory.mainInventory[i] == null)
				return;
			
			if(player.inventory.mainInventory[i].getItem() instanceof ModuleTypePanel) {
				int meta = player.inventory.mainInventory[i].getItemDamage();
				TileEntitySolarPanel tile = (TileEntitySolarPanel) BlockSSPSolarPanel.getBlockEntity(meta);
				IModulPanel.setData(player.inventory.mainInventory[i], tile);
				
			}
		}
	}

	@SubscribeEvent
	public void FlyUpdate(LivingEvent.LivingUpdateEvent event) {

		if (event.entityLiving == null || !(event.entityLiving instanceof EntityPlayer))
			return;
		EntityPlayer player = (EntityPlayer) event.entity;

		if (event.entityLiving instanceof EntityPlayer) {
			if (player.capabilities.isCreativeMode == false) {

				NBTTagCompound nbtData = NBTData.getOrCreateNbtData1(player);
				boolean fly = nbtData.getBoolean("isFlyActive");
				if (!player.capabilities.isCreativeMode) {
					if (player.inventory.armorInventory[2] != null) {
						if (player.inventory.armorInventory[2].getItem() == IUItem.quantumBodyarmor) {
							NBTTagCompound nbtData1 = NBTData.getOrCreateNbtData(player.inventory.armorInventory[2]);
							boolean jetpack = nbtData1.getBoolean("jetpack");
							if (jetpack == false) {
								if(nbtData.getBoolean("isFlyActive")) {
								player.capabilities.isFlying = false;
								player.capabilities.allowFlying = false;
								nbtData1.setBoolean("isFlyActive", false);
								nbtData.setBoolean("isFlyActive", false);
								if (player.getEntityWorld().isRemote)
									player.capabilities.setFlySpeed((float) 0.05);
								}
							} else {
								player.capabilities.isFlying = true;
								player.capabilities.allowFlying = true;
								nbtData.setBoolean("isFlyActive", true);
								nbtData1.setBoolean("isFlyActive", true);
								if (player.getEntityWorld().isRemote)
									player.capabilities.setFlySpeed((float) 0.2);
							}
						} else if (player.inventory.armorInventory[2].getItem() != IUItem.quantumBodyarmor
								&& player.inventory.armorInventory[2] != null) {
							if (nbtData.getBoolean("isFlyActive") == true) {
								player.capabilities.isFlying = false;
								player.capabilities.allowFlying = false;
								nbtData.setBoolean("isFlyActive", false);
								if (player.getEntityWorld().isRemote)
									player.capabilities.setFlySpeed((float) 0.05);
							}
						}
					} else {
						if (nbtData.getBoolean("isFlyActive") == true) {
							player.capabilities.isFlying = false;
							player.capabilities.allowFlying = false;
							nbtData.setBoolean("isFlyActive", false);
							if (player.getEntityWorld().isRemote)
								player.capabilities.setFlySpeed((float) 0.05);
						}
					}
				} else {
					if (nbtData.getBoolean("isFlyActive") == true) {
						player.capabilities.isFlying = false;
						player.capabilities.allowFlying = false;
						nbtData.setBoolean("isFlyActive", false);
						if (player.getEntityWorld().isRemote)
							player.capabilities.setFlySpeed((float) 0.05);
					}
				}
			}

		}

	}
	
	@SubscribeEvent
	public void UpdateHelmet(LivingEvent.LivingUpdateEvent event) {

		if (event.entityLiving == null || !(event.entityLiving instanceof EntityPlayer))
			return;
		EntityPlayer player = (EntityPlayer) event.entity;
		NBTTagCompound nbtData = NBTData.getOrCreateNbtData1(player);
		if (player.inventory.armorInventory[3] != null) {
			if (player.inventory.armorInventory[3].getItem() == IUItem.quantumHelmet) {
				nbtData.setBoolean("isNightVision", true);
			} else if (player.inventory.armorInventory[3].getItem() == Ic2Items.nanoHelmet.getItem()) {
				nbtData.setBoolean("isNightVision", true);
			} else if (player.inventory.armorInventory[3].getItem() == Ic2Items.quantumHelmet.getItem()) {
				nbtData.setBoolean("isNightVision", true);
			} else if (player.inventory.armorInventory[3].getItem() == IUItem.advancedSolarHelmet) {
				nbtData.setBoolean("isNightVision", true);
				nbtData.setBoolean("isNightVisionEnable", true);
			} else if (player.inventory.armorInventory[3].getItem() == IUItem.hybridSolarHelmet) {
				nbtData.setBoolean("isNightVision", true);
				nbtData.setBoolean("isNightVisionEnable", true);
			} else if (player.inventory.armorInventory[3].getItem() == IUItem.spectralSolarHelmet) {
				nbtData.setBoolean("isNightVision", true);
				nbtData.setBoolean("isNightVisionEnable", true);
			} else if (player.inventory.armorInventory[3].getItem() == IUItem.singularSolarHelmet) {
				nbtData.setBoolean("isNightVision", true);
				nbtData.setBoolean("isNightVisionEnable", true);
			} else if (player.inventory.armorInventory[3].getItem() == Ic2Items.nightvisionGoggles.getItem()) {
				nbtData.setBoolean("isNightVision", true);
			} else if (player.inventory.armorInventory[3].getItem() == IUItem.ultimateSolarHelmet) {
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
			if (event.entityLiving == null || !(event.entityLiving instanceof EntityPlayer))
				return;
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

	//
	@SubscribeEvent
	public void checkinstruments(LivingEvent.LivingUpdateEvent event) {
		if (event.entityLiving == null || !(event.entityLiving instanceof EntityPlayer))
			return;
		EntityPlayer player = (EntityPlayer) event.entity;
		for (int i = 0; i < player.inventory.mainInventory.length; i++) {
			// TODO start Check inventory
			if (player.inventory.mainInventory[i] != null
					&& (player.inventory.mainInventory[i].getItem() instanceof EnergyAxe
							|| player.inventory.mainInventory[i].getItem() instanceof EnergyPickaxe
							|| player.inventory.mainInventory[i].getItem() instanceof EnergyShovel)) {
				ItemStack input = player.inventory.mainInventory[i];
				NBTTagCompound nbtData = NBTData.getOrCreateNbtData(input);
				if (nbtData.getBoolean("create")) {
					Map<Integer, Integer> enchantmentMap4 = new HashMap<Integer, Integer>();

					if (input.getItem() instanceof EnergyAxe) {
						EnergyAxe drill = (EnergyAxe) input.getItem();
						if (Config.enableefficiency) {
							enchantmentMap4.put(Integer.valueOf(Enchantment.efficiency.effectId),
									Integer.valueOf(drill.efficienty));
							enchantmentMap4.put(Integer.valueOf(Enchantment.fortune.effectId),
									Integer.valueOf(drill.lucky));
							nbtData.setBoolean("create", false);
							EnchantmentHelper.setEnchantments(enchantmentMap4, input);
						}
					}
					else if (input.getItem() instanceof EnergyPickaxe) {
						EnergyPickaxe drill = (EnergyPickaxe) input.getItem();
						if (Config.enableefficiency) {
							enchantmentMap4.put(Integer.valueOf(Enchantment.efficiency.effectId),
									Integer.valueOf(drill.efficienty));
							enchantmentMap4.put(Integer.valueOf(Enchantment.fortune.effectId),
									Integer.valueOf(drill.lucky));
							nbtData.setBoolean("create", false);
							EnchantmentHelper.setEnchantments(enchantmentMap4, input);
						}
					}
					else  if (input.getItem() instanceof EnergyShovel) {
						EnergyShovel drill = (EnergyShovel) input.getItem();
						if (Config.enableefficiency) {
							enchantmentMap4.put(Integer.valueOf(Enchantment.efficiency.effectId),
									Integer.valueOf(drill.efficienty));
							enchantmentMap4.put(Integer.valueOf(Enchantment.fortune.effectId),
									Integer.valueOf(drill.lucky));
							nbtData.setBoolean("create", false);
							EnchantmentHelper.setEnchantments(enchantmentMap4, input);
						}
					}
				}
			}
		}
	}

	//
	@SubscribeEvent
	public void checkdrill(LivingEvent.LivingUpdateEvent event) {
		if (event.entityLiving == null || !(event.entityLiving instanceof EntityPlayer))
			return;
		EntityPlayer player = (EntityPlayer) event.entity;
		for (int i = 0; i < player.inventory.mainInventory.length; i++) {
			// TODO start Check inventory
			if (player.inventory.mainInventory[i] != null
					&& (player.inventory.mainInventory[i].getItem() == IUItem.ultDDrill)) {
				ItemStack input = player.inventory.mainInventory[i];
				NBTTagCompound nbtData = NBTData.getOrCreateNbtData(input);
				if (nbtData.getBoolean("create")) {
					Map<Integer, Integer> enchantmentMap4 = new HashMap<Integer, Integer>();
					AdvancedMultiTool drill = (AdvancedMultiTool) input.getItem();
					if (Config.enableefficiency) {
						enchantmentMap4.put(Integer.valueOf(Enchantment.efficiency.effectId),
								Integer.valueOf(Config.efficiencylevel));
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
			if (player.inventory.mainInventory[i] != null
					&& player.inventory.mainInventory[i].getItem() instanceof ic2.core.item.block.ItemElectricBlock) {
				int meta = player.inventory.mainInventory[i].getItemDamage();
				if (meta == 0) {
					player.inventory.mainInventory[i] = new ItemStack(IUItem.electricblock, 1, 2);
				}
				if (meta == 7) {
					player.inventory.mainInventory[i] = new ItemStack(IUItem.electricblock, 1, 5);
				}
				if (meta == 1) {
					player.inventory.mainInventory[i] = new ItemStack(IUItem.electricblock, 1, 3);
				}
				if (meta == 2) {
					player.inventory.mainInventory[i] = new ItemStack(IUItem.electricblock, 1, 4);
				}
			}
			if (player.inventory.mainInventory[i] != null
					&& player.inventory.mainInventory[i].getItem() instanceof ic2.core.item.block.ItemChargepadBlock) {
				int meta = player.inventory.mainInventory[i].getItemDamage();

				player.inventory.mainInventory[i] = new ItemStack(IUItem.Chargepadelectricblock, 1, meta + 2);

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
					 if (block instanceof ic2.core.block.wiring.BlockCable) {
						int blockmeta = player.worldObj.getBlockMetadata(x + i, y, z + j);
						if (blockmeta != 0 && blockmeta != 13 && blockmeta != 3 && blockmeta != 6&& blockmeta != 9) {
							if (!ItemArmorImprovemedQuantum.hasCompleteHazmat(player)
									&& !ItemArmorHazmat.hasCompleteHazmat(player)) {
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
		if (player.inventory.armorInventory[0] != null
				&& player.inventory.armorInventory[0].getItem() == IUItem.quantumBoots) {
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
		if (player.inventory.armorInventory[0] != null
				&& player.inventory.armorInventory[0].getItem() == IUItem.quantumBoots) {
			player.motionY = +0.8;

		}
	}

	//
	
}