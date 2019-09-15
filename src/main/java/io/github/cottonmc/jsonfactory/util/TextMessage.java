package io.github.cottonmc.jsonfactory.util;

import com.mojang.brigadier.Message;
import org.cactoos.Text;
import org.cactoos.text.UncheckedText;

public final class TextMessage implements Text, Message {
    private final Text text;
    private final UncheckedText unchecked;

    public TextMessage(Text text) {
        this.text = text;
        this.unchecked = new UncheckedText(text);
    }

    @Override
    public String getString() {
        return unchecked.asString();
    }

    @Override
    public String asString() throws Exception {
        return text.asString();
    }
}
