package ru.job4j.synchronize.nonblockingalgorithm;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CASCountTest {

    @Test
    public void whenOnceIncrementThen1Get() {
        CASCount<Integer> casCount = new CASCount<>();
        casCount.increment();
        assertThat(casCount.get(), is(1));
    }

    @Test
    public void whenTwiceIncrementThen2Get() {
        CASCount<Integer> casCount = new CASCount<>();
        casCount.increment();
        casCount.increment();
        assertThat(casCount.get(), is(2));
    }
}
