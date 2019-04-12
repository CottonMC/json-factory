package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.Model
import io.github.cottonmc.jsonfactory.output.Suffixed

internal object FenceBlockModel : ContentGenerator("Fence Block Model", "models/block", GeneratorInfo.FENCES) {
    override fun generate(id: Identifier) = listOf(
        Suffixed(Model(
            parent = Identifier.mc("block/fence_post"),
            textures = mapOf(
                "texture" to id.prefixPath("block/")
            )
        ), "fence_post"),
        Suffixed(Model(
            parent = Identifier.mc("block/fence_side"),
            textures = mapOf(
                "texture" to id.prefixPath("block/")
            )
        ), "fence_side"),
        Suffixed(Model(
            parent = Identifier.mc("block/fence_inventory"),
            textures = mapOf(
                "texture" to id.prefixPath("block/")
            )
        ), "fence_inventory")
    )
}
