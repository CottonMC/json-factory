package io.github.cottonmc.jsonfactory.gens.variants

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.ListProperty
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.ModelBlockState
import io.github.cottonmc.jsonfactory.output.Suffixed

internal object StairBlockState : ContentGenerator("Stair Block State", "blockstates",
    GeneratorInfo.STAIRS
) {
    override fun generate(id: Identifier) = listOf(Suffixed(
        ModelBlockState.create(id, listOf(ListProperty.horizontalFacing, ListProperty.half, ListProperty.stairShape)) { values, variant ->
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
        }, "stairs"))

    private fun getYRotation(facing: String): Int = when (facing) {
        "south" -> 90
        "west" -> 180
        "north" -> 270
        else -> 0
    }
}
