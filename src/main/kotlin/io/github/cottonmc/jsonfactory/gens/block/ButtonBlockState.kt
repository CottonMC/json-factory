package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.BlockStateProperty.Companion.buttonFace
import io.github.cottonmc.jsonfactory.data.BlockStateProperty.Companion.horizontalFacing
import io.github.cottonmc.jsonfactory.data.BlockStateProperty.Companion.powered
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.ModelBlockState
import io.github.cottonmc.jsonfactory.output.suffixed

internal object ButtonBlockState :
    AbstractContentGenerator("button.block_state", "blockstates", GeneratorInfo.BUTTONS) {
    private fun getXRotation(face: String) = when (face) {
        "wall" -> 90
        "ceiling" -> 180
        else -> 0
    }

    private fun getYRotation(facing: String) = when (facing) {
        "east" -> 90
        "south" -> 180
        "west" -> 270
        else -> 0
    }

    override fun generate(id: Identifier) =
        listOf(ModelBlockState.create(id, setOf(horizontalFacing, powered, buttonFace)) { values, variant ->
            val suffix = if (values[powered] == "true") "_pressed" else ""

            variant.copy(
                model = variant.model.suffixPath("_button$suffix"),
                x = getXRotation(values[buttonFace]!!),
                y = getYRotation(values[horizontalFacing]!!).let {
                    if (values[buttonFace] == "ceiling") (it + 180) % 360
                    else it
                },
                uvlock = values[buttonFace] == "wall"
            )
        }.suffixed("button"))
}
