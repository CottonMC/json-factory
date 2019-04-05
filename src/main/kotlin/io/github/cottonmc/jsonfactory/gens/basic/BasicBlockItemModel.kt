package io.github.cottonmc.jsonfactory.gens.basic

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.Model

internal object BasicBlockItemModel : ContentGenerator("Basic Block Item Model", "models/item", GeneratorInfo.BLOCK) {
    override fun generate(id: Identifier) = listOf(Model(
        parent = id.prefixPath("block/")
    ))
}
