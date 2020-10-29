package ru.job4j.synchronize.userstorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    synchronized boolean add(User user) {
        if (!users.containsValue(user)) {
            users.put(id.incrementAndGet(), user);
            return true;
        }
        return false;
    }

    synchronized boolean update(User user) {
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            return true;
        } else {
            return false;
        }
    }

    synchronized boolean delete(User user) {
        return users.values().removeIf(value -> value.equals(user));
    }

    synchronized boolean transfer(int fromId, int toId, int amount) {
        User userFrom = users.get(fromId);
        User userTo = users.get(toId);
        if (userFrom != null && userTo != null && userFrom.getAmount() >= amount) {
            userFrom.setAmount(userFrom.getAmount() - amount);
            userTo.setAmount(userFrom.getAmount() + amount);
            return true;
        }
        return false;
    }

    synchronized public ConcurrentHashMap<Integer, User> getUsers() {
        return users;
    }
}
