package io.github.cottonmc.jsonfactory.data.types

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.Json

data class ModelBlockState(val variants: Map<String, Variant>) : Json {
    data class Variant(val model: Identifier)
}
