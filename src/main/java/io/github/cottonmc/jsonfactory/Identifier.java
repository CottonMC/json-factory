package io.github.cottonmc.jsonfactory;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Identifier {
    private static final Pattern NAMESPACE_PATTERN = Pattern.compile("^[a-z0-9-_]+$");
    private static final Pattern PATH_PATTERN = Pattern.compile("^[a-z0-9-_./]+$");

    private final String namespace;
    private final String path;

    public Identifier(String identifier) {
        Objects.requireNonNull(identifier, "identifier");
        String[] components = identifier.split(":");

        if (components.length != 2) {
            throw new IllegalArgumentException("Identifier '" + identifier + "' does not contain exactly 2 color-separated components");
        }

        namespace = validateNamespace(components[0]);
        path = validatePath(components[1]);
    }

    public Identifier(String namespace, String path) {
        this.namespace = validateNamespace(namespace);
        this.path = validateNamespace(path);
    }

    public String getNamespace() {
        return namespace;
    }

    public String getPath() {
        return path;
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

    private static String validateNamespace(String namespace) {
        Objects.requireNonNull(namespace, "namespace");

        if (!NAMESPACE_PATTERN.matcher(namespace).matches()) {
            throw new IllegalArgumentException("Namespace '" + namespace + "' doesn't match pattern " + NAMESPACE_PATTERN.pattern());
        }

        return namespace;
    }

    private static String validatePath(String path) {
        Objects.requireNonNull(path, "path");

        if (!PATH_PATTERN.matcher(path).matches()) {
            throw new IllegalArgumentException("Path '" + path + "' doesn't match pattern " + PATH_PATTERN.pattern());
        }

        return path;
    }
}
