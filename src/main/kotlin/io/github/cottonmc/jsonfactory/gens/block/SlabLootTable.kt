package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.loot.*
import io.github.cottonmc.jsonfactory.output.loot.Function
import io.github.cottonmc.jsonfactory.output.suffixed

internal object SlabLootTable : AbstractContentGenerator(
    "slab.loot_table", "loot_tables/blocks",
    GeneratorInfo.SLABS, resourceRoot = ResourceRoot.Data
) {
    override fun generate(id: Identifier) = listOf(
        LootTable(
            pools = listOf(
                Pool(
                    entries = listOf(
                        Entry(
                            name = id.suffixPath("_slab"),
                            functions = listOf(
                                Function(Identifier.mc("explosion_decay")),
                                Function.SetCount(
                                    conditions = listOf(
                                        Condition.BlockStateProperty(
                                            id.suffixPath("_slab"),
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
        ).suffixed("slab")
    )
}
