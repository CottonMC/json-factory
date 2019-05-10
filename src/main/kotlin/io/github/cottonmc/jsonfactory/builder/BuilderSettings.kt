package io.github.cottonmc.jsonfactory.builder

import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.Gens

/**
 * an enum regrouping the settings, just so that we can read the builder a lot easier
 * */
enum class BuilderSettings(val generators: Set<ContentGenerator>) {

    /**
     * basic blocks
     * */
    SLABS(setOf(*Gens.Variants.allSlabs.toTypedArray())),
    BARREL(setOf(*Gens.Variants.allBarrels.toTypedArray())),
    BUTTON(setOf(*Gens.Variants.allButtons.toTypedArray())),
    CAMPFIRE(setOf(*Gens.Variants.allCampfires.toTypedArray())),
    CHEST(setOf(*Gens.Variants.allChests.toTypedArray())),
    DOOR(setOf(*Gens.Variants.allDoors.toTypedArray())),
    FENCES(setOf(*Gens.Variants.allFenceGates.toTypedArray())),
    FENCE_GATES(setOf(*Gens.Variants.allFences.toTypedArray())),
    LADDERS(setOf(*Gens.Variants.allLadders.toTypedArray())),
    LEAVES(setOf(*Gens.Variants.allLeaves.toTypedArray())),
    POTTED_PLANT(setOf(*Gens.Variants.allPottedPlants.toTypedArray())),
    PRESSURE_PLATE(setOf(*Gens.Variants.allPressurePlates.toTypedArray())),
    SCAFFOLDING(setOf(*Gens.Variants.allScaffolding.toTypedArray())),
    SIGNS(setOf(*Gens.Variants.allSigns.toTypedArray())),
    STAIRS(setOf(*Gens.Variants.allStairs.toTypedArray())),
    TRAPDOOR(setOf(*Gens.Variants.allTrapdoors.toTypedArray())),
    WALL(setOf(*Gens.Variants.allWalls.toTypedArray())),
    PILLAR(setOf(Gens.pillarBlockModel, Gens.pillarBlockState, Gens.basicLootTable, Gens.basicBlockItemModel)),

    /**
     * full fences, with gates
     * */
    FULL_FENCE(setOf(*Gens.Variants.allFenceGates.toTypedArray(),*Gens.Variants.allFences.toTypedArray())),
    /**
     * Everything that a stone type block can reasonable have in vanilla ideas
     * */
    FULL_STONE(
        SLABS.generators +
        BUTTON.generators+
        PRESSURE_PLATE.generators+
        STAIRS.generators+
        WALL.generators+
        TRAPDOOR.generators),
    /**
     * Everything that a wooden type block can reasonable have in vanilla ideas
     * */
    FULL_WOOD(
        SLABS.generators
        +BUTTON.generators
        +DOOR.generators
        +FULL_FENCE.generators
        +PRESSURE_PLATE.generators
        +SIGNS.generators
        +STAIRS.generators
        +TRAPDOOR.generators
    ),
    /**
     * Everything that a block type could reasonable have, if it is not a tile entities
     * */
    FULL_NO_BLOCK_ENTITY_GENERIC(
        SLABS.generators
        +BUTTON.generators
        +DOOR.generators
        +FULL_FENCE.generators
        +LADDERS.generators
        +PRESSURE_PLATE.generators
        +SIGNS.generators
        +STAIRS.generators
        +WALL.generators
        +TRAPDOOR.generators
        ),

}