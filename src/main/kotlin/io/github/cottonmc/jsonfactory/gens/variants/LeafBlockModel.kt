package io.github.cottonmc.jsonfactory.gens.variants

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.Model
import io.github.cottonmc.jsonfactory.output.Suffixed

internal object LeafBlockModel : ContentGenerator("Leaf Block Model", "models/block", GeneratorInfo.LEAVES) {
    override fun generate(id: Identifier) = listOf(
        Suffixed(
            Model(
                parent = Identifier.mc("block/leaves"),
                textures = mapOf(
                    "all" to id.prefixPath("block/")
                )
            ),
            "leaves"
        )
    )
}
