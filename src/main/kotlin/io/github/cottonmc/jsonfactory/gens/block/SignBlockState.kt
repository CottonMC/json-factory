package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.ModelBlockState
import io.github.cottonmc.jsonfactory.output.suffixed

internal object SignBlockState : AbstractContentGenerator("Sign Block State", "blockstates", GeneratorInfo.SIGNS) {
    override fun generate(id: Identifier) = listOf(
        ModelBlockState(mapOf("" to ModelBlockState.Variant(id.copy(path = "block/${id.path}_sign")))).suffixed(
            "sign"
        ),
        ModelBlockState(mapOf("" to ModelBlockState.Variant(id.copy(path = "block/${id.path}_sign")))).suffixed(
            "wall_sign"
        )
    )
}
