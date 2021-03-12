package com.Denfop.integration.DE;

import com.brandon3055.brandonscore.common.utills.InfoHelper;
import com.brandon3055.brandonscore.common.utills.ItemNBTHelper;
import com.brandon3055.brandonscore.common.utills.Utills;
import com.brandon3055.draconicevolution.common.handler.ConfigHandler;
import com.brandon3055.draconicevolution.common.items.tools.baseclasses.ToolHandler;
import com.brandon3055.draconicevolution.common.utills.IConfigurableItem;
import com.brandon3055.draconicevolution.common.utills.IUpgradableItem;
import com.brandon3055.draconicevolution.common.utills.ItemConfigField;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.server.S18PacketEntityTeleport;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.util.*;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.world.BlockEvent;

import java.util.*;

public abstract class MiningTool extends ToolBase implements IUpgradableItem {
    public MiningTool(Item.ToolMaterial material) {
        super(0.0F, material, null);
    }

    public Map<Block, Integer> getObliterationList(ItemStack stack) {
        Map<Block, Integer> blockMap = new HashMap<Block, Integer>();
        NBTTagCompound compound = ItemNBTHelper.getCompound(stack);
        if (compound.hasNoTags())
            return blockMap;
        for (int i = 0; i < 9; i++) {
            NBTTagCompound tag = new NBTTagCompound();
            if (compound.hasKey("Item" + i))
                tag = compound.getCompoundTag("Item" + i);
            if (!tag.hasNoTags()) {
                ItemStack stack1 = ItemStack.loadItemStackFromNBT(tag);
                if (stack1 != null && stack1.getItem() instanceof net.minecraft.item.ItemBlock)
                    blockMap.put(Block.getBlockFromItem(stack1.getItem()), Integer.valueOf(stack1.getItemDamage()));
            }
        }
        return blockMap;
    }

    public boolean hasEffect(ItemStack par1ItemStack, int pass) {
        return true;
    }

    public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player) {
        int radius = IConfigurableItem.ProfileHelper.getInteger(stack, "ToolDigAOE", 0);
        int depth = IConfigurableItem.ProfileHelper.getInteger(stack, "ToolDigDepth", 1) - 1;
        return (getEnergyStored(stack) >= this.energyPerOperation && radius > 0) ? breakAOEBlocks(stack, x, y, z, radius, depth, player) : super.onBlockStartBreak(stack, x, y, z, player);
    }

    public boolean onBlockDestroyed(ItemStack stack, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_) {
        if (IConfigurableItem.ProfileHelper.getInteger(stack, "ToolDigAOE", 0) == 0)
            extractEnergy(stack, this.energyPerOperation, false);
        return super.onBlockDestroyed(stack, p_150894_2_, p_150894_3_, p_150894_4_, p_150894_5_, p_150894_6_, p_150894_7_);
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        ToolHandler.updateGhostBlocks(player, world);
        return super.onItemRightClick(stack, world, player);
    }

    public boolean breakAOEBlocks(ItemStack stack, int x, int y, int z, int breakRadius, int breakDepth, EntityPlayer player) {
        Map<Block, Integer> blockMap = IConfigurableItem.ProfileHelper.getBoolean(stack, "ToolVoidJunk", false) ? getObliterationList(stack) : new HashMap<Block, Integer>();
        Block block = player.worldObj.getBlock(x, y, z);
        int meta = player.worldObj.getBlockMetadata(x, y, z);
        boolean effective = false;
        if (block != null)
            for (String s : getToolClasses(stack)) {
                if (block.isToolEffective(s, meta) || func_150893_a(stack, block) > 1.0F)
                    effective = true;
            }
        if (!effective)
            return true;
        float refStrength = ForgeHooks.blockStrength(block, player, player.worldObj, x, y, z);
        MovingObjectPosition mop = ToolHandler.raytraceFromEntity(player.worldObj, player, 4.5D);
        if (mop == null) {
            ToolHandler.updateGhostBlocks(player, player.worldObj);
            return true;
        }
        int sideHit = mop.sideHit;
        int xMax = breakRadius;
        int xMin = breakRadius;
        int yMax = breakRadius;
        int yMin = breakRadius;
        int zMax = breakRadius;
        int zMin = breakRadius;
        int yOffset = 0;
        switch (sideHit) {
            case 0:
                yMax = breakDepth;
                yMin = 0;
                zMax = breakRadius;
                break;
            case 1:
                yMin = breakDepth;
                yMax = 0;
                zMax = breakRadius;
                break;
            case 2:
                xMax = breakRadius;
                zMin = 0;
                zMax = breakDepth;
                yOffset = breakRadius - 1;
                break;
            case 3:
                xMax = breakRadius;
                zMax = 0;
                zMin = breakDepth;
                yOffset = breakRadius - 1;
                break;
            case 4:
                xMax = breakDepth;
                xMin = 0;
                zMax = breakRadius;
                yOffset = breakRadius - 1;
                break;
            case 5:
                xMin = breakDepth;
                xMax = 0;
                zMax = breakRadius;
                yOffset = breakRadius - 1;
                break;
        }
        if (IConfigurableItem.ProfileHelper.getBoolean(stack, "BaseSafeAOE", false))
            for (int i = x - xMin; i <= x + xMax; i++) {
                for (int yPos = y + yOffset - yMin; yPos <= y + yOffset + yMax; yPos++) {
                    for (int zPos = z - zMin; zPos <= z + zMax; zPos++) {
                        if (player.worldObj.getTileEntity(i, yPos, zPos) != null) {
                            if (player.worldObj.isRemote) {
                                player.addChatComponentMessage(new ChatComponentTranslation("msg.de.baseSafeAOW.txt", new Object[0]));
                            } else {
                                ((EntityPlayerMP) player).playerNetServerHandler.sendPacket(new S23PacketBlockChange(x, y, z, player.worldObj));
                            }
                            return true;
                        }
                    }
                }
            }
        for (int xPos = x - xMin; xPos <= x + xMax; xPos++) {
            for (int yPos = y + yOffset - yMin; yPos <= y + yOffset + yMax; yPos++) {
                for (int zPos = z - zMin; zPos <= z + zMax; zPos++)
                    breakExtraBlock(stack, player.worldObj, xPos, yPos, zPos, breakRadius * (breakDepth / 2 + 1), player, refStrength, (Math.abs(x - xPos) <= 1 && Math.abs(y - yPos) <= 1 && Math.abs(z - zPos) <= 1), blockMap);
            }
        }
        List<EntityItem> items = player.worldObj.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox((x - xMin), (y + yOffset - yMin), (z - zMin), (x + xMax + 1), (y + yOffset + yMax + 1), (z + zMax + 1)));
        for (EntityItem item : items) {
            if (!player.worldObj.isRemote) {
                item.setLocationAndAngles(player.posX, player.posY, player.posZ, 0.0F, 0.0F);
                ((EntityPlayerMP) player).playerNetServerHandler.sendPacket(new S18PacketEntityTeleport(item));
                item.delayBeforeCanPickup = 0;
                if (ConfigHandler.rapidlyDespawnMinedItems)
                    item.lifespan = 100;
            }
        }
        return true;
    }

    protected void breakExtraBlock(ItemStack stack, World world, int x, int y, int z, int totalSize, EntityPlayer player, float refStrength, boolean breakSound, Map<Block, Integer> blockMap) {
        if (world.isAirBlock(x, y, z))
            return;
        Block block = world.getBlock(x, y, z);
        if (block.getMaterial() instanceof net.minecraft.block.material.MaterialLiquid || (block.getBlockHardness(world, x, y, x) == -1.0F && !player.capabilities.isCreativeMode))
            return;
        int meta = world.getBlockMetadata(x, y, z);
        boolean effective = false;
        for (String s : getToolClasses(stack)) {
            if (block.isToolEffective(s, meta) || func_150893_a(stack, block) > 1.0F)
                effective = true;
        }
        if (!effective)
            return;
        float strength = ForgeHooks.blockStrength(block, player, world, x, y, z);
        if (!player.canHarvestBlock(block) || !ForgeHooks.canHarvestBlock(block, player, meta) || (refStrength / strength > 10.0F && !player.capabilities.isCreativeMode))
            return;
        if (!world.isRemote) {
            BlockEvent.BreakEvent event = ForgeHooks.onBlockBreakEvent(world, world.getWorldInfo().getGameType(), (EntityPlayerMP) player, x, y, z);
            if (event.isCanceled()) {
                ((EntityPlayerMP) player).playerNetServerHandler.sendPacket(new S23PacketBlockChange(x, y, z, world));
                return;
            }
        }
        int scaledPower = this.energyPerOperation + totalSize * this.energyPerOperation / 10;
        if (player.capabilities.isCreativeMode || (blockMap.containsKey(block) && blockMap.get(block).intValue() == meta)) {
            block.onBlockHarvested(world, x, y, z, meta, player);
            if (block.removedByPlayer(world, player, x, y, z, false))
                block.onBlockDestroyedByPlayer(world, x, y, z, meta);
            if (!world.isRemote)
                ((EntityPlayerMP) player).playerNetServerHandler.sendPacket(new S23PacketBlockChange(x, y, z, world));
            if (blockMap.containsKey(block) && blockMap.get(block).intValue() == meta)
                extractEnergy(stack, scaledPower, false);
            if (breakSound)
                world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(block) + (meta << 12));
            return;
        }
        extractEnergy(stack, scaledPower, false);
        if (!world.isRemote) {
            block.onBlockHarvested(world, x, y, z, meta, player);
            if (block.removedByPlayer(world, player, x, y, z, true)) {
                block.onBlockDestroyedByPlayer(world, x, y, z, meta);
                block.harvestBlock(world, player, x, y, z, meta);
                player.addExhaustion(-0.025F);
                if (block.getExpDrop(world, meta, EnchantmentHelper.getFortuneModifier(player)) > 0)
                    player.addExperience(block.getExpDrop(world, meta, EnchantmentHelper.getFortuneModifier(player)));
            }
            EntityPlayerMP mpPlayer = (EntityPlayerMP) player;
            mpPlayer.playerNetServerHandler.sendPacket(new S23PacketBlockChange(x, y, z, world));
        } else {
            if (breakSound)
                world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(block) + (meta << 12));
            if (block.removedByPlayer(world, player, x, y, z, true))
                block.onBlockDestroyedByPlayer(world, x, y, z, meta);
            Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C07PacketPlayerDigging(2, x, y, z, (Minecraft.getMinecraft()).objectMouseOver.sideHit));
        }
    }

    public List<IUpgradableItem.EnumUpgrade> getUpgrades(ItemStack itemstack) {
        return new ArrayList<IUpgradableItem.EnumUpgrade>() {

        };
    }

    public int getMaxUpgradePoints(int upgradeIndex, ItemStack stack) {
        return getMaxUpgradePoints(upgradeIndex);
    }

    public float getEfficiency(ItemStack stack) {
        int i = IUpgradableItem.EnumUpgrade.DIG_SPEED.getUpgradePoints(stack);
        if (i == 0)
            return super.getEfficiency(stack);
        return i * 3.0F;
    }

    public List<String> getUpgradeStats(ItemStack stack) {
        List<String> strings = new ArrayList<String>();
        int digaoe = 0;
        int depth = 0;
        int attackaoe = 0;
        for (ItemConfigField field : getFields(stack, 0)) {
            if (field.name.equals("ToolDigAOE")) {
                digaoe = 1 + ((Integer) field.max).intValue() * 2;
                continue;
            }
            if (field.name.equals("ToolDigDepth")) {
                depth = ((Integer) field.max).intValue();
                continue;
            }
            if (field.name.equals("WeaponAttackAOE"))
                attackaoe = 1 + ((Integer) field.max).intValue() * 2;
        }
        strings.add(InfoHelper.ITC() + StatCollector.translateToLocal("gui.de.RFCapacity.txt") + ": " + InfoHelper.HITC() + Utills.formatNumber(getMaxEnergyStored(stack)));
        strings.add(InfoHelper.ITC() + StatCollector.translateToLocal("gui.de.max.txt") + " " + StatCollector.translateToLocal("gui.de.DigAOE.txt") + ": " + InfoHelper.HITC() + digaoe + "x" + digaoe);
        if (depth > 0)
            strings.add(InfoHelper.ITC() + StatCollector.translateToLocal("gui.de.max.txt") + " " + StatCollector.translateToLocal("gui.de.DigDepth.txt") + ": " + InfoHelper.HITC() + depth);
        strings.add(InfoHelper.ITC() + StatCollector.translateToLocal("gui.de.max.txt") + " " + StatCollector.translateToLocal("gui.de.DigSpeed.txt") + ": " + InfoHelper.HITC() + getEfficiency(stack));
        if (attackaoe > 0)
            strings.add(InfoHelper.ITC() + StatCollector.translateToLocal("gui.de.max.txt") + " " + StatCollector.translateToLocal("gui.de.AttackAOE.txt") + ": " + InfoHelper.HITC() + attackaoe + "x" + attackaoe);
        return strings;
    }
}
