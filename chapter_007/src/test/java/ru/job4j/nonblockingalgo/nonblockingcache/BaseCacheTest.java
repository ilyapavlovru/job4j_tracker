package ru.job4j.nonblockingalgo.nonblockingcache;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class BaseCacheTest {

    private static Base staticModel1 = new Base(1, 0);

    @Test
    public void updateInOrderWithOptimisticException() throws InterruptedException {
        BaseCache baseCache = new BaseCache();
        AtomicReference<Exception> ex = new AtomicReference<>();

        Base model1 = new Base(1, 0);
        Base model2 = new Base(2, 0);
        baseCache.add(model1);
        baseCache.add(model2);

        Thread thread1 = new Thread(
                () -> {
                    try {
                        if (!baseCache.update(model1)) {
                            throw new OptimisticException("Throw OptimisticException in Thread");
                        }
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );

        Thread thread2 = new Thread(
                () -> {
                    try {
                        if (!baseCache.update(model1)) {
                            throw new OptimisticException("Throw OptimisticException in Thread");
                        }
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        Assert.assertThat(ex.get().getMessage(), is("Throw OptimisticException in Thread"));
    }

    @Test
    public void updateInOrderWithoutOptimisticException() throws InterruptedException {
        BaseCache baseCache = new BaseCache();
        AtomicReference<Exception> ex = new AtomicReference<>();

        baseCache.add(staticModel1);

        Thread thread1 = new Thread(
                () -> {
                    try {
                        if (!baseCache.update(staticModel1)) {
                            throw new OptimisticException("Throw OptimisticException in Thread");
                        }
                        staticModel1 = baseCache.getBases().get(staticModel1.id);
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );

        Thread thread2 = new Thread(
                () -> {
                    try {
                        Thread.sleep(1000);
                        if (!baseCache.update(staticModel1)) {
                            throw new OptimisticException("Throw OptimisticException in Thread");
                        }
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        Assert.assertThat(ex.get(), is(nullValue()));
    }
}
