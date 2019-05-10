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
interface ContentGenerator {
    val displayName: String
    val path: String
    val info: GeneratorInfo
    val extension: String
    val resourceRoot: AbstractContentGenerator.ResourceRoot

    /**
     * Generates [Output]s from an [id].
     */
    fun generate(id: Identifier): List<Output>
}
