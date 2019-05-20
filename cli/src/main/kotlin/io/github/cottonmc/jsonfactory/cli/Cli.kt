package io.github.cottonmc.jsonfactory.cli

import io.github.cottonmc.jsonfactory.frontend.Frontend
import io.github.cottonmc.jsonfactory.frontend.MessageType
import io.github.cottonmc.jsonfactory.frontend.i18n.CombinedI18n
import io.github.cottonmc.jsonfactory.frontend.i18n.ResourceBundleI18n
import io.github.cottonmc.jsonfactory.frontend.i18n.invoke
import java.nio.file.Path

internal class Cli(val outputDirectory: Path?) : Frontend {
    val i18n = CombinedI18n(
        ResourceBundleI18n("json-factory.i18n.I18n-cli"),
        ResourceBundleI18n.createBackendI18n()
    )

    override fun printMessage(msg: String, type: MessageType, vararg messageParameters: Any?) = when (type) {
        // TODO: I18n
        MessageType.Warn, MessageType.Error -> System.err.println(i18n(msg, messageParameters))
        else -> println(i18n(msg, messageParameters))
    }

    override fun printSeparator() {}
    override fun onFinishedGenerating() {}
    override suspend fun shouldOverwriteFile(path: Path) = false
    override suspend fun selectOutputDirectory(): Path = outputDirectory!!
}
