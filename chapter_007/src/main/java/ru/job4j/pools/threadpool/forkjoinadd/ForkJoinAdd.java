package ru.job4j.pools.threadpool.forkjoinadd;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinAdd extends RecursiveTask<Long> {

    private final long[] numbers;
    private final int start;
    private final int end;
    private static final long THRESHOLD = 25;

    public ForkJoinAdd(long[] numbers, int start, int end) {
        System.out.println(Thread.currentThread().getName() + " Внутри конструктора, start: " + start + " end: " + end);
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {

        System.out.println(Thread.currentThread().getName() + " Текущие значения start: " + start + " end: " + end);
        int length = end - start;
        System.out.println(Thread.currentThread().getName() + " Длина length: " + length + " threshold = " + THRESHOLD);
        if (length <= THRESHOLD) {
            System.out.println(Thread.currentThread().getName() + " Условие length <= threshold ВЫПОЛНЕНО, вызываем метод add");
            return add();
        }
        System.out.println(Thread.currentThread().getName() + " Условие length <= threshold НЕ выполнено");

        System.out.println(Thread.currentThread().getName() + " Создаем firstTask с параметрами: " + start + " " + (start + length / 2));
        ForkJoinAdd firstTask = new ForkJoinAdd(numbers, start, start + length / 2);
        System.out.println(Thread.currentThread().getName() + " Запуск firstTask.fork()");
        firstTask.fork();  // start asynchronously
        System.out.println(Thread.currentThread().getName() + " Создаем secondTask с параметрами: " + (start + length / 2) + " " + end);
        ForkJoinAdd secondTask = new ForkJoinAdd(numbers, start + length / 2, end);

        System.out.println(Thread.currentThread().getName() + " Вызываем метод secondTask.compute()");
        Long secondTaskResult = secondTask.compute();
        System.out.println(Thread.currentThread().getName() + " Вызываем метод firstTask.join()");
        Long firstTaskResult = firstTask.join();

        System.out.println(Thread.currentThread().getName() + " Вызваем return firstTaskResult + secondTaskResult: значение firstTaskResult: " + firstTaskResult + " secondTaskResult: " + secondTaskResult);
        return firstTaskResult + secondTaskResult;
    }

    private long add() {
        System.out.println(Thread.currentThread().getName() + " Внутри add для start: " + start + " end: " + end);
        long result = 0;
        for (int i = start; i < end; i++) {
            result += numbers[i];
        }
        System.out.println(Thread.currentThread().getName() + " Завершено add для start: " + start + " end: " + end + " result = " + result);
        return result;
    }

    public static long startForkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinAdd(numbers, 0, numbers.length);
        return new ForkJoinPool().invoke(task);
    }
}
