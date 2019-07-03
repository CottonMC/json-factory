package io.github.cottonmc.jsonfactory.data

import arrow.core.Either
import arrow.core.Left
import arrow.core.identity
import arrow.core.right
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.google.gson.annotations.JsonAdapter
import java.lang.reflect.Type

/**
 * An identifier with a [namespace] and a [path].
 */
@JsonAdapter(Identifier.Companion::class)
data class Identifier(val namespace: String, val path: String) {
    override fun toString() = "$namespace:$path"

    /**
     * Returns a new `Identifier` with its path prefixed with the [prefix].
     */
    fun prefixPath(prefix: String): Identifier = copy(path = prefix + path)

    /**
     * Returns a new `Identifier` with its path suffixed with the [suffix].
     */
    fun suffixPath(suffix: String): Identifier = copy(path = path + suffix)

    /**
     * Returns a new `Identifier` with its path wrapped with the [prefix] and the [suffix].
     */
    fun wrapPath(prefix: String, suffix: String): Identifier = copy(path = "$prefix$path$suffix")

    /**
     * Returns true if the [namespace] of this identifier is a valid Minecraft identifier namespace.
     */
    fun hasValidNamespace(): Boolean = NAMESPACE_REGEX.matches(namespace)

    /**
     * Returns true if the [path] of this identifier is a valid Minecraft identifier path.
     */
    fun hasValidPath(): Boolean = PATH_REGEX.matches(path)

    companion object : JsonSerializer<Identifier> {
        private val NAMESPACE_REGEX = "[a-z0-9_.-]+".toRegex()
        private val PATH_REGEX = "[a-z0-9/._-]+".toRegex()

        /**
         * Creates an Identifier from a [combined] string in the `namespace:path` format.
         *
         * @throws IllegalArgumentException if the input is invalid
         */
        operator fun invoke(combined: String): Identifier =
            parse(combined).fold({ throw IllegalArgumentException(it) }, ::identity)

        /**
         * Creates an Identifier from a [combined] string in the `namespace:path` format.
         * Returns null if the input is invalid.
         */
        fun orNull(combined: String): Identifier? =
            parse(combined).fold({ null }, ::identity)

        /**
         * Creates an Identifier from a [combined] string in the `namespace:path` format.
         * Returns `Either.Left` if the input is invalid.
         */
        fun parse(combined: String): Either<String, Identifier> {
            val split = combined.split(':')

            if (split.size != 2) {
                // size != 2 means that the string either doesn't have a namespace or
                // has too many parts
                return Left("Identifiers must have exactly one colon")
            }

            return Identifier(split[0], split[1]).right()
        }

        override fun serialize(src: Identifier, typeOfSrc: Type?, context: JsonSerializationContext?) =
            JsonPrimitive(src.toString())

        /**
         * Creates an Identifier from the `minecraft` namespace and the [path].
         */
        fun mc(path: String) = Identifier("minecraft", path)
    }
}
