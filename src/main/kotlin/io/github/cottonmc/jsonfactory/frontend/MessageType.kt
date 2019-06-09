package io.github.cottonmc.jsonfactory.frontend

/**
 * Types for messages in a [Frontend]'s log.
 */
enum class MessageType {
    /**
     * A regular information message.
     */
    Default,

    /**
     * A warning, displayed when user input is invalid or a non-fatal error during generation.
     */
    Warn,

    /**
     * An error that caused generation to fail.
     */
    Error,

    /**
     * An important and highlighted message.
     */
    Important
}
