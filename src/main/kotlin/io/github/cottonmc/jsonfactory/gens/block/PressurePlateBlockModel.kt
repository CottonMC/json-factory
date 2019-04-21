package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.Model
import io.github.cottonmc.jsonfactory.output.suffixed

internal object PressurePlateBlockModel : ContentGenerator(
    "pressure_plates.block_model", "models/block",
    GeneratorInfo.PRESSURE_PLATES
) {
    override fun generate(id: Identifier) = listOf(
        Model(
            parent = Identifier.mc("block/pressure_plate_up"),
            textures = mapOf(
                "texture" to id.prefixPath("block/")
            )
        ).suffixed("pressure_plate"),
        Model(
            parent = Identifier.mc("block/pressure_plate_down"),
            textures = mapOf(
                "texture" to id.prefixPath("block/")
            )
        ).suffixed("pressure_plate_down")
    )
}
