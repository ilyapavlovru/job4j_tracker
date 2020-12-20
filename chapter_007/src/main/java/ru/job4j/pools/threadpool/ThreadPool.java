package ru.job4j.pools.threadpool;

import java.util.LinkedList;
import java.util.List;

class MyTask implements Runnable {

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
        System.out.printf("%s: задача finished... Execution time is %s ms\n", Thread.currentThread().getName(), waitTime);
    }
}

public class ThreadPool {
    private final static List<Thread> threads = new LinkedList<>();
    private final static SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    public void work(Runnable job) {
        tasks.offer(job);
    }

    public void shutdown() {
        tasks.off();
    }

    public static void main(String[] args) throws InterruptedException {

        ThreadPool threadPool = new ThreadPool();
        int size = Runtime.getRuntime().availableProcessors();

        // производитель 5 раз добавляет задачу MyTask в очередь
        final Thread producer = new Thread(
                () -> {
                    for (int index = 0; index != 5; index++) {
                        threadPool.work(new MyTask(5000));
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            System.out.println(Thread.currentThread().getName() + ": Поймали catch потока producer");
                            e.printStackTrace();
                        }
                    }
                    threadPool.shutdown(); // посылаем сигнал очереди, что новых задач больше не будет
                }
        );

        for (int i = 0; i < size; i++) {
            threads.add(new Thread(
                    () -> {
                        System.out.println(Thread.currentThread().getName() + ": Consumer поток запущен");
                        while (tasks.isFlag() || tasks.getQueueSize() > 0) {
                            try {
                                System.out.println(Thread.currentThread().getName() + ": Consumer пытается извлечь элемент..");

                                // получаем очередную задачу из очереди
                                MyTask task = (MyTask) tasks.poll();
                                System.out.println(Thread.currentThread().getName() + ": Consumer поток пробует запустить задачу на выполнение, задача: " + task);
                                if (task != null) {
                                    task.run();
                                }
                                System.out.println(Thread.currentThread().getName() + ": Consumer поток после выполнения метода run");

                            } catch (InterruptedException e) {
                                System.out.println(Thread.currentThread().getName() + ": Consumer поток поймал catch");
                                e.printStackTrace();
                                Thread.currentThread().interrupt();
                            }
                        }
                        System.out.println(Thread.currentThread().getName() + ": Consumer поток завершил работу");
                    }
            ));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        producer.start();
        producer.join();

        for (Thread thread : threads) {
            thread.join();
        }
    }
}
