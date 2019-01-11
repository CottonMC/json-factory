package io.github.cottonmc.jsonfactory.data

import com.beust.klaxon.JsonObject
import java.io.File

/**
 * A JSON output.
 */
interface Json : Output {
    /**
     * Converts this object to a Klaxon JsonObject.
     */
    fun toJson(): JsonObject
    override fun writeToFile(file: File) = file.writeText(Serializer.toJson(this))
}
