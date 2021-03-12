package com.Denfop.block.ore;

import com.Denfop.IUCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockOre extends Block {

    public BlockOre(Material material) {
        super(material);
        this.setCreativeTab(IUCore.tabssp4);
        this.setHarvestLevel("pickaxe", 2);
        this.setHardness(1F);
        this.setLightLevel(0.3F);
        this.setStepSound(Block.soundTypeGlass);
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.getItemFromBlock(this);
    }
}
