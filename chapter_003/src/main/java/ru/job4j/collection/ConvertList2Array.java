package ru.job4j.collection;

import java.util.List;

public class ConvertList2Array {
    // cells - количество столбцов
    // groups - количество строк
    public static int[][] toArray(List<Integer> list, int cells) {
        int groups = (int) Math.ceil((double) list.size() / cells);
        int[][] array = new int[groups][cells];
        int row = 0, cell = 0, i = 0;
        for (Integer num : list) {
            cell = i++ % cells;
            array[row][cell] = num;
            if (cell == cells - 1) {
                row++;
            }
        }
        return array;
    }


    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7);
        int[][] rsl = toArray(list, 3);
        for (int[] row : rsl) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}