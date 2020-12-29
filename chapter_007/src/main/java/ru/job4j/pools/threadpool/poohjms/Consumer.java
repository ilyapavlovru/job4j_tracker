package ru.job4j.pools.threadpool.poohjms;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class Consumer implements Runnable {

    //todo final?
    ConcurrentHashMap<String, BlockingQueue<Message>> map ;
    private final String queueName;

    public Consumer(ConcurrentHashMap<String, BlockingQueue<Message>> map, String queueName) {
        this.map = map;
        this.queueName = queueName;
    }

    @Override
    public void run() {
        try {
            process();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void process() throws InterruptedException {

        // получаем очередь по имени очереди
        BlockingQueue<Message> queue = map.get(queueName);

        // извлекаем первое сообщение из очереди
        Message take = queue.take();

        System.out.println("message = " + take);
        System.out.println("[Consumer] Queue name = " + queueName + ", remainingCapacity : " + queue.remainingCapacity());


        Thread.sleep(500);

    }

}
