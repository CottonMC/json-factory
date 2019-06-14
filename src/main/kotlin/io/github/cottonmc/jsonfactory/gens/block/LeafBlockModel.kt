package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.Model
import io.github.cottonmc.jsonfactory.output.suffixed

internal object LeafBlockModel : AbstractContentGenerator("leaves.block_model", "models/block", GeneratorInfo.LEAVES) {
    override fun generate(id: Identifier) = listOf(
        Model(
            parent = Identifier.mc("block/leaves"),
            textures = mapOf(
                "all" to id.wrapPath("block/", "_leaves")
            )
        ).suffixed("leaves")
    )
}
