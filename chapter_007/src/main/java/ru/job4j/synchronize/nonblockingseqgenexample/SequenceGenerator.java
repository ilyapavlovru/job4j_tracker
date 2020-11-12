package ru.job4j.synchronize.nonblockingseqgenexample;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * http://java-online.ru/concurrent-atomic.xhtml
 */
public class SequenceGenerator {
    private static BigInteger MULTIPLIER;
    private AtomicReference<BigInteger> element;

    public SequenceGenerator() {
        if (MULTIPLIER == null) {
            MULTIPLIER = BigInteger.valueOf(2);
        }
        element = new AtomicReference<BigInteger>(BigInteger.ONE);
    }

    /**
     * Возвращает текущее значение
     * @return
     */
    public BigInteger next() {
        BigInteger value;
        BigInteger next;  // следующее значение
        do {
            value = element.get();
            next = value.multiply(MULTIPLIER);
        } while (!element.compareAndSet(value, next));
        return value;
    }
}
