package io.github.cottonmc.jsonfactory.gens.item

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.Model
import io.github.cottonmc.jsonfactory.output.suffixed

class SuffixedItemModel(
    val parent: Identifier,
    private val type: String,
    info: GeneratorInfo = GeneratorInfo.ITEM
) : ContentGenerator("$type.item_model", "models/item", info) {
    override fun generate(id: Identifier) = listOf(
        Model(
            parent = parent,
            textures = mapOf(
                "layer0" to id.copy(path = "item/${id.path}_$type")
            )
        ).suffixed(type)
    )
}
