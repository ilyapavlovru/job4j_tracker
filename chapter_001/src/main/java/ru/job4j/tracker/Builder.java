package ru.job4j.tracker;

public class Builder extends Engineer {
    private String buildingSkill;
    public House buildHouse(Instruction instruction) {
        House house = new House();
        return house;
    }
}
