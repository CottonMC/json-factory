package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.BlockStateProperty
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.ModelBlockState
import io.github.cottonmc.jsonfactory.output.suffixed

internal object SlabBlockState : AbstractContentGenerator("slab.block_state", "blockstates", GeneratorInfo.SLABS) {
    private val typeSuffixes = mapOf(
        "bottom" to "_slab",
        "top" to "_slab_top",
        "double" to ""
    )

    override fun generate(id: Identifier) = listOf(
        ModelBlockState.create(
            id, setOf(BlockStateProperty.slabType)
        ) { values, variant ->
            variant.copy(
                model = variant.model.suffixPath(
                    typeSuffixes[values["type"]] ?: ""
                )
            )
        }.suffixed("slab")
    )
}
