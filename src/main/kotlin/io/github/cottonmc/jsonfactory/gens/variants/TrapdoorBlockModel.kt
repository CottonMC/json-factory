package io.github.cottonmc.jsonfactory.gens.variants

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.output.Model
import io.github.cottonmc.jsonfactory.output.Suffixed

object TrapdoorBlockModel : ContentGenerator("Trapdoor Block Model", "models/block",
    Categories.BlockVariants
) {
    override fun generate(id: Identifier) = listOf(
        Suffixed(Model(
            parent = Identifier.mc("block/template_orientable_trapdoor_bottom"),
            textures = mapOf(
                "texture" to id.prefixPath("block/").suffixPath("_trapdoor")
            )
        ), "trapdoor_bottom"),
        Suffixed(Model(
            parent = Identifier.mc("block/template_orientable_trapdoor_top"),
            textures = mapOf(
                "texture" to id.prefixPath("block/").suffixPath("_trapdoor")
            )
        ), "trapdoor_top"),
        Suffixed(Model(
            parent = Identifier.mc("block/template_orientable_trapdoor_open"),
            textures = mapOf(
                "texture" to id.prefixPath("block/").suffixPath("_trapdoor")
            )
        ), "trapdoor_open")
    )
}
