package io.github.cottonmc.jsonfactory.data

import com.beust.klaxon.JsonObject
import java.io.File

interface ToJson : Output {
    fun toJson(): JsonObject
    override fun writeToFile(file: File) = file.writeText(Serializer.toJson(this))
}
