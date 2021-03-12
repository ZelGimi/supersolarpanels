package com.Denfop.block.ore;

import com.Denfop.IUCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockSSPDiamond extends Block {

    public BlockSSPDiamond(Material material) {
        super(material);
        this.setCreativeTab(IUCore.tabssp4);
        this.setHarvestLevel("pickaxe", 3);
        this.setHardness(1F);
        this.setLightLevel(0.3F);
        this.setStepSound(Block.soundTypeGlass);
    }

    public int quantityDropped(Random random) {
        return 1;
    }

    public int quantityDroppedWithBonus(int fortune, Random random) {
        return (fortune == 0) ? quantityDropped(random) : (quantityDropped(random) + fortune + random.nextInt(fortune * 2));
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Items.diamond;
    }
}
