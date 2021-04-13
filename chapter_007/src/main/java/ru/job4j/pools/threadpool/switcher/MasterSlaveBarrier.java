package ru.job4j.pools.threadpool.switcher;

public class MasterSlaveBarrier {

    private int flag = 0;

    public synchronized void tryMaster() {
        while (flag != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        flag = 1;
        System.out.println("Thread A");
        notify();
    }

    public synchronized void trySlave() {
        while (flag != 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        flag = 0;
        System.out.println("Thread B");
        notify();
    }
}