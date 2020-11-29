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
        System.out.println("baseCache:" + baseCache.getBases());

        Thread thread1 = new Thread(
                () -> {
                    try {
                        System.out.println(Thread.currentThread().getName() + ": is started");
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
                        System.out.println(Thread.currentThread().getName() + ": is started");
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

        System.out.println("baseCache:" + baseCache.getBases());
        System.out.println(ex.get().getMessage());

        Assert.assertThat(ex.get().getMessage(), is("Throw OptimisticException in Thread"));
    }

    @Test
    public void updateInOrderWithoutOptimisticException() throws InterruptedException {
        BaseCache baseCache = new BaseCache();
        AtomicReference<Exception> ex = new AtomicReference<>();

        baseCache.add(staticModel1);

        System.out.println("baseCache:" + baseCache.getBases());

        Thread thread1 = new Thread(
                () -> {
                    try {
                        System.out.println(Thread.currentThread().getName() + ": is started");
                        if (!baseCache.update(staticModel1)) {
                            throw new OptimisticException("Throw OptimisticException in Thread");
                        }
                        staticModel1 = baseCache.getBases().get(staticModel1.id);
                        System.out.println(Thread.currentThread().getName() + ": staticModel1 = " + staticModel1);

                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );

        Thread thread2 = new Thread(
                () -> {
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + ": is started");
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

        System.out.println("baseCache:" + baseCache.getBases());

        Assert.assertThat(ex.get(), is(nullValue()));
    }
}
