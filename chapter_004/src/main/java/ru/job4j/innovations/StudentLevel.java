package ru.job4j.innovations;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentLevel {
    public static List<Student> levelOf(List<Student> students, int bound) {
        List<Student> rsl = students.stream()
                .flatMap(Stream::ofNullable)
                .sorted((left, right) -> right.getScore() - left.getScore())
                .takeWhile(st -> (st.getScore() > bound))
                .sorted((left, right) -> left.getSurname().compareTo(right.getSurname()))
                .collect(Collectors.toList());
        return rsl;
    }
}