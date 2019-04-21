package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.Model
import io.github.cottonmc.jsonfactory.output.prefixed

object PottedPlantModel : ContentGenerator("Potted Plant Model", "models/block", GeneratorInfo.POTTED_PLANTS) {
    override fun generate(id: Identifier) = listOf(
        Model(
            parent = Identifier.mc("block/flower_pot_cross"),
            textures = mapOf(
                "plant" to id.prefixPath("block/")
            )
        ).prefixed("potted")
    )
}
