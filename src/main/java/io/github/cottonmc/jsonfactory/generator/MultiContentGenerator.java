package io.github.cottonmc.jsonfactory.generator;

import io.github.cottonmc.jsonfactory.GenerationResult;
import io.github.cottonmc.jsonfactory.context.GenerationContext;
import org.organicdesign.fp.collections.ImList;
import org.organicdesign.fp.collections.PersistentVector;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.organicdesign.fp.StaticImports.vec;

/**
 * A content generator that groups multiple generators into one.
 */
public final class MultiContentGenerator implements ContentGenerator {
    private final ImList<? extends ContentGenerator> generators;
    private Set<String> requiredKeys = null;

    /**
     * Constructs a new grouping content generator from an iterable of content generators.
     *
     * <p>Any changes to the iterable after this constructor is called will not be reflected in the generator.
     *
     * @param generators the generators
     */
    public MultiContentGenerator(Iterable<? extends ContentGenerator> generators) {
        this.generators = PersistentVector.ofIter(Objects.requireNonNull(generators, "generators"));
    }

    /**
     * Constructs a new grouping content generator from an array of content generators.
     *
     * <p>Any changes to the array after this constructor is called will not be reflected in the generator.
     *
     * @param generators the generators
     */
    public MultiContentGenerator(ContentGenerator... generators) {
        this(vec(Objects.requireNonNull(generators, "generators")));
    }

    @Override
    public Set<GenerationResult> generate(GenerationContext context) {
        Set<GenerationResult> results = new HashSet<>();

        for (ContentGenerator generator : generators) {
            results.addAll(generator.generate(context));
        }

        return results;
    }

    @Override
    public Set<String> getRequiredKeys() {
        if (requiredKeys == null) {
            // Cache the set for future use
            requiredKeys = generators.flatMap(ContentGenerator::getRequiredKeys).toImSet();
        }

        return requiredKeys;
    }
}
