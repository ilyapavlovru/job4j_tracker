package ru.job4j.synchronize;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CountTest {

    /**
     * Класс описывает нить со счетчиком
     */
    private class ThreadCount extends Thread {
        private final Count count;

        private ThreadCount(final Count count) {
            this.count = count;
        }

        @Override
        public void run() {
            this.count.increment();
        }
    }

    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        // создаем счетчик
        final Count count = new Count();
        // создаем нити
        Thread first = new ThreadCount(count);
        Thread second = new ThreadCount(count);
        // запускаем нити
        first.start();
        second.start();
        // заставляем главную нить дождаться выполнения наших нитей
        first.join();
        second.join();
        //проверяем результат
        assertThat(count.get(), is(2));
    }
}