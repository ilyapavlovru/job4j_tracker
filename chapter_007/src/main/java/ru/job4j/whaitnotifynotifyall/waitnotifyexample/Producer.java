package ru.job4j.whaitnotifynotifyall.waitnotifyexample;

// Класс Производитель
public class Producer implements Runnable {
    Store store;

    public Producer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 1; i < 6; i++) {
            System.out.println(Thread.currentThread().getName() + ": Producer пытается добавить продукт... счетчик i равен " + i);
            store.put();
        }
    }
}
