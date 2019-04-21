package io.github.cottonmc.jsonfactory.frontend

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.Gens
import kotlinx.coroutines.*
import java.nio.file.Files
import java.nio.file.Path

class Generator(private val frontend: Frontend) {
    /**
     * A map of all generators to a boolean.
     * If `true`, the generator is selected.
     */
    val gens2Selections = Gens.allGens.map { it to false }.toMap().toMutableMap()

    /**
     * Generates files with all selected generators.
     */
    fun generateAll(idText: String) = GlobalScope.launch {
        if (idText.isBlank()) {
            frontend.printMessage("The ID input field is empty.", MessageType.Note)
            return@launch

        } else if (gens2Selections.none { (_, value) -> value }) {
            frontend.printMessage("No generators selected.", MessageType.Note)
            return@launch
        }

        frontend.selectOutputDirectory()?.let { selectedFile ->
            frontend.printSeparator()
            frontend.printMessage("Started generating.", MessageType.Important)
            frontend.printMessage("In $selectedFile")

            val split = idText.split(',').map(String::trim)
            split.mapNotNull { idText ->
                Identifier.orNull(idText).also { id ->
                    if (id == null) {
                        frontend.printMessage("Invalid ID: $idText", MessageType.Error)
                    }
                }
            }.flatMap { id ->
                generate(id, selectedFile)
            }.joinAll()

            frontend.printMessage("Finished generating.", MessageType.Important)
            frontend.onFinishedGenerating()
        }
    }

    private suspend fun generate(id: Identifier, resourceDir: Path) = coroutineScope {
        gens2Selections.filter { (_, value) -> value }.keys.map { gen ->
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

                    frontend.printMessage("Generated " + path.relativize(resourceDir).toString())
                }
            }
        }
    }
}
