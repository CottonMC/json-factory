package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.Model
import io.github.cottonmc.jsonfactory.output.Suffixed

internal object PressurePlateBlockModel : ContentGenerator("Pressure Plate Block Model", "models/block",
    GeneratorInfo.PRESSURE_PLATES
) {
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
