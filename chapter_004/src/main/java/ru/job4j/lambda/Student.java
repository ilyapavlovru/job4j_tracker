package ru.job4j.lambda;

import java.util.Date;

public class Student {
    private String fio;
    private String group;
    private Date entranceDate;
    private double gpa; // средний балл

    public Student(String fio, String group) {
        this.fio = fio;
        this.group = group;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Date getEntranceDate() {
        return entranceDate;
    }

    public void setEntranceDate(Date entranceDate) {
        this.entranceDate = entranceDate;
    }

    @Override
    public String toString() {
        return "{"
                + "fio='" + fio + '\''
                + ", group=" + group
                + '}';
    }
}
