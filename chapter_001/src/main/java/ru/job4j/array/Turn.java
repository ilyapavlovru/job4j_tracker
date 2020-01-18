package ru.job4j.array;

public class Turn {

    public int[] back(int[] array) {

        int len = array.length / 2;

        for (int i = 0; i < len; i++) {
            int temp = array[i];
            array[i] = array[array.length - i  - 1];
            array[array.length - i  - 1] = temp;
        }

        return array;
    }
}