package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.VariantBlockState
import io.github.cottonmc.jsonfactory.output.model.ModelVariant
import io.github.cottonmc.jsonfactory.output.suffixed

/**
 * @since 0.4.0
 */
class SuffixedBlockState(private val type: String, info: GeneratorInfo) :
    AbstractContentGenerator("$type.block_state", "blockstates", info) {
    override fun generate(id: Identifier) = listOf(
        VariantBlockState(
            mapOf(
                "" to ModelVariant(id.wrapPath("block/", "_$type"))
            )
        ).suffixed(type)
    )
}
