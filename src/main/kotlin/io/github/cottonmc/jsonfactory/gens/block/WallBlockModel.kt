package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.Model
import io.github.cottonmc.jsonfactory.output.Suffixed

internal object WallBlockModel : ContentGenerator("Wall Block Model", "models/block", GeneratorInfo.WALLS) {
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
