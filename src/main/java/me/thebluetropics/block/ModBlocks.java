package me.thebluetropics.block;

import me.thebluetropics.ImmersifPaintingsMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {
    public static final ArrayList<Block> paintings = new ArrayList<>();

    public static final PaintingBlock MOUNT_FUJI = new PaintingBlock(FabricBlockSettings.create().nonOpaque());
    public static final PaintingBlock THE_MOYAI_VALLEY = new PaintingBlock(FabricBlockSettings.create().nonOpaque());
    public static final PaintingBlock GRASS_AND_SKY = new PaintingBlock(FabricBlockSettings.create().nonOpaque());
    public static final PaintingBlock STATION = new PaintingBlock(FabricBlockSettings.create().nonOpaque());
    public static final PaintingBlock ALPHA_JOURNEY = new PaintingBlock(FabricBlockSettings.create().nonOpaque());
    public static final PaintingBlock UP_TO_THE_SKY = new PaintingBlock(FabricBlockSettings.create().nonOpaque());
    public static final PaintingBlock REFLECTION = new PaintingBlock(FabricBlockSettings.create().nonOpaque());
    public static final PaintingBlock SEE_THAT_TREE_OVER_THERE = new PaintingBlock(FabricBlockSettings.create().nonOpaque());
    public static final DoublePaintingBlock KINRYUZAN_TEMPLE = new DoublePaintingBlock(FabricBlockSettings.create().nonOpaque());
    public static final DoublePaintingBlock OCEANIC_JOURNEY = new DoublePaintingBlock(FabricBlockSettings.create().nonOpaque());
    public static final DoublePaintingBlock COLD_AND_COZY = new DoublePaintingBlock(FabricBlockSettings.create().nonOpaque());

    public static void registerPainting(Block block, String id) {
        Registry.register(Registries.BLOCK, new Identifier(ImmersifPaintingsMod.MOD_ID, id), block);
        Registry.register(Registries.ITEM, new Identifier(ImmersifPaintingsMod.MOD_ID, id), new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerBlocks() {
        registerPainting(MOUNT_FUJI, "mount_fuji");
        registerPainting(THE_MOYAI_VALLEY, "the_moyai_valley");
        registerPainting(GRASS_AND_SKY, "grass_and_sky");
        registerPainting(STATION, "station");
        registerPainting(ALPHA_JOURNEY, "alpha_journey");
        registerPainting(UP_TO_THE_SKY, "up_to_the_sky");
        registerPainting(REFLECTION, "reflection");
        registerPainting(SEE_THAT_TREE_OVER_THERE, "see_that_tree_over_there");
        registerPainting(KINRYUZAN_TEMPLE, "kinryuzan_temple");
        registerPainting(OCEANIC_JOURNEY, "oceanic_journey");
        registerPainting(COLD_AND_COZY, "cold_and_cozy");
    }
}
