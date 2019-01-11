package io.github.cottonmc.jsonfactory.data.gens

import io.github.cottonmc.jsonfactory.data.ContentGenerator
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.types.Model

object BasicItemModel : ContentGenerator<Model>("Basic Item Model", "models/item", Category.Item) {
    override fun generate(id: Identifier) = Model(
        parent = "item/generated",
        textures = mapOf(
            "layer0" to "${id.namespace}:item/${id.path}"
        )
    )
}
