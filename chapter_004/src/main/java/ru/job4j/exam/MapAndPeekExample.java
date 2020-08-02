package ru.job4j.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapAndPeekExample {

    public static void method1 () {
        List<Integer> list = new ArrayList<>();

        List<Integer> result = Stream.of(1, 2, 3, 4)
                .peek(x -> list.add(x))
                .map(x -> x * 2)
                .collect(Collectors.toList());

        System.out.println(list);
        System.out.println(result);
    }

    public static void method2 () {
        List<Integer> list = new ArrayList<>();

        List<Integer> result = Stream.of(1, 2, 3, 4)
                .peek(x -> list.add(x))
                .map(x -> x * 2)
                .filter(x -> x > 8) // you have inserted a filter here
                .collect(Collectors.toList());

        System.out.println(list);
        System.out.println(result);
    }

    public static void method3 () {
        List<Integer> list = new ArrayList<>();

        long howMany = Stream.of(1, 2, 3, 4)
                .peek(x -> list.add(x))
                .count();

        System.out.println(list);
        System.out.println(howMany);
    }

    public static void main(String[] args) {

        System.out.println("Test#1");
        method1();

        System.out.println("Test#2");
        method2();

        System.out.println("Test#3");
        method3();
    }
}
