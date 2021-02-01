package io.github.cottonmc.jsonfactory.test;

import io.github.cottonmc.jsonfactory.GenerationPath;
import io.github.cottonmc.jsonfactory.context.ContextKeys;
import io.github.cottonmc.jsonfactory.context.GenerationContext;
import io.github.cottonmc.jsonfactory.GenerationResult;
import io.github.cottonmc.jsonfactory.Identifier;
import io.github.cottonmc.jsonfactory.generator.BlockGenerators;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.BDDAssertions.*;

final class SimpleBlockTests {
    private static final Identifier ID = new Identifier("tiny", "potato");

    private GenerationContext context;

    @BeforeEach
    void given() {
        context = new GenerationContext().add(ContextKeys.ID, ID);
    }

    @Test
    @DisplayName("simple block model")
    void blockModel() {
        Set<GenerationResult> results = BlockGenerators.SIMPLE_BLOCK_MODEL.generate(context);

        then(results)
            .singleElement()
            .extracting(TestUtil::toJson)
            .isEqualTo(TestUtil.expected("block-models/simple.json"));

        then(results)
            .singleElement()
            .extracting(GenerationResult::getPath)
            .isEqualTo(new GenerationPath("assets", "tiny", "models", "block", "potato.json"));
    }

    @Test
    @DisplayName("simple block item model")
    void blockItemModel() {
        Set<GenerationResult> results = BlockGenerators.SIMPLE_BLOCK_ITEM_MODEL.generate(context);

        then(results)
            .singleElement()
            .extracting(TestUtil::toJson)
            .isEqualTo(TestUtil.expected("block-item-models/simple.json"));

        then(results)
            .singleElement()
            .extracting(GenerationResult::getPath)
            .isEqualTo(new GenerationPath("assets", "tiny", "models", "item", "potato.json"));
    }

    @Test
    @DisplayName("simple block state")
    void blockState() {
        Set<GenerationResult> results = BlockGenerators.SIMPLE_BLOCK_STATE.generate(context);

        then(results)
            .singleElement()
            .extracting(TestUtil::toJson)
            .isEqualTo(TestUtil.expected("block-states/simple.json"));

        then(results)
            .singleElement()
            .extracting(GenerationResult::getPath)
            .isEqualTo(new GenerationPath("assets", "tiny", "blockstates", "potato.json"));
    }

    @Test
    @DisplayName("simple loot table")
    void lootTable() {
        Set<GenerationResult> results = BlockGenerators.SIMPLE_BLOCK_LOOT_TABLE.generate(context);

        then(results)
            .singleElement()
            .extracting(TestUtil::toJson)
            .isEqualTo(TestUtil.expected("block-loot-tables/simple.json"));

        then(results)
            .singleElement()
            .extracting(GenerationResult::getPath)
            .isEqualTo(new GenerationPath("data", "tiny", "loot_tables", "blocks", "potato.json"));
    }
}
