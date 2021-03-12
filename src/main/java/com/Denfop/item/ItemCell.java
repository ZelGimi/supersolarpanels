package com.Denfop.item;

import com.Denfop.IUCore;
import com.Denfop.SSPItem;
import com.Denfop.block.Base.BlocksItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.IC2;
import ic2.core.util.LiquidUtil;
import ic2.core.util.StackUtil;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.util.*;

public class ItemCell extends Item {
    private final Map<Integer, String> names;
    private final Map<Block, ItemStack> cells;
    private final List<String> itemNames;
    private final IIcon[] IIconsList;
    private final int itemsCount;

    public ItemCell() {
        super();
        this.names = new HashMap<Integer, String>();
        this.cells = new IdentityHashMap<Block, ItemStack>();
        setHasSubtypes(true);
        SSPItem.cell = addCell(0, "itemCellEmpty");
        SSPItem.uuMatterCell = addRegisterCell(1, "itemCellUuMatter", "fluidUuMatter");
        this.itemNames = new ArrayList<String>();
        this.IIconsList = new IIcon[2];
        this.itemsCount = 1;
        this.addItemsNames();
        this.setCreativeTab(IUCore.tabssp3);

    }

    public String getUnlocalizedName(final ItemStack stack) {
        return this.itemNames.get(stack.getItemDamage());
    }

    public IIcon getIconFromDamage(final int par1) {
        return this.IIconsList[par1];
    }

    public void addItemsNames() {
        this.itemNames.add("itemCellEmpty");
        this.itemNames.add("itemCellUuMatter");
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister IIconRegister) {
        this.IIconsList[0] = IIconRegister.registerIcon("supersolarpanel:itemCellEmpty");
        this.IIconsList[1] = IIconRegister.registerIcon("supersolarpanel:itemCellUuMatter");

    }


    public void getSubItems(final Item item, final CreativeTabs tabs, final List itemList) {
        for (int meta = 0; meta <= this.itemNames.size() - 1; ++meta) {
            final ItemStack stack = new ItemStack(this, 1, meta);
            itemList.add(stack);
        }
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float xOffset, float yOffset, float zOffset) {
        if (!IC2.platform.isSimulating())
            return false;
        MovingObjectPosition position = getMovingObjectPositionFromPlayer(world, player, true);
        if (position == null)
            return false;
        if (position.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            x = position.blockX;
            y = position.blockY;
            z = position.blockZ;
            if (!world.canMineBlock(player, x, y, z))
                return false;
            if (!player.canPlayerEdit(x, y, z, position.sideHit, stack))
                return false;
            if (stack.getItemDamage() == 0) {
                if (world.getBlockMetadata(x, y, z) == 0) {
                    ItemStack filledCell = this.cells.get(world.getBlock(x, y, z));
                    if (filledCell != null && StackUtil.storeInventoryItem(filledCell.copy(), player, false)) {
                        world.setBlockToAir(x, y, z);
                        stack.stackSize--;
                        return true;
                    }
                }
            } else {
                FluidStack fs = FluidContainerRegistry.getFluidForFilledItem(stack);
                ForgeDirection dir = ForgeDirection.VALID_DIRECTIONS[position.sideHit];
                if ((fs != null && LiquidUtil.placeFluid(fs, world, x, y, z)) || (player.canPlayerEdit(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ, position.sideHit, stack) && LiquidUtil.placeFluid(fs, world, x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ))) {
                    if (!player.capabilities.isCreativeMode)
                        stack.stackSize--;
                    return true;
                }
            }
        }
        return false;
    }

    private ItemStack addCell(int meta, String name, Block... blocks) {
        this.names.put(Integer.valueOf(meta), name);
        ItemStack ret = new ItemStack(this, 1, meta);
        for (Block block : blocks)
            this.cells.put(block, ret);
        return ret;
    }

    private ItemStack addRegisterCell(int meta, String name, String blockName) {
        ItemStack ret = addCell(meta, name, BlocksItems.getFluidBlock(blockName));
        FluidContainerRegistry.registerFluidContainer(BlocksItems.getFluid(blockName), ret.copy(), SSPItem.cell.copy());

        return ret;
    }
}
