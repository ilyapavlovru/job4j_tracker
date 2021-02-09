package ru.job4j.pools.threadpool.poohjms;

import java.util.concurrent.*;

public class QueuePooh {

    private final ConcurrentHashMap<String, BlockingQueue<Message>> map;

    public QueuePooh() {
        this.map = new ConcurrentHashMap<>();
    }

    public Message add(Message message) {
        map.putIfAbsent(message.getName(), new LinkedBlockingQueue<>(10));
        BlockingQueue<Message> topicQueue = map.get(message.getName());
        topicQueue.add(message);
        return message;
    }

    public Message take(String topicName) {
        return map.getOrDefault(topicName, null).poll();
    }
}
