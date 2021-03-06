package io.github.cottonmc.jsonfactory.frontend

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import kotlinx.coroutines.*
import java.nio.file.Files
import java.nio.file.Path
import java.util.logging.Level

/**
 * A coroutine-based generation and writing backend used for [Frontend]s.
 *
 * @param frontend the frontend
 * @param generators the used generators
 */
class ContentWriter(private val frontend: Frontend, private val generators: Iterable<ContentGenerator>) {
    /**
     * Generates and writes files with all selected generators.
     */
    suspend fun writeAll(ids: List<Identifier>) {
        frontend.selectOutputDirectory()?.let { outputDir ->
            check(Files.isDirectory(outputDir)) {
                "Output path must be a directory!"
            }

            frontend.printSeparator()
            frontend.log("message.writer.started_generating", Level.INFO, outputDir)

            ids.flatMap { id ->
                write(id, outputDir)
            }.joinAll()

            frontend.log("message.writer.finished_generating")
            frontend.onFinishedGenerating()
        }
    }

    private suspend fun write(id: Identifier, resourceDir: Path) = coroutineScope {
        generators.map { gen ->
            launch(Dispatchers.IO) {
                val root = gen.resourceRoot.path
                val sep = resourceDir.fileSystem.separator
                val namespace = id.namespace
                val directory = gen.path
                val fileName = id.path
                val extension = gen.extension
                val generated = gen.generate(id)

                for (value in generated) {
                    val name = value.nameWrapper.applyTo(fileName)

                    val path = resourceDir.resolve(
                        "$root$sep$namespace$sep$directory$sep$name.$extension"
                    )

                    if (Files.exists(path) && !frontend.shouldOverwriteFile(path)) {
                        return@launch
                    }

                    Files.createDirectories(path.parent)
                    value.writeToStream(Files.newOutputStream(path))

                    frontend.log("message.writer.generated", Level.INFO, resourceDir.relativize(path))
                }
            }
        }
    }
}
