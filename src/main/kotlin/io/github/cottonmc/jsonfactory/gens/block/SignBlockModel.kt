package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.Model
import io.github.cottonmc.jsonfactory.output.suffixed

internal object SignBlockModel : ContentGenerator("signs.block_model", "models/block", GeneratorInfo.SIGNS) {
    override fun generate(id: Identifier) = listOf(
        Model(textures = mapOf("particle" to id.prefixPath("block/"))).suffixed("sign")
    )
}
