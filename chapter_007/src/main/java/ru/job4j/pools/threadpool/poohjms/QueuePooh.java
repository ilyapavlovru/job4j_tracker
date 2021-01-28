package ru.job4j.pools.threadpool.poohjms;

import java.util.concurrent.*;

public class QueuePooh {

    private final BlockingQueue<Message> queue;

    public QueuePooh() {
        this.queue = new LinkedBlockingQueue<>(10);
    }

    // добавить сообщение в очередь сообщений
    public Message add(Message message) {
        try {
            queue.put(message);
            return message;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    // прочитать первое сообщение из очереди сообщений и удалить его из очереди
    public Message take() {
        return queue.poll();
    }
}
