package ru.job4j.synchronize.waitnotifyexample;

public class Consumer implements Runnable {
    Store store;

    public Consumer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 1; i < 6; i++) {
            System.out.println(Thread.currentThread().getName() + ": Consumer пытается забрать продукт... счетчик i равен " + i);
            store.get();
        }
    }
}
