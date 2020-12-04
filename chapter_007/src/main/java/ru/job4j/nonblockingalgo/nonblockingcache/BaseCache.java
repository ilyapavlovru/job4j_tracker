package ru.job4j.nonblockingalgo.nonblockingcache;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class BaseCache {
    private ConcurrentHashMap<Integer, Base> bases = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    boolean add(Base model) {
        return bases.putIfAbsent(id.incrementAndGet(), model) == null;
    }

    boolean update(Base model) {
        int id = model.id;
        int ver = model.version;  // текущая версия
        try {
            bases.computeIfPresent(id, (key, value) -> {
                        if (value.version == ver) {
                            return new Base(id, ver + 1);
                        }
                        throw new OptimisticException(Thread.currentThread().getName() + ": try to update but versions not equal");
                    }
            );
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
