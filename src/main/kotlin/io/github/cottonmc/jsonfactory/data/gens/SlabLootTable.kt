package io.github.cottonmc.jsonfactory.data.gens

import io.github.cottonmc.jsonfactory.data.ContentGenerator
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.output.LootTable
import io.github.cottonmc.jsonfactory.data.output.Suffixed

object SlabLootTable : ContentGenerator<LootTable>("Slab Loot Table", "loot_tables/blocks", Category.Block, resourceRoot = ResourceRoot.Data) {
    override fun generate(id: Identifier) = listOf(Suffixed(LootTable(id.let {
        it.copy(
            path = it.path + "_slab"
        )
    }/*, true*/), "slab"))
}
