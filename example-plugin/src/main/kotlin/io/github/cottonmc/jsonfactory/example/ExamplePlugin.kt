package io.github.cottonmc.jsonfactory.example

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.frontend.AutoFill
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.Model
import io.github.cottonmc.jsonfactory.frontend.plugin.Plugin

object ExamplePlugin : Plugin {
    // TODO: I18n
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

    override val autoFills = setOf(
        AutoFill("auto_fill.test1", "minecraft:cobblestone,minecraft:dirt"),
        AutoFill("auto_fill.test2", "minecraft:sand")
    )
}
