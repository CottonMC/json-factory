package io.github.cottonmc.jsonfactory.gens

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.output.ModelBlockState
import io.github.cottonmc.jsonfactory.output.Suffixed

object SlabBlockState : ContentGenerator("Slab Block State", "blockstates", Categories.BlockVariants) {
    override fun generate(id: Identifier) = listOf(Suffixed(ModelBlockState(
        mapOf(
            "type=bottom" to ModelBlockState.Variant(
                Identifier(
                    id.namespace,
                    "block/${id.path}_slab"
                )
            ),
            "type=top" to ModelBlockState.Variant(
                Identifier(
                    id.namespace,
                    "block/${id.path}_slab_top"
                )
            ),
            "type=double" to ModelBlockState.Variant(
                Identifier(
                    id.namespace,
                    "block/${id.path}"
                )
            )
        )
    ), "slab"))
}
