package ru.job4j.whaitnotifynotifyall.waitnotify;

/**
 * Поток, который выполняет метод await общего ресурса
 * при условии, что поле count общего ресурса равно total (константа общего ресурса)
 */
class ThreadSecond implements Runnable {

    CountBarrier countBarrier;

    public ThreadSecond(CountBarrier countBarrier) {
        this.countBarrier = countBarrier;
    }

    @Override
    public void run(){
        for (int i = 1; i < 6; i++) {
            System.out.println("Inside run of Consumer");
            countBarrier.await();
        }
    }
}