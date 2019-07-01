package io.github.cottonmc.jsonfactory.frontend

import java.nio.file.Path
import java.util.logging.Level

/**
 * A json-factory frontend. Used by [ContentWriter] for generating content and printing messages.
 */
interface Frontend {
    /**
     * Prints a [message][msg] at the [level] in the frontend's log, formatted with the [messageParameters].
     * Errors and warnings should always be displayed to the user.
     *
     * The message can be an [i18n][io.github.cottonmc.jsonfactory.frontend.i18n.I18n] key.
     */
    fun log(msg: String, level: Level = Level.INFO, vararg messageParameters: Any?)

    /**
     * Prints a separator in the frontend's log.
     */
    // TODO: Is this needed
    fun printSeparator()

    /**
     * Called when content generation is finished.
     */
    fun onFinishedGenerating()

    /**
     * Checks if a file [path] should be overwritten with a new generated file.
     */
    suspend fun shouldOverwriteFile(path: Path): Boolean

    /**
     * Shows the user the output file selection menu, if one exists, and returns
     * the directory if one is selected or `null` if nothing is selected.
     */
    suspend fun selectOutputDirectory(): Path?
}
