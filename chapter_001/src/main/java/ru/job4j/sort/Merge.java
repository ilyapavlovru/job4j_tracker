package ru.job4j.sort;

import java.util.Arrays;

public class Merge {
    public int[] merge(int[] left, int[] right) {
        int[] rsl = new int[left.length + right.length];
        int i = 0; int j = 0; int k = 0;
        while ((i < left.length) || (j < right.length)) {
            if (i == left.length) {
                rsl[k] = right[j];
                k++;
                j++;
            } else if (j == right.length) {
                rsl[k] = left[i];
                k++;
                i++;
            } else if (left[i] < right[j]) {
                rsl[k] = left[i];
                k++;
                i++;
            } else {
                rsl[k] = right[j];
                k++;
                j++;
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