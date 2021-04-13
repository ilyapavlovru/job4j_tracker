package ru.job4j.pools.threadpool.switcher;

public class Second implements Runnable {

    private final MasterSlaveBarrier masterSlaveBarrier;

    Second(MasterSlaveBarrier masterSlaveBarrier) {
        this.masterSlaveBarrier = masterSlaveBarrier;
    }
    public void run() {
        while (true) {
            masterSlaveBarrier.trySlave();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}