package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        String leftStrDigit = left.substring(0, left.indexOf("."));
        String rightStrDigit = right.substring(0, right.indexOf("."));

        int leftDigit = Integer.parseInt(leftStrDigit);
        int rightDigit = Integer.parseInt(rightStrDigit);

        return leftDigit - rightDigit;
    }
}