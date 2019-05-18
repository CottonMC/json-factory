package io.github.cottonmc.jsonfactory.gui

import io.github.cottonmc.jsonfactory.frontend.Frontend
import io.github.cottonmc.jsonfactory.frontend.ContentWriter
import io.github.cottonmc.jsonfactory.frontend.I18n
import io.github.cottonmc.jsonfactory.frontend.MessageType
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gui.components.JFCheckBox
import io.github.cottonmc.jsonfactory.gui.components.JFLabel
import io.github.cottonmc.jsonfactory.gui.components.JFScrollPane
import io.github.cottonmc.jsonfactory.gui.components.JFTitledSeparator
import io.github.cottonmc.jsonfactory.gui.util.Markdown
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.swing.Swing
import kotlinx.coroutines.withContext
import net.miginfocom.swing.MigLayout
import org.jdesktop.swingx.*
import org.jdesktop.swingx.hyperlink.HyperlinkAction
import java.awt.*
import java.io.File
import java.net.URI
import javax.imageio.ImageIO
import javax.swing.*
import javax.swing.text.AttributeSet
import javax.swing.text.DefaultCaret
import javax.swing.text.SimpleAttributeSet
import javax.swing.text.StyleConstants

internal class Gui private constructor(gens: List<ContentGenerator>) : Frontend {
    internal val frame = JFrame()
    internal val fileChooser = JFileChooser().apply {
        fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
    }
    private val idField = JXTextField("enter an id or comma-separated list of ids").apply {
        columns = 25
    }
    private val contentWriter = ContentWriter(this, gens)
    private val generators = createGeneratorPanel()
    private val saveButton = JButton("Generate").apply {
        addActionListener {
            contentWriter.writeAll(idField.text)
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

            add(JMenu("Theme").apply {
                horizontalAlignment = SwingConstants.CENTER
                val buttonGroup = ButtonGroup()

                for ((group, themes) in Settings.Theme.values().groupBy(Settings.Theme::group)) {
                    add(JMenu(group.name).apply {
                        for (theme in themes.sortedBy { it.name }) {
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
                    Tips.show(frame, isStartup = false)
                }
            })
        })
    }

    init {
        val rightPanel = JSplitPane(JSplitPane.VERTICAL_SPLIT, JPanel(MigLayout()).apply {
            add(JLabel("ID"))
            add(idField, "span 2, wrap")
            add(saveButton, "skip, span, wrap")
            add(JLabel("<html><i>Note: save in src/main/resources or pack root </i>"), "span, wrap")
        }, JFScrollPane(JPanel(GridLayout()).apply {
            add(outputTextArea)
        }))

        val panel = JSplitPane(JSplitPane.HORIZONTAL_SPLIT, JXTitledPanel("Generators", generators), rightPanel)
        panel.dividerLocation = 320

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

    private fun createGeneratorPanel(): JTabbedPane {
        val pane = JTabbedPane(SwingConstants.TOP)
        val gens = contentWriter.gens2Selections.keys

        for ((i, category) in gens.map { it.info.category }.distinct().withIndex()) {
            pane.addTab("", JFScrollPane(JPanel(MigLayout()).apply {
                val descKey = getDescriptionKey(category.id)
                I18N.getOptional(descKey)?.let {
                    add(JFLabel(descKey) { Markdown.toHtml("*$it*") }, "wrap")
                }

                val categoryGens = gens.filter { it.info.category == category }

                val subcategories = categoryGens.map { it.info.subcategory }.distinct().sortedBy {
                    it?.let { _ -> I18N[it.id] } ?: "A" // Weird hack to sort nulls first
                }

                for (subcategory in subcategories) {
                    if (subcategory != null) {
                        add(JFTitledSeparator(subcategory.id) { "<html><b>$it</b>" }, "wrap")
                        I18N.getOptional(getDescriptionKey(subcategory.id))?.let {
                            add(JFLabel(getDescriptionKey(subcategory.id)) { "<html><i>$it</i>" }, "wrap")
                        }
                    }

                    for (gen in categoryGens.filter { it.info.subcategory == subcategory }) {
                        add(JFCheckBox(gen.id, false).apply {
                            addActionListener {
                                contentWriter.gens2Selections[gen] = isSelected
                            }
                        }, "wrap")
                    }
                }
            }))

            pane.setTabComponentAt(i, JFLabel(category.id))
        }

        return pane
    }

    internal fun printMessage(prefix: String, msg: String, prefixAttributes: AttributeSet = defaultAttributes, mainAttributes: AttributeSet? = null) {
        val doc = outputTextArea.styledDocument

        if (prefix.isNotEmpty()) {
            doc.insertString(doc.length, prefix, prefixAttributes)
            doc.insertString(doc.length, " ", null)
        }
        doc.insertString(doc.length, "$msg\n", mainAttributes)
        outputTextArea.repaint()
    }

    private fun showAboutDialog() = JXDialog(frame, JPanel(BorderLayout()).apply {
        name = "About JSON Factory"

        add(JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            add(JLabel("<html><b>About JSON Factory</b>"))
            add(JLabel("JSON Factory is developed by the Cotton project."))
            add(JLabel("It is licensed under the MIT license."))
            add(
                JXHyperlink(
                    HyperlinkAction.createHyperlinkAction(
                        URI.create("https://github.com/CottonMC/json-factory"),
                        Desktop.Action.BROWSE
                    )
                )
            )
        }, BorderLayout.CENTER)
        add(JLabel(ImageIcon(icon128)), BorderLayout.WEST)
    }).apply {
        pack()
        isVisible = true
        val screenSize = Toolkit.getDefaultToolkit().screenSize
        setLocation(screenSize.width / 2 - width / 2, screenSize.height / 2 - height / 2)
    }

    override fun printMessage(msg: String, type: MessageType) {
        val prefix = when (type) {
            MessageType.Warn -> "Note:"
            else -> ""
        }

        val prefixAttributes = when (type) {
            MessageType.Warn -> noteAttributes
            else -> defaultAttributes
        }

        printMessage(prefix, msg, prefixAttributes, mainAttributes = when (type) {
            MessageType.Error -> errorAttributes
            MessageType.Important -> boldAttributes
            else -> null
        })
    }

    override fun printSeparator() = printMessage("-".repeat(25))

    override fun onFinishedGenerating() {
        if (Settings.playFinishedSound) {
            Sounds.finished.start()
        }
    }

    override suspend fun shouldOverwriteFile(file: File): Boolean = withContext(Dispatchers.Swing) {
        Sounds.confirm.start()
        val confirm = JOptionPane.showConfirmDialog(
            frame,
            "Do you want to overwrite the existing file $file?"
        )

        confirm == JOptionPane.YES_OPTION
    }

    override suspend fun selectOutputDirectory(): File? = withContext(Dispatchers.Swing) {
        if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
            fileChooser.selectedFile
        } else null
    }

    companion object {
        val I18N = I18n()

        internal val defaultAttributes = SimpleAttributeSet().apply {
            StyleConstants.setForeground(this, Color(0x2E9DFF))
        }

        internal val boldAttributes = SimpleAttributeSet().apply {
            StyleConstants.setForeground(this, Color(0x2E9DFF))
            StyleConstants.setBold(this, true)
        }

        internal val errorAttributes = SimpleAttributeSet().apply {
            StyleConstants.setForeground(this, Color.RED)
            StyleConstants.setBold(this, true)
        }

        internal val noteAttributes = SimpleAttributeSet().apply {
            StyleConstants.setBackground(this, Color.ORANGE)
            StyleConstants.setForeground(this, Color.BLACK)
            StyleConstants.setBold(this, true)
        }

        private val icon = readImage("icon")
        private val icon32 = readImage("icon32")
        private val icon128 = readImage("icon128")

        private fun readImage(name: String) =
            ImageIO.read(Gui::class.java.getResourceAsStream("/json-factory/$name.png"))

        fun createAndShow(gens: List<ContentGenerator>) = SwingUtilities.invokeAndWait {
            Gui(gens).apply {
                show()
                if (Settings.showTipsOnStartup) {
                    Tips.show(frame, isStartup = true)
                }
            }
        }

        fun getDescriptionKey(l10nKey: String) = "$l10nKey.description"
    }
}
