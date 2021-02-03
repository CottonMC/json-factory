package io.github.cottonmc.jf2.legacy;

import io.github.cottonmc.jf2.GenerationResult;
import io.github.cottonmc.jf2.Identifier;
import io.github.cottonmc.jf2.context.GenerationContext;
import io.github.cottonmc.jsonfactory.gens.ContentGenerator;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import static org.organicdesign.fp.StaticImports.xform;

public final class TemplateCreator implements io.github.cottonmc.jf2.generator.ContentGenerator {
    private final LegacyContentGenerator parent;

    public TemplateCreator(ContentGenerator generator) {
        this.parent = new LegacyContentGenerator(Objects.requireNonNull(generator, "generator"));
    }

    @Override
    public Set<GenerationResult> generate(@Nullable GenerationContext context) {
        GenerationContext dummyContext = new GenerationContext().add("id", new Identifier("___namespace_marker___", "___path_marker___"));

        return xform(parent.generate(dummyContext))
            .map(result -> {
                String template = result.getData()
                    .replace("___namespace_marker___:___path_marker___", "{{id}}")
                    .replace("___namespace_marker___", "{{id.namespace}}")
                    .replace("___path_marker___", "{{id.path}}");

                if (!template.endsWith("\n")) {
                    template += "\n";
                }

                return new GenerationResult(result.getPath(), template);
            })
            .toImSet();
    }

    @Override
    public Set<String> getRequiredKeys() {
        return Collections.emptySet();
    }
}
