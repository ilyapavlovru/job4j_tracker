package ru.job4j.collection;

import java.util.LinkedList;

public class FreezeStr {

    public static boolean eq(String left, String right) {
        boolean rsl = true;

        if (left.length() != right.length()) {
            return false;
        }

        LinkedList<String> leftList = new LinkedList<>();
        String[] leftListSpl = left.split("");
        String[] rightListSpl = right.split("");

        for (String s : leftListSpl) {
            leftList.add(s);
        }

        for (String string : rightListSpl) {
            if (leftList.contains(string)) {
                leftList.remove(string);
            } else {
                rsl = false;
                break;
            }
        }
        return rsl;
    }

    public static void main(String[] args) {
        FreezeStr.eq("Hello", "Hello");
    }
}