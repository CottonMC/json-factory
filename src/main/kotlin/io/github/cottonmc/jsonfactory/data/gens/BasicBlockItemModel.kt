package io.github.cottonmc.jsonfactory.data.gens

import io.github.cottonmc.jsonfactory.data.ContentGenerator
import io.github.cottonmc.jsonfactory.data.types.Identifier
import io.github.cottonmc.jsonfactory.data.output.Model

object BasicBlockItemModel : ContentGenerator<Model>("Basic Block Item Model", "models/item", Categories.Block) {
    override fun generate(id: Identifier) = listOf(Model(
        parent = id.copy(path = "block/" + id.path)
    ))
}
