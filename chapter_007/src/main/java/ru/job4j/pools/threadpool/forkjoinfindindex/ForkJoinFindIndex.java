package ru.job4j.pools.threadpool.forkjoinfindindex;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinFindIndex extends RecursiveTask<Integer> {

    private final long[] numbers;
    private final int start;
    private final int end;
    private static final long threshold = 25;
    private final long searchValue;

    public ForkJoinFindIndex(long[] numbers, long searchValue, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
        this.searchValue = searchValue;
    }

    @Override
    protected Integer compute() {

        int length = end - start;
        if (length <= threshold) {
            return find(searchValue);
        }

        ForkJoinFindIndex firstTask = new ForkJoinFindIndex(numbers, searchValue, start, start + length / 2);
        firstTask.fork();
        
        ForkJoinFindIndex secondTask = new ForkJoinFindIndex(numbers, searchValue, start + length / 2, end);

        Integer secondTaskResult = secondTask.compute();
        Integer firstTaskResult = firstTask.join();
        
        return firstTaskResult > 0 ? firstTaskResult : secondTaskResult;
    }

    private Integer find(long searchValue) {
        int result = 0;
        for (int i = start; i < end; i++) {
            if (numbers[i] == searchValue) {
                return i;
            }
        }
        return result;
    }

    public static int startForkJoinFindIndex() {
//        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        long[] numbers = {1, 2, 9, 2, 5, 4, 7, 4, 6};
        int searchValue = 9;
        ForkJoinTask<Integer> task = new ForkJoinFindIndex(numbers, searchValue, 0, numbers.length);
        return new ForkJoinPool().invoke(task);
    }
}
