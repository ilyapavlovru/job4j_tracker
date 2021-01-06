package ru.job4j.pools.threadpool.poohjms;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public class QConsumer implements Callable<Message> {

    //todo final?
    BlockingQueue<Message> queue;

    public QConsumer(BlockingQueue<Message> queue) {
        this.queue = queue;
    }


    @Override
    public Message call() throws Exception {
        Message message = null;
        try {
            message = process();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return message;
    }

    private Message process() throws InterruptedException {

        // извлекаем первое сообщение из очереди
        Message message = queue.poll();
        System.out.println("message = " + message);
        System.out.println("[Consumer] Poll, remainingCapacity : " + queue.remainingCapacity());
        Thread.sleep(500);
        return message;
    }
}
