package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.Model

internal object BasicBlockModel : AbstractContentGenerator("Basic Block Model", "models/block", GeneratorInfo.BLOCK) {
    override fun generate(id: Identifier) = listOf(
        Model(
            parent = Identifier.mc("block/cube_all"),
            textures = mapOf(
                "all" to id.prefixPath("block/")
            )
        )
    )
}
