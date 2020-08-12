package ru.job4j.exam.lambdaexceptions;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LambdaCheckedExceptionDemo3 {
    static void writeToFile(Integer integer) throws IOException {
        //logic to write to file which throws IOException
    }
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(3, 9, 7, 0, 10, 20);
        integers.forEach(i -> {
            try {
                writeToFile(i);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
