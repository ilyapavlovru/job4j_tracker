package ru.job4j.whaitnotifynotifyall.waitnotifyqueue;

public class Consumer implements Runnable {

    SimpleBlockingQueue simpleBlockingQueue;

    public Consumer(SimpleBlockingQueue simpleBlockingQueue) {
        this.simpleBlockingQueue = simpleBlockingQueue;
    }

    @Override
    public void run() {
        for (int i = 1; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + ": Consumer пытается извлечь элемент со значением i = " + i);
            simpleBlockingQueue.poll();
        }
    }
}
