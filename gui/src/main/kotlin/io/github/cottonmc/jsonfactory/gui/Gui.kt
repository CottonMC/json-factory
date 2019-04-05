package io.github.cottonmc.jsonfactory.gui

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.gens.Gens
import net.miginfocom.swing.MigLayout
import org.jdesktop.swingx.*
import org.jdesktop.swingx.hyperlink.HyperlinkAction
import org.jdesktop.swingx.tips.TipLoader
import java.awt.*
import java.io.File
import java.net.URI
import java.nio.file.Files
import java.util.*
import javax.imageio.ImageIO
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
    private val idField = JXTextField("enter an id or comma-separated list of ids").apply {
        columns = 25
    }
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

            add(JCheckBoxMenuItem("Show Tips on Startup").apply {
                isSelected = Settings.showTipsOnStartup
                addActionListener {
                    Settings.showTipsOnStartup = isSelected
                }
            })

            add(JMenu("Theme").apply {
                horizontalAlignment = SwingConstants.CENTER
                val buttonGroup = ButtonGroup()

                for ((group, themes) in Settings.Theme.values().groupBy(Settings.Theme::group)) {
                    add(JMenu(group.name).apply {
                        for (theme in themes) {
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

                add(JCheckBoxMenuItem("Force System Window Decorations").apply {
                    isSelected = Settings.forceSystemWindowDecorations
                    addActionListener {
                        Settings.forceSystemWindowDecorations = isSelected
                    }
                })
            })
        })

        add(JMenu("Help").apply {
            add(JMenuItem("About").apply {
                addActionListener {
                    showAboutDialog()
                }
            })

            add(JMenuItem("Tip of the Day").apply {
                addActionListener {
                    showTips()
                }
            })
        })
    }
    private val tipOfTheDay: JXTipOfTheDay

    init {
        val rightPanel = JSplitPane(JSplitPane.VERTICAL_SPLIT, JPanel(MigLayout()).apply {
            add(JLabel("ID"))
            add(idField, "span 2, wrap")
            add(saveButton, "skip, span, wrap")
            add(JLabel("<html><i>Note: save in src/main/resources or pack root </i>"), "span, wrap")
        }, JScrollPane(JPanel(GridLayout()).apply {
            add(outputTextArea)
        }))

        val panel = JSplitPane(JSplitPane.HORIZONTAL_SPLIT, JXTitledPanel("Generators", generators), rightPanel)
        panel.dividerLocation = 320

        // Load tipOfTheDay
        val props = Properties()
        props.load(Gui::class.java.getResourceAsStream("/json-factory/tips.properties"))
        tipOfTheDay = JXTipOfTheDay(TipLoader.load(props))

        frame.apply {
            title = "JSON Factory"
            defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
            contentPane = panel
            jMenuBar = this@Gui.menuBar
            try {
                iconImages = listOf(icon, icon32, icon128)
            } catch (e: Exception) {
                System.err.println("Exception while loading icon")
                e.printStackTrace()
            }
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

        for (category in GeneratorInfo.Categories.categories) {
            pane.addTab(category.displayName, JScrollPane(JPanel(MigLayout()).apply {
                category.description?.let {
                    add(JLabel(it), "wrap")
                }

                val categoryGens = gens.filter { it.info.category == category }

                val subcategories = categoryGens.map { it.info.subcategory }.distinct().sortedBy {
                    it?.displayName ?: "A"
                }

                for (subcategory in subcategories) {
                    if (subcategory != null) {
                        add(JXTitledSeparator("<html><b>${subcategory.displayName}</b>"), "wrap")
                        subcategory.description?.let {
                            add(JLabel("<html><i>$it</i>"), "wrap")
                        }
                    }

                    for (gen in categoryGens.filter { it.info.subcategory == subcategory }) {
                        add(JCheckBox(gen.displayName, false).apply {
                            addActionListener {
                                selectedGens[gen] = isSelected
                            }
                        }, "wrap")
                    }
                }
            }).apply {
                verticalScrollBar.unitIncrement = 16
            })
        }

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

    private fun showTips() {
        tipOfTheDay.currentTip = (0 until tipOfTheDay.model.tipCount).random()
        tipOfTheDay.showDialog(frame)
    }

    private fun showAboutDialog() = JXDialog(frame, JPanel(BorderLayout()).apply {
        name = "About JSON Factory"

        add(JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            add(JLabel("<html><b>About JSON Factory</b>"))
            add(JLabel("JSON Factory is developed by the Cotton project."))
            add(JLabel("It is licensed under the MIT license."))
            add(JXHyperlink(HyperlinkAction.createHyperlinkAction(
                URI.create("https://github.com/CottonMC/json-factory"),
                Desktop.Action.BROWSE
            )))
        }, BorderLayout.CENTER)
        add(JLabel(ImageIcon(icon128)), BorderLayout.WEST)
    }).apply {
        pack()
        isVisible = true
        val screenSize = Toolkit.getDefaultToolkit().screenSize
        setLocation(screenSize.width / 2 - width / 2, screenSize.height / 2 - height / 2)
    }

    companion object {
        private val defaultAttributes = SimpleAttributeSet().apply {
            StyleConstants.setForeground(this, Color(0x2E9DFF))
        }

        private val boldAttributes = SimpleAttributeSet().apply {
            StyleConstants.setForeground(this, Color(0x2E9DFF))
            StyleConstants.setBold(this, true)
        }

        private val errorAttributes = SimpleAttributeSet().apply {
            StyleConstants.setForeground(this, Color.RED)
            StyleConstants.setBold(this, true)
        }

        private val noteAttributes = SimpleAttributeSet().apply {
            StyleConstants.setBackground(this, Color.ORANGE)
            StyleConstants.setForeground(this, Color.BLACK)
            StyleConstants.setBold(this, true)
        }

        private val icon = readImage("icon")
        private val icon32 = readImage("icon32")
        private val icon128 = readImage("icon128")

        private fun readImage(name: String) =
            ImageIO.read(Gui::class.java.getResourceAsStream("/json-factory/$name.png"))

        fun show() = SwingUtilities.invokeAndWait {
            Gui().apply {
                show()
                if (Settings.showTipsOnStartup) {
                    showTips()
                }
            }
        }
    }
}
