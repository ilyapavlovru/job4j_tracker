package ru.job4j.interview.strategypattern;

public class F1Car extends Auto {

    public F1Car() {
        super(new F1PitstopStrategy());
    }
}