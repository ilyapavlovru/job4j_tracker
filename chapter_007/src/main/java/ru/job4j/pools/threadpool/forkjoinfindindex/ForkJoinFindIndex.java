package ru.job4j.pools.threadpool.forkjoinfindindex;

import java.util.concurrent.RecursiveTask;

public class ForkJoinFindIndex<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final int start;
    private final int end;
    private static final long threshold = 10_000;
    private final int searchIndex;

    public ForkJoinFindIndex(T[] array, int searchIndex, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.searchIndex = searchIndex;
    }

    @Override
    protected Integer compute() {

        if (array.length <= 10) {
            return find(searchIndex);
        }

        int length = end - start;
        if (length <= threshold) {
            return find(searchIndex);
        }

        ForkJoinFindIndex firstTask = new ForkJoinFindIndex<T>(array, searchIndex, start, start + length / 2);
        firstTask.fork();
        
        ForkJoinFindIndex secondTask = new ForkJoinFindIndex<T>(array, searchIndex, start + length / 2, end);

        Integer secondTaskResult = secondTask.compute();
        Integer firstTaskResult = (Integer) firstTask.join();
        
        return firstTaskResult > 0 ? firstTaskResult : secondTaskResult;
    }

    private Integer find(int searchIndex) {
        for (int i = start; i < end; i++) {
            if (i == searchIndex) {
                return i;
            }
        }
        return 0;
    }
}
