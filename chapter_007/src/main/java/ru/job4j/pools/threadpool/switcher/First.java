package ru.job4j.pools.threadpool.switcher;

public class First implements Runnable{

    MasterSlaveBarrier masterSlaveBarrier;

    First(MasterSlaveBarrier masterSlaveBarrier){
        this.masterSlaveBarrier = masterSlaveBarrier;
    }
    public void run(){
        while (true) {
            masterSlaveBarrier.tryMaster();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}