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
     * The placeholder block texture generator.
     */
    val placeholderTextureBlock = PlaceholderTexture(ContentGenerator.Categories.Block)

    /**
     * The placeholder block texture generator.
     */
    val placeholderTextureItem = PlaceholderTexture(ContentGenerator.Categories.Item)

    /**
     * The base ore block model generator.
     *
     * NOTE: The child ore models assume that this is named `modid:ore_template`.
     */
    val oreTemplateModel = BaseOreBlockModel

    /**
     * The stone ore block model generator.
     */
    val stoneOreBlockModel = OreBlockModel("Stone", Identifier.mc("block/stone"))

    /**
     * The netherrack ore block model generator.
     */
    val netherrackOreBlockModel = OreBlockModel("Netherrack", Identifier.mc("block/netherrack"))

    /**
     * The pillar block model generator.
     */
    val pillarBlockModel = PillarBlockModel

    /**
     * The pillar block state generator.
     */
    val pillarBlockState = PillarBlockState

    object Variants {
        // SLABS

        /**
         * The slab block model generator.
         */
        val slabBlockModel = SlabBlockModel

        /**
         * The slab blockstate generator.
         */
        val slabBlockState = SlabBlockState

        /**
         * The slab item model generator.
         */
        val slabItemModel = SuffixedBlockItemModel("Slab", "slab")

        /**
         * The slab loot table generator.
         */
        val slabLootTable = SlabLootTable

        /**
         * A list of all slab generators.
         */
        val allSlabs = listOf(slabBlockModel, slabBlockState, slabItemModel, slabLootTable)

        // STAIRS

        /**
         * The stair block model generator.
         */
        val stairBlockModel = StairBlockModel

        /**
         * The stair blockstate generator.
         */
        val stairBlockState = StairBlockState

        /**
         * The stair item model generator.
         */
        val stairItemModel = SuffixedBlockItemModel("Stair", "stairs")

        /**
         * The stair loot table generator.
         */
        val stairLootTable = SuffixedLootTable("Stair", "stairs")

        /**
         * A list of all stair generators.
         */
        val allStairs = listOf(stairBlockModel, stairBlockState, stairItemModel, stairLootTable)

        // PRESSURE PLATES

        /**
         * The pressure plate block model generator.
         */
        val pressurePlateBlockModel = PressurePlateBlockModel

        /**
         * The pressure plate blockstate generator.
         */
        val pressurePlateBlockState = PressurePlateBlockState

        /**
         * The pressure plate item model generator.
         */
        val pressurePlateItemModel = SuffixedBlockItemModel("Pressure Plate", "pressure_plate")

        /**
         * The pressure plate loot table generator.
         */
        val pressurePlateLootTable = SuffixedLootTable("Pressure Plate", "pressure_plate")

        /**
         * A list of all pressure plate generators.
         */
        val allPressurePlates = listOf(pressurePlateBlockModel, pressurePlateBlockState, pressurePlateItemModel, pressurePlateLootTable)

        // BUTTONS

        /**
         * The button block model generator.
         */
        val buttonBlockModel = ButtonBlockModel

        /**
         * The button blockstate generator.
         */
        val buttonBlockState = ButtonBlockState

        /**
         * The button item model generator.
         */
        val buttonItemModel = SuffixedBlockItemModel("Button", "button", "button_inventory")

        /**
         * The button loot table generator.
         */
        val buttonLootTable = SuffixedLootTable("Button", "button")

        /**
         * A list of all button generators.
         */
        val allButtons = listOf(buttonBlockModel, buttonBlockState, buttonItemModel, buttonLootTable)

        // FENCES

        /**
         * The fence block model generator.
         */
        val fenceBlockModel = FenceBlockModel

        /**
         * The fence blockstate generator.
         */
        val fenceBlockState = FenceBlockState

        /**
         * The fence item model generator.
         */
        val fenceItemModel = SuffixedBlockItemModel("Fence", "fence", "fence_inventory")

        /**
         * The fence loot table generator.
         */
        val fenceLootTable = SuffixedLootTable("Fence", "fence")

        /**
         * A list of all fence generators.
         */
        val allFences = listOf(fenceBlockModel, fenceBlockState, fenceItemModel, fenceLootTable)

        // WALLS

        /**
         * The wall block model generator.
         */
        val wallBlockModel = WallBlockModel

        /**
         * The wall blockstate generator.
         */
        val wallBlockState = WallBlockState

        /**
         * The wall item model generator.
         */
        val wallItemModel = SuffixedBlockItemModel("Wall", "wall", "wall_inventory")

        /**
         * The wall loot table generator.
         */
        val wallLootTable = SuffixedLootTable("Wall", "wall")

        /**
         * A list of all wall generators.
         */
        val allWalls = listOf(wallBlockModel, wallBlockState, wallItemModel, wallLootTable)

        // SIGNS

        /**
         * The sign block model generator.
         */
        val signBlockModel = SignBlockModel

        /**
         * The sign blockstate generator.
         */
        val signBlockState = SignBlockState

        /**
         * The sign item model generator.
         */
        val signItemModel = SuffixedItemModel(Identifier.mc("item/generated"), "Sign", "sign", ContentGenerator.Categories.BlockVariants)

        /**
         * The sign loot table generator.
         */
        val signLootTable = SuffixedLootTable("Sign", "sign")

        /**
         * A list of all sign generators.
         */
        val allSigns = listOf(signBlockModel, signBlockState, signItemModel, signLootTable)

        // FENCE GATES

        /**
         * The fence gate model generator.
         */
        val fenceGateBlockModel = FenceGateBlockModel

        /**
         * The fence gate block state generator.
         */
        val fenceGateBlockState = FenceGateBlockState

        /**
         * The fence gate item model generator.
         */
        val fenceGateItemModel = SuffixedBlockItemModel("Fence Gate", "fence_gate")

        /**
         * The fence gate loot table generator.
         */
        val fenceGateLootTable = SuffixedLootTable("Fence Gate", "fence_gate")

        /**
         * A list of all fence gate generators.
         */
        val allFenceGates = listOf(fenceGateBlockModel, fenceGateBlockState, fenceGateItemModel, fenceGateLootTable)

        // TRAPDOORS

        /**
         * The trapdoor model generator.
         */
        val trapdoorBlockModel = TrapdoorBlockModel

        /**
         * The trapdoor block state generator.
         */
        val trapdoorBlockState = TrapdoorBlockState

        /**
         * The trapdoor item model generator.
         */
        val trapdoorItemModel = SuffixedBlockItemModel("Trapdoor", "trapdoor", "trapdoor_bottom")

        /**
         * The trapdoor loot table generator.
         */
        val trapdoorLootTable = SuffixedLootTable("Trapdoor", "trapdoor")

        /**
         * A list of all trapdoor generators.
         */
        val allTrapdoors = listOf(trapdoorBlockModel, trapdoorBlockState, trapdoorItemModel, trapdoorLootTable)
    }

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
        placeholderTextureBlock,
        placeholderTextureItem,
        // Ores
        oreTemplateModel,
        stoneOreBlockModel,
        netherrackOreBlockModel,
        // Pillars
        pillarBlockModel,
        pillarBlockState,
        // Slabs
        Variants.slabBlockModel,
        Variants.slabBlockState,
        Variants.slabItemModel,
        Variants.slabLootTable,
        // Stairs
        Variants.stairBlockModel,
        Variants.stairBlockState,
        Variants.stairItemModel,
        Variants.stairLootTable,
        // Pressure plates
        Variants.pressurePlateBlockModel,
        Variants.pressurePlateBlockState,
        Variants.pressurePlateItemModel,
        Variants.pressurePlateLootTable,
        // Buttons
        Variants.buttonBlockModel,
        Variants.buttonBlockState,
        Variants.buttonItemModel,
        Variants.buttonLootTable,
        // Fences
        Variants.fenceBlockModel,
        Variants.fenceBlockState,
        Variants.fenceItemModel,
        Variants.fenceLootTable,
        // Walls
        Variants.wallBlockModel,
        Variants.wallBlockState,
        Variants.wallItemModel,
        Variants.wallLootTable,
        // Signs
        Variants.signBlockModel,
        Variants.signBlockState,
        Variants.signItemModel,
        Variants.signLootTable,
        // Fence gates
        Variants.fenceGateBlockModel,
        Variants.fenceGateBlockState,
        Variants.fenceGateItemModel,
        Variants.fenceGateLootTable
    )
}
