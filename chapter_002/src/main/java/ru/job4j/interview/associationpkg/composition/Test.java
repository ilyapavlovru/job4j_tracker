package ru.job4j.interview.associationpkg.agregation.composition3;

class Engine {
    int power;
    {
        power = 100;
    }
    public Engine(int p) {
        power = p;
    }
}

class Car {
    String model;
    {
        model = "Audi";
    }
    Engine engine;
    public Car() {
        this.engine = new Engine(360);
    }
}

public class Test {
    public static void main(String[] args) {
        Car car = new Car();
    }
}
