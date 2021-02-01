package io.github.cottonmc.jsonfactory.generator;

import com.samskivert.mustache.Template;
import io.github.cottonmc.jsonfactory.GenerationPath;
import io.github.cottonmc.jsonfactory.GenerationResult;
import io.github.cottonmc.jsonfactory.context.GenerationContext;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * A content generator that uses {@linkplain Template Mustache templates} for generation.
 *
 * <p>Mustache generators use two separate {@link Template} objects: one of the content and one for the content's path.
 * Both of the templates are evaluated using a {@link GenerationContext} to create the data.
 *
 * <p>The path template should use {@code /} at its path component separator (as in {@code file/in/directory}.
 * The separators will be converted to correct platform-specific ones with the path methods in {@link GenerationPath}.
 */
public final class MustacheContentGenerator implements ContentGenerator {
    private final Template pathTemplate;
    private final Template contentTemplate;

    /**
     * Constructs a Mustache-based content generator.
     *
     * @param pathTemplate    the template used for content paths
     * @param contentTemplate the template used for content
     */
    public MustacheContentGenerator(Template pathTemplate, Template contentTemplate) {
        this.pathTemplate = Objects.requireNonNull(pathTemplate, "pathTemplate");
        this.contentTemplate = Objects.requireNonNull(contentTemplate, "contentTemplate");
    }

    @Override
    public Set<GenerationResult> generate(GenerationContext context) {
        return Collections.singleton(
            new GenerationResult(
                new GenerationPath(pathTemplate.execute(context).split("/")),
                contentTemplate.execute(context)
            )
        );
    }
}