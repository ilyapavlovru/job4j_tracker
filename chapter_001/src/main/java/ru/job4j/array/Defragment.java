package ru.job4j.array;

public class Defragment {

    public static String[] compress(String[] array) {

        for (int index = 0; index < array.length; index++) {

            if (array[index] == null) {
                int point = index; // указатель, на не null ячейку.
                int i = index;
                while (i < array.length) {
                    if (array[i] != null) {
                        array[point] = array[i];
                        array[i] = null;
                        break;
                    }
                    i++;
                }

            }
            System.out.print(array[index] + " ");
        }
        return array;
    }

    public static void main(String[] args) {
        String[] input = {"I", null, "wanna", null, "be", null, "compressed"};
        String[] compressed = compress(input);
        System.out.println();
        for (int index = 0; index < compressed.length; index++) {
            System.out.print(compressed[index] + " ");
        }
    }
}