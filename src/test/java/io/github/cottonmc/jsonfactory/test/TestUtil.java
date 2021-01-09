package io.github.cottonmc.jsonfactory.test;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonObject;
import blue.endless.jankson.api.SyntaxError;
import io.github.cottonmc.jsonfactory.GenerationResult;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

final class TestUtil {
    private static final String EXPECTED_ROOT = "/json-factory/expected/";

    static JsonObject toJson(GenerationResult result) {
        try {
            return Jankson.builder().build().load(result.getData());
        } catch (SyntaxError e) {
            throw new RuntimeException(e);
        }
    }

    static JsonObject expected(String location) {
        try (InputStream in = TestUtil.class.getResourceAsStream(EXPECTED_ROOT + location)) {
            return Jankson.builder().build().load(in);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (SyntaxError e) {
            throw new RuntimeException(e);
        }
    }
}
