package io.github.cottonmc.jsonfactory.util;

import java.util.Objects;

public final class Identifier {
    private final String namespace;
    private final String path;

    public Identifier(String namespace, String path) {
        this.namespace = Objects.requireNonNull(namespace, "Identifier namespace must be non-null");
        this.path = Objects.requireNonNull(path, "Identifier path must be non-null");
    }

    public Identifier(String id) {
        String[] split = id.split(":");
        if (split.length != 2) {
            throw new IllegalArgumentException("Illegal amount of colon-separated parts in identifier '" + id + "': " + split.length);
        }

        this.namespace = split[0];
        this.path = split[1];
    }

    public String getNamespace() {
        return namespace;
    }

    public String getPath() {
        return path;
    }

    public Identifier prefixPath(String prefix) {
        return new Identifier(namespace, prefix + path);
    }

    public Identifier suffixPath(String suffix) {
        return new Identifier(namespace, path + suffix);
    }

    public Identifier wrapPath(String prefix, String suffix) {
        return new Identifier(namespace, prefix + path + suffix);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identifier that = (Identifier) o;
        return namespace.equals(that.namespace) && path.equals(that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(namespace, path);
    }

    @Override
    public String toString() {
        return namespace + ':' + path;
    }
}
