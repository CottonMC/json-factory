package io.github.cottonmc.jsonfactory.output;

/**
 * A simple {@link Output} implementation.
 */
final class SimpleOutput implements Output {
    private final String location;
    private final String content;

    SimpleOutput(String location, String content) {
        this.location = location;
        this.content = content;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public String getContent() {
        return content;
    }
}
