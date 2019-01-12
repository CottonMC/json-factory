package io.github.cottonmc.jsonfactory.data.gens

import io.github.cottonmc.jsonfactory.data.ContentGenerator
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.Output
import io.github.cottonmc.jsonfactory.data.types.Model

object BasicBlockItemModel : ContentGenerator<Model>("Basic Block Item Model", "models/item", Category.Block) {
    override fun generate(id: Identifier) = container(Model(
        parent = "${id.namespace}:block/${id.path}"
    ))
}
