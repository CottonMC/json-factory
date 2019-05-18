package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.loot.*
import io.github.cottonmc.jsonfactory.output.loot.Function
import io.github.cottonmc.jsonfactory.output.suffixed

internal object CampfireLootTable : AbstractContentGenerator(
    "campfire.loot_table",
    "loot_tables/blocks",
    GeneratorInfo.CAMPFIRES,
    resourceRoot = ResourceRoot.Data
) {
    override fun generate(id: Identifier) = listOf(
        LootTable(
            pools = listOf(
                Pool(
                    entries = listOf(
                        Entry(
                            name = null,
                            type = Identifier.mc("alternatives"),
                            children = listOf(
                                Entry(
                                    name = id.suffixPath("_campfire"),
                                    conditions = listOf(
                                        Condition.MatchTool(
                                            mapOf(
                                                "enchantments" to listOf(
                                                    mapOf(
                                                        "enchantment" to Identifier.mc("silk_touch"),
                                                        "levels" to mapOf("min" to 1)
                                                    )
                                                )
                                            )
                                        )
                                    )
                                ),
                                Entry(
                                    name = Identifier.mc("charcoal"),
                                    conditions = listOf(
                                        Condition(Identifier.mc("survives_explosion"))
                                    ),
                                    functions = listOf(
                                        Function.SetCount(
                                            emptyList(),
                                            2
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
            )
        ).suffixed("campfire")
    )
}
