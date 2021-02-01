package io.github.cottonmc.jsonfactory.test;

import io.github.cottonmc.jsonfactory.context.ContextKeys;
import io.github.cottonmc.jsonfactory.context.GenerationContext;
import io.github.cottonmc.jsonfactory.GenerationResult;
import io.github.cottonmc.jsonfactory.Identifier;
import io.github.cottonmc.jsonfactory.JsonFactory;
import io.github.cottonmc.jsonfactory.generator.BlockGenerators;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

import static org.assertj.core.api.BDDAssertions.*;

final class JsonFactoryTests {
    private static final Identifier ID = new Identifier("tiny", "potato");

    @Test
    @DisplayName("generate to directory")
    void generateToDirectory() throws Exception {
        Path root = Files.createTempDirectory("json-factory-test");
        JsonFactory factory = new JsonFactory(BlockGenerators.SIMPLE_BLOCK);
        Set<GenerationResult> results = factory.generateTo(root, new GenerationContext().add(ContextKeys.ID, ID));

        then(results)
            .extracting(GenerationResult::getPath)
            .extracting(path -> path.resolveAgainst(root))
            .allMatch(Files::exists, "file exists");
    }
}
