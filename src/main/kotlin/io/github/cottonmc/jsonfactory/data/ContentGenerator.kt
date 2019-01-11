package io.github.cottonmc.jsonfactory.data

abstract class ContentGenerator<out T : Output>(
    val displayName: String,
    val path: String,
    val category: Category,
    val extension: String = "json",
    val resourceRoot: ResourceRoot = ResourceRoot.Assets
) {
    abstract fun generate(id: Identifier): T
    override fun toString() = displayName

    enum class Category(val displayName: String, val path: String) {
        Block("Block", "block"), Item("Item", "item")
    }

    enum class ResourceRoot(val path: String) {
        Assets("assets"), Data("data")
    }
}
