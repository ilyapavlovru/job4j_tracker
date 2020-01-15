package ru.job4j.calculator;

/**
 * Класс для вычисления арифметических операций + - * /
 * @author Ilya Pavlov (i.pavlov@list.ru)
 * @since 10.01.2020
 * @version 1
 */

public class Calculator {

    /**
     * Сложение
     * @param first первый аргумент
     * @param second второй аргумент
     */
    public static void add(double first, double second) {
        double result =  first + second;
        System.out.println(first + " + " + second + " = " + result);
    }

    /**
     * Деление
     * @param first первый аргумент
     * @param second второй аргумент
     */
    public static void div(double first, double second) {
        double result =  first / second;
        System.out.println(first + " / " + second + " = " + result);
    }

    /**
     * Умножение
     * @param first первый аргумент
     * @param second второй аргумент
     */
    public static void multiply(double first, double second) {
        double result =  first * second;
        System.out.println(first + " * " + second + " = " + result);
    }

    /**
     * Вычитание
     * @param first первый аргумент
     * @param second второй аргумент
     */
    public static void subtrack(double first, double second) {
        double result =  first - second;
        System.out.println(first + " - " + second + " = " + result);
    }

    /**
     * Main
     * @param args - args
     */
    public static void main(String[] args) {
        add(1, 1);
        div(4, 2);
        multiply(2, 1);
        subtrack(10, 5);
    }
}
