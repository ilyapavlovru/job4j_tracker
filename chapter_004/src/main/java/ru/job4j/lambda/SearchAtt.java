package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SearchAtt {

    public static List<Attachment> filterSize(List<Attachment> list) {
        List<Attachment> rsl = new ArrayList<>();
        for (Attachment att : list) {
            if (att.getSize() > 100) {
                rsl.add(att);
            }
        }
        return rsl;
    }

    public static List<Attachment> filterName(List<Attachment> list) {
        List<Attachment> rsl = new ArrayList<>();
        for (Attachment att : list) {
            if (att.getName().contains("bug")) {
                rsl.add(att);
            }
        }
        return rsl;
    }

    public static Predicate<Attachment> isSizeMoreThan100() {
        return att -> att.getSize() > 100;
    }

    public static Predicate<Attachment> isNameContainsBug() {
        return att -> att.getName().contains("bug");
    }

    private static List<Attachment> filterAttachmentsViaStream(List<Attachment> attachments,
                                                               Predicate<Attachment> predicate) {
        return attachments.stream()
                .filter(predicate)
                .collect(Collectors.<Attachment>toList());
    }

    private static List<Attachment> filterAttachmentsViaForeach(List<Attachment> attachments,
                                                                Predicate<Attachment> predicate) {
        List<Attachment> rsl = new ArrayList<>();
        for (Attachment att : attachments) {
            if (predicate.test(att)) {
                rsl.add(att);
            }
        }
        return rsl;
    }

    public static void main(String[] args) {
        List<Attachment> attachments = Arrays.asList(
                new Attachment("image 1", 250),
                new Attachment("bug01", 105),
                new Attachment("bug02", 34)
        );
        System.out.println(filterAttachmentsViaForeach(attachments, isSizeMoreThan100()));
        System.out.println(filterAttachmentsViaForeach(attachments, isNameContainsBug()));
    }
}