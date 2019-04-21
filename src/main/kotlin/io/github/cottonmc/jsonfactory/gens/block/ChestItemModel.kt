package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.Model
import io.github.cottonmc.jsonfactory.output.suffixed

internal object ChestItemModel : ContentGenerator("chests.item_model", "models/item", GeneratorInfo.CHESTS) {
    override fun generate(id: Identifier) = listOf(
        Model(
            parent = Identifier.mc("builtin/entity"),
            textures = mapOf(
                "particle" to id
            ),
            display = mapOf(
                "gui" to Model.DisplayPosition(
                    rotation = listOf(30, 45, 0),
                    translation = listOf(0, 0, 0),
                    scale = listOf(0.625, 0.625, 0.625)
                ),
                "ground" to Model.DisplayPosition(
                    rotation = listOf(0, 0, 0),
                    translation = listOf(0, 3, 0),
                    scale = listOf(0.25, 0.25, 0.25)
                ),
                "head" to Model.DisplayPosition(
                    rotation = listOf(0, 180, 0),
                    translation = listOf(0, 0, 0),
                    scale = listOf(1, 1, 1)
                ),
                "fixed" to Model.DisplayPosition(
                    rotation = listOf(0, 180, 0),
                    translation = listOf(0, 0, 0),
                    scale = listOf(0.5, 0.5, 0.5)
                ),
                "thirdperson_righthand" to Model.DisplayPosition(
                    rotation = listOf(75, 315, 0),
                    translation = listOf(0, 2.5, 0),
                    scale = listOf(0.375, 0.375, 0.375)
                ),
                "firstperson_righthand" to Model.DisplayPosition(
                    rotation = listOf(0, 315, 0),
                    translation = listOf(0, 0, 0),
                    scale = listOf(0.4, 0.4, 0.4)
                )
            )
        ).suffixed("chest")
    )
}
