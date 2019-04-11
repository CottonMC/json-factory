package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.ModelBlockState
import io.github.cottonmc.jsonfactory.output.Suffixed

/**
 * @since 0.4.0
 */
class SuffixedBlockState(displayName: String, private val suffix: String, info: GeneratorInfo) : ContentGenerator(displayName, "blockstates", info) {
    override fun generate(id: Identifier) = listOf(
        Suffixed(
            ModelBlockState(
                mapOf(
                    "" to ModelBlockState.Variant(id.wrapPath("block/", "_$suffix"))
                )
            ),
            suffix
        )
    )
}
