package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.Point
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.Model

internal object BaseOreBlockModel : AbstractContentGenerator(
    // TODO Desc
    "ores.template (use the ID namespace:ore_template)", "models/block",
    GeneratorInfo.ORES
) {
    override fun generate(id: Identifier) = listOf(
        Model(
            parent = Identifier.mc("block/block"),
            textures = mapOf("particle" to "#base"),
            elements = listOf(
                Model.Element(
                    from = Point(0, 0, 0), to = Point(16, 16, 16), faces = mapOf(
                        "down" to Model.Face("#base", "down"),
                        "up" to Model.Face("#base", "up"),
                        "north" to Model.Face("#base", "north"),
                        "east" to Model.Face("#base", "east"),
                        "south" to Model.Face("#base", "south"),
                        "west" to Model.Face("#base", "west")
                    )
                ),
                Model.Element(
                    from = Point(-0.01, -0.01, -0.01), to = Point(16.01, 16.01, 16.01), faces = mapOf(
                        "down" to Model.Face("#ore", "down"),
                        "up" to Model.Face("#ore", "up"),
                        "north" to Model.Face("#ore", "north"),
                        "east" to Model.Face("#ore", "east"),
                        "south" to Model.Face("#ore", "south"),
                        "west" to Model.Face("#ore", "west")
                    )
                )
            )
        )
    )
}
