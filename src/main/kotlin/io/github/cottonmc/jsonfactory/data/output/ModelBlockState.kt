package io.github.cottonmc.jsonfactory.data.output

import io.github.cottonmc.jsonfactory.data.types.Identifier

data class ModelBlockState(val variants: Map<String, Variant>) : Json {
    data class Variant(val model: Identifier)
}
