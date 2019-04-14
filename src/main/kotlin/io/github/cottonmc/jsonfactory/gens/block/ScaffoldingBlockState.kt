package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.BlockStateProperty
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.ModelBlockState
import io.github.cottonmc.jsonfactory.output.suffixed

internal object ScaffoldingBlockState :
    ContentGenerator("Scaffolding Block State", "blockstates", GeneratorInfo.SCAFFOLDING) {

    private val typePrefixes = mapOf(
        "true" to "unstable_",
        "false" to "stable_"
    )

    override fun generate(id: Identifier) = listOf(
        ModelBlockState.create(id, setOf(BlockStateProperty.bottom)) { values, variant ->
            variant.copy(model = variant.model.wrapPath("${typePrefixes[values["bottom"]]}", "_scaffolding"))
        }.suffixed("scaffolding")
    )
}
