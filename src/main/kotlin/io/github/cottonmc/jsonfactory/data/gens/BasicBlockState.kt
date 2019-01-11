package io.github.cottonmc.jsonfactory.data.gens

import io.github.cottonmc.jsonfactory.data.ContentGenerator
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.types.ModelBlockState

object BasicBlockState : ContentGenerator<ModelBlockState>("Basic Block State") {
    override fun generate(id: Identifier) = ModelBlockState(
        mapOf(
            "" to ModelBlockState.Variant(Identifier(id.namespace, "block/${id.path}"))
        )
    )
}
