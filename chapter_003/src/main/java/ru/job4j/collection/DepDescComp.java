package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String firstElInO1;
        if(o1.contains("/")) {
            firstElInO1 = o1.substring(0, o1.indexOf('/'));
        } else {
            firstElInO1 = o1;
        }
        String firstElInO2;
        if(o2.contains("/")) {
            firstElInO2 = o2.substring(0, o2.indexOf('/'));
        } else {
            firstElInO2 = o2;
        }

        if ((!firstElInO1.equals(firstElInO2)) || (!o1.contains("/") && !o2.contains("/"))) {
            return o2.compareTo(o1);
        } else {
            return o1.compareTo(o2);
        }
    }
}