package io.github.cottonmc.jsonfactory.gens.block.sign

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.Model
import io.github.cottonmc.jsonfactory.output.Suffixed

internal object SignBlockModel : ContentGenerator("Sign Block Model", "models/block", GeneratorInfo.SIGNS) {
    override fun generate(id: Identifier) = listOf(
        Suffixed(
            Model(textures = mapOf("particle" to id.prefixPath("block/"))),
            "sign"
        )
    )
}
