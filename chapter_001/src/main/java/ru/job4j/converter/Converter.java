package ru.job4j.converter;

public class Converter {
    public static int rubleToEuro(int value) {
        return value / 70;
    }
    public static int rubleToDollar(int value) {
        return value / 60;
    }
    public static int euroToRuble(int value) {
        return value * 70;
    }
    public static int dollarToRuble(int value) {
        return value * 60;
    }
    public static void main(String[] args) {
        int euro = rubleToEuro(140);
        System.out.println("140 rubles are " + euro + " euro.");
        int dollar = rubleToDollar(120);
        System.out.println("120 rubles are " + dollar + " dollar.");
        int ruble1 = euroToRuble(1);
        System.out.println("1 euro is " + ruble1 + " rubles.");
        int ruble2 = dollarToRuble(1);
        System.out.println("1 dollar is " + ruble2 + " rubles.");
        int in = 140;
        int expected = 2;
        int out = rubleToEuro(in);
        boolean passed = expected == out;
        System.out.println("140 rubles are 2 euro. Test result : " + passed);
        in = 120;
        expected = 2;
        out = rubleToDollar(in);
        passed = expected == out;
        System.out.println("120 rubles are 2 dollars. Test result : " + passed);
        in = 1;  // евро
        expected = 70; // рублей
        out = euroToRuble(in); // рублей
        passed = expected == out;
        System.out.println("1 euro is 70 rubles. Test result : " + passed);
        in = 1;  // доллар
        expected = 60; // рублей
        out = dollarToRuble(in); // рублей
        passed = expected == out;
        System.out.println("1 dollar is 60 rubles. Test result : " + passed);
    }
}
