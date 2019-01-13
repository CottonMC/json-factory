package io.github.cottonmc.jsonfactory.gens.variants

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.output.Model
import io.github.cottonmc.jsonfactory.output.Suffixed

object SignBlockModel : ContentGenerator("Sign Block Model", "models/block", Categories.BlockVariants) {
    override fun generate(id: Identifier) = listOf(
        Suffixed(
            Model(textures = mapOf("particle" to id.prefixPath("block/"))),
            "sign"
        )
    )
}
