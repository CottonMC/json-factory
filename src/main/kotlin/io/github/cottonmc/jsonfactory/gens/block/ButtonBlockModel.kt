package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.Model
import io.github.cottonmc.jsonfactory.output.suffixed

internal object ButtonBlockModel :
    AbstractContentGenerator("buttons.block_model", "models/block", GeneratorInfo.BUTTONS) {
    override fun generate(id: Identifier) = listOf(
        Model(
            parent = Identifier.mc("block/button"),
            textures = mapOf(
                "texture" to id.prefixPath("block/")
            )
        ).suffixed("button"),
        Model(
            parent = Identifier.mc("block/button_pressed"),
            textures = mapOf(
                "texture" to id.prefixPath("block/")
            )
        ).suffixed("button_pressed"),
        Model(
            parent = Identifier.mc("block/button_inventory"),
            textures = mapOf(
                "texture" to id.prefixPath("block/")
            )
        ).suffixed("button_inventory")
    )
}
