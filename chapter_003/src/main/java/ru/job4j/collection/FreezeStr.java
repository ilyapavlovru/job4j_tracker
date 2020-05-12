package ru.job4j.collection;

import java.util.HashMap;

public class FreezeStr {
    public static boolean eq(String left, String right) {
        boolean rsl = false;
        if (left.length() != right.length()) {
            return false;
        }
        HashMap<Character, Integer> leftMap = new HashMap<>();
        HashMap<Character, Integer> rightMap = new HashMap<>();
        char[] leftListSpl = left.toCharArray();
        char[] rightListSpl = right.toCharArray();
        for (char c : leftListSpl) {
            if (leftMap.putIfAbsent(c, 1) != null) {
                leftMap.computeIfPresent(c, (key, val) -> val + 1);
            }
        }
        for (char c : rightListSpl) {
            if (rightMap.putIfAbsent(c, 1) != null) {
                rightMap.computeIfPresent(c, (key, val) -> val + 1);
            }
            if (leftMap.get(c) == null) {
                return false;
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