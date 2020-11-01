package ru.job4j.synchronize.waitnotify;

/**
 * Поток который добавляет увеличивает значение count общего ресурса
 */
class ThreadFirst implements Runnable{

    CountBarrier countBarrier;

    public ThreadFirst(CountBarrier countBarrier) {
        this.countBarrier = countBarrier;
    }

    @Override
    public void run(){
        for (int i = 1; i < 6; i++) {
            System.out.println("Inside run of Producer");
            countBarrier.count();
        }
    }
}