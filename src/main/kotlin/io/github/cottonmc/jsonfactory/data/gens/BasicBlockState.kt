package io.github.cottonmc.jsonfactory.data.gens

import io.github.cottonmc.jsonfactory.data.ContentGenerator
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.types.ModelBlockState

object BasicBlockState : ContentGenerator<ModelBlockState>("Basic Block State", "blockstates", Category.Block) {
    override fun generate(id: Identifier) = container(ModelBlockState(
        mapOf(
            "" to ModelBlockState.Variant(Identifier(id.namespace, "block/${id.path}"))
        )
    ))
}
