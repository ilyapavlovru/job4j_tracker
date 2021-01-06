package ru.job4j.pools.threadpool.poohjms;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class TProducer implements Callable<Message> {

    private final ConcurrentHashMap<String, BlockingQueue<Message>> map;
    private final Message message;

    public TProducer(ConcurrentHashMap<String, BlockingQueue<Message>> map, Message message) {
        this.map = map;
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
            // если очередь с таким именем существует
            map.computeIfPresent(message.getName(), (key, val) -> {
                BlockingQueue<Message> queue = map.get(message.getName());
                try {
                    // добавляем новое сообщение в эту очередь
                    queue.put(this.message);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return queue;
            });

            // если очереди с таким именем еще нет, то нужно создать новую очередь
            map.computeIfAbsent(message.getName(), key -> {
                BlockingQueue<Message> queue = new LinkedBlockingQueue<>(10);
                try {
                    // добавляем первое сообщение в эту новую очередь
                    queue.put(this.message);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return queue;
            });
            return message;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
