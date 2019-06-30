package io.github.cottonmc.jsonfactory.gui

import io.github.cottonmc.jsonfactory.data.Identifiers
import io.github.cottonmc.jsonfactory.frontend.AutoFill
import io.github.cottonmc.jsonfactory.frontend.ContentWriter
import io.github.cottonmc.jsonfactory.frontend.Frontend
import io.github.cottonmc.jsonfactory.frontend.MessageType
import io.github.cottonmc.jsonfactory.frontend.i18n.invoke
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gui.api.theme.Theme
import io.github.cottonmc.jsonfactory.gui.components.FasterScrollPane
import io.github.cottonmc.jsonfactory.gui.components.TabbedPaneResizer
import io.github.cottonmc.jsonfactory.gui.components.dsl.*
import io.github.cottonmc.jsonfactory.gui.components.translatable.*
import io.github.cottonmc.jsonfactory.gui.util.I18n
import io.github.cottonmc.jsonfactory.gui.util.Markdown
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.swing.Swing
import kotlinx.coroutines.withContext
import net.miginfocom.swing.MigLayout
import org.jdesktop.swingx.JXDialog
import org.jdesktop.swingx.JXErrorPane
import org.jdesktop.swingx.JXHyperlink
import org.jdesktop.swingx.error.ErrorInfo
import org.jdesktop.swingx.hyperlink.HyperlinkAction
import org.pushingpixels.meteor.addDelayedComponentListener
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

internal class Gui private constructor(
    gens: List<ContentGenerator>,
    autoFills: List<AutoFill>,
    defaultOutputFile: File
) : Frontend {
    private val frame = JFrame()
    private val fileChooser = JFileChooser().apply {
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
    private val menuBar = menuBar {
        menu("gui.menu.settings") {
            checkBoxItem("gui.menu.settings.play_finished_sound") {
                isSelected = Settings.playFinishedSound
                addActionListener {
                    Settings.playFinishedSound = isSelected
                }
            }

            menu("gui.menu.settings.theme") {
                horizontalAlignment = SwingConstants.CENTER
                val buttonGroup = ButtonGroup()

                for ((group, themes) in Settings.themes.values.groupBy(Theme::group)) {
                    menu(group.translationKey) {
                        for (theme in themes.sortedBy { it.name }) {
                            radioButtonItem(theme.name) {
                                addActionListener {
                                    Settings.theme = theme
                                }

                                if (theme == Settings.theme) {
                                    isSelected = true
                                }

                                buttonGroup.add(this)
                            }
                        }
                    }
                }
            }
        }

        menu("gui.menu.help") {
            item("gui.menu.help.about") {
                addActionListener {
                    showAboutDialog()
                }
            }

            item("gui.menu.help.tip_of_the_day") {
                addActionListener {
                    Tips.show(frame, isStartup = false)
                }
            }
        }

        if (autoFills.isNotEmpty()) {
            menu("gui.menu.auto_fills") {
                for (autoFill in autoFills) {
                    item(I18n[autoFill.translationKey]) {
                        addActionListener {
                            idField.text = autoFill.value
                        }
                    }
                }
            }
        }
    }

    init {
        // This attaches the resizer to the tabbed pane.
        TabbedPaneResizer(generators)

        val panel = JPanel(MigLayout()).apply {
            add(JFTitledSeparator("gui.generation_panel.generate") { "<html><h1>$it</h1>" }, "skip, span, wrap")
            add(idField, "span 2")
            add(saveButton, "skip 2, wrap")
            add(
                JFLabel("gui.generation_panel.note_save_location", "src/main/resources") { "<html><i>$it</i>" },
                "span, wrap"
            )
            add(JFTitledSeparator("gui.generators") { "<html><h1>$it</h1>" }, "skip, span, wrap")
            add(generators, "sx, sy")

            addDelayedComponentListener(
                onComponentResized = {
                    generators.preferredSize = Dimension(width - 20, generators.preferredSize.height)
                }
            )
            insets.set(10, 10, 10, 10)
        }

        frame.apply {
            title = "JSON Factory"
            defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
            contentPane = FasterScrollPane(panel)
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
            pane.addTab("", JPanel(MigLayout()).apply {
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
                            add(
                                JFLabel(
                                    getDescriptionKey(
                                        subcategory.id
                                    )
                                ) { "<html><i>$it</i>" }, "wrap"
                            )
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
            })

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

        fun getDescriptionKey(translationKey: String) = "$translationKey.description"
    }
}
