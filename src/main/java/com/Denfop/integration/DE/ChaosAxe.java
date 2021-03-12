package com.Denfop.integration.DE;

import com.Denfop.IUCore;
import com.brandon3055.draconicevolution.client.render.IRenderTweak;
import com.brandon3055.draconicevolution.common.handler.BalanceConfigHandler;
import com.brandon3055.draconicevolution.common.utills.IConfigurableItem;
import com.brandon3055.draconicevolution.common.utills.IInventoryTool;
import com.brandon3055.draconicevolution.common.utills.IUpgradableItem;
import com.brandon3055.draconicevolution.common.utills.ItemConfigField;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

import java.util.List;

public class ChaosAxe extends MiningTool implements IInventoryTool, IRenderTweak {
    public ChaosAxe() {
        super(DraconicIntegration.CHAOS);
        setHarvestLevel("axe", 15);
        setUnlocalizedName("ChaosAxe");
        setCapacity((int) (BalanceConfigHandler.draconicToolsBaseStorage * 1.5));
        setMaxExtract(BalanceConfigHandler.draconicToolsMaxTransfer);
        setMaxReceive(BalanceConfigHandler.draconicToolsMaxTransfer);
        this.energyPerOperation = BalanceConfigHandler.draconicToolsEnergyPerAction;
        DraconicIntegration.register(this);
        setCreativeTab(IUCore.tabssp2);
    }

    public List<ItemConfigField> getFields(ItemStack stack, int slot) {
        List<ItemConfigField> list = super.getFields(stack, slot);
        list.add((new ItemConfigField(2, slot, "ToolDigAOE")).setMinMaxAndIncromente(Integer.valueOf(0), Integer.valueOf(IUpgradableItem.EnumUpgrade.DIG_AOE.getUpgradePoints(stack)), Integer.valueOf(1)).readFromItem(stack, Integer.valueOf(0)).setModifier("AOE"));
        list.add((new ItemConfigField(2, slot, "ToolDigDepth")).setMinMaxAndIncromente(Integer.valueOf(1), Integer.valueOf(IUpgradableItem.EnumUpgrade.DIG_DEPTH.getUpgradePoints(stack)), Integer.valueOf(1)).readFromItem(stack, Integer.valueOf(1)));
        list.add((new ItemConfigField(6, slot, "AxeTreeMode")).readFromItem(stack, Boolean.valueOf(false)));
        return list;
    }

    public boolean hasEffect(ItemStack par1ItemStack, int pass) {
        return true;
    }

    public String getInventoryName() {
        return StatCollector.translateToLocal("info.de.toolInventoryEnch.txt");
    }

    public int getInventorySlots() {
        return 0;
    }

    public boolean isEnchantValid(Enchantment enchant) {
        return (enchant.type == EnumEnchantmentType.digger);
    }

    public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player) {
        if (IConfigurableItem.ProfileHelper.getBoolean(stack, "AxeTreeMode", false) && isTree(player.worldObj, x, y, z)) {
            trimLeavs(x, y, z, player, player.worldObj, stack);
            for (int i = 0; i < 9; i++)
                player.worldObj.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(player.worldObj.getBlock(x, y, z)) + (player.worldObj.getBlockMetadata(x, y, z) << 12));
            chopTree(x, y, z, player, player.worldObj, stack);
            return false;
        }
        return super.onBlockStartBreak(stack, x, y, z, player);
    }

    public int getUpgradeCap(ItemStack itemstack) {
        return BalanceConfigHandler.draconicToolsMaxUpgrades;
    }

    public int getMaxTier(ItemStack itemstack) {
        return 3;
    }

    public List<String> getUpgradeStats(ItemStack stack) {
        return super.getUpgradeStats(stack);
    }

    public int getCapacity(ItemStack stack) {
        int points = IUpgradableItem.EnumUpgrade.RF_CAPACITY.getUpgradePoints(stack);
        return (int) (BalanceConfigHandler.draconicToolsBaseStorage * 1.5 + points * BalanceConfigHandler.draconicToolsStoragePerUpgrade);
    }

    public int getMaxUpgradePoints(int upgradeIndex) {
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.RF_CAPACITY.index)
            return BalanceConfigHandler.draconicToolsMaxCapacityUpgradePoints;
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.DIG_AOE.index)
            return 4;
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.DIG_DEPTH.index)
            return 7;
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.DIG_SPEED.index)
            return 32;
        return BalanceConfigHandler.draconicToolsMaxUpgradePoints;
    }

    public int getBaseUpgradePoints(int upgradeIndex) {
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.DIG_AOE.index)
            return 2;
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.DIG_DEPTH.index)
            return 1;
        if (upgradeIndex == IUpgradableItem.EnumUpgrade.DIG_SPEED.index)
            return 5;
        return 0;
    }

    private boolean isTree(World world, int X, int Y, int Z) {
        Block wood = world.getBlock(X, Y, Z);
        if (wood == null || !wood.isWood(world, X, Y, Z))
            return false;
        int top = Y;
        for (int y = Y; y <= Y + 50; y++) {
            if (!world.getBlock(X, y, Z).isWood(world, X, y, Z) && !world.getBlock(X, y, Z).isLeaves(world, X, y, Z)) {
                top += y;
                break;
            }
        }
        int leaves = 0;
        for (int xPos = X - 1; xPos <= X + 1; xPos++) {
            for (int yPos = Y; yPos <= top; yPos++) {
                for (int zPos = Z - 1; zPos <= Z + 1; zPos++) {
                    if (world.getBlock(xPos, yPos, zPos).isLeaves(world, xPos, yPos, zPos))
                        leaves++;
                }
            }
        }
        return leaves >= 3;
    }

    void chopTree(int X, int Y, int Z, EntityPlayer player, World world, ItemStack stack) {
        for (int xPos = X - 1; xPos <= X + 1; xPos++) {
            for (int yPos = Y; yPos <= Y + 1; yPos++) {
                for (int zPos = Z - 1; zPos <= Z + 1; zPos++) {
                    Block block = world.getBlock(xPos, yPos, zPos);
                    int meta = world.getBlockMetadata(xPos, yPos, zPos);
                    if (block.isWood(world, xPos, yPos, zPos)) {
                        world.setBlockToAir(xPos, yPos, zPos);
                        if (!player.capabilities.isCreativeMode) {
                            if (block.removedByPlayer(world, player, xPos, yPos, zPos, false))
                                block.onBlockDestroyedByPlayer(world, xPos, yPos, zPos, meta);
                            block.harvestBlock(world, player, xPos, yPos, zPos, meta);
                            block.onBlockHarvested(world, xPos, yPos, zPos, meta, player);
                            onBlockDestroyed(stack, world, block, xPos, yPos, zPos, player);
                        }
                        chopTree(xPos, yPos, zPos, player, world, stack);
                    }
                }
            }
        }
    }

    void trimLeavs(int X, int Y, int Z, EntityPlayer player, World world, ItemStack stack) {
        scedualUpdates(X, Y, Z, player, world, stack);
    }

    void scedualUpdates(int X, int Y, int Z, EntityPlayer player, World world, ItemStack stack) {
        for (int xPos = X - 15; xPos <= X + 15; xPos++) {
            for (int yPos = Y; yPos <= Y + 50; yPos++) {
                for (int zPos = Z - 15; zPos <= Z + 15; zPos++) {
                    Block block = world.getBlock(xPos, yPos, zPos);
                    if (block.isLeaves(world, xPos, yPos, zPos))
                        world.scheduleBlockUpdate(xPos, yPos, zPos, block, 2 + world.rand.nextInt(10));
                }
            }
        }
    }

    public List<IUpgradableItem.EnumUpgrade> getUpgrades(ItemStack itemstack) {
        List<IUpgradableItem.EnumUpgrade> list = super.getUpgrades(itemstack);
        list.add(IUpgradableItem.EnumUpgrade.DIG_SPEED);
        list.add(IUpgradableItem.EnumUpgrade.DIG_DEPTH);
        list.add(IUpgradableItem.EnumUpgrade.DIG_AOE);
        list.add(IUpgradableItem.EnumUpgrade.RF_CAPACITY);
        return list;
    }

    public void tweakRender(IItemRenderer.ItemRenderType type) {
        GL11.glTranslated(0.34D, 0.69D, 0.1D);
        GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(140.0F, 0.0F, -1.0F, 0.0F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glScaled(0.7D, 0.7D, 0.7D);
        if (type == IItemRenderer.ItemRenderType.INVENTORY) {
            GL11.glScalef(11.0F, 11.0F, 11.0F);
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslated(-1.3D, 0.0D, -0.45D);
        } else if (type == IItemRenderer.ItemRenderType.ENTITY) {
            GL11.glRotatef(90.5F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslated(0.0D, 0.0D, -0.9D);
        }
    }
}
