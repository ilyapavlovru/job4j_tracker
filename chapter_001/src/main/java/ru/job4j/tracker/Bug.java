package ru.job4j.tracker;

public class Bug extends Item {
    public Bug(String name) {
        super(); // конструктор наследник должен вызывать любой из конструкторов родителя
        System.out.println("load Bug");
    }
}