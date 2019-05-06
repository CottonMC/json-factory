package io.github.cottonmc.jsonfactory.gens

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.output.Output

/**
 * Wrapper class, that can be used to extend the existing generators, without modifying them.
 * */
class DecoratedContentGenerator(
    val superGenerator: ContentGeneratorTemplate,
    val decorator: (Identifier, List<Output>) -> List<Output>
) : ContentGeneratorTemplate by superGenerator {

    override fun generate(id: Identifier): List<Output> {
        val list = superGenerator.generate(id)
        return decorator(id,list)
    }
}