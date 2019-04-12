package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.BlockStateProperty
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.ModelBlockState
import io.github.cottonmc.jsonfactory.output.Suffixed

internal object TrapdoorBlockState : ContentGenerator("Trapdoor Block State", "blockstates", GeneratorInfo.TRAPDOORS) {
    override fun generate(id: Identifier) = listOf(
        Suffixed(
            ModelBlockState.createOld(id, listOf(BlockStateProperty.horizontalFacing, BlockStateProperty.half, BlockStateProperty.open)) {
                    values, variant ->
                val suffix = when {
                    values["open"] == "true" -> "open"
                    else -> values["half"]
                }

                variant.copy(
                    model = variant.model.suffixPath("_trapdoor_$suffix"),
                    x = if (values["open"] == "true" && values["half"] == "top") 180
                        else 0,
                    y = getYRotation(values["facing"] ?: "").let {
                        if (values["open"] == "true" && values["half"] == "top")
                            (it + 180) % 360
                        else it
                    }
                )
            }, "trapdoor"
        )
    )

    private fun getYRotation(facing: String) = when (facing) {
        "east" -> 90
        "south" -> 180
        "west" -> 270
        else -> 0
    }
}
