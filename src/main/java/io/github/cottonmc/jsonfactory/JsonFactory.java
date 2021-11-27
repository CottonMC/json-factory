package io.github.cottonmc.jsonfactory;

import io.github.cottonmc.jsonfactory.context.GenerationContext;
import io.github.cottonmc.jsonfactory.generator.ContentGenerator;
import io.github.cottonmc.jsonfactory.generator.MultiContentGenerator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Set;

public final class JsonFactory {
    private final ContentGenerator generator;
    private boolean skipExisting = false;

    public JsonFactory(Iterable<? extends ContentGenerator> generators) {
        Objects.requireNonNull(generators, "generators");
        this.generator = new MultiContentGenerator(generators);
    }

    public JsonFactory(ContentGenerator generator) {
        Objects.requireNonNull(generator, "generator");
        this.generator = generator;
    }

    /**
     * Makes this factory skip existing output files when generating.
     */
    public void skipExisting() {
        skipExisting = true;
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
            Path target = result.getPath().resolveAgainst(directory);

            if (skipExisting && Files.exists(target)) continue;

            // Create missing containing directories
            Files.createDirectories(target.getParent());
            Files.write(target, result.getData().getBytes(StandardCharsets.UTF_8));
        }

        return results;
    }
}
