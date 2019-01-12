package io.github.cottonmc.jsonfactory.data

import io.github.cottonmc.jsonfactory.data.output.Output
import io.github.cottonmc.jsonfactory.data.types.Identifier

/**
 * Generates content from [Identifier]s.
 *
 * @property displayName the display name of this generator
 * @property path the output directory
 * @property category the gui category
 * @property extension the file extension (without the dot)
 * @property resourceRoot the resource root
 */
abstract class ContentGenerator<out T : Output>(
    val displayName: String,
    val path: String,
    val category: Category,
    val extension: String = "json",
    val resourceRoot: ResourceRoot = ResourceRoot.Assets
) {
    /**
     * Generates [T]s from an [id].
     */
    abstract fun generate(id: Identifier): List<Output>
    override fun toString() = displayName

    interface Category {
        val displayName: String
        val path: String
    }

    enum class Categories(override val displayName: String, override val path: String) : Category {
        Block("Block", "block"), Item("Item", "item"), Ore("Ore", "block");

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
