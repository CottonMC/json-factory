package io.github.cottonmc.jsonfactory.gens

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.output.ModelBlockState

object PillarBlockState : ContentGenerator<ModelBlockState>("Pillar Block State", "blockstates", Categories.Block) {
    override fun generate(id: Identifier) = listOf(
        ModelBlockState(
            mapOf(
                "axis=y" to ModelBlockState.Variant(
                    Identifier(
                        id.namespace,
                        "block/${id.path}"
                    )
                ),
                "axis=z" to ModelBlockState.Variant(
                    Identifier(
                        id.namespace,
                        "block/${id.path}"
                    ),
                    x = 90
                ),
                "axis=x" to ModelBlockState.Variant(
                    Identifier(
                        id.namespace,
                        "block/${id.path}"
                    ),
                    x = 90, y = 90
                )
            )
        )
    )
}
