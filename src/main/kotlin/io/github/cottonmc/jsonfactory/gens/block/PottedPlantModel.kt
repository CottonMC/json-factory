package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.Suffixed
import io.github.cottonmc.jsonfactory.output.model.Model

object PottedPlantModel : ContentGenerator("Potted Plant Model", "models/block", GeneratorInfo.POTTED_PLANTS) {
    override fun generate(id: Identifier) = listOf(
        Suffixed(
            Model(
                parent = Identifier.mc("block/flower_pot_cross"),
                textures = mapOf(
                    "plant" to id
                )
            ),
            "potted"
        )
    )
}
