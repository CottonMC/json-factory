package io.github.cottonmc.jsonfactory.data.gens

import io.github.cottonmc.jsonfactory.data.types.Identifier
import io.github.cottonmc.jsonfactory.data.output.ModelBlockState

object BasicBlockState : ContentGenerator<ModelBlockState>("Basic Block State", "blockstates", Categories.Block) {
    override fun generate(id: Identifier) = listOf(ModelBlockState(
        mapOf(
            "" to ModelBlockState.Variant(
                Identifier(
                    id.namespace,
                    "block/${id.path}"
                )
            )
        )
    ))
}
