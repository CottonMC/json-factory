package io.github.cottonmc.jsonfactory.gui

import io.github.cottonmc.jsonfactory.data.ContentGenerator
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.gens.Gens
import io.github.cottonmc.jsonfactory.data.Serializer
import net.miginfocom.swing.MigLayout
import java.awt.Dimension
import java.io.File
import javax.swing.*
import javax.swing.filechooser.FileFilter

class Gui private constructor() {
    private val frame = JFrame()
    private val fileChooser = JFileChooser().apply {
        fileFilter = object : FileFilter() {
            override fun accept(f: File) = f.isDirectory || f.extension.equals("json", ignoreCase = true)
            override fun getDescription() = "JSON files (.json)"
        }
        fileSelectionMode = JFileChooser.FILES_ONLY
    }
    private val idField = JTextField("minecraft:prismarine_bricks")
    private val comboBox = JComboBox<ContentGenerator<*>>(Gens.allGens)
    private val saveButton = JButton("Save").apply {
        addActionListener {
            save()
        }
    }

    init {
        val panel = JPanel(MigLayout()).apply {
            add(JLabel("ID"))
            add(idField, "span 2, wrap")
            add(comboBox, "span 2")
            add(saveButton)
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

        if (fileChooser.currentDirectory == null) {
            fileChooser.currentDirectory = File(".")
        }

        fileChooser.selectedFile = File(fileChooser.currentDirectory, "${id.path}.json")
        val answer = fileChooser.showSaveDialog(frame)

        if (answer == JFileChooser.APPROVE_OPTION) {
            if (fileChooser.selectedFile.exists()) {
                val confirm = JOptionPane.showConfirmDialog(frame, "Do you want to overwrite an existing file?")

                if (confirm != JOptionPane.YES_OPTION)
                    return
            }

            fileChooser.selectedFile.writeText(
                Serializer.toJson((comboBox.selectedItem as ContentGenerator<*>).generate(
                    id
                ))
            )
        }
    }

    companion object {
        fun show() {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
            Gui().show()
        }
    }
}
