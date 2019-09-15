package io.github.cottonmc.jsonfactory.output;

/**
 * A location-aware output.
 */
public interface Output {
    /**
     * The location as a slash-separated list of path components starting from the parent directly.
     * <p>Example: assets/minecraft/models/block/stone.json
     * @return the location
     */
    String getLocation();

    /**
     * The contents of the output. This is what will be written to the files.
     * @return the content
     */
    String getContent();

    /**
     * Creates a simple immutable output with the parameters.
     *
     * @param location the location
     * @param content the content
     * @return an output object
     */
    static Output of(String location, String content) {
        return new SimpleOutput(location, content);
    }
}
