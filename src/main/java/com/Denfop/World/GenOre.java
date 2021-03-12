package com.Denfop.World;

import com.Denfop.Config;
import com.Denfop.SSPItem;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

public class GenOre implements IWorldGenerator {
    public static void init() {
        GameRegistry.registerWorldGenerator(new GenOre(), 0);
    }

    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

        switch (world.provider.dimensionId) {
            case -1:
                generateNether(world, random, chunkX * 16, chunkZ * 16);
            case 0:
                generateSurface(world, random, chunkX * 16, chunkZ * 16);
            case 1:
                generateEnd(world, random, chunkX * 16, chunkZ * 16);
        }
    }

    private void generateNether(World world, Random random, int x, int y) {
        if (Config.EnableNetherOres) {
            addOreSpawn1(SSPItem.nethercopperrack, world, random, x, y, 16, 16, 3 + random.nextInt(2), 20, 20, 80);
            addOreSpawn1(SSPItem.netheruranrack, world, random, x, y, 16, 16, 3 + random.nextInt(2), 4, 20, 70);
            addOreSpawn1(SSPItem.nethertinrack, world, random, x, y, 16, 16, 3 + random.nextInt(2), 16, 5, 70);
            addOreSpawn1(SSPItem.netherleadrack, world, random, x, y, 16, 16, 3 + random.nextInt(2), 4, 10, 80);
            addOreSpawn1(SSPItem.netheremeraldrack, world, random, x, y, 16, 16, 3 + random.nextInt(2), 4, 30, 60);
            addOreSpawn1(SSPItem.netherdiamondrack, world, random, x, y, 16, 16, 3 + random.nextInt(2), 4, 10, 60);

            addOreSpawn1(SSPItem.nethercoalrack, world, random, x, y, 16, 16, 3 + random.nextInt(2), 30, 10, 70);
            addOreSpawn1(SSPItem.netherredstonerack, world, random, x, y, 16, 16, 3 + random.nextInt(2), 15, 10, 70);
            addOreSpawn1(SSPItem.netherlapisrack, world, random, x, y, 16, 16, 3 + random.nextInt(2), 12, 10, 70);
            addOreSpawn1(SSPItem.netherironrack, world, random, x, y, 16, 16, 3 + random.nextInt(2), 16, 10, 70);
            addOreSpawn1(SSPItem.nethergoldrack, world, random, x, y, 16, 16, 10, 10, 0, 128);
        }
    }

    private void generateSurface(World world, Random random, int x, int y) {
        if (Config.EnableMickailOre)
            this.addOreSpawn(SSPItem.mikhail_ore, world, random, x, y, 16, 16, 3 + random.nextInt(3), 14, 10, 60);
        if (Config.EnableSpinelOre)
            this.addOreSpawn(SSPItem.spinelore, world, random, x, y, 16, 16, 3 + random.nextInt(3), 14, 10, 60);
        if (Config.EnablePlatiumOre)
            this.addOreSpawn(SSPItem.platiumore, world, random, x, y, 16, 16, 2 + random.nextInt(3), 10, 0, 60);
        if (Config.EnableWolframOre)
            this.addOreSpawn(SSPItem.wolframore, world, random, x, y, 16, 16, 3 + random.nextInt(3), 15, 10, 70);
        if (Config.EnableChromiumOre)
            this.addOreSpawn(SSPItem.chromiumore, world, random, x, y, 16, 16, 3 + random.nextInt(3), 14, 10, 70);
        if (Config.EnableIridiumOre)
            this.addOreSpawn(SSPItem.iridiumore, world, random, x, y, 16, 16, 3 + random.nextInt(3), 10, 10, 60);
        if (Config.EnableMagnesiumOre)
            this.addOreSpawn(SSPItem.magnesiumore, world, random, x, y, 16, 16, 3 + random.nextInt(3), 12, 10, 70);
        if (Config.EnableNickelOre)
            this.addOreSpawn(SSPItem.nicelore, world, random, x, y, 16, 16, 3 + random.nextInt(3), 12, 10, 60);
        if (Config.EnableToriyOre)
            this.addOreSpawn(SSPItem.toriyore, world, random, x, y, 16, 16, 3 + random.nextInt(2), 10, 10, 70);
        if (Config.EnableMagnetitOre)
            this.addOreSpawn(SSPItem.magnetitore, world, random, x, y, 16, 16, 3 + random.nextInt(2), 10, 10, 70);
    }

    private void generateEnd(World world, Random random, int x, int y) {
        if (Config.EnableEndOres) {
            addOreSpawn2(SSPItem.endcopper_stone, world, random, x, y, 16, 16, 3 + random.nextInt(2), 20, 0, 128);
            addOreSpawn2(SSPItem.enduran_stone, world, random, x, y, 16, 16, 3 + random.nextInt(2), 4, 0, 128);
            addOreSpawn2(SSPItem.endtin_stone, world, random, x, y, 16, 16, 3 + random.nextInt(2), 16, 0, 128);
            addOreSpawn2(SSPItem.endlead_stone, world, random, x, y, 16, 16, 3 + random.nextInt(2), 4, 0, 128);
            addOreSpawn2(SSPItem.endemerald_stone, world, random, x, y, 16, 16, 3 + random.nextInt(2), 4, 0, 128);
            addOreSpawn2(SSPItem.enddiamond_stone, world, random, x, y, 16, 16, 3 + random.nextInt(2), 4, 0, 128);

            addOreSpawn2(SSPItem.endcoal_stone, world, random, x, y, 16, 16, 3 + random.nextInt(2), 30, 0, 128);
            addOreSpawn2(SSPItem.endredstone_stone, world, random, x, y, 16, 16, 3 + random.nextInt(2), 15, 0, 128);
            addOreSpawn2(SSPItem.endlapis_stone, world, random, x, y, 16, 16, 3 + random.nextInt(2), 12, 0, 128);
            addOreSpawn2(SSPItem.endiron_stone, world, random, x, y, 16, 16, 6 + random.nextInt(2), 10, 0, 128);
            addOreSpawn2(SSPItem.endgold_stone, world, random, x, y, 16, 16, 4, 10, 0, 128);
        }
    }

    public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY) {
        for (int i = 0; i < chancesToSpawn; i++) {
            int posX = blockXPos + random.nextInt(maxX);
            int posY = minY + random.nextInt(maxY - minY);
            int posZ = blockZPos + random.nextInt(maxZ);


            (new WorldGenMinable(block, 0, maxVeinSize, Blocks.stone)).generate(world, random, posX, posY, posZ);

        }
    }

    public void addOreSpawn1(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY) {
        for (int i = 0; i < chancesToSpawn; i++) {
            int posX = blockXPos + random.nextInt(maxX);
            int posY = minY + random.nextInt(maxY - minY);
            int posZ = blockZPos + random.nextInt(maxZ);


            (new WorldGenMinable(block, 0, maxVeinSize, Blocks.netherrack)).generate(world, random, posX, posY, posZ);
        }
    }

    public void addOreSpawn2(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY) {
        for (int i = 0; i < chancesToSpawn; i++) {
            int posX = blockXPos + random.nextInt(maxX);
            int posY = minY + random.nextInt(maxY - minY);
            int posZ = blockZPos + random.nextInt(maxZ);


            (new WorldGenMinable(block, 0, maxVeinSize, Blocks.end_stone)).generate(world, random, posX, posY, posZ);
        }
    }

}
