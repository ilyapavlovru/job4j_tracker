package ru.job4j.whaitnotifynotifyall.waitnotify;

public class CountBarrierApp {
    public static void main(String[] args) {
        CountBarrier countBarrier = new CountBarrier(3);
        ThreadFirst threadFirst = new ThreadFirst(countBarrier);
        ThreadSecond threadSecond = new ThreadSecond(countBarrier);
        new Thread(threadFirst).start();
        new Thread(threadSecond).start();
    }
}
