package io.github.cottonmc.jsonfactory.generator;

import io.github.cottonmc.jsonfactory.GenerationResult;
import io.github.cottonmc.jsonfactory.context.GenerationContext;

import java.util.Set;

public final class DefaultedContentGenerator implements ContentGenerator {
    private final ContentGenerator parent;
    private final GenerationContext defaults;

    public DefaultedContentGenerator(ContentGenerator parent, GenerationContext defaults) {
        this.parent = parent;
        this.defaults = defaults;
    }

    @Override
    public Set<GenerationResult> generate(GenerationContext context) {
        GenerationContext subcontext = context.withDefaults(defaults);
        return parent.generate(subcontext);
    }

    @Override
    public Set<String> getRequiredKeys() {
        return parent.getRequiredKeys();
    }
}
