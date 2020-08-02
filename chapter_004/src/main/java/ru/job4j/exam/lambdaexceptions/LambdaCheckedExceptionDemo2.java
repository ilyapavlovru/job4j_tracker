package ru.job4j.exam.lambdaexceptions;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.function.Function;

/**
 * Если тело лямбда-выражения может бросить проверяемое checked исключение,
 * то вариант 2 - оно должно быть обработано внутри лямбда-выражения с помощью конструкции try-catch-finally
 */
public class LambdaCheckedExceptionDemo2 {
    static Integer writeToFile(String s) throws IOException {
        //logic to write to file which throws IOException
        return 0;
    }

    // wrap Integer writeToFile(String s) in a method that doesn't declare a checked exception
    static Integer myWrappedMethod(String s) {
        try {
            return writeToFile(s);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static void main(String[] args) {
        // usage:
        Function<String, Integer> f = (String t) -> myWrappedMethod(t);

        // or:
        Function<String, Integer> f2 =
                (String s) -> {
                    try {
                        return writeToFile(s);
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                };
    }
}
