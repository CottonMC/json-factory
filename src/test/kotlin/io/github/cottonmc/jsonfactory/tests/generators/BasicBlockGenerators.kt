package io.github.cottonmc.jsonfactory.tests.generators

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.Gens
import io.github.cottonmc.jsonfactory.tests.util.JsonScenario
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object BasicBlockGenerators : Spek({
    Feature("Basic Block") {
        val id = Identifier("minecraft", "dirt")

        JsonScenario("generating the block model", id, "expected/basic_block_model.json", Gens.BASIC_BLOCK_MODEL)
        JsonScenario("generating the block state", id, "expected/basic_block_state.json", Gens.BASIC_BLOCK_STATE)
        JsonScenario("generating the block item model", id, "expected/basic_block_item_model.json", Gens.BASIC_BLOCK_ITEM_MODEL)
        JsonScenario("generating the loot table", id, "expected/basic_loot_table.json", Gens.BASIC_LOOT_TABLE)
    }
})
