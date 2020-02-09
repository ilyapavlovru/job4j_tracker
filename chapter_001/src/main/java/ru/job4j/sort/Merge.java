package ru.job4j.sort;

import java.util.Arrays;

public class Merge {
    public int[] merge(int[] left, int[] right) {
        int[] rsl = new int[left.length + right.length];
        int iLeft = 0; int iRight = 0; int iResult = 0;
        while ((iLeft < left.length) || (iRight < right.length)) {
            if (iLeft == left.length) {
                rsl[iResult++] = right[iRight++];
            } else if (iRight == right.length) {
                rsl[iResult++] = left[iLeft++];
            } else if (left[iLeft] < right[iRight]) {
                rsl[iResult++] = left[iLeft++];
            } else {
                rsl[iResult++] = right[iRight++];
            }
        }
        return rsl;
    }
    public static void main(String[] args) {
        Merge process = new Merge();
        int[] rsl = process.merge(
                new int[] {1, 3, 5},
                new int[] {2, 4}
        );
        System.out.println(Arrays.toString(rsl));
    }
}