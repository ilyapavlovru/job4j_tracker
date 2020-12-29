package ru.job4j.pools.threadpool.poohjms;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    private final BlockingQueue<Message> queue;
    private final Message message;

    public Producer(BlockingQueue<Message> queue, Message message) {
        this.queue = queue;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            process();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void process() throws InterruptedException {
        System.out.println("[Producer] Put : " + message);
        queue.put(this.message);
        System.out.println("[Producer] Queue remainingCapacity : " + queue.remainingCapacity());
        Thread.sleep(100);
    }
}
