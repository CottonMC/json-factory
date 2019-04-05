package io.github.cottonmc.jsonfactory.gens.variants

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.output.Model
import io.github.cottonmc.jsonfactory.output.Suffixed

internal object StairBlockModel : ContentGenerator("Stair Block Model", "models/block",
    Categories.BlockVariants, Subcategories.Stairs
) {
    override fun generate(id: Identifier) = listOf(
        Suffixed(Model(
            parent = Identifier.mc("block/stairs"),
            textures = mapOf(
                "bottom" to id.prefixPath("block/"),
                "top" to id.prefixPath("block/"),
                "side" to id.prefixPath("block/")
            )
        ), "stairs"),
        Suffixed(Model(
            parent = Identifier.mc("block/inner_stairs"),
            textures = mapOf(
                "bottom" to id.prefixPath("block/"),
                "top" to id.prefixPath("block/"),
                "side" to id.prefixPath("block/")
            )
        ), "stairs_inner"),
        Suffixed(Model(
            parent = Identifier.mc("block/outer_stairs"),
            textures = mapOf(
                "bottom" to id.prefixPath("block/"),
                "top" to id.prefixPath("block/"),
                "side" to id.prefixPath("block/")
            )
        ), "stairs_outer")
    )
}
