package io.github.cottonmc.jsonfactory.data.gens

import io.github.cottonmc.jsonfactory.data.ContentGenerator
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.Output
import io.github.cottonmc.jsonfactory.data.types.Model

object SlabBlockModel : ContentGenerator<Model>("Slab Block Model", "models/block", Category.Block) {
    override fun generate(id: Identifier) = listOf(
        Output.Container(Model(
            parent = "block/slab",
            textures = mapOf(
                "bottom" to "${id.namespace}:block/${id.path}",
                "top" to "${id.namespace}:block/${id.path}",
                "side" to "${id.namespace}:block/${id.path}"
            )
        ), "slab"),
        Output.Container(Model(
            parent = "block/slab_top",
            textures = mapOf(
                "bottom" to "${id.namespace}:block/${id.path}",
                "top" to "${id.namespace}:block/${id.path}",
                "side" to "${id.namespace}:block/${id.path}"
            )
        ), "slab_top")
    )
}
