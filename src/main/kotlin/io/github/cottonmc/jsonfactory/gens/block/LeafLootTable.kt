package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.loot.*
import io.github.cottonmc.jsonfactory.output.loot.Function
import io.github.cottonmc.jsonfactory.output.suffixed

internal object LeafLootTable : AbstractContentGenerator(
    "leaves.loot_table", "loot_tables/blocks",
    GeneratorInfo.LEAVES, resourceRoot = ResourceRoot.Data
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
                                    name = id.suffixPath("_leaves"),
                                    conditions = listOf(
                                        Condition.Alternative(
                                            listOf(
                                                Condition.MatchTool(mapOf("item" to Identifier.mc("shears"))),
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
                                        )
                                    )
                                ),
                                Entry(
                                    name = id.suffixPath("_sapling"),
                                    conditions = listOf(
                                        Condition(Identifier.mc("survives_explosion")),
                                        Condition.TableBonus(
                                            Identifier.mc("fortune"), chances = listOf(
                                                0.05, 0.0625, 0.83333336, 0.1
                                            )
                                        )
                                    )
                                )
                            )
                        )
                    )
                ),
                Pool(
                    entries = listOf(
                        Entry(
                            name = Identifier.mc("stick"),
                            conditions = listOf(
                                Condition.TableBonus(
                                    Identifier.mc("fortune"), chances = listOf(
                                        0.02,
                                        0.022222223,
                                        0.025,
                                        0.033333335,
                                        0.1
                                    )
                                )
                            ),
                            functions = listOf(
                                Function.SetCountMinMax(emptyList(), min = 1.0, max = 2.0),
                                Function(Identifier.mc("explosion_decay"))
                            )
                        )
                    ),
                    conditions = listOf(
                        Condition.Inverted(
                            Condition.Alternative(
                                listOf(
                                    Condition.MatchTool(predicate = mapOf("item" to Identifier.mc("shears"))),
                                    Condition.MatchTool(
                                        predicate = mapOf(
                                            "enchantments" to listOf(
                                                mapOf(
                                                    "enchantment" to Identifier.mc("silk_touch"),
                                                    "levels" to mapOf("min" to 1)
                                                )
                                            )
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
            )
        ).suffixed("leaves")
    )
}
