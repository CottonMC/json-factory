package io.github.cottonmc.jsonfactory.data.gens

import io.github.cottonmc.jsonfactory.data.ContentGenerator
import io.github.cottonmc.jsonfactory.data.types.Identifier
import io.github.cottonmc.jsonfactory.data.output.Suffixed
import io.github.cottonmc.jsonfactory.data.output.loot.*
import io.github.cottonmc.jsonfactory.data.output.loot.Function

object SlabLootTable : ContentGenerator<LootTable>("Slab Loot Table", "loot_tables/blocks", Categories.Block, resourceRoot = ResourceRoot.Data) {
    override fun generate(id: Identifier) = listOf(
        Suffixed(
            LootTable(
                pools = listOf(
                    Pool(
                        entries = listOf(
                            Entry(
                                name = id.copy(path = id.path + "_slab"),
                                functions = listOf(
                                    Function(Identifier.mc("explosion_decay")),
                                    Function.SetCount(
                                        conditions = listOf(
                                            Condition.BlockStateProperty(
                                                id.copy(path = id.path + "_slab"),
                                                properties = mapOf(
                                                    "type" to "double"
                                                )
                                            )
                                        ),
                                        count = 2
                                    )
                                )
                            )
                        )
                    )
                )
            ), "slab")
    )
}
