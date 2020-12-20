package ru.job4j.pools.threadpool;

import ru.job4j.whaitnotifynotifyall.waitnotifyqueuejunit.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

class MyTask implements Runnable {

    private boolean isDone;

    private final long waitTime;

    public MyTask(int timeInMillis) {
        this.waitTime = timeInMillis;
    }

    @Override
    public void run() {

        System.out.printf("%s: задача started... \n", Thread.currentThread().getName());
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + ": Поймали catch потока MyTask");
            Thread.currentThread().interrupt();
        }
        System.out.printf("%s задача finished... Execution time is %s ms\n", Thread.currentThread().getName(), waitTime);
        isDone = true;
    }

    boolean isDone() {
        return isDone;
    }
}

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    public void work(Runnable job) {
        tasks.offer(job);
    }

    public void shutdown() {
        tasks.off();
    }

    public static void main(String[] args) throws InterruptedException {

        ThreadPool threadPool = new ThreadPool();
//
//        // закидываем 10 задач в очередь задач
//        for (int i = 0; i < 10; i++) {
//            threadPool.work(new MyTask());
//        }


        // производитель 3 раза добавляет задачу MyTask в очередь
        final Thread producer = new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        threadPool.tasks.offer(new MyTask(5000));
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            System.out.println(Thread.currentThread().getName() + ": Поймали catch потока producer");
                            e.printStackTrace();
                        }
                    }
                    threadPool.tasks.off(); // посылаем сигнал очереди, что новых задач больше не будет
                }
        );


        // запускаем 1-й поток потребитель/выполнитель задач
        final Thread consumer = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + ": Consumer1 поток запущен");
                    while (threadPool.tasks.isFlag() || threadPool.tasks.getQueueSize() > 0) {
                        try {
                            System.out.println(Thread.currentThread().getName() + ": Consumer1 пытается извлечь элемент..");

                            // получаем очередную задачу из очереди
                            MyTask task = (MyTask) threadPool.tasks.poll();
                            System.out.println(Thread.currentThread().getName() + ": Consumer1 поток запускает задачу на выполнение, задача: " + task);
                            task.run();
                            System.out.println(Thread.currentThread().getName() + ": Consumer1 поток после выполнения метода run");

                        } catch (InterruptedException e) {
                            System.out.println(Thread.currentThread().getName() + ": Consumer1 поток поймал catch");
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );

        // запускаем 2-й поток потребитель/выполнитель задач
        final Thread consumer2 = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + ": Consumer2 поток запущен");
                    while (threadPool.tasks.isFlag() || threadPool.tasks.getQueueSize() > 0) {
                        try {
                            System.out.println(Thread.currentThread().getName() + ": Consumer2 пытается извлечь элемент..");
                            // получаем очередную задачу из очереди
                            MyTask task = (MyTask) threadPool.tasks.poll();
                            System.out.println(Thread.currentThread().getName() + ": Consumer2 поток запускает задачу на выполнение, задача: " + task);
                            task.run();
                            System.out.println(Thread.currentThread().getName() + ": Consumer2 поток после выполнения метода run");
                        } catch (InterruptedException e) {
                            System.out.println(Thread.currentThread().getName() + ": Consumer2 поток поймал catch");
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );

        consumer.start();
        consumer2.start();
        producer.start();
        producer.join();
        consumer.join();
        consumer2.join();
    }
}
