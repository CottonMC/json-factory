package io.github.cottonmc.jsonfactory.gens

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.output.Model
import io.github.cottonmc.jsonfactory.output.Suffixed

object PressurePlateBlockModel : ContentGenerator("Pressure Plate Block Model", "models/block", Categories.BlockVariants) {
    override fun generate(id: Identifier) = listOf(
        Suffixed(Model(
            parent = Identifier.mc("block/pressure_plate_up"),
            textures = mapOf(
                "texture" to id.prefixPath("block/")
            )
        ), "pressure_plate"),
        Suffixed(Model(
            parent = Identifier.mc("block/pressure_plate_down"),
            textures = mapOf(
                "texture" to id.prefixPath("block/")
            )
        ), "pressure_plate_down")
    )
}
