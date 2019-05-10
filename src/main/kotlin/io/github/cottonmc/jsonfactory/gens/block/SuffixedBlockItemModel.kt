package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.Model
import io.github.cottonmc.jsonfactory.output.suffixed

class SuffixedBlockItemModel(
    display: String,
    private val fileNameSuffix: String,
    info: GeneratorInfo,
    private val parentSuffix: String = fileNameSuffix
) : AbstractContentGenerator("$display Block Item Model", "models/item", info) {
    override fun generate(id: Identifier) = listOf(
        Model(
            parent = id.copy(path = "block/${id.path}_$parentSuffix")
        ).suffixed(fileNameSuffix)
    )
}
