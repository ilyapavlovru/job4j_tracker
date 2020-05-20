package ru.job4j.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class School {
    public static Predicate<Student> isScoreMoreThan70AndLessThan100() {
        return att -> att.getScore() >= 70 & att.getScore() <= 100;
    }

    public static Predicate<Student> isScoreMoreThan50AndLessThan70() {
        return att -> att.getScore() >= 50 & att.getScore() < 70;
    }

    public static Predicate<Student> isScoreMoreThan0AndLessThan50() {
        return att -> att.getScore() > 0 & att.getScore() < 50;
    }

    public static List<Student> collect(List<Student> students, Predicate<Student> predicate) {
        return students.stream()
                .filter(predicate)
                .collect(Collectors.<Student>toList());
    }

    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Ivanov", 100),
                new Student("Petrov", 60),
                new Student("Sidorov", 1)
        );
        System.out.println(collect(students, isScoreMoreThan70AndLessThan100()));
    }
}
