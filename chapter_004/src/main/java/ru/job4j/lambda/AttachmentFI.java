package ru.job4j.lambda;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class AttachmentFI {
    public static void raw(List<Attachment> list, Function<Attachment, InputStream> func) {
        for (Attachment att : list) {
            Function<Attachment, InputStream> function = new Function<Attachment, InputStream>() {
                @Override
                public InputStream apply(Attachment attachment) {
                    String name = attachment.getName();
                    InputStream stream = new ByteArrayInputStream(name.getBytes(StandardCharsets.UTF_8));
                    return stream;
                }
            };
            func.apply(att);
        }
    }
}
