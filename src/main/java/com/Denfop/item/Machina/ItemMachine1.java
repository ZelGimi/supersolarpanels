package com.Denfop.item.Machina;

import com.Denfop.IUCore;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemMachine1 extends ItemBlock {
    public ItemMachine1(Block block) {
        super(block);
        setMaxDamage(0);
        setHasSubtypes(true);
        setCreativeTab(IUCore.tabssp);
    }

    @Override
    public int getMetadata(int i) {
        return i;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        int meta = itemstack.getItemDamage();
        switch (meta) {

            case 1:
                return "ssp.blockMatter1";

            case 2:
                return "ssp.blockMatter2";
            case 3:
                return "ssp.blockMatter3";

            case 4:
                return "ssp.Alloymachine";
            case 5:
                return "ssp.blockMatter";
            case 6:
                return "ssp.blockGenerationMicrochip";
            case 7:
                return "ssp.blockGenerationStone";
            case 8:
                return "ssp.blockQuantumQuarry";
            case 0:
                return "blockMolecularTransformer";
        }
        return null;
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean b) {
        int meta = itemStack.getItemDamage();
        switch (meta) {


        }
    }

    @Override
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
        par3List.add(new ItemStack(par1, 1, 4));
        par3List.add(new ItemStack(par1, 1, 5));
        par3List.add(new ItemStack(par1, 1, 6));
        par3List.add(new ItemStack(par1, 1, 7));
        par3List.add(new ItemStack(par1, 1, 8));
    }
}
