package io.github.cottonmc.jsonfactory.frontend

import java.io.File

interface Frontend {
    fun printMessage(msg: String, type: MessageType = MessageType.Default, vararg messageParameters: Any?)
    fun printSeparator()
    fun onFinishedGenerating()
    suspend fun shouldOverwriteFile(file: File): Boolean

    /**
     * Shows the user the output file selection menu, if one exists, and returns
     * the directory if one is selected or `null` if nothing is selected.
     */
    suspend fun selectOutputDirectory(): File?
}
