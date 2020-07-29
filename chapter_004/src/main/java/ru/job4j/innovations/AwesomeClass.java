package ru.job4j.innovations;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Пример функции высшего порядка
 */
public class AwesomeClass {
    public static void main(String[] args) {
        Function<Integer, Integer> f = x -> x + 3;
        BiFunction<Function<Integer, Integer>, Integer, Integer> g = (func, x) -> func.apply(x) * func.apply(x);
        System.out.println(g.apply(f, 7));
    }
}
