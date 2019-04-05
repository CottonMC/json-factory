package io.github.cottonmc.jsonfactory.gens.variants

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.Point
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.Model
import io.github.cottonmc.jsonfactory.output.Suffixed

internal object LadderBlockModel : ContentGenerator("Ladder Block Model", "models/block", GeneratorInfo.LADDERS) {
    override fun generate(id: Identifier) = listOf(
        Suffixed(
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
            ),
            "ladder"
        )
    )
}
