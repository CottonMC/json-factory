package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.VariantBlockState
import io.github.cottonmc.jsonfactory.output.model.ModelVariant
import io.github.cottonmc.jsonfactory.output.prefixed

/**
 * @since 0.4.0
 */
class PrefixedBlockState(private val type: String, info: GeneratorInfo, l10nPrefix: String = type) :
    AbstractContentGenerator("$l10nPrefix.block_state", "blockstates", info) {
    override fun generate(id: Identifier) = listOf(
        VariantBlockState(
            mapOf(
                "" to ModelVariant(id.prefixPath("block/${type}_"))
            )
        ).prefixed(type)
    )
}
