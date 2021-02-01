package io.github.cottonmc.jsonfactory;

import java.util.Objects;

/**
 * The result from a content generator, containing a path and the generated data.
 */
public final class GenerationResult {
    private final GenerationPath path;
    private final String data;

    public GenerationResult(GenerationPath path, String data) {
        this.path = Objects.requireNonNull(path, "path");
        this.data = Objects.requireNonNull(data, "data");
    }

    public GenerationPath getPath() {
        return path;
    }

    public String getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenerationResult that = (GenerationResult) o;
        return path.equals(that.path) && data.equals(that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, data);
    }

    @Override
    public String toString() {
        return "GenerationResult[path=" + path + ", data=" + data + "]";
    }
}
