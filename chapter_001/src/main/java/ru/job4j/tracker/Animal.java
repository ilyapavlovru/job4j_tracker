package ru.job4j.tracker;

public class Animal {

    private String name;

    public Animal() {
        super();
        System.out.println("load Animal");
    }
    public Animal(String nm) {
        this.name = nm;
        System.out.println("load Animal with name " + this.name);
    }
    public static void main(String[] args) {
        Animal animal = new Animal();
        Predator predator = new Predator();
        Tiger tiger = new Tiger();

        Animal animal2 = new Animal("Animal");
        Predator predator2 = new Predator("Predator");
        Tiger tiger2 = new Tiger("Tiger");
    }
}
