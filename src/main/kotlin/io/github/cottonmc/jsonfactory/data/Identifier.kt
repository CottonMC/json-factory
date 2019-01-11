package io.github.cottonmc.jsonfactory.data

import java.lang.IllegalArgumentException

data class Identifier(val namespace: String, val path: String) {
    override fun toString() = "$namespace:$path"

    companion object {
        operator fun invoke(combined: String) =
            orNull(combined) ?: throw IllegalArgumentException("Not a valid identifier")

        fun orNull(combined: String): Identifier? =
            if (':' !in combined) null
            else {
                val split = combined.split(':')
                Identifier(split[0], split[1])
            }
    }
}
