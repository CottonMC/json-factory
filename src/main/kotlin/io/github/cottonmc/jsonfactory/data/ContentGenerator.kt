package io.github.cottonmc.jsonfactory.data

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
     * Generates a [T] from an [id].
     */
    abstract fun generate(id: Identifier): T
    override fun toString() = displayName

    enum class Category(val displayName: String, val path: String) {
        Block("Block", "block"), Item("Item", "item")
    }

    enum class ResourceRoot(val path: String) {
        Assets("assets"), Data("data")
    }
}
