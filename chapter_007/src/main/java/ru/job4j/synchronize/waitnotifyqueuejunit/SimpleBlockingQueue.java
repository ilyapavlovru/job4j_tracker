package ru.job4j.synchronize.waitnotifyqueuejunit;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    /**
     * limit of queue size
     */
    private final int total = 2;

    /**
     * flag is false when producer finished adding elements
     */
    private boolean flag = true;

    public synchronized void off() {
        flag = false;
        notify();
    }

    public synchronized boolean isFlag() {
        System.out.println(Thread.currentThread().getName() + ": Потребитель проверяет значение flag = " + flag);
        return flag;
    }

    /**
     * Add element to queue
     *
     * @param value
     */
    public synchronized void offer(T value) {
        System.out.println(Thread.currentThread().getName() + ": Производитель пытается добавить 1 элемент. " + "Элементов в очереди: " + queue.size());
        while (queue.size() >= total) {
            System.out.println(Thread.currentThread().getName() + ": Много элементов! " + "Элементов в очереди: " + queue.size());
            try {
                System.out.println(Thread.currentThread().getName() + ": Производитель ожидает..." + "Элементов в очереди: " + queue.size());
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.add(value);
        System.out.println(Thread.currentThread().getName() + ": Производитель добавил 1 элемент. " + "Элементов в очереди: " + queue.size());
        notify();
    }

    /**
     * Retrieve and remove the head of the queue
     *
     * @return
     */
    public synchronized T poll() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ": Потребитель пытается извлечь 1 элемент. " + "Элементов в очереди: " + queue.size());
        while (queue.size() < 1) {
            System.out.println(Thread.currentThread().getName() + ": Нет элементов! " + "Элементов в очереди: " + queue.size());
            System.out.println(Thread.currentThread().getName() + ": Потребитель ожидает..." + "Элементов в очереди: " + queue.size());
            wait();
        }
        System.out.println(Thread.currentThread().getName() + ": Потребитель извлек 1 элемент. " + "Элементов в очереди: " + (queue.size() - 1));
        notify();
        return queue.poll();
    }
}
