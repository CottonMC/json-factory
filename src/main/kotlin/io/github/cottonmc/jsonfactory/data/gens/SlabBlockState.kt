package io.github.cottonmc.jsonfactory.data.gens

import io.github.cottonmc.jsonfactory.data.ContentGenerator
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.types.ModelBlockState

object SlabBlockState : ContentGenerator<ModelBlockState>("Slab Block State", "blockstates", Category.Block) {
    override fun generate(id: Identifier) = container(ModelBlockState(
        mapOf(
            "type=bottom" to ModelBlockState.Variant(Identifier(id.namespace, "block/${id.path}_slab")),
            "type=top" to ModelBlockState.Variant(Identifier(id.namespace, "block/${id.path}_slab_top")),
            "type=double" to ModelBlockState.Variant(Identifier(id.namespace, "block/${id.path}"))
        )
    ), "slab")
}
