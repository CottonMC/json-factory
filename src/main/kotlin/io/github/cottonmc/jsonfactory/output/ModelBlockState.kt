package io.github.cottonmc.jsonfactory.output

import io.github.cottonmc.jsonfactory.data.Identifier

data class ModelBlockState(val variants: Map<String, Variant>) : Json {
    data class Variant(val model: Identifier)
}
