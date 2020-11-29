package ru.job4j.nonblockingalgo.nonblockingcache;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class BaseCache {
    @GuardedBy("this")
    private ConcurrentHashMap<Integer, Base> bases = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    synchronized boolean add(Base model) {
        if (!bases.containsValue(model)) {
            bases.put(id.incrementAndGet(), model);
            return true;
        }
        return false;
    }

    boolean update(Base model) {
        System.out.println(Thread.currentThread().getName() + ": is trying to update model " + model);
        int id = model.id;
        int ver = model.version;  // текущая версия
        try {
            bases.computeIfPresent(id, (key, value) -> {
                        if (value.version == ver) {
                            System.out.println(Thread.currentThread().getName() + ": update successful");
                            System.out.println(Thread.currentThread().getName() + ": model before: id = " + id + ", ver = " + ver);
                            System.out.println(Thread.currentThread().getName() + ": model after: id = " + id + ", ver = " + (ver + 1));
                            return new Base(id, ver + 1);
                        } else {
                            System.out.println(Thread.currentThread().getName() + ": update is NOT successful");
                            throw new OptimisticException(Thread.currentThread().getName() + ": try to update but versions not equal");
                        }
                    }
            );
            System.out.println(Thread.currentThread().getName() + ": finished computeIfPresent. "
                    + "Updated model is " + bases.get(model.id));
            return true;
        } catch (OptimisticException e) {
            System.out.println(Thread.currentThread().getName() + ": in catch block of OptimisticException - try to update but versions not equal");
            return false;
        }
    }

    boolean delete(Base model) {
        int id = model.id;
        int ver = model.version;  // текущая версия
        try {
            // set new value is null and computeIfPresent method will remove the entry for the specified key
            bases.computeIfPresent(id, (key, value) -> {
                        if (value.version == ver) {
                            return null;
                        } else {
                            throw new OptimisticException("try to delete but versions not equal");
                        }
                    }
            );
            return true;
        } catch (OptimisticException e) {
            System.out.println("In catch block of OptimisticException - try to delete but versions not equal");
            return false;
        }
    }

    public ConcurrentHashMap<Integer, Base> getBases() {
        return bases;
    }
}
