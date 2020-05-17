package ru.job4j.lambda;

public class StudentTest {

    public static void changeStudent(Student student) {
        student.setFio("Pavlov Ilya Alekseevich");
    }

    public static void main(String[] args) {
        Student student = new Student("Ivanov Ivan Ivanovich", "P-122");
        changeStudent(student);
        System.out.println(student);
    }
}
