package io.github.cottonmc.jsonfactory;

import com.samskivert.mustache.Template;
import io.github.cottonmc.jsonfactory.context.GenerationContext;
import io.github.cottonmc.jsonfactory.generator.ContentGenerator;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public final class MustacheContentGenerator implements ContentGenerator {
    private final Template pathTemplate;
    private final Template contentTemplate;

    public MustacheContentGenerator(Template pathTemplate, Template contentTemplate) {
        this.pathTemplate = Objects.requireNonNull(pathTemplate, "pathTemplate");
        this.contentTemplate = Objects.requireNonNull(contentTemplate, "contentTemplate");
    }

    @Override
    public Set<GenerationResult> generate(GenerationContext context) {
        return Collections.singleton(
            new GenerationResult(pathTemplate.execute(context), contentTemplate.execute(context))
        );
    }
}
