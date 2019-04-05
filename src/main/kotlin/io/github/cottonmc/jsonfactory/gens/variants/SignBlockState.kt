package io.github.cottonmc.jsonfactory.gens.variants

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.ModelBlockState
import io.github.cottonmc.jsonfactory.output.Suffixed

internal object SignBlockState : ContentGenerator("Sign Block State", "blockstates", GeneratorInfo.SIGNS) {
    override fun generate(id: Identifier) = listOf(
        Suffixed(
            ModelBlockState(mapOf("" to ModelBlockState.Variant(id.copy(path = "block/${id.path}_sign")))),
            "sign"
        ),
        Suffixed(
            ModelBlockState(mapOf("" to ModelBlockState.Variant(id.copy(path = "block/${id.path}_sign")))),
            "wall_sign"
        )
    )
}
