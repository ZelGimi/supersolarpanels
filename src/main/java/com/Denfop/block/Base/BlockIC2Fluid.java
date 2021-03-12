package com.Denfop.block.Base;

import com.Denfop.Constants;
import com.Denfop.IUCore;
import com.Denfop.item.base.ItemBlockIC2;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.IC2;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

import java.util.Random;

public class BlockIC2Fluid extends BlockFluidClassic {
    private final int color;
    protected IIcon[] fluidIcon;
    protected Fluid fluid;

    public BlockIC2Fluid(String internalName, Fluid fluid, Material material, int color) {
        super(fluid, material);
        if (!fluid.getName().startsWith("ic3"))
            throw new RuntimeException("Invalid fluid name: " + fluid.getName());
        setCreativeTab(IUCore.tabssp);
        setBlockName(internalName);
        GameRegistry.registerBlock(this, ItemBlockIC2.class, internalName);
        this.fluid = fluid;
        this.color = color;

    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {

        String name = this.fluidName.substring("ic3".length());
        if (this.fluid.equals(BlocksItems.getFluid("fluidUuMatter"))) {
            this

                    .fluidIcon = new IIcon[]{iconRegister.registerIcon(Constants.TEXTURES + ":blocks/" + name + "_still"), iconRegister.registerIcon(Constants.TEXTURES + ":blocks/" + name + "_flow")};
        } else {
            this

                    .fluidIcon = new IIcon[]{iconRegister.registerIcon(Constants.TEXTURES + ":blocks/" + name + "_still"), iconRegister.registerIcon(Constants.TEXTURES + ":blocks/" + name + "_flow")};

        }

    }

    public void updateTick(World world, int x, int y, int z, Random random) {
        super.updateTick(world, x, y, z, random);
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        super.onNeighborBlockChange(world, x, y, z, block);
    }

    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack itemStack) {
        if (!IC2.platform.isSimulating())
            return;

    }

    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity, int x, int y, int z, int oldMeta) {
        if (!IC2.platform.isSimulating())
            return;


    }


    public IIcon getIcon(int side, int meta) {
        return (side != 0 && side != 1) ? this.fluidIcon[1] : this.fluidIcon[0];
    }

    public String getUnlocalizedName() {
        return super.getUnlocalizedName().substring(5);
    }

    public int getColor() {
        return this.color;
    }


}
