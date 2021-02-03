package io.github.cottonmc.jf2.generator;

import static io.github.cottonmc.jf2.generator.DefaultGenerators.*;

public final class BlockGenerators {
    public static final ContentGenerator SIMPLE_BLOCK_STATE = load("block-states/simple.json", "assets/{{id.namespace}}/blockstates/{{id.path}}.json");
    public static final ContentGenerator SIMPLE_BLOCK_MODEL = load("block-models/simple.json", "assets/{{id.namespace}}/models/block/{{id.path}}.json");
    public static final ContentGenerator SIMPLE_BLOCK_ITEM_MODEL = load("block-item-models/simple.json", "assets/{{id.namespace}}/models/item/{{id.path}}.json");
    public static final ContentGenerator SIMPLE_BLOCK_LOOT_TABLE = load("block-loot-tables/simple.json", "data/{{id.namespace}}/loot_tables/blocks/{{id.path}}.json");
    public static final ContentGenerator SIMPLE_BLOCK = multi(
        SIMPLE_BLOCK_STATE, SIMPLE_BLOCK_MODEL, SIMPLE_BLOCK_ITEM_MODEL, SIMPLE_BLOCK_LOOT_TABLE
    );

    private BlockGenerators() {
    }
}
