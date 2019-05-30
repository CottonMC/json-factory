package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.VariantBlockState
import io.github.cottonmc.jsonfactory.output.model.ModelVariant

internal object BasicBlockState : AbstractContentGenerator("basic.block_state", "blockstates", GeneratorInfo.BLOCK) {
    override fun generate(id: Identifier) = listOf(
        VariantBlockState(
            mapOf(
                "" to ModelVariant(id.prefixPath("block/"))
            )
        )
    )
}
