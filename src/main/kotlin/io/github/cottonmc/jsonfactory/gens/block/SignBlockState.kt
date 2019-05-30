package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.VariantBlockState
import io.github.cottonmc.jsonfactory.output.model.ModelVariant
import io.github.cottonmc.jsonfactory.output.suffixed

internal object SignBlockState : AbstractContentGenerator("sign.block_state", "blockstates", GeneratorInfo.SIGNS) {
    override fun generate(id: Identifier) = listOf(
        VariantBlockState(mapOf("" to ModelVariant(
            id.wrapPath(
                "block/",
                "_sign"
            )
        )
        )).suffixed(
            "sign"
        ),
        VariantBlockState(mapOf("" to ModelVariant(
            id.wrapPath(
                "block/",
                "_sign"
            )
        )
        )).suffixed(
            "wall_sign"
        )
    )
}
