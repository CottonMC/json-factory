package io.github.cottonmc.jsonfactory.cli

import io.github.cottonmc.jsonfactory.frontend.Frontend
import io.github.cottonmc.jsonfactory.frontend.MessageType
import java.nio.file.Path

class Cli(val outputDirectory: Path) : Frontend {
    override fun printMessage(msg: String, type: MessageType, vararg messageParameters: Any?) = when (type) {
        // TODO: I18n
        MessageType.Warn, MessageType.Error -> System.err.println(msg)
        else -> println(msg)
    }

    override fun printSeparator() = println("-".repeat(25))
    override fun onFinishedGenerating() {}
    override suspend fun shouldOverwriteFile(path: Path) = false
    override suspend fun selectOutputDirectory(): Path = outputDirectory
}
