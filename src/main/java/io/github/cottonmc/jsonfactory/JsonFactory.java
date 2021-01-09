package io.github.cottonmc.jsonfactory;

import io.github.cottonmc.jsonfactory.generator.ContentGenerator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Set;

public final class JsonFactory {
    private final ContentGenerator generator;

    public JsonFactory(Iterable<? extends ContentGenerator> generators) {
        Objects.requireNonNull(generators, "generators");
        this.generator = new MultiContentGenerator(generators);
    }

    public JsonFactory(ContentGenerator generator) {
        Objects.requireNonNull(generator, "generator");
        this.generator = generator;
    }

    public Set<GenerationResult> generate(GenerationContext context) {
        Objects.requireNonNull(context, "context");
        return generator.generate(context);
    }

    public Set<GenerationResult> generateTo(Path directory, GenerationContext context) throws IOException {
        Objects.requireNonNull(directory, "directory");
        Objects.requireNonNull(context, "context");

        if (Files.exists(directory) && !Files.isDirectory(directory)) {
            throw new IOException("Output path '" + directory + "' is not a directory");
        }

        Set<GenerationResult> results = generate(context);

        for (GenerationResult result : results) {
            Path target = directory.resolve(result.getPath().replace("/", directory.getFileSystem().getSeparator()));

            // Create missing containing directories
            Files.createDirectories(target.getParent());
            Files.write(target, result.getData().getBytes(StandardCharsets.UTF_8));
        }

        return results;
    }
}
