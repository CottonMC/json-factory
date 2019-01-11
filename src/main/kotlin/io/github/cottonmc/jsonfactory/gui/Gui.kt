package io.github.cottonmc.jsonfactory.gui

import io.github.cottonmc.jsonfactory.data.ContentGenerator
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.gens.Gens
import net.miginfocom.swing.MigLayout
import java.awt.GridLayout
import java.io.File
import java.nio.file.Files
import javax.swing.*

class Gui private constructor() {
    private val frame = JFrame()
    private val fileChooser = JFileChooser().apply {
        fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
    }
    private val idField = JTextField("minecraft:prismarine_bricks")
    private val selectedGens = Gens.allGens.map { it to false }.toMap().toMutableMap()
    private val generators = createGeneratorPanel()
    private val saveButton = JButton("Save").apply {
        addActionListener {
            save()
        }
    }

    init {
        val panel = JPanel(GridLayout(1, 0)).apply {
            add(generators)
            add(JPanel(MigLayout()).apply {
                add(JLabel("ID"))
                add(idField, "span 2, wrap")
                add(saveButton, "skip, span, wrap")
                add(JLabel("<html><i>Note: save in src/main/resources or pack root </i>"), "span")
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
            frame.pack()
        }
    }

    private fun save() {
        val id = Identifier.orNull(idField.text) ?: run {
            JOptionPane.showMessageDialog(frame, "The ID is invalid.", "Invalid ID", JOptionPane.ERROR_MESSAGE)
            return
        }

        val answer = fileChooser.showSaveDialog(frame)

        if (answer == JFileChooser.APPROVE_OPTION) {
            for (gen in selectedGens.filter { (_, value) -> value }.keys) {
                val sep = File.separatorChar

                val file = File(
                    fileChooser.selectedFile,
                    "${gen.resourceRoot.path}$sep${id.namespace}$sep${gen.path}$sep${id.path}.${gen.extension}"
                )

                if (file.exists()) {
                    val confirm = JOptionPane.showConfirmDialog(frame, "Do you want to overwrite the existing file $file?")

                    if (confirm != JOptionPane.YES_OPTION)
                        return
                }

                Files.createDirectories(file.parentFile?.toPath())
                gen.generate(id).writeToFile(file)
            }
        }
    }

    private fun createGeneratorPanel(): JTabbedPane {
        val pane = JTabbedPane(SwingConstants.TOP)
        val gens = selectedGens.keys

        for (category in ContentGenerator.Category.values()) {
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
