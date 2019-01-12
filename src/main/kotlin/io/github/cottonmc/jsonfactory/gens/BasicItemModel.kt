package io.github.cottonmc.jsonfactory.gens

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.output.Model

class BasicItemModel(val parent: Identifier, suffix: String = "") : ContentGenerator("Basic Item Model$suffix", "models/item", Categories.Item) {
    override fun generate(id: Identifier) = listOf(Model(
        parent = parent,
        textures = mapOf(
            "layer0" to id.copy(path = "item/" + id.path)
        )
    ))
}
