package ru.job4j.interview;

public class Bug extends Item {
    public Bug(String name) {
        //super(name);
        System.out.println("Отработал конструктор класса Bug с параметром name = " + name);
    }
}