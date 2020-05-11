package ru.job4j.collection;

import java.util.HashMap;

public class FreezeStr {
    public static boolean eq(String left, String right) {
        boolean rsl = false;

        if (left.length() != right.length()) {
            return false;
        }

        HashMap<String, Integer> leftMap = new HashMap<>();
        HashMap<String, Integer> rightMap = new HashMap<>();
        String[] leftListSpl = left.split("");
        String[] rightListSpl = right.split("");

        for (String s : leftListSpl) {
            if (leftMap.containsKey(s)) {
                Integer value = leftMap.get(s);
                value++;
                leftMap.put(s, value);
            } else {
                leftMap.put(s, 1);
            }
        }

        for (String s : rightListSpl) {
            if (rightMap.containsKey(s)) {
                Integer value = rightMap.get(s);
                value++;
                rightMap.put(s, value);
            } else {
                rightMap.put(s, 1);
            }
        }

        if (leftMap.equals(rightMap)) {
            rsl = true;
        }

        return rsl;
    }

    public static void main(String[] args) {
        FreezeStr.eq("Hello", "Hello");
    }
}