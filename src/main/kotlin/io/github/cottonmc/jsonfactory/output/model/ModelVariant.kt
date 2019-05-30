package io.github.cottonmc.jsonfactory.output.model

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.output.Json
import io.github.cottonmc.jsonfactory.output.createProperties

/**
 * A variant in a blockstate definition. Used by [VariantBlockState] and [MultipartBlockState].
 *
 * @property model the model
 * @property x the x rotation
 * @property y the y rotation
 * @property uvlock the uvlock flag (prevents texture rotation)
 */
data class ModelVariant(val model: Identifier, val x: Int = 0, val y: Int = 0, val uvlock: Boolean = false) :
    Json.ByProperties {
    override val properties = createProperties { self ->
        +self::model

        if (x != 0) +self::x
        if (y != 0) +self::y
        if (uvlock) +self::uvlock
    }
}
