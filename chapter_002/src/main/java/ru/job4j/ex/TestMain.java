package ru.job4j.ex;

public class TestMain {
    public static void functionThatChangesParam(String arg) {
        arg = "After";
    }

    public static void main(String[] args) {
        String toChange = "Before";
        System.out.println(toChange);
        functionThatChangesParam(toChange);
        System.out.println(toChange);
    }
}
