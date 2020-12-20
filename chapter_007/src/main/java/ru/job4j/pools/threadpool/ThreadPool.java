package ru.job4j.pools.threadpool;

import ru.job4j.whaitnotifynotifyall.waitnotifyqueuejunit.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

class MyTask implements Runnable {

    private boolean isDone;

    @Override
    public void run() {

        System.out.printf("%s: задача started... \n", Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + ": Поймали catch потока MyTask");
            Thread.currentThread().interrupt();
        }
        System.out.printf("%s: задача finished... \n", Thread.currentThread().getName());
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
                        threadPool.tasks.offer(new MyTask());
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            System.out.println(Thread.currentThread().getName() + ": Поймали catch потока producer");
                            e.printStackTrace();
                        }
                    }
                    threadPool.tasks.off();
                }
        );



        // запускаем поток потребитель/выполнитель задач
        final Thread consumer = new Thread(
                () -> {
                    while (threadPool.tasks.isFlag() || threadPool.tasks.getQueueSize() > 0) {
                        try {
                            System.out.println(Thread.currentThread().getName() + ": Consumer пытается извлечь элемент..");

                            // получаем очередную задачу из очереди
                            MyTask task = (MyTask) threadPool.tasks.poll();
                            System.out.println(Thread.currentThread().getName() + ": Consumer поток запускает задачу на выполнение, задача: " + task);
                            task.run();
                            System.out.println(Thread.currentThread().getName() + ": Consumer поток после выполнения метода run");

                        } catch (InterruptedException e) {
                            System.out.println(Thread.currentThread().getName() + ": Поймали catch потока consumer");
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );

        consumer.start();
        producer.start();
        producer.join();
//        consumer.interrupt();
        consumer.join();

    }
}
