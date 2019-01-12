package io.github.cottonmc.jsonfactory.output.loot

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.output.Json

data class LootTable(val pools: List<Pool>, val type: Identifier = Identifier.mc("block")) : Json
