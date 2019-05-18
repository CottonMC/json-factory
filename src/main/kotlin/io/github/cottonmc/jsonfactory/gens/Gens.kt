package io.github.cottonmc.jsonfactory.gens

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.Gens.ALL_GENS
import io.github.cottonmc.jsonfactory.gens.block.*
import io.github.cottonmc.jsonfactory.gens.item.BasicItemModel
import io.github.cottonmc.jsonfactory.gens.item.SuffixedItemModel

/**
 * Lists [ContentGenerator]s.
 *
 * The generator properties (apart from the contents of [ALL_GENS]) are mostly version-independent and
 * won't change as often as their classes.
 */
object Gens {
    /**
     * The `block/cube_all` (default) block model generator.
     */
    val basicBlockModel: ContentGenerator = BasicBlockModel

    /**
     * The `item/generated` (default) item model generator.
     */
    val basicItemModel: ContentGenerator = BasicItemModel(Identifier.mc("item/generated"))

    /**
     * The `item/handheld` item model generator.
     */
    val basicHandheldItemModel: ContentGenerator =
        // TODO: Parens
        BasicItemModel(Identifier.mc("item/handheld"), " (Handheld)")

    /**
     * The block item model generator.
     */
    val basicBlockItemModel: ContentGenerator = BasicBlockItemModel

    /**
     * The basic `blockstates` file generator.
     */
    val basicBlockState: ContentGenerator = BasicBlockState

    /**
     * The basic loot table generator.
     */
    val basicLootTable: ContentGenerator = BasicLootTable

    /**
     * The placeholder block texture generator.
     */
    val placeholderTextureBlock: ContentGenerator =
        PlaceholderTexture(GeneratorInfo.BLOCK)

    /**
     * The placeholder item texture generator.
     */
    val placeholderTextureItem: ContentGenerator =
        PlaceholderTexture(GeneratorInfo.ITEM)

    /**
     * The base ore block model generator.
     *
     * NOTE: The child ore models assume that this is named `modid:ore_template`.
     */
    val oreTemplateModel: ContentGenerator = BaseOreBlockModel

    /**
     * The stone ore block model generator.
     */
    val stoneOreBlockModel: ContentGenerator =
        OreBlockModel("stone", Identifier.mc("block/stone"))

    /**
     * The netherrack ore block model generator.
     */
    val netherrackOreBlockModel: ContentGenerator =
        OreBlockModel("netherrack", Identifier.mc("block/netherrack"))

    /**
     * The pillar block model generator.
     */
    val pillarBlockModel: ContentGenerator = PillarBlockModel

    /**
     * The pillar block state generator.
     */
    val pillarBlockState: ContentGenerator = PillarBlockState

    object Variants {
        // SLABS

        /**
         * The slab block model generator.
         */
        val slabBlockModel: ContentGenerator = SlabBlockModel

        /**
         * The slab blockstate generator.
         */
        val slabBlockState: ContentGenerator = SlabBlockState

        /**
         * The slab item model generator.
         */
        val slabItemModel: ContentGenerator = SuffixedBlockItemModel("slab", GeneratorInfo.SLABS)

        /**
         * The slab loot table generator.
         */
        val slabLootTable: ContentGenerator = SlabLootTable

        /**
         * A set of all slab generators.
         */
        val ALL_SLABS = setOf(slabBlockModel, slabBlockState, slabItemModel, slabLootTable)

        // STAIRS

        /**
         * The stair block model generator.
         */
        val stairBlockModel: ContentGenerator = StairBlockModel

        /**
         * The stair blockstate generator.
         */
        val stairBlockState: ContentGenerator = StairBlockState

        /**
         * The stair item model generator.
         */
        val stairItemModel: ContentGenerator = SuffixedBlockItemModel("stairs", GeneratorInfo.STAIRS)

        /**
         * The stair loot table generator.
         */
        val stairLootTable: ContentGenerator = SuffixedLootTable("stairs", GeneratorInfo.STAIRS)

        /**
         * A set of all stair generators.
         */
        val ALL_STAIRS = setOf(stairBlockModel, stairBlockState, stairItemModel, stairLootTable)

        // PRESSURE PLATES

        /**
         * The pressure plate block model generator.
         */
        val pressurePlateBlockModel: ContentGenerator = PressurePlateBlockModel

        /**
         * The pressure plate blockstate generator.
         */
        val pressurePlateBlockState: ContentGenerator = PressurePlateBlockState

        /**
         * The pressure plate item model generator.
         */
        val pressurePlateItemModel: ContentGenerator =
            SuffixedBlockItemModel("pressure_plate", GeneratorInfo.PRESSURE_PLATES)

        /**
         * The pressure plate loot table generator.
         */
        val pressurePlateLootTable: ContentGenerator =
            SuffixedLootTable("pressure_plate", GeneratorInfo.PRESSURE_PLATES)

        /**
         * A set of all pressure plate generators.
         */
        val ALL_PRESSURE_PLATES =
            setOf(pressurePlateBlockModel, pressurePlateBlockState, pressurePlateItemModel, pressurePlateLootTable)

        // BUTTONS

        /**
         * The button block model generator.
         */
        val buttonBlockModel: ContentGenerator = ButtonBlockModel

        /**
         * The button blockstate generator.
         */
        val buttonBlockState: ContentGenerator = ButtonBlockState

        /**
         * The button item model generator.
         */
        val buttonItemModel: ContentGenerator =
            SuffixedBlockItemModel("buttons", GeneratorInfo.BUTTONS, "button_inventory")

        /**
         * The button loot table generator.
         */
        val buttonLootTable: ContentGenerator = SuffixedLootTable("buttons", GeneratorInfo.BUTTONS)

        /**
         * A set of all button generators.
         */
        val ALL_BUTTONS = setOf(buttonBlockModel, buttonBlockState, buttonItemModel, buttonLootTable)

        // FENCES

        /**
         * The fence block model generator.
         */
        val fenceBlockModel: ContentGenerator = FenceBlockModel

        /**
         * The fence blockstate generator.
         */
        val fenceBlockState: ContentGenerator = FenceBlockState

        /**
         * The fence item model generator.
         */
        val fenceItemModel: ContentGenerator =
            SuffixedBlockItemModel("fences", GeneratorInfo.FENCES, "fence_inventory")

        /**
         * The fence loot table generator.
         */
        val fenceLootTable: ContentGenerator = SuffixedLootTable("fences", GeneratorInfo.FENCES)

        /**
         * A set of all fence generators.
         */
        val ALL_FENCES = setOf(fenceBlockModel, fenceBlockState, fenceItemModel, fenceLootTable)

        // WALLS

        /**
         * The wall block model generator.
         */
        val wallBlockModel: ContentGenerator = WallBlockModel

        /**
         * The wall blockstate generator.
         */
        val wallBlockState: ContentGenerator = WallBlockState

        /**
         * The wall item model generator.
         */
        val wallItemModel: ContentGenerator =
            SuffixedBlockItemModel("walls", GeneratorInfo.WALLS, "wall_inventory")

        /**
         * The wall loot table generator.
         */
        val wallLootTable: ContentGenerator = SuffixedLootTable("walls", GeneratorInfo.WALLS)

        /**
         * A set of all wall generators.
         */
        val ALL_WALLS = setOf(wallBlockModel, wallBlockState, wallItemModel, wallLootTable)

        // SIGNS

        /**
         * The sign block model generator.
         */
        val signBlockModel: ContentGenerator = SignBlockModel

        /**
         * The sign blockstate generator.
         */
        val signBlockState: ContentGenerator = SignBlockState

        /**
         * The sign item model generator.
         */
        val signItemModel: ContentGenerator = SuffixedItemModel(
            Identifier.mc("item/generated"),
            "sign",
            GeneratorInfo.SIGNS
        )

        /**
         * The sign loot table generator.
         */
        val signLootTable: ContentGenerator = SuffixedLootTable("signs", GeneratorInfo.SIGNS)

        /**
         * A set of all sign generators.
         */
        val ALL_SIGNS = setOf(signBlockModel, signBlockState, signItemModel, signLootTable)

        // FENCE GATES

        /**
         * The fence gate model generator.
         */
        val fenceGateBlockModel: ContentGenerator = FenceGateBlockModel

        /**
         * The fence gate block state generator.
         */
        val fenceGateBlockState: ContentGenerator = FenceGateBlockState

        /**
         * The fence gate item model generator.
         */
        val fenceGateItemModel: ContentGenerator =
            SuffixedBlockItemModel("fence_gates", GeneratorInfo.FENCE_GATES)

        /**
         * The fence gate loot table generator.
         */
        val fenceGateLootTable: ContentGenerator = SuffixedLootTable(
            "fence_gates",
            GeneratorInfo.FENCE_GATES
        )

        /**
         * A set of all fence gate generators.
         */
        val ALL_FENCE_GATES = setOf(fenceGateBlockModel, fenceGateBlockState, fenceGateItemModel, fenceGateLootTable)

        // TRAPDOORS

        /**
         * The trapdoor model generator.
         */
        val trapdoorBlockModel: ContentGenerator = TrapdoorBlockModel

        /**
         * The trapdoor block state generator.
         */
        val trapdoorBlockState: ContentGenerator = TrapdoorBlockState

        /**
         * The trapdoor item model generator.
         */
        val trapdoorItemModel: ContentGenerator =
            SuffixedBlockItemModel("trapdoors", GeneratorInfo.TRAPDOORS, "trapdoor_bottom")

        /**
         * The trapdoor loot table generator.
         */
        val trapdoorLootTable: ContentGenerator = SuffixedLootTable("trapdoors", GeneratorInfo.TRAPDOORS)

        /**
         * A set of all trapdoor generators.
         */
        val ALL_TRAPDOORS = setOf(trapdoorBlockModel, trapdoorBlockState, trapdoorItemModel, trapdoorLootTable)

        // DOORS

        /**
         * The door model generator.
         */
        val doorBlockModel: ContentGenerator = DoorBlockModel

        /**
         * The door block state generator.
         */
        val doorBlockState: ContentGenerator = DoorBlockState

        /**
         * The door item model generator.
         */
        val doorItemModel: ContentGenerator = SuffixedItemModel(
            Identifier.mc("item/generated"),
            "door",
            GeneratorInfo.DOORS
        )

        /**
         * The door loot table generator.
         */
        val doorLootTable: ContentGenerator = SuffixedLootTable("doors", GeneratorInfo.DOORS)

        /**
         * A set of all door generators.
         */
        val ALL_DOORS = setOf(doorBlockModel, doorBlockState, doorItemModel, doorLootTable)

        // LADDERS

        /**
         * The ladder model generator.
         */
        val ladderBlockModel: ContentGenerator = LadderBlockModel

        /**
         * The ladder block state generator.
         */
        val ladderBlockState: ContentGenerator = LadderBlockState

        /**
         * The ladder item model generator.
         */
        val ladderItemModel: ContentGenerator = SuffixedItemModel(
            Identifier.mc("item/generated"),
            "ladder",
            GeneratorInfo.LADDERS
        )

        /**
         * The ladder loot table generator.
         */
        val ladderLootTable: ContentGenerator = SuffixedLootTable("ladders", GeneratorInfo.LADDERS)

        /**
         * A set of all ladder generators.
         */
        val ALL_LADDERS = setOf(ladderBlockModel, ladderBlockState, ladderItemModel, ladderLootTable)

        // LEAVES

        /**
         * The leaf model generator.
         */
        val leafBlockModel: ContentGenerator = LeafBlockModel

        /**
         * The leaf block state generator.
         */
        val leafBlockState: ContentGenerator = SuffixedBlockState("leaves", GeneratorInfo.LEAVES)

        /**
         * The leaf item model generator.
         */
        val leafItemModel: ContentGenerator = SuffixedBlockItemModel("leaves", GeneratorInfo.LEAVES)

        /**
         * The leaf loot table generator.
         */
        val leafLootTable: ContentGenerator = LeafLootTable

        /**
         * A set of all leaf generators.
         */
        val ALL_LEAVES = setOf(leafBlockModel, leafBlockState, leafItemModel, leafLootTable)

        // POTTED PLANTS

        /**
         * The potted plant model generator.
         */
        val pottedPlantBlockModel: ContentGenerator = PottedPlantModel

        /**
         * The potted plant block state generator.
         */
        val pottedPlantBlockState: ContentGenerator =
            PrefixedBlockState("potted", GeneratorInfo.POTTED_PLANTS)

        /**
         * The potted plant loot table generator.
         */
        val pottedPlantLootTable: ContentGenerator = PottedPlantLootTable

        /**
         * A set of all potted plant generators.
         */
        val ALL_POTTED_PLANTS = setOf(pottedPlantBlockModel, pottedPlantBlockState, pottedPlantLootTable)

        // CHESTS

        /**
         * The chest model generator.
         */
        val chestBlockModel: ContentGenerator = ChestBlockModel

        /**
         * The chest block state generator.
         */
        val chestBlockState: ContentGenerator = SuffixedBlockState("chest", GeneratorInfo.CHESTS)

        /**
         * The chest item model generator.
         */
        val chestItemModel: ContentGenerator = ChestItemModel

        /**
         * The chest loot table generator.
         */
        val chestLootTable: ContentGenerator = ChestLootTable

        /**
         * A set of all chest generators.
         */
        val ALL_CHESTS = setOf(chestBlockModel, chestBlockState, chestItemModel, chestLootTable)

        // SCAFFOLDING

        /**
         * The scaffolding block model generator.
         */
        val scaffoldingBlockModel: ContentGenerator = ScaffoldingBlockModel

        /**
         * The scaffolding block state generator.
         */
        val scaffoldingBlockState: ContentGenerator = ScaffoldingBlockState

        /**
         * The scaffolding item model generator.
         */
        val scaffoldingItemModel: ContentGenerator =
            SuffixedBlockItemModel("scaffolding", GeneratorInfo.SCAFFOLDING)

        /**
         * The scaffolding loot table generator.
         */
        val scaffoldingLootTable: ContentGenerator = ScaffoldingLootTable

        /**
         * A set of all scaffolding generators.
         */
        val ALL_SCAFFOLDING =
            setOf(scaffoldingBlockModel, scaffoldingBlockState, scaffoldingItemModel, scaffoldingLootTable)

        // BARRELS

        /**
         * The barrel block model generator.
         */
        val barrelBlockModel: ContentGenerator = BarrelBlockModel

        /**
         * The barrel block state generator.
         */
        val barrelBlockState: ContentGenerator = BarrelBlockState

        /**
         * The barrel item model generator.
         */
        val barrelItemModel: ContentGenerator = SuffixedBlockItemModel("barrel", GeneratorInfo.BARRELS)

        /**
         * The barrel loot table generator.
         */
        val barrelLootTable: ContentGenerator = BarrelLootTable

        /**
         * A set of all barrel generators.
         */
        val ALL_BARRELS = setOf(barrelBlockModel, barrelBlockState, barrelItemModel, barrelLootTable)

        // CAMPFIRES

        /**
         * The campfire block model generator.
         */
        val campfireBlockModel: ContentGenerator = CampfireBlockModel

        /**
         * The campfire block state generator.
         */
        val campfireBlockState: ContentGenerator = CampfireBlockState

        /**
         * The campfire item model generator.
         */
        val campfireItemModel: ContentGenerator = SuffixedItemModel(
            parent = Identifier.mc("item/generated"),
            type = "campfire",
            info = GeneratorInfo.CAMPFIRES
        )

        /**
         * The campfire loot table generator.
         */
        val campfireLootTable: ContentGenerator = CampfireLootTable

        /**
         * A set of all campfire generators.
         */
        val ALL_CAMPFIRES = setOf(campfireBlockModel, campfireBlockState, campfireItemModel, campfireLootTable)
    }

    /**
     * A list of all generators.
     */
    val ALL_GENS = sequence {
        yield(basicBlockModel)
        yield(basicItemModel)
        yield(basicHandheldItemModel)
        yield(basicBlockItemModel)
        yield(basicBlockState)
        yield(basicLootTable)
        yield(placeholderTextureBlock)
        yield(placeholderTextureItem)

        // Ore
        yield(oreTemplateModel)
        yield(stoneOreBlockModel)
        yield(netherrackOreBlockModel)

        // Pillar
        yield(pillarBlockModel)
        yield(pillarBlockState)

        yieldAll(Variants.ALL_SLABS)
        yieldAll(Variants.ALL_STAIRS)
        yieldAll(Variants.ALL_PRESSURE_PLATES)
        yieldAll(Variants.ALL_BUTTONS)
        yieldAll(Variants.ALL_FENCES)
        yieldAll(Variants.ALL_WALLS)
        yieldAll(Variants.ALL_SIGNS)
        yieldAll(Variants.ALL_FENCE_GATES)
        yieldAll(Variants.ALL_TRAPDOORS)
        yieldAll(Variants.ALL_DOORS)
        yieldAll(Variants.ALL_LADDERS)
        yieldAll(Variants.ALL_LEAVES)
        yieldAll(Variants.ALL_POTTED_PLANTS)
        yieldAll(Variants.ALL_CHESTS)
        yieldAll(Variants.ALL_SCAFFOLDING)
        yieldAll(Variants.ALL_BARRELS)
        yieldAll(Variants.ALL_CAMPFIRES)
    }.toList()
}
