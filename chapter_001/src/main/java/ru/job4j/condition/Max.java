package ru.job4j.condition;

public class Max {
    public static int max(int left, int right) {
        boolean condition = left > right;
        return condition ? left : right;
    }
    public static int max(int first, int second, int third) {
        return max(
                first,
                max(second, third)
        );
    }
}
