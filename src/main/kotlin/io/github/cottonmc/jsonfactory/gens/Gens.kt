package io.github.cottonmc.jsonfactory.gens

import io.github.cottonmc.jsonfactory.data.Identifier

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
    val basicHandheldItemModel = BasicItemModel(Identifier.mc("item/handheld"))

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
        SlabBlockModel,
        SlabBlockState,
        SlabLootTable,
        BaseOreBlockModel,
        OreBlockModel("Stone", Identifier.mc("block/stone")),
        OreBlockModel("Netherrack", Identifier.mc("block/netherrack")),
        PillarBlockModel,
        PillarBlockState,
        StairBlockModel,
        StairBlockState,
        SuffixedLootTable("Stair", "stairs"),
        PressurePlateBlockModel,
        PressurePlateBlockState,
        SuffixedLootTable("Pressure Plate", "pressure_plate")
    )
}
