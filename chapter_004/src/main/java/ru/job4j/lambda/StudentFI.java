package ru.job4j.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class StudentFI {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Sidorov Sidor Sidorovich", "P-124"),
                new Student("Petrov Petr Petrovich", "P-123"),
                new Student("Ivanov Ivan Ivanovich", "P-122")
        );

        // comparator asc by getFio
        Comparator<Student> comparator = new Comparator<Student>() {
            @Override
            public int compare(Student left, Student right) {
                return left.getFio().compareTo(right.getFio());
            }
        };
        students.sort(comparator);
        System.out.println(students);


        Function<Student, Mark> function = new Function<Student, Mark>() {
            @Override
            public Mark apply(Student student) {
                Mark mark = new Mark();
                if (student.getGpa() >= 4.5) {
                    mark.setName("Excellent");
                } else {
                    mark.setName("Another");
                }
                return mark;
            }
        };
        System.out.println(function.apply(students.get(0)));
    }
}
