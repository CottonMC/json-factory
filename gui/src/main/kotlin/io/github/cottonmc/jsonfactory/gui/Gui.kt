package io.github.cottonmc.jsonfactory.gui

import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.Gens
import net.miginfocom.swing.MigLayout
import java.awt.*
import java.io.File
import java.nio.file.Files
import javax.swing.*
import javax.swing.text.AttributeSet
import javax.swing.text.DefaultCaret
import javax.swing.text.SimpleAttributeSet
import javax.swing.text.StyleConstants

internal class Gui private constructor() {
    private val frame = JFrame()
    private val fileChooser = JFileChooser().apply {
        fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
    }
    private val idField = JTextField("enter an id or comma-separated list of ids", 25)
    private val selectedGens = Gens.allGens.map { it to false }.toMap().toMutableMap()
    private val generators = createGeneratorPanel()
    private val saveButton = JButton("Generate").apply {
        addActionListener {
            saveAll()
        }
    }
    private val outputTextArea = JTextPane().apply {
        font = Font.getFont(Font.MONOSPACED)
        (caret as? DefaultCaret)?.updatePolicy = DefaultCaret.ALWAYS_UPDATE
        isEditable = false
    }
    private val menuBar = JMenuBar().apply {
        add(JMenu("Settings").apply {
            add(JCheckBoxMenuItem("Play Finished Sound").apply {
                isSelected = Settings.playFinishedSound
                addActionListener {
                    Settings.playFinishedSound = isSelected
                }
            })

            addSeparator()
            add(JLabel("<html><b>Theme</b>"))
            val buttonGroup = ButtonGroup()

            for (theme in Settings.Theme.values()) {
                add(JRadioButtonMenuItem(theme.name).apply {
                    addActionListener {
                        Settings.theme = theme
                    }

                    if (theme == Settings.theme) {
                        isSelected = true
                    }

                    buttonGroup.add(this)
                })
            }
        })
    }

    init {
        val rightPanel = JSplitPane(JSplitPane.VERTICAL_SPLIT, JPanel(MigLayout()).apply {
            add(JLabel("ID"))
            add(idField, "span 2, wrap")
            add(saveButton, "skip, span, wrap")
            add(JLabel("<html><i>Note: save in src/main/resources or pack root </i>"), "span, wrap")
        }, JScrollPane(JPanel(GridLayout()).apply {
            add(outputTextArea)
        }))

        val panel = JSplitPane(JSplitPane.HORIZONTAL_SPLIT, generators, rightPanel)
        panel.dividerLocation = 320

        frame.apply {
            title = "JSON Factory"
            defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
            contentPane = panel
            jMenuBar = this@Gui.menuBar
        }
    }

    fun show() {
        SwingUtilities.invokeLater {
            frame.isVisible = true
            frame.size = Dimension(640, 440)
            printMessage("Welcome", "to JSON Factory!")
        }
    }

    private fun saveAll() {
        if (selectedGens.none { (_, value) -> value }) {
            printMessage("Note:", "No generators selected.", noteAttributes)
            return
        }

        val answer = fileChooser.showSaveDialog(frame)

        if (answer == JFileChooser.APPROVE_OPTION) {
            printMessage("", "-".repeat(25))
            printMessage("Started", "generating.", boldAttributes)
            printMessage("In", fileChooser.selectedFile.path)

            val split = idField.text.split(',')
            for (idText in split) {
                val id = Identifier.orNull(idText)

                if (id == null) {
                    printMessage("Invalid ID:", idText, errorAttributes)
                    continue
                }

                save(id, fileChooser.selectedFile)
            }

            printMessage("Finished", "generating.", boldAttributes)
            if (Settings.playFinishedSound) {
                Sounds.finished.start()
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
                    Sounds.confirm.start()
                    val confirm =
                        JOptionPane.showConfirmDialog(frame, "Do you want to overwrite the existing file $file?")

                    if (confirm != JOptionPane.YES_OPTION)
                        return
                }

                Files.createDirectories(file.parentFile?.toPath())
                value.writeToFile(file)

                printMessage("Generated", file.toRelativeString(resourceDir))
            }
        }
    }

    private fun createGeneratorPanel(): JTabbedPane {
        val pane = JTabbedPane(SwingConstants.TOP)
        val gens = selectedGens.keys

        for (category in ContentGenerator.Categories.categories) {
            pane.addTab(category.displayName, JScrollPane(JPanel(MigLayout()).apply {
                category.description?.let {
                    add(JLabel(it), "wrap")
                }

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

    private fun printMessage(prefix: String, msg: String, prefixAttributes: AttributeSet = defaultAttributes) {
        val doc = outputTextArea.styledDocument

        if (prefix.isNotEmpty()) {
            doc.insertString(doc.length, prefix, prefixAttributes)
            doc.insertString(doc.length, " ", null)
        }
        doc.insertString(doc.length, "$msg\n", null)
        outputTextArea.repaint()
    }

    companion object {
        private val defaultAttributes = SimpleAttributeSet().apply {
            StyleConstants.setForeground(this, Color(0x1680f9))
        }

        private val boldAttributes = SimpleAttributeSet().apply {
            StyleConstants.setForeground(this, Color(0x1680f9))
            StyleConstants.setBold(this, true)
        }

        private val errorAttributes = SimpleAttributeSet().apply {
            StyleConstants.setForeground(this, Color.RED)
            StyleConstants.setBold(this, true)
        }

        private val noteAttributes = SimpleAttributeSet().apply {
            StyleConstants.setBackground(this, Color.ORANGE)
            StyleConstants.setBold(this, true)
        }

        fun show() {
            Gui().show()
        }
    }
}
