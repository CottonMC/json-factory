package io.github.cottonmc.jsonfactory.gui

import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import net.miginfocom.swing.MigLayout
import org.jdesktop.swingx.*
import org.jdesktop.swingx.hyperlink.HyperlinkAction
import java.awt.*
import java.net.URI
import javax.imageio.ImageIO
import javax.swing.*
import javax.swing.text.AttributeSet
import javax.swing.text.DefaultCaret
import javax.swing.text.SimpleAttributeSet
import javax.swing.text.StyleConstants

internal class Gui private constructor() {
    internal val frame = JFrame()
    internal val fileChooser = JFileChooser().apply {
        fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
    }
    private val idField = JXTextField("enter an id or comma-separated list of ids").apply {
        columns = 25
    }
    private val generator = Generator(this)
    private val generators = createGeneratorPanel()
    private val saveButton = JButton("Generate").apply {
        addActionListener {
            generator.generateAll(idField.text)
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
        val gens = generator.gens2Selections.keys

        for (category in GeneratorInfo.Categories.categories) {
            pane.addTab(category.displayName, JFScrollPane(JPanel(MigLayout()).apply {
                category.description?.let {
                    add(JLabel(Markdown.toHtml(it)), "wrap")
                }

                val categoryGens = gens.filter { it.info.category == category }

                val subcategories = categoryGens.map { it.info.subcategory }.distinct().sortedBy {
                    it?.displayName ?: "A" // Weird hack to sort nulls first
                }

                for (subcategory in subcategories) {
                    if (subcategory != null) {
                        add(JXTitledSeparator("<html><b>${subcategory.displayName}</b>"), "wrap")
                        subcategory.description?.let {
                            add(JLabel(Markdown.toHtml("*$it*")), "wrap")
                        }
                    }

                    for (gen in categoryGens.filter { it.info.subcategory == subcategory }) {
                        add(JCheckBox(gen.displayName, false).apply {
                            addActionListener {
                                generator.gens2Selections[gen] = isSelected
                            }
                        }, "wrap")
                    }
                }
            }))
        }

        return pane
    }

    internal fun printMessage(prefix: String, msg: String, prefixAttributes: AttributeSet = defaultAttributes) {
        val doc = outputTextArea.styledDocument

        if (prefix.isNotEmpty()) {
            doc.insertString(doc.length, prefix, prefixAttributes)
            doc.insertString(doc.length, " ", null)
        }
        doc.insertString(doc.length, "$msg\n", null)
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

        private val icon = readImage("icon")
        private val icon32 = readImage("icon32")
        private val icon128 = readImage("icon128")

        private fun readImage(name: String) =
            ImageIO.read(Gui::class.java.getResourceAsStream("/json-factory/$name.png"))

        fun show() = SwingUtilities.invokeAndWait {
            Gui().apply {
                show()
                if (Settings.showTipsOnStartup) {
                    Tips.show(frame, isStartup = true)
                }
            }
        }
    }
}
