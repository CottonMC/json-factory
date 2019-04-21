package io.github.cottonmc.jsonfactory.frontend

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.Gens
import kotlinx.coroutines.*
import java.io.File
import java.nio.file.Files

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
            frontend.printMessage("The ID input field is empty.", MessageType.Warn)
            return@launch

        } else if (gens2Selections.none { (_, value) -> value }) {
            frontend.printMessage("No generators selected.", MessageType.Warn)
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

    private suspend fun generate(id: Identifier, resourceDir: File) = coroutineScope {
        gens2Selections.filter { (_, value) -> value }.keys.map { gen ->
            launch(Dispatchers.IO) {
                val root = gen.resourceRoot.path
                val sep = File.separatorChar
                val namespace = id.namespace
                val directory = gen.path
                val fileName = id.path
                val extension = gen.extension
                val generated = gen.generate(id)

                for (value in generated) {
                    val name = value.nameWrapper.applyTo(fileName)

                    val file = resourceDir.resolve(
                        "$root$sep$namespace$sep$directory$sep$name.$extension"
                    )

                    if (file.exists() && !frontend.shouldOverwriteFile(file)) {
                        return@launch
                    }

                    Files.createDirectories(file.parentFile.toPath())
                    value.writeToFile(file)

                    frontend.printMessage("Generated " + file.toRelativeString(resourceDir))
                }
            }
        }
    }
}
