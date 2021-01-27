package ru.job4j.pools.threadpool.poohjms;

import java.util.concurrent.*;

public class QueuePooh {

    private final ExecutorService pool;
    private final BlockingQueue<Message> queue;

    public QueuePooh() {
        this.pool = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors());
        this.queue = new LinkedBlockingQueue<>(10);
    }

    // добавить сообщение в очередь сообщений
    public Message add(Message message) {
        Future<Message> futureTask = pool.submit(new Callable<Message>() {
            @Override
            public Message call() {
                try {
                    queue.put(message);
                    return message;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
        return getFutureTaskResponse(futureTask);
    }

    // прочитать первое сообщение из очереди сообщений и удалить его из очереди
    public Message take() {
        Future<Message> futureTask = pool.submit(new Callable<Message>() {
            @Override
            public Message call() {
                return queue.poll();
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
