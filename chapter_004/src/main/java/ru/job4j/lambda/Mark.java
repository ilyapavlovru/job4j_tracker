package ru.job4j.lambda;

public class Mark {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Mark{"
                + "name='" + name + '\''
                + '}';
    }
}
