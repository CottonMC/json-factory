package io.github.cottonmc.jf2.legacy;

import io.github.cottonmc.jf2.GenerationPath;
import io.github.cottonmc.jf2.GenerationResult;
import io.github.cottonmc.jf2.context.ContextKeys;
import io.github.cottonmc.jf2.context.GenerationContext;
import io.github.cottonmc.jsonfactory.data.Identifier;
import io.github.cottonmc.jsonfactory.gens.ContentGenerator;
import org.organicdesign.fp.collections.ImSet;

import java.util.Collections;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

import static org.organicdesign.fp.StaticImports.xform;

public final class LegacyContentGenerator implements io.github.cottonmc.jf2.generator.ContentGenerator {
    private final ContentGenerator parent;

    public LegacyContentGenerator(ContentGenerator parent) {
        this.parent = Objects.requireNonNull(parent, "parent");

        if (!parent.getExtension().toLowerCase(Locale.ROOT).equals("json")) {
            throw new IllegalArgumentException("Unknown content type '" + parent.getExtension() + "': LegacyContentGenerator only supports json");
        }
    }

    @Override
    public ImSet<GenerationResult> generate(GenerationContext context) {
        Identifier id = Identifier.Companion.invoke(Objects.toString(context.get(ContextKeys.ID)));
        String resourceRoot = parent.getResourceRoot().getPath();
        String namespace = id.getNamespace();
        String directory = parent.getPath();
        String baseFileName = id.getPath();
        String extension = parent.getExtension();

        return xform(parent.generate(id))
            .map(output -> {
                String fileName = output.getNameWrapper().applyTo(baseFileName);
                String fullPath = String.format("%s/%s/%s/%s.%s", resourceRoot, namespace, directory, fileName, extension);
                String data = Outputs.toString(output);

                return new GenerationResult(new GenerationPath(fullPath.split("/")), data);
            })
            .toImSet();
    }

    @Override
    public Set<String> getRequiredKeys() {
        // The legacy content generators only expect ID
        return Collections.singleton(ContextKeys.ID);
    }
}
