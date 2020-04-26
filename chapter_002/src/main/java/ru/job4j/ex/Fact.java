package ru.job4j.ex;

public class Fact {
    public static void main(String[] args) {
        System.out.println("Factorial " + calc(0));
    }

    public static int calc(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n should not be less then 1.");
        }
        int rsl = 1;
        for (int index = 1; index <= n; index++) {
            rsl *= index;
        }
        return rsl;
    }
}