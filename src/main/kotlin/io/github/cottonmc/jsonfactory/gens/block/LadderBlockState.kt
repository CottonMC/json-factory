package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.BlockStateProperty
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.VariantBlockState
import io.github.cottonmc.jsonfactory.output.suffixed

internal object LadderBlockState :
    AbstractContentGenerator("ladder.block_state", "blockstates", GeneratorInfo.LADDERS) {
    private fun getYRotation(facing: String) = when (facing) {
        "east" -> 90
        "south" -> 180
        "west" -> 270
        else -> 0
    }

    override fun generate(id: Identifier) = listOf(
        VariantBlockState.create(id, setOf(BlockStateProperty.horizontalFacing)) { values, variant ->
            variant.copy(
                model = variant.model.suffixPath("_ladder"),
                y = getYRotation(values["facing"] ?: "")
            )
        }.suffixed("ladder")
    )
}
