package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.ModelBlockState
import io.github.cottonmc.jsonfactory.output.suffixed

/**
 * @since 0.4.0
 */
class SuffixedBlockState(private val type: String, info: GeneratorInfo) :
    ContentGenerator("$type.block_state", "blockstates", info) {
    override fun generate(id: Identifier) = listOf(
        ModelBlockState(
            mapOf(
                "" to ModelBlockState.Variant(id.wrapPath("block/", "_$type"))
            )
        ).suffixed(type)
    )
}
