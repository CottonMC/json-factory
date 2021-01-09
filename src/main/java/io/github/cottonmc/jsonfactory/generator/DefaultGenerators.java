package io.github.cottonmc.jsonfactory.generator;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import io.github.cottonmc.jsonfactory.MultiContentGenerator;
import io.github.cottonmc.jsonfactory.MustacheContentGenerator;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class DefaultGenerators {
    private static final String TEMPLATE_ROOT = "/json-factory/templates/";
    private static final Mustache.Compiler COMPILER = Mustache.compiler();

    static ContentGenerator load(String... paths) {
        if ((paths.length & 1) != 0) {
            throw new IllegalArgumentException("Unpairable paths");
        }

        Map<String, String> pathMap = new HashMap<>();

        for (int i = 0; i < paths.length; i += 2) {
            pathMap.put(paths[i], paths[i + 1]);
        }

        return load(pathMap);
    }

    static ContentGenerator load(Map<String, String> paths) {
        if (paths.isEmpty()) throw new IllegalArgumentException("empty paths");

        List<ContentGenerator> generators = new ArrayList<>();

        paths.forEach((templateLocation, pathTemplateStr) -> {
            Template pathTemplate = COMPILER.compile(pathTemplateStr);

            try (InputStream in = DefaultGenerators.class.getResourceAsStream(TEMPLATE_ROOT + templateLocation);
                 InputStreamReader reader = new InputStreamReader(in)) {
                generators.add(new MustacheContentGenerator(pathTemplate, COMPILER.compile(reader)));
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });

        return generators.size() == 1 ? generators.get(0) : new MultiContentGenerator(generators);
    }

    static ContentGenerator multi(ContentGenerator... generators) {
        return new MultiContentGenerator(generators);
    }
}
