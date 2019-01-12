package io.github.cottonmc.jsonfactory.gens

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.output.Model
import io.github.cottonmc.jsonfactory.output.Suffixed

object SlabBlockModel : ContentGenerator("Slab Block Model", "models/block", Categories.BlockVariants) {
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
