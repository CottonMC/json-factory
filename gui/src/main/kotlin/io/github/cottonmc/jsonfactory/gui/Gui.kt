package io.github.cottonmc.jsonfactory.gui

import io.github.cottonmc.jsonfactory.data.Identifiers
import io.github.cottonmc.jsonfactory.frontend.AutoFill
import io.github.cottonmc.jsonfactory.frontend.Frontend
import io.github.cottonmc.jsonfactory.frontend.ContentWriter
import io.github.cottonmc.jsonfactory.frontend.MessageType
import io.github.cottonmc.jsonfactory.frontend.i18n.invoke
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gui.components.*
import io.github.cottonmc.jsonfactory.gui.util.I18n
import io.github.cottonmc.jsonfactory.gui.util.Markdown
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.swing.Swing
import kotlinx.coroutines.withContext
import net.miginfocom.swing.MigLayout
import org.jdesktop.swingx.*
import org.jdesktop.swingx.error.ErrorInfo
import org.jdesktop.swingx.hyperlink.HyperlinkAction
import java.awt.*
import java.io.File
import java.net.URI
import java.nio.file.Path
import java.util.logging.Level
import javax.imageio.ImageIO
import javax.swing.*
import javax.swing.text.AttributeSet
import javax.swing.text.DefaultCaret
import javax.swing.text.SimpleAttributeSet
import javax.swing.text.StyleConstants

internal class Gui private constructor(gens: List<ContentGenerator>, autoFills: List<AutoFill>, defaultOutputFile: File) : Frontend {
    internal val frame = JFrame()
    internal val fileChooser = JFileChooser().apply {
        fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
        currentDirectory = defaultOutputFile
    }
    private val idField = JFTextField("gui.generation_panel.id.prompt").apply {
        columns = 25
    }
    /**
     * A map of all generators to a boolean.
     * If `true`, the generator is selected.
     */
    private val gens2Selections: MutableMap<ContentGenerator, Boolean> = gens.map { it to false }.toMap().toMutableMap()
    private val generators = createGeneratorPanel()
    private val saveButton = JFButton("gui.generation_panel.generate").apply {
        addActionListener {
            Identifiers.convertToIds(idField.text).fold(
                { printMessage(it, MessageType.Warn) },
                { ids ->
                    GlobalScope.launch {
                        ContentWriter(this@Gui, gens2Selections.filterValues { it }.keys).writeAll(ids)
                    }
                }
            )
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
                    add(JMenu(/* TODO */ group.name).apply {
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

        if (autoFills.isNotEmpty()) {
            add(JMenu("Auto-Fills").apply {
                for (autoFill in autoFills) {
                    add(JMenuItem(I18n[autoFill.i18nKey]).apply {
                        addActionListener {
                            idField.text = autoFill.value
                        }
                    })
                }
            })
        }
    }

    init {
        val rightPanel = JSplitPane(JSplitPane.VERTICAL_SPLIT, JPanel(MigLayout()).apply {
            add(JFLabel("gui.generation_panel.id"))
            add(idField, "span 2, wrap")
            add(saveButton, "skip, span, wrap")
            add(
                JFLabel("gui.generation_panel.note_save_location", "src/main/resources") { "<html><i>$it</i>" },
                "span, wrap"
            )
        }, JFScrollPane(JPanel(GridLayout()).apply {
            add(outputTextArea)
        }))

        val panel = JSplitPane(JSplitPane.HORIZONTAL_SPLIT, JFTitledPanel("Generators", generators), rightPanel)
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
                // TODO: i18n
                JXErrorPane.showDialog(
                    this,
                    ErrorInfo("Error when loading icons", "Couldn't load icons.", null, null, e, Level.WARNING, null)
                )
            }
        }
    }

    fun show() {
        SwingUtilities.invokeLater {
            frame.isVisible = true
            frame.size = Dimension(640, 440)
            printMessage("gui.message.welcome")
        }
    }

    private fun createGeneratorPanel(): JTabbedPane {
        val pane = JTabbedPane(SwingConstants.TOP)
        val gens = gens2Selections.keys

        for ((i, category) in gens.map { it.info.category }.distinct().withIndex()) {
            pane.addTab("", JFScrollPane(JPanel(MigLayout()).apply {
                val descKey = getDescriptionKey(category.id)
                I18n.getOptional(descKey)?.let {
                    add(JFLabel(descKey) { Markdown.toHtml("*$it*") }, "wrap")
                }

                val categoryGens = gens.filter { it.info.category == category }

                val subcategories = categoryGens.map { it.info.subcategory }.distinct().sortedBy {
                    it?.let { _ -> I18n[it.id] } ?: "A" // Weird hack to sort nulls first
                }

                for (subcategory in subcategories) {
                    if (subcategory != null) {
                        add(JFTitledSeparator(subcategory.id) { "<html><b>$it</b>" }, "wrap")
                        I18n.getOptional(getDescriptionKey(subcategory.id))?.let {
                            add(JFLabel(getDescriptionKey(subcategory.id)) { "<html><i>$it</i>" }, "wrap")
                        }
                    }

                    for (gen in categoryGens.filter { it.info.subcategory == subcategory }) {
                        add(JFCheckBox(gen.id, false).apply {
                            addActionListener {
                                gens2Selections[gen] = isSelected
                            }
                        }, "wrap")
                    }
                }
            }))

            pane.setTabComponentAt(i, JFLabel(category.id))
        }

        return pane
    }

    internal fun printMessage(
        prefix: String,
        msg: String,
        prefixAttributes: AttributeSet = defaultAttributes,
        mainAttributes: AttributeSet? = null
    ) {
        val doc = outputTextArea.styledDocument

        if (prefix.isNotEmpty()) {
            doc.insertString(doc.length, prefix, prefixAttributes)
            doc.insertString(doc.length, " ", null)
        }
        doc.insertString(doc.length, "$msg\n", mainAttributes)
        outputTextArea.repaint()
    }

    private fun showAboutDialog() = JXDialog(frame, JPanel(BorderLayout()).apply {
        name = I18n["gui.about.title"]

        add(JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            VersionProvider().version.forEach {
                add(JLabel("<html><b>$it</b>"))
            }
            add(JFLabel("gui.about.0"))
            add(JFLabel("gui.about.1"))
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

    override fun printMessage(msg: String, type: MessageType, vararg messageParameters: Any?) {
        val prefix = when (type) {
            MessageType.Warn -> I18n["gui.message.note"]
            else -> ""
        }

        val prefixAttributes = when (type) {
            MessageType.Warn -> noteAttributes
            else -> defaultAttributes
        }

        printMessage(
            prefix, I18n(msg, messageParameters), prefixAttributes, mainAttributes = when (type) {
                MessageType.Error -> errorAttributes
                MessageType.Important -> boldAttributes
                else -> null
            }
        )
    }

    override fun printSeparator() = printMessage("-".repeat(25))

    override fun onFinishedGenerating() {
        if (Settings.playFinishedSound) {
            Sounds.finished.start()
        }
    }

    override suspend fun shouldOverwriteFile(path: Path): Boolean = withContext(Dispatchers.Swing) {
        Sounds.confirm.start()
        val confirm = JOptionPane.showConfirmDialog(
            frame,
            I18n["gui.message.confirm_overwrite", path]
        )

        confirm == JOptionPane.YES_OPTION
    }

    override suspend fun selectOutputDirectory(): Path? = withContext(Dispatchers.Swing) {
        if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
            fileChooser.selectedFile.toPath()
        } else null
    }

    companion object {
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

        private val icon by readImage("icon")
        private val icon32 by readImage("icon32")
        private val icon128 by readImage("icon128")

        private fun readImage(name: String) = lazy {
            ImageIO.read(Gui::class.java.getResourceAsStream("/json-factory/$name.png"))
        }

        fun createAndShow(
            gens: List<ContentGenerator>,
            autoFills: List<AutoFill>,
            defaultOutputFile: File
        ) = SwingUtilities.invokeAndWait {
            Gui(gens, autoFills, defaultOutputFile).apply {
                show()
                if (Settings.showTipsOnStartup) {
                    Tips.show(frame, isStartup = true)
                }
            }
        }

        fun getDescriptionKey(l10nKey: String) = "$l10nKey.description"
    }
}
