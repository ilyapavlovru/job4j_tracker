package ru.job4j.whaitnotifynotifyall.waitnotifyexample;

// класс Магазин, хранящий произведенные продукты
public class Store {
    private int product = 0;

    public synchronized void get() {
        System.out.println(Thread.currentThread().getName() + ": Покупатель пытается купить 1 продукт. " + "Продуктов на складе: " + product);
        while (product < 1) {
            System.out.println(Thread.currentThread().getName() + ": Мало продуктов! " + "Продуктов на складе: " + product);
            try {
                System.out.println(Thread.currentThread().getName() + ": Покупатель ожидает..." + "Продуктов на складе: " + product);
                wait();
            } catch (InterruptedException e){
            }
        }
        product--;
        System.out.println(Thread.currentThread().getName() + ": Покупатель купил 1 продукт. " + "Продуктов на складе: " + product);
        notify();
    }

    public synchronized void put() {
        System.out.println(Thread.currentThread().getName() + ": Производитель пытается добавить 1 продукт. " + "Продуктов на складе: " + product);
        while (product >= 3) {
            System.out.println(Thread.currentThread().getName() + ": Много продуктов! " + "Продуктов на складе: " + product);
            try {
                System.out.println(Thread.currentThread().getName() + ": Производитель ожидает..." + "Продуктов на складе: " + product);
                wait();
            } catch (InterruptedException e) {
            }
        }
        product++;
        System.out.println(Thread.currentThread().getName() + ": Производитель добавил 1 продукт. " + "Продуктов на складе: " + product);
        notify();
    }
}
