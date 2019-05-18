package io.github.cottonmc.jsonfactory.gens

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.output.Output

/**
 * Wrapper class, that can be used to extend the existing generators, without modifying them.
 *
 * @param rootGenerator the base generator that will be decorated
 * @param decorator the function used to modify the outputs of the root generator
 */
class DecoratedContentGenerator(
    private val rootGenerator: ContentGenerator,
    private val decorator: (Identifier, List<Output>) -> List<Output>
) : ContentGenerator by rootGenerator {
    override fun generate(id: Identifier): List<Output> {
        val list = rootGenerator.generate(id)
        return decorator(id, list)
    }
}
