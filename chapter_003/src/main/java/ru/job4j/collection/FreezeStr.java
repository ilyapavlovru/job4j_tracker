package ru.job4j.collection;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

public class FreezeStr {
    public static boolean eq(String left, String right) {
        boolean rsl = false;

        if (left.length() != right.length()) {
            return false;
        }

        HashSet<String> leftSet = new HashSet<String>();
        HashSet<String> rightSet = new HashSet<String>();
        String[] leftListSpl = left.split("");
        String[] rightListSpl = right.split("");

        int leftBytesSum = 0;
        int rightBytesSum = 0;
        byte[] leftBytes = left.getBytes();
        byte[] rightBytes = right.getBytes();

        for (byte b : leftBytes) {
            leftBytesSum += b;
        }

        for (byte b : rightBytes) {
            rightBytesSum += b;
        }

        for (String s : leftListSpl) {
            leftSet.add(s);
        }

        for (String s : rightListSpl) {
            rightSet.add(s);
        }

        if (leftSet.equals(rightSet)) {
            if (leftBytesSum == rightBytesSum) {
                System.out.println("");
                rsl = true;
            }
        }

        return rsl;
    }

    public static void main(String[] args) {
        FreezeStr.eq("Hello", "Hello");
    }
}