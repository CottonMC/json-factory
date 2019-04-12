package io.github.cottonmc.jsonfactory.output.model

import io.github.cottonmc.jsonfactory.output.Json
import io.github.cottonmc.jsonfactory.output.Property
import io.github.cottonmc.jsonfactory.output.createProperties
import io.github.cottonmc.jsonfactory.output.model.ModelBlockState.Variant

/**
 * A `blockstates` file using the multipart format.
 */
data class MultipartBlockState(val multipart: List<Multipart>) : Json {
    data class Multipart(val apply: Variant, val `when`: When? = null) : Json.ByProperties {
        override val properties = createProperties { self ->
            +self::apply
            +self::`when`.removeIfNull()
        }
    }

    data class When(val state: String, val value: String) : Json.ByProperties {
        override val properties = createProperties {
            +Property(state, value)
        }
    }
}
