package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.BlockStateProperty
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.VariantBlockState
import io.github.cottonmc.jsonfactory.output.suffixed

internal object ScaffoldingBlockState :
    AbstractContentGenerator("scaffolding.block_state", "blockstates", GeneratorInfo.SCAFFOLDING) {
    private val typePrefixes = mapOf(
        "true" to "_unstable",
        "false" to "_stable"
    )

    override fun generate(id: Identifier) = listOf(
        VariantBlockState.create(id, setOf(BlockStateProperty.scaffoldingBottom)) { values, variant ->
            variant.copy(model = variant.model.suffixPath("${typePrefixes[values["bottom"]]}" + "_scaffolding"))
        }.suffixed("scaffolding")
    )
}
