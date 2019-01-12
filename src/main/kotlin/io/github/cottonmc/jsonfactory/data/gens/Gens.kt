package io.github.cottonmc.jsonfactory.data.gens

import io.github.cottonmc.jsonfactory.data.types.Identifier

object Gens {
    val allGens = arrayOf(
        BasicBlockModel,
        BasicItemModel,
        BasicBlockItemModel,
        BasicBlockState,
        BasicLootTable,
        PlaceholderTexture(ContentGenerator.Categories.Block),
        PlaceholderTexture(ContentGenerator.Categories.Item),
        SlabBlockModel,
        SlabBlockState,
        SlabLootTable,
        BaseOreBlockModel,
        OreBlockModel("Stone", Identifier.mc("block/stone")),
        OreBlockModel("Netherrack", Identifier.mc("block/netherrack"))
    )
}
