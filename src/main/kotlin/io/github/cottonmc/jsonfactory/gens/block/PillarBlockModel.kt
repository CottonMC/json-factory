package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.Model

internal object PillarBlockModel :
    AbstractContentGenerator("Pillar Block Model", "models/block", GeneratorInfo.PILLARS) {
    override fun generate(id: Identifier) = listOf(
        Model(
            parent = Identifier.mc("block/cube_column"),
            textures = mapOf(
                "side" to id.prefixPath("block/"),
                "end" to id.copy(path = "block/${id.path}_top")
            )
        )
    )
}
