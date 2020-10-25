package ru.job4j.shared;

import net.jcip.annotations.ThreadSafe;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@ThreadSafe
public class UserCache {
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    public void add(User user) {
        users.put(id.incrementAndGet(), User.of(user.getName()));
    }

    public User findById(int id) {
        return User.of(users.get(id).getName());
    }

    public List<User> findAllViaForeach() {
        List<User> rst = new ArrayList<>();
        for (Integer key : users.keySet()) {
            User value = users.get(key);
            rst.add(User.of(value.getName()));
        }
        return rst;
    }

    public List<User> findAllViaStreams() {
        return users.values().stream()
                .map(user -> User.of(user.getName()))
                .collect(Collectors.toList());
    }
}
