package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.BlockStateProperty
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.ModelBlockState
import io.github.cottonmc.jsonfactory.output.suffixed

internal object CampfireBlockState : ContentGenerator("campfires.block_state", "blockstates", GeneratorInfo.CAMPFIRES) {
    override fun generate(id: Identifier) = listOf(
        ModelBlockState.create(
            id,
            setOf(BlockStateProperty.horizontalFacing, BlockStateProperty.lit)
        ) { values, variant ->
            val y = getYRotation(values["facing"]!!).let {
                it
            }

            val typePrefixes = mapOf(
                "true" to "_campfire",
                "false" to "_campfire_off"
            )

            variant.copy(
                model = variant.model.suffixPath("${typePrefixes[values["lit"]]}"),
                y = y
            )
        }.suffixed("campfire")
    )

    private fun getYRotation(facing: String): Int = when (facing) {
        "west" -> 90
        "north" -> 180
        "east" -> 270
        else -> 0
    }

}
