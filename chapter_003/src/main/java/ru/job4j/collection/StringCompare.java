package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int len1 = left.length();
        int len2 = right.length();
        int min = Math.min(len1, len2);

        int k = 0;
        while (k < min) {
            char c1 = left.charAt(k);
            char c2 = right.charAt(k);
            if (c1 != c2) {
                return c1 - c2;
            }
            k++;
        }
        return len1 - len2;
    }
}