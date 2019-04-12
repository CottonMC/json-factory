package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.Model
import io.github.cottonmc.jsonfactory.output.suffixed

internal object SlabBlockModel : ContentGenerator("Slab Block Model", "models/block", GeneratorInfo.SLABS) {
    override fun generate(id: Identifier) = listOf(
        Model(
            parent = Identifier.mc("block/slab"),
            textures = mapOf(
                "bottom" to id.prefixPath("block/"),
                "top" to id.prefixPath("block/"),
                "side" to id.prefixPath("block/")
            )
        ).suffixed("slab"),
        Model(
            parent = Identifier.mc("block/slab_top"),
            textures = mapOf(
                "bottom" to id.prefixPath("block/"),
                "top" to id.prefixPath("block/"),
                "side" to id.prefixPath("block/")
            )
        ).suffixed("slab_top")
    )
}
