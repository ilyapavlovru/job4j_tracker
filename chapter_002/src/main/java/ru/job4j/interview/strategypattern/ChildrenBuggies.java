package ru.job4j.interview.strategypattern;

public class ChildrenBuggies extends Auto {

    public ChildrenBuggies() {
        super(new HybridFillStrategy());
    }
}