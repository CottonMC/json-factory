package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.Model
import io.github.cottonmc.jsonfactory.output.suffixed

internal object TrapdoorBlockModel : ContentGenerator("Trapdoor Block Model", "models/block", GeneratorInfo.TRAPDOORS) {
    override fun generate(id: Identifier) = listOf(
        Model(
            parent = Identifier.mc("block/template_orientable_trapdoor_bottom"),
            textures = mapOf(
                "texture" to id.wrapPath("block/", "_trapdoor")
            )
        ).suffixed("trapdoor_bottom"),
        Model(
            parent = Identifier.mc("block/template_orientable_trapdoor_top"),
            textures = mapOf(
                "texture" to id.wrapPath("block/", "_trapdoor")
            )
        ).suffixed("trapdoor_top"),
        Model(
            parent = Identifier.mc("block/template_orientable_trapdoor_open"),
            textures = mapOf(
                "texture" to id.wrapPath("block/", "_trapdoor")
            )
        ).suffixed("trapdoor_open")
    )
}
