package io.github.cottonmc.jsonfactory.builder

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.frontend.Frontend
import io.github.cottonmc.jsonfactory.frontend.ContentWriter
import io.github.cottonmc.jsonfactory.frontend.I18n
import io.github.cottonmc.jsonfactory.frontend.MessageType
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import java.io.File

/**
 * Builder class, as an API frontend.
 */
class JsonFactoryBuilder : Frontend {
    private var folder: File = File(".")
    private val generators = ArrayList<ContentGenerator>()
    private val identifiers = ArrayList<Identifier>()
    private var addCallback: (ContentGenerator) -> ContentGenerator = { it }
    private val i18n = I18n()

    override fun printMessage(msg: String, type: MessageType, vararg messageParameters: Any?) {
        val translated = i18n.get(msg, *messageParameters)
        when (type) {
            MessageType.Error -> System.err.println("ERROR: $translated")
            MessageType.Important -> println("IMPORTANT: $translated")
            MessageType.Warn -> println("WARN: $translated")
            MessageType.Default -> println("DEFAULT: $translated")
        }
    }

    override fun printSeparator() {
        println()
    }

    override fun onFinishedGenerating() {
        // TODO: I18n
        printMessage("Generation finished.", MessageType.Default)
    }

    override suspend fun shouldOverwriteFile(file: File): Boolean {
        return false
    }

    override suspend fun selectOutputDirectory(): File? {
        return folder
    }

    /**
     * Adds a specific [generator] to this builder.
     */
    fun addGenerator(generator: ContentGenerator): JsonFactoryBuilder {
        generators.add(addCallback(generator))
        return this
    }

    /**
     * Adds a set of [generators] to this builder.
     */
    fun addGenerators(generators: Set<AbstractContentGenerator>): JsonFactoryBuilder {
        this.generators.addAll(generators.map(addCallback))
        return this
    }

    /**
     * Sets a function that is run on each generator when they get added
     *
     * Intended to be used to decorate generators, and modify their behaviour.
     */
    fun setGeneratorCallback(callback: (ContentGenerator) -> ContentGenerator) {
        addCallback = callback
    }

    /**
     * Adds a new identifier for generation.
     */
    fun addIdentifier(id: Identifier): JsonFactoryBuilder {
        identifiers.add(id)
        return this
    }

    /**
     * Sets the target folder, where we should generate into.
     */
    fun setTarget(folder: File): JsonFactoryBuilder {
        require(folder.isDirectory) {
            "$folder is not a directory!"
        }

        this.folder = folder
        return this
    }

    /**
     * Writes all of the required resources.
     */
    fun generate() {
        ContentWriter(this, generators).writeAll(identifiers.joinToString(separator = ","))
    }
}
