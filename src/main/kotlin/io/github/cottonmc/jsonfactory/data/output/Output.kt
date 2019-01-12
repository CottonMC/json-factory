package io.github.cottonmc.jsonfactory.data.output

import java.io.File

/**
 * A type that can write itself to a file.
 */
interface Output {
    val suffix: String get() = ""

    /**
     * Writes this object to the [file].
     */
    fun writeToFile(file: File)
}
