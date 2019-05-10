package io.github.cottonmc.jsonfactory.example

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.Model
import io.github.cottonmc.jsonfactory.plugin.Plugin

object ExamplePlugin : Plugin {
    override val generators: List<ContentGenerator> = listOf(
        object : AbstractContentGenerator("my gen", "models/block", GeneratorInfo.BLOCK) {
            override fun generate(id: Identifier) = listOf(
                Model(
                    parent = id.prefixPath("parent_"),
                    textures = mapOf("particle" to id.prefixPath("block/"))
                )
            )
        }
    )
}
