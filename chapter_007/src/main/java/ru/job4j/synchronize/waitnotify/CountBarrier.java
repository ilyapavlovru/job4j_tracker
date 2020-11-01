package ru.job4j.synchronize.waitnotify;

public class CountBarrier {
    private final Object monitor = this;
    private final int total;
    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            this.count++;
            monitor.notify();
            System.out.println("Поток " + Thread.currentThread().getName() + ". Поток добавил 1 к значению счетчика count. Значение count: " + count);
        }
    }

    public void await() {
        synchronized (monitor) {
            while (count != total) {
                System.out.println("Поток " + Thread.currentThread().getName() + ": count != total ==> monitor.wait()");
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            this.count++;
            System.out.println("Поток " + Thread.currentThread().getName() + ": count == total. Поток выполнил count++ внутри метода await. " + "Значение count: " + count);
            monitor.notify();
        }
    }
}
