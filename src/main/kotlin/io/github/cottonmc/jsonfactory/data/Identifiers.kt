package io.github.cottonmc.jsonfactory.data

import arrow.core.Either
import arrow.core.Left
import arrow.core.Right
import io.github.cottonmc.jsonfactory.data.Identifier

/**
 * [Identifier] utilities.
 * @since 0.5.0
 */
object Identifiers {
    /**
     * Converts a [list of ID strings][list] into a list of identifiers (right) or an error message (left).
     */
    fun convertToIds(list: List<String>): Either<String, List<Identifier>> {
        if (list.isEmpty()) {
            return Left("The ID input is empty.")
        }

        return Right(list.mapNotNull { idText ->
            Identifier.orNull(idText).also { id ->
                if (id == null) {
                    return Left("Invalid ID: $idText")
                }
            }
        })
    }

    /**
     * Converts a comma-separated [id string][idString] into a into a list of identifiers (right)
     * or an error message (left). Whitespaces before and after the comma-separated parts are trimmed.
     *
     * Equivalent to `convertToIds(idString.split(",").map(String::trim))`.
     */
    fun convertToIds(idString: String): Either<String, List<Identifier>> =
        convertToIds(idString.split(",").map(String::trim))
}
