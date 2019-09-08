package io.github.cottonmc.jsonfactory.generator;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import org.cactoos.Input;
import org.cactoos.text.TextOf;
import org.cactoos.text.UncheckedText;

import java.util.Map;

public final class MustacheContentGenerator implements ContentGenerator {
    private final Template template;

    public MustacheContentGenerator(Input templateInput) {
        this(new UncheckedText(new TextOf(templateInput)).asString());
    }

    public MustacheContentGenerator(String template) {
        this(Mustache.compiler().compile(template));
    }

    public MustacheContentGenerator(Template template) {
        this.template = template;
    }

    @Override
    public String generate(Map<String, ?> properties) {
        return template.execute(properties);
    }
}
