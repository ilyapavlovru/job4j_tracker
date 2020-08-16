package ru.job4j.innovations;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FlatIt {
    public static List<Integer> flatten(Iterator<Iterator<Integer>> it) {
        List<Integer> res = StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(it, Spliterator.ORDERED), false)
                .flatMap(x ->
                        StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(x, Spliterator.ORDERED), false)
                )
                .collect(Collectors.toList());

        return res;
    }
}