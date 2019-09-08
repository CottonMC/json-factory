package io.github.cottonmc.jsonfactory.generator;

import java.util.Map;

@FunctionalInterface
public interface ContentGenerator {
    String generate(Map<String, ?> properties);
}
