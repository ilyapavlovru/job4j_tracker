package ru.job4j.exam;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CountingApp {

    public static void main(String[] args) {

        // пример подсчета уникальных элементов через stream
        Stream<String> stream = Stream.of("a", "b", "a", "c", "c", "a", "a", "d");
        Map<String, Long> counter2 = stream.collect(Collectors.groupingBy(s -> s, Collectors.counting()));


        int count = counter2.values().size();

        // поиск элемента с максимальной повторяемостью
        Optional<Map.Entry<String, Long>> maxEntry = counter2.entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue));

        String strWithMaxCount = maxEntry.get().getKey();
    }

}
