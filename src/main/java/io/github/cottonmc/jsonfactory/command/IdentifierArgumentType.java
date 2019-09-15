package io.github.cottonmc.jsonfactory.command;

import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import io.github.cottonmc.jsonfactory.util.Identifier;

final class IdentifierArgumentType implements ArgumentType<Identifier> {
    private static final SimpleCommandExceptionType MISSING_NAMESPACE =
            new SimpleCommandExceptionType(new LiteralMessage("Identifier is missing a namespace"));
    private static final SimpleCommandExceptionType MISSING_PATH =
            new SimpleCommandExceptionType(new LiteralMessage("Identifier is missing a path"));

    private IdentifierArgumentType() {}

    public static IdentifierArgumentType id() {
        return new IdentifierArgumentType();
    }

    @Override
    public Identifier parse(StringReader reader) throws CommandSyntaxException {
        String namespace = reader.readUnquotedString();
        if (namespace.isEmpty()) {
            throw MISSING_NAMESPACE.create();
        }

        if (reader.read() != ':') {
            throw MISSING_PATH.create();
        }

        String path = reader.readUnquotedString();

        return new Identifier(namespace, path);
    }

    @Override
    public String toString() {
        return "id";
    }
}
