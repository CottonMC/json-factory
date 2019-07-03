package io.github.cottonmc.jsonfactory.data

import arrow.core.*
import arrow.core.extensions.either.monad.binding

/**
 * [Identifier] utilities.
 * @since 0.5.0
 */
// TODO: Some sort of i18n for these
object Identifiers {
    /**
     * Converts a [list of ID strings][list] into a list of identifiers (right) or an error message (left).
     */
    fun convertToIds(list: List<String>): Either<String, List<Identifier>> {
        if (list.isEmpty()) {
            return Left("The ID input is empty.")
        }

        return binding {
            list.map { idText ->
                Identifier.parse(idText).bind()
            }
        }
    }

    /**
     * Converts a comma-separated [id string][idString] into a into a list of identifiers (right)
     * or an error message (left). Whitespaces before and after the comma-separated parts are trimmed.
     *
     * Equivalent to `convertToIds(idString.split(",").map(String::trim))`.
     */
    fun convertToIds(idString: String): Either<String, List<Identifier>> =
        convertToIds(idString.split(",").map(String::trim))

    /**
     * Converts a [list of ID strings][list] into a list of validated identifiers (right) or an error message (left).
     *
     * The resulting identifiers will be valid for using in Minecraft.
     * @see convertToIds
     * @see Identifier.hasValidNamespace
     * @see Identifier.hasValidPath
     */
    fun convertToValidatedIds(list: List<String>): Either<String, List<Identifier>> =
        convertToIds(list).flatMap {
            it.map { id ->
                when {
                    !id.hasValidNamespace() -> return@flatMap Left("Invalid namespace: ${id.namespace} in $id")
                    !id.hasValidPath() -> return@flatMap Left("Invalid path: ${id.path} in $id")
                    else -> id
                }
            }.right()
        }
}
