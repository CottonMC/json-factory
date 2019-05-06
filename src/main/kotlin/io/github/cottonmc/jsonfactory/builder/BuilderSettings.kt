package io.github.cottonmc.jsonfactory.builder

import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.Gens

/**
 * an enum regrouping the settings, just so that we can read the builder a lot easier
 * */
enum class BuilderSettings(vararg val generators:ContentGenerator) {

    /**
     * basic blocks
     * */
    SLABS(*Gens.Variants.allSlabs.toTypedArray()),
    BARREL(*Gens.Variants.allBarrels.toTypedArray()),
    BUTTON(*Gens.Variants.allButtons.toTypedArray()),
    CAMPFIRE(*Gens.Variants.allCampfires.toTypedArray()),
    CHEST(*Gens.Variants.allChests.toTypedArray()),
    DOOR(*Gens.Variants.allDoors.toTypedArray()),
    FENCES(*Gens.Variants.allFenceGates.toTypedArray()),
    FENCE_GATES(*Gens.Variants.allFences.toTypedArray()),
    LADDERS(*Gens.Variants.allLadders.toTypedArray()),
    LEAVES(*Gens.Variants.allLeaves.toTypedArray()),
    POTTED_PLANT(*Gens.Variants.allPottedPlants.toTypedArray()),
    PRESSURE_PLATE(*Gens.Variants.allPressurePlates.toTypedArray()),
    SCAFFOLDING(*Gens.Variants.allScaffolding.toTypedArray()),
    SIGNS(*Gens.Variants.allSigns.toTypedArray()),
    STAIRS(*Gens.Variants.allStairs.toTypedArray()),
    TRAPDOOR(*Gens.Variants.allTrapdoors.toTypedArray()),
    WALL(*Gens.Variants.allWalls.toTypedArray()),
    PILLAR(Gens.PILLAR_BLOCK_MODEL,Gens.PILLAR_BLOCK_STATE,Gens.BASIC_LOOT_TABLE,Gens.BASIC_BLOCK_ITEM_MODEL),

    /**
     * full fences, with gates
     * */
    FULL_FENCE(*Gens.Variants.allFenceGates.toTypedArray(),*Gens.Variants.allFences.toTypedArray()),
    /**
     * Everything that a stone block can reasonable have in vanilla ideas
     * */
    STONE_FULL( *SLABS.generators,
        *BUTTON.generators,
        *PRESSURE_PLATE.generators,
        *STAIRS.generators,
        *WALL.generators,
        *TRAPDOOR.generators),
    /**
     * Everything that a wooden block can reasonable have in vanilla ideas
     * */
    WOOD_FULL(
        *SLABS.generators,
        *BUTTON.generators,
        *DOOR.generators,
        *FULL_FENCE.generators,
        *PRESSURE_PLATE.generators,
        *SIGNS.generators,
        *STAIRS.generators,
        *TRAPDOOR.generators
    ),
    /**
     * Everything that a block should reasonable have, without any tile entities
     * */
    FULL_NO_TILE_GENERIC(
        *SLABS.generators,
        *BUTTON.generators,
        *DOOR.generators,
        *FULL_FENCE.generators,
        *LADDERS.generators,
        *PRESSURE_PLATE.generators,
        *SIGNS.generators,
        *STAIRS.generators,
        *WALL.generators,
        *TRAPDOOR.generators
        ),

}