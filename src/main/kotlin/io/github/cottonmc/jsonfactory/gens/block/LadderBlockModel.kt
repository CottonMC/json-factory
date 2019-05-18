package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.Point
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.Model
import io.github.cottonmc.jsonfactory.output.suffixed

internal object LadderBlockModel :
    AbstractContentGenerator("ladders.block_model", "models/block", GeneratorInfo.LADDERS) {
    override fun generate(id: Identifier) = listOf(
        Model(
            textures = mapOf(
                "particle" to id.wrapPath("block/", "_ladder"),
                "texture" to id.wrapPath("block/", "_ladder")
            ),
            elements = listOf(
                Model.Element(
                    from = Point(0, 0, 15.2),
                    to = Point(16, 16, 15.2),
                    shade = false,
                    faces = mapOf(
                        "north" to Model.Face(uv = Model.Uv(0, 0, 16, 16), texture = "#texture"),
                        "south" to Model.Face(uv = Model.Uv(0, 0, 16, 16), texture = "#texture")
                    )
                )
            )
        ).suffixed("ladder")
    )
}
