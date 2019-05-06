package io.github.cottonmc.jsonfactory.gens

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.output.Output

/**
 * Template for content generators
 * @see ContentGenerator
 * */
interface ContentGeneratorTemplate {
    val displayName: String
    val path: String
    val info: GeneratorInfo
    val extension: String
    val resourceRoot: ContentGenerator.ResourceRoot

    fun generate(id: Identifier): List<Output>
}