package ru.job4j.interview.associationpkg.agregation;

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
    public Car(Engine someEngine) {
        this.engine = someEngine;
    }
}

public class Test {
    public static void main(String[] args) {
        Engine goodEngine = new Engine(360);
        Car car = new Car(goodEngine);
    }
}
