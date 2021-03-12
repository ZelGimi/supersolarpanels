package com.Denfop.item.Upgrade;

import com.Denfop.Constants;
import com.Denfop.IUCore;
import com.Denfop.SSPItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.Direction;
import ic2.api.item.IItemHudInfo;
import ic2.core.upgrade.IUpgradableBlock;
import ic2.core.upgrade.IUpgradeItem;
import ic2.core.upgrade.UpgradableProperty;
import ic2.core.upgrade.UpgradeRegistry;
import ic2.core.util.StackUtil;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidHandler;

import java.text.DecimalFormat;
import java.util.*;

public class ItemUpgradeModule extends Item implements IUpgradeItem, IItemHudInfo {

    private static final DecimalFormat decimalformat = new DecimalFormat("0.##");
    private static final List<StackUtil.AdjacentInv> emptyInvList = Arrays.asList(new StackUtil.AdjacentInv[0]);
    private final int fluidAmountPerTick;
    private final List<String> itemNames;
    private final IIcon[] IIconsList;
    private final int itemsCount;

    public ItemUpgradeModule() {
        super();
        setHasSubtypes(true);
        this.setCreativeTab(IUCore.tabssp3);
        SSPItem.overclockerUpgrade = UpgradeRegistry.register(new ItemStack(this, 1, Type.Overclocker1.ordinal()));
        SSPItem.overclockerUpgrade1 = UpgradeRegistry.register(new ItemStack(this, 1, Type.Overclocker2.ordinal()));
        SSPItem.overclockerUpgrade = new ItemStack(this, 1, 0);
        SSPItem.overclockerUpgrade1 = new ItemStack(this, 1, 1);
        this.fluidAmountPerTick = 50;
        this.itemNames = new ArrayList<String>();
        this.IIconsList = new IIcon[2];
        this.itemsCount = 1;
        this.addItemsNames();
    }

    private static String getSideName(ItemStack stack) {
        switch (getDirection(stack) - 1) {
            case 0:
                return "ic2.dir.west";
            case 1:
                return "ic2.dir.east";
            case 2:
                return "ic2.dir.bottom";
            case 3:
                return "ic2.dir.top";
            case 4:
                return "ic2.dir.north";
            case 5:
                return "ic2.dir.south";
        }
        return "ic2.tooltip.upgrade.ejector.anyside";
    }

    private static List<StackUtil.AdjacentInv> getTargetInventories(ItemStack stack, TileEntity parent) {
        int rawDir = getDirection(stack);
        if (rawDir < 1 || rawDir > 6)
            return StackUtil.getAdjacentInventories(parent);
        Direction dir = Direction.directions[rawDir - 1];
        StackUtil.AdjacentInv inv = StackUtil.getAdjacentInventory(parent, dir);
        if (inv == null)
            return emptyInvList;
        return Arrays.asList(inv);
    }

    public static Type getType(int meta) {
        if (meta < 0 || meta >= Type.Values.length)
            return null;
        return Type.Values[meta];
    }

    private static int getDirection(ItemStack stack) {
        return StackUtil.getOrCreateNbtData(stack).getByte("dir");
    }

    public static boolean getItemFromBlock(String string) {

        return false;
    }

    public String getUnlocalizedName(final ItemStack stack) {
        return this.itemNames.get(stack.getItemDamage());
    }

    public IIcon getIconFromDamage(final int par1) {
        return this.IIconsList[par1];
    }

    public void addItemsNames() {
        this.itemNames.add("ssp.overclockerUpgrade1");
        this.itemNames.add("ssp.overclockerUpgrade2");
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister IIconRegister) {
        this.IIconsList[0] = IIconRegister.registerIcon(Constants.TEXTURES_MAIN + "overclockerUpgrade1");
        this.IIconsList[1] = IIconRegister.registerIcon(Constants.TEXTURES_MAIN + "overclockerUpgrade2");

    }

    public String getItemStackDisplayName(ItemStack itemStack) {
        return StatCollector.translateToLocal(getUnlocalizedName(itemStack));
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        int dir;
        Type type = getType(stack.getItemDamage());
        if (type == null)
            return null;

        return super.getIcon(stack, pass);
    }

    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    public List<String> getHudInfo(ItemStack stack) {
        List<String> info = new LinkedList<String>();
        info.add("Machine Upgrade");
        return info;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        String side;
        Type type = getType(stack.getItemDamage());
        if (type == null)
            return;
        super.addInformation(stack, player, list, par4);
        switch (type) {
            case Overclocker1:
                list.add(StatCollector.translateToLocalFormatted("ic2.tooltip.upgrade.overclocker.time", decimalformat
                        .format(100.0D * Math.pow(getProcessTimeMultiplier(stack, null), stack.stackSize))));
                list.add(StatCollector.translateToLocalFormatted("ic2.tooltip.upgrade.overclocker.power", decimalformat
                        .format(100.0D * Math.pow(getEnergyDemandMultiplier(stack, null), stack.stackSize))));
                break;
            case Overclocker2:
                list.add(StatCollector.translateToLocalFormatted("ic2.tooltip.upgrade.overclocker.time", decimalformat
                        .format(100.0D * Math.pow(getProcessTimeMultiplier(stack, null), stack.stackSize))));
                list.add(StatCollector.translateToLocalFormatted("ic2.tooltip.upgrade.overclocker.power", decimalformat
                        .format(100.0D * Math.pow(getEnergyDemandMultiplier(stack, null), stack.stackSize))));
                break;

        }
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, float xOffset, float yOffset, float zOffset) {
        int dir;
        NBTTagCompound nbtData;
        Type type = getType(stack.getItemDamage());
        if (type == null)
            return false;

        return false;
    }

    public void getSubItems(final Item item, final CreativeTabs tabs, final List itemList) {
        for (int meta = 0; meta <= this.itemNames.size() - 1; ++meta) {
            final ItemStack stack = new ItemStack(this, 1, meta);
            itemList.add(stack);
        }
    }

    public boolean isSuitableFor(ItemStack stack, Set<UpgradableProperty> types) {
        Type type = getType(stack.getItemDamage());
        if (type == null)
            return false;
        switch (type) {

            case Overclocker1:
                return (types.contains(UpgradableProperty.Processing) || types.contains(UpgradableProperty.Augmentable));
            case Overclocker2:
                return (types.contains(UpgradableProperty.Processing) || types.contains(UpgradableProperty.Augmentable));

        }
        return false;
    }

    public int getAugmentation(ItemStack stack, IUpgradableBlock parent) {
        Type type = getType(stack.getItemDamage());
        if (type == null)
            return 0;
        switch (type) {
            case Overclocker1:
                return 1;
            case Overclocker2:
                return 1;
        }
        return 0;
    }

    public int getExtraProcessTime(ItemStack stack, IUpgradableBlock parent) {
        return 0;
    }

    public double getProcessTimeMultiplier(ItemStack stack, IUpgradableBlock parent) {
        Type type = getType(stack.getItemDamage());
        if (type == null)
            return 1.0D;
        switch (type) {
            case Overclocker1:
                return 0.5D;
            case Overclocker2:
                return 0.4D;
        }
        return 1.0D;
    }

    public int getExtraEnergyDemand(ItemStack stack, IUpgradableBlock parent) {
        return 0;
    }

    public double getEnergyDemandMultiplier(ItemStack stack, IUpgradableBlock parent) {
        Type type = getType(stack.getItemDamage());
        if (type == null)
            return 1.0D;
        switch (type) {
            case Overclocker1:
                return 1.3D;
            case Overclocker2:
                return 1.2D;
        }
        return 1.0D;
    }

    public int getExtraEnergyStorage(ItemStack stack, IUpgradableBlock parent) {
        Type type = getType(stack.getItemDamage());
        if (type == null)
            return 0;

        return 0;
    }

    public double getEnergyStorageMultiplier(ItemStack stack, IUpgradableBlock parent) {
        return 1.0D;
    }

    public int getExtraTier(ItemStack stack, IUpgradableBlock parent) {
        Type type = getType(stack.getItemDamage());
        if (type == null)
            return 0;

        return 0;
    }

    public boolean modifiesRedstoneInput(ItemStack stack, IUpgradableBlock parent) {
        Type type = getType(stack.getItemDamage());
        if (type == null)
            return false;

        return false;
    }

    public int getRedstoneInput(ItemStack stack, IUpgradableBlock parent, int externalInput) {
        Type type = getType(stack.getItemDamage());
        if (type == null)
            return externalInput;

        return externalInput;
    }

    public boolean onTick(ItemStack stack, IUpgradableBlock parent) {
        int i;
        IFluidHandler fParent;
        int amount, rawDir;
        Type type = getType(stack.getItemDamage());
        if (type == null)
            return false;
        boolean ret = false;

        return false;
    }

    public void onProcessEnd(ItemStack stack, IUpgradableBlock parent, List<ItemStack> output) {
    }

    private enum Type {
        Overclocker1, Overclocker2;

        public static final Type[] Values = values();

        static {

        }
    }
}
