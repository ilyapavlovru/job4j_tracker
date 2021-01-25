package ru.job4j.pools.threadpool.switcher;

public class Switcher {
    public static void main(String[] args) {
        MasterSlaveBarrier masterSlaveBarrier = new MasterSlaveBarrier();
        First first = new First(masterSlaveBarrier);
        Second second = new Second(masterSlaveBarrier);
        new Thread(first).start();
        new Thread(second).start();
    }
}