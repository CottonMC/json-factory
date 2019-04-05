package io.github.cottonmc.jsonfactory.gens.variants

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.ListProperty
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.output.ModelBlockState
import io.github.cottonmc.jsonfactory.output.Suffixed

internal object FenceGateBlockState : ContentGenerator("Fence Gate Block State", "blockstates", Categories.BlockVariants, Subcategories.FenceGates) {
    override fun generate(id: Identifier) = listOf(
        Suffixed(
            ModelBlockState.create(id, listOf(ListProperty.horizontalFacing, ListProperty.gateInWall, ListProperty.open)) {
                    values, variant ->
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
            }, "fence_gate"
        )
    )

    private fun getYRotation(facing: String) = when (facing) {
        "west" -> 90
        "north" -> 180
        "east" -> 270
        else -> 0
    }
}
