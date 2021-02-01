package io.github.cottonmc.jsonfactory.generator;

import io.github.cottonmc.jsonfactory.context.ContextKeys;
import io.github.cottonmc.jsonfactory.context.GenerationContext;
import io.github.cottonmc.jsonfactory.GenerationResult;

import java.util.Collections;
import java.util.Set;

/**
 * Content generators generate data files, such as JSON.
 *
 * <h2>Composing content generators</h2>
 *
 * Multiple content generators can be composed into one using {@link MultiContentGenerator}.
 * This can be used to combine the generators of one block or item type into one generator.
 *
 * @see MustacheContentGenerator
 */
public interface ContentGenerator {
    /**
     * Generates data from a context.
     *
     * @param context the generation context, cannot be null
     * @return the generated data
     */
    Set<GenerationResult> generate(GenerationContext context);

    /**
     * Gets the required context keys for generating data using this generator.
     *
     * <p>The default implementation returns a set containing {@link ContextKeys#ID}.
     *
     * @return the required context keys
     */
    // TODO: Separate ContextKey type
    default Set<String> getRequiredKeys() {
        return Collections.singleton(ContextKeys.ID);
    }
}
