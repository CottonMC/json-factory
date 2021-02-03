package io.github.cottonmc.jf2.legacy;

import io.github.cottonmc.jf2.JsonFactory;
import io.github.cottonmc.jf2.context.GenerationContext;
import io.github.cottonmc.jsonfactory.gens.Gens;
import picocli.CommandLine;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.concurrent.Callable;

import static org.organicdesign.fp.StaticImports.xform;

@CommandLine.Command(name = "jf2-legacy-dumper", mixinStandardHelpOptions = true)
public final class Dumper implements Callable<Void> {
    @CommandLine.Option(names = {"-o", "--output"})
    private Path targetDirectory = Path.of(".");

    @CommandLine.Option(names = "--adorn")
    private boolean adorn = false;

    @Override
    public Void call() throws Exception {
        Files.createDirectories(targetDirectory);

        JsonFactory factory = new JsonFactory(xform(Gens.INSTANCE.getALL_GENS())
            .filter(generator -> generator.getExtension().toLowerCase(Locale.ROOT).equals("json"))
            .map(TemplateCreator::new));
        factory.generateTo(targetDirectory, new GenerationContext());

        return null;
    }

    public static void main(String[] args) {
        new CommandLine(new Dumper()).execute(args);
    }
}
