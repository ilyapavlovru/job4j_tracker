package ru.job4j.tracker;

public class Tiger extends Predator {
    public Tiger() {
        super();
        System.out.println("load Tiger");
    }
    public Tiger(String nm) {
        super(nm);
        System.out.println("load Tiger with String");
    }
}
