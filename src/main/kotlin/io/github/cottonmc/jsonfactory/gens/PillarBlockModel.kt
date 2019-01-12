package io.github.cottonmc.jsonfactory.gens

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.output.Model

object PillarBlockModel : ContentGenerator<Model>("Pillar Block Model", "models/block", Categories.Block) {
    override fun generate(id: Identifier) = listOf(Model(
        parent = Identifier.mc("block/cube_column"),
        textures = mapOf(
            "side" to id.copy(path = "block/" + id.path),
            "end" to id.copy(path = "block/${id.path}_top")
        )
    ))
}
