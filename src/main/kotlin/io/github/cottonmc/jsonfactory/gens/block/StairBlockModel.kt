package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.Model
import io.github.cottonmc.jsonfactory.output.suffixed

internal object StairBlockModel : ContentGenerator("Stair Block Model", "models/block", GeneratorInfo.STAIRS) {
    override fun generate(id: Identifier) = listOf(
        Model(
            parent = Identifier.mc("block/stairs"),
            textures = mapOf(
                "bottom" to id.prefixPath("block/"),
                "top" to id.prefixPath("block/"),
                "side" to id.prefixPath("block/")
            )
        ).suffixed("stairs"),
        Model(
            parent = Identifier.mc("block/inner_stairs"),
            textures = mapOf(
                "bottom" to id.prefixPath("block/"),
                "top" to id.prefixPath("block/"),
                "side" to id.prefixPath("block/")
            )
        ).suffixed("stairs_inner"),
        Model(
            parent = Identifier.mc("block/outer_stairs"),
            textures = mapOf(
                "bottom" to id.prefixPath("block/"),
                "top" to id.prefixPath("block/"),
                "side" to id.prefixPath("block/")
            )
        ).suffixed("stairs_outer")
    )
}
