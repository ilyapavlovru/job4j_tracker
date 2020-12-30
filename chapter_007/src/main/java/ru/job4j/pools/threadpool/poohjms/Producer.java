package ru.job4j.pools.threadpool.poohjms;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class Producer implements Runnable {

    ConcurrentHashMap<String, BlockingQueue<Message>> map;
    private final Message message;

    public Producer(ConcurrentHashMap<String, BlockingQueue<Message>> map, Message message) {
        this.map = map;
        this.message = message;
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

        System.out.println("[Producer] Put : " + message);

        // если очередь с таким именем существует
        map.computeIfPresent(message.topic, (key, val) -> {
            BlockingQueue<Message> queue = map.get(message.topic);
            try {
                // добавляем новое сообщение в эту очередь
                queue.put(this.message);
                System.out.println("[Producer] Queue name = " + message.topic + ", remainingCapacity : " + queue.remainingCapacity());
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return queue;
        });


        // если очереди с таким именем еще нет, то нужно создать новую очередь
        map.computeIfAbsent(message.topic, key -> {
            BlockingQueue<Message> queue = new LinkedBlockingQueue<>(10);
            try {
                // добавляем первое сообщение в эту новую очередь
                queue.put(this.message);
                System.out.println("[Producer] New queue created, name = " + message.topic + ", remainingCapacity : " + queue.remainingCapacity());
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return queue;
        });

        System.out.println("*********************************************");
    }
}
