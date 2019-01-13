package io.github.cottonmc.jsonfactory.gens.variants

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.output.Model
import io.github.cottonmc.jsonfactory.output.Suffixed

object SlabBlockModel : ContentGenerator("Slab Block Model", "models/block",
    Categories.BlockVariants
) {
    override fun generate(id: Identifier) = listOf(
        Suffixed(Model(
            parent = Identifier.mc("block/slab"),
            textures = mapOf(
                "bottom" to id.prefixPath("block/"),
                "top" to id.prefixPath("block/"),
                "side" to id.prefixPath("block/")
            )
        ), "slab"),
        Suffixed(Model(
            parent = Identifier.mc("block/slab_top"),
            textures = mapOf(
                "bottom" to id.prefixPath("block/"),
                "top" to id.prefixPath("block/"),
                "side" to id.prefixPath("block/")
            )
        ), "slab_top")
    )
}
