package ru.job4j.pools.threadpool;

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
        return flag;
    }

    /**
     * Add element to queue
     *
     * @param value
     */
    public synchronized void offer(T value) {
        while (queue.size() >= total) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.add(value);
        notify();
    }

    /**
     * Retrieve and remove the head of the queue
     *
     * @return
     */
    public synchronized T poll() throws InterruptedException {
        while (queue.size() < 1 && flag) {
            wait();
        }
        notify();
        return queue.poll();
    }

    public synchronized int getQueueSize() {
        return queue.size();
    }
}
