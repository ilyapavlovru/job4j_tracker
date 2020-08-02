package ru.job4j.exam.lambdaexceptions;

import java.io.IOException;

/**
 * Если тело лямбда-выражения может бросить проверяемое checked исключение,
 * то вариант 1 - оно должно быть объявлено в абстрактном методе целевого функционального интерфейса в выражении throws.
 */

@FunctionalInterface
interface CheckedFunction<T, R> {
    R apply(T t) throws IOException;
}

public class LambdaCheckedExceptionDemo {
    public static void main(String[] args) throws IOException {
        CheckedFunction<String, Integer> func = s -> s.length();
        System.out.println(func.apply("Hello")); // 5
    }
}
