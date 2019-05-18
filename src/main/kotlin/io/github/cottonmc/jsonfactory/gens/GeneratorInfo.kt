package io.github.cottonmc.jsonfactory.gens

/**
 * Contains the [category] and [subcategory] of a [ContentGenerator].
 *
 * The [subcategory] is optional.
 *
 * @since 0.4.0
 */
data class GeneratorInfo(val category: Category, val subcategory: Subcategory? = null) {
    /**
     * A generator category.
     */
    interface Category {
        /**
         * The identifier.
         */
        val id: String

        /**
         * The placeholder texture output path of the category,
         * relative to `/assets/<namespace>/textures`. Example: `block`.
         *
         * Can be set to `null` if [PlaceholderTexture] is not used,
         * but using `PlaceholderTexture` requires a non-null value.
         */
        val placeholderTexturePath: String?
    }

    enum class Categories(
        override val id: String, override val placeholderTexturePath: String,
        val description: String? = null
    ) : Category {
        Block("categories.block", "block"), Item("categories.item", "item"),
        BlockVariants("categories.block_variants", "block", "Suffixes will be added to the output files' names.");
    }

    /**
     * A subcategory within a [Category].
     * @since 0.4.0
     */
    interface Subcategory {
        /**
         * The identifier.
         */
        val id: String
    }

    enum class Subcategories(_id: String, val description: String? = null) : Subcategory {
        Ores("ores", description = "Note: The child ore models assume that the template is named `modid:ore_template`."),
        Pillars("pillars"),
        Slabs("slabs"),
        Stairs("stairs"),
        PressurePlates("pressure_plates"),
        Buttons("buttons"),
        Fences("fences"),
        Walls("walls"),
        Signs("signs"),
        FenceGates("fence_gates"),
        Trapdoors("trapdoors"),
        Doors("doors"),
        Ladders("ladders"),
        Leaves("leaves"),
        PottedPlants("potted_plants"),
        Chests("chests"),
        Scaffolding("scaffolding"),
        Campfires("campfires"),
        Barrels("barrels"),
        Saplings("saplings");

        override val id = "subcategories.$_id"
    }

    companion object {
        val BLOCK = GeneratorInfo(Categories.Block)
        val ITEM = GeneratorInfo(Categories.Item)

        val ORES = GeneratorInfo(Categories.Block, Subcategories.Ores)
        val PILLARS = GeneratorInfo(Categories.Block, Subcategories.Pillars)
        val SLABS = GeneratorInfo(Categories.BlockVariants, Subcategories.Slabs)
        val STAIRS = GeneratorInfo(Categories.BlockVariants, Subcategories.Stairs)
        val PRESSURE_PLATES = GeneratorInfo(Categories.BlockVariants, Subcategories.PressurePlates)
        val BUTTONS = GeneratorInfo(Categories.BlockVariants, Subcategories.Buttons)
        val FENCES = GeneratorInfo(Categories.BlockVariants, Subcategories.Fences)
        val WALLS = GeneratorInfo(Categories.BlockVariants, Subcategories.Walls)
        val SIGNS = GeneratorInfo(Categories.BlockVariants, Subcategories.Signs)
        val FENCE_GATES = GeneratorInfo(Categories.BlockVariants, Subcategories.FenceGates)
        val TRAPDOORS = GeneratorInfo(Categories.BlockVariants, Subcategories.Trapdoors)
        val DOORS = GeneratorInfo(Categories.BlockVariants, Subcategories.Doors)
        val LADDERS = GeneratorInfo(Categories.BlockVariants, Subcategories.Ladders)
        val LEAVES = GeneratorInfo(Categories.BlockVariants, Subcategories.Leaves)
        val POTTED_PLANTS = GeneratorInfo(Categories.BlockVariants, Subcategories.PottedPlants)
        val CHESTS = GeneratorInfo(Categories.BlockVariants, Subcategories.Chests)
        val SCAFFOLDING = GeneratorInfo(Categories.BlockVariants, Subcategories.Scaffolding)
        val BARRELS = GeneratorInfo(Categories.BlockVariants, Subcategories.Barrels)
        val CAMPFIRES = GeneratorInfo(Categories.BlockVariants, Subcategories.Campfires)
        val SAPLINGS = GeneratorInfo(Categories.BlockVariants, Subcategories.Saplings)
    }
}
