package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.BlockStateProperty
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.VariantBlockState

internal object PillarBlockState :
    AbstractContentGenerator("pillar.block_state", "blockstates", GeneratorInfo.PILLARS) {
    override fun generate(id: Identifier) = listOf(
        VariantBlockState.create(id, setOf(BlockStateProperty.axis)) { values, variant ->
            val axis = values["axis"] ?: ""

            variant.copy(
                x = if (axis != "y") 90 else 0,
                y = if (axis == "x") 90 else 0
            )
        }
    )
}
