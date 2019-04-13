package io.github.cottonmc.jsonfactory.output.model

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.BlockStateProperty
import io.github.cottonmc.jsonfactory.output.Json
import io.github.cottonmc.jsonfactory.output.createProperties

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
    data class Variant(val model: Identifier, val x: Int = 0, val y: Int = 0, val uvlock: Boolean = false) :
        Json.ByProperties {
        override val properties = createProperties { self ->
            +self::model

            if (x != 0) +self::x
            if (y != 0) +self::y
            if (uvlock) +self::uvlock
        }
    }

    companion object {
        /**
         * Creates a [ModelBlockState] from an [id], a set of [properties] and a [transform] function.
         *
         * @param prefix an optional prefix for the model file
         */
        fun create(
            id: Identifier,
            properties: Set<BlockStateProperty>,
            prefix: String = "",
            transform: (values: BlockStateProperty.ValueMap, variant: Variant) -> Variant = { _, variant -> variant }
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
                val variant = Variant(id.prefixPath("block/$prefix"))

                variants[key] = transform(BlockStateProperty.ValueMap(o.toMap()), variant)
            }

            return ModelBlockState(variants)
        }
    }
}
