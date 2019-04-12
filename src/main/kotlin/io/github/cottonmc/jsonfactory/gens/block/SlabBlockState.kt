package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.BlockStateProperty
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.ModelBlockState
import io.github.cottonmc.jsonfactory.output.Suffixed

internal object SlabBlockState : ContentGenerator("Slab Block State", "blockstates", GeneratorInfo.SLABS) {
    private val typeSuffixes = mapOf(
        "bottom" to "_slab",
        "top" to "_slab_top",
        "double" to ""
    )

    override fun generate(id: Identifier) = listOf(
        Suffixed(
            ModelBlockState.createOld(
                id, listOf(BlockStateProperty.slabType)
            ) { values, variant ->
                variant.copy(
                    model = variant.model.suffixPath(
                        typeSuffixes[values["type"]] ?: ""
                    )
                )
            }, "slab"
        )
    )
}