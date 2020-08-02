package ru.job4j.exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExamples {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, -1, 0, 1, 2, 3);
        List<Integer> rst = list.stream()
                .filter(x -> x > 0)
                .collect(Collectors.toList());
        System.out.println(rst);
    }
}
