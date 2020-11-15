package ru.job4j.whaitnotifynotifyall.waitnotifyqueuejunit;

import java.util.concurrent.CopyOnWriteArrayList;

public class ParallelSearch {
    public static void main(String[] args) throws InterruptedException {
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>();
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        // потребитель пытается получтить элементы пока значение флага общего ресурса равно true или поток не прерван
        final Thread consumer = new Thread(
                () -> {
                    while (queue.isFlag() || !Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println(Thread.currentThread().getName() + ": Consumer пытается извлечь элемент..");
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        // производитель 3 раза добавляет число в очередь
        final Thread producer = new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        queue.offer(index);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.off();
                }
        );
        consumer.start();
        producer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        System.out.println("Main thread finished...");
    }
}
