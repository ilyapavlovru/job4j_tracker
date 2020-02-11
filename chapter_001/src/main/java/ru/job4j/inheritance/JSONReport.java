package ru.job4j.inheritance;

public class JSONReport extends TextReport {
    public String generate(String name, String body) {
        return "{" +
                System.lineSeparator() + System.lineSeparator() +
                "\tname : " + name + "," +
                System.lineSeparator() +
                "\tbody : " + body +
                System.lineSeparator() + System.lineSeparator() +
                "}";
    }
}
