package ru.job4j.synchronize;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.collection.DynamicList;

import java.util.ArrayList;
import java.util.Iterator;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {

    @GuardedBy("this")
    private DynamicList<T> dynamicList = new DynamicList<>();

    public synchronized void add(T value) {
        this.dynamicList.add(value);
    }

    public synchronized T get(int index) {
        return this.dynamicList.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.dynamicList.iterator());
    }

    private Iterator<T> copy(Iterator<T> iterator) {
        ArrayList<T> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        return result.iterator();
    }
}
