package io.github.cottonmc.jsonfactory.output

import io.github.cottonmc.jsonfactory.data.Identifier

data class ModelBlockState(val variants: Map<String, Variant>) : Json {
    data class Variant(val model: Identifier, val x: Int = 0, val y: Int = 0, val uvlock: Boolean = false) : Json.ByProperties {
        override val properties = createProperties { self ->
            +self::model

            if (x != 0) +self::x
            if (y != 0) +self::y
            if (uvlock) +self::uvlock
        }
    }
}
