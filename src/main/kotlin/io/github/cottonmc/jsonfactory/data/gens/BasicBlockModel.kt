package io.github.cottonmc.jsonfactory.data.gens

import io.github.cottonmc.jsonfactory.data.types.Identifier
import io.github.cottonmc.jsonfactory.data.output.Model

object BasicBlockModel : ContentGenerator<Model>("Basic Block Model", "models/block", Categories.Block) {
    override fun generate(id: Identifier) = listOf(Model(
        parent = Identifier.mc("block/cube_all"),
        textures = mapOf(
            "all" to id.copy(path = "block/" + id.path)
        )
    ))
}
