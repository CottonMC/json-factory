package io.github.cottonmc.jsonfactory.util;

import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class ImmutableMapBuilder<K, V> {
    private final Set<Map.Entry<K, V>> entries = new HashSet<>();

    public ImmutableMapBuilder<K, V> add(K key, V value) {
        entries.add(new MapEntry<>(key, value));
        return this;
    }

    public ImmutableMapBuilder<K, V> add(Map.Entry<K, V>... entries) {
        this.entries.addAll(Arrays.asList(entries));
        return this;
    }

    public Map<K, V> build() {
        return new MapOf<>(entries);
    }
}
