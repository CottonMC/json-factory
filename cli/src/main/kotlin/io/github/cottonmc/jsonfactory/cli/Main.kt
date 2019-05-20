package io.github.cottonmc.jsonfactory.cli

import arrow.core.Left
import arrow.core.Try
import arrow.core.flatMap
import io.github.cottonmc.jsonfactory.data.Identifiers
import io.github.cottonmc.jsonfactory.frontend.ContentWriter
import io.github.cottonmc.jsonfactory.gens.Gens
import kotlinx.coroutines.runBlocking
import picocli.CommandLine
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.Properties
import java.util.concurrent.Callable
import kotlin.system.exitProcess

@CommandLine.Command(
    name = "json-factory-cli",
    mixinStandardHelpOptions = true,
    versionProvider = Main.VersionProvider::class,
    resourceBundle = "json-factory.i18n.I18n-cli"
)
private object Main : Callable<Unit> {
    @CommandLine.Option(
        names = ["-G", "--generators"],
        required = true,
        arity = "1..*",
        descriptionKey = "cli.option.generators"
    )
    lateinit var generators: List<String>
        private set

    @CommandLine.Option(
        names = ["-i", "--identifiers"],
        required = true,
        arity = "1..*",
        descriptionKey = "cli.option.identifiers"
    )
    lateinit var identifiers: List<String>
        private set

    @CommandLine.Option(names = ["-o", "--output"], descriptionKey = "cli.option.output")
    var outputDirectory: Path = Paths.get(".")

    @CommandLine.Option(names = ["--stacktrace"], descriptionKey = "cli.option.stacktrace")
    var stacktrace: Boolean = false

    override fun call() = runBlocking {
        val cli = Cli(outputDirectory)

        // TODO: i18n for messages
        if (!Files.isDirectory(outputDirectory)) {
            System.err.println("Provided output path $outputDirectory is not a directory!")
            exitProcess(1)
        }

        Identifiers.convertToIds(identifiers).flatMap {
            Try {
                ContentWriter(cli, generators.map { genId ->
                    Gens.ALL_GENS.find { gen ->
                        genId == gen.id
                    } ?: return@flatMap Left("Unknown generator: $genId")
                }).writeAll(it)
            }.toEither {
                if (!stacktrace) "${it::class.simpleName}: ${it.message}"
                else collectString { out -> it.printStackTrace(out) }
            }
        }.fold(
            ifLeft = { System.err.println(it) },
            ifRight = { it.join() }
        )
    }

    internal class VersionProvider : CommandLine.IVersionProvider {
        override fun getVersion(): Array<String> {
            val properties = Properties()
            properties.load(VersionProvider::class.java.getResourceAsStream("/json-factory/version-info.properties"))
            return arrayOf("JSON Factory CLI " + properties["version"] + " (git commit ${properties["commit-hash"]})")
        }
    }
}

private inline fun collectString(block: (PrintStream) -> Unit): String = ByteArrayOutputStream().use { out ->
    PrintStream(out).use(block)
    out.toString("UTF-8")
}

fun main(args: Array<String>): Unit =
    CommandLine.call(Main, *args)
