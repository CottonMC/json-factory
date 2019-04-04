package io.github.cottonmc.jsonfactory.gens.basic

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.output.Model

class BasicItemModel(val parent: Identifier, suffix: String = "") : ContentGenerator("Basic Item Model$suffix", "models/item",
    Categories.Item
) {
    override fun generate(id: Identifier) = listOf(Model(
        parent = parent,
        textures = mapOf(
            "layer0" to id.prefixPath("item/")
        )
    ))
}
