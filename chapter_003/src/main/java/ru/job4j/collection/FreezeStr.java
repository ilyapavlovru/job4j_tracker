package ru.job4j.collection;

import java.util.Collections;
import java.util.LinkedList;

public class FreezeStr {

    // Временная сложность O(n log(n))
    public static boolean eq(String left, String right) {
        boolean rsl = true;

        if (left.length() != right.length()) {
            return false;
        }

        LinkedList<String> leftList = new LinkedList<>();
        LinkedList<String> rightList = new LinkedList<>();
        String[] leftListSpl = left.split("");
        String[] rightListSpl = right.split("");

        for (String s : leftListSpl) {
            leftList.add(s);
        }

        for (String s : rightListSpl) {
            rightList.add(s);
        }

        Collections.sort(leftList);
        Collections.sort(rightList);

        int i = 0;
        for (String string : leftList) {
            if (!string.equals(rightList.get(i++))) {
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