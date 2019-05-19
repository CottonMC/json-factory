package io.github.cottonmc.jsonfactory.gui.util

import arrow.core.Either
import arrow.core.Left
import arrow.core.Right
import io.github.cottonmc.jsonfactory.data.Identifier

object IdentifierParser {
    fun getIds(string: String): Either<String, List<Identifier>> {
        if (string.isBlank()) {
            return Left("The ID input is empty.")
        }

        val split = string.split(',').map(String::trim)
        return Right(split.mapNotNull { idText ->
            Identifier.orNull(idText).also { id ->
                if (id == null) {
                    return Left("Invalid ID: $idText")
                }
            }
        })
    }
}
