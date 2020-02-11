package ru.job4j.pojo;

import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setFio("Ivanov Ivan Ivanovich");
        student.setGroup("P122");
        student.setEntranceDate(new Date());

        System.out.println(student.getFio() + " entered " + student.getGroup() + " : " + student.getEntranceDate());
    }
}
