package io.github.cottonmc.jsonfactory.data.output.loot

import io.github.cottonmc.jsonfactory.data.types.Identifier
import io.github.cottonmc.jsonfactory.data.output.Json

data class LootTable(val pools: List<Pool>, val type: Identifier = Identifier.mc("block")) : Json
