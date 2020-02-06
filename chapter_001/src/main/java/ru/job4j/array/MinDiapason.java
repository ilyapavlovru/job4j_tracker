package ru.job4j.array;

public class MinDiapason {
    public static int findMin(int[] data, int start, int finish) {
        int rst = data[start];
        for (int i = start; i <= finish; i++) {
            if (data[i] < rst) {
                rst = data[i];
            }
        }
        return rst;
    }
}
