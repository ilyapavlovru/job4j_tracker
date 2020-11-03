package ru.job4j.synchronize.waitnotifyqueue;

import java.util.Queue;

public class Program {
    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue simpleBlockingQueue = new SimpleBlockingQueue<Integer>();
        Thread producerThread = new Thread(new Producer(simpleBlockingQueue), "ProducerThread");
        Thread consumerThread = new Thread(new Consumer(simpleBlockingQueue), "ConsumerThread");
        producerThread.start();
        consumerThread.start();
        producerThread.join();
        consumerThread.join();
        Queue queue = simpleBlockingQueue.getQueue();
        queue.forEach(System.out::println);
    }
}
