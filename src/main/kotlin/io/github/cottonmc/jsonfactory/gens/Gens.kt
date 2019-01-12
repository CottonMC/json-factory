package io.github.cottonmc.jsonfactory.gens

import io.github.cottonmc.jsonfactory.data.Identifier

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
        OreBlockModel("Netherrack", Identifier.mc("block/netherrack")),
        PillarBlockModel,
        PillarBlockState,
        StairBlockModel,
        StairBlockState,
        StairLootTable
    )
}
