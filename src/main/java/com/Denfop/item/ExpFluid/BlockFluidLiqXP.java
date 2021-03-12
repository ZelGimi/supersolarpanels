package com.Denfop.item.ExpFluid;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockFluidLiqXP extends BlockFluidClassic {


    public IIcon stillIcon;

    public IIcon flowingIcon;

    protected Fluid fluid;


    public BlockFluidLiqXP(Fluid fluid, Material material) {
        super(fluid, material);
        //	this.fluid = fluid;


    }


    @Override
    public void registerBlockIcons(IIconRegister register) {
        this.stillIcon = register.registerIcon("wirelessvajra:xpliquid_still");

    }

    @Override
    public IIcon getIcon(int side, int meta) {
        switch (side) {
            case 0:
                return this.stillIcon;
            case 1:
                return this.stillIcon;
            case 2:
                return this.stillIcon;
            case 3:
                return this.stillIcon;
            case 4:
                return this.stillIcon;
            case 5:
                return this.stillIcon;


        }
        return this.stillIcon;

    }


    @Override
    public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
        if (world.getBlock(x, y, z).getMaterial().isLiquid()) return false;
        return super.canDisplace(world, x, y, z);
    }

    @Override
    public boolean displaceIfPossible(World world, int x, int y, int z) {
        if (world.getBlock(x, y, z).getMaterial().isLiquid()) return false;
        return super.displaceIfPossible(world, x, y, z);
    }
}
