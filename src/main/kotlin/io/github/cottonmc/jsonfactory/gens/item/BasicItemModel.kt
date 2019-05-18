package io.github.cottonmc.jsonfactory.gens.item

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.Model

class BasicItemModel(val parent: Identifier, suffix: String = "") : AbstractContentGenerator(
    "basic.item_model$suffix", "models/item",
    GeneratorInfo.ITEM
) {
    override fun generate(id: Identifier) = listOf(
        Model(
            parent = parent,
            textures = mapOf(
                "layer0" to id.prefixPath("item/")
            )
        )
    )
}
