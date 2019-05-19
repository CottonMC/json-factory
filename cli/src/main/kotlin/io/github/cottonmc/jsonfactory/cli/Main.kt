package io.github.cottonmc.jsonfactory.cli

import arrow.core.Left
import arrow.core.Right
import arrow.core.flatMap
import io.github.cottonmc.jsonfactory.cli.util.IdentifierParser
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.frontend.ContentWriter
import io.github.cottonmc.jsonfactory.gens.Gens
import kotlinx.coroutines.runBlocking
import picocli.CommandLine
import java.nio.file.Path
import java.nio.file.Paths
import java.util.Properties
import java.util.concurrent.Callable

@CommandLine.Command(
    name = "json-factory-cli",
    mixinStandardHelpOptions = true,
    versionProvider = Main.VersionProvider::class,
    resourceBundle = "json-factory.i18n.I18n-cli"
)
object Main : Callable<Unit> {
    @CommandLine.Option(names = ["-G", "--generators"], required = true, arity = "1..*", descriptionKey = "cli.option.generators")
    lateinit var generators: List<String>
        private set

    @CommandLine.Option(names = ["-i", "--identifiers"], required = true, arity = "1..*", descriptionKey = "cli.option.identifiers")
    lateinit var identifiers: List<String>
        private set

    @CommandLine.Option(names = ["-o", "--output"], descriptionKey = "cli.option.output")
    var outputDirectory: Path = Paths.get(".")

    override fun call() = runBlocking {
        IdentifierParser.convertToIds(identifiers).flatMap {
            Right(ContentWriter(Cli(outputDirectory), generators.map { genId ->
                Gens.ALL_GENS.find { gen ->
                    genId == gen.id
                } ?: run {
                    return@flatMap Left("Unknown generator: $genId")
                }
            }).withAllEnabled().writeAll(it))
        }.fold(
            ifLeft = { System.err.println(it) },
            ifRight = { it.join() }
        )
    }

    internal class VersionProvider : CommandLine.IVersionProvider {
        override fun getVersion(): Array<String> {
            val properties = Properties()
            properties.load(VersionProvider::class.java.getResourceAsStream("/json-factory/version-info.properties"))
            return arrayOf("JSON Factory GUI " + properties["version"] + " (git commit ${properties["commit-hash"]})")
        }
    }
}

private object IdentifierTypeConverter : CommandLine.ITypeConverter<Identifier> {
    override fun convert(value: String) = Identifier(value)
}

fun main(args: Array<String>): Unit =
    CommandLine(Main).run {
        registerConverter(Identifier::class.java, IdentifierTypeConverter)
        parseWithHandlers<List<Any?>>(
            CommandLine.RunLast(),
            CommandLine.DefaultExceptionHandler(),
            *args
        )
    }
