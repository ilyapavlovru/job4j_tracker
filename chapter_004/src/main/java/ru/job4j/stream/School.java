package ru.job4j.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class School {
    public static List<Student> collect(List<Student> students, Predicate<Student> predicate) {
        return students.stream()
                .filter(predicate)
                .collect(Collectors.<Student>toList());
    }

    public static Map<String, Integer> collectStudentsToMap(List<Student> students) {
        return students.stream()
                .distinct()
                .collect(Collectors.toMap(att -> att.getSurname(), att -> att.getScore()));
    }

    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Ivanov", 100),
                new Student("Petrov", 60),
                new Student("Sidorov", 1)
        );
        System.out.println(collect(students, att -> att.getScore() >= 70 & att.getScore() <= 100));

        System.out.println(collectStudentsToMap(students));
    }
}
