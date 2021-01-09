package io.github.cottonmc.jsonfactory.generator;

import io.github.cottonmc.jsonfactory.context.GenerationContext;
import io.github.cottonmc.jsonfactory.GenerationResult;

import java.util.Set;

public interface ContentGenerator {
    Set<GenerationResult> generate(GenerationContext context);
}
