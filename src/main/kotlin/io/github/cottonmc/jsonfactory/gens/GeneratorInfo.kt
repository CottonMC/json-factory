package io.github.cottonmc.jsonfactory.gens

data class GeneratorInfo(val category: Category, val subcategory: Subcategory? = null) {
    interface Category {
        val displayName: String
        val path: String
        val description: String?
    }

    enum class Categories(override val displayName: String, override val path: String,
                          override val description: String? = null) :
        Category {
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

    interface Subcategory {
        val displayName: String
        val description: String?
    }

    enum class Subcategories(_displayName: String? = null, override val description: String? = null) :
        Subcategory {
        Ores, Pillars, Slabs, Stairs, PressurePlates("Pressure Plates"), Buttons, Fences, Walls, Signs,
        FenceGates("Fence Gates"), Trapdoors, Doors, Ladders;

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
    }
}
