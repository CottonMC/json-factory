package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.BlockStateProperty
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.ModelBlockState
import io.github.cottonmc.jsonfactory.output.suffixed

internal object BarrelBlockState :
    AbstractContentGenerator("barrels.block_state", "blockstates", GeneratorInfo.BARRELS) {
    override fun generate(id: Identifier) = listOf(
        ModelBlockState.create(id, setOf(BlockStateProperty.facing, BlockStateProperty.open)) { values, variant ->
            val x = getXRotation(values["facing"]!!).let {
                it
            }
            val y = getYRotation(values["facing"]!!).let {
                it
            }

            val typePrefixes = mapOf(
                "true" to "_barrel_open",
                "false" to "_barrel"
            )

            variant.copy(
                model = variant.model.suffixPath("${typePrefixes[values["open"]]}"),
                x = x,
                y = y
            )
        }.suffixed("barrel")
    )

    private fun getYRotation(facing: String): Int = when (facing) {
        "east" -> 90
        "south" -> 180
        "west" -> 270
        else -> 0
    }

    private fun getXRotation(facing: String): Int = when (facing) {
        "down" -> 180
        "north" -> 90
        "south" -> 90
        "west" -> 90
        "east" -> 90
        else -> 0
    }

}
