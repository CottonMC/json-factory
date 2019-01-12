package io.github.cottonmc.jsonfactory.gens

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.output.Model

class OreBlockModel(display: String, private val base: Identifier) :
    ContentGenerator<Model>("Ore Block Model ($display)", "models/block", Categories.Ore) {
    override fun generate(id: Identifier) = listOf(
        Model(
            parent = id.copy(path = "block/ore_template"),
            textures = mapOf(
                "base" to base,
                "ore" to id.copy(path = "block/" + id.path)
            )
        )
    )
}
