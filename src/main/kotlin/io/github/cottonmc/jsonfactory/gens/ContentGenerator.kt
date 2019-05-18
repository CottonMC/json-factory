package io.github.cottonmc.jsonfactory.gens

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.output.Output

/**
 * Generates content from [Identifier]s.
 *
 * @property id the unique identifier of this generator
 * @property path the output directory
 * @property info the generator info
 * @property extension the file extension (without the dot)
 * @property resourceRoot the resource root
 */
interface ContentGenerator {
    val id: String
    val path: String
    val info: GeneratorInfo
    val extension: String
    // TODO: Move ResourceRoot
    val resourceRoot: AbstractContentGenerator.ResourceRoot

    /**
     * Generates [Output]s from an [id].
     */
    fun generate(id: Identifier): List<Output>
}
