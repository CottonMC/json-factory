package io.github.cottonmc.jsonfactory.gui

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.Gens
import io.github.cottonmc.jsonfactory.gui.Gui.Companion.boldAttributes
import io.github.cottonmc.jsonfactory.gui.Gui.Companion.errorAttributes
import io.github.cottonmc.jsonfactory.gui.Gui.Companion.noteAttributes
import kotlinx.coroutines.*
import kotlinx.coroutines.swing.Swing
import java.io.File
import java.nio.file.Files
import javax.swing.JFileChooser
import javax.swing.JOptionPane

internal class Generator(private val gui: Gui) {
    /**
     * A map of all generators to a boolean.
     * If `true`, the generator is selected.
     */
    val gens2Selections = Gens.allGens.map { it to false }.toMap().toMutableMap()

    /**
     * Generates files with all selected generators.
     */
    fun generateAll(idText: String) = GlobalScope.launch(Dispatchers.Swing) {
        if (idText.isBlank()) {
            gui.printMessage("Note:", "The ID input field is empty.", noteAttributes)
            return@launch

        } else if (gens2Selections.none { (_, value) -> value }) {
            gui.printMessage("Note:", "No generators selected.", noteAttributes)
            return@launch
        }

        val answer = gui.fileChooser.showSaveDialog(gui.frame)

        if (answer == JFileChooser.APPROVE_OPTION) {
            gui.printMessage("", "-".repeat(25))
            gui.printMessage("Started", "generating.", boldAttributes)
            gui.printMessage("In", gui.fileChooser.selectedFile.path)

            val split = idText.split(',').map(String::trim)
            split.mapNotNull { idText ->
                Identifier.orNull(idText).also { id ->
                    if (id == null) {
                        gui.printMessage("Invalid ID:", idText, errorAttributes)
                    }
                }
            }.flatMap { id ->
                generate(id, gui.fileChooser.selectedFile)
            }.joinAll()

            gui.printMessage("Finished", "generating.", boldAttributes)
            if (Settings.playFinishedSound) {
                Sounds.finished.start()
            }
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

                    val file = File(
                        resourceDir,
                        "$root$sep$namespace$sep$directory$sep$name.$extension"
                    )

                    if (file.exists()) {
                        Sounds.confirm.start()
                        val confirm = withContext(Dispatchers.Swing) {
                            JOptionPane.showConfirmDialog(gui.frame, "Do you want to overwrite the existing file $file?")
                        }

                        if (confirm != JOptionPane.YES_OPTION)
                            return@launch
                    }

                    Files.createDirectories(file.parentFile?.toPath())
                    value.writeToFile(file)

                    withContext(Dispatchers.Swing) {
                        gui.printMessage("Generated", file.toRelativeString(resourceDir))
                    }
                }
            }
        }
    }
}
