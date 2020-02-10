package ru.job4j.tracker;

public class Animal {
    public Animal() {
        super();
        System.out.println("load Animal");
    }
    public static void main(String[] args) {
        Animal animal = new Animal();
        Predator predator = new Predator();
        Tiger tiger = new Tiger();
    }
}
