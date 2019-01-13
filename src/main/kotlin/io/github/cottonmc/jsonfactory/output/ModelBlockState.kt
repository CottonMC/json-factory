package io.github.cottonmc.jsonfactory.output

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.ListProperty

/**
 * Represents a `blockstates` file using [variants].
 */
data class ModelBlockState(val variants: Map<String, Variant>) : Json {
    /**
     * A blockstate variant.
     *
     * @property model the model
     * @property x the x rotation
     * @property y the y rotation
     * @property uvlock the uvlock flag (prevents texture rotation)
     */
    data class Variant(val model: Identifier, val x: Int = 0, val y: Int = 0, val uvlock: Boolean = false) : Json.ByProperties {
        override val properties = createProperties { self ->
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
                acc.flatMap { existing ->
                    prop.values.map { value ->
                        listOf(prop.name to value) + existing
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
