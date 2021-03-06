package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.BlockStateProperty
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.VariantBlockState
import io.github.cottonmc.jsonfactory.output.suffixed

internal object StairBlockState : AbstractContentGenerator(
    "stairs.block_state", "blockstates",
    GeneratorInfo.STAIRS
) {
    override fun generate(id: Identifier) = listOf(
        VariantBlockState.create(
            id,
            setOf(BlockStateProperty.horizontalFacing, BlockStateProperty.halfTB, BlockStateProperty.stairShape)
        ) { values, variant ->
            val shape = values["shape"]!!
            val x = if (values["half"] == "top") 180 else 0
            val y = getYRotation(values["facing"]!!).let {
                if ("left" in shape)
                    (it + 270) % 360
                else it
            }.let {
                if (values["half"] == "top" && shape != "straight")
                    (it + 90) % 360
                else it
            }

            val suffix = when {
                "outer" in shape -> "_outer"
                "inner" in shape -> "_inner"
                else -> ""
            }

            variant.copy(
                model = variant.model.suffixPath(
                    "_stairs$suffix"
                ),
                x = x,
                y = y,
                uvlock = x != 0 || y != 0
            )
        }.suffixed("stairs")
    )

    private fun getYRotation(facing: String): Int = when (facing) {
        "south" -> 90
        "west" -> 180
        "north" -> 270
        else -> 0
    }
}
