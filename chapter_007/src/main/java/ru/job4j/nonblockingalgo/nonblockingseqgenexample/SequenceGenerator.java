package ru.job4j.nonblockingalgo.nonblockingseqgenexample;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * http://java-online.ru/concurrent-atomic.xhtml
 */
public class SequenceGenerator {
    private static BigInteger multiplier;
    private AtomicReference<BigInteger> element;

    public SequenceGenerator() {
        if (multiplier == null) {
            multiplier = BigInteger.valueOf(2);
        }
        element = new AtomicReference<BigInteger>(BigInteger.ONE);
    }

    /**
     * Возвращает текущее значение
     * @return
     */
    public BigInteger next() {
        BigInteger value;  // текущее значение
        BigInteger next;  // следующее значение
        do {
            value = element.get();
            next = value.multiply(multiplier);
        } while (!element.compareAndSet(value, next));
        return value;
    }
}
