package ru.job4j.shared;

import net.jcip.annotations.Immutable;

/**
 * Immutable class example
 * @param <T>
 */
@Immutable
public class Node<T> {
    private final Node next;
    private final T value;

    public Node(Node next, T value) {
        this.next = next;
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public Node<T> setNext(Node next) {
        return new Node<>(next, this.value);
    }

    public T getValue() {
        return value;
    }

    public Node<T> setValue(T value) {
        return new Node<>(this.next, value);
    }
}
