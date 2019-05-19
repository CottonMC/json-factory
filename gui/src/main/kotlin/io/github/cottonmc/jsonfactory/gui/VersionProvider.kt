package io.github.cottonmc.jsonfactory.gui

import picocli.CommandLine
import java.util.Properties

internal class VersionProvider : CommandLine.IVersionProvider {
    override fun getVersion(): Array<String> {
        val properties = Properties()
        properties.load(VersionProvider::class.java.getResourceAsStream("/json-factory/version-info.properties"))
        return arrayOf("JSON Factory GUI " + properties["version"] + " (git commit ${properties["commit-hash"]})")
    }
}
