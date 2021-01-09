package io.github.cottonmc.jsonfactory;

import java.util.Objects;

public final class GenerationResult {
    private final String path;
    private final String data;

    public GenerationResult(String path, String data) {
        this.path = Objects.requireNonNull(path, "path");
        this.data = Objects.requireNonNull(data, "data");
    }

    public String getPath() {
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
}
