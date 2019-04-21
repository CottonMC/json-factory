package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.Model
import io.github.cottonmc.jsonfactory.output.suffixed

internal object FenceBlockModel : ContentGenerator("fences.block_model", "models/block", GeneratorInfo.FENCES) {
    override fun generate(id: Identifier) = listOf(
        Model(
            parent = Identifier.mc("block/fence_post"),
            textures = mapOf(
                "texture" to id.prefixPath("block/")
            )
        ).suffixed("fence_post"),
        Model(
            parent = Identifier.mc("block/fence_side"),
            textures = mapOf(
                "texture" to id.prefixPath("block/")
            )
        ).suffixed("fence_side"),
        Model(
            parent = Identifier.mc("block/fence_inventory"),
            textures = mapOf(
                "texture" to id.prefixPath("block/")
            )
        ).suffixed("fence_inventory")
    )
}
