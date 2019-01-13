package io.github.cottonmc.jsonfactory.gens

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.output.Model
import io.github.cottonmc.jsonfactory.output.Suffixed

object ButtonBlockModel : ContentGenerator("Button Block Model", "models/block", Categories.BlockVariants) {
    override fun generate(id: Identifier) = listOf(
        Suffixed(Model(
            parent = Identifier.mc("block/button"),
            textures = mapOf(
                "texture" to id.prefixPath("block/")
            )
        ), "button"),
        Suffixed(Model(
            parent = Identifier.mc("block/button_pressed"),
            textures = mapOf(
                "texture" to id.prefixPath("block/")
            )
        ), "button_pressed"),
        Suffixed(Model(
            parent = Identifier.mc("block/button_inventory"),
            textures = mapOf(
                "texture" to id.prefixPath("block/")
            )
        ), "button_inventory")
    )
}
