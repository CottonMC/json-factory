package io.github.cottonmc.jsonfactory.gens.basic

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.output.Model
import io.github.cottonmc.jsonfactory.output.Suffixed

class SuffixedItemModel(val parent: Identifier, display: String, private val suffix: String, category: Category = Categories.Item, subcategory: Subcategory? = null) :
    ContentGenerator("$display Item Model", "models/item", category, subcategory) {
    override fun generate(id: Identifier) = listOf(
        Suffixed(
            Model(
                parent = parent,
                textures = mapOf(
                    "layer0" to id.copy(path = "item/${id.path}_$suffix")
                )
            ),
            suffix)
    )
}
