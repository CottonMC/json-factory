package io.github.cottonmc.jsonfactory.data.gens

import io.github.cottonmc.jsonfactory.data.ContentGenerator
import io.github.cottonmc.jsonfactory.data.types.Identifier
import io.github.cottonmc.jsonfactory.data.output.Model
import io.github.cottonmc.jsonfactory.data.output.Suffixed

object SlabBlockModel : ContentGenerator<Model>("Slab Block Model", "models/block", Categories.Block) {
    override fun generate(id: Identifier) = listOf(
        Suffixed(Model(
            parent = Identifier.mc("block/slab"),
            textures = mapOf(
                "bottom" to id.copy(path = "block/" + id.path),
                "top" to id.copy(path = "block/" + id.path),
                "side" to id.copy(path = "block/" + id.path)
            )
        ), "slab"),
        Suffixed(Model(
            parent = Identifier.mc("block/slab_top"),
            textures = mapOf(
                "bottom" to id.copy(path = "block/" + id.path),
                "top" to id.copy(path = "block/" + id.path),
                "side" to id.copy(path = "block/" + id.path)
            )
        ), "slab_top")
    )
}
