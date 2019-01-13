package io.github.cottonmc.jsonfactory.gens

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.variants.*

/**
 * Lists [ContentGenerator]s.
 *
 * The generator properties (apart from [allGens]) are mostly version-independent and
 * won't change as often as their classes.
 */
object Gens {
    /**
     * The `block/cube_all` (default) block model generator.
     */
    val basicBlockModel = BasicBlockModel

    /**
     * The `item/generated` (default) item model generator.
     */
    val basicItemModel = BasicItemModel(Identifier.mc("item/generated"))

    /**
     * The `item/handheld` item model generator.
     */
    val basicHandheldItemModel = BasicItemModel(Identifier.mc("item/handheld"), " (Handheld)")

    /**
     * The block item model generator.
     */
    val basicBlockItemModel = BasicBlockItemModel

    /**
     * The basic `blockstates` file generator.
     */
    val basicBlockState = BasicBlockState

    /**
     * The basic loot table generator.
     */
    val basicLootTable = BasicLootTable

    /**
     * An array of all generators.
     */
    val allGens = arrayOf(
        basicBlockModel,
        basicItemModel,
        basicHandheldItemModel,
        basicBlockItemModel,
        basicBlockState,
        basicLootTable,
        PlaceholderTexture(ContentGenerator.Categories.Block),
        PlaceholderTexture(ContentGenerator.Categories.Item),
        // Slabs
        SlabBlockModel,
        SlabBlockState,
        SuffixedBlockItemModel("Slab", "slab"),
        SlabLootTable,
        // Ores
        BaseOreBlockModel,
        OreBlockModel("Stone", Identifier.mc("block/stone")),
        OreBlockModel("Netherrack", Identifier.mc("block/netherrack")),
        // Pillars
        PillarBlockModel,
        PillarBlockState,
        // Stairs
        StairBlockModel,
        StairBlockState,
        SuffixedBlockItemModel("Stair", "stairs"),
        SuffixedLootTable("Stair", "stairs"),
        // Pressure plates
        PressurePlateBlockModel,
        PressurePlateBlockState,
        SuffixedBlockItemModel("Pressure Plate", "pressure_plate"),
        SuffixedLootTable("Pressure Plate", "pressure_plate"),
        // Buttons
        ButtonBlockModel,
        ButtonBlockState,
        SuffixedBlockItemModel("Button", "button", "button_inventory"),
        SuffixedLootTable("Button", "button"),
        // Fences
        FenceBlockModel,
        FenceBlockState,
        SuffixedBlockItemModel("Fence", "fence", "fence_inventory"),
        SuffixedLootTable("Fence", "fence"),
        // Walls
        WallBlockModel,
        WallBlockState,
        SuffixedBlockItemModel("Wall", "wall", "wall_inventory"),
        SuffixedLootTable("Wall", "wall"),
        // Signs
        SignBlockModel,
        SignBlockState,
        SuffixedItemModel(Identifier.mc("item/generated"), "Sign", "sign"),
        SuffixedLootTable("Sign", "sign")
    )
}
