package io.github.cottonmc.jsonfactory.data

import com.beust.klaxon.JsonObject

interface ToJson {
    fun toJson(): JsonObject
}
