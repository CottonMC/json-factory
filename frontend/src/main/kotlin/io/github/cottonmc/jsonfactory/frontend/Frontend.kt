package io.github.cottonmc.jsonfactory.frontend

import java.nio.file.Path

interface Frontend {
    fun printMessage(msg: String, type: MessageType = MessageType.Default)
    fun printSeparator()
    fun onFinishedGenerating()
    fun shouldOverwriteFile(path: Path): Boolean

    /**
     * Shows the user the output path selection menu, if one exists, and returns
     * the directory if one is selected or `null` if nothing is selected.
     */
    fun selectOutputDirectory(): Path?
}
