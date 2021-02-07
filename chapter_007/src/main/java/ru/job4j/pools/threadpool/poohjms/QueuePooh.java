package ru.job4j.pools.threadpool.poohjms;

import java.util.concurrent.*;

public class QueuePooh {

    private final ConcurrentHashMap<String, BlockingQueue<Message>> map;

    public QueuePooh() {
        this.map = new ConcurrentHashMap<>();
    }

    public Message add(Message message) {
        map.computeIfPresent(message.getName(), (key, val) -> {
            BlockingQueue<Message> queue = map.get(message.getName());
            try {
                queue.put(message);
                return queue;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        });
        map.computeIfAbsent(message.getName(), key -> {
            BlockingQueue<Message> queue = new LinkedBlockingQueue<>(10);
            try {
                queue.put(message);
                return queue;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        });
        return message;
    }

    public Message take(String topicName) {
        BlockingQueue<Message> queue = map.get(topicName);
        if (queue != null) {
            return queue.poll();
        }
        return null;
    }
}
