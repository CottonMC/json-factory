package io.github.cottonmc.jsonfactory.generator;

import io.github.cottonmc.jsonfactory.output.Output;

import java.util.Collection;
import java.util.Map;

@FunctionalInterface
public interface ContentGenerator {
    Collection<Output> generate(Map<String, ?> properties);
}
