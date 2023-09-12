package me.thebluetropics.block;

import me.thebluetropics.ImmersifPaintingsMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModBlocks {
    public static final SmallPaintingBlock MOUNT_FUJI = new SmallPaintingBlock(FabricBlockSettings.create().nonOpaque());
    public static final SmallPaintingBlock THE_MOYAI_VALLEY = new SmallPaintingBlock(FabricBlockSettings.create().nonOpaque());
    public static final SmallPaintingBlock GRASS_AND_SKY = new SmallPaintingBlock(FabricBlockSettings.create().nonOpaque());
    public static final SmallPaintingBlock STATION = new SmallPaintingBlock(FabricBlockSettings.create().nonOpaque());
    public static final SmallPaintingBlock ALPHA_JOURNEY = new SmallPaintingBlock(FabricBlockSettings.create().nonOpaque());

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
    }
}
