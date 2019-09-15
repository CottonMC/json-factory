package io.github.cottonmc.jsonfactory.util;

import com.mojang.brigadier.Message;
import org.cactoos.Text;

public final class MessageText implements Text, Message {
    private final Message message;

    public MessageText(Message message) {
        this.message = message;
    }

    @Override
    public String getString() {
        return message.getString();
    }

    @Override
    public String asString() {
        return message.getString();
    }
}
