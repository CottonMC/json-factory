package io.github.cottonmc.jsonfactory.generator;

import com.samskivert.mustache.Template;
import io.github.cottonmc.jsonfactory.GenerationPath;
import io.github.cottonmc.jsonfactory.GenerationResult;
import io.github.cottonmc.jsonfactory.context.GenerationContext;
import org.jetbrains.annotations.Nullable;

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
 *
 * <p>By default, Mustache content generators use the same required context keys as the default implementation of
 * {@link ContentGenerator#getRequiredKeys()}. A custom set can be specified using the second constructor.
 */
public final class MustacheContentGenerator implements ContentGenerator {
    private final Template pathTemplate;
    private final Template contentTemplate;
    private final @Nullable Set<String> requiredKeys;

    /**
     * Constructs a Mustache-based content generator.
     *
     * @param pathTemplate    the template used for content paths
     * @param contentTemplate the template used for content
     */
    public MustacheContentGenerator(Template pathTemplate, Template contentTemplate) {
        this.pathTemplate = Objects.requireNonNull(pathTemplate, "pathTemplate");
        this.contentTemplate = Objects.requireNonNull(contentTemplate, "contentTemplate");
        this.requiredKeys = null;
    }

    /**
     * Constructs a Mustache-based content generator with custom context key requirements.
     *
     * @param pathTemplate    the template used for content paths
     * @param contentTemplate the template used for content
     * @param requiredKeys    the required context keys for this generator
     */
    public MustacheContentGenerator(Template pathTemplate, Template contentTemplate, Set<String> requiredKeys) {
        this.pathTemplate = Objects.requireNonNull(pathTemplate, "pathTemplate");
        this.contentTemplate = Objects.requireNonNull(contentTemplate, "contentTemplate");
        this.requiredKeys = Objects.requireNonNull(requiredKeys, "requiredKeys");
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

    @Override
    public Set<String> getRequiredKeys() {
        return requiredKeys != null ? requiredKeys : ContentGenerator.super.getRequiredKeys();
    }
}
