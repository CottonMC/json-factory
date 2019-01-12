package io.github.cottonmc.jsonfactory.gens

import io.github.cottonmc.jsonfactory.output.Output
import io.github.cottonmc.jsonfactory.data.Identifier

/**
 * Generates content from [Identifier]s.
 *
 * @property displayName the display name of this generator
 * @property path the output directory
 * @property category the gui category
 * @property extension the file extension (without the dot)
 * @property resourceRoot the resource root
 */
abstract class ContentGenerator(
    val displayName: String,
    val path: String,
    val category: Category,
    val extension: String = "json",
    val resourceRoot: ResourceRoot = ResourceRoot.Assets
) {
    /**
     * Generates Outputs from an [id].
     */
    abstract fun generate(id: Identifier): List<Output>
    override fun toString() = displayName

    interface Category {
        val displayName: String
        val path: String
        val description: String?
    }

    enum class Categories(override val displayName: String, override val path: String,
                          override val description: String? = null) : Category {
        Block("Block", "block"), Item("Item", "item"), Ore("Ore", "block"),
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

    enum class ResourceRoot(val path: String) {
        Assets("assets"), Data("data")
    }
}
