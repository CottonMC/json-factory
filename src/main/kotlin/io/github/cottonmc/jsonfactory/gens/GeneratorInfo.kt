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
         * The display name.
         */
        val displayName: String

        /**
         * The output path of the category. (Example: `models/block`)
         */
        val path: String

        /**
         * An optional description. Can be a Markdown string.
         */
        val description: String?
    }

    enum class Categories(
        override val displayName: String, override val path: String,
        override val description: String? = null
    ) : Category {
        Block("Block", "block"), Item("Item", "item"),
        BlockVariants("Block Variants", "block", "Suffixes will be added to the output files' names.");

        companion object {
            private val _categories = LinkedHashSet<Category>()
            val categories: Set<Category> get() = _categories

            init {
                values().forEach(::addCategory)
            }

            fun addCategory(category: Category) {
                _categories += category
            }
        }
    }

    /**
     * A subcategory within a [Category].
     * @since 0.4.0
     */
    interface Subcategory {
        /**
         * The display name.
         */
        val displayName: String

        /**
         * An optional description. Can be a Markdown string.
         */
        val description: String?
    }

    enum class Subcategories(_displayName: String? = null, override val description: String? = null) : Subcategory {
        Ores(description = "Note: The child ore models assume that the template is named `modid:ore_template`."),
        Pillars,
        Slabs,
        Stairs,
        PressurePlates("Pressure Plates"),
        Buttons,
        Fences,
        Walls,
        Signs,
        FenceGates("Fence Gates"),
        Trapdoors,
        Doors,
        Ladders,
        Leaves,
        PottedPlants("Potted Plants"),
        Chests,
        Scaffolding,
        Campfires,
        Barrels,
        Saplings;

        override val displayName = _displayName ?: name
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
