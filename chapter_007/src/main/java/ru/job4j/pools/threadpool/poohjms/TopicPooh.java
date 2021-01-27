package ru.job4j.pools.threadpool.poohjms;

import java.util.concurrent.*;

public class TopicPooh {

    private final ExecutorService pool;
    private final ConcurrentHashMap<String, BlockingQueue<Message>> map;

    public TopicPooh() {
        this.pool = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors());
        this.map = new ConcurrentHashMap<>();
    }

    public Message add(Message message) {
        Future<Message> futureTask = pool.submit(new Callable<Message>() {
            @Override
            public Message call() {
                // если очередь с таким именем существует
                map.computeIfPresent(message.getName(), (key, val) -> {
                    BlockingQueue<Message> queue = map.get(message.getName());
                    try {
                        // добавляем новое сообщение в эту очередь
                        queue.put(message);
                        return queue;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return null;
                    }
                });
                // если очереди с таким именем еще нет, то нужно создать новую очередь
                map.computeIfAbsent(message.getName(), key -> {
                    BlockingQueue<Message> queue = new LinkedBlockingQueue<>(10);
                    try {
                        // добавляем первое сообщение в эту новую очередь
                        queue.put(message);
                        return queue;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return null;
                    }
                });
                return message;
            }
        });
        return getFutureTaskResponse(futureTask);
    }

    public Message take(String topicName) {
        Future<Message> futureTask = pool.submit(new Callable<Message>() {
            @Override
            public Message call() {
                BlockingQueue<Message> queue = map.get(topicName);
                // извлекаем первое сообщение из очереди
                if (queue != null) {
                    return queue.poll();
                }
                return null;
            }
        });
        return getFutureTaskResponse(futureTask);
    }

    private Message getFutureTaskResponse(Future<Message> futureTask) {
        while (true) {
            try {
                if (futureTask.isDone()) {
                    return futureTask.get();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
