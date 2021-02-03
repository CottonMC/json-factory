package io.github.cottonmc.jsonfactory.test;

import io.github.cottonmc.jsonfactory.GenerationPath;
import io.github.cottonmc.jsonfactory.GenerationResult;
import io.github.cottonmc.jsonfactory.Identifier;
import io.github.cottonmc.jsonfactory.context.ContextKeys;
import io.github.cottonmc.jsonfactory.context.GenerationContext;
import io.github.cottonmc.jsonfactory.generator.BlockGenerators;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.organicdesign.fp.tuple.Tuple2;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.BDDAssertions.then;
import static org.organicdesign.fp.StaticImports.xform;

final class SlabTests {
    private static final Identifier ID = new Identifier("tiny", "potato");

    private GenerationContext context;

    @BeforeEach
    void given() {
        context = new GenerationContext().add(ContextKeys.ID, ID);
    }

    @Test
    @DisplayName("slab block model")
    void blockModel() {
        Set<GenerationResult> results = BlockGenerators.SLAB_BLOCK_MODEL.generate(context);
        Map<String, GenerationResult> namesToResults = xform(results)
            .toImMap(result -> {
                List<String> pathComponents = result.getPath().getComponents();
                String name = pathComponents.get(pathComponents.size() - 1);

                return Tuple2.of(name, result);
            });

        then(namesToResults.keySet())
            .containsExactlyInAnyOrder("potato_slab.json", "potato_slab_top.json");

        then(results)
            .extracting(TestUtil::toJson)
            .containsExactlyInAnyOrder(
                TestUtil.expected("block-models/slab.json"),
                TestUtil.expected("block-models/slab_top.json")
            );
    }

    @Test
    @DisplayName("slab block item model")
    void blockItemModel() {
        Set<GenerationResult> results = BlockGenerators.SLAB_BLOCK_ITEM_MODEL.generate(context);

        then(results)
            .singleElement()
            .extracting(TestUtil::toJson)
            .isEqualTo(TestUtil.expected("block-item-models/slab.json"));

        then(results)
            .singleElement()
            .extracting(GenerationResult::getPath)
            .isEqualTo(new GenerationPath("assets", "tiny", "models", "item", "potato_slab.json"));
    }

    @Test
    @DisplayName("slab block state")
    void blockState() {
        Set<GenerationResult> results = BlockGenerators.SLAB_BLOCK_STATE.generate(context);

        then(results)
            .singleElement()
            .extracting(TestUtil::toJson)
            .isEqualTo(TestUtil.expected("block-states/slab.json"));

        then(results)
            .singleElement()
            .extracting(GenerationResult::getPath)
            .isEqualTo(new GenerationPath("assets", "tiny", "blockstates", "potato_slab.json"));
    }

    @Test
    @DisplayName("slab loot table")
    void lootTable() {
        Set<GenerationResult> results = BlockGenerators.SLAB_BLOCK_LOOT_TABLE.generate(context);

        then(results)
            .singleElement()
            .extracting(TestUtil::toJson)
            .isEqualTo(TestUtil.expected("block-loot-tables/slab.json"));

        then(results)
            .singleElement()
            .extracting(GenerationResult::getPath)
            .isEqualTo(new GenerationPath("data", "tiny", "loot_tables", "blocks", "potato_slab.json"));
    }
}
