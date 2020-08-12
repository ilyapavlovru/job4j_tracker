package ru.job4j.innovations;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FlatIt {
    public static List<Integer> flatten(Iterator<Iterator<Integer>> it) {
        List<Integer> res = StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(it, Spliterator.ORDERED), false)
                .flatMap(x -> {
                            List<Integer> lst = new ArrayList<>();
                            while (x.hasNext()) {
                                lst.add(x.next());
                            }
                            return lst.stream();
                        }
                )
                .collect(Collectors.toList());
        return res;
    }
}