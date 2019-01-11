package io.github.cottonmc.jsonfactory.data.gens

import io.github.cottonmc.jsonfactory.data.ContentGenerator

object Gens {
    val allGens = arrayOf(
        BasicBlockModel,
        BasicItemModel,
        BasicBlockItemModel,
        BasicBlockState,
        BasicLootTable,
        PlaceholderTexture(ContentGenerator.Category.Block),
        PlaceholderTexture(ContentGenerator.Category.Item)
    )
}
