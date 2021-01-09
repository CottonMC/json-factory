package io.github.cottonmc.jsonfactory;

import io.github.cottonmc.jsonfactory.context.GenerationContext;
import io.github.cottonmc.jsonfactory.generator.ContentGenerator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class MultiContentGenerator implements ContentGenerator {
    private final Iterable<? extends ContentGenerator> generators;

    public MultiContentGenerator(Iterable<? extends ContentGenerator> generators) {
        this.generators = Objects.requireNonNull(generators, "generators");
    }

    public MultiContentGenerator(ContentGenerator... generators) {
        this(new HashSet<>(Arrays.asList(generators)));
    }

    @Override
    public Set<GenerationResult> generate(GenerationContext context) {
        Set<GenerationResult> results = new HashSet<>();

        for (ContentGenerator generator : generators) {
            results.addAll(generator.generate(context));
        }

        return results;
    }
}
