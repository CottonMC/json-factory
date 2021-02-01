package io.github.cottonmc.jsonfactory.generator;

import com.samskivert.mustache.Template;
import io.github.cottonmc.jsonfactory.GenerationPath;
import io.github.cottonmc.jsonfactory.GenerationResult;
import io.github.cottonmc.jsonfactory.context.GenerationContext;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import static org.organicdesign.fp.StaticImports.vec;

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
            new GenerationResult(
                new GenerationPath(vec(pathTemplate.execute(context).split("/"))),
                contentTemplate.execute(context)
            )
        );
    }
}
