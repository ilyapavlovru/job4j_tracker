package ru.job4j.lambda;

import java.util.Arrays;
import java.util.Comparator;

public class FI {
    public static void main(String[] args) {
        Attachment[] atts = {
                new Attachment("image 1*", 20),
                new Attachment("image 3***", 120),
                new Attachment("image 2**", 230)
        };
//        Comparator<Attachment> comparator = new Comparator<Attachment>() {
//            @Override
//            public int compare(Attachment left, Attachment right) {
//                return left.getSize() - right.getSize();
//            }
//        };
//        Arrays.sort(atts, comparator);

        System.out.println("*** Sort asc by size ***");
        Comparator<Attachment> comparator = (left, right) -> left.getSize() - right.getSize();
        Arrays.sort(atts, comparator);
        for (Attachment at : atts) {
            System.out.println(at.toString());
        }

        System.out.println("*** Sort asc by name ***");
        Comparator<Attachment> cmpText = (left, right) -> left.getName().compareTo(right.getName());
        Arrays.sort(atts, cmpText);
        for (Attachment at : atts) {
            System.out.println(at.toString());
        }

        System.out.println("*** Sort asc by name length ***");
        Comparator<Attachment> cmpDescSize = (left, right) -> right.getName().length() - left.getName().length();
        Arrays.sort(atts, cmpDescSize);
        for (Attachment at : atts) {
            System.out.println(at.toString());
        }
    }

}