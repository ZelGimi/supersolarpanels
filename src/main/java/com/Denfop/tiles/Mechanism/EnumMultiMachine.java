package com.Denfop.tiles.Mechanism;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.tileentity.TileEntity;

public enum EnumMultiMachine {

    DOUBLE_MACERATOR(TileEntityDoubleMacerator.class, "Macerator Double", 2, 300, 2),
    TRIPLE_MACERATOR(TileEntityTripleMacerator.class, "Macerator Triple", 2, 300, 3),
    QUAD_MACERATOR(TileEntityQuadMacerator.class, "Macerator Quad", 2, 300, 4),

    DOUBLE_COMPRESSER(TileEntityDoubleCompressor.class, "Compressor Double", 2, 300, 2),
    TRIPLE_COMPRESSER(TileEntityTripleCompressor.class, "Compressor Triple", 2, 300, 3),
    QUAD_COMPRESSER(TileEntityQuadCompressor.class, "Compressor Quad", 2, 300, 4),

    DOUBLE_EXTRACTOR(TileEntityDoubleExtractor.class, "Extractor Double", 2, 300, 2),
    TRIPLE_EXTRACTOR(TileEntityTripleExtractor.class, "Extractor Triple", 2, 300, 3),
    QUAD_EXTRACTOR(TileEntityQuadExtractor.class, "Extractor Quad", 2, 300, 4),

    DOUBLE_ELECTRIC_FURNACE(TileEntityDoubleElectricFurnace.class, "Double Electric Furnace", 3, 100, 2),
    TRIPLE_ELECTRIC_FURNACE(TileEntityTripleElectricFurnace.class, "Triple Electric Furnace", 3, 100, 3),
    QUAD_ELECTRIC_FURNACE(TileEntityQuadElectricFurnace.class, "Quad Electric Furnace", 3, 100, 4),

    DOUBLE_METAL_FORMER(TileEntityDoubleMetalFormer.class, "Metal Former Double", 10, 200, 2),
    TRIPLE_METAL_FORMER(TileEntityTripleMetalFormer.class, "Metal Former Triple", 10, 200, 3),
    QUAD_METAL_FORMER(TileEntityQuadMetalFormer.class, "Metal Former Quad", 10, 200, 4),

    DOUBLE_RECYCLER(TileEntityDoubleRecycler.class, "Double Recycler", 1, 45, 2),
    TRIPLE_RECYCLER(TileEntityTripleRecycler.class, "Triple Recycler", 1, 45, 3),
    QUAD_RECYCLER(TileEntityQuadRecycler.class, "Quad Recycler", 1, 45, 4);

    private final Class<? extends TileEntity> clazz;
    private final String name;
    public int usagePerTick, lenghtOperation, sizeWorkingSlot;

    EnumMultiMachine(Class<? extends TileEntity> clazz, String name, int usagePerTick, int lenghtOperation, int sizeWorkingSlot) {
        this.clazz = clazz;
        this.name = name;
        this.usagePerTick = usagePerTick;
        this.lenghtOperation = lenghtOperation;
        this.sizeWorkingSlot = sizeWorkingSlot;
    }

    public static void registerTile() {
        for (EnumMultiMachine machine : EnumMultiMachine.values()) {
            GameRegistry.registerTileEntity(machine.clazz, machine.name);
        }
    }

}
