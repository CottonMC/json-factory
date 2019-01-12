package io.github.cottonmc.jsonfactory.gens

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.output.Model

object BasicBlockItemModel : ContentGenerator<Model>("Basic Block Item Model", "models/item", Categories.Block) {
    override fun generate(id: Identifier) = listOf(Model(
        parent = id.copy(path = "block/" + id.path)
    ))
}
