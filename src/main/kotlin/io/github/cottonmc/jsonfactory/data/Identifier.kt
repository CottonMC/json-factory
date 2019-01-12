package io.github.cottonmc.jsonfactory.data

import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

/**
 * An identifier with a [namespace] and a [path].
 */
data class Identifier(val namespace: String, val path: String) {
    override fun toString() = "$namespace:$path"

    companion object : JsonSerializer<Identifier> {
        /**
         * Creates an Identifier from a [combined] string in the `namespace:path` format.
         *
         * @throws IllegalArgumentException if the input is invalid
         */
        operator fun invoke(combined: String) =
            orNull(combined) ?: throw IllegalArgumentException("Not a valid identifier")

        /**
         * Creates an Identifier from a [combined] string in the `namespace:path` format.
         * Returns null if the input is invalid.
         */
        fun orNull(combined: String): Identifier? =
            if (':' !in combined) null
            else {
                val split = combined.split(':')
                Identifier(split[0], split[1])
            }

        override fun serialize(src: Identifier, typeOfSrc: Type?, context: JsonSerializationContext?) =
            JsonPrimitive(src.toString())

        /**
         * Creates an Identifier from the `minecraft` namespace and the [path].
         */
        fun mc(path: String) = Identifier("minecraft", path)
    }
}
