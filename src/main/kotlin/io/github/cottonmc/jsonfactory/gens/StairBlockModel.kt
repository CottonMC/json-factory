package io.github.cottonmc.jsonfactory.gens

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.output.Model
import io.github.cottonmc.jsonfactory.output.Suffixed

object StairBlockModel : ContentGenerator<Model>("Stair Block Model", "models/block", Categories.BlockVariants) {
    override fun generate(id: Identifier) = listOf(
        Suffixed(Model(
            parent = Identifier.mc("block/stairs"),
            textures = mapOf(
                "bottom" to id.copy(path = "block/" + id.path),
                "top" to id.copy(path = "block/" + id.path),
                "side" to id.copy(path = "block/" + id.path)
            )
        ), "stairs"),
        Suffixed(Model(
            parent = Identifier.mc("block/inner_stairs"),
            textures = mapOf(
                "bottom" to id.copy(path = "block/" + id.path),
                "top" to id.copy(path = "block/" + id.path),
                "side" to id.copy(path = "block/" + id.path)
            )
        ), "stairs_inner"),
        Suffixed(Model(
            parent = Identifier.mc("block/outer_stairs"),
            textures = mapOf(
                "bottom" to id.copy(path = "block/" + id.path),
                "top" to id.copy(path = "block/" + id.path),
                "side" to id.copy(path = "block/" + id.path)
            )
        ), "stairs_outer")
    )
}
