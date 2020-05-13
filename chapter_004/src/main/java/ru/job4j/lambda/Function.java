package ru.job4j.lambda;

public interface Function<T, R> {
    R apply(T t);
}