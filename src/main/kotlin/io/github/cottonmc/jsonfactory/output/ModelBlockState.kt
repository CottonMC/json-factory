package io.github.cottonmc.jsonfactory.output

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.BlockStateProperty

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
        // Uses strings instead of `BlockStateProperty`s as the key of `values`
        @Deprecated("", replaceWith = ReplaceWith("create(id, properties, transform)"))
        internal inline fun createOld(
            id: Identifier,
            properties: List<BlockStateProperty>,
            crossinline transform: (values: Map<String, String>, variant: Variant) -> Variant = { _, variant -> variant }
        ) = create(id, properties) { values, variant ->
            transform(values.mapKeys { (prop, _) -> prop.name }, variant)
        }

        /**
         * Creates a [ModelBlockState] from an [id], a list of [properties] and a [transform] function.
         */
        fun create(
            id: Identifier,
            properties: List<BlockStateProperty>,
            transform: (values: Map<BlockStateProperty, String>, variant: Variant) -> Variant = { _, variant -> variant }
        ): ModelBlockState {
            val output: Sequence<Sequence<Pair<BlockStateProperty, String>>> = properties.fold(sequenceOf(emptySequence())) { acc, prop ->
                acc.flatMap { existing ->
                    prop.values.asSequence().map { value ->
                        sequence {
                            yield(prop to value)
                            yieldAll(existing)
                        }
                    }
                }
            }

            val variants = HashMap<String, Variant>()

            for (o in output) {
                val key = o.joinToString(separator = ",") { (prop, value) -> "${prop.name}=$value" }
                val variant = Variant(id.prefixPath("block/"))

                variants[key] = transform(o.toMap(), variant)
            }

            return ModelBlockState(variants)
        }
    }
}
