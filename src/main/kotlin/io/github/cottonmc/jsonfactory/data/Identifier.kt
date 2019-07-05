package io.github.cottonmc.jsonfactory.data

import arrow.core.*
import arrow.core.extensions.either.monad.binding
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
                // size != 2 means that the string either doesn't have a namespace or has too many parts
                return Left("Identifiers must have exactly one colon")
            }

            return Identifier(split[0], split[1]).right()
        }

        /**
         * Parses a [collection of ID strings][idStrings] into a list of identifiers (right) or an error message (left).
         * @see parse
         */
        fun parseAll(idStrings: Collection<String>): Either<String, List<Identifier>> {
            if (idStrings.isEmpty()) {
                return Left("The ID input is empty.")
            }

            return binding {
                idStrings.map { idText ->
                    parse(idText).bind()
                }
            }
        }

        /**
         * Parses a comma-separated [id string][idString] into a into a list of identifiers (right)
         * or an error message (left). Whitespaces before and after the comma-separated parts are trimmed.
         *
         * Equivalent to `parseAll(idString.split(",").map(String::trim))`.
         * @see parse
         * @see parseAll
         */
        fun splitAndParse(idString: String): Either<String, List<Identifier>> =
            parseAll(idString.split(",").map(String::trim))

        /**
         * Validates a [list of identifiers][ids] into a list of validated identifiers (right) or an error message (left).
         *
         * The resulting identifiers will be valid for using in Minecraft.
         * @see Identifier.hasValidNamespace
         * @see Identifier.hasValidPath
         */
        fun validateAll(ids: List<Identifier>): Either<String, List<Identifier>> {
            for (id in ids) {
                if (!id.hasValidNamespace())
                    return Left("ID '$id' has an invalid namespace")
                else if (!id.hasValidPath())
                    return Left("ID '$id' has an invalid path")
            }

            return ids.right()
        }

        override fun serialize(src: Identifier, typeOfSrc: Type?, context: JsonSerializationContext?) =
            JsonPrimitive(src.toString())

        /**
         * Creates an Identifier from the `minecraft` namespace and the [path].
         */
        fun mc(path: String) = Identifier("minecraft", path)
    }
}
