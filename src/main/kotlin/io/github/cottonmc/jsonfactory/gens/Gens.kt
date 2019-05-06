package io.github.cottonmc.jsonfactory.gens

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.Gens.allGens
import io.github.cottonmc.jsonfactory.gens.block.*
import io.github.cottonmc.jsonfactory.gens.item.BasicItemModel
import io.github.cottonmc.jsonfactory.gens.item.SuffixedItemModel

/**
 * Lists [ContentGenerator]s.
 *
 * The generator properties (apart from the contents of [allGens]) are mostly version-independent and
 * won't change as often as their classes.
 */
object Gens {
    /**
     * The `block/cube_all` (default) block model generator.
     */
    val BASIC_BLOCK_MODEL: ContentGenerator = BasicBlockModel

    /**
     * The `item/generated` (default) item model generator.
     */
    val BASIC_ITEM_MODEL: ContentGenerator = BasicItemModel(Identifier.mc("item/generated"))

    /**
     * The `item/handheld` item model generator.
     */
    val BASIC_HANDHELD_ITEM_MODEL: ContentGenerator =
        BasicItemModel(Identifier.mc("item/handheld"), " (Handheld)")

    /**
     * The block item model generator.
     */
    val BASIC_BLOCK_ITEM_MODEL: ContentGenerator = BasicBlockItemModel

    /**
     * The basic `blockstates` file generator.
     */
    val BASIC_BLOCK_STATE: ContentGenerator = BasicBlockState

    /**
     * The basic loot table generator.
     */
    val BASIC_LOOT_TABLE: ContentGenerator = BasicLootTable

    /**
     * The placeholder block texture generator.
     */
    val PLACEHOLDER_TEXTURE_BLOCK: ContentGenerator =
        PlaceholderTexture(GeneratorInfo.BLOCK)

    /**
     * The placeholder item texture generator.
     */
    val PLACEHOLDER_TEXTURE_ITEM: ContentGenerator =
        PlaceholderTexture(GeneratorInfo.ITEM)

    /**
     * The base ore block model generator.
     *
     * NOTE: The child ore models assume that this is named `modid:ore_template`.
     */
    val ORE_TEMPLATE_MODEL: ContentGenerator = BaseOreBlockModel

    /**
     * The stone ore block model generator.
     */
    val STONE_ORE_BLOCK_MODEL: ContentGenerator =
        OreBlockModel("Stone", Identifier.mc("block/stone"))

    /**
     * The netherrack ore block model generator.
     */
    val NETHERRACK_ORE_BLOCK_MODEL: ContentGenerator =
        OreBlockModel("Netherrack", Identifier.mc("block/netherrack"))

    /**
     * The pillar block model generator.
     */
    val PILLAR_BLOCK_MODEL: ContentGenerator = PillarBlockModel

    /**
     * The pillar block state generator.
     */
    val PILLAR_BLOCK_STATE: ContentGenerator = PillarBlockState

    object Variants {
        // SLABS

        /**
         * The slab block model generator.
         */
        val SLAB_BLOCK_MODEL: ContentGenerator = SlabBlockModel

        /**
         * The slab blockstate generator.
         */
        val SLAB_BLOCK_STATE: ContentGenerator = SlabBlockState

        /**
         * The slab item model generator.
         */
        val SLAB_ITEM_MODEL: ContentGenerator = SuffixedBlockItemModel("Slab", "slab", GeneratorInfo.SLABS)

        /**
         * The slab loot table generator.
         */
        val SLAB_LOOT_TABLE: ContentGenerator = SlabLootTable

        /**
         * A list of all slab generators.
         */
        val allSlabs = listOf(SLAB_BLOCK_MODEL, SLAB_BLOCK_STATE, SLAB_ITEM_MODEL, SLAB_LOOT_TABLE)

        // STAIRS

        /**
         * The stair block model generator.
         */
        val STAIR_BLOCK_MODEL: ContentGenerator = StairBlockModel

        /**
         * The stair blockstate generator.
         */
        val STAIR_BLOCK_STATE: ContentGenerator = StairBlockState

        /**
         * The stair item model generator.
         */
        val STAIR_ITEM_MODEL: ContentGenerator = SuffixedBlockItemModel("Stair", "stairs", GeneratorInfo.STAIRS)

        /**
         * The stair loot table generator.
         */
        val STAIR_LOOT_TABLE: ContentGenerator = SuffixedLootTable("Stair", "stairs", GeneratorInfo.STAIRS)

        /**
         * A list of all stair generators.
         */
        val allStairs = listOf(STAIR_BLOCK_MODEL, STAIR_BLOCK_STATE, STAIR_ITEM_MODEL, STAIR_LOOT_TABLE)

        // PRESSURE PLATES

        /**
         * The pressure plate block model generator.
         */
        val PRESSURE_PLATE_BLOCK_MODEL: ContentGenerator = PressurePlateBlockModel

        /**
         * The pressure plate blockstate generator.
         */
        val PRESSURE_PLATE_BLOCK_STATE: ContentGenerator = PressurePlateBlockState

        /**
         * The pressure plate item model generator.
         */
        val PRESSURE_PLATE_ITEM_MODEL: ContentGenerator =
            SuffixedBlockItemModel("Pressure Plate", "pressure_plate", GeneratorInfo.PRESSURE_PLATES)

        /**
         * The pressure plate loot table generator.
         */
        val PRESSURE_PLATE_LOOT_TABLE: ContentGenerator =
            SuffixedLootTable("Pressure Plate", "pressure_plate", GeneratorInfo.PRESSURE_PLATES)

        /**
         * A list of all pressure plate generators.
         */
        val allPressurePlates =
            listOf(PRESSURE_PLATE_BLOCK_MODEL, PRESSURE_PLATE_BLOCK_STATE, PRESSURE_PLATE_ITEM_MODEL, PRESSURE_PLATE_LOOT_TABLE)

        // BUTTONS

        /**
         * The button block model generator.
         */
        val BUTTON_BLOCK_MODEL: ContentGenerator = ButtonBlockModel

        /**
         * The button blockstate generator.
         */
        val BUTTON_BLOCK_STATE: ContentGenerator = ButtonBlockState

        /**
         * The button item model generator.
         */
        val BUTTON_ITEM_MODEL: ContentGenerator =
            SuffixedBlockItemModel("Button", "button", GeneratorInfo.BUTTONS, "button_inventory")

        /**
         * The button loot table generator.
         */
        val BUTTON_LOOT_TABLE: ContentGenerator = SuffixedLootTable("Button", "button", GeneratorInfo.BUTTONS)

        /**
         * A list of all button generators.
         */
        val allButtons = listOf(BUTTON_BLOCK_MODEL, BUTTON_BLOCK_STATE, BUTTON_ITEM_MODEL, BUTTON_LOOT_TABLE)

        // FENCES

        /**
         * The fence block model generator.
         */
        val FENCE_BLOCK_MODEL: ContentGenerator = FenceBlockModel

        /**
         * The fence blockstate generator.
         */
        val FENCE_BLOCK_STATE: ContentGenerator = FenceBlockState

        /**
         * The fence item model generator.
         */
        val FENCE_ITEM_MODEL: ContentGenerator =
            SuffixedBlockItemModel("Fence", "fence", GeneratorInfo.FENCES, "fence_inventory")

        /**
         * The fence loot table generator.
         */
        val FENCE_LOOT_TABLE: ContentGenerator = SuffixedLootTable("Fence", "fence", GeneratorInfo.FENCES)

        /**
         * A list of all fence generators.
         */
        val allFences = listOf(FENCE_BLOCK_MODEL, FENCE_BLOCK_STATE, FENCE_ITEM_MODEL, FENCE_LOOT_TABLE)

        // WALLS

        /**
         * The wall block model generator.
         */
        val WALL_BLOCK_MODEL: ContentGenerator = WallBlockModel

        /**
         * The wall blockstate generator.
         */
        val WALL_BLOCK_STATE: ContentGenerator = WallBlockState

        /**
         * The wall item model generator.
         */
        val WALL_ITEM_MODEL: ContentGenerator =
            SuffixedBlockItemModel("Wall", "wall", GeneratorInfo.WALLS, "wall_inventory")

        /**
         * The wall loot table generator.
         */
        val WALL_LOOT_TABLE: ContentGenerator = SuffixedLootTable("Wall", "wall", GeneratorInfo.WALLS)

        /**
         * A list of all wall generators.
         */
        val allWalls = listOf(WALL_BLOCK_MODEL, WALL_BLOCK_STATE, WALL_ITEM_MODEL, WALL_LOOT_TABLE)

        // SIGNS

        /**
         * The sign block model generator.
         */
        val SIGN_BLOCK_MODEL: ContentGenerator = SignBlockModel

        /**
         * The sign blockstate generator.
         */
        val SIGN_BLOCK_STATE: ContentGenerator = SignBlockState

        /**
         * The sign item model generator.
         */
        val SIGN_ITEM_MODEL: ContentGenerator = SuffixedItemModel(
            Identifier.mc("item/generated"),
            "Sign",
            "sign",
            GeneratorInfo.SIGNS
        )

        /**
         * The sign loot table generator.
         */
        val SIGN_LOOT_TABLE: ContentGenerator = SuffixedLootTable("Sign", "sign", GeneratorInfo.SIGNS)

        /**
         * A list of all sign generators.
         */
        val allSigns = listOf(SIGN_BLOCK_MODEL, SIGN_BLOCK_STATE, SIGN_ITEM_MODEL, SIGN_LOOT_TABLE)

        // FENCE GATES

        /**
         * The fence gate model generator.
         */
        val FENCE_GATE_BLOCK_MODEL: ContentGenerator = FenceGateBlockModel

        /**
         * The fence gate block state generator.
         */
        val FENCE_GATE_BLOCK_STATE: ContentGenerator = FenceGateBlockState

        /**
         * The fence gate item model generator.
         */
        val FENCE_GATE_ITEM_MODEL: ContentGenerator =
            SuffixedBlockItemModel("Fence Gate", "fence_gate", GeneratorInfo.FENCE_GATES)

        /**
         * The fence gate loot table generator.
         */
        val FENCE_GATE_LOOT_TABLE: ContentGenerator = SuffixedLootTable(
            "Fence Gate",
            "fence_gate",
            GeneratorInfo.FENCE_GATES
        )

        /**
         * A list of all fence gate generators.
         */
        val allFenceGates = listOf(FENCE_GATE_BLOCK_MODEL, FENCE_GATE_BLOCK_STATE, FENCE_GATE_ITEM_MODEL, FENCE_GATE_LOOT_TABLE)

        // TRAPDOORS

        /**
         * The trapdoor model generator.
         */
        val TRAPDOOR_BLOCK_MODEL: ContentGenerator = TrapdoorBlockModel

        /**
         * The trapdoor block state generator.
         */
        val TRAPDOOR_BLOCK_STATE: ContentGenerator = TrapdoorBlockState

        /**
         * The trapdoor item model generator.
         */
        val TRAPDOOR_ITEM_MODEL: ContentGenerator =
            SuffixedBlockItemModel("Trapdoor", "trapdoor", GeneratorInfo.TRAPDOORS, "trapdoor_bottom")

        /**
         * The trapdoor loot table generator.
         */
        val TRAPDOOR_LOOT_TABLE: ContentGenerator = SuffixedLootTable("Trapdoor", "trapdoor", GeneratorInfo.TRAPDOORS)

        /**
         * A list of all trapdoor generators.
         */
        val allTrapdoors = listOf(TRAPDOOR_BLOCK_MODEL, TRAPDOOR_BLOCK_STATE, TRAPDOOR_ITEM_MODEL, TRAPDOOR_LOOT_TABLE)

        // DOORS

        /**
         * The door model generator.
         */
        val DOOR_BLOCK_MODEL: ContentGenerator = DoorBlockModel

        /**
         * The door block state generator.
         */
        val DOOR_BLOCK_STATE: ContentGenerator = DoorBlockState

        /**
         * The door item model generator.
         */
        val DOOR_ITEM_MODEL: ContentGenerator = SuffixedItemModel(
            Identifier.mc("item/generated"),
            "Door",
            "door",
            GeneratorInfo.DOORS
        )

        /**
         * The door loot table generator.
         */
        val DOOR_LOOT_TABLE: ContentGenerator = SuffixedLootTable("Door", "door", GeneratorInfo.DOORS)

        /**
         * A list of all door generators.
         */
        val allDoors = listOf(DOOR_BLOCK_MODEL, DOOR_BLOCK_STATE, DOOR_ITEM_MODEL, DOOR_LOOT_TABLE)

        // LADDERS

        /**
         * The ladder model generator.
         */
        val LADDER_BLOCK_MODEL: ContentGenerator = LadderBlockModel

        /**
         * The ladder block state generator.
         */
        val LADDER_BLOCK_STATE: ContentGenerator = LadderBlockState

        /**
         * The ladder item model generator.
         */
        val LADDER_ITEM_MODEL: ContentGenerator = SuffixedItemModel(
            Identifier.mc("item/generated"),
            "Ladder",
            "ladder",
            GeneratorInfo.LADDERS
        )

        /**
         * The ladder loot table generator.
         */
        val LADDER_LOOT_TABLE: ContentGenerator = SuffixedLootTable("Ladder", "ladder", GeneratorInfo.LADDERS)

        /**
         * A list of all ladder generators.
         */
        val allLadders = listOf(LADDER_BLOCK_MODEL, LADDER_BLOCK_STATE, LADDER_ITEM_MODEL, LADDER_LOOT_TABLE)

        // LEAVES

        /**
         * The leaf model generator.
         */
        val LEAF_BLOCK_MODEL: ContentGenerator = LeafBlockModel

        /**
         * The leaf block state generator.
         */
        val LEAF_BLOCK_STATE: ContentGenerator = SuffixedBlockState("Leaf Block State", "leaves", GeneratorInfo.LEAVES)

        /**
         * The leaf item model generator.
         */
        val LEAF_ITEM_MODEL: ContentGenerator = SuffixedBlockItemModel("Leaf", "leaves", GeneratorInfo.LEAVES)

        /**
         * The leaf loot table generator.
         */
        val LEAF_LOOT_TABLE: ContentGenerator = LeafLootTable

        /**
         * A list of all leaf generators.
         */
        val allLeaves = listOf(LEAF_BLOCK_MODEL, LEAF_BLOCK_STATE, LEAF_ITEM_MODEL, LEAF_LOOT_TABLE)

        // POTTED PLANTS

        /**
         * The potted plant model generator.
         */
        val POTTED_PLANT_BLOCK_MODEL: ContentGenerator = PottedPlantModel

        /**
         * The potted plant block state generator.
         */
        val POTTED_PLANT_BLOCK_STATE: ContentGenerator =
            PrefixedBlockState("Potted Plant Block State", "potted", GeneratorInfo.POTTED_PLANTS)

        /**
         * The potted plant loot table generator.
         */
        val POTTED_PLANT_LOOT_TABLE: ContentGenerator = PottedPlantLootTable

        /**
         * A list of all potted plant generators.
         */
        val allPottedPlants = listOf(POTTED_PLANT_BLOCK_MODEL, POTTED_PLANT_BLOCK_STATE, POTTED_PLANT_LOOT_TABLE)

        // CHESTS

        /**
         * The chest model generator.
         */
        val CHEST_BLOCK_MODEL: ContentGenerator = ChestBlockModel

        /**
         * The chest block state generator.
         */
        val CHEST_BLOCK_STATE: ContentGenerator = SuffixedBlockState("Chest Block State", "chest", GeneratorInfo.CHESTS)

        /**
         * The chest item model generator.
         */
        val CHEST_ITEM_MODEL: ContentGenerator = ChestItemModel

        /**
         * The chest loot table generator.
         */
        val CHEST_LOOT_TABLE: ContentGenerator = ChestLootTable

        /**
         * A list of all chest generators.
         */
        val allChests = listOf(CHEST_BLOCK_MODEL, CHEST_BLOCK_STATE, CHEST_ITEM_MODEL, CHEST_LOOT_TABLE)

        // SCAFFOLDING

        /**
         * The scaffolding block model generator.
         */
        val SCAFFOLDING_BLOCK_MODEL: ContentGenerator = ScaffoldingBlockModel

        /**
         * The scaffolding block state generator.
         */
        val SCAFFOLDING_BLOCK_STATE: ContentGenerator = ScaffoldingBlockState

        /**
         * The scaffolding item model generator.
         */
        val SCAFFOLDING_ITEM_MODEL: ContentGenerator =
            SuffixedBlockItemModel("Scaffolding", "scaffolding", GeneratorInfo.SCAFFOLDING)

        /**
         * The scaffolding loot table generator.
         */
        val SCAFFOLDING_LOOT_TABLE: ContentGenerator = ScaffoldingLootTable

        /**
         * A list of all scaffolding generators.
         */
        val allScaffolding =
            listOf(SCAFFOLDING_BLOCK_MODEL, SCAFFOLDING_BLOCK_STATE, SCAFFOLDING_ITEM_MODEL, SCAFFOLDING_LOOT_TABLE)

        // BARRELS

        /**
         * The barrel block model generator.
         */
        val BARREL_BLOCK_MODEL: ContentGenerator = BarrelBlockModel

        /**
         * The barrel block state generator.
         */
        val BARREL_BLOCK_STATE: ContentGenerator = BarrelBlockState

        /**
         * The barrel item model generator.
         */
        val BARREL_ITEM_MODEL: ContentGenerator = SuffixedBlockItemModel("Barrel", "barrel", GeneratorInfo.BARRELS)

        /**
         * The barrel loot table generator.
         */
        val BARREL_LOOT_TABLE: ContentGenerator = BarrelLootTable

        /**
         * A list of all barrel generators.
         */
        val allBarrels = listOf(BARREL_BLOCK_MODEL, BARREL_BLOCK_STATE, BARREL_ITEM_MODEL, BARREL_LOOT_TABLE)

        // CAMPFIRES

        /**
         * The campfire block model generator.
         */
        val CAMPFIRE_BLOCK_MODEL: ContentGenerator = CampfireBlockModel

        /**
         * The campfire block state generator.
         */
        val CAMPFIRE_BLOCK_STATE: ContentGenerator = CampfireBlockState

        /**
         * The campfire item model generator.
         */
        val CAMPFIRE_ITEM_MODEL: ContentGenerator = SuffixedItemModel(
            parent = Identifier.mc("item/generated"),
            display = "Campfire",
            suffix = "campfire",
            info = GeneratorInfo.CAMPFIRES
        )

        /**
         * The campfire loot table generator.
         */
        val CAMPFIRE_LOOT_TABLE: ContentGenerator = CampfireLootTable

        /**
         * A list of all campfire generators.
         */
        val allCampfires = listOf(CAMPFIRE_BLOCK_MODEL, CAMPFIRE_BLOCK_STATE, CAMPFIRE_ITEM_MODEL, CAMPFIRE_LOOT_TABLE)
    }

    /**
     * A list of all generators.
     */
    val allGens = sequence {
        yield(BASIC_BLOCK_MODEL)
        yield(BASIC_ITEM_MODEL)
        yield(BASIC_HANDHELD_ITEM_MODEL)
        yield(BASIC_BLOCK_ITEM_MODEL)
        yield(BASIC_BLOCK_STATE)
        yield(BASIC_LOOT_TABLE)
        yield(PLACEHOLDER_TEXTURE_BLOCK)
        yield(PLACEHOLDER_TEXTURE_ITEM)

        // Ore
        yield(ORE_TEMPLATE_MODEL)
        yield(STONE_ORE_BLOCK_MODEL)
        yield(NETHERRACK_ORE_BLOCK_MODEL)

        // Pillar
        yield(PILLAR_BLOCK_MODEL)
        yield(PILLAR_BLOCK_STATE)

        yieldAll(Variants.allSlabs)
        yieldAll(Variants.allStairs)
        yieldAll(Variants.allPressurePlates)
        yieldAll(Variants.allButtons)
        yieldAll(Variants.allFences)
        yieldAll(Variants.allWalls)
        yieldAll(Variants.allSigns)
        yieldAll(Variants.allFenceGates)
        yieldAll(Variants.allTrapdoors)
        yieldAll(Variants.allDoors)
        yieldAll(Variants.allLadders)
        yieldAll(Variants.allLeaves)
        yieldAll(Variants.allPottedPlants)
        yieldAll(Variants.allChests)
        yieldAll(Variants.allScaffolding)
        yieldAll(Variants.allBarrels)
        yieldAll(Variants.allCampfires)
    }.toList()
}
