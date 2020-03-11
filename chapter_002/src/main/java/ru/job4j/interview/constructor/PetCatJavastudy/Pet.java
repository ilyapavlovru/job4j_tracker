package ru.job4j.interview.constructor.PetCatJavastudy;

public class Pet {
    private String name;
    {
        name = "NoName";
    }
    static {
        System.out.println("Static block in Pet");
    }
    {
        System.out.println("First block in Pet");
    }
    {
        System.out.println("Second block in Pet");
    }

    public Pet() {
        System.out.println("Pet empty constructor");
    }

    public Pet(String name) {
        System.out.println("Pet constructor with Name " + name);
        this.name = name;
    }
}

