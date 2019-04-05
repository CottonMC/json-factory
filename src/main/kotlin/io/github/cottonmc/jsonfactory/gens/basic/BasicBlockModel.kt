package io.github.cottonmc.jsonfactory.gens.basic

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.Model

internal object BasicBlockModel : ContentGenerator("Basic Block Model", "models/block", GeneratorInfo.BLOCK) {
    override fun generate(id: Identifier) = listOf(Model(
        parent = Identifier.mc("block/cube_all"),
        textures = mapOf(
            "all" to id.prefixPath("block/")
        )
    ))
}
