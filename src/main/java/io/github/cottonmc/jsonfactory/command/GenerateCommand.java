package io.github.cottonmc.jsonfactory.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import io.github.cottonmc.jsonfactory.generator.ContentGenerator;
import io.github.cottonmc.jsonfactory.util.Identifier;

import java.util.List;
import java.util.Map;

import static io.github.cottonmc.jsonfactory.command.IdentifierArgumentType.id;
import static io.github.cottonmc.jsonfactory.command.IdentifierListArgumentType.idList;

public final class GenerateCommand<S> {
    private final Map<Identifier, ContentGenerator> generators;

    public GenerateCommand(Map<Identifier, ContentGenerator> generators) {
        this.generators = generators;
    }

    public void register(CommandDispatcher<S> dispatcher) {
        dispatcher.register(
                literal("generate").then(argument("id", id())
                        .then(argument("generators", idList(generators::containsKey)).executes(this::run))
                )
        );
    }

    @SuppressWarnings("unchecked")
    private int run(CommandContext<S> context) {
        Identifier id = context.getArgument("id", Identifier.class);
        List<Identifier> generatorIds = context.getArgument("generators", List.class);

        // TODO

        return 1;
    }

    private LiteralArgumentBuilder<S> literal(String name) {
        return LiteralArgumentBuilder.literal(name);
    }

    private <T> RequiredArgumentBuilder<S, T> argument(String name, ArgumentType<T> type) {
        return RequiredArgumentBuilder.argument(name, type);
    }
}
