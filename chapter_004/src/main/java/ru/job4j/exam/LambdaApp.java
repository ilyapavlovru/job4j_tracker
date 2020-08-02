package ru.job4j.exam;

import java.util.function.Function;

public class LambdaApp {
    public static void main(String[] args) {
        Function<Integer, String> convert = x -> String.valueOf(x) + " долларов";
        System.out.println(convert.apply(5)); // 5 долларов
    }
}
