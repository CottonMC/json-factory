package io.github.cottonmc.jsonfactory;

import org.organicdesign.fp.collections.PersistentVector;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import static org.organicdesign.fp.StaticImports.vec;

/**
 * A path to a generated file.
 */
public final class GenerationPath {
    private final List<String> parts;

    /**
     * Creates a generation path from a list of path components.
     * Any changes in the part list will <i>not</i> be reflected in this path object.
     *
     * @param parts the path components
     */
    public GenerationPath(List<String> parts) {
        Objects.requireNonNull(parts, "parts");

        if (parts.isEmpty()) {
            throw new IllegalArgumentException("Empty path");
        }

        this.parts = PersistentVector.ofIter(parts); // Convert the list to an immutable one
    }

    /**
     * Creates a generation path from an array of path components.
     * Any changes in the part array will <i>not</i> be reflected in this path object.
     *
     * @param parts the path components
     */
    public GenerationPath(String... parts) {
        this(vec(Objects.requireNonNull(parts, "parts")));
    }

    /**
     * Joins this path into a path string using a separator.
     *
     * @param separator the separator between the path components
     * @return the joined path
     */
    public String join(String separator) {
        Objects.requireNonNull(separator, "separator");
        return String.join(separator, parts);
    }

    /**
     * {@linkplain Path#resolve(Path) Resolves} this generation path as a {@link Path} against another {@link Path}.
     *
     * @param root the path to resolve against
     * @return the resolved path
     */
    public Path resolveAgainst(Path root) {
        Objects.requireNonNull(root, "root");
        return root.resolve(toPath());
    }

    /**
     * Converts this generation path to a {@link Path}.
     *
     * @return a {@link Path} corresponding to this generation path
     */
    public Path toPath() {
        String first = parts.get(0); // safe because non-empty
        String[] more = new String[0];

        if (parts.size() > 1) {
            more = new String[parts.size() - 1];

            for (int i = 1; i < parts.size(); i++) {
                more[i - 1] = parts.get(i);
            }
        }

        return Path.of(first, more);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenerationPath that = (GenerationPath) o;
        return parts.equals(that.parts);
    }

    @Override
    public int hashCode() {
        return parts.hashCode();
    }

    @Override
    public String toString() {
        return "GenerationPath[" + join("/") + "]";
    }
}
