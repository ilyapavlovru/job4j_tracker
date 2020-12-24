package ru.job4j.pools.threadpool.forkjoinfindindex;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

import static org.junit.Assert.*;

public class ForkJoinFindIndexTest {

    @Test
    public void whenSearchIndexIs2ThenFindIt() {
        User[] users = {
                new User("Ilya", "ilya@mail.ru"),
                new User("Petr", "petr@mail.ru"),
                new User("Matvey", "matvey@mail.ru"),
                new User("Lena", "lena@mail.ru")};
        int searchIndex = 2;
        ForkJoinTask<Integer> task = new ForkJoinFindIndex<>(users, searchIndex, 0, users.length);
        int res = new ForkJoinPool().invoke(task);
        assertEquals(searchIndex, res);
    }
}