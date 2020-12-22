package ru.job4j.pools.threadpool;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    public void work(Runnable job) {
        tasks.offer(job);
    }

    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        int size = Runtime.getRuntime().availableProcessors();
        final Thread producer = new Thread(
                () -> {
                    for (int index = 0; index != 5; index++) {
                        threadPool.work(new MyTask(5000));
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    threadPool.tasks.off();
                }
        );

        for (int i = 0; i < size; i++) {
            threadPool.threads.add(new Thread(
                    () -> {
                        while (threadPool.tasks.isFlag() || threadPool.tasks.getQueueSize() > 0) {
                            try {
                                MyTask task = (MyTask) threadPool.tasks.poll();
                                if (task != null) {
                                    task.run();
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
            ));
        }

        for (Thread thread : threadPool.threads) {
            thread.start();
        }

        producer.start();
        producer.join();

        for (Thread thread : threadPool.threads) {
            thread.join();
        }
        threadPool.shutdown();
    }
}
