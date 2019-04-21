package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.ModelBlockState

internal object BasicBlockState : ContentGenerator("basic.block_state", "blockstates", GeneratorInfo.BLOCK) {
    override fun generate(id: Identifier) = listOf(
        ModelBlockState(
            mapOf(
                "" to ModelBlockState.Variant(id.prefixPath("block/"))
            )
        )
    )
}
