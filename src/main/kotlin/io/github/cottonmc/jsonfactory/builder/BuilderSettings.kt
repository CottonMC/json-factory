package io.github.cottonmc.jsonfactory.builder

import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.Gens

/**
 * An enum containing generator groups for the builder.
 */
enum class BuilderSettings(val generators: Set<ContentGenerator>) {
    SLABS(Gens.Variants.ALL_SLABS),
    BARREL(Gens.Variants.ALL_BARRELS),
    BUTTON(Gens.Variants.ALL_BUTTONS),
    CAMPFIRE(Gens.Variants.ALL_CAMPFIRES),
    CHEST(Gens.Variants.ALL_CHESTS),
    DOOR(Gens.Variants.ALL_DOORS),
    FENCES(Gens.Variants.ALL_FENCE_GATES),
    FENCE_GATES(Gens.Variants.ALL_FENCES),
    LADDERS(Gens.Variants.ALL_LADDERS),
    LEAVES(Gens.Variants.ALL_LEAVES),
    POTTED_PLANT(Gens.Variants.ALL_POTTED_PLANTS),
    PRESSURE_PLATE(Gens.Variants.ALL_PRESSURE_PLATES),
    SCAFFOLDING(Gens.Variants.ALL_SCAFFOLDING),
    SIGNS(Gens.Variants.ALL_SIGNS),
    STAIRS(Gens.Variants.ALL_STAIRS),
    TRAPDOOR(Gens.Variants.ALL_TRAPDOORS),
    WALL(Gens.Variants.ALL_WALLS),
    PILLAR(setOf(Gens.pillarBlockModel, Gens.pillarBlockState, Gens.basicLootTable, Gens.basicBlockItemModel)),

    /**
     * All fence and fence gate generators.
     */
    FULL_FENCE(Gens.Variants.ALL_FENCE_GATES + Gens.Variants.ALL_FENCES),

    /**
     * Everything that a stone type block can reasonable have in vanilla ideas.
     */
    FULL_STONE(
        SLABS.generators
                + BUTTON.generators
                + PRESSURE_PLATE.generators
                + STAIRS.generators
                + WALL.generators
                + TRAPDOOR.generators
    ),

    /**
     * Everything that a wooden block can reasonable have in vanilla ideas.
     * */
    FULL_WOOD(
        SLABS.generators
                + BUTTON.generators
                + DOOR.generators
                + FULL_FENCE.generators
                + PRESSURE_PLATE.generators
                + SIGNS.generators
                + STAIRS.generators
                + TRAPDOOR.generators
    ),

    /**
     * Everything that a block type could reasonable have, if it is not a block entity.
     */
    FULL_NO_BLOCK_ENTITY_GENERIC(
        SLABS.generators
                + BUTTON.generators
                + DOOR.generators
                + FULL_FENCE.generators
                + LADDERS.generators
                + PRESSURE_PLATE.generators
                + SIGNS.generators
                + STAIRS.generators
                + WALL.generators
                + TRAPDOOR.generators
    ),
}
