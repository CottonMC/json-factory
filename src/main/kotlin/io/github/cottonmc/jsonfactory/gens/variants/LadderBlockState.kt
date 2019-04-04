package io.github.cottonmc.jsonfactory.gens.variants

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.ListProperty
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.output.ModelBlockState
import io.github.cottonmc.jsonfactory.output.Suffixed

internal object LadderBlockState : ContentGenerator("Ladder Block State", "blockstates", Categories.BlockVariants) {
    private fun getYRotation(facing: String) = when (facing) {
        "east" -> 90
        "south" -> 180
        "west" -> 270
        else -> 0
    }

    override fun generate(id: Identifier) = listOf(
        Suffixed(
            ModelBlockState.create(id, listOf(ListProperty.horizontalFacing)) { values, variant ->
                variant.copy(
                    model = variant.model.suffixPath("_ladder"),
                    y = getYRotation(values["facing"]!!)
                )
            },
            "ladder"
        )
    )
}
