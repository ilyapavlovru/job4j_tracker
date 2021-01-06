package ru.job4j.pools.threadpool.poohjms;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public class TConsumer implements Callable<Message> {

    private final ConcurrentHashMap<String, BlockingQueue<Message>> map;
    private final String topicName;

    public TConsumer(ConcurrentHashMap<String, BlockingQueue<Message>> map, String topicName) {
        this.map = map;
        this.topicName = topicName;
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
            BlockingQueue<Message> queue = map.get(topicName);
            // извлекаем первое сообщение из очереди
            Message message = queue.poll();
            Thread.sleep(500);
            return message;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
