package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;

public class FIFunctionDemo {
    List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> rsl = new ArrayList<>();
        for (int index = start; index < end; index++) {
            rsl.add(func.apply((double) index));
        }
        return rsl;
    }

    public static void main(String[] args) {
        FIFunctionDemo function = new FIFunctionDemo();
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
        System.out.println(result);
    }
}
