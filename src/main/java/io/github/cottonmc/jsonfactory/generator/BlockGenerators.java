package io.github.cottonmc.jsonfactory.generator;

import static io.github.cottonmc.jsonfactory.generator.DefaultGenerators.*;

public final class BlockGenerators {
    public static final ContentGenerator SIMPLE_BLOCK_STATE = load("block_states/simple.json", "assets/{{id.namespace}}/blockstates/{{id.path}}.json");
    public static final ContentGenerator SIMPLE_BLOCK_MODEL = load("block_models/simple.json", "assets/{{id.namespace}}/models/block/{{id.path}}.json");
    public static final ContentGenerator SIMPLE_BLOCK_ITEM_MODEL = load("block_item_models/simple.json", "assets/{{id.namespace}}/models/item/{{id.path}}.json");
    public static final ContentGenerator SIMPLE_BLOCK_LOOT_TABLE = load("block_loot_tables/simple.json", "assets/{{id.namespace}}/models/item/{{id.path}}.json");
    public static final ContentGenerator SIMPLE_BLOCK = multi(
        SIMPLE_BLOCK_STATE, SIMPLE_BLOCK_MODEL, SIMPLE_BLOCK_ITEM_MODEL, SIMPLE_BLOCK_LOOT_TABLE
    );

    private BlockGenerators() {
    }
}
