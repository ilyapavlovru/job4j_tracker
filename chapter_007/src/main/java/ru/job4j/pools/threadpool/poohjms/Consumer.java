package ru.job4j.pools.threadpool.poohjms;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public class Consumer implements Callable<Message> {

    //todo final?
    ConcurrentHashMap<String, BlockingQueue<Message>> map ;
    private final String queueName;

    public Consumer(ConcurrentHashMap<String, BlockingQueue<Message>> map, String queueName) {
        this.map = map;
        this.queueName = queueName;
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

        // получаем очередь по имени очереди
        System.out.println("получаем очередь по имени очереди");
        BlockingQueue<Message> queue = map.get(queueName);

        // извлекаем первое сообщение из очереди
        Message message = queue.poll();
        System.out.println("message = " + message);
        System.out.println("[Consumer] Queue name = " + queueName + ", remainingCapacity : " + queue.remainingCapacity());
        Thread.sleep(500);
        return message;
    }
}
