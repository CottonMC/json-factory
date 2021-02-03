package io.github.cottonmc.jsonfactory.generator;

import static io.github.cottonmc.jsonfactory.generator.DefaultGenerators.*;

public final class BlockGenerators {
    public static final ContentGenerator SIMPLE_BLOCK_STATE = load("block-states/simple.json", "assets/{{id.namespace}}/blockstates/{{id.path}}.json");
    public static final ContentGenerator SIMPLE_BLOCK_MODEL = load("block-models/simple.json", "assets/{{id.namespace}}/models/block/{{id.path}}.json");
    public static final ContentGenerator SIMPLE_BLOCK_ITEM_MODEL = load("block-item-models/simple.json", "assets/{{id.namespace}}/models/item/{{id.path}}.json");
    public static final ContentGenerator SIMPLE_BLOCK_LOOT_TABLE = load("block-loot-tables/simple.json", "data/{{id.namespace}}/loot_tables/blocks/{{id.path}}.json");
    public static final ContentGenerator SIMPLE_BLOCK = multi(
        SIMPLE_BLOCK_STATE, SIMPLE_BLOCK_MODEL, SIMPLE_BLOCK_ITEM_MODEL, SIMPLE_BLOCK_LOOT_TABLE
    );

    public static final ContentGenerator SLAB_BLOCK_STATE = load("block-states/slab.json", "assets/{{id.namespace}}/blockstates/{{id.path}}_slab.json");
    public static final ContentGenerator SLAB_BLOCK_MODEL = multi(
        load("block-models/slab.json", "assets/{{id.namespace}}/models/block/{{id.path}}_slab.json"),
        load("block-models/slab_top.json", "assets/{{id.namespace}}/models/block/{{id.path}}_slab_top.json")
    );
    public static final ContentGenerator SLAB_BLOCK_ITEM_MODEL = load("block-item-models/slab.json", "assets/{{id.namespace}}/models/item/{{id.path}}_slab.json");
    public static final ContentGenerator SLAB_BLOCK_LOOT_TABLE = load("block-loot-tables/slab.json", "data/{{id.namespace}}/loot_tables/blocks/{{id.path}}_slab.json");
    public static final ContentGenerator SLAB = multi(
        SLAB_BLOCK_STATE, SLAB_BLOCK_MODEL, SLAB_BLOCK_ITEM_MODEL, SLAB_BLOCK_LOOT_TABLE
    );

    private BlockGenerators() {
    }
}
