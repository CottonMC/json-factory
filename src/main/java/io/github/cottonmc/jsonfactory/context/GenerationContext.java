package io.github.cottonmc.jsonfactory.context;

import com.samskivert.mustache.Mustache;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A simple add-only implementation of {@link Mustache.CustomContext}.
 *
 * <p>The naming convention for context keys is {@code lowerCamelCase}. This is because context values are often
 * Java objects that use those conventions as well.
 */
public final class GenerationContext implements Mustache.CustomContext {
    private final Map<String, Object> data = new HashMap<>();

    /**
     * Adds a value to this context.
     *
     * @param key   the key
     * @param value the value
     * @return this context
     * @throws NullPointerException     if the key is null
     * @throws IllegalArgumentException if the key is already bound to a value
     */
    public GenerationContext add(String key, Object value) {
        Objects.requireNonNull(key, "key");

        if (!data.containsKey(key)) {
            data.put(key, value);
        } else {
            throw new IllegalArgumentException(
                String.format(
                    "Cannot set value '%s' for key '%s'; it already has value '%s'",
                    value, key, data.get(key)
                )
            );
        }
        return this;
    }

    /**
     * Creates a copy of this context.
     *
     * @return the copy
     */
    public GenerationContext copy() {
        GenerationContext copy = new GenerationContext();
        copy.data.putAll(data);
        return copy;
    }

    /**
     * Returns a new {@link GenerationContext} with default values from the {@code defaults}.
     * The default values are copied for any keys present in the defaults that are missing from this one.
     *
     * @param defaults the default context
     * @return the new content
     * @throws NullPointerException if the defaults are null
     */
    public GenerationContext withDefaults(GenerationContext defaults) {
        GenerationContext copy = copy();
        defaults.data.forEach(copy.data::putIfAbsent);
        return copy;
    }

    /**
     * Fetches the value of a variable.
     *
     * @param name the name of the variable
     * @return the variable value
     */
    @Override
    public Object get(String name) {
        return data.get(name);
    }
}
