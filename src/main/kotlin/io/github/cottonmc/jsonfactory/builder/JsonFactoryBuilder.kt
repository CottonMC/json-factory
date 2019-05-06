package io.github.cottonmc.jsonfactory.builder

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.frontend.Frontend
import io.github.cottonmc.jsonfactory.frontend.Generator
import io.github.cottonmc.jsonfactory.frontend.MessageType
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import java.io.File

/**
 * Builder class, as an API frontend.
 * */
class JsonFactoryBuilder():Frontend{
    override fun printMessage(msg: String, type: MessageType) {
        when(type){
            MessageType.Error-> error(msg)
            MessageType.Important-> println(msg)
            MessageType.Warn-> println(msg)
            MessageType.Default->println(msg)
        }
    }

    override fun printSeparator() {
        println()
    }

    override fun onFinishedGenerating() {
        printMessage("generation finished",MessageType.Default)
    }

    override suspend fun shouldOverwriteFile(file: File): Boolean {
        return false
    }

    override suspend fun selectOutputDirectory(): File? {
        return folder
    }

    var folder: File = File(".")
    val generators=ArrayList<ContentGenerator>()
    val identifiers=ArrayList<Identifier>()

    /**
     * adds a specific content generator to the pool.
     * */
    fun addGenerator(generator:ContentGenerator):JsonFactoryBuilder{
        generators.add(generator)
        return this
    }

    /**
     * Adds a new identifier for generation.
     * */
    fun addIdentifier(id:Identifier):JsonFactoryBuilder{
        identifiers.add(id)
        return this
    }

    /**
     * Sets the target folder, where we should generate into
     * */
    fun setTarget(folder: File): JsonFactoryBuilder {

        if(!folder.isDirectory)
            throw IllegalStateException("$folder is not a directory!")
        this.folder = folder
        return this
    }

    /**
     * Builds all of the required resources.
     * */
    fun output(){
            Generator(this,generators).generateAll(identifiers.joinToString(separator = ","))
    }

}