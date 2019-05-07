package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.ModelBlockState
import io.github.cottonmc.jsonfactory.output.prefixed

/**
 * @since 0.4.0
 */
class PrefixedBlockState(displayName: String, private val prefix: String, info: GeneratorInfo) :
    AbstractContentGenerator(displayName, "blockstates", info) {
    override fun generate(id: Identifier) = listOf(
        ModelBlockState(
            mapOf(
                "" to ModelBlockState.Variant(id.prefixPath("block/${prefix}_"))
            )
        ).prefixed(prefix)
    )
}
