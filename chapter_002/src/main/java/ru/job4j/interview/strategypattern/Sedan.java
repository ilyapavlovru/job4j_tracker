package ru.job4j.interview.strategypattern;

public class Sedan extends Auto {

    public Sedan() {
        super(new StandartFillStrategy());
    }
}