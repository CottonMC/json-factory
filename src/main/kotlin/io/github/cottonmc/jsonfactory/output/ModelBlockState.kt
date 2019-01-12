package io.github.cottonmc.jsonfactory.output

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.ListProperty

data class ModelBlockState(val variants: Map<String, Variant>) : Json {
    data class Variant(val model: Identifier, val x: Int = 0, val y: Int = 0, val uvlock: Boolean = false) : Json.ByProperties {
        override val properties = this.createProperties { self ->
            +self::model

            if (x != 0) +self::x
            if (y != 0) +self::y
            if (uvlock) +self::uvlock
        }
    }

    companion object {
        /**
         * Creates a [ModelBlockState] from an [id], a list of [properties] and a [transform] function.
         */
        fun create(
            id: Identifier,
            properties: List<ListProperty>,
            transform: (values: Map<String, String>, variant: Variant) -> Variant = { _, variant -> variant }
        ): ModelBlockState {
            val output: List<List<Pair<String, String>>> = properties.fold(listOf(emptyList())) { acc, prop ->
                acc.flatMap { prevs ->
                    prop.values.map { value ->
                        listOf(prop.name to value) + prevs
                    }
                }
            }

            val variants = HashMap<String, Variant>()

            for (o in output) {
                val key = o.joinToString(separator = ",") { (name, value) -> "$name=$value" }
                val variant = Variant(id.copy(path = "block/" + id.path))

                variants[key] = transform(o.toMap(), variant)
            }

            return ModelBlockState(variants)
        }
    }
}
