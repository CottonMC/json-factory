package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.ModelBlockState
import io.github.cottonmc.jsonfactory.output.suffixed

/**
 * @since 0.4.0
 */
class SuffixedBlockState(displayName: String, private val suffix: String, info: GeneratorInfo) :
    AbstractContentGenerator(displayName, "blockstates", info) {
    override fun generate(id: Identifier) = listOf(
        ModelBlockState(
            mapOf(
                "" to ModelBlockState.Variant(id.wrapPath("block/", "_$suffix"))
            )
        ).suffixed(suffix)
    )
}
