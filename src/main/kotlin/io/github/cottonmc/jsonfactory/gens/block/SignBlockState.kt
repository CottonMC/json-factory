package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.ModelBlockState
import io.github.cottonmc.jsonfactory.output.suffixed

internal object SignBlockState : ContentGenerator("signs.block_state", "blockstates", GeneratorInfo.SIGNS) {
    override fun generate(id: Identifier) = listOf(
        ModelBlockState(mapOf("" to ModelBlockState.Variant(id.wrapPath("block/", "_sign")))).suffixed(
            "sign"
        ),
        ModelBlockState(mapOf("" to ModelBlockState.Variant(id.wrapPath("block/", "_sign")))).suffixed(
            "wall_sign"
        )
    )
}
