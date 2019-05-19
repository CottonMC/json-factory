package io.github.cottonmc.jsonfactory.data

import arrow.core.Either
import arrow.core.Left
import arrow.core.Right
import io.github.cottonmc.jsonfactory.data.Identifier

object Identifiers {
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

    fun convertToIds(idString: String): Either<String, List<Identifier>> =
        convertToIds(idString.split(",").map(String::trim))
}
