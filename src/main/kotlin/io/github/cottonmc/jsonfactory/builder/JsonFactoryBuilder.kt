package io.github.cottonmc.jsonfactory.builder

import com.google.common.flogger.FluentLogger
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.frontend.Frontend
import io.github.cottonmc.jsonfactory.frontend.ContentWriter
import io.github.cottonmc.jsonfactory.frontend.i18n.invoke
import io.github.cottonmc.jsonfactory.frontend.i18n.ResourceBundleI18n
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import kotlinx.coroutines.runBlocking
import java.io.File
import java.nio.file.Path
import java.util.logging.Level

/**
 * Builder class, as an API frontend.
 *
 * Note: using this class requires a Flogger (https://github.com/google/flogger) backend.
 */
class JsonFactoryBuilder : Frontend {
    private var folder: File = File(".")
    private val generators = ArrayList<ContentGenerator>()
    private val identifiers = ArrayList<Identifier>()
    private var addCallback: (ContentGenerator) -> ContentGenerator = { it }
    private val i18n = ResourceBundleI18n.createBackendI18n()

    override fun log(msg: String, level: Level, vararg messageParameters: Any?) =
        logger.at(level).log(i18n(msg, messageParameters))

    override fun printSeparator() {
        println()
    }

    override fun onFinishedGenerating() {}

    override suspend fun shouldOverwriteFile(path: Path): Boolean {
        return false
    }

    override suspend fun selectOutputDirectory(): Path? {
        return folder.toPath()
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
    fun generate() = runBlocking {
        ContentWriter(this@JsonFactoryBuilder, generators).writeAll(identifiers)
    }

    companion object {
        private val logger by lazy { FluentLogger.forEnclosingClass() }
    }
}
