package io.github.cottonmc.jsonfactory.output.model

import io.github.cottonmc.jsonfactory.data.BlockStateProperty
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.output.Json

/**
 * Represents a `blockstates` file using [variants].
 */
data class VariantBlockState(val variants: Map<String, ModelVariant>) : Json {
    companion object {
        /**
         * Creates a [VariantBlockState] from an [id], a set of [properties] and a [transform] function.
         */
        fun create(
            id: Identifier,
            properties: Set<BlockStateProperty>,
            transform: (values: BlockStateProperty.ValueMap, variant: ModelVariant) -> ModelVariant = { _, variant -> variant }
        ): VariantBlockState {
            val output: Sequence<Sequence<Pair<BlockStateProperty, String>>> =
                properties.fold(sequenceOf(emptySequence())) { acc, prop ->
                    acc.flatMap { existing ->
                        prop.values.asSequence().map { value ->
                            sequence {
                                yield(prop to value)
                                yieldAll(existing)
                            }
                        }
                    }
                }

            val variants = HashMap<String, ModelVariant>()

            for (o in output) {
                val key = o.joinToString(separator = ",") { (prop, value) -> "${prop.name}=$value" }
                val variant = ModelVariant(model = id)

                variants[key] = transform(BlockStateProperty.ValueMap(o.toMap()), variant)
                    .let {
                        // Prefix the model here so the transformation can apply its own prefixes
                        it.copy(model = it.model.prefixPath("block/"))
                    }
            }

            return VariantBlockState(variants)
        }
    }
}
