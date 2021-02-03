package io.github.cottonmc.jf2.legacy;

import io.github.cottonmc.jsonfactory.output.Output;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

final class Outputs {
    static String toString(Output output) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        output.writeToStream(out);
        return out.toString(StandardCharsets.UTF_8);
    }
}
