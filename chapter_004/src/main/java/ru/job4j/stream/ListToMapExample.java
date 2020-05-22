package ru.job4j.stream;

import java.util.List;
import java.util.stream.Collectors;

public class ListToMapExample {
    public static void main(String[] args) {
        System.out.println(
                List.of(1, 1, 2, 2).stream().distinct().collect(
                        Collectors.toMap(
                                e -> e,
                                e -> e * e
                        ))
        );
    }
}
