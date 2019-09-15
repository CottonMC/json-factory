package io.github.cottonmc.jsonfactory.generator;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import io.github.cottonmc.jsonfactory.output.Output;
import org.cactoos.Input;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.cactoos.text.TextOf;
import org.cactoos.text.UncheckedText;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public final class MustacheContentGenerator implements ContentGenerator {
    private static final Mustache.Compiler COMPILER = Mustache.compiler();

    /**
     * A map of file name templates to file content templates.
     */
    private final Map<Template, Template> templates;

    public MustacheContentGenerator(String pathTemplate, Input templateInput) {
        this(pathTemplate, new UncheckedText(new TextOf(templateInput)).asString());
    }

    public MustacheContentGenerator(String pathTemplate, String template) {
        this(new MapOf<>(new MapEntry<>(COMPILER.compile(pathTemplate), COMPILER.compile(template))));
    }

    public MustacheContentGenerator(Map<Template, Template> templates) {
        this.templates = templates;
    }

    @Override
    public Collection<Output> generate(Map<String, ?> properties) {
        return templates.entrySet().stream()
                .map(entry -> Output.of(entry.getKey().execute(properties), entry.getValue().execute(properties)))
                .collect(Collectors.toSet());
    }
}
