package com.Denfop.item.energy;


import com.Denfop.Config;
import com.Denfop.IUCore;
import com.Denfop.proxy.CommonProxy;
import com.Denfop.utils.Helpers;
import com.Denfop.utils.NBTData;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import ic2.core.IC2;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.*;

public class ultDDrill extends ItemTool implements IElectricItem {
    public static final Set<Block> mineableBlocks = Sets.newHashSet(Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.gold_block,
            Blocks.gold_ore, Blocks.diamond_ore, Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail,
            Blocks.detector_rail, Blocks.golden_rail, Blocks.activator_rail, Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel, Blocks.snow_layer, Blocks.snow, Blocks.clay, Blocks.farmland, Blocks.soul_sand, Blocks.mycelium);

    private static final Set<Material> materials = Sets.newHashSet(Material.iron, Material.anvil, Material.rock, Material.glass, Material.ice, Material.packedIce,
            Material.grass, Material.ground, Material.sand, Material.snow, Material.craftedSnow, Material.clay);

    private static final Set<String> toolType = ImmutableSet.of("pickaxe", "shovel");
    public int soundTicker;
    public int damageVsEntity = 1;
    public int mode;
    private final float bigHolePower = Config.bigHolePower;
    private final float normalPower = Config.effPower;
    private final float lowPower = Config.lowPower;
    private final float ultraLowPower = Config.ultraLowPower;
    private final int maxCharge = Config.ultdrillmaxCharge;
    private final int tier = Config.ultdrilltier;
    private final int maxWorkRange = 1;
    private final int energyPerOperation = Config.energyPerOperation;
    private final int energyPerLowOperation = Config.energyPerLowOperation;
    private final int energyPerbigHolePowerOperation = Config.energyPerbigHolePowerOperation;
    private final int energyPerultraLowPowerOperation = Config.energyPerultraLowPowerOperation;
    private final int transferLimit = Config.ultdrilltransferLimit;

    public ultDDrill(Item.ToolMaterial toolMaterial) {
        super(0.0F, toolMaterial, new HashSet());
        setMaxDamage(27);
        this.efficiencyOnProperMaterial = this.normalPower;
        setCreativeTab(IUCore.tabssp2);
    }

    public static int readToolMode(ItemStack itemstack) {
        NBTTagCompound nbt = NBTData.getOrCreateNbtData(itemstack);
        int toolMode = nbt.getInteger("toolMode");

        if (toolMode < 0 || toolMode > 3)
            toolMode = 0;
        return toolMode;
    }

    public static MovingObjectPosition raytraceFromEntity(World world, Entity player, boolean par3, double range) {
        float pitch = player.rotationPitch;
        float yaw = player.rotationYaw;
        double x = player.posX;
        double y = player.posY;
        double z = player.posZ;
        if (!world.isRemote && player instanceof EntityPlayer)
            y++;
        Vec3 vec3 = Vec3.createVectorHelper(x, y, z);
        float f3 = MathHelper.cos(-yaw * 0.017453292F - 3.1415927F);
        float f4 = MathHelper.sin(-yaw * 0.017453292F - 3.1415927F);
        float f5 = -MathHelper.cos(-pitch * 0.017453292F);
        float f6 = MathHelper.sin(-pitch * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        if (player instanceof EntityPlayerMP)
            range = ((EntityPlayerMP) player).theItemInWorldManager.getBlockReachDistance();
        Vec3 vec31 = vec3.addVector(range * f7, range * f6, range * f8);
        return world.func_147447_a(vec3, vec31, par3, !par3, par3);
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase damagee, EntityLivingBase damager) {
        return true;
    }

    public void init() {
    }

    public boolean canProvideEnergy(ItemStack itemStack) {
        return false;
    }

    public double getMaxCharge(ItemStack itemStack) {
        return this.maxCharge;
    }

    public int getTier(ItemStack itemStack) {
        return this.tier;
    }

    public double getTransferLimit(ItemStack itemStack) {
        return this.transferLimit;
    }

    public Set<String> getToolClasses(ItemStack stack) {
        return toolType;
    }

    public boolean canHarvestBlock(Block block, ItemStack stack) {
        return (Items.diamond_pickaxe.canHarvestBlock(block, stack) || Items.diamond_pickaxe.func_150893_a(stack, block) > 1.0F || Items.diamond_shovel.canHarvestBlock(block, stack) || Items.diamond_shovel.func_150893_a(stack, block) > 1.0F || mineableBlocks.contains(block));
    }

    public float getDigSpeed(ItemStack tool, Block block, int meta) {
        return !ElectricItem.manager.canUse(tool, this.energyPerOperation) ? 1.0F : (canHarvestBlock(block, tool) ? this.efficiencyOnProperMaterial : 1.0F);
    }

    public int getHarvestLevel(ItemStack stack, String toolType) {
        return (!toolType.equals("pickaxe") && !toolType.equals("shovel")) ? super.getHarvestLevel(stack, toolType) : this.toolMaterial.getHarvestLevel();
    }

    public boolean hitEntity(ItemStack stack, EntityLiving entity1, EntityLiving entity2) {
        return true;
    }

    public int getDamageVsEntity(Entity entity) {
        return this.damageVsEntity;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        this.itemIcon = register.registerIcon("supersolarpanel:ultDDrill");
    }

    public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player) {
        if (readToolMode(stack) == 1 || readToolMode(stack) == 0)
            return false;
        if (readToolMode(stack) == 2) {
            World world = player.worldObj;
            Block block = world.getBlock(x, y, z);
            int meta = world.getBlockMetadata(x, y, z);
            if (block == null)
                return super.onBlockStartBreak(stack, x, y, z, player);
            MovingObjectPosition mop = raytraceFromEntity(world, player, true, 4.5D);
            if (mop != null && (materials.contains(block.getMaterial()) || block == Blocks.monster_egg)) {
                byte xRange = 1;
                byte yRange = 1;
                byte zRange = 1;
                switch (mop.sideHit) {
                    case 0:
                    case 1:
                        yRange = 0;
                        break;
                    case 2:
                    case 3:
                        zRange = 0;
                        break;
                    case 4:
                    case 5:
                        xRange = 0;
                        break;
                }
                boolean lowPower = false;
                boolean silktouch = EnchantmentHelper.getSilkTouchModifier(player);
                int fortune = EnchantmentHelper.getFortuneModifier(player);

                for (int xPos = x - xRange; xPos <= x + xRange; xPos++) {
                    for (int yPos = y - yRange; yPos <= y + yRange; yPos++) {
                        for (int zPos = z - zRange; zPos <= z + zRange; zPos++) {
                            if (ElectricItem.manager.canUse(stack, this.energyPerOperation)) {
                                Block localBlock = world.getBlock(xPos, yPos, zPos);
                                if (localBlock != null && canHarvestBlock(localBlock, stack) &&
                                        localBlock.getBlockHardness(world, xPos, yPos, zPos) >= 0.0F && (
                                        materials.contains(localBlock.getMaterial()) || block == Blocks.monster_egg))

                                    if (!player.capabilities.isCreativeMode) {
                                        int localMeta = world.getBlockMetadata(xPos, yPos, zPos);
                                        if (localBlock.getBlockHardness(world, xPos, yPos, zPos) > 0.0F)
                                            onBlockDestroyed(stack, world, localBlock, xPos, yPos, zPos, player);
                                        if (!silktouch)
                                            localBlock.dropXpOnBlockBreak(world, xPos, yPos, zPos, localBlock.getExpDrop(world, localMeta, fortune));
                                        localBlock.onBlockHarvested(world, xPos, yPos, zPos, localMeta, player);
                                        if (localBlock.removedByPlayer(world, player, xPos, yPos, zPos, true)) {
                                            localBlock.onBlockDestroyedByPlayer(world, xPos, yPos, zPos, localMeta);
                                            localBlock.harvestBlock(world, player, xPos, yPos, zPos, localMeta);
                                        }
                                        ElectricItem.manager.use(stack, this.energyPerOperation, player);
                                    } else {
                                        world.setBlockToAir(xPos, yPos, zPos);
                                    }
                                world.func_147479_m(xPos, yPos, zPos);

                            } else {
                                lowPower = true;
                                break;
                            }
                        }
                    }
                }
                if (lowPower) {
                    CommonProxy.sendPlayerMessage(player, "Not enough energy to complete this operation !");
                } else if (!IUCore.isSimulating()) {
                    world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(block) + (meta << 12));
                }
                return true;
            }
        }
        //
        if (readToolMode(stack) == 3) {
            World world = player.worldObj;
            Block block = world.getBlock(x, y, z);
            int meta = world.getBlockMetadata(x, y, z);
            if (block == null)
                return super.onBlockStartBreak(stack, x, y, z, player);
            MovingObjectPosition mop = raytraceFromEntity(world, player, true, 4.5D);
            if (mop != null && (materials.contains(block.getMaterial()) || block == Blocks.monster_egg)) {
                byte xRange = 2;
                byte yRange = 2;
                byte zRange = 2;
                switch (mop.sideHit) {
                    case 0:
                    case 1:
                        yRange = 0;
                        break;
                    case 2:
                    case 3:
                        zRange = 0;
                        break;
                    case 4:
                    case 5:
                        xRange = 0;
                        break;
                }
                boolean lowPower = false;
                boolean silktouch = EnchantmentHelper.getSilkTouchModifier(player);
                int fortune = EnchantmentHelper.getFortuneModifier(player);

                for (int xPos = x - xRange; xPos <= x + xRange; xPos++) {
                    for (int yPos = y - yRange; yPos <= y + yRange; yPos++) {
                        for (int zPos = z - zRange; zPos <= z + zRange; zPos++) {
                            if (ElectricItem.manager.canUse(stack, this.energyPerOperation)) {
                                Block localBlock = world.getBlock(xPos, yPos, zPos);
                                if (localBlock != null && canHarvestBlock(localBlock, stack) &&
                                        localBlock.getBlockHardness(world, xPos, yPos, zPos) >= 0.0F && (
                                        materials.contains(localBlock.getMaterial()) || block == Blocks.monster_egg))

                                    if (!player.capabilities.isCreativeMode) {
                                        int localMeta = world.getBlockMetadata(xPos, yPos, zPos);
                                        if (localBlock.getBlockHardness(world, xPos, yPos, zPos) > 0.0F)
                                            onBlockDestroyed(stack, world, localBlock, xPos, yPos, zPos, player);
                                        if (!silktouch)
                                            localBlock.dropXpOnBlockBreak(world, xPos, yPos, zPos, localBlock.getExpDrop(world, localMeta, fortune));
                                        localBlock.onBlockHarvested(world, xPos, yPos, zPos, localMeta, player);
                                        if (localBlock.removedByPlayer(world, player, xPos, yPos, zPos, true)) {
                                            localBlock.onBlockDestroyedByPlayer(world, xPos, yPos, zPos, localMeta);
                                            localBlock.harvestBlock(world, player, xPos, yPos, zPos, localMeta);
                                        }
                                        ElectricItem.manager.use(stack, this.energyPerOperation, player);
                                    } else {
                                        world.setBlockToAir(xPos, yPos, zPos);
                                    }
                                world.func_147479_m(xPos, yPos, zPos);

                            } else {
                                lowPower = true;
                                break;
                            }
                        }
                    }
                }
                if (lowPower) {
                    CommonProxy.sendPlayerMessage(player, "Not enough energy to complete this operation !");
                } else if (!IUCore.isSimulating()) {
                    world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(block) + (meta << 12));
                }
                return true;
            }
        }
        //
        return super.onBlockStartBreak(stack, x, y, z, player);
    }

    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int xPos, int yPos, int zPos, EntityLivingBase entity) {
        if (!IUCore.isSimulating())
            return true;
        if (block == null)
            return false;
        if (entity != null) {
            float energy;
            int toolMode = readToolMode(stack);
            switch (toolMode) {
                case 0:
                    energy = this.energyPerOperation;
                    break;
                case 1:
                    energy = this.energyPerLowOperation;
                    break;
                case 2:
                    energy = this.energyPerbigHolePowerOperation;
                    break;
                case 3:
                    energy = this.energyPerultraLowPowerOperation;
                    break;
                default:
                    energy = 0.0F;
                    break;
            }
            if (energy != 0.0F && block.getBlockHardness(world, xPos, yPos, zPos) != 0.0F)
                ElectricItem.manager.use(stack, energy, entity);
        }
        return true;
    }

    public void saveToolMode(ItemStack itemstack, int toolMode) {
        NBTTagCompound nbt = NBTData.getOrCreateNbtData(itemstack);
        nbt.setInteger("toolMode", toolMode);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float xOffset, float yOffset, float zOffset) {
        for (int i = 0; i < player.inventory.mainInventory.length; i++) {
            ItemStack torchStack = player.inventory.mainInventory[i];
            if (torchStack != null && torchStack.getUnlocalizedName().toLowerCase().contains("torch")) {
                Item item = torchStack.getItem();
                if (item instanceof net.minecraft.item.ItemBlock) {
                    int oldMeta = torchStack.getItemDamage();
                    int oldSize = torchStack.stackSize;
                    boolean result = torchStack.tryPlaceItemIntoWorld(player, world, x, y, z, side, xOffset, yOffset, zOffset);
                    if (player.capabilities.isCreativeMode) {
                        torchStack.setItemDamage(oldMeta);
                        torchStack.stackSize = oldSize;
                    } else if (torchStack.stackSize <= 0) {
                        ForgeEventFactory.onPlayerDestroyItem(player, torchStack);
                        player.inventory.mainInventory[i] = null;
                    }
                    if (result)
                        return true;
                }
            }
        }
        return super.onItemUse(stack, player, world, x, y, z, side, xOffset, yOffset, zOffset);
    }

    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        if (IC2.keyboard.isModeSwitchKeyDown(player)) {
            int toolMode = readToolMode(itemStack) + 1;

            if (toolMode > 3)
                toolMode = 0;
            saveToolMode(itemStack, toolMode);
            switch (toolMode) {
                case 0:
                    CommonProxy.sendPlayerMessage(player, EnumChatFormatting.GREEN + Helpers.formatMessage("message.text.mode") + ": " + Helpers.formatMessage("message.ultDDrill.mode.normal"));
                    this.efficiencyOnProperMaterial = this.normalPower;
                    mode = 0;
                    Map<Integer, Integer> enchantmentMap = new HashMap<Integer, Integer>();
                    if (Config.enableefficiency) {
                        enchantmentMap.put(Integer.valueOf(Enchantment.efficiency.effectId), Integer.valueOf(Config.efficiencylevel));
                    }
                    if (Config.enablefortune) {
                        enchantmentMap.remove(Integer.valueOf(Enchantment.fortune.effectId), Integer.valueOf(Config.fortunelevel));
                    }
                    if (Config.enablefortune || Config.enableefficiency)
                        EnchantmentHelper.setEnchantments(enchantmentMap, itemStack);
                    break;

                case 1:
                    Map<Integer, Integer> enchantmentMap4 = new HashMap<Integer, Integer>();
                    mode = 1;
                    if (Config.enableefficiency) {
                        enchantmentMap4.remove(Integer.valueOf(Enchantment.efficiency.effectId), Integer.valueOf(Config.efficiencylevel));
                    }
                    if (Config.enableefficiency1) {
                        enchantmentMap4.put(Integer.valueOf(Enchantment.efficiency.effectId), Integer.valueOf(Config.efficiencylevel1));
                    }
                    if (Config.enableefficiency1 || Config.enableefficiency)
                        EnchantmentHelper.setEnchantments(enchantmentMap4, itemStack);
                    CommonProxy.sendPlayerMessage(player, EnumChatFormatting.GOLD + Helpers.formatMessage("message.text.mode") + ": " + Helpers.formatMessage("message.ultDDrill.mode.lowPower"));
                    this.efficiencyOnProperMaterial = this.lowPower;
                    break;
                case 2:
                    CommonProxy.sendPlayerMessage(player, EnumChatFormatting.AQUA + Helpers.formatMessage("message.text.mode") + ": " + Helpers.formatMessage("message.ultDDrill.mode.bigHoles"));
                    this.efficiencyOnProperMaterial = this.bigHolePower;
                    Map<Integer, Integer> enchantmentMap2 = new HashMap<Integer, Integer>();
                    mode = 2;
                    if (Config.enableefficiency1) {
                        enchantmentMap2.remove(Integer.valueOf(Enchantment.efficiency.effectId), Integer.valueOf(Config.efficiencylevel1));
                    }
                    if (Config.enablesilkTouch) {
                        enchantmentMap2.put(Integer.valueOf(Enchantment.silkTouch.effectId), Integer.valueOf(1));
                    }
                    if (Config.enableefficiency1 || Config.enablesilkTouch)
                        EnchantmentHelper.setEnchantments(enchantmentMap2, itemStack);
                    break;
                case 3:
                    CommonProxy.sendPlayerMessage(player, EnumChatFormatting.LIGHT_PURPLE + Helpers.formatMessage("message.text.mode") + ": " + Helpers.formatMessage("message.ultDDrill.mode.bigHoles1"));
                    this.efficiencyOnProperMaterial = this.ultraLowPower;
                    Map<Integer, Integer> enchantmentMap1 = new HashMap<Integer, Integer>();
                    if (Config.enablesilkTouch) {
                        enchantmentMap1.remove(Integer.valueOf(Enchantment.silkTouch.effectId), Integer.valueOf(1));
                    }
                    mode = 3;
                    if (Config.enablefortune) {
                        enchantmentMap1.put(Integer.valueOf(Enchantment.fortune.effectId), Integer.valueOf(Config.fortunelevel));
                    }
                    if (Config.enablefortune || Config.enablesilkTouch)
                        EnchantmentHelper.setEnchantments(enchantmentMap1, itemStack);
                    break;
            }
        }
        return itemStack;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
        Integer toolMode = readToolMode(par1ItemStack);
        if (toolMode.intValue() == 0)
            par3List.add(EnumChatFormatting.GOLD + Helpers.formatMessage("message.text.mode") + ": " + EnumChatFormatting.WHITE + Helpers.formatMessage("message.ultDDrill.mode.normal"));
        if (toolMode.intValue() == 1)
            par3List.add(EnumChatFormatting.GOLD + Helpers.formatMessage("message.text.mode") + ": " + EnumChatFormatting.WHITE + Helpers.formatMessage("message.ultDDrill.mode.lowPower"));
        if (toolMode.intValue() == 2)
            par3List.add(EnumChatFormatting.GOLD + Helpers.formatMessage("message.text.mode") + ": " + EnumChatFormatting.WHITE + Helpers.formatMessage("message.ultDDrill.mode.bigHoles"));
        if (toolMode.intValue() == 3)
            par3List.add(EnumChatFormatting.GOLD + Helpers.formatMessage("message.text.mode") + ": " + EnumChatFormatting.WHITE + Helpers.formatMessage("message.ultDDrill.mode.bigHoles1"));
    }

    public String getRandomDrillSound() {
        switch (IUCore.random.nextInt(4)) {
            case 1:
                return "drillOne";
            case 2:
                return "drillTwo";
            case 3:
                return "drillThree";
        }
        return "drill";
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List subs) {
        ItemStack stack = new ItemStack(this, 1);
        Map<Integer, Integer> enchantmentMap4 = new HashMap<Integer, Integer>();


        enchantmentMap4.put(Integer.valueOf(Enchantment.efficiency.effectId), Integer.valueOf(10));
        EnchantmentHelper.setEnchantments(enchantmentMap4, stack);
        ElectricItem.manager.charge(stack, 2.147483647E9D, 2147483647, true, false);
        subs.add(stack);
        ItemStack itemstack = new ItemStack(this, 1, getMaxDamage());
        EnchantmentHelper.setEnchantments(enchantmentMap4, itemstack);

        subs.add(itemstack);
    }

    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack var1) {
        return EnumRarity.uncommon;
    }

    public Item getChargedItem(ItemStack itemStack) {
        return this;
    }

    public Item getEmptyItem(ItemStack itemStack) {
        return this;
    }
}
