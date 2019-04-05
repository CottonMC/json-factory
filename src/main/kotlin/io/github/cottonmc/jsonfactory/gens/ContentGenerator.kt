package io.github.cottonmc.jsonfactory.gens

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.output.Output

/**
 * Generates content from [Identifier]s.
 *
 * @property displayName the display name of this generator
 * @property path the output directory
 * @property info the generator info
 * @property extension the file extension (without the dot)
 * @property resourceRoot the resource root
 */
abstract class ContentGenerator(
    val displayName: String,
    val path: String,
    val info: GeneratorInfo,
    val extension: String = "json",
    val resourceRoot: ResourceRoot = ResourceRoot.Assets
) {
    /**
     * Generates Outputs from an [id].
     */
    abstract fun generate(id: Identifier): List<Output>
    override fun toString() = displayName

    enum class ResourceRoot(val path: String) {
        Assets("assets"), Data("data")
    }
}
