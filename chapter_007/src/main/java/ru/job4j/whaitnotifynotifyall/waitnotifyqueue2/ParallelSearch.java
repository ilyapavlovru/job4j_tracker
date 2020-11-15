package ru.job4j.whaitnotifynotifyall.waitnotifyqueue2;

public class ParallelSearch {
    public static void main(String[] args) {

        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>();

        // потребитель пытается получтить элементы пока значение флага общего ресурса равно true
        final Thread consumer = new Thread(
                () -> {
                    while (queue.isFlag()) {
                        System.out.println(Thread.currentThread().getName() + ": Consumer пытается извлечь элемент..");
                        queue.poll();
                    }
                }
        );
        consumer.start();

        // производитель 3 раза добавляет число в очередь
        new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        queue.offer(index);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.off();
                }

        ).start();
    }
}
