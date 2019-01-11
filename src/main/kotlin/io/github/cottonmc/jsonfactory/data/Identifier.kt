package io.github.cottonmc.jsonfactory.data

/**
 * An identifier with a [namespace] and a [path].
 */
data class Identifier(val namespace: String, val path: String) {
    override fun toString() = "$namespace:$path"

    companion object {
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
    }
}
