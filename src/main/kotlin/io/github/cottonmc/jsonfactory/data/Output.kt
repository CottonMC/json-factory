package io.github.cottonmc.jsonfactory.data

import java.io.File

/**
 * A type that can write itself to a file.
 */
interface Output {
    /**
     * Writes this object to the [file].
     */
    fun writeToFile(file: File)
}
