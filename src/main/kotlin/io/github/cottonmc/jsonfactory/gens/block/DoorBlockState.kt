package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.BlockStateProperty
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.ModelBlockState
import io.github.cottonmc.jsonfactory.output.suffixed

internal object DoorBlockState : ContentGenerator("Door Block State", "blockstates", GeneratorInfo.DOORS) {
    private val properties = listOf(
        /* facing */ BlockStateProperty.horizontalFacing,
        /* half   */ BlockStateProperty.halfUL,
        /* hinge  */ BlockStateProperty.doorHinge,
        /* open   */ BlockStateProperty.open
    )

    override fun generate(id: Identifier) = listOf(
        ModelBlockState.createOld(id, properties) {
                values, variant ->
            val suffix = "_" + getHalf(values["half"]) + when {
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
        }.suffixed(
            "door"
        )
    )

    private fun getYRotation(facing: String) = when (facing) {
        "south" -> 90
        "west" -> 180
        "north" -> 270
        else -> 0
    }

    private fun getHalf(half: String?) = when (half) {
        "lower" -> "bottom"
        else -> "top"
    }
}
