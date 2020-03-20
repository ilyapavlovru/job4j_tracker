package ru.job4j.interview.constructor.petcatjavastudy;

public class Cat extends Pet {

    private String name;
    static {
        System.out.println("Static block in Cat");
    }
    {
        System.out.println("First block in Cat");
    }
    {
        System.out.println("Second block in Cat");
    }

    public Cat() {
        System.out.println("Cat empty constructor");
    }

    public Cat(String name) {
        super(name); // without this will call super(). Если эту строчку убрать, то будет вызван конструктор Pet();
        System.out.println("Cat constructor with Name: " + name);
        this.name = name;
    }

}