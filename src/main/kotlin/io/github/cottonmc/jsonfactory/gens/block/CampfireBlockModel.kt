package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.Model
import io.github.cottonmc.jsonfactory.output.suffixed

internal object CampfireBlockModel : ContentGenerator("Campfire Off Block Model", "models/block", GeneratorInfo.CAMPFIRES) {
    override fun generate(id: Identifier) = listOf(
        Model(
            parent = Identifier.mc("block/campfire_off"),
            textures = mapOf(
                "log" to id.prefixPath("block/${id.path}_campfire_log")
            )
        ).suffixed("campfire_off"),
        Model(
            parent = Identifier.mc("block/campfire"),
            textures = mapOf(
                "log" to id.prefixPath("block/${id.path}_campfire_log"),
                "litlog" to id.copy(path = "block/${id.path}_campfire_log_lit")
            )
        ).suffixed("campfire")
    )
}
