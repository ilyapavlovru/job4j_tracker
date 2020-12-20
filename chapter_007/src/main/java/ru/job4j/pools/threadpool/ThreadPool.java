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
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
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
                    threadPool.shutdown();
                }
        );

        for (int i = 0; i < size; i++) {
            threads.add(new Thread(
                    () -> {
                        while (tasks.isFlag() || tasks.getQueueSize() > 0) {
                            try {
                                MyTask task = (MyTask) tasks.poll();
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
