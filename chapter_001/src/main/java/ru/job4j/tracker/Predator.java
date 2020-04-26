package ru.job4j.tracker;

public class Predator extends Animal {
    public Predator() {
        super();
        System.out.println("load Predator");
    }

    public Predator(String nm) {
        super(nm);
        System.out.println("load Predator with String");
    }
}
