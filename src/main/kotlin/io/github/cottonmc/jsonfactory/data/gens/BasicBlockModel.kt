package io.github.cottonmc.jsonfactory.data.gens

import io.github.cottonmc.jsonfactory.data.ContentGenerator
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.output.Model

object BasicBlockModel : ContentGenerator<Model>("Basic Block Model", "models/block", Category.Block) {
    override fun generate(id: Identifier) = listOf(Model(
        parent = "block/cube_all",
        textures = mapOf(
            "all" to "${id.namespace}:block/${id.path}"
        )
    ))
}
