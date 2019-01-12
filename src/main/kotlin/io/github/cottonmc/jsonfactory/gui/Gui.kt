package io.github.cottonmc.jsonfactory.gui

import io.github.cottonmc.jsonfactory.data.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.data.types.Identifier
import io.github.cottonmc.jsonfactory.data.gens.Gens
import net.miginfocom.swing.MigLayout
import java.awt.Dimension
import java.awt.GridLayout
import java.io.File
import java.nio.file.Files
import javax.swing.*

internal class Gui private constructor() {
    private val frame = JFrame()
    private val fileChooser = JFileChooser().apply {
        fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
    }
    private val idField = JTextField("minecraft:prismarine_bricks")
    private val selectedGens = Gens.allGens.map { it to false }.toMap().toMutableMap()
    private val generators = createGeneratorPanel()
    private val saveButton = JButton("Generate").apply {
        addActionListener {
            saveAll()
        }
    }

    init {
        val panel = JPanel(GridLayout(1, 0)).apply {
            add(generators)
            add(JPanel(MigLayout()).apply {
                add(JLabel("ID"))
                add(idField, "span 2, wrap")
                add(saveButton, "skip, span, wrap")
                add(JLabel("<html><i>Note: save in src/main/resources or pack root </i>"), "span, wrap")
            })
        }

        frame.apply {
            title = "JSON Factory"
            defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
            contentPane = panel
        }
    }

    fun show() {
        SwingUtilities.invokeLater {
            frame.isVisible = true
            frame.size = Dimension(640, 440)
        }
    }

    private fun saveAll() {
        val answer = fileChooser.showSaveDialog(frame)

        if (answer == JFileChooser.APPROVE_OPTION) {
            val split = idField.text.split(',')
            for (idText in split) {
                val id = Identifier.orNull(idText)

                if (id == null) {
                    JOptionPane.showMessageDialog(
                        frame,
                        "ID $idText is invalid.",
                        "Invalid ID",
                        JOptionPane.ERROR_MESSAGE
                    )
                    continue
                }

                save(id, fileChooser.selectedFile)
            }
        }
    }

    private fun save(id: Identifier, resourceDir: File) {
        for (gen in selectedGens.filter { (_, value) -> value }.keys) {
            val root = gen.resourceRoot.path
            val sep = File.separatorChar
            val namespace = id.namespace
            val directory = gen.path
            val fileName = id.path
            val extension = gen.extension
            val generated = gen.generate(id)

            for (value in generated) {
                val s = if (value.suffix.isEmpty()) "" else "_${value.suffix}"

                val file = File(
                    resourceDir,
                    "$root$sep$namespace$sep$directory$sep$fileName$s.$extension"
                )

                if (file.exists()) {
                    val confirm =
                        JOptionPane.showConfirmDialog(frame, "Do you want to overwrite the existing file $file?")

                    if (confirm != JOptionPane.YES_OPTION)
                        return
                }

                Files.createDirectories(file.parentFile?.toPath())
                value.writeToFile(file)
            }
        }
    }

    private fun createGeneratorPanel(): JTabbedPane {
        val pane = JTabbedPane(SwingConstants.TOP)
        val gens = selectedGens.keys

        for (category in ContentGenerator.Categories.categories) {
            pane.addTab(category.displayName, JScrollPane(JPanel(MigLayout()).apply {
                for (gen in gens.filter { it.category == category }) {
                    add(JCheckBox(gen.displayName, false).apply {
                        addActionListener {
                            selectedGens[gen] = isSelected
                        }
                    }, "wrap")
                }
            }))
        }

        pane.border = BorderFactory.createTitledBorder("Generators")
        return pane
    }

    companion object {
        fun show() {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
            Gui().show()
        }
    }
}
