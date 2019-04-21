package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.Model
import io.github.cottonmc.jsonfactory.output.suffixed

internal object WallBlockModel : ContentGenerator("walls.block_model", "models/block", GeneratorInfo.WALLS) {
    override fun generate(id: Identifier) = listOf(
        Model(
            parent = Identifier.mc("block/template_wall_post"),
            textures = mapOf(
                "wall" to id.prefixPath("block/")
            )
        ).suffixed("wall_post"),
        Model(
            parent = Identifier.mc("block/template_wall_side"),
            textures = mapOf(
                "wall" to id.prefixPath("block/")
            )
        ).suffixed("wall_side"),
        Model(
            parent = Identifier.mc("block/wall_inventory"),
            textures = mapOf(
                "wall" to id.prefixPath("block/")
            )
        ).suffixed("wall_inventory")
    )
}
