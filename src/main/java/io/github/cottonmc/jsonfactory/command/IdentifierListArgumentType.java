package io.github.cottonmc.jsonfactory.command;

import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.exceptions.*;
import io.github.cottonmc.jsonfactory.util.Identifier;
import org.cactoos.list.ListOf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class IdentifierListArgumentType implements ArgumentType<List<Identifier>> {
    private static final List<String> EXAMPLES = new ListOf<>("minecraft:oak", "minecraft:stone minecraft:sandstone");
    private static final Dynamic2CommandExceptionType CONVERSION_ERROR =
            new Dynamic2CommandExceptionType(
                    (str, reason) -> new LiteralMessage(String.format("Couldn't convert string to identifier: %s (%s)", str, reason))
            );
    private static final DynamicNCommandExceptionType INVALID_INPUTS =
            new DynamicNCommandExceptionType(
                    args -> new LiteralMessage(
                            "Invalid inputs: " + Stream.of(args)
                                    .map(Object::toString)
                                    .collect(Collectors.joining(", "))
                    )
            );

    private final Predicate<? super Identifier> predicate;

    private IdentifierListArgumentType(Predicate<? super Identifier> predicate) {
        this.predicate = predicate;
    }

    @Override
    public List<Identifier> parse(StringReader reader) throws CommandSyntaxException {
        String text = reader.getRemaining();
        reader.setCursor(reader.getTotalLength());

        List<Identifier> result = new ArrayList<>();
        List<String> invalidIds = new ArrayList<>();
        for (String id : text.split(" +")) {
            try {
                Identifier identifier = new Identifier(id);
                if (predicate.test(identifier)) {
                    result.add(identifier);
                } else {
                    invalidIds.add(id);
                }
            } catch (RuntimeException e) {
                throw CONVERSION_ERROR.create(id, e.getMessage());
            }
        }

        if (!invalidIds.isEmpty()) {
            throw INVALID_INPUTS.create(this, invalidIds.toArray());
        }

        return result;
    }

    @Override
    public Collection<String> getExamples() {
        return EXAMPLES;
    }

    @Override
    public String toString() {
        return "idList()";
    }

    public static IdentifierListArgumentType idList() {
        return new IdentifierListArgumentType(id -> true);
    }

    public static IdentifierListArgumentType idList(Predicate<? super Identifier> predicate) {
        return new IdentifierListArgumentType(predicate);
    }
}
