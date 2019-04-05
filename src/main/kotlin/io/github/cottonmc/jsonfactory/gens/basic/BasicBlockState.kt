package io.github.cottonmc.jsonfactory.gens.basic

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.ModelBlockState

internal object BasicBlockState : ContentGenerator("Basic Block State", "blockstates", GeneratorInfo.BLOCK) {
    override fun generate(id: Identifier) = listOf(ModelBlockState(
        mapOf(
            "" to ModelBlockState.Variant(id.prefixPath("block/"))
        )
    ))
}
