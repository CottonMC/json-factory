package io.github.cottonmc.jsonfactory.generator;

import io.github.cottonmc.jsonfactory.GenerationResult;
import io.github.cottonmc.jsonfactory.context.GenerationContext;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.organicdesign.fp.StaticImports.set;

/**
 * A content generator that groups multiple generators into one.
 */
public final class MultiContentGenerator implements ContentGenerator {
    private final Iterable<? extends ContentGenerator> generators;

    /**
     * Constructs a new grouping content generator from an iterable of content generators.
     *
     * @param generators the generators
     */
    public MultiContentGenerator(Iterable<? extends ContentGenerator> generators) {
        this.generators = Objects.requireNonNull(generators, "generators");
    }

    /**
     * Constructs a new grouping content generator from an array of content generators.
     *
     * @param generators the generators
     */
    public MultiContentGenerator(ContentGenerator... generators) {
        this(set(Objects.requireNonNull(generators, "generators")));
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
