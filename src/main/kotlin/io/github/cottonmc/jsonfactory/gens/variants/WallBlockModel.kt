package io.github.cottonmc.jsonfactory.gens.variants

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.output.Model
import io.github.cottonmc.jsonfactory.output.Suffixed

object WallBlockModel : ContentGenerator("Wall Block Model", "models/block",
    Categories.BlockVariants
) {
    override fun generate(id: Identifier) = listOf(
        Suffixed(Model(
            parent = Identifier.mc("block/template_wall_post"),
            textures = mapOf(
                "wall" to id.prefixPath("block/")
            )
        ), "wall_post"),
        Suffixed(Model(
            parent = Identifier.mc("block/template_wall_side"),
            textures = mapOf(
                "wall" to id.prefixPath("block/")
            )
        ), "wall_side"),
        Suffixed(Model(
            parent = Identifier.mc("block/wall_inventory"),
            textures = mapOf(
                "wall" to id.prefixPath("block/")
            )
        ), "wall_inventory")
    )
}
