package io.github.cottonmc.jsonfactory.data.gens

import io.github.cottonmc.jsonfactory.data.types.Identifier
import io.github.cottonmc.jsonfactory.data.output.loot.Condition
import io.github.cottonmc.jsonfactory.data.output.loot.Entry
import io.github.cottonmc.jsonfactory.data.output.loot.LootTable
import io.github.cottonmc.jsonfactory.data.output.loot.Pool

object BasicLootTable : ContentGenerator<LootTable>("Basic Loot Table", "loot_tables/blocks", Categories.Block, resourceRoot = ResourceRoot.Data) {
    override fun generate(id: Identifier) = listOf(
        LootTable(
            pools = listOf(
                Pool(
                    entries = listOf(
                        Entry(
                            id
                        )
                    ),
                    conditions = listOf(
                        Condition(
                            condition = Identifier.mc("survives_explosion")
                        )
                    )
                )
            )
        )
    )
}
