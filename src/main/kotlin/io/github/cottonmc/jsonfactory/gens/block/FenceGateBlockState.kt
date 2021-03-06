package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.BlockStateProperty
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.VariantBlockState
import io.github.cottonmc.jsonfactory.output.suffixed

internal object FenceGateBlockState :
    AbstractContentGenerator("fence_gate.block_state", "blockstates", GeneratorInfo.FENCE_GATES) {
    override fun generate(id: Identifier) = listOf(
        VariantBlockState.create(
            id,
            setOf(BlockStateProperty.horizontalFacing, BlockStateProperty.gateInWall, BlockStateProperty.open)
        ) { values, variant ->
            variant.copy(
                model = variant.model.suffixPath("_fence_gate").let {
                    if (values["in_wall"]?.toBoolean() == true) it.suffixPath("_wall")
                    else it
                }.let {
                    if (values["open"]?.toBoolean() == true) it.suffixPath("_open")
                    else it
                },
                uvlock = true,
                y = getYRotation(values["facing"] ?: "")
            )
        }.suffixed("fence_gate")
    )

    private fun getYRotation(facing: String) = when (facing) {
        "west" -> 90
        "north" -> 180
        "east" -> 270
        else -> 0
    }
}
