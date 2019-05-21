package io.github.cottonmc.jsonfactory.gens

/**
 * A generator resource root, equivalent to the Minecraft resource root with the same [path].
 */
enum class ResourceRoot(val path: String) {
    /**
     * The *"assets"* resource root, contains models and textures, etc.
     */
    Assets("assets"),

    /**
     * The *"data"* resource root, contains loot tables and recipes, etc.
     */
    Data("data")
}
