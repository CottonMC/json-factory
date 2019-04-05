package io.github.cottonmc.jsonfactory.gens.basic

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.output.Model
import io.github.cottonmc.jsonfactory.output.Suffixed

class SuffixedBlockItemModel(
    display: String,
    private val fileNameSuffix: String,
    private val parentSuffix: String = fileNameSuffix,
    subcategory: Subcategory? = null
) : ContentGenerator("$display Block Item Model", "models/item",
    Categories.BlockVariants, subcategory
) {
    override fun generate(id: Identifier) = listOf(
        Suffixed(Model(
        parent = id.copy(path = "block/${id.path}_$parentSuffix")
    ), fileNameSuffix))
}
