package ru.job4j.stream;

import java.util.Objects;

public class Student {
    String surname;
    int score;

    public Student(String surname, int score) {
        this.surname = surname;
        this.score = score;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return score == student.score
                && Objects.equals(surname, student.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname);
    }

    @Override
    public String toString() {
        return "{"
                + "surname='" + surname + '\''
                + ", score=" + score
                + '}';
    }
}
