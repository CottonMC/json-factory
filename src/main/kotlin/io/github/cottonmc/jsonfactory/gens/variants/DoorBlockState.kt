package io.github.cottonmc.jsonfactory.gens.variants

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.ListProperty
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.output.ModelBlockState
import io.github.cottonmc.jsonfactory.output.Suffixed

object DoorBlockState : ContentGenerator("Door Block State", "blockstates", Categories.BlockVariants) {
    private val properties = listOf(
        /* facing */ ListProperty.horizontalFacing,
        /* half   */ ListProperty.halfUL,
        /* hinge  */ ListProperty.doorHinge,
        /* open   */ ListProperty.open
    )

    override fun generate(id: Identifier) = listOf(
        Suffixed(
            ModelBlockState.create(id, properties) {
                    values, variant ->
                val suffix = when {
                    values["open"] == "true" && values["hinge"] == "left" -> "_hinge"
                    values["open"] == "false" && values["hinge"] == "right" -> "_hinge"
                    else -> ""
                }

                variant.copy(
                    model = variant.model.suffixPath("_door$suffix"),
                    y = getYRotation(values["facing"] ?: "").let {
                        if (values["open"] == "true")
                            (it + (if (values["hinge"] == "left") 90 else 270)) % 360
                        else it
                    }
                )
            }, "trapdoor"
        )
    )

    private fun getYRotation(facing: String) = when (facing) {
        "south" -> 90
        "west" -> 180
        "north" -> 270
        else -> 0
    }
}
