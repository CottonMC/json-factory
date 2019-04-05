package io.github.cottonmc.jsonfactory.gens.variants

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.ListProperty
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.output.ModelBlockState
import io.github.cottonmc.jsonfactory.output.Suffixed

internal object ButtonBlockState : ContentGenerator("Button Block State", "blockstates",
    Categories.BlockVariants, Subcategories.Buttons
) {
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

    override fun generate(id: Identifier) = listOf(Suffixed(
        ModelBlockState.create(id, listOf(ListProperty.horizontalFacing, ListProperty.powered, ListProperty.buttonFace)) { values, variant ->
            val suffix = if (values["powered"] == "true") "_pressed" else ""

            variant.copy(
                model = variant.model.suffixPath("_button$suffix"),
                x = getXRotation(values["face"]!!),
                y = getYRotation(values["facing"]!!).let {
                    if (values["face"] == "ceiling") (it + 180) % 360
                    else it
                },
                uvlock = values["face"] == "wall"
            )
        },
        "button"))
}
