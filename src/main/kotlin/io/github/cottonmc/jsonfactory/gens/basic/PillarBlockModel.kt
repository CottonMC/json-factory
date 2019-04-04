package io.github.cottonmc.jsonfactory.gens.basic

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.output.Model

internal object PillarBlockModel : ContentGenerator("Pillar Block Model", "models/block",
    Categories.Block
) {
    override fun generate(id: Identifier) = listOf(Model(
        parent = Identifier.mc("block/cube_column"),
        textures = mapOf(
            "side" to id.prefixPath("block/"),
            "end" to id.copy(path = "block/${id.path}_top")
        )
    ))
}
