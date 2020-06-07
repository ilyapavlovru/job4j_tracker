package ru.job4j.innovations;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentLevel {
    public static List<Student> levelOf(List<Student> students, int bound) {
        return students.stream()
                .flatMap(Stream::ofNullable)
                .sorted((left, right) -> left.getSurname().compareTo(right.getSurname()))
                .takeWhile(st -> st.getScore() > bound)
                .collect(Collectors.toList());
    }
}