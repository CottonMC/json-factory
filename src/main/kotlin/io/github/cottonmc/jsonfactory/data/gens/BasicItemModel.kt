package io.github.cottonmc.jsonfactory.data.gens

import io.github.cottonmc.jsonfactory.data.types.Identifier
import io.github.cottonmc.jsonfactory.data.output.Model

object BasicItemModel : ContentGenerator<Model>("Basic Item Model", "models/item", Categories.Item) {
    override fun generate(id: Identifier) = listOf(Model(
        parent = Identifier.mc("item/generated"),
        textures = mapOf(
            "layer0" to id.copy(path = "item/" + id.path)
        )
    ))
}
