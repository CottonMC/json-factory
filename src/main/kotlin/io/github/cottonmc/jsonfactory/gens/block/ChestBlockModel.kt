package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.Model
import io.github.cottonmc.jsonfactory.output.suffixed

internal object ChestBlockModel : AbstractContentGenerator("chest.block_model", "models/block", GeneratorInfo.CHESTS) {
    override fun generate(id: Identifier) = listOf(
        Model(
            textures = mapOf(
                "particle" to id
            )
        ).suffixed("chest")
    )
}
