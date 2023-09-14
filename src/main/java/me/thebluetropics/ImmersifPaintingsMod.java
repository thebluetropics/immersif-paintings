package me.thebluetropics;

import me.thebluetropics.block.ModBlocks;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImmersifPaintingsMod implements ModInitializer {
	public static final String MOD_ID = "immersif_paintings";
    public static final Logger LOGGER = LoggerFactory.getLogger("immersif_paintings");

	@Override
	public void onInitialize() {
		ModBlocks.registerBlocks();

		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
			if (LootTables.DESERT_PYRAMID_CHEST.equals(id)) {
				LootPool.Builder pool = LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1.0f))
					.bonusRolls(ConstantLootNumberProvider.create(0.0f))
					.with(ItemEntry.builder(ModBlocks.MOUNT_FUJI.asItem()).weight(5))
					.with(ItemEntry.builder(ModBlocks.GRASS_AND_SKY.asItem()).weight(15))
					.with(ItemEntry.builder(ModBlocks.THE_MOYAI_VALLEY.asItem()).weight(15))
					.with(ItemEntry.builder(Items.AIR).weight(30));
				tableBuilder.pool(pool);
			}

			if (LootTables.ABANDONED_MINESHAFT_CHEST.equals(id)) {
				LootPool.Builder pool = LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1.0f))
					.bonusRolls(ConstantLootNumberProvider.create(0.0f))
					.with(ItemEntry.builder(ModBlocks.KINRYUZAN_TEMPLE.asItem()).weight(10))
					.with(ItemEntry.builder(ModBlocks.REFLECTION.asItem()).weight(10))
					.with(ItemEntry.builder(Items.AIR).weight(30));
				tableBuilder.pool(pool);
			}

			if (LootTables.JUNGLE_TEMPLE_CHEST.equals(id)) {
				tableBuilder.pool(
					LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1.0f))
						.bonusRolls(ConstantLootNumberProvider.create(0.0f))
						.with(ItemEntry.builder(ModBlocks.ALPHA_JOURNEY.asItem()).weight(15))
						.with(ItemEntry.builder(Items.AIR).weight(30))
				);
			}

			if (LootTables.BURIED_TREASURE_CHEST.equals(id)) {
				tableBuilder.pool(
					LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1.0f))
						.bonusRolls(ConstantLootNumberProvider.create(0.0f))
						.with(ItemEntry.builder(ModBlocks.OCEANIC_JOURNEY.asItem()).weight(10))
						.with(ItemEntry.builder(ModBlocks.KINRYUZAN_TEMPLE.asItem()).weight(10))
						.with(ItemEntry.builder(Items.AIR).weight(30))
				);
			}

			if (LootTables.SHIPWRECK_TREASURE_CHEST.equals(id)) {
				tableBuilder.pool(
					LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1.0f))
						.bonusRolls(ConstantLootNumberProvider.create(0.0f))
						.with(ItemEntry.builder(ModBlocks.COLD_AND_COZY.asItem()).weight(10))
						.with(ItemEntry.builder(Items.AIR).weight(30))
				);
			}

			if (LootTables.SHIPWRECK_MAP_CHEST.equals(id)) {
				tableBuilder.pool(
					LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1.0f))
						.bonusRolls(ConstantLootNumberProvider.create(0.0f))
						.with(ItemEntry.builder(ModBlocks.SEE_THAT_TREE_OVER_THERE.asItem()).weight(15))
						.with(ItemEntry.builder(Items.AIR).weight(30))
				);
			}

			if (LootTables.SHIPWRECK_SUPPLY_CHEST.equals(id)) {
				tableBuilder.pool(
					LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1.0f))
						.bonusRolls(ConstantLootNumberProvider.create(0.0f))
						.with(ItemEntry.builder(ModBlocks.UP_TO_THE_SKY.asItem()).weight(15))
						.with(ItemEntry.builder(Items.AIR).weight(30))
				);
			}
		});
	}
}