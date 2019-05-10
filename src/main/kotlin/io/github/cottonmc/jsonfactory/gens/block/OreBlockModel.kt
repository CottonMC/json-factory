package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.Model

class OreBlockModel(display: String, private val base: Identifier) :
    AbstractContentGenerator(
        "Ore Block Model ($display)", "models/block",
        GeneratorInfo.ORES
    ) {
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
