package io.github.cottonmc.jsonfactory.data.gens

import io.github.cottonmc.jsonfactory.data.ContentGenerator
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.types.LootTable

object BasicLootTable : ContentGenerator<LootTable>("Basic Loot Table", "loot_tables/blocks", Category.Block, resourceRoot = ResourceRoot.Data) {
    override fun generate(id: Identifier) = container(LootTable(id))
}
