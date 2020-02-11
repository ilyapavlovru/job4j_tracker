package ru.job4j.tracker;

public class Engineer extends Profession {
    private int experience;
    public Project make(Instruction instruction) {
        Project project = new Project();
        return project;
    }
}
