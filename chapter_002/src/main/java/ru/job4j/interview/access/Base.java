package ru.job4j.interview.access;

public class Base {
    String name = "Default Name";

}

class Derived extends Base {
    public void say() {
        System.out.println(name);
        System.out.println("Hello from Derived");
    }
}

class AnoterClass {
    String name;
    void say() {
        System.out.println();
    }
}

class Test {
    public static void main(String[] args) {
        Base derived = new Derived();


    }
}
