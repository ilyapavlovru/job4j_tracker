package ru.job4j.pools.threadpool.customthreadpool;

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
