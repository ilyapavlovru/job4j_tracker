package ru.job4j.pools.threadpool.poohjms;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

public class QProducer implements Callable<Message> {

    private final BlockingQueue<Message> queue;
    private final Message message;

    public QProducer(BlockingQueue<Message> queue, Message message) {
        this.queue = queue;
        this.message = message;
    }

    @Override
    public Message call() {
        Message message = null;
        try {
            message = process();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return message;
    }

    private Message process() throws InterruptedException {
        try {
            queue.put(this.message);
            Thread.sleep(100);
            return this.message;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
