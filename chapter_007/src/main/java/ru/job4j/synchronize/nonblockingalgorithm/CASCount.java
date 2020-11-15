package ru.job4j.synchronize.nonblockingalgorithm;

import java.util.concurrent.atomic.AtomicReference;

public class CASCount<T> {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public CASCount() {
        count.set(0);
    }

    public void increment() {
        Integer value;  // текущее значение
        Integer next;  // следующее значение
        do {
            value = count.get();
            next = value + 1;
        } while (!count.compareAndSet(value, next));
    }

    public int get() {
        return count.get();
    }
}
