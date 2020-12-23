package ru.job4j.pools.threadpool.forkjoinfindindex;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinFindIndex extends RecursiveTask<Integer> {

    private final User[] users;
    private final int start;
    private final int end;
    private static final long threshold = 25;
    private final String searchValue;

    public ForkJoinFindIndex(User[] users, String searchValue, int start, int end) {
        this.users = users;
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

        ForkJoinFindIndex firstTask = new ForkJoinFindIndex(users, searchValue, start, start + length / 2);
        firstTask.fork();
        
        ForkJoinFindIndex secondTask = new ForkJoinFindIndex(users, searchValue, start + length / 2, end);

        Integer secondTaskResult = secondTask.compute();
        Integer firstTaskResult = firstTask.join();
        
        return firstTaskResult > 0 ? firstTaskResult : secondTaskResult;
    }

    private Integer find(String searchValue) {
        int result = 0;
        for (int i = start; i < end; i++) {
            if (users[i].getUserName().equals(searchValue)) {
                return i;
            }
        }
        return result;
    }

    public static int startForkJoinFindIndex() {
//        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        long[] numbers = {1, 2, 9, 2, 5, 4, 7, 4, 6};
        User[] users = {
                new User("Ilya", "ilya@mail.ru"),
                new User("Petr", "petr@mail.ru"),
                new User("Matvey", "matvey@mail.ru"),
                new User("Lena", "lena@mail.ru"),
        };
        String searchValue = "Matvey";
        ForkJoinTask<Integer> task = new ForkJoinFindIndex(users, searchValue, 0, users.length);
        return new ForkJoinPool().invoke(task);
    }
}
