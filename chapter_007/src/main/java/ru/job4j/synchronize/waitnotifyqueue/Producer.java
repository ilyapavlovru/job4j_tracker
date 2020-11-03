package ru.job4j.synchronize.waitnotifyqueue;

// Класс Производитель
public class Producer implements Runnable {
    SimpleBlockingQueue simpleBlockingQueue;

    public Producer(SimpleBlockingQueue simpleBlockingQueue) {
        this.simpleBlockingQueue = simpleBlockingQueue;
    }

    @Override
    public void run() {
        for (int i = 1; i < 6; i++) {
            System.out.println(Thread.currentThread().getName() + ": Producer пытается добавить элемент со значением i = " + i);
            simpleBlockingQueue.offer(i);
        }
    }
}
