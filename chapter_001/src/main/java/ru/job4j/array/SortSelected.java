package ru.job4j.array;

public class SortSelected {
    public static int[] sort(int[] data) {
        for (int i = 0; i < data.length - 2; i++) {
            // ищем min эл-т в массиве для диапазона i .. data.length - 1
            int min = MinDiapason.findMin(data, i, data.length - 1);
            // ищем индекс этого эл-та
            int index = FindLoop.indexOf(data, min, i, data.length - 1);
            // меняем местами эл-т с индексом i и эл-т с индексом index
            int tmp = data[i];
            data[i] = data[index];
            data[index] = tmp;
        }
        return data;
    }
}