package io.github.cottonmc.jsonfactory.data

import java.io.File

interface Output {
    fun writeToFile(file: File)
}
