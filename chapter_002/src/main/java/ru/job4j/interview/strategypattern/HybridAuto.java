package ru.job4j.interview.strategypattern;

public class HybridAuto extends Auto {

    public HybridAuto() {
        super(new HybridFillStrategy());
    }
}