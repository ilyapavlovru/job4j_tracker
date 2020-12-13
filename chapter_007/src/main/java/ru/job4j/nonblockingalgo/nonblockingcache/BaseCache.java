package ru.job4j.nonblockingalgo.nonblockingcache;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class BaseCache {
    private final ConcurrentHashMap<Integer, Base> bases = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    boolean add(Base model) {
        return bases.putIfAbsent(id.incrementAndGet(), model) == null;
    }

    boolean update(Base model) {
        int id = model.id;
        int ver = model.version;  // текущая версия
        bases.computeIfPresent(id, (key, value) -> {
                    if (value.version == ver) {
                        return new Base(id, ver + 1);
                    }
                    throw new OptimisticException("Throw OptimisticException in Thread");
                }
        );
        return true;
    }

    boolean delete(Base model) {
        return bases.values().removeIf(value -> value.equals(model));
    }

    public ConcurrentHashMap<Integer, Base> getBases() {
        return bases;
    }
}
