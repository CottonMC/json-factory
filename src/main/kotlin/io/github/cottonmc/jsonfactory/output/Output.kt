package io.github.cottonmc.jsonfactory.output

import java.io.File

/**
 * A type that can write itself to a file.
 */
interface Output {
    /**
     * The file name suffix (placed before the extension).
     */
    val suffix: String get() = ""

    /**
     * Writes this object to the [file].
     */
    fun writeToFile(file: File)
}
