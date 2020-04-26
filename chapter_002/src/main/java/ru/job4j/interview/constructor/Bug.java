package ru.job4j.interview.constructor;

public class Bug extends Item {
    public Bug(String name) {
        super(name);  // если раскомментировать, то вызовется конструктор с параметром, иначе вызовется конструктор без параметров (конструктор по умолчанию)
        System.out.println("Отработал конструктор класса Bug с параметром name = " + name);
    }
    public void say() {
        System.out.println("Hello");
    }
}